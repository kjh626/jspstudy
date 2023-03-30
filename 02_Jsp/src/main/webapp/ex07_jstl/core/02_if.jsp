<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--
		<c:if></c:if>
		
		1. if문을 대체하는 태그이다.
		2. else문이 지원되지 않는다.
		3. 형식
			<c:if test="조건식">
				실행문
			</c:if>
		  ※ 태그로 바꿀 수 있는 것은 최대한 태그로 바꾸는 게 좋다. 자바코드를 최대한 없애는 식으로
	--%>
	<%-- var는 variable(변수)의 줄임말 --%>
	<c:set var="age" value="31" scope="page"></c:set>
	<c:if test="${age <= 100}">
		<h1>살아있네~</h1>
	</c:if>
	<c:if test="${age > 100}">
		<h1>죽었네ㅜㅜ</h1>
	</c:if>
	
	<%-- (예제) 점수로 ABCDF 나누기 --%>
	<c:set var="score" value="55" scope="page"></c:set>
	<c:if test="${score >= 90 and score <= 100}">
		<h1>${score}점은 A학점입니다.</h1>
	</c:if>
	<c:if test="${score >= 80 and score < 90}">
		<h1>${score}점은 B학점입니다.</h1>
	</c:if>
	<c:if test="${score >= 70 and score < 80}">
		<h1>${score}점은 C학점입니다.</h1>
	</c:if>
	<c:if test="${score >= 60 and score < 70}">
		<h1>${score}점은 D학점입니다.</h1>
	</c:if>
	<c:if test="${score >= 0 and score < 60}">
		<h1>${score}점은 F학점입니다.</h1>
	</c:if>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>