<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<%-- jsp:(콜론) 이 붙은 것들이 Jsp 액션태그들이다. <하고 자동완성보면 보임. --%>
	<%-- include 액션 태그는 그냥 include보다 뭔가 더 할 수 있다. --%>
	
<%-- 동적 include : 포함되는 페이지에 파라미터를 전달할 수 있다. (jsp 액션 태그) --%>
<%-- header.jsp로 전달하고 싶은 파라미터를 태그로 만들어줌 ( 파라미터 이름이 title, 값은 body1 ) --%>
<jsp:include page="header.jsp">
	<jsp:param value="body1" name="title"/>
</jsp:include>

	<h1>body1</h1> 

<%-- 정적 include : 항상 같은 모습의 페이지를 포함한다. (include 지시어) --%>
<%@ include file="footer.jsp"%>
