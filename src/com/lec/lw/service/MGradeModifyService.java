package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.lw.dao.MemberDao;
import com.lec.lw.dto.MemberDto;

public class MGradeModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int result = MemberDao.FAIL;
		HttpSession httpSession = request.getSession();
		MemberDto member = (MemberDto)httpSession.getAttribute("member");
		String mid = member.getMid();
		String llevelnumStr = request.getParameter("llevelnum");
		int llevelnum = 1;
		if(llevelnumStr!=null) {
			llevelnum = Integer.parseInt(llevelnumStr);
		}
		MemberDao memberDao = MemberDao.getInstance();
		MemberDto memberDto = new MemberDto(mid, null, null, null, null, null, null, null, null, null, null, llevelnum, 0);
		result = memberDao.modifyLevel(memberDto);
		if(result == MemberDao.SUCCESS) { // 수정 성공시 세션도 수정
			HttpSession session = request.getSession();
			session.setAttribute("member", memberDto);
			request.setAttribute("levelModifyResult", "회원등급 변경 성공");
		}else { // 수정 실패시
			request.setAttribute("levelModifyResult", "회원등급 변경 실패");
		}
	}

}
