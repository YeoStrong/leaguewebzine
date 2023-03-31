package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.lw.dao.VboardDao;
import com.lec.lw.dao.VcommentDao;

public class VboardCommentDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int vcnum = Integer.parseInt(request.getParameter("vcnum"));
		VcommentDao vcommentDao = VcommentDao.getInstance();
		int result = vcommentDao.deleteVcomment(vcnum);
		if(result == VcommentDao.SUCCESS) {
			request.setAttribute("vcommentResult", "댓글 삭제 성공");
		}else {
			request.setAttribute("vcommentResult", "댓글 삭제 실패");
		}
	}

}
