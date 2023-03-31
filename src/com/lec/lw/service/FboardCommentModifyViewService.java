package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.lw.dao.FcommentDao;

public class FboardCommentModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int fcnum = Integer.parseInt(request.getParameter("fcnum"));
		FcommentDao fcDao = FcommentDao.getInstance();
		request.setAttribute("fcomment", fcDao.fcommentModifyView(fcnum));
	}

}
