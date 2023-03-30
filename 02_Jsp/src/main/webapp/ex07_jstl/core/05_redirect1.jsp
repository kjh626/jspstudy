<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 
		<c:redirect>    잘 안쓴다. jsp에서 리다이렉트 할 일이 없음. 보통 자바에서 리다이렉트 쓴다. response.sendRedirect하면 되지..
						그냥 태그로 바꿔준 거라 생각해라
		1. 리다이렉트를 수행하는 태그이다.
		2. 형식
			1) <c:redirect url="매핑" context="컨텍스트패스" />
			2) <c:redirect url="매핑" context="컨텍스트패스">
					<c:param name="파라미터" value="값" />
			   </c:redirect>
	--%>
	<c:set var="contextPath" value="${pageContext.request.contextPath }" />
	<c:redirect url="/ex07_jstl/core/05_redirect2.jsp" context="${contextPath}">
		<c:param name="age" value="31" />
	</c:redirect>
	
</body>
</html>