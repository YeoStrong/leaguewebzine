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
import com.lec.lw.dto.GboardDto;
import com.lec.lw.dto.NboardDto;
import com.lec.lw.dto.VboardDto;

public class MainDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	private static MainDao instance = new MainDao();
	public static MainDao getInstance() {
		return instance;
	}
	private MainDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// (1) nboard 상위 5개
	public ArrayList<NboardDto> mainNboard(){
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
				"    WHERE RN BETWEEN 1 AND 5";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int    nnum      = rs.getInt("nnum");
				String ntitle   = rs.getString("ntitle");
				String ncontent = rs.getString("ncontent");
				String nimage   = rs.getString("nimage");
				Timestamp ndate = rs.getTimestamp("ndate");
				int    nhit     = rs.getInt("nhit");
				String aid      = rs.getString("aid");
				String anickname= rs.getString("anickname");
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
	// (2) gboard 상위 5개
	public ArrayList<GboardDto> mainGboard(){
		ArrayList<GboardDto> dtos = new ArrayList<GboardDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT *" + 
				"    FROM (SELECT ROWNUM RN, B.*" + 
				"        FROM (SELECT G.*, MNICKNAME" + 
				"            FROM GBOARD G, MEMBER M" + 
				"            WHERE G.MID = M.MID" + 
				"            ORDER BY GNUM DESC) B)" + 
				"    WHERE RN BETWEEN 1 AND 5";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
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
	// (3) fboard 상위 5개
	public ArrayList<FboardDto> mainFboard(){
		ArrayList<FboardDto> dtos = new ArrayList<FboardDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT *" + 
				"    FROM (SELECT ROWNUM RN, A.*" + 
				"        FROM (SELECT F.*, MNICKNAME" + 
				"            FROM FBOARD F, MEMBER M" + 
				"            WHERE F.MID = M.MID" + 
				"            ORDER BY FNUM DESC) A)" + 
				"    WHERE RN BETWEEN 1 AND 5";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
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
	// (3) vboard 상위 5개
	public ArrayList<VboardDto> mainVboard(){
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
				"    WHERE RN BETWEEN 1 AND 5";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
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
}
