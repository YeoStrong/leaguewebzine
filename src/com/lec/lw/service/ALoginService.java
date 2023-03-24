package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.lw.dao.AdminDao;
import com.lec.lw.dto.AdminDto;

public class ALoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String aid = request.getParameter("aid");
		String apw = request.getParameter("apw");
		AdminDao aDao = AdminDao.getInstance();
		int result = aDao.loginCheck(aid, apw);
		if(result == AdminDao.LOGIN_SUCCESS) {
			HttpSession httpSession = request.getSession();
			//httpSession.setAttribute("aid", aid);
			AdminDto admin = aDao.getAdmin(aid);
			httpSession.setAttribute("admin", admin);
			request.setAttribute("adminLoginResult", "관리자계정으로 들어오셨습니다");
		}else {
			request.setAttribute("adminLoginErrorMsg", "관리자계정 로그인이 실패되었습니다");
		}
	}
}
