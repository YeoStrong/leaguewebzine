package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.lw.dao.FboardDao;
import com.lec.lw.dto.FboardDto;

public class FboardReplyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int fnum = Integer.parseInt(request.getParameter("fnum"));
		FboardDao fboardDao = FboardDao.getInstance();
		FboardDto originFboard = fboardDao.modifyViewFboard_replyViewFboard(fnum);
		request.setAttribute("originFboard", originFboard); // 답변글을 달려는 글의 원글정보
	}

}
