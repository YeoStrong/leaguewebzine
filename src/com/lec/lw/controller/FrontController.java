package com.lec.lw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.lw.service.ALoginService;
import com.lec.lw.service.FboardCommentDeleteService;
import com.lec.lw.service.FboardCommentModifyService;
import com.lec.lw.service.FboardCommentModifyViewService;
import com.lec.lw.service.FboardCommentService;
import com.lec.lw.service.FboardContentService;
import com.lec.lw.service.FboardDeleteService;
import com.lec.lw.service.FboardListService;
import com.lec.lw.service.FboardModifyService;
import com.lec.lw.service.FboardModifyViewService;
import com.lec.lw.service.FboardReplyService;
import com.lec.lw.service.FboardReplyViewService;
import com.lec.lw.service.FboardWriteService;
import com.lec.lw.service.GboardCommentDeleteService;
import com.lec.lw.service.GboardCommentModifyService;
import com.lec.lw.service.GboardCommentModifyViewService;
import com.lec.lw.service.GboardCommentService;
import com.lec.lw.service.GboardContentService;
import com.lec.lw.service.GboardDeleteService;
import com.lec.lw.service.GboardListService;
import com.lec.lw.service.GboardModifyService;
import com.lec.lw.service.GboardModifyViewService;
import com.lec.lw.service.GboardWriteService;
import com.lec.lw.service.MAllViewService;
import com.lec.lw.service.MGradeModifyService;
import com.lec.lw.service.MJoinService;
import com.lec.lw.service.MLoginService;
import com.lec.lw.service.MLogoutService;
import com.lec.lw.service.MModifyService;
import com.lec.lw.service.MMyPageService;
import com.lec.lw.service.MWithdrawalService;
import com.lec.lw.service.MainPageService;
import com.lec.lw.service.MidConfirmService;
import com.lec.lw.service.MnicknameConfirmService;
import com.lec.lw.service.NboardContentService;
import com.lec.lw.service.NboardDeleteService;
import com.lec.lw.service.NboardListService;
import com.lec.lw.service.NboardModifyService;
import com.lec.lw.service.NboardModifyViewService;
import com.lec.lw.service.NboardWriteService;
import com.lec.lw.service.Service;
import com.lec.lw.service.VboardCommentDeleteService;
import com.lec.lw.service.VboardCommentModifyService;
import com.lec.lw.service.VboardCommentModifyViewService;
import com.lec.lw.service.VboardCommentService;
import com.lec.lw.service.VboardContentService;
import com.lec.lw.service.VboardDeleteService;
import com.lec.lw.service.VboardListService;
import com.lec.lw.service.VboardModifyService;
import com.lec.lw.service.VboardModifyViewService;
import com.lec.lw.service.VboardWriteService;


@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		String viewPage = null;
		Service service = null;
		if(command.equals("/main.do")) { // 첫화면
			service = new MainPageService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}
		/* * * * * * * * * * * * * * * * * * * * * * * * * * * 
		 * * * * * * * * * * member 관련 요청  * * * * * * * * * *
		  * * * * * * * * * * * * * * * * * * * * * * * * * * */
		else if(command.equals("/joinView.do")) { // 회원가입 화면
			viewPage = "member/join.jsp";
		}else if(command.equals("/midConfirm.do")) {
			service = new MidConfirmService();
			service.execute(request, response);
			viewPage = "member/midConfirm.jsp";
		}else if(command.equals("/mnicknameConfirm.do")) {
			service = new MnicknameConfirmService();
			service.execute(request, response);
			viewPage = "member/mnicknameConfirm.jsp";
		}else if(command.equals("/join.do")) { // 회원가입 DB 처리
			service = new MJoinService(); // execute메소드 : mId중복체크 후 회원가입
			service.execute(request, response);
			viewPage = "loginView.do";
		}else if(command.equals("/loginView.do")) { // 로그인 화면
			viewPage = "member/login.jsp";
		}else if(command.equals("/login.do")) { // 로그인 DB 및 세션 처리
			service = new MLoginService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if(command.equals("/logout.do")) {//로그아웃 - 세션 날리기
			service = new MLogoutService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if(command.equals("/modifyView.do")) { // 정보 수정하기 위한 view
			viewPage = "member/modify.jsp";
		}else if(command.equals("/modify.do")) { // db에 정보 수정
			service = new MModifyService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if(command.equals("/myPage.do")) { // 마이페이지
			service = new MMyPageService();
			service.execute(request, response);
			viewPage = "member/myPage.jsp";
		}else if(command.equals("/allView.do")) { // 회원목록 출력(페이징처리)
			service = new MAllViewService();
			service.execute(request, response);
			viewPage = "member/mAllView.jsp";
		}else if(command.equals("/withdrawal.do")) { // 회원탈퇴
			service = new MWithdrawalService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if(command.equals("/mgradeModifyView.do")) { // 회원탈퇴
			viewPage = "member/mgradeModify.jsp";
		}else if(command.equals("/mgradeModify.do")) { // 회원탈퇴
			service = new MGradeModifyService();
			service.execute(request, response);
			viewPage = "allView.do";
		}
		/* * * * * * * * * * * * * * * * * * * * * * * * * * * 
		 * * * * * * * * * * admin 관련 요청  * * * * * * * * * *
		 * * * * * * * * * * * * * * * * * * * * * * * * * * */
		else if(command.equals("/adminLoginView.do")) {
			viewPage = "admin/adminLogin.jsp";
		}else if(command.equals("/adminLogin.do")) {
			service = new ALoginService();
			service.execute(request, response);
			viewPage = "allView.do";
		}
		/* * * * * * * * * * * * * * * * * * * * * * * * * * * 
		 * * * * * * * * 공지게시판 관련 요청  * * * * * * * * * * *
		 * * * * * * * * * * * * * * * * * * * * * * * * * * */
		else if(command.equals("/nboardList.do")) {
			service = new NboardListService();
			service.execute(request, response);
			viewPage = "nboard/nboardList.jsp";
		}else if(command.equals("/nboardWriteView.do")) {
			viewPage = "nboard/nboardWrite.jsp";
		}else if(command.equals("/nboardWrite.do")) {
			service = new NboardWriteService();
			service.execute(request, response);
			viewPage = "nboardList.do";
		}else if(command.equals("/nboardContent.do")) {
			service = new NboardContentService();
			service.execute(request, response);
			viewPage = "nboard/nboardContent.jsp";
		}else if(command.equals("/nboardModifyView.do")) {
			service = new NboardModifyViewService();
			service.execute(request, response);
			viewPage = "nboard/nboardModify.jsp";
		}else if(command.equals("/nboardModify.do")) {
			service = new NboardModifyService();
			service.execute(request, response);
			viewPage = "nboardList.do";
		}else if(command.equals("/nboardDelete.do")) {
			service = new NboardDeleteService();
			service.execute(request, response);
			viewPage = "nboardList.do";
		}
		/* * * * * * * * * * * * * * * * * * * * * * * * * * * 
		 * * * * * * * * 자유게시판 관련 요청  * * * * * * * * * *
		 * * * * * * * * * * * * * * * * * * * * * * * * * * */
		else if(command.equals("/fboardList.do")) {
			service = new FboardListService();
			service.execute(request, response);
			viewPage = "fboard/fboardList.jsp";
		}else if(command.equals("/fboardWriteView.do")) {
			viewPage = "fboard/fboardWrite.jsp";
		}else if(command.equals("/fboardWrite.do")) {
			service = new FboardWriteService();
			service.execute(request, response);
			viewPage = "fboardList.do";
		}else if(command.equals("/fboardContent.do")) {
			service = new FboardContentService();
			service.execute(request, response);
			viewPage = "fboard/fboardContent.jsp";
		}else if(command.equals("/fboardModifyView.do")) {
			service = new FboardModifyViewService();
			service.execute(request, response);
			viewPage = "fboard/fboardModify.jsp";
		}else if(command.equals("/fboardModify.do")) {
			service = new FboardModifyService();
			service.execute(request, response);
			viewPage = "fboardList.do";
		}else if(command.equals("/fboardDelete.do")) {
			service = new FboardDeleteService();
			service.execute(request, response);
			viewPage = "fboardList.do";
		}else if(command.equals("/fboardReplyView.do")) {
			service = new FboardReplyViewService();
			service.execute(request, response);
			viewPage = "fboard/fboardReply.jsp";
		}else if(command.equals("/fboardReply.do")) {
			service = new FboardReplyService();
			service.execute(request, response);
			viewPage = "fboardList.do";
		}else if(command.equals("/fboardComment.do")) {
			service = new FboardCommentService();
			service.execute(request, response);
			viewPage = "fboardContent.do";
		}else if(command.equals("/fboardCommentModifyView.do")) {
			service = new FboardCommentModifyViewService();
			service.execute(request, response);
			viewPage = "fboard/fcommentModify.jsp";
		}else if(command.equals("/fboardCommentModify.do")) {
			service = new FboardCommentModifyService();
			service.execute(request, response);
			viewPage = "fboardContent.do";
		}else if(command.equals("/fboardCommentDelete.do")) {
			service = new FboardCommentDeleteService();
			service.execute(request, response);
			viewPage = "fboardContent.do";
		}
		/* * * * * * * * * * * * * * * * * * * * * * * * * * * 
		 * * * * * * * * 공략게시판 관련 요청  * * * * * * * * * *
		 * * * * * * * * * * * * * * * * * * * * * * * * * * */
		else if(command.equals("/gboardList.do")) {
			service = new GboardListService();
			service.execute(request, response);
			viewPage = "gboard/gboardList.jsp";
		}else if(command.equals("/gboardWriteView.do")) {
			viewPage = "gboard/gboardWrite.jsp";
		}else if(command.equals("/gboardWrite.do")) {
			service = new GboardWriteService();
			service.execute(request, response);
			viewPage = "gboardList.do";
		}else if(command.equals("/gboardContent.do")) {
			service = new GboardContentService();
			service.execute(request, response);
			viewPage = "gboard/gboardContent.jsp";
		}else if(command.equals("/gboardModifyView.do")) {
			service = new GboardModifyViewService();
			service.execute(request, response);
			viewPage = "gboard/gboardModify.jsp";
		}else if(command.equals("/gboardModify.do")) {
			service = new GboardModifyService();
			service.execute(request, response);
			viewPage = "gboardList.do";
		}else if(command.equals("/gboardDelete.do")) {
			service = new GboardDeleteService();
			service.execute(request, response);
			viewPage = "gboardList.do";
		}else if(command.equals("/gboardComment.do")) {
			service = new GboardCommentService();
			service.execute(request, response);
			viewPage = "gboardContent.do";
		}else if(command.equals("/gboardCommentModifyView.do")) {
			service = new GboardCommentModifyViewService();
			service.execute(request, response);
			viewPage = "gboard/gcommentModify.jsp";
		}else if(command.equals("/gboardCommentModify.do")) {
			service = new GboardCommentModifyService();
			service.execute(request, response);
			viewPage = "gboardContent.do";
		}else if(command.equals("/gboardCommentDelete.do")) {
			service = new GboardCommentDeleteService();
			service.execute(request, response);
			viewPage = "gboardContent.do";
		}
		/* * * * * * * * * * * * * * * * * * * * * * * * * * * 
		 * * * * * * * * 영상게시판 관련 요청  * * * * * * * * * * *
		 * * * * * * * * * * * * * * * * * * * * * * * * * * */
		else if(command.equals("/vboardList.do")) {
			service = new VboardListService();
			service.execute(request, response);
			viewPage = "vboard/vboardList.jsp";
		}else if(command.equals("/vboardWriteView.do")) {
			viewPage = "vboard/vboardWrite.jsp";
		}else if(command.equals("/vboardWrite.do")) {
			service = new VboardWriteService();
			service.execute(request, response);
			viewPage = "vboardList.do";
		}else if(command.equals("/vboardContent.do")) {
			service = new VboardContentService();
			service.execute(request, response);
			viewPage = "vboard/vboardContent.jsp";
		}else if(command.equals("/vboardModifyView.do")) {
			service = new VboardModifyViewService();
			service.execute(request, response);
			viewPage = "vboard/vboardModify.jsp";
		}else if(command.equals("/vboardModify.do")) {
			service = new VboardModifyService();
			service.execute(request, response);
			viewPage = "vboardList.do";
		}else if(command.equals("/vboardDelete.do")) {
			service = new VboardDeleteService();
			service.execute(request, response);
			viewPage = "vboardList.do";
		}else if(command.equals("/vboardComment.do")) {
			service = new VboardCommentService();
			service.execute(request, response);
			viewPage = "vboardContent.do";
		}else if(command.equals("/vboardCommentModifyView.do")) {
			service = new VboardCommentModifyViewService();
			service.execute(request, response);
			viewPage = "vboard/vcommentModify.jsp";
		}else if(command.equals("/vboardCommentModify.do")) {
			service = new VboardCommentModifyService();
			service.execute(request, response);
			viewPage = "vboardContent.do";
		}else if(command.equals("/vboardCommentDelete.do")) {
			service = new VboardCommentDeleteService();
			service.execute(request, response);
			viewPage = "vboardContent.do";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
