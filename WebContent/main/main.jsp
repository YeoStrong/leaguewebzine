<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="${conPath}/css/style.css" rel="stylesheet">
</head>
<body>
	<c:if test="${not empty next }">
		<script>
			location.href = '${conPath}/${next}';
		</script>
	</c:if>
	<c:if test="${not empty loginErrorMsg }">
		<script>
			alert('${loginErrorMsg}');
			history.back();
		</script>
	</c:if>
	<c:if test="${not empty modifyResult }">
		<script>alert('${modifyResult}');</script>
	</c:if>
	<c:if test="${not empty withdrawalResult }">
		<script>
			alert('${withdrawalResult}');
		</script>
	</c:if>
	<c:if test="${member.mwithdrawal eq 0}">
		<script>
			alert('탈퇴했거나 없는 회원입니다.');
			location.href="${conPath}/logout.do";
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<div class="container">
		  <div class="row">
		    <div class="col">
		    	<table class="table caption-top table-striped table-hover w-75">
					<caption>공지사항</caption>
					<thead class="table-dark">
						<tr><th>작성</th><th>제목</th><th>조회</th></tr>
					</thead>
					<c:if test="${totCnt==0 }">
						<tr><td colspan="6">등록된 글이 없습니다</td></tr>
					</c:if>
					<c:if test="${totCnt!=0 }">
						<c:forEach items="${nboardList }" var="nboard">
							<tr>
								<td>${nboard.anickname }</td>
								<td class="left">
									${nboard.ntitle }
									<c:if test="${not empty nboard.nimage }">
										<img src="https://cdn-icons-png.flaticon.com/512/5088/5088374.png" width="10">
									</c:if>
								</td>
								<td>${nboard.nhit }</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
		    </div>
		    <div class="col">
		    	<table class="table caption-top table-striped table-hover w-75">
					<caption>공략게시판</caption>
					<thead class="table-dark">
						<tr><th>작성</th><th>제목<th>조회</th></tr>
					</thead>
					<c:if test="${totCnt!=0 }">
						<c:forEach items="${gboardList }" var="gboard">
							<tr>
								<td>${gboard.mnickname }</td>
								<td class="left">
									${gboard.gtitle }
									<c:if test="${not empty gboard.gfile1 or not empty gboard.gfile2 or not empty gboard.gfile3 }">
										<img src="https://cdn-icons-png.flaticon.com/512/5088/5088374.png" width="10">
									</c:if>
								</td>
								<td>${gboard.ghit }</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
		    </div>
		  </div>
		</div>
		<div class="container">
		  <div class="row">
		    <div class="col">
		    	<table class="table caption-top table-striped table-hover w-75">
					<caption>자유게시판</caption>
					<thead class="table-dark">
						<tr><th>작성</th><th>제목</th><th>조회</th></tr>
					</thead>
					<c:if test="${totCnt!=0 }">
						<c:forEach items="${fboardList }" var="fboard">
							<tr>
								<td>${fboard.mnickname }</td>
								<td class="left">
									${fboard.ftitle }
									<c:if test="${not empty fboard.fimage }">
										<img src="https://cdn-icons-png.flaticon.com/512/5088/5088374.png" width="10">
									</c:if>
								</td>
								<td>${fboard.fhit }</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
		    </div>
		    <div class="col">
		    	<table class="table caption-top table-striped table-hover w-75">
					<caption>플레이 영상</caption>
					<thead class="table-dark">
						<tr><th>작성</th><th>제목</th><th>조회</th></tr>
					</thead>
					<c:if test="${totCnt!=0 }">
						<c:forEach items="${vboardList }" var="vboard">
							<tr>
								<td>${vboard.mnickname }</td>
								
								<td class="left">
									${vboard.vtitle }
									<c:if test="${not empty vboard.vvideo }">
										<img src="https://cdn-icons-png.flaticon.com/512/5088/5088374.png" width="10">
									</c:if>
								</td>
								<td>${vboard.vhit }</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
		    </div>
		  </div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>