package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.lw.dao.NboardDao;

public class NboardDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int nnum = Integer.parseInt(request.getParameter("nnum"));
		NboardDao nboardDao = NboardDao.getInstance();
		int result = nboardDao.deleteNboard(nnum);
		if(result == NboardDao.SUCCESS) {
			request.setAttribute("nboardResult", "공지 삭제 성공");
		}else {
			request.setAttribute("nboardResult", "공지 삭제 실패");
		}
	}

}
