package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.lw.dao.FcommentDao;
import com.lec.lw.dto.FcommentDto;
import com.lec.lw.dto.MemberDto;

public class FboardCommentModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String fccontent = request.getParameter("fccontent");
		int fcnum = Integer.parseInt(request.getParameter("fcnum"));
		HttpSession httpSession = request.getSession();
		MemberDto member = (MemberDto)httpSession.getAttribute("member");
		String mid = member.getMid();
		String mnickname = member.getMnickname();
		FcommentDao fcDao = FcommentDao.getInstance();
		FcommentDto fcDto = new FcommentDto(fcnum, mid, mnickname, 0, fccontent, null);
		request.setAttribute("fcommentModifyResult", fcDao.modifyFcomment(fcDto));
	}

}
