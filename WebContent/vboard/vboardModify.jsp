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
	<form action="${conPath }/vboardModify.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="vnum" value="${vboard.vnum }">
		<input type="hidden" name="dbFileName" value="${vboard.vvideo }">
		<table class="table caption-top w-50 align-middle">
			<caption>${vboard.vnum }번 글 수정</caption>
			<tr><th>작성자</th>
					<td><input type="text" required="required" class="form-control"
								value="${vboard.mnickname }(${vboard.mid })" readonly="readonly"></td>
			</tr>
			<tr><th>챔피언</th>
					<td><input type="text" name="cname" required="required" class="form-control"
								value="${vboard.cname}" readonly="readonly"></td>
			</tr>
			<tr><th>제목</th>
					<td><input type="text" name="vtitle" required="required" class="form-control"
								value="${vboard.vtitle }"></td>
			</tr>
			<tr><th>본문</th>
					<td><textarea rows="5" class="form-control"
							name="vcontent">${vboard.vcontent }</textarea></td>
			</tr>
			<tr><th>영상</th>
					<td><input type="file" name="vvideo"  class="form-control"> 원첨부파일:
							<c:if test="${not empty vboard.vvideo }">
						 		<a href="${conPath }/vboardUp/${vboard.vvideo}" target="_blank">${vboard.vvideo}</a>
						 	</c:if>
						 	<c:if test="${empty vboard.vvideo }">
						 		첨부파일없음
						 	</c:if>
					</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
					<button type="submit" class="btn btn-secondary btn-sm" >수정</button>
					<button type="reset" class="btn btn-secondary btn-sm" onclick="history.back()" >이전</button>
					<button type="button" class="btn btn-secondary btn-sm"
					 onclick="location='${conPath}/vboardList.do?pageNum=${param.pageNum }'" >목록</button>
				</td>
			</tr>
		</table>
	</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>