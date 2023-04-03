<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
    <title>Tetris</title>
    <style>
        body {
            background: #202028;
            color: #fff;
            font-family: sans-serif;
            font-size: 2em;
            text-align: center;
        }
        canvas {
            border: solid .2em #fff;
            height: 90vh;
        }
    </style>
</head>
<body>
    <div id="score"></div>
    <canvas id="tetris" width="240" height="400" />
    <script src="tetris.js"></script>
</body>
</html>