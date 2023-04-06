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
	$(function(){
		$('.bbs').on('click', function(){
			<%-- ★ 클릭한 bbs 대상은 ★this★라고 부른다 (이벤트 대상은 this. 클릭한 대상은 this!) --%>
			<%-- .요청. 상세보기로 넘겨주면서 파라미터 붙여준다 --%>
			<%-- ↓ 클릭한 div클래스 . 제이쿼리는 data메소드에 변수이름만 적어주면 값을 가져온다--%>
			location.href = '${contextPath}/detail.do?bbsNo=' + $(this).data('bbs_no')
		})
	})
</script>
</head>
<body>
	
	<div>
		<h1>BBS 목록</h1>
	</div>
	<div>
		<c:forEach var="bbs" items="${bbslist}">
			<div class="bbs" data-bbs_no="${bbs.bbsNo}">
			<%-- data속성에 bbsNo라는 변수이름을 가진다 --%>
				<span>${bbs.title}</span>
				<span>...</span>
				<span>${bbs.createdDate}</span>
			</div>
		</c:forEach>
	</div>
	<div>
		<a href="${contextPath}/write.do">작성하러 가기</a>
	</div>

</body>
</html>