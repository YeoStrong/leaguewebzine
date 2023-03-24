package com.lec.lw.dto;

import java.sql.Timestamp;

public class VcommentDto {
	private int vcnum;
	private String mid;
	private String mnickname;
	private int vnum;
	private String vccontent;
	private Timestamp vcdate;
	public VcommentDto() {}
	public VcommentDto(int vcnum, String mid, String mnickname, int vnum, String vccontent, Timestamp vcdate) {
		this.vcnum = vcnum;
		this.mid = mid;
		this.mnickname = mnickname;
		this.vnum = vnum;
		this.vccontent = vccontent;
		this.vcdate = vcdate;
	}
	public int getVcnum() {
		return vcnum;
	}
	public void setVcnum(int vcnum) {
		this.vcnum = vcnum;
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
	public int getVnum() {
		return vnum;
	}
	public void setVnum(int vnum) {
		this.vnum = vnum;
	}
	public String getVccontent() {
		return vccontent;
	}
	public void setVccontent(String vccontent) {
		this.vccontent = vccontent;
	}
	public Timestamp getVcdate() {
		return vcdate;
	}
	public void setVcdate(Timestamp vcdate) {
		this.vcdate = vcdate;
	}
	@Override
	public String toString() {
		return "VcommentDto [vcnum=" + vcnum + ", mid=" + mid + ", mnickname=" + mnickname + ", vnum=" + vnum
				+ ", vccontent=" + vccontent + ", vcdate=" + vcdate + "]";
	}
}
