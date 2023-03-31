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

import com.lec.lw.dto.FboardDto;

public class FboardDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	private static FboardDao instance = new FboardDao();
	public static FboardDao getInstance() {
		return instance;
	}
	private FboardDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// (1) 글목록(startRow~endRow)
	public ArrayList<FboardDto> listFboard(String schword, int startRow, int endRow){
		ArrayList<FboardDto> dtos = new ArrayList<FboardDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT *" + 
				"    FROM (SELECT ROWNUM RN, A.*" + 
				"        FROM (SELECT F.*, MNICKNAME" + 
				"            FROM FBOARD F, MEMBER M" + 
				"            WHERE F.MID = M.MID AND FTITLE LIKE '%'||TRIM(?)||'%'" + 
				"            ORDER BY FGROUP DESC, FSTEP) A)" + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, schword);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int    fnum      = rs.getInt("fnum");
				String ftitle   = rs.getString("ftitle");
				String fcontent = rs.getString("fcontent");
				String fimage   = rs.getString("fimage");
				Timestamp fdate = rs.getTimestamp("fdate");
				int    fhit     = rs.getInt("fhit");
				int    fgroup   = rs.getInt("fgroup");
				int    fstep    = rs.getInt("fstep");
				int    findent  = rs.getInt("findent");
				String mid      = rs.getString("mid");
				String mnickname= rs.getString("mnickname");
				String fip		= rs.getString("fip");
				dtos.add(new FboardDto(fnum, ftitle, fcontent, fimage, fdate, fhit, fgroup, fstep, findent, mid, mnickname, fip));
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
	public int fboardTotCnt(String schword) {
		int totCnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) CNT FROM FBOARD F, MEMBER M" + 
				"            WHERE F.MID = M.MID AND FTITLE LIKE '%'||TRIM(?)||'%'";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, schword);
			rs = pstmt.executeQuery();
			rs.next();
			totCnt = rs.getInt("cnt");
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
	public int writeFboard(FboardDto dto) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO FBOARD (FNUM, FTITLE, FCONTENT, FIMAGE, FGROUP, FSTEP, FINDENT, MID, FIP)" + 
				"    VALUES (FBOARD_FNUM_SEQ.NEXTVAL, ?, ?, ?, FBOARD_FNUM_SEQ.CURRVAL, 0, 0, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getFtitle());
			pstmt.setString(2, dto.getFcontent());
			pstmt.setString(3, dto.getFimage());
			pstmt.setString(4, dto.getMid());
			pstmt.setString(5, dto.getFip());
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
	private void hitUpFboard(int fnum) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FBOARD SET FHIT = FHIT + 1 WHERE FNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fnum);
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
	// (5) 글번호(fnum)로 글전체 내용(boardDto) 가져오기
	public FboardDto contentFboard(int fnum) {
		FboardDto dto = null;
		hitUpFboard(fnum); // 글 상세보기 시 조회수 1 올리기
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT F.*, MNICKNAME FROM FBOARD F, MEMBER M  WHERE F.MID=M.MID AND FNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String ftitle = rs.getString("ftitle");
				String fcontent = rs.getString("fcontent");
				String fimage = rs.getString("fimage");
				Timestamp fdate = rs.getTimestamp("fdate");
				int    fhit = rs.getInt("fhit");
				int    fgroup = rs.getInt("fgroup");
				int    fstep = rs.getInt("fstep");
				int    findent = rs.getInt("findent");
				String mid = rs.getString("mid");
				String mnickname = rs.getString("mnickname");
				String fip = rs.getString("fip");
				dto = new FboardDto(fnum, ftitle, fcontent, fimage, fdate, fhit, fgroup, fstep, findent, mid, mnickname, fip);
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
	// (5) 글번호(fnum)로 글전체 내용(boardDto) 가져오기(수정/답변 view 용)
		public FboardDto modifyViewFboard_replyViewFboard(int fnum) {
			FboardDto dto = null;
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			String sql = "SELECT F.*, MNICKNAME FROM FBOARD F, MEMBER M  WHERE F.MID=M.MID AND FNUM=?";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, fnum);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					String ftitle = rs.getString("ftitle");
					String fcontent = rs.getString("fcontent");
					String fimage = rs.getString("fimage");
					Timestamp fdate = rs.getTimestamp("fdate");
					int    fhit = rs.getInt("fhit");
					int    fgroup = rs.getInt("fgroup");
					int    fstep = rs.getInt("fstep");
					int    findent = rs.getInt("findent");
					String mid = rs.getString("mid");
					String mnickname = rs.getString("mnickname");
					String fip = rs.getString("fip");
					dto = new FboardDto(fnum, ftitle, fcontent, fimage, fdate, fhit, fgroup, fstep, findent, mid, mnickname, fip);
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
	public int modifyFboard(FboardDto dto) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FBOARD " + 
				"    SET FTITLE = ?," + 
				"        FCONTENT = ?," + 
				"        FIMAGE = ?" + 
				"    WHERE FNUM = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getFtitle());
			pstmt.setString(2, dto.getFcontent());
			pstmt.setString(3, dto.getFimage());
			pstmt.setInt(4, dto.getFnum());
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
	private void deleteFcomment(int fnum) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM FCOMMENT WHERE FNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fnum);
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
	public int deleteFboard(int fnum) {
		int result = FAIL;
		deleteFcomment(fnum);
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM FBOARD WHERE FNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fnum);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "글삭제 성공":"글번호(fnum) 오류");
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
	// (8) 답변글 쓰기 전 단계(원글의 fgroup과 같고, 원글의 fstep보다 크면 fstep을 하나 증가하기)
	private void preReplyFboardStep(int fgroup, int fstep) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FBOARD SET FSTEP = FSTEP + 1 WHERE FGROUP=? AND FSTEP>?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fgroup);
			pstmt.setInt(2, fstep);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " preReplyStep에서 오류");
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
	}
	// (9) 답변글 쓰기
	public int replyFboard(FboardDto dto) {
		int result = FAIL;
		preReplyFboardStep(dto.getFgroup(), dto.getFstep());
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO FBOARD (FNUM, FTITLE, FCONTENT, FIMAGE, FGROUP, FSTEP, FINDENT, MID, FIP)" + 
				"    VALUES (FBOARD_FNUM_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getFtitle());
			pstmt.setString(2, dto.getFcontent());
			pstmt.setString(3, dto.getFimage());
			pstmt.setInt(4, dto.getFgroup());
			pstmt.setInt(5, dto.getFstep() + 1);
			pstmt.setInt(6, dto.getFindent() + 1);
			pstmt.setString(7, dto.getMid());
			pstmt.setString(8, dto.getFip());
			pstmt.executeUpdate();
			result = SUCCESS;
			System.out.println("답변글쓰기 성공");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " 답변글쓰기 실패 ");
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
