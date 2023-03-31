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

import com.lec.lw.dto.GboardDto;

public class GboardDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	private static GboardDao instance = new GboardDao();
	public static GboardDao getInstance() {
		return instance;
	}
	private GboardDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// (1) 글목록(startRow~endRow)
	public ArrayList<GboardDto> listGboard(String schword, int startRow, int endRow){
		ArrayList<GboardDto> dtos = new ArrayList<GboardDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT *" + 
				"    FROM (SELECT ROWNUM RN, B.*" + 
				"        FROM (SELECT G.*, MNICKNAME" + 
				"            FROM GBOARD G, MEMBER M" + 
				"            WHERE G.MID = M.MID AND GTITLE LIKE  '%'||TRIM(?)||'%'" + 
				"            ORDER BY GNUM DESC) B)" + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, schword);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int    gnum      = rs.getInt("gnum");
				String gtitle   = rs.getString("gtitle");
				String gcontent = rs.getString("gcontent");
				String gfile1   = rs.getString("gfile1");
				String gfile2   = rs.getString("gfile2");
				String gfile3   = rs.getString("gfile3");
				Timestamp gdate = rs.getTimestamp("gdate");
				int    ghit     = rs.getInt("ghit");
				int    grating  = rs.getInt("grating");
				String mid      = rs.getString("mid");
				String mnickname= rs.getString("mnickname");
				String cname    = rs.getString("cname");
				String gip		= rs.getString("gip");
				dtos.add(new GboardDto(gnum, gtitle, gcontent, gfile1, gfile2, gfile3, gdate, ghit, grating, mid, mnickname, cname, gip));
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
	public int getGboardTotCnt(String schword) {
		int totCnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) CNT FROM GBOARD G, MEMBER M" + 
				"    WHERE G.MID = M.MID AND GTITLE LIKE '%'||TRIM(?)||'%'";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, schword);
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
	public int writeGboard(GboardDto dto) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO GBOARD (GNUM, GTITLE, GCONTENT, GFILE1, GFILE2, GFILE3, MID, CNAME, GIP)" + 
				"    VALUES (GBOARD_GNUM_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getGtitle());
			pstmt.setString(2, dto.getGcontent());
			pstmt.setString(3, dto.getGfile1());
			pstmt.setString(4, dto.getGfile2());
			pstmt.setString(5, dto.getGfile3());
			pstmt.setString(6, dto.getMid());
			pstmt.setString(7, dto.getCname());
			pstmt.setString(8, dto.getGip());
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
	private void hitUpGboard(int gnum) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE GBOARD SET GHIT = GHIT + 1 WHERE GNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gnum);
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
	// (5) 글번호로 글전체 내용(boardDto) 가져오기
	public GboardDto contentGboard(int gnum) {
		GboardDto dto = null;
		hitUpGboard(gnum); // 글 상세보기 시 조회수 1 올리기
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT G.*, MNICKNAME FROM GBOARD G, MEMBER M  WHERE G.MID=M.MID AND GNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String gtitle = rs.getString("gtitle");
				String gcontent = rs.getString("gcontent");
				String gfile1 = rs.getString("gfile1");
				String gfile2 = rs.getString("gfile2");
				String gfile3 = rs.getString("gfile3");
				Timestamp gdate = rs.getTimestamp("gdate");
				int    ghit = rs.getInt("ghit");
				int    grating = rs.getInt("grating");
				String mid = rs.getString("mid");
				String mnickname = rs.getString("mnickname");
				String cname = rs.getString("cname");
				String gip = rs.getString("gip");
				dto = new GboardDto(gnum, gtitle, gcontent, gfile1, gfile2, gfile3, gdate, ghit, grating, mid, mnickname, cname, gip);
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
	// (5)-1 글번호로 글전체 내용(GboardDto) 가져오기(수정 view용)
	public GboardDto modifyViewGboard(int gnum) {
		GboardDto dto = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT G.*, MNICKNAME FROM GBOARD G, MEMBER M  WHERE G.MID=M.MID AND GNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String gtitle = rs.getString("gtitle");
				String gcontent = rs.getString("gcontent");
				String gfile1 = rs.getString("gfile1");
				String gfile2 = rs.getString("gfile2");
				String gfile3 = rs.getString("gfile3");
				Timestamp gdate = rs.getTimestamp("gdate");
				int    ghit = rs.getInt("ghit");
				int    grating = rs.getInt("grating");
				String mid = rs.getString("mid");
				String mnickname = rs.getString("mnickname");
				String cname = rs.getString("cname");
				String gip = rs.getString("gip");
				dto = new GboardDto(gnum, gtitle, gcontent, gfile1, gfile2, gfile3, gdate, ghit, grating, mid, mnickname, cname, gip);
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
	public int modifyGboard(GboardDto dto) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE GBOARD " + 
				"    SET GTITLE = ?," + 
				"        GCONTENT = ?," + 
				"        GFILE1 = ?," + 
				"        GFILE2 = ?," + 
				"        GFILE3 = ?," + 
				"        CNAME = ?" + 
				"    WHERE GNUM = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getGtitle());
			pstmt.setString(2, dto.getGcontent());
			pstmt.setString(3, dto.getGfile1());
			pstmt.setString(4, dto.getGfile2());
			pstmt.setString(5, dto.getGfile3());
			pstmt.setString(6, dto.getCname());
			pstmt.setInt(7, dto.getGnum());
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "글수정 성공":"글번호(gnum) 오류");
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
	// (7)-1 글 삭제하기 전 댓글 선 삭제
	private void deleteGcomment(int gnum) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM GCOMMENT WHERE GNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gnum);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " 글삭제 전 댓글 삭제 실패");
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
	}
	// (7)-2 글 삭제하기
	public int deleteGboard(int gnum) {
		int result = FAIL;
		deleteGcomment(gnum);
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM GBOARD WHERE GNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gnum);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "글삭제 성공":"글번호(gnum) 오류");
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
