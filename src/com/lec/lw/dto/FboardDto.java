package com.lec.lw.dto;

import java.sql.Timestamp;

public class FboardDto {
	private int fnum;
	private String ftitle;
	private String fcontent;
	private String fimage;
	private Timestamp fdate;
	private int fhit;
	private int fgroup;
	private int fstep;
	private int findent;
	private String mid;
	private String mnickname;
	private String fip;
	public FboardDto() {}
	public FboardDto(int fnum, String ftitle, String fcontent, String fimage, Timestamp fdate, int fhit, int fgroup,
			int fstep, int findent, String mid, String mnickname, String fip) {
		this.fnum = fnum;
		this.ftitle = ftitle;
		this.fcontent = fcontent;
		this.fimage = fimage;
		this.fdate = fdate;
		this.fhit = fhit;
		this.fgroup = fgroup;
		this.fstep = fstep;
		this.findent = findent;
		this.mid = mid;
		this.mnickname = mnickname;
		this.fip = fip;
	}
	public int getFnum() {
		return fnum;
	}
	public void setFnum(int fnum) {
		this.fnum = fnum;
	}
	public String getFtitle() {
		return ftitle;
	}
	public void setFtitle(String ftitle) {
		this.ftitle = ftitle;
	}
	public String getFcontent() {
		return fcontent;
	}
	public void setFcontent(String fcontent) {
		this.fcontent = fcontent;
	}
	public String getFimage() {
		return fimage;
	}
	public void setFimage(String fimage) {
		this.fimage = fimage;
	}
	public Timestamp getFdate() {
		return fdate;
	}
	public void setFdate(Timestamp fdate) {
		this.fdate = fdate;
	}
	public int getFhit() {
		return fhit;
	}
	public void setFhit(int fhit) {
		this.fhit = fhit;
	}
	public int getFgroup() {
		return fgroup;
	}
	public void setFgroup(int fgroup) {
		this.fgroup = fgroup;
	}
	public int getFstep() {
		return fstep;
	}
	public void setFstep(int fstep) {
		this.fstep = fstep;
	}
	public int getFindent() {
		return findent;
	}
	public void setFindent(int findent) {
		this.findent = findent;
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
	public String getFip() {
		return fip;
	}
	public void setFip(String fip) {
		this.fip = fip;
	}
	@Override
	public String toString() {
		return "FboardDto [fnum=" + fnum + ", ftitle=" + ftitle + ", fcontent=" + fcontent + ", fimage=" + fimage
				+ ", fdate=" + fdate + ", fhit=" + fhit + ", fgroup=" + fgroup + ", fstep=" + fstep + ", findent="
				+ findent + ", mid=" + mid + ", mnickname=" + mnickname + ", fip=" + fip + "]";
	}
}
