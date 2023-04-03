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
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<form action="${conPath }/modifyView.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="dbMpw" value="${member.mpw }">
			<input type="hidden" name="dbMphoto" value="${member.mphoto }">
			<table class="table caption-top w-50 align-middle">
				<caption>나의 정보</caption>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="mid" class="form-control" value="${member.mid }" disabled readonly>
					</td>
					<td rowspan="4">
						<img src="${conPath }/memberPhotoUp/${member.mphoto}"
								 alt="${member.mname }사진">
					</td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td>
						<input type="text" name="mnickname" class="form-control" value="${member.mnickname }" disabled readonly>
						<div id="mnicknameConfirmResult"> &nbsp; </div>
					 </td>
				</tr>
				<tr>
					<th>이름</th>
					<td colspan="2">
						<input type="text" name="mname" class="form-control" value="${member.mname }" disabled readonly>
					</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td colspan="2">
						<input type="text" name="mtel" class="form-control" value="${member.mtel }" disabled readonly>
					</td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td colspan="2">
						<input type="text" name="mbirth" class="form-control" value="${member.mbirth }" disabled readonly
							id="datepicker">
					</td>
				</tr>
				<tr>
					<th>메일</th>
					<td colspan="2">
						<input type="email" name="memail" class="form-control" value="${member.memail }" disabled readonly>
					</td>
				</tr>
				<tr>
					<th>성별</th>
					<td style="text-align: left;">
  						<input type='radio' name='mgender' value='남성' class="btn" ${member.mgender eq "남성"? "checked":"" } disabled readonly />남성
						<input type='radio' name='mgender' value='여성' class="btn" ${member.mgender eq "여성"? "checked":"" } disabled readonly />여성
					</td>
				</tr>
				<tr>
					<th>인증사진</th>
					<td colspan="2">
						<input type="file" name="mphoto" class="form-control" style="margin:0 0 0 0px;" disabled readonly>
					</td>
				</tr>
				<tr>
					<th>주소</th>
					<td colspan="2">
						<input type="text" name="maddress" class="form-control" value="${member.maddress }" disabled readonly>
					</td>
				</tr>
				<tr>
					<td colspan="3" style="text-align: center;">
						<p>
							<button type="submit" class="btn btn-secondary btn-sm" >정보수정</button>
							<button type="reset" class="btn btn-secondary btn-sm" 
							onclick="history.back()" >이전</button>
							<button type="button" class="btn btn-secondary btn-sm"
							onclick="location.href='${conPath}/withdrawal.do'" >회원탈퇴</button>
						</p>
					</td>
				</tr>
			</table>
		</form>
		<table class="table caption-top table-striped table-hover w-50">
			<caption>내가 쓴 글</caption>
			<thead class="table-dark">
				<tr><th>게시판 종류</th><th>글수</th></tr>
			</thead>
			<tr>
				<td>자유게시판</td><td>${totFcnt }</td>
			</tr>
			<tr>
				<td>공략게시판</td><td>${totGcnt }</td>
			</tr>
			<tr>
				<td>플레이영상</td><td>${totVcnt }</td>
			</tr>
			<tr>
				<td>자유게시판 댓글</td><td>${commentFcnt }</td>
			</tr>
			<tr>
				<td>공략게시판 댓글</td><td>${commentGcnt }</td>
			</tr>
			<tr>
				<td>플레이영상 댓글</td><td>${commentVcnt }</td>
			</tr>
		</table>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>