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
import com.lec.lw.dto.GcommentDto;

public class GcommentDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	private static GcommentDao instance = new GcommentDao();
	public static GcommentDao getInstance() {
		return instance;
	}
	private GcommentDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// (1) 1번글에 댓글 쓰기
	public int writeGcomment(GcommentDto dto) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO GCOMMENT (GCNUM, MID, GNUM, GCCONTENT)" + 
				"    VALUES(GCOMMENT_GCNUM_SEQ.NEXTVAL, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMid());
			pstmt.setInt(2, dto.getGnum());
			pstmt.setString(3, dto.getGccontent());
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
	public ArrayList<GcommentDto> listGcomment(int gnum){
		ArrayList<GcommentDto> dtos = new ArrayList<GcommentDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT G.*, MNICKNAME FROM GCOMMENT G, MEMBER M WHERE M.MID=G.MID AND GNUM=? ORDER BY GCDATE";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gnum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int gcnum = rs.getInt("gcnum");
				String mid      = rs.getString("mid");
				String mnickname= rs.getString("mnickname");
				String gccontent = rs.getString("gccontent");
				Timestamp gcdate = rs.getTimestamp("gcdate");
				dtos.add(new GcommentDto(gcnum, mid, mnickname, gnum, gccontent, gcdate));
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
	// (3) 댓글 수정 VIEW
	public GcommentDto gcommentModifyView(int gcnum) {
		GcommentDto dto = null;
		Connection      conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT G.*, MNICKNAME FROM GCOMMENT G, MEMBER M WHERE GCNUM=? AND G.MID=M.MID";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gcnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int gnum = rs.getInt("gnum");
				String gccontent = rs.getString("gccontent");
				String mid = rs.getString("mid");
				String mnickname = rs.getString("mnickname");
				Timestamp gcdate = rs.getTimestamp("gcdate");
				dto = new GcommentDto(gcnum, mid, mnickname, gnum, gccontent, gcdate);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {System.out.println(e.getMessage());}
		}
		return dto;
	}
	// (4) 댓글 수정
	public int modifyGcomment(GcommentDto dto) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE GCOMMENT SET GCCONTENT = ? WHERE GCNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getGccontent());
			pstmt.setInt(2, dto.getGcnum());
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "댓글수정 성공":"댓글번호(gcnum) 오류");
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
	// (5) 댓글 삭제
	public int deleteGcomment(int gcnum) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM GCOMMENT WHERE GCNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gcnum);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "댓글삭제 성공":"댓글번호(gcnum) 오류");
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
