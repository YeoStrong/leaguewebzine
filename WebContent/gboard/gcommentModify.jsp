<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="${conPath}/css/style.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<form action="${conPath }/gboardCommentModify.do">
			<input type="hidden" name="gnum" value="${gcomment.gnum }">
			<input type="hidden" name="gcnum" value="${gcomment.gcnum }">
			<table class="table caption-top w-50 align-middle">
				<caption>댓글 수정</caption>
				<tr>
					<th>댓글</th>
					<td>
						<textarea rows="2" cols="20" name="gccontent" class="form-control">${gcomment.gccontent}</textarea>
					</td>
				</tr>
			</table>
			<p style="text-align: center; margin: 20px auto;">
				<button type="submit" class="btn btn-secondary btn-sm" >수정</button>
				<button type="reset" class="btn btn-secondary btn-sm" onclick="history.back()" >이전</button>
				<button type="button" class="btn btn-secondary btn-sm"
				onclick="location='${conPath}/gboardList.do?pageNum=${param.pageNum }'" >목록</button>
			</p>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>