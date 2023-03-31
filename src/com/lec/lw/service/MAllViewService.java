package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.lw.dao.MemberDao;

public class MAllViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		// allView.do나 allView.do?pageNum=2
				String pageNum = request.getParameter("pageNum");
				if(pageNum==null) pageNum = "1";
				int currentPage = Integer.parseInt(pageNum);
				final int PAGESIZE = 10, BLOCKSIZE=10;
				int startRow = (currentPage - 1 ) * PAGESIZE + 1;
				int endRow   = startRow + PAGESIZE - 1;
				MemberDao mDao = MemberDao.getInstance();
				request.setAttribute("members", mDao.getMemberlist(startRow, endRow)); // ★
				int totCnt = mDao.getMemberTotCnt();
				int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);
				int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE + 1;
				int endPage   = startPage + BLOCKSIZE - 1;
				if(endPage > pageCnt) {
					endPage = pageCnt;
				}
				request.setAttribute("BLOCKSIZE", BLOCKSIZE);// ★
				request.setAttribute("startPage", startPage);// ★
				request.setAttribute("endPage", endPage);// ★
				request.setAttribute("pageCnt", pageCnt);// ★
				request.setAttribute("pageNum", currentPage);// ★
	}
}
