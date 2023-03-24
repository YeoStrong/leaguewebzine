package com.lec.lw.dto;

import java.sql.Timestamp;

public class FcommentDto {
	private int fcnum;
	private String mid;
	private String mnickname;
	private int fnum;
	private String fccontent;
	private Timestamp fcdate;
	public FcommentDto() {}
	public FcommentDto(int fcnum, String mid, String mnickname, int fnum, String fccontent, Timestamp fcdate) {
		this.fcnum = fcnum;
		this.mid = mid;
		this.mnickname = mnickname;
		this.fnum = fnum;
		this.fccontent = fccontent;
		this.fcdate = fcdate;
	}
	public int getFcnum() {
		return fcnum;
	}
	public void setFcnum(int fcnum) {
		this.fcnum = fcnum;
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
	public int getFnum() {
		return fnum;
	}
	public void setFnum(int fnum) {
		this.fnum = fnum;
	}
	public String getFccontent() {
		return fccontent;
	}
	public void setFccontent(String fccontent) {
		this.fccontent = fccontent;
	}
	public Timestamp getFcdate() {
		return fcdate;
	}
	public void setFcdate(Timestamp fcdate) {
		this.fcdate = fcdate;
	}
	@Override
	public String toString() {
		return "FcommentDto [fcnum=" + fcnum + ", mid=" + mid + ", mnickname=" + mnickname + ", fnum=" + fnum
				+ ", fccontent=" + fccontent + ", fcdate=" + fcdate + "]";
	}
}
