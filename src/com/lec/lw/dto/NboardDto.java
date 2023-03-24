package com.lec.lw.dto;

import java.sql.Timestamp;

public class NboardDto {
	private int    nnum;
	private String ntitle;
	private String ncontent;
	private String nimage;
	private Timestamp ndate;
	private int    nhit;
	private String aid;
	private String anickname;
	public NboardDto() {}
	public NboardDto(int nnum, String ntitle, String ncontent, String nimage, Timestamp ndate, int nhit, String aid,
			String anickname) {
		this.nnum = nnum;
		this.ntitle = ntitle;
		this.ncontent = ncontent;
		this.nimage = nimage;
		this.ndate = ndate;
		this.nhit = nhit;
		this.aid = aid;
		this.anickname = anickname;
	}
	public int getNnum() {
		return nnum;
	}
	public void setNnum(int nnum) {
		this.nnum = nnum;
	}
	public String getNtitle() {
		return ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public String getNimage() {
		return nimage;
	}
	public void setNimage(String nimage) {
		this.nimage = nimage;
	}
	public Timestamp getNdate() {
		return ndate;
	}
	public void setNdate(Timestamp ndate) {
		this.ndate = ndate;
	}
	public int getNhit() {
		return nhit;
	}
	public void setNhit(int nhit) {
		this.nhit = nhit;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getAnickname() {
		return anickname;
	}
	public void setAnickname(String anickname) {
		this.anickname = anickname;
	}
	@Override
	public String toString() {
		return "NboardDto [nnum=" + nnum + ", ntitle=" + ntitle + ", ncontent=" + ncontent + ", nimage=" + nimage
				+ ", ndate=" + ndate + ", nhit=" + nhit + ", aid=" + aid + ", anickname=" + anickname + "]";
	}
}
