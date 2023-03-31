package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.lw.dao.FboardDao;
import com.lec.lw.dao.FcommentDao;
import com.lec.lw.dto.FboardDto;
import com.lec.lw.dto.MemberDto;

public class FboardContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int fnum = Integer.parseInt(request.getParameter("fnum"));
		FboardDao fboardDao = FboardDao.getInstance();
		FboardDto fboard = fboardDao.contentFboard(fnum);
		request.setAttribute("fboard", fboard);
		FcommentDao fcDao = FcommentDao.getInstance();
		request.setAttribute("fcomment", fcDao.listFcomment(fnum));
	}

}
