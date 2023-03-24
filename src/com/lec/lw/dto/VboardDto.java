package com.lec.lw.dto;

import java.sql.Timestamp;

public class VboardDto {
	private int vnum;
	private String cname;
	private String vtitle;
	private String vcontent;
	private String vvideo;
	private Timestamp vdate;
	private int vhit;
	private String mid;
	private String mnickname;
	private String vip;
	public VboardDto() {}
	public VboardDto(int vnum, String cname, String vtitle, String vcontent, String vvideo, Timestamp vdate, int vhit,
			String mid, String mnickname, String vip) {
		this.vnum = vnum;
		this.cname = cname;
		this.vtitle = vtitle;
		this.vcontent = vcontent;
		this.vvideo = vvideo;
		this.vdate = vdate;
		this.vhit = vhit;
		this.mid = mid;
		this.mnickname = mnickname;
		this.vip = vip;
	}
	public int getVnum() {
		return vnum;
	}
	public void setVnum(int vnum) {
		this.vnum = vnum;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getVtitle() {
		return vtitle;
	}
	public void setVtitle(String vtitle) {
		this.vtitle = vtitle;
	}
	public String getVcontent() {
		return vcontent;
	}
	public void setVcontent(String vcontent) {
		this.vcontent = vcontent;
	}
	public String getVvideo() {
		return vvideo;
	}
	public void setVvideo(String vvideo) {
		this.vvideo = vvideo;
	}
	public Timestamp getVdate() {
		return vdate;
	}
	public void setVdate(Timestamp vdate) {
		this.vdate = vdate;
	}
	public int getVhit() {
		return vhit;
	}
	public void setVhit(int vhit) {
		this.vhit = vhit;
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
	public String getVip() {
		return vip;
	}
	public void setVip(String vip) {
		this.vip = vip;
	}
	@Override
	public String toString() {
		return "VboardDto [vnum=" + vnum + ", cname=" + cname + ", vtitle=" + vtitle + ", vcontent=" + vcontent
				+ ", vvideo=" + vvideo + ", vdate=" + vdate + ", vhit=" + vhit + ", mid=" + mid + ", mnickname="
				+ mnickname + ", vip=" + vip + "]";
	}
}
