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
					url : '${conPath}/vboardCommentModifyView.do',
					type : 'post',
					data : 'vcnum='+vcnum,
					dataType : 'html',
					success : function(data){
						$('#vccontent'+vcnum).html(data);
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
			<caption>${vboard.vnum }글 상세보기</caption>
			<tr><th>작성자</th><td>${vboard.mnickname} (${vboard.mid}) 님</td>	</tr>
			<tr><th>챔피언</th><td>${vboard.cname }</td></tr>
			<tr><th>제목</th>	<td>${vboard.vtitle }</td></tr>
			<tr><th>본문</th>	<td><pre>${vboard.vcontent}</pre></td></tr>
			<tr><th>조회수</th><td>${vboard.vhit }</td></tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<a href="${conPath }/vboardUp/${vboard.vvideo}" target="_blank">${vboard.vvideo}</a>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<c:if test="${member.mid eq vboard.mid }">
				 		<button onclick="location='${conPath}/vboardModifyView.do?vnum=${vboard.vnum }&pageNum=${param.pageNum }'" class="btn btn-secondary btn-sm">수정</button>
				 	</c:if>
				 	<c:if test="${member.mid eq vboard.mid or not empty admin}">
						<button onclick="location='${conPath}/vboardDelete.do?vnum=${vboard.vnum }&pageNum=${param.pageNum }'" class="btn btn-secondary btn-sm">삭제</button>
			 		</c:if>
				 	<button onclick="location='${conPath}/vboardList.do?pageNum=${param.pageNum }'" class="btn btn-secondary btn-sm">목록</button>
				 </td>
		</table>
		<form action="${conPath }/vboardComment.do" style="text-align: center; margin: 20px auto;">
			<input type="hidden" name="vnum" value="${vboard.vnum }">
			<textarea name="vccontent" rows="2" cols="20" style="width:45%;"></textarea>
			<button type="submit" class="btn btn-secondary btn-sm" style="margin:0 0 20px 0;" >댓글 작성</button>
		</form>
		<table class="table caption-top w-50 align-middle">
			<caption>댓글</caption>
			<tr><th>닉네임</th><th>댓글내용</th><th>날짜</th><th>수정/삭제</th></tr>
			<c:forEach items="${vcomment }" var="comment">
				<tr>
					<td>${comment.mnickname }</td>
					<td class="left">${comment.vccontent }</td>
					<td><fmt:formatDate value="${comment.vcdate }" type="both" dateStyle="short"/></td>
					<td>
						<c:if test="${member.mid eq comment.mid }">
					 		<button onclick="location='${conPath}/vboardCommentModifyView.do?vnum=${comment.vnum }&vcnum=${comment.vcnum }'" class="btn btn-secondary btn-sm">수정</button>
					 	</c:if>
					 	<c:if test="${member.mid eq comment.mid or not empty admin}">
							<button onclick="location='${conPath}/vboardCommentDelete.do?vnum=${comment.vnum }&vcnum=${comment.vcnum }&pageNum=${param.pageNum }'" class="btn btn-secondary btn-sm">삭제</button>
				 		</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>