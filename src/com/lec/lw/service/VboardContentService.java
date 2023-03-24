package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.lw.dao.VboardDao;
import com.lec.lw.dto.VboardDto;

public class VboardContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int vnum = Integer.parseInt(request.getParameter("vnum"));
		VboardDao vboardDao = VboardDao.getInstance();
		VboardDto vboard = vboardDao.contentVboard(vnum);
		request.setAttribute("vboard", vboard);
	}

}
