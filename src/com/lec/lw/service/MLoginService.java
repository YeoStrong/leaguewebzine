package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.lw.dao.MemberDao;
import com.lec.lw.dto.MemberDto;

public class MLoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		request.setAttribute("next", request.getParameter("next"));
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
//		String mwithdrawalStr = request.getParameter("mwithdrawal");
//		int mwithdrawal = 1;
//		if(mwithdrawalStr!=null) {
//			mwithdrawal = Integer.parseInt(mwithdrawalStr);
//		}
		MemberDao mDao = MemberDao.getInstance();
		int result = mDao.loginCheck(mid, mpw);
		if(result==MemberDao.LOGIN_SUCCESS) { // 로그인 성공
			HttpSession session = request.getSession();
			MemberDto member = mDao.getMember(mid);
			session.setAttribute("member", member);
		}else {
			request.setAttribute("loginErrorMsg", "아이디와 비밀번호를 확인하세요.");
		}
	}

}
