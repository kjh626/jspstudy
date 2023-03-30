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
		// request의 속성 a 저장하기(저장되는지 확인용)
		request.setAttribute("a", 1);
	
		// request의 속성 x, y 저장하기(연산 확인용)
		request.setAttribute("x", 10);
		request.setAttribute("y", 5);
		
	
		// request를 전달하는 forward
		request.getRequestDispatcher("03_request_attribute2.jsp").forward(request, response);
	%>
	
	
	<%--
		참고. forward를 수행하는 Jsp 액션 태그 <= 태그로 forward할 일 없다. 자바 코드 공부해라
		<jsp:forward page="03_request_attribute2.jsp"></jsp:forward>
	--%>

</body>
</html>