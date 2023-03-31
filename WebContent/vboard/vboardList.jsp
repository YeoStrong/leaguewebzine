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
			$('tr').click(function(){
				var vnum = Number($(this).children().eq(0).text()); // 0번째 td안의 있는 text;
				if(!isNaN(vnum)){
					location.href = '${conPath}/vboardContent.do?vnum='+vnum+'&pageNum=${pageNum}';
				}
			});
		});
	</script>
</head>
<body>
	<c:if test="${not empty vboardResult }">
		<script>alert('${vboardResult}');</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<table class="table caption-top table-striped table-hover w-75">
			<caption>플레이 영상</caption>
			<thead class="table-dark">
			<tr><th>글번호</th><th>작성자</th><th>챔피언</th><th>글제목</th><th>조회수</th>
					<th>날짜</th><th>ip</th></tr>
			</thead>
			<c:if test="${totCnt==0 }">
				<tr><td colspan="6">등록된 글이 없습니다</td></tr>
			</c:if>
			<c:if test="${totCnt!=0 }">
				<c:forEach items="${vboardList }" var="vboard">
					<tr><td>${vboard.vnum }</td>
						<td>${vboard.mnickname }</td>
						<td>${vboard.cname }</td>
						<td class="left">
							${vboard.vtitle } <!-- 글제목에 a태그를 걸지 말고 query로 tr을 클릭하면 상세보기 페이지로 가기 -->
							<c:if test="${not empty vboard.vvideo }">
								<img src="https://cdn-icons-png.flaticon.com/512/5088/5088374.png" width="10">
							</c:if>
						</td>
						<td>${vboard.vhit }</td>
						<td><fmt:formatDate value="${vboard.vdate }" type="date" dateStyle="short"/></td>
						<td>${vboard.vip }</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<form method="get" action="${conPath }/vboardList.do">
			<div class="input-group input-group-sm mb-3 d-flex justify-content-center" style=" margin:30px auto; text-align: center;">
				<c:if test="${not empty member }">
					<button type="button" class="btn btn-secondary btn-sm" onclick="location.href='${conPath}/vboardWriteView.do'" style="margin:0 40px 0 0;" >글쓰기</button>
	    		</c:if>
		  		<input type="text" name="schword" class="form-control" style="max-width:150px;" value="${schword }" placeholder="제목으로 검색" aria-describedby="button-addon2">
		  		<button class="btn btn-outline-secondary" type="submit" id="button-addon2" >검색</button>
			</div>
		</form>
		<div class="paging">
			<c:if test="${startPage > BLOCKSIZE }">
				[ <a href="${conPath }/vboardList.do?pageNum=${startPage-1}&schword=${schword}"> 이전 </a> ]
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:if test="${i == pageNum }">
					<b> [ ${i } ] </b>
				</c:if>
				<c:if test="${i != pageNum }">
					[ <a href="${conPath }/vboardList.do?pageNum=${i}&schword=${schword}"> ${i } </a> ]
				</c:if>
			</c:forEach>
			<c:if test="${endPage<pageCnt }">
			  [ <a href="${conPath }/vboardList.do?pageNum=${endPage+1}&schword=${schword}"> 다음 </a> ]
			</c:if>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>