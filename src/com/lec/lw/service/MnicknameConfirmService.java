package com.lec.lw.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.lw.dao.MemberDao;
public class MnicknameConfirmService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mnickname = request.getParameter("mnickname");
		MemberDao mDao = MemberDao.getInstance();
		int result = mDao.mnicknameConfirm(mnickname);
		if(result == MemberDao.EXISTENT){
			request.setAttribute("mnicknameConfirmResult","<b>중복된 닉네임</b>");
		}else{
			request.setAttribute("mnicknameConfirmResult","사용 가능한 닉네임");
		}
	}
}
