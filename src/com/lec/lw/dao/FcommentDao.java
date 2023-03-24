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

import com.lec.lw.dto.FcommentDto;

public class FcommentDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	private static FcommentDao instance = new FcommentDao();
	public static FcommentDao getInstance() {
		return instance;
	}
	private FcommentDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// (1) 1번글에 댓글 쓰기
	public int writeFcomment(FcommentDto dto) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO FCOMMENT (FCNUM, MID, FNUM, FCCONTENT)" + 
				"    VALUES(FCOMMENT_FCNUM_SEQ.NEXTVAL, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMid());
			pstmt.setInt(2, dto.getFnum());
			pstmt.setString(3, dto.getFccontent());
			pstmt.executeUpdate();
			result = SUCCESS;
			System.out.println("댓글쓰기 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " 댓글쓰기 실패 :");
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
	// (2) 1번글에 달린 댓글 모두 출력
	public ArrayList<FcommentDto> listFcomment(int fnum){
		ArrayList<FcommentDto> dtos = new ArrayList<FcommentDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM FCOMMENT WHERE FNUM=? ORDER BY FCDATE";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fnum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int fcnum = rs.getInt("fcnum");
				String mid      = rs.getString("mid");
				String mnickname= rs.getString("mnickname");
				String fccontent = rs.getString("fccontent");
				Timestamp fcdate = rs.getTimestamp("fcdate");
				dtos.add(new FcommentDto(fcnum, mid, mnickname, fnum, fccontent, fcdate));
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
	// (3) 댓글 수정
	public int modifyFcomment(FcommentDto dto) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FCOMMENT SET FCCONTENT = ? WHERE FCNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getFccontent());
			pstmt.setInt(2, dto.getFcnum());
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "댓글수정 성공":"댓글번호(fcnum) 오류");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "댓글 수정 실패 ");
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
	// (4) 댓글 삭제
	public int deleteFcomment(int fcnum) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM FCOMMENT WHERE FCNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fcnum);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "댓글삭제 성공":"댓글번호(fcnum) 오류");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "댓글 삭제 실패 ");
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
