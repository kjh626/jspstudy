<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
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
		request.setCharacterEncoding("UTF-8");
	
		String item = request.getParameter("item");
		int itemCount = Integer.parseInt(request.getParameter("item_count"));
	
		/* 
			item: 신라면
			itemCount: 3 -- key:value
			Map에 저장하면 되겠다. (자바스크립트에서는 객체, 자바는 맵)
			자바스크립트의 객체는 jsonObject라고 보면 됨. 자바에서 jsonObject처리하려면 Map으로 하면 된다.
			{						   자바에서는 Map		
				item:신라면				  key에 item,itemCount 
				itemCount:3				  value에 신라면, 3    을 넣어준다.
			} property:value		→   key:value          
			자바에서는 Map으로 반환하면 자바스크립트에서는 obj(객체)로 받는 경우 매우 많다.
			이거 처리해주는 라이브러리 있다. (JSONObject 라이브러리를 쓰면서 연습했는데 불편함. 나중에 라이브러리 알려줌)
		*/ 
		
		// 제품명 + 구매수량을 하나의 Map으로 저장한다.  => Map하나는 제품 하나 -> arrayList에 Map 담아서 보관하면 여러개 
		Map<String, Object> map = new HashMap<>();
		map.put("item", item);
		map.put("itemCount", itemCount);
		
		// 참고 (자바에서 쓸 수 있는 또 하나의 방법=> 클래스 만들어서 객체 작업해주는 것)
		/*
		class Product {
			String item;
			int itemCount;
			Product(String item, int itemCount){
				this.item = item;
				this.itemCount = itemCount;
			}
		}
		Product product = new Product(item, itemCount);
		*/
		
		// session에 저장된 cart 속성이 있는지 확인한 뒤 없다면 새로운 cart를 만들어서 session에 저장한다.
		// 괄호 는 타입 복붙해줌 세션에 있는 카트의 타입
		List<Map<String, Object>> cart = (List<Map<String, Object>>) session.getAttribute("cart");
		if(cart == null){
			cart = new ArrayList<>();
			session.setAttribute("cart", cart);
		}
		/*
		cart     session
		
		
		cart     memory
		add
		add를 하면 memory의 cart에 추가해주는 것인데, 이걸 session의 cart도 알 수 있다.(주소값 참조) => 주소를 공유하는 개념이라고 생각해라
		일단 memory에 저장해두고 session에 전해준다..? session의 cart는 주소값을 참조한다.
		*/
		
		// session의 cart에 Map 저장하기  ※ cart는 session에 올라간 cart 맞다.(코드를 보라..)
		cart.add(map);
	%>

	<script>
	<%-- 확인,취소 버튼 누를수 있는 대화상자 confirm() --%>
		if(confirm('<%=item%>을 장바구니에 추가했습니다.\n장바구니를 확인하려면 "확인", 계속 쇼핑하려면 "취소"버튼을 누르세요.')) {
			<%-- 확인을 눌렀을 때 --%>
			location.href = '03_cart_list.jsp';
		} else {
			location.href = '01_form.jsp';
		}
	</script>

</body>
</html>