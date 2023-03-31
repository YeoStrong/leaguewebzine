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
	<c:if test="${empty member && empty admin }">
		<script>
			alert('로그인 하셔야 글을 보실 수 있습니다.');
			history.back();
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<table class="table caption-top w-50 align-middle">
			<caption>${nboard.nnum }번 공지사항</caption>
			<tr><th>작성자</th><td>${nboard.anickname} (${nboard.aid}) 님</td>	</tr>
			<tr><th>제목</th>	 <td>${nboard.ntitle }</td></tr>
			<tr><th>본문</th>	 <td><pre>${nboard.ncontent}</pre></td></tr>
			<tr><th>조회수</th><td>${nboard.nhit }</td></tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<c:if test="${not empty nboard.nimage }">
						<a href="${conPath }/nboardUp/${nboard.nimage}" target="_blank">${nboard.nimage}</a>
					</c:if>
					<c:if test="${empty nboard.nimage }">
						첨부파일없음
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<c:if test="${not empty admin }">
				 		<button onclick="location='${conPath}/nboardModifyView.do?nnum=${nboard.nnum }&pageNum=${param.pageNum }'" class="btn btn-secondary btn-sm">수정</button>
				 	</c:if>
				 	<c:if test="${not empty admin}">
						<button onclick="location='${conPath}/nboardDelete.do?nnum=${nboard.nnum }&pageNum=${param.pageNum }'" class="btn btn-secondary btn-sm">삭제</button>
			 		</c:if>
				 	<button onclick="location='${conPath}/nboardList.do?pageNum=${param.pageNum }'" class="btn btn-secondary btn-sm">목록</button>
				 </td>
		</table>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>