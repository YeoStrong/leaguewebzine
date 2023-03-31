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
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
	<form action="${conPath }/fboardModify.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="fnum" value="${fboard.fnum }">
		<input type="hidden" name="dbFileName" value="${fboard.fimage }">
		<table class="table caption-top w-50 align-middle">
			<caption>${fboard.fnum }번 글 수정</caption>
			<tr><th>작성자</th>
					<td><input type="text" required="required"
								value="${fboard.mnickname }(${fboard.mid })" class="form-control" readonly="readonly"></td>
			</tr>
			<tr><th>제목</th>
					<td><input type="text" name="ftitle" required="required" class="form-control"
								value="${fboard.ftitle }"></td>
			</tr>
			<tr><th>본문</th>
					<td><textarea rows="5" class="form-control" 
							name="fcontent">${fboard.fcontent }</textarea></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<input type="file" name="fimage"  class="form-control"> 원첨부파일:
					<c:if test="${not empty fboard.fimage }">
				 		<a href="${conPath }/fboardUp/${fboard.fimage}" target="_blank">${fboard.fimage}</a>
				 	</c:if>
				 	<c:if test="${empty fboard.fimage }">
				 		첨부파일없음
				 	</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
					<button type="submit" class="btn btn-secondary btn-sm" >수정</button>
					<button type="reset" class="btn btn-secondary btn-sm" onclick="history.back()" >이전</button>
					<button type="button" class="btn btn-secondary btn-sm"
					 onclick="location='${conPath}/fboardList.do?pageNum=${param.pageNum }'" >목록</button>
				</td>
			</tr>
		</table>
	</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>