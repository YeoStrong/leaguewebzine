package com.lec.lw.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class MemberDto {
	private String mid;
	private String mnickname;
	private String mpw;
	private String mname;
	private String mtel;
	private Date mbirth;
	private String memail;
	private String mgender;
	private String mphoto;
	private String maddress;
	private Timestamp mrdate;
	private int    llevelnum;
	private int    mwithdrawal;
	public MemberDto() {}
	public MemberDto(String mid, String mnickname, String mpw, String mname, String mtel, Date mbirth,
			String memail, String mgender, String mphoto, String maddress, Timestamp mrdate, int llevelnum,
			int mwithdrawal) {
		this.mid = mid;
		this.mnickname = mnickname;
		this.mpw = mpw;
		this.mname = mname;
		this.mtel = mtel;
		this.mbirth = mbirth;
		this.memail = memail;
		this.mgender = mgender;
		this.mphoto = mphoto;
		this.maddress = maddress;
		this.mrdate = mrdate;
		this.llevelnum = llevelnum;
		this.mwithdrawal = mwithdrawal;
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
	public String getMpw() {
		return mpw;
	}
	public void setMpw(String mpw) {
		this.mpw = mpw;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMtel() {
		return mtel;
	}
	public void setMtel(String mtel) {
		this.mtel = mtel;
	}
	public Date getMbirth() {
		return mbirth;
	}
	public void setMbirth(Date mbirth) {
		this.mbirth = mbirth;
	}
	public String getMemail() {
		return memail;
	}
	public void setMemail(String memail) {
		this.memail = memail;
	}
	public String getMgender() {
		return mgender;
	}
	public void setMgender(String mgender) {
		this.mgender = mgender;
	}
	public String getMphoto() {
		return mphoto;
	}
	public void setMphoto(String mphoto) {
		this.mphoto = mphoto;
	}
	public String getMaddress() {
		return maddress;
	}
	public void setMaddress(String maddress) {
		this.maddress = maddress;
	}
	public Timestamp getMrdate() {
		return mrdate;
	}
	public void setMrdate(Timestamp mrdate) {
		this.mrdate = mrdate;
	}
	public int getLlevelnum() {
		return llevelnum;
	}
	public void setLlevelnum(int llevelnum) {
		this.llevelnum = llevelnum;
	}
	public int getMwithdrawal() {
		return mwithdrawal;
	}
	public void setMwithdrawal(int mwithdrawal) {
		this.mwithdrawal = mwithdrawal;
	}
	@Override
	public String toString() {
		return "MemberDto [mid=" + mid + ", mnickname=" + mnickname + ", mpw=" + mpw + ", mname=" + mname + ", mtel="
				+ mtel + ", mbirth=" + mbirth + ", memail=" + memail + ", mgender=" + mgender + ", mphoto=" + mphoto
				+ ", maddress=" + maddress + ", mrdate=" + mrdate + ", llevelnum=" + llevelnum + ", mwithdrawal="
				+ mwithdrawal + "]";
	}
}
