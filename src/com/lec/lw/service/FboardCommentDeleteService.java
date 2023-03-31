package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.lw.dao.FboardDao;
import com.lec.lw.dao.FcommentDao;

public class FboardCommentDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int fcnum = Integer.parseInt(request.getParameter("fcnum"));
		FcommentDao fcommentDao = FcommentDao.getInstance();
		int result = fcommentDao.deleteFcomment(fcnum);
		if(result == FcommentDao.SUCCESS) {
			request.setAttribute("fcommentResult", "댓글 삭제 성공");
		}else {
			request.setAttribute("fcommentResult", "댓글 삭제 실패");
		}
	}

}
