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
	<link href="${conPath}/css/style.css" rel="stylesheet">
	<style>
		#content_form {
			height:420px;
			margin: 80px auto 0px;
		}
		#content_form table tr { height: 60px;}
	</style>
	<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
	<script>
		$(document).ready(function(){
			
		});
	</script>
</head>
<body>
	<c:if test="${empty member }"> <!-- 로그인 후에만 글쓰기 가능 -->
		<script>
			location.href='${conPath}/loginView.do?next=vboardWriteView.do';
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<form action="${conPath }/vboardWrite.do" method="post" enctype="multipart/form-data">
			<table class="table caption-top w-50 align-middle">
				<caption>플레이 영상 작성</caption>
				<tr>
					<th>챔피언</th><td><input type="text" name="cname" class="form-control" 	required="required"></td>
				</tr>
				<tr>
					<th>제목</th><td><input type="text" name="vtitle" class="form-control" 	required="required"></td>
				</tr>
				<tr>
					<th>본문</th><td><textarea name="vcontent" rows="5" class="form-control"></textarea></td>
				</tr>
				<tr>
					<th>영상</th><td><input type="file" name="vvideo" class="form-control" required="required"></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<button type="submit" class="btn btn-secondary btn-sm" >글쓰기</button>
						<button type="reset" class="btn btn-secondary btn-sm" >취소</button>
						<button type="button" class="btn btn-secondary btn-sm"
						 onclick="location.href='${conPath}/vboardList.do'" >목록</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>