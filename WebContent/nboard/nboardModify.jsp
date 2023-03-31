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
			height:400px;
			margin: 100px auto 0px;
		}
	</style>
	<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
	<script>
		$(document).ready(function(){
			
		});
	</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
	<form action="${conPath }/nboardModify.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="nnum" value="${nboard.nnum }">
		<input type="hidden" name="dbFileName" value="${nboard.nimage }">
		<table  class="table caption-top w-50 align-middle">
			<caption>${nboard.nnum }번 공지사항 수정</caption>
			<tr><th>작성자</th>
					<td><input type="text" required="required" class="form-control"
								value="${nboard.anickname }(${nboard.aid })" readonly="readonly"></td>
			</tr>
			<tr><th>제목</th>
					<td><input type="text" name="ntitle" required="required" class="form-control"
								value="${nboard.ntitle }"></td>
			</tr>
			<tr><th>본문</th>
					<td><textarea rows="5" class="form-control"
							name="ncontent">${nboard.ncontent }</textarea></td>
			</tr>
			<tr><th>첨부파일</th>
					<td><input type="file" name="nimage" class="form-control"> 원첨부파일:
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
					<button type="submit" class="btn btn-secondary btn-sm" >수정</button>
					<button type="reset" class="btn btn-secondary btn-sm" onclick="history.back()" >이전</button>
					<button type="button" class="btn btn-secondary btn-sm"
					 onclick="location='${conPath}/nboardList.do?pageNum=${param.pageNum }'" >목록</button>
				</td>
			</tr>
		</table>
	</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>