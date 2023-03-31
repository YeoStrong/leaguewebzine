package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.lw.dao.GboardDao;
import com.lec.lw.dao.GcommentDao;

public class GboardCommentDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int gcnum = Integer.parseInt(request.getParameter("gcnum"));
		GcommentDao gcommentDao = GcommentDao.getInstance();
		int result = gcommentDao.deleteGcomment(gcnum);
		if(result == GboardDao.SUCCESS) {
			request.setAttribute("gcommentResult", "댓글 삭제 성공");
		}else {
			request.setAttribute("gcommentResult", "댓글 삭제 실패");
		}
	}

}
