<%@page import="com.lec.lw.dto.FboardDto"%>
<%@page import="com.lec.lw.dao.FboardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="${conPath }/css/style.css" rel="stylesheet">
	<style>
		#content_form {
			height:430px;
			margin: 70px auto 0px;
		}
	</style>
	<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
	<script>
		$(document).ready(function(){
			
		});
	</script>
</head>
<body>
<%
	FboardDao dao = FboardDao.getInstance();
	FboardDto dto = new FboardDto();
	for(int i=0 ; i<60 ; i++){
		dto.setFtitle("제목 " + i);
		dto.setFcontent(i+"번째 본문");
		dto.setFip("192.168.10."+i);
		if(i%5!=0){
			dto.setMid("aaa");
			dto.setFimage(null);
			dao.writeFboard(dto);			
		}else if(i%5==0){
			dto.setMid("bbb");
			dto.setFimage("a.docx");
			dao.writeFboard(dto);		
		}	
	}
	response.sendRedirect("../fboardList.do");
%>
</body>
</html>