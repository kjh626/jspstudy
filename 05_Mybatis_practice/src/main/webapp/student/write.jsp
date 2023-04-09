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
	
	function fnInit(){
		let ave = ($('#kor').val() + $('#eng').val() + $('#math').val()) / 3;
		let grade = A;
		$('#ave').val(ave)
		$('#grade').val(grade)
	}
	function goList(){
		fnInit();
		location.href='${contextPath}/list.do';
	}
</script>
</head>
<body>
	
	<div>
		<h1>신규학생등록 화면</h1>
	</div>
	<div>
		<form method="post" action="${contextPath}/add.do">
			<div>
				<label for="name">이름</label>
				<input type="text" name="name">
			</div>
			<div>
				<label for="kor">국어</label>
				<input type="text" name="kor" id="kor">
			</div><div>
				<label for="eng">영어</label>
				<input type="text" name="eng" id="eng">
			</div><div>
				<label for="math">수학</label>
				<input type="text" name="math" id="math">
			</div>
			<div>
				<button>작성완료</button>
				<input type="hidden" id="ave" name="ave">
				<input type="hidden" id="grade" name="grade">
				<input type="reset" value="다시작성">
				<input type="button" value="목록" onclick="goList()">
			</div>
		</form>
	</div>
	
</body>
</html>