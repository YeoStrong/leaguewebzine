package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.lw.dao.VcommentDao;
import com.lec.lw.dto.VcommentDto;
import com.lec.lw.dto.MemberDto;

public class VboardCommentModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String vccontent = request.getParameter("vccontent");
		int vcnum = Integer.parseInt(request.getParameter("vcnum"));
		HttpSession httpSession = request.getSession();
		MemberDto member = (MemberDto)httpSession.getAttribute("member");
		String mid = member.getMid();
		String mnickname = member.getMnickname();
		VcommentDao vcDao = VcommentDao.getInstance();
		VcommentDto vcDto = new VcommentDto(vcnum, mid, mnickname, 0, vccontent, null);
		request.setAttribute("vcommentModifyResult", vcDao.modifyVcomment(vcDto));
	}

}
