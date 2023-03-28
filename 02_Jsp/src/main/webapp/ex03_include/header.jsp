<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	request.setCharacterEncoding("UTF-8");
	Optional<String> opt = Optional.ofNullable(request.getParameter("title"));
	String title = opt.orElse("환영합니다.");
%>
<title><%=title%></title>
<%-- 헤더는 계속 타이틀이 바뀌기 때문에 include하면 안 된다. 동적 include를 활용해 파라미터를 전달해줘서 계속 바꾸면 된다. request활용 --%>
<%-- request.getContextPath() == /02_Jsp --%> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/header.css">
<script src="<%=request.getContextPath()%>/resources/js/lib/jquery-3.6.4.min.js"></script>
</head>
<body>
	<nav>
		<ul>
			<li><a href="body1.jsp">body1</a></li>
			<li><a href="body2.jsp">body2</a></li>
			<li><a href="body3.jsp">body3</a></li>
		</ul>
	</nav>
	
	<hr>
