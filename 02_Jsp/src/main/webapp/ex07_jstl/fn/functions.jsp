<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%-- <fn: 이렇게 태그로 쓰지 않고,  ${fn: 이리 쓴다.(${fn:length()} => 대부분의 펑션 결과는 값이다. EL과 함께 사용. --%>
	<c:set var="str" value="Hello World" scope="page" />
	
	<%-- substring이 그나마 많이 쓸 거 같다.(if와 합쳐서..)
		너무 길다(if) -> 이만치 표시하세요(substring). 너무 길지 않다(if) -> 이렇게 표시하세요 --%>
	<h3>${fn:length(str)}</h3>
	<h3>${fn:substring(str, 0, 5)}</h3>
	<h3>${fn:substringBefore(str, ' ')}</h3> <%-- ' ' 이전의 텍스트 = Hello --%>	
	<h3>${fn:substringAfter(str, ' ')}</h3> <%-- ' ' 다음부터 --%>
	
	<h3>${fn:indexOf(str, 'l')}</h3>
	<%-- 문제는 lastIndexOf => jstl의 펑션에 없음 --%>
	<h3>${fn:replace(str, 'l', 'z')}</h3>
	<%-- replace가 l을 모두 바꿔준다. --%>
	
	<c:set var="str2" value="<script>location.href='/';</script>" scope="page" />
	<h3>${fn:escapeXml(str2)}</h3> <%-- 이렇게 처리하면 방어가능 (보안에 관련된 펑션) --%>
	<%-- 이런 동작 방식을 xss라고 한다(스크립트 태그로 공격이 들어오면 당하니까 방어하기 위해.). 막아주지 않으면 털린다. 아무 처리 안하면 로그인도 성공할 수 있다. 
			나중에 이런 공격은 자바측에서 막아주는 것이 좋다.--%>
	<%-- '<'를 &lt;으로 바꿔준다. 그러면 태그로 인식이 안 된다. 닫아주는 코드('>')도(&gt;)로 바꿔주면 됨 --%>
	
	
	<%-- true false를 반환하는 메소드라 if랑 사용하면 찰떡이다. --%>
	<c:if test="${fn:startsWith(str, 'Hello')}">
		<h3>Hello로 시작한다.</h3>
	</c:if>
	<c:if test="${fn:endsWith(str, 'World')}">
		<h3>World로 끝난다.</h3>
	</c:if>
	<c:if test="${fn:contains(str, 'h')}">
		<h3>h를 포함한다.</h3>
	</c:if>
	<%-- 대소문자 무시하고 포함여부를 확인 --%>
	<c:if test="${fn:containsIgnoreCase(str, 'h')}">
		<h3>H, h를 포함한다.</h3>
	</c:if>
	
	<c:set var="str3" value="a,b,c,d,e,f,g,h,i,j,k" scope="page" />
	<c:set var="items" value="${fn:split(str3, ',')}" scope="page" />
	<%-- ','로 분리해서 배열로 만들어 달라는 의미이다. --%>
	<c:forEach var="item" items="${items}" varStatus="vs">
		<div>${vs.index} - ${item}</div>
	</c:forEach>
	
	<%-- 다시 합치는 과정 --%>
	<c:set var="str4" value="${fn:join(items, ',')}" scope="page" />
	<h3>${str4}</h3>
	
</body>
</html>