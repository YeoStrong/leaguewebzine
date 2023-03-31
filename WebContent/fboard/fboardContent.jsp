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
				var fcnum = $(this).attr('id');
				$.ajax({
					url : '${conPath}/fboardCommentModifyView.do',
					type : 'post',
					data : 'fcnum='+fcnum,
					dataType : 'html',
					success : function(data){
						$('#fccontent'+fcnum).html(data);
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
			<caption>${fboard.fnum }번 글</caption>
			<tr><td>작성자</td><td>${fboard.mnickname} (${fboard.mid}) 님</td>	</tr>
			<tr><td>제목</td>	 <td>${fboard.ftitle }</td></tr>
			<tr><td>본문</td>	 <td><pre>${fboard.fcontent}</pre></td></tr>
			<tr><th>조회수</th><td>${fboard.fhit }</td></tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<c:if test="${not empty fboard.fimage }">
						<a href="${conPath }/fboardUp/${fboard.fimage}" target="_blank">${fboard.fimage}</a>
					</c:if>
					<c:if test="${empty fboard.fimage }">
						첨부파일없음
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<c:if test="${member.mid eq fboard.mid }">
				 		<button onclick="location='${conPath}/fboardModifyView.do?fnum=${fboard.fnum }&pageNum=${param.pageNum }'" class="btn btn-secondary btn-sm">수정</button>
				 	</c:if>
				 	<c:if test="${member.mid eq fboard.mid or not empty admin}">
						<button onclick="location='${conPath}/fboardDelete.do?fnum=${fboard.fnum }&fgroup=${fboard.fgroup }&fstep=${fboard.fstep }&findent=${fboard.findent }&pageNum=${param.pageNum }'" class="btn btn-secondary btn-sm">삭제</button>
			 		</c:if>
				 	<c:if test="${not empty member }">
				 		<button onclick="location='${conPath}/fboardReplyView.do?fnum=${fboard.fnum }&pageNum=${param.pageNum }'" class="btn btn-secondary btn-sm">답변</button>
				 	</c:if>
				 	<button onclick="location='${conPath}/fboardList.do?pageNum=${param.pageNum }'" class="btn btn-secondary btn-sm">목록</button>
				 </td>
		</table>
		<form action="${conPath }/fboardComment.do" style="text-align: center; margin: 20px auto;">
			<input type="hidden" name="fnum" value="${fboard.fnum }">
			<textarea name="fccontent" rows="2" cols="20" style="width:45%;"></textarea>
			<button type="submit" class="btn btn-secondary btn-sm" style="margin:0 0 20px 0;" >댓글 작성</button>
		</form>
		<table class="table caption-top w-50 align-middle">
			<caption>댓글</caption>
			<tr><th>닉네임</th><th>댓글내용</th><th>날짜</th><th>수정/삭제</th></tr>
			<c:forEach items="${fcomment }" var="comment">
				<tr>
					<td>${comment.mnickname }</td>
					<td class="left" style="min-width:400px;">${comment.fccontent }</td>
					<td><fmt:formatDate value="${comment.fcdate }" type="both" dateStyle="short"/></td>
					<td>
						<c:if test="${member.mid eq comment.mid }">
					 		<button onclick="location='${conPath}/fboardCommentModifyView.do?fnum=${comment.fnum }&fcnum=${comment.fcnum }'" class="btn btn-secondary btn-sm">수정</button>
					 	</c:if>
					 	<c:if test="${member.mid eq comment.mid or not empty admin}">
							<button onclick="location='${conPath}/fboardCommentDelete.do?fnum=${comment.fnum }&fcnum=${comment.fcnum }&pageNum=${param.pageNum }'" class="btn btn-secondary btn-sm">삭제</button>
				 		</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>