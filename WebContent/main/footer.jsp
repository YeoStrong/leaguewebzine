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
<meta name="theme-color" content="#712cf9">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
	<div id="footer" class="container">
	  <footer class="py-3 my-4">
	    <ul class="nav justify-content-center border-bottom pb-3 mb-3">
	      <li class="nav-item"><a href="${conPath }/main.do" class="nav-link px-2 text-muted">Home</a></li>
	      <li class="nav-item"><a href="${conPath }/nboardList.do" class="nav-link px-2 text-muted">Notice</a></li>
	      <li class="nav-item"><a href="tetris/tetris.jsp" class="nav-link px-2 text-muted">Tetris</a></li>
	      <li class="nav-item"><a href="${conPath }/adminLoginView.do" class="nav-link px-2 text-muted">admin mode</a></li>
	    </ul>
	    <p class="text-center text-muted">&copy; 2023 Company, Inc</p>
	  </footer>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>