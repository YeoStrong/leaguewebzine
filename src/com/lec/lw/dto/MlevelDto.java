package com.lec.lw.dto;

public class MlevelDto {
	private int llevelnum;
	private String lname;
	public MlevelDto() {}
	public MlevelDto(int llevelnum, String lname) {
		this.llevelnum = llevelnum;
		this.lname = lname;
	}
	public int getLlevelnum() {
		return llevelnum;
	}
	public void setLlevelnum(int llevelnum) {
		this.llevelnum = llevelnum;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	@Override
	public String toString() {
		return "MlevelDto [llevelnum=" + llevelnum + ", lname=" + lname + "]";
	}
}
