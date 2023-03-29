<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div>
		<h1>쇼핑 목록</h1>
		<form action="02_add_to_cart.jsp">
			<select name="item">
				<option value="양파">양파</option>
				<option value="대파">대파</option>
				<option value="마늘">마늘</option>
			</select>
			<input type="number" name="item_count" min="1" max="10">개
			<button>장바구니에 추가</button> 
		</form>
	</div>

</body>
</html>