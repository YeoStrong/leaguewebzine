package com.lec.lw.dto;

import java.sql.Timestamp;

public class GboardDto {
	private int gnum;
	private String gtitle;
	private String gcontent;
	private String gfile1;
	private String gfile2;
	private String gfile3;
	private Timestamp gdate;
	private int ghit;
	private int grating;
	private String mid;
	private String mnickname;
	private String cname;
	private String gip;
	public GboardDto() {}
	public GboardDto(int gnum, String gtitle, String gcontent, String gfile1, String gfile2, String gfile3,
			Timestamp gdate, int ghit, int grating, String mid, String mnickname, String cname, String gip) {
		this.gnum = gnum;
		this.gtitle = gtitle;
		this.gcontent = gcontent;
		this.gfile1 = gfile1;
		this.gfile2 = gfile2;
		this.gfile3 = gfile3;
		this.gdate = gdate;
		this.ghit = ghit;
		this.grating = grating;
		this.mid = mid;
		this.mnickname = mnickname;
		this.cname = cname;
		this.gip = gip;
	}
	public int getGnum() {
		return gnum;
	}
	public void setGnum(int gnum) {
		this.gnum = gnum;
	}
	public String getGtitle() {
		return gtitle;
	}
	public void setGtitle(String gtitle) {
		this.gtitle = gtitle;
	}
	public String getGcontent() {
		return gcontent;
	}
	public void setGcontent(String gcontent) {
		this.gcontent = gcontent;
	}
	public String getGfile1() {
		return gfile1;
	}
	public void setGfile1(String gfile1) {
		this.gfile1 = gfile1;
	}
	public String getGfile2() {
		return gfile2;
	}
	public void setGfile2(String gfile2) {
		this.gfile2 = gfile2;
	}
	public String getGfile3() {
		return gfile3;
	}
	public void setGfile3(String gfile3) {
		this.gfile3 = gfile3;
	}
	public Timestamp getGdate() {
		return gdate;
	}
	public void setGdate(Timestamp gdate) {
		this.gdate = gdate;
	}
	public int getGhit() {
		return ghit;
	}
	public void setGhit(int ghit) {
		this.ghit = ghit;
	}
	public int getGrating() {
		return grating;
	}
	public void setGrating(int grating) {
		this.grating = grating;
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
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getGip() {
		return gip;
	}
	public void setGip(String gip) {
		this.gip = gip;
	}
	@Override
	public String toString() {
		return "GboardDto [gnum=" + gnum + ", gtitle=" + gtitle + ", gcontent=" + gcontent + ", gfile1=" + gfile1
				+ ", gfile2=" + gfile2 + ", gfile3=" + gfile3 + ", gdate=" + gdate + ", ghit=" + ghit + ", grating="
				+ grating + ", mid=" + mid + ", mnickname=" + mnickname + ", cname=" + cname + ", gip=" + gip + "]";
	}
}
