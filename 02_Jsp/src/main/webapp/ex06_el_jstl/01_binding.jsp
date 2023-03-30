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
		Jsp의 binding (속성을 저장할 수 있는 영역)
		1. pageContext : this,               현재 Jsp 페이지에서 접근할 수 있다.  this로 따지면 현재객체를 말함. 현재 페이지라고 생각하면 된다. -> jsp실행하면 01_binding_jsp.class 이런 서블릿 클래스로 만들고 실행이 된다.
		2. request     : HttpServletRequest, 하나의 요청에서 접근할 수 있다. (일회용, 요청 1번만)
			※ request 가 저장할 수 있는 속성에는 2가지 형태가 있다.
			① attribute, ② parameter
		3. session     : HttpSession,        브라우저 종료까지 접근할 수 있다. (시간지정 가능, 기본 30분)
		4. application : ServletContext,     애플리케이션 종료까지 접근할 수 있다. (※ 우리가 프로젝트를 만든다면 애플리케이션을 만드는 것. 모든 기능이 들어간 하나의 홈페이지가 애플리케이션이 될 수 있음.)
	--%>
	
	<%
		pageContext.setAttribute("a", 1);
		request.setAttribute("a", 10);
		session.setAttribute("a", 100);
		application.setAttribute("a", 1000);
	%>
	
	<%-- 표현식을 사용해서 저장된 속성을 불러옴 => 표현언어(EL): ${a} 로 불러올 수 있음. 이제부터 표현식 대체할 것임 --%>
	<h1><%=pageContext.getAttribute("a")%></h1>
	<h1><%=request.getAttribute("a")%></h1>
	<h1><%=session.getAttribute("a")%></h1>
	<h1><%=application.getAttribute("a")%></h1>
	
</body>
</html>