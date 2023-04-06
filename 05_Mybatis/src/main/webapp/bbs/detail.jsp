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
	function goEdit(){
		// 누굴 편집하는지 알아야 되니까 파라미터 전달 필요
		location.href='${contextPath}/edit.do?bbsNo=${bbs.bbsNo}';
		
		// 편집을 누르면 제목이나 내용 입력할 수 있게 바꿔준다(→ EditService).
		// or (스프링) 편집을 눌렀을 때 title과 content를 input과 textarea로 바꿔주는 작업을 할 수 있다.(프론트작업. 자바스크립트,제이쿼리 작업)
		// 우리는 후자 작업은 아니고 새페이지를 만들어서 해줄 거임. 내용물들을 가져갈 수 있게 해주려고 함  
	}
	function goRemove(){
		// 주소로 remove.do 하면 못하게 하도록 튕겨내는 것 4장에서 했었다.. , 삭제버튼을 form방식으로 만들고 포스트방식으로만 삭제 가능하게 만들어 줬었음.
		// GET방식(주소로 요청) 으로 요청하면 서비스에 잘못된 요청 alert 넣어줘서 처리했었다.
		if(confirm('삭제할거야?')){ 
			// location은 get방식이다? redirect
			// location 안 쓰고 frm_remove에 서브밋을 해준다
			$('#frm_remove').submit();
		} 
	}
	function goList(){
		location.href='${contextPath}/list.do';
	}
</script>
</head>
<body>

	<div>
		<h1>BBS 상세</h1>
	</div>
	<div>
		<div>BBS_NO : ${bbs.bbsNo}</div>
		<div>TITLE : ${bbs.title}</div>
		<div>CREATED_DATE : ${bbs.createdDate}</div>
		<div>MODIFIED_DATE : ${bbs.modifiedDate == null ? '없음' : bbs.modifiedDate}</div>
		<pre>${bbs.content}</pre>
	</div>
	<div>
		<input type="button" value="편집" onclick="goEdit()">
		<input type="button" value="삭제" onclick="goRemove()">
		<input type="button" value="목록" onclick="goList()">
		<form id="frm_remove" method="post" action="${contextPath}/remove.do">
			<input type="hidden" name="bbsNo" value="${bbs.bbsNo}">
		</form>
	</div>

</body>
</html>