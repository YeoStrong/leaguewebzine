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
			$('.btn').click(function(){
				var gcnum = $(this).attr('id');
				$.ajax({
					url : '${conPath}/gboardCommentModifyView.do',
					type : 'post',
					data : 'gcnum='+gcnum,
					dataType : 'html',
					success : function(data){
						$('#gccontent'+gcnum).html(data);
					}
				});
			});
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
			<caption>${gboard.gnum }번 공략</caption>
			<tr><td>작성자</td><td>${gboard.mnickname} (${gboard.mid}) 님</td>	</tr>
			<tr><td>챔피언</td><td>${gboard.cname}</td></tr>
			<tr><td>제목</td>	<td>${gboard.gtitle }</td></tr>
			<tr><td>본문</td>	<td><pre>${gboard.gcontent}</pre></td></tr>
			<tr><th>조회수</th><td>${gboard.ghit }</td></tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<c:if test="${not empty gboard.gfile1 }">
						<a href="${conPath }/gboardUp/${gboard.gfile1}" target="_blank">${gboard.gfile1}</a>
					</c:if>
					<c:if test="${not empty gboard.gfile2 }">
						<a href="${conPath }/gboardUp/${gboard.gfile2}" target="_blank">${gboard.gfile2}</a>
					</c:if>
					<c:if test="${not empty gboard.gfile3 }">
						<a href="${conPath }/gboardUp/${gboard.gfile3}" target="_blank">${gboard.gfile3}</a>
					</c:if>
					<c:if test="${empty gboard.gfile1 && empty gboard.gfile2 && empty gboard.gfile3 }">
						첨부파일없음
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<c:if test="${member.mid eq gboard.mid }">
				 		<button onclick="location='${conPath}/gboardModifyView.do?gnum=${gboard.gnum }&pageNum=${param.pageNum }'" class="btn btn-secondary btn-sm">수정</button>
				 	</c:if>
				 	<c:if test="${member.mid eq gboard.mid or not empty admin}">
						<button onclick="location='${conPath}/gboardDelete.do?gnum=${gboard.gnum }&pageNum=${param.pageNum }'" class="btn btn-secondary btn-sm">삭제</button>
			 		</c:if>
				 	<button onclick="location='${conPath}/gboardList.do?pageNum=${param.pageNum }'" class="btn btn-secondary btn-sm">목록</button>
				 </td>
		</table>
		<form action="${conPath }/gboardComment.do" style="text-align: center; margin: 20px auto;">
			<input type="hidden" name="gnum" value="${gboard.gnum }">
			<textarea name="gccontent" rows="2" cols="20" style="width:45%;"></textarea>
			<button type="submit" class="btn btn-secondary btn-sm" style="margin:0 0 20px 0;" >댓글 작성</button>
		</form>
		<table class="table caption-top w-50 align-middle">
			<caption>댓글</caption>
			<tr><th>닉네임</th><th>댓글내용</th><th>날짜</th><th>수정/삭제</th></tr>
			<c:forEach items="${gcomment }" var="comment">
				<tr>
					<td>${comment.mnickname }</td>
					<td class="left" style="min-width:400px;">${comment.gccontent }</td>
					<td><fmt:formatDate value="${comment.gcdate }" type="both" dateStyle="short"/></td>
					<td>
						<c:if test="${member.mid eq comment.mid }">
					 		<button onclick="location='${conPath}/gboardCommentModifyView.do?gnum=${comment.gnum }&gcnum=${comment.gcnum }'" class="btn btn-secondary btn-sm">수정</button>
					 	</c:if>
					 	<c:if test="${member.mid eq comment.mid or not empty admin}">
							<button onclick="location='${conPath}/gboardCommentDelete.do?gnum=${comment.gnum }&gcnum=${comment.gcnum }&pageNum=${param.pageNum }'" class="btn btn-secondary btn-sm">삭제</button>
				 		</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>