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
		String[] seasons = {"spring", "summer", "autumn", "winter"};	
	%>

	<ul>
		<%for(int i = 0; i < seasons.length; i++){%>
			<li><%=seasons[i]%></li>
		<%} %>
	</ul>
	
	<hr>
	
	<%-- out 객체 활용하기 (out은 중요하지 않음. 연습해 본 거임) --%>
	<%-- 경우에 따라 out을 써야 깔끔한 경우가 있음. --%>
	<ul>
	<%
		for(int i = 0; i < seasons.length; i++){
			out.println("<li>" + seasons[i] + "</li>");
		}
	%>
	</ul>

</body>
</html>