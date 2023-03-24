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

import com.lec.lw.dto.VboardDto;

public class VboardDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	private static VboardDao instance = new VboardDao();
	public static VboardDao getInstance() {
		return instance;
	}
	private VboardDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// (1) 글목록(startRow~endRow)
	public ArrayList<VboardDto> listVboard(int startRow, int endRow){
		ArrayList<VboardDto> dtos = new ArrayList<VboardDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT *" + 
				"    FROM (SELECT ROWNUM RN, B.*" + 
				"        FROM (SELECT V.*, MNICKNAME" + 
				"            FROM VBOARD V, MEMBER M" + 
				"            WHERE V.MID = M.MID" + 
				"            ORDER BY VNUM DESC) B)" + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int    vnum      = rs.getInt("vnum");
				String cname    = rs.getString("cname");
				String vtitle   = rs.getString("vtitle");
				String vcontent = rs.getString("vcontent");
				String vvideo   = rs.getString("vvideo");
				Timestamp vdate = rs.getTimestamp("vdate");
				int    vhit     = rs.getInt("vhit");
				String mid      = rs.getString("mid");
				String mnickname= rs.getString("mnickname");
				String vip		= rs.getString("vip");
				dtos.add(new VboardDto(vnum, cname, vtitle, vcontent, vvideo, vdate, vhit, mid, mnickname, vip));
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
	public int getVboardTotCnt() {
		int totCnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) FROM VBOARD";
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
	public int writeVboard(VboardDto dto) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO VBOARD (VNUM, CNAME, VTITLE, VCONTENT, VVIDEO, MID, VIP)" + 
				"    VALUES (VBOARD_VNUM_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getCname());
			pstmt.setString(2, dto.getVtitle());
			pstmt.setString(3, dto.getVcontent());
			pstmt.setString(4, dto.getVvideo());
			pstmt.setString(6, dto.getMid());
			pstmt.setString(8, dto.getVip());
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
	private void hitUpVboard(int vnum) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE VBOARD SET VHIT = VHIT + 1 WHERE VNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vnum);
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
	// (5) 글번호(VNUM)로 글전체 내용(boardDto) 가져오기
	public VboardDto contentVboard(int vnum) {
		VboardDto dto = null;
		hitUpVboard(vnum); // 글 상세보기 시 조회수 1 올리기
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT V.*, MNICKNAME FROM VBOARD V, MEMBER M  WHERE V.MID=M.MID AND M.MID=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String cname = rs.getString("cname");
				String vtitle = rs.getString("vtitle");
				String vcontent = rs.getString("vcontent");
				String vvideo = rs.getString("vvideo");
				Timestamp vdate = rs.getTimestamp("vdate");
				int    vhit = rs.getInt("vhit");
				String mid = rs.getString("mid");
				String mnickname = rs.getString("mnickname");
				String vip = rs.getString("vip");
				dto = new VboardDto(vnum, cname, vtitle, vcontent, vvideo, vdate, vhit, mid, mnickname, vip);
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
	// (5)-1 글번호로 글전체 내용(VboardDto) 가져오기(수정 view용)
	public VboardDto modifyViewVboard(int vnum) {
		VboardDto dto = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT V.*, MNICKNAME FROM VBOARD V, MEMBER M  WHERE V.MID=M.MID AND M.MID=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String cname = rs.getString("cname");
				String vtitle = rs.getString("vtitle");
				String vcontent = rs.getString("vcontent");
				String vvideo = rs.getString("vvideo");
				Timestamp vdate = rs.getTimestamp("vdate");
				int    vhit = rs.getInt("vhit");
				String mid = rs.getString("mid");
				String mnickname = rs.getString("mnickname");
				String vip = rs.getString("vip");
				dto = new VboardDto(vnum, cname, vtitle, vcontent, vvideo, vdate, vhit, mid, mnickname, vip);
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
	public int modifyVboard(VboardDto dto) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE VBOARD " + 
				"    SET CNAME = ?," + 
				"        VTITLE = ?," + 
				"        VCONTENT = ?," + 
				"        VVIDEO = ?" + 
				"    WHERE VNUM = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getCname());
			pstmt.setString(2, dto.getVtitle());
			pstmt.setString(3, dto.getVcontent());
			pstmt.setString(4, dto.getVvideo());
			pstmt.setInt(5, dto.getVnum());
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "글수정 성공":"글번호(vnum) 오류");
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
	private void deleteVcomment(int vnum) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM VCOMMENT WHERE VNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vnum);
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
	public int deleteVboard(int vnum) {
		int result = FAIL;
		deleteVcomment(vnum);
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM VBOARD WHERE VNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vnum);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "글삭제 성공":"글번호(vnum) 오류");
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
