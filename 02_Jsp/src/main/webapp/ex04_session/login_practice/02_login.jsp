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
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		if(id.equals("admin") && pw.equals("1234")){
			
			session.setAttribute("loginId", id);
			session.setMaxInactiveInterval(60 * 10);
			
		}
	
		response.sendRedirect(request.getContextPath() + "/ex04_session/login/01_form.jsp");
		
	%>

</body>
</html>