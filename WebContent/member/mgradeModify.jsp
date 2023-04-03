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
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<form action="${conPath }/mgradeModify.do" method="post" enctype="multipart/form-data">
			<table class="table caption-top w-50 align-middle">
				<caption>회원 등급 변경</caption>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="mid" class="form-control" value="${member.mid }">
					</td>
				</tr>
				<tr>
					<th>회원등급</th>
					<td>
						<input type="text" name="llevelnum" class="form-control" value="${member.llevelnum }">
					 </td>
				</tr>
				
				<tr>
					<td colspan="3" style="text-align: center;">
						<p>
							<button type="submit" class="btn btn-secondary btn-sm" >등급 변경</button>
							<button type="reset" class="btn btn-secondary btn-sm" >초기화</button>
							<button type="reset" class="btn btn-secondary btn-sm" 
							onclick="history.back()" >이전</button>
						</p>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>















