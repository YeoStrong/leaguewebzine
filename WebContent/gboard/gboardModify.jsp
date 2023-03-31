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
			height:700px;
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
	<form action="${conPath }/gboardModify.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="gnum" value="${gboard.gnum }">
		<input type="hidden" name="dbFileNames" value="${gboard.gfile1 }">
		<table class="table caption-top w-50 align-middle">
			<caption>${gboard.gnum }번 공략 수정</caption>
			<tr><th>작성자</th>
					<td><input type="text" required="required" class="form-control"
								value="${gboard.mnickname }(${gboard.mid })" readonly="readonly"></td>
			</tr>
			<tr><th>챔피언</th>
					<td><input type="text" name="cname" required="required" class="form-control"
								value="${gboard.cname }" readonly="readonly"></td>
			</tr>
			<tr><th>제목</th>
					<td><input type="text" name="gtitle" required="required" class="form-control"
								value="${gboard.gtitle }"></td>
			</tr>
			<tr><th>본문</th>
					<td><textarea rows="5"  class="form-control"
							name="gcontent">${gboard.gcontent }</textarea></td>
			</tr>
			<tr><th>첨부파일</th>
					<td>
						<input type="file" name="gfile1"  class="form-control"> 원첨부파일1:
						<c:if test="${not empty gboard.gfile1 }">
					 		<a href="${conPath }/gboardUp/${gboard.gfile1}" target="_blank">${gboard.gfile1}</a>
					 	</c:if>
						<c:if test="${empty gboard.gfile1 }">
					 		첨부파일없음
					 	</c:if>
						<input type="file" name="gfile2"  class="form-control"> 원첨부파일2:
						<c:if test="${not empty gboard.gfile2 }">
					 		<a href="${conPath }/gboardUp/${gboard.gfile2}" target="_blank">${gboard.gfile2}</a>
					 	</c:if>
						<c:if test="${empty gboard.gfile2 }">
					 		첨부파일없음
					 	</c:if>
					 	<input type="file" name="gfile3"  class="form-control"> 원첨부파일1:
						<c:if test="${not empty gboard.gfile3 }">
					 		<a href="${conPath }/gboardUp/${gboard.gfile3}" target="_blank">${gboard.gfile3}</a>
					 	</c:if>
						<c:if test="${empty gboard.gfile3 }">
					 		첨부파일없음
					 	</c:if>
					</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
					<button type="submit" class="btn btn-secondary btn-sm" >수정</button>
					<button type="reset" class="btn btn-secondary btn-sm" onclick="history.back()" >이전</button>
					<button type="button" class="btn btn-secondary btn-sm"
					 onclick="location='${conPath}/gboardList.do?pageNum=${param.pageNum }'" >목록</button>
				</td>
			</tr>
		</table>
	</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>