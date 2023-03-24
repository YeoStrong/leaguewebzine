package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.lw.dao.NboardDao;
import com.lec.lw.dto.NboardDto;

public class NboardContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int nnum = Integer.parseInt(request.getParameter("nnum"));
		NboardDao nboardDao = NboardDao.getInstance();
		NboardDto nboard = nboardDao.contentNboard(nnum);
		request.setAttribute("nboard", nboard);
	}

}
