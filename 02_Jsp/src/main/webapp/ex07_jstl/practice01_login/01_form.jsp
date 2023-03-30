<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%-- pageContext.request.contextPath 이건 알아두는 게 좋다. 원래 썼던거 보다 이렇게 set으로 만들어서 contextPath쓰는 게 좋다.(추천!) --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page"></c:set> 
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
</head>
<body>

	<%-- 세션에 loginId라는 속성이 있는지 확인. 없다면 로그인이 안 된 것이다. --%>
	<%-- sessionScope.loginId 에서 sessionScope는 생략해도 된다. loginId라는 이름이 중복되지 않기 때문에 --%>
	<%-- "${sessionScope.loginId == null}" 괄호 안에 띄어쓰기는 괜찮다. 괄호밖 띄어쓰기는 안 돼.( null}    ") --%>
	<c:choose>
		<c:when test="${sessionScope.loginId == null}">
			<div>
				<form method="post" action="${contextPath}/ex07_jstl/practice01_login/02_login.jsp">
					<div><input type="text" name="id" placeholder="아이디"></div>
					<div><input type="password" name="pw" placeholder="비밀번호"></div>
					<div><button>로그인</button></div>
				</form>
			</div>
		</c:when>
		<c:otherwise>
			<div>
				${loginId}님 반갑습니다.
				<input type="button" value="로그아웃" id="btn_logout">
			</div>
		</c:otherwise>
	</c:choose>
	<script>
		$('#btn_logout').on('click', function(){
			location.href='${contextPath}/ex07_jstl/practice01_login/03_logout.jsp';
		})
	</script>
	
</body>
</html>