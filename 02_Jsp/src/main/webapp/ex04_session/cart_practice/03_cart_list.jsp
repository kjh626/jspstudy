<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
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
		// session에 저장된 carta 속성 가져오기
		List<Map<String, Object>> carta = (List<Map<String, Object>>)session.getAttribute("carta");
	%>
	
	<% if(carta == null){ %>
		<div>장바구니가 비었습니다.</div>
	<% } else { %>
		<div>장바구니 목록</div>
		<% for(int i = 0; i < carta.size(); i++){ %>
			<div><%=carta.get(i).get("item")%> <%=carta.get(i).get("itemCount")%>개</div>
		<% } %>
	<% } %>
	
	<div>
		<input type="button" value="계속쇼핑하기" onclick="goShopping()">
		<input type="button" value="장바구니비우기" onclick="removeCart()">
	</div>
	<script>
		function goShopping(){
			location.href = '01_form.jsp';
		}
		function removeCart(){
			if(confirm('장바구니를 비울까요?')){
				location.href = '04_remove_cart.jsp';
			} else {
				alert('취소되었습니다.');
			}
		}
	</script>

</body>
</html>