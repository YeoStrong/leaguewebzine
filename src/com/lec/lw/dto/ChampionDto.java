package com.lec.lw.dto;

public class ChampionDto {
	private String cname;
	private String crole;
	private String cline;
	private String cphoto;
	public ChampionDto() {}
	public ChampionDto(String cname, String crole, String cline, String cphoto) {
		this.cname = cname;
		this.crole = crole;
		this.cline = cline;
		this.cphoto = cphoto;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCrole() {
		return crole;
	}
	public void setCrole(String crole) {
		this.crole = crole;
	}
	public String getCline() {
		return cline;
	}
	public void setCline(String cline) {
		this.cline = cline;
	}
	public String getCphoto() {
		return cphoto;
	}
	public void setCphoto(String cphoto) {
		this.cphoto = cphoto;
	}
	@Override
	public String toString() {
		return "ChampionDto [cname=" + cname + ", crole=" + crole + ", cline=" + cline + ", cphoto=" + cphoto + "]";
	}
}
