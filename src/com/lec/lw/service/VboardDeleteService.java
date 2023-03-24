package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.lw.dao.VboardDao;

public class VboardDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int vnum = Integer.parseInt(request.getParameter("vnum"));
		VboardDao vboardDao = VboardDao.getInstance();
		int result = vboardDao.deleteVboard(vnum);
		if(result == VboardDao.SUCCESS) {
			request.setAttribute("vboardResult", "글 삭제 성공");
		}else {
			request.setAttribute("vboardResult", "글 삭제 실패");
		}
	}

}
