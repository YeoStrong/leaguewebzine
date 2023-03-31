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
	<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
	<script>
		$(document).ready(function(){
			
		});
	</script>
</head>
<body>
	<c:if test="${empty member }"> <!-- 로그인 후에만 해당 답글을 달 수 있음 -->
		<script>
			location.href='${conPath}/loginView.do?next=fboardReplyView.do?fnum=${param.fnum }&pageNum=${param.pageNum }';
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<form action="${conPath }/fboardReply.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="fgroup" value="${originFboard.fgroup }">
			<input type="hidden" name="fstep" value="${originFboard.fstep }">
			<input type="hidden" name="findent" value="${originFboard.findent }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<table class="table caption-top w-50 align-middle">
				<caption>${originFboard.fnum }번 글에 대한 답변</caption>
				<tr><td>작성자</td><td>${member.mnickname }(${member.mid })</td></tr>
				<tr>
					<td>제목</td>
					<td>
						<input type="text" name="ftitle" required="required"
								value="[답]${originBoard.ftitle }" class="form-control">
					</td>
				</tr>
				<tr><td>본문</td><td><textarea name="fcontent" rows="3" cols="3" class="form-control"></textarea></td></tr>
				<tr><td>첨부파일</td><td><input type="file" name="fimage" class="form-control"></td></tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<button type="submit" class="btn btn-secondary btn-sm" >답변</button>
						<button type="reset" class="btn btn-secondary btn-sm" >취소</button>
						<button type="button" class="btn btn-secondary btn-sm"
						 onclick="location.href='${conPath}/fboardList.do'" >목록</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>