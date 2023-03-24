package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.lw.dao.GboardDao;
import com.lec.lw.dto.GboardDto;

public class GboardModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int gnum = Integer.parseInt(request.getParameter("gnum"));
		GboardDao gboardDao = GboardDao.getInstance();
		GboardDto gboardDto = gboardDao.modifyViewGboard(gnum);
		request.setAttribute("gboard", gboardDto);
	}

}
