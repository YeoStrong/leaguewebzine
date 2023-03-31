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
</head>
<body>
	<c:if test="${not empty admin }">
		<script>
			alert('이미 관리자 모드입니다.');
			location.href='${conPath }/allView.do';
		</script>
	</c:if>
	<c:if test="${not empty member }">
		<script>
			alert('관리자 전용 모드입니다.'); 
			history.back();
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<form action="${conPath }/adminLogin.do" method="post">
			<table class="table caption-top w-25 align-middle">
			<caption>관리자 로그인</caption>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="aid" required="required" autofocus="autofocus" class="form-control"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="apw" required="required" class="form-control"></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
					<button type="submit" class="btn btn-secondary btn-sm" >로그인</button>
				</td>
			</tr>
		</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>