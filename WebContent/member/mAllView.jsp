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
			height:430px;
			margin: 70px auto 0px;
		}
	</style>
</head>
<body>
	<c:if test="${not empty adminLoginResult }">
		<script>
			alert('${adminLoginResult}');
		</script>
	</c:if>
	<c:if test="${not empty adminLoginErrorMsg }">
		<script>
			alert('${adminLoginErrorMsg}');
			history.back();
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<table class="table caption-top w-75 align-middle">
			<caption>전체회원보기</caption>
			<tr>
				<th>ID</th><th>닉네임</th><th>인증사진</th><th>등급</th><th>가입일</th>
			</tr>
			<c:forEach var="dto" items="${members }">
				<tr>
					<td>
						${dto.mid }
					</td>
					<td>
						${dto.mnickname }
					</td>
					<td>
						<c:if test="${dto.mphoto != 'NOIMG.JPG' }">
							○
						</c:if>
						<c:if test="${dto.mphoto eq 'NOIMG.JPG'}">
							X
						</c:if>
					</td>
					<td>
						${dto.llevelnum }
					</td>
					<td>
						${dto.mrdate }
					</td>
				</tr>
			</c:forEach>
		</table>
		<div class="paging">
			<c:if test="${startPage > BLOCKSIZE }">
				[ <a href="${conPath }/allView.do?pageNum=${startPage-1}&schword=${schword}"> 이전 </a> ]
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:if test="${i == pageNum }">
					<b> [ ${i } ] </b>
				</c:if>
				<c:if test="${i != pageNum }">
					[ <a href="${conPath }/allView.do?pageNum=${i}&schword=${schword}"> ${i } </a> ]
				</c:if>
			</c:forEach>
			<c:if test="${endPage<pageCnt }">
			  [ <a href="${conPath }/allView.do?pageNum=${endPage+1}&schword=${schword}"> 다음 </a> ]
			</c:if>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>















