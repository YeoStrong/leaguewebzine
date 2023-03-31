package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.lw.dao.VcommentDao;
import com.lec.lw.dto.VcommentDto;
import com.lec.lw.dto.MemberDto;

public class VboardCommentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int vnum = Integer.parseInt(request.getParameter("vnum"));
		String vccontent = request.getParameter("vccontent");
		HttpSession httpSession = request.getSession();
		MemberDto member = (MemberDto)httpSession.getAttribute("member");
		String mid = member.getMid();
		String mnickname = member.getMnickname();
		VcommentDao vcDao = VcommentDao.getInstance();
		VcommentDto vcDto = new VcommentDto(0, mid, mnickname, vnum, vccontent, null);
		request.setAttribute("vcommentWriteResult", vcDao.writeVcomment(vcDto));
	}

}
