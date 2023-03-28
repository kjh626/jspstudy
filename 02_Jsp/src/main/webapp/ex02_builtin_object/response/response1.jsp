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
		// response도 선언없이 쓰고 싶으면 가져다 쓰면 된다.
		response.sendRedirect(request.getContextPath() + "/ex02_builtin_object/response/response2.jsp");
	%>

</body>
</html>