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
<%-- 외부 정적 파일(css, js)을 포함할 땐 매번 경로가 변할 수 있도록 처리한다. 경로가 변해야 캐싱한 내용을 사용하지 않고 외부 파일을 읽는다. --%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/header.css?dt=<%=System.currentTimeMillis()%>">
<%-- └→ css 분리해놓으면 css 수정할 때 적용 안 될 수 있어서, 경로에 파라미터를 주고 수정할 때마다 파라미터를 계속 바꿔줘야 한다. 
		어떤 파라미터(이름 그냥 dt로 준 거. 그냥)는 상관 없다. 그냥 주셈. 자동으로 현재시간(타임스탬프값)을 파라미터로 던져주는 게 좋다. --%>
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
