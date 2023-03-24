package com.lec.lw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lec.lw.dto.NboardDto;

public class NboardDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	private static NboardDao instance = new NboardDao();
	public static NboardDao getInstance() {
		return instance;
	}
	private NboardDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// (1) 글목록(startRow~endRow)
	public ArrayList<NboardDto> listNboard(int startRow, int endRow){
		ArrayList<NboardDto> dtos = new ArrayList<NboardDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT *" + 
				"    FROM (SELECT ROWNUM RN, B.*" + 
				"        FROM (SELECT N.*, ANICKNAME" + 
				"            FROM NBOARD N, ADMIN A" + 
				"            WHERE N.AID = A.AID" + 
				"            ORDER BY NNUM DESC) B)" + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int    nnum      = rs.getInt("nnum");
				String ntitle   = rs.getString("ntitle");
				String ncontent = rs.getString("ncontent");
				String nimage   = rs.getString("nimage");
				Timestamp ndate = rs.getTimestamp("ndate");
				int    nhit     = rs.getInt("nhit");
				String aid      = rs.getString("aid");
				String anickname      = rs.getString("anickname");
				dtos.add(new NboardDto(nnum, ntitle, ncontent, nimage, ndate, nhit, aid, anickname));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return dtos;
	}
	// (2) 글갯수
	public int getNboardTotCnt() {
		int totCnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) FROM NBOARD";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			totCnt = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return totCnt;
	}
	// (3) 글쓰기(원글쓰기)
	public int writeNboard(NboardDto dto) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO NBOARD (NNUM, NTITLE, NCONTENT, NIMAGE, AID)" + 
				"    VALUES(NBOARD_NNUM_SEQ.NEXTVAL, ?, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getNtitle());
			pstmt.setString(2, dto.getNcontent());
			pstmt.setString(3, dto.getNimage());
			pstmt.setString(4, dto.getAid());
			pstmt.executeUpdate();
			result = SUCCESS;
			System.out.println("원글쓰기 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " 원글쓰기 실패 :");
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return result;
	}
	// (4) hit 1회 올리기
	private void hitUpNboard(int nnum) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE NBOARD SET NHIT = NHIT + 1 WHERE NNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nnum);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " 조회수 up 실패");
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
	}
	// (5) 글번호로 글전체 내용(NboardDto) 가져오기
	public NboardDto contentNboard(int nnum) {
		NboardDto dto = null;
		hitUpNboard(nnum); // 글 상세보기 시 조회수 1 올리기
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT N.*, ANICKNAME FROM NBOARD N, ADMIN A  WHERE N.AID=A.AID AND NNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String ntitle = rs.getString("ntitle");
				String ncontent = rs.getString("ncontent");
				String nimage = rs.getString("nimage");
				Timestamp ndate = rs.getTimestamp("ndate");
				int    nhit = rs.getInt("nhit");
				String aid = rs.getString("aid");
				String anickname = rs.getString("anickname");
				dto = new NboardDto(nnum, ntitle, ncontent, nimage, ndate, nhit, aid, anickname);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return dto;
	}
	// (5)-1  글번호로 글전체 내용(NboardDto) 가져오기(수정 view용)
	public NboardDto modifyViewNboard(int nnum) {
		NboardDto dto = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT N.*, ANICKNAME FROM NBOARD N, ADMIN A  WHERE N.AID=A.AID AND NNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String ntitle = rs.getString("ntitle");
				String ncontent = rs.getString("ncontent");
				String nimage = rs.getString("nimage");
				Timestamp ndate = rs.getTimestamp("ndate");
				int    nhit = rs.getInt("nhit");
				String aid = rs.getString("aid");
				String anickname = rs.getString("anickname");
				dto = new NboardDto(nnum, ntitle, ncontent, nimage, ndate, nhit, aid, anickname);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return dto;
	}
	// (6) 글 수정하기
	public int modifyNboard(NboardDto dto) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE NBOARD " + 
				"    SET NTITLE = ?," + 
				"        NCONTENT = ?," + 
				"        NIMAGE = ?," + 
				"        NDATE = SYSDATE" + 
				"    WHERE NNUM = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getNtitle());
			pstmt.setString(2, dto.getNcontent());
			pstmt.setString(3, dto.getNimage());
			pstmt.setInt(4, dto.getNnum());
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "글수정 성공":"글번호(nnum) 오류");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "글 수정 실패 ");
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return result;
	}
	// (7) 글 삭제하기
	public int deleteNboard(int nnum) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM NBOARD WHERE NNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nnum);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "글삭제 성공":"글번호(nnum) 오류");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "글 삭제 실패 ");
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return result;
	}
}
