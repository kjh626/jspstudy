<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	

	<%
		// ☆ request는 선언 없이 사용할 수 있는 내장객체 중 하나이다. ☆
		request.setCharacterEncoding("UTF-8");
		String model = request.getParameter("model");
		// 값이 있거나 null 값인 애들은 클래스 Optional로 쌀 수 있다.(값을 가질 수도 있고 null값을 가질 수도 있다. ) => 값이 있으면 그대로 꺼내고 null이면 0으로 꺼낸다.
		// Optional 연습 많이해서 익숙해지게 될 것이다.
		Optional opt = Optional.ofNullable(request.getParameter("price")); // ↓ Optional 1.8부터 나온 코드임. ※ null은 잡는데, 빈 문자열을 못잡는다.
		Object strPrice = opt.orElse("0");    // → └→ 파라미터 price null일 수도 있는데 파라미터가 없으면 0을 쓰겠음. 이라는 의미 (=전달이 안 되면 0을 쓰겠다)  
		int price = Integer.parseInt(strPrice.toString());
	%>
	
	<h1>모델 : <%= model %></h1>
	<h1>가격 : <%= price %></h1>
	
</body>
</html>