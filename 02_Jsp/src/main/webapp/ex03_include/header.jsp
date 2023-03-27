<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 헤더는 계속 타이틀이 바뀌기 때문에 include하면 안 된다. 파라미터를 전달해줘서 계속 바꾸면 된다. request를 사용 -->
</head>
<style>
	nav ul {
		display: flex;
		list-style-type: none;
	}
	nav ul li {
		width: 100px;
		height: 30px;
		text-align: center;
		line-height: 30px;
	}
	nav ul li a {
		text-decoration: none;
		color: #181818;
		display: block;		/* 블록레벨이 되면 너비랑 높이를 줄 수 있다. 100에 30 사이즈로 a링크가 만들어진다. */
		width: 100%;
		height: 100%:
	}
</style>
<body>
	<nav>
		<ul>
			<li><a href="body1.jsp">body1</a></li>
			<li><a href="body2.jsp">body2</a></li>
			<li><a href="body3.jsp">body3</a></li>
		</ul>
	</nav>
	
	<hr>
