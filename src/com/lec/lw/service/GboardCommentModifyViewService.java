package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.lw.dao.GcommentDao;

public class GboardCommentModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int gcnum = Integer.parseInt(request.getParameter("gcnum"));
		GcommentDao gcDao = GcommentDao.getInstance();
		request.setAttribute("gcomment", gcDao.gcommentModifyView(gcnum));
	}

}
