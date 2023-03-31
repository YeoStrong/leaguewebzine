package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.lw.dao.GcommentDao;
import com.lec.lw.dto.GcommentDto;
import com.lec.lw.dto.MemberDto;

public class GboardCommentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int gnum = Integer.parseInt(request.getParameter("gnum"));
		String gccontent = request.getParameter("gccontent");
		HttpSession httpSession = request.getSession();
		MemberDto member = (MemberDto)httpSession.getAttribute("member");
		String mid = member.getMid();
		String mnickname = member.getMnickname();
		GcommentDao gcDao = GcommentDao.getInstance();
		GcommentDto gcDto = new GcommentDto(0, mid, mnickname, gnum, gccontent, null);
		request.setAttribute("gcommentWriteResult", gcDao.writeGcomment(gcDto));
	}

}
