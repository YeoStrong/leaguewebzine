package com.lec.lw.dto;

import java.sql.Timestamp;

public class GcommentDto {
	private int gcnum;
	private String mid;
	private String mnickname;
	private int gnum;
	private String gccontent;
	private Timestamp gcdate;
	public GcommentDto() {}
	public GcommentDto(int gcnum, String mid, String mnickname, int gnum, String gccontent, Timestamp gcdate) {
		this.gcnum = gcnum;
		this.mid = mid;
		this.mnickname = mnickname;
		this.gnum = gnum;
		this.gccontent = gccontent;
		this.gcdate = gcdate;
	}
	public int getGcnum() {
		return gcnum;
	}
	public void setGcnum(int gcnum) {
		this.gcnum = gcnum;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMnickname() {
		return mnickname;
	}
	public void setMnickname(String mnickname) {
		this.mnickname = mnickname;
	}
	public int getGnum() {
		return gnum;
	}
	public void setGnum(int gnum) {
		this.gnum = gnum;
	}
	public String getGccontent() {
		return gccontent;
	}
	public void setGccontent(String gccontent) {
		this.gccontent = gccontent;
	}
	public Timestamp getGcdate() {
		return gcdate;
	}
	public void setGcdate(Timestamp gcdate) {
		this.gcdate = gcdate;
	}
	@Override
	public String toString() {
		return "GcommentDto [gcnum=" + gcnum + ", mid=" + mid + ", mnickname=" + mnickname + ", gnum=" + gnum
				+ ", gccontent=" + gccontent + ", gcdate=" + gcdate + "]";
	}
}
