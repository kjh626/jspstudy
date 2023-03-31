<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- fmt는 중요하진 않고 나중에 필요하면 꺼내서 봐라 --%>
	<c:set var="n" value="12345.678" scope="page" />
	
	<%-- 천 단위 구분기호 사용하기 + 소수점 아래 자리 개수 정하기 --%>
	<%-- 쇼핑몰에서 돈 표시할 때 요긴하게 쓰면 될듯 --%>
	<h1><fmt:formatNumber value="${n}" pattern="#,##0" /></h1> 
	<h1><fmt:formatNumber value="${n}" pattern="#,##0.00" /></h1>
	
	<%-- 백분율 : 숫자에 100을 곱한 뒤 %를 붙인다. --%>
	<h1><fmt:formatNumber value="${n}" type="percent" /></h1>
	
	<%-- 통화 : 통화 기호와 천 단위 구분기호를 모두 사용한다. --%>
	<h1><fmt:formatNumber value="${n}" type="currency" currencySymbol="￦" /></h1>
	<h1><fmt:formatNumber value="${n}" type="currency" currencySymbol="$" /></h1>

	<%-- 여기서는 기본적으로 자릿수 조정은 반올림이다.
		 반올림을 해서는 안 될 경우에는 자릿수 처리를 자바에서 미리 해와야 한다. --%>

</body>
</html>