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
			height:800px;
			margin: 0 auto; text-align: center;
		}
	</style>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
  <script>
  	$(function(){
  		$('input[name="mid"]').keyup(function(){
  			var mid = $(this).val();
  			if(mid.length<2){
  				$('#midConfirmResult').text('아이디는 2글자 이상');
  			}else{
  				$.ajax({
  					url : '${conPath}/midConfirm.do',
  					type : 'get',
  					data : 'mid='+mid,
  					dataType : 'html',
  					success : function(data){
  						$('#midConfirmResult').html(data);
  					},
  				});// ajax함수
  			}//if
  		});// keyup event
  		
  		$('input[name="mpw"], input[name="mpwChk"]').keyup(function(){
  			var pw = $('input[name="mpw"]').val();
  			var pwChk = $('input[name="mpwChk"]').val();
  			if(pw == pwChk){
  				$('#mpwChkResult').text('비밀번호 일치');
  			}else{
  				$('#mpwChkResult').text('비밀번호 불일치');
  			}
  		});// keyup event(비밀번호 일치 확인용)
  		 
  		$('input[name="mnickname"]').keyup(function(){
  			var mnickname = $(this).val();
  			if(mnickname.length<2){
  				$('#mnicknameConfirmResult').text('닉네임은 2글자 이상');
  			}else{
  				$.ajax({
  					url : '${conPath}/mnicknameConfirm.do',
  					type : 'get',
  					data : 'mnickname='+mnickname,
  					dataType : 'html',
  					success : function(data){
  						$('#mnicknameConfirmResult').html(data);
  					},
  				});// ajax함수
  			}//if
  		});// keyup event
  		
  		$('form').submit(function(){
  		// "사용 가능한 ID입니다"(#idConfirmResult), "비밀번호 일치(#pwChkResult)"가 출력되었을 경우만 submit 가능
			var midConfirmResult = $('#midConfirmResult').text().trim();
  			var mpwChkResult = $('#mpwChkResult').text().trim();
  			var mnicknameConfirmResult = $('#mnicknameConfirmResult').text().trim();
  			if(midConfirmResult != '사용 가능한 ID'){
  				alert('사용 가능한 ID인지 확인 요망');
  				$('input[name="mid"]').focus();
  				return false; // submit 제한
  			}else if(mpwChkResult != '비밀번호 일치'){
  				alert('비밀번호를 확인하세요');
  				$('input[name="mpw"]').focus();
  				return false;
  			}else if(mnicknameConfirmResult != '사용 가능한 닉네임' && mnicknameConfirmResult!= ''){
  				alert('닉네임을 확인하세요');
  				$('input[name="mnickname"]').focus();
  				return false;
  			}
  		});
  	});
  </script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker({
    	dateFormat: "yy-mm-dd",
    	monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    	dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
    	changeMonth: true, // 월을 바꿀수 있는 셀렉트 박스를 표시한다.
    	changeYear: true, // 년을 바꿀 수 있는 셀렉트 박스를 표시한다.
    	showMonthAfterYear: true,
    	yearSuffix: '년',
    	showOtherMonths: true, // 현재 달이 아닌 달의 날짜도 회색으로 표시
    	//minDate: '-100y',	 // 현재날짜로부터 100년이전까지 년을 표시한다.
    	minDate: new Date(1950, 1 - 1, 1), // 1950년 1월1일을 최소 날짜로 세팅
    	maxDate : 'y', // 현재 날짜 이전만 선택 가능
    	yearRange: 'c-100:c+10', // 년도 선택 셀렉트박스를 현재 년도에서 이전, 이후로 얼마의 범위를 
    });
  } );
  </script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<form action="${conPath }/join.do" method="post" enctype="multipart/form-data">
			<table class="table caption-top w-50 align-middle">
				<caption>회원가입</caption>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="mid" class="form-control" required="required" autofocus="autofocus">
						<div id="midConfirmResult"> &nbsp; </div>
					</td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td>
						<input type="text" name="mnickname" class="form-control" required="required" autofocus="autofocus">
						<div id="mnicknameConfirmResult"> &nbsp; </div>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="mpw" class="form-control" required="required"></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>
						<input type="password" name="mpwChk" class="form-control" required="required">
						<div id="mpwChkResult"> &nbsp; </div>
					</td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" name="mname" class="form-control"></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input type="text" name="mtel" class="form-control"></td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td><input type="text" name="mbirth" class="form-control" id="datepicker"></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<input type="text" name="memail" class="form-control">
					</td>
				</tr>
				<tr>
					<th>성별</th>
					<td style="text-align: left;">
  						<input type='radio' name='mgender' value='남성' class="btn" />남성
						<input type='radio' name='mgender' value='여성' class="btn" />여성
					</td>
				</tr>
				<tr>
					<th>인증사진</th>
					<td><input type="file" name="mphoto" class="form-control"></td>
				</tr>
				<tr>
					<th>주소</th><td><input type="text" name="maddress" class="form-control"></td>
				</tr>
				<tr>
					<td colspan="2">
						<p>
							<button type="submit" class="btn btn-secondary btn-sm" >회원가입</button>
							<button type="button" class="btn btn-secondary btn-sm"
							onclick="location.href='${conPath}/loginView.do'" >로그인</button>
						</p>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>