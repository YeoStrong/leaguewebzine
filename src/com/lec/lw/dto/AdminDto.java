package com.lec.lw.dto;

public class AdminDto {
	private String aid;
	private String apw;
	private String anickname;
	public AdminDto() {}
	public AdminDto(String aid, String apw, String anickname) {
		this.aid = aid;
		this.apw = apw;
		this.anickname = anickname;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getApw() {
		return apw;
	}
	public void setApw(String apw) {
		this.apw = apw;
	}
	public String getAnickname() {
		return anickname;
	}
	public void setAnickname(String anickname) {
		this.anickname = anickname;
	}
	@Override
	public String toString() {
		return "AdminDto [aid=" + aid + ", apw=" + apw + ", anickname=" + anickname + "]";
	}
}
