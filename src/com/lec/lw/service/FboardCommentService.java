package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.lw.dao.FcommentDao;
import com.lec.lw.dto.FcommentDto;
import com.lec.lw.dto.MemberDto;

public class FboardCommentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int fnum = Integer.parseInt(request.getParameter("fnum"));
		String fccontent = request.getParameter("fccontent");
		HttpSession httpSession = request.getSession();
		MemberDto member = (MemberDto)httpSession.getAttribute("member");
		String mid = member.getMid();
		String mnickname = member.getMnickname();
		FcommentDao fcDao = FcommentDao.getInstance();
		FcommentDto fcDto = new FcommentDto(0, mid, mnickname, fnum, fccontent, null);
		request.setAttribute("fcommentWriteResult", fcDao.writeFcomment(fcDto));
	}

}
