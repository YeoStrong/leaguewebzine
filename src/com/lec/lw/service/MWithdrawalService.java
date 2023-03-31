package com.lec.lw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.lw.dao.MemberDao;
import com.lec.lw.dto.MemberDto;

public class MWithdrawalService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String mid = null;
		MemberDto sessionMember = (MemberDto) session.getAttribute("member");
		if(sessionMember!=null) {
			mid = sessionMember.getMid();
		}
		MemberDao mDao = MemberDao.getInstance();
		int result = mDao.withdrawalMember(mid); // 회원탈퇴
		session.invalidate(); // 세션 삭제
		if(result == MemberDao.SUCCESS) {
			request.setAttribute("withdrawalResult", "회원 탈퇴가 완료되었습니다.");
		}else {
			request.setAttribute("withdrawalResult", "회원 탈퇴가 제대로 되지 않았습니다.");
		}
	}
}
