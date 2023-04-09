<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>
	function goEdit(){
		location.href='${contextPath}/edit.do?stuNo=${student.stuNo}';
		
	}
	function goList(){
		location.href='${contextPath}/list.do';
	}
</script>
</head>
<body>

	<div>
		<h1>학생 상세 조회</h1>
	</div>
	<div>
		<label for="stuNo">학번</label>
		<input type="text" id="stuNo" value="${student.stuNo}" readonly>
	</div>
	<div>
		<label for="name">이름</label>
		<input type="text" value="${student.name}">
	</div>
	<div>
		<label for="kor">국어</label>
		<input type="text" value="${student.kor}">
	</div>
	<div>
		<label for="eng">영어</label>
		<input type="text" value="${student.eng}">
	</div>
	<div>
		<label for="math">수학</label>
		<input type="text" value="${student.math}">
	</div>
	<div>
		<span>평균</span>
		<input type="text" value="${student.ave}" readonly>
	</div>
	<div>
		<span>학점</span>
		<input type="text" value="${student.grade}" readonly>
	</div>
	<div>
		<input type="button" value="수정하기" onclick="goEdit()">
		<input type="button" value="목록" onclick="goList()">
	</div>

</body>
</html>