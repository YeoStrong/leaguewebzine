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
			height:370px;
			margin: 130px auto 0px;
		}
	</style>
</head>
<body>
	<c:if test="${not empty  joinResult}">
		<script>
			alert('${joinResult}');
		</script>
	</c:if>
	<c:if test="${not empty  joinErrorMsg}">
		<script>
			alert('${joinErrorMsg}');
			history.back();
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<form action="${conPath }/login.do" method="post">
			<input type="hidden" name="next" value="${param.next }">
			<table class="table caption-top w-25 align-middle">
				<caption>로그인</caption>
				<tr>
					<th>아이디</th><td><input type="text" name="mid" class="form-control" value="${mid }" required="required"></td>
				</tr>
				<tr>
					<th>비밀번호</th><td><input type="password" name="mpw" class="form-control" required="required"></td>
				</tr>
				<tr>
					<td colspan="2">
						<p style="text-align: center;">
							<button type="submit" class="btn btn-secondary btn-sm" >로그인</button>
							<button type="button" class="btn btn-secondary btn-sm"
						 	onclick="location.href='${conPath}/joinView.do'" >회원가입</button>
						</p>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>