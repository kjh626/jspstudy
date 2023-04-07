<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	// JSTL 안 가져왔기 때문에 c:set태그 안 쓰고 자바코드로 contextPath 만들어줌
	pageContext.setAttribute("contextPath", request.getContextPath());
%>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>
	// 펑션들 작업 공간(목록보겠다 펑션, 상세보기 펑션 등)
	// 자바스크립트의 함수는 호이스팅 대상이다. 언제나 먼저 처리된다. 위에 호출하든 아래에 호출하든 상관없다.
	
	/* 함수 호출 */
	fnGetAllMember();
	
	/* 함수 정의 */
	function fnGetAllMember(){
		/* 
			ajax는 목록 달라고 요청하고, 목록을 응답으로 받아와야 한다. 
			목록 자체를 받아와야 하는데 우리가 배운 것은 XML,JSON으로 받아오는 걸 했었다.
			받아와서 member_list에 뿌려줘야 한다.
		*/
		$.ajax({
			// 요청
			type: 'get',
			url : '${contextPath}/list.do',
			// 응답 (response를 이용해서 응답 끝냄.)
			// 요청을 받은 애는? 컨트롤러 (만들어야 함)
			/* ajax는 오로지 request와 response로 요청과 응답을 끝냄. redirect, forward 없다 */
			dataType: 'json',
			success: function(resData){    // 응답 데이터는 resData로 전달된다.
				console.log(resData);
			}
		})
	}
	
</script>
</head>
<body>
	<%-- 적당한 뷰 만들어주기 --%>
	
	<div class="wrap" >
	
		<h1>회원 관리</h1>
		<%-- ajax처리에서는 서브밋을 수행하지 않는다. 그래서 action, method, button 필요 없다 --%>
		<form id="frm_member">
			<div>
				<label for="id">아이디</label>
				<input type="text" id="id" name="id">
			</div>
			<div>
				<label for="name">이름</label>
				<input type="text" id="name" name="name">
			</div>
			<div>
			<%-- value를 줘야 값이 넘어가서 DB에서 쓸 수 있다. value M,F : 1바이트/ 남,여 : 3바이트  --%>
			<%-- value를 소문자로 저장하면 DB에 소문자로 저장, 대문자로 저장하면 DB에 대문자로 저장 --%>
				<input type="radio" id="male" name="gender" value="M">
				<label for="male">남자</label>
				<input type="radio" id="female" name="gender" value="F">
				<label for="female">여자</label>
			</div>
			<div>
				<label for="address">주소</label>
				<input type="text" id="address" name="address">
			</div>
			<div>
			<%-- 신규등록은 테이블로 데이터 넘기는 버튼 --%>
			<%-- 테이블에서 상세보기 누르면 form에 정보 뿌려주고 수정할 수 있게 해준다 --%>
				<input type="button" value="초기화" onclick="">
				<input type="button" value="신규등록" onclick="">
				<input type="button" value="변경저장" onclick="">
				<input type="button" value="삭제" onclick="">
			</div>
		</form>
		
		<hr>
		
		<table border="1">
		<%-- 돔 인식을 위한 태그가 필요해서 span태그 하나 줌 --%>
			<caption>전체 회원 수 : <span id="member_count"></span>명</caption>
			<thead>
				<tr>
					<td>회원번호</td>
					<td>아이디</td>
					<td>이름</td>
					<td>성별</td>
					<td>주소</td>
					<td>버튼</td>
				</tr>
			</thead>
			<%-- tbody는 비워둘 것 (돔 조작을 통해 만들 예정) --%>
			<tbody id="member_list"></tbody>
		</table>
		
	</div>
	
</body>
</html>