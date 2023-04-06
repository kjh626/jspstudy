<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>
	function goList(){
		location.href='${contextPath}/list.do';
	}
</script>
</head>
<body>
	
	<div>
		<h1>BBS 편집</h1>
	</div>
	<div>
		<%-- modify.do 로 연결 . modify.do로 넘어가면서 3개의 값(title, content, bbsNo)을 넘겨줘야 함 --%>
		<form method="post" action="${contextPath}/modify.do">
			<div>
				<input type="text" name="title" value="${bbs.title}">
			</div>
			<div>
				<textarea rows="5" cols="30" name="content">${bbs.content}</textarea>
			</div>
			<div>
			<%-- modify할 때 필요한 거 : 번호! --%>
				<input type="hidden" name="bbsNo" value="${bbs.bbsNo}">
				<button>편집완료</button>
				<input type="button" value="목록" onclick="goList()">
			</div>
		</form>
	</div>
	
</body>
</html>