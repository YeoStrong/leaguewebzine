package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.lw.dao.VcommentDao;

public class VboardCommentModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int vcnum = Integer.parseInt(request.getParameter("vcnum"));
		VcommentDao vcDao = VcommentDao.getInstance();
		request.setAttribute("vcomment", vcDao.vcommentModifyView(vcnum));
	}

}
