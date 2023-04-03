package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.lw.dao.MemberDao;
import com.lec.lw.dto.MemberDto;

public class MMyPageService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession();
		MemberDto member = (MemberDto)httpSession.getAttribute("member");
		MemberDao memberDao = MemberDao.getInstance();
		String mid = member.getMid();
		int totFcnt = memberDao.myFboardCnt(mid); 
		int totGcnt = memberDao.myGboardCnt(mid); 
		int totVcnt = memberDao.myVboardCnt(mid);
		int commentFcnt = memberDao.myFcommentCnt(mid);
		int commentGcnt = memberDao.myGcommentCnt(mid);
		int commentVcnt = memberDao.myVcommentCnt(mid);
		request.setAttribute("totFcnt", totFcnt);
		request.setAttribute("totGcnt", totGcnt);
		request.setAttribute("totVcnt", totVcnt);
		request.setAttribute("commentFcnt", commentFcnt);
		request.setAttribute("commentGcnt", commentGcnt);
		request.setAttribute("commentVcnt", commentVcnt);
	}

}
