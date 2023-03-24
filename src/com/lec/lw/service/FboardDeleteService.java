package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.lw.dao.FboardDao;

public class FboardDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int fnum = Integer.parseInt(request.getParameter("fnum"));
		FboardDao fboardDao = FboardDao.getInstance();
		int result = fboardDao.deleteFboard(fnum);
		if(result == FboardDao.SUCCESS) {
			request.setAttribute("fboardResult", "글 삭제 성공");
		}else {
			request.setAttribute("fboardResult", "글 삭제 실패");
		}
	}

}
