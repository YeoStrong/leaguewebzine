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

import com.lec.lw.dto.VcommentDto;

public class VcommentDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	private static VcommentDao instance = new VcommentDao();
	public static VcommentDao getInstance() {
		return instance;
	}
	private VcommentDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// (1) 1번글에 댓글 쓰기
	public int writeVcomment(VcommentDto dto) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO VCOMMENT (VCNUM, MID, VNUM, VCCONTENT)" + 
				"    VALUES(VCOMMENT_VCNUM_SEQ.NEXTVAL, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMid());
			pstmt.setInt(2, dto.getVnum());
			pstmt.setString(3, dto.getVccontent());
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
	public ArrayList<VcommentDto> listVcomment(int vnum){
		ArrayList<VcommentDto> dtos = new ArrayList<VcommentDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM VCOMMENT WHERE VNUM=? ORDER BY VCDATE";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vnum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int vcnum = rs.getInt("vcnum");
				String mid      = rs.getString("mid");
				String mnickname= rs.getString("mnickname");
				String vccontent = rs.getString("vccontent");
				Timestamp vcdate = rs.getTimestamp("vcdate");
				dtos.add(new VcommentDto(vcnum, mid, mnickname, vnum, vccontent, vcdate));
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
	public int modifyVcomment(VcommentDto dto) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE VCOMMENT SET VCCONTENT = ? WHERE VCNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getVccontent());
			pstmt.setInt(2, dto.getVcnum());
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "댓글수정 성공":"댓글번호(vcnum) 오류");
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
	public int deleteVcomment(int vcnum) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM VCOMMENT WHERE VCNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vcnum);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "댓글삭제 성공":"댓글번호(vcnum) 오류");
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
