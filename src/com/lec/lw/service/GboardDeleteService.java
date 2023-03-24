package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.lw.dao.GboardDao;

public class GboardDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int gnum = Integer.parseInt(request.getParameter("gnum"));
		GboardDao gboardDao = GboardDao.getInstance();
		int result = gboardDao.deleteGboard(gnum);
		if(result == GboardDao.SUCCESS) {
			request.setAttribute("gboardResult", "글 삭제 성공");
		}else {
			request.setAttribute("gboardResult", "글 삭제 실패");
		}
	}

}
