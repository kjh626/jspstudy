<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
<%--
	 링크주소는 목록보기를 활용할 것이다. 
	<a href="/04_Dbcp/getAllBoardList.do">게시판</a>
	
	변수 처리하는 방법 3가지 
	1. 표현식으로 
	<a href="<%=request.getContextPath()%>/getAllBoardList.do">게시판</a>
	
	2. 표현언어로 
	<%
		pageContext.setAttribute("contextPath", request.getContextPath());
	%>
	<a href="${contextPath}/getAllBoardList.do">게시판</a>
 
    ※ contextpath를 안 적어줘도 되는 방법
	<a href="<c:url value="/getAllBoardList.do" />">게시판</a>
--%>
	
	<%-- 3. 최종적으로 스크립트 요소 쓰지 않고 태그와 EL로 작성 --%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page" />
	<a href="${contextPath}/getAllBoardList.do">게시판</a>
	
</body>
</html>