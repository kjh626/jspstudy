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
				<span id="correct"></span>
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
				<input type="hidden" id="memberNo" name="memberNo">
				<input type="button" value="초기화" onclick="fnInit()">
				<input type="button" value="신규등록" onclick="fnAddMember()">
				<input type="button" value="변경저장" onclick="fnModifyMember()">
				<input type="button" value="삭제" onclick="fnRemoveMember()">
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
	
	
	<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
	<script>
	// 펑션들 작업 공간(목록보겠다 펑션, 상세보기 펑션 등)
	// 자바스크립트의 함수는 호이스팅 대상이다. 언제나 먼저 처리된다. 위에 호출하든 아래에 호출하든 상관없다.
	
	/* 함수 호출 */
	fnInit();
	fnGetAllMember();
	
	/* 함수 정의 */
	function fnInit(){
		$('#id').val('').prop('disabled', false);
		$('#name').val('');
		$(':radio[name=gender]').prop('checked', false);
		$('#address').val('');
		
	}
	$('#id').on('keyup', function(){
		var regId = /^[a-z_-][0-9a-z_-]{4,19}$/;
		if(regId.test(id.value)){
			$('#correct').text('올바른 아이디입니다.')
		} else {
			$('#correct').text('틀린 아이디 구성입니다.').attr('style', )
		}
	})
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
				/*
					resData <--- out.println(obj.toString())
					resData = {
						"memberCount": 2,
						"memberList": [
							{},
							{}
						]
					}
				*/
				// ★ resData.memberCount 또는 resData['memberCount'] 두개 기능 똑같다. ★
				$('#member_count').text(resData.memberCount);
				let memberList = $('#member_list');
				// 가장 먼저 기존의 목록을 초기화를 해주는 것이 좋다. 
				memberList.empty();  // 기존의 회원 목록을 지운다.
				
				if(resData.memberCount === 0){
					memberList.append('<tr><td colspan="6">회원이 없습니다.</td></tr>');
				} else {
					/* $.each(배열, (인덱스, 요소)=>{}) */
					/* $.each(배열, function(인덱스, 요소){}) */
					$.each(resData.memberList, (i, element)=>{   // element는 하나의 회원 객체를 의미한다.
						let str = '<tr>';
						str += '<td>' + element.memberNo + '</td>';		
						str += '<td>' + element.id + '</td>';		
						str += '<td>' + element.name + '</td>';
						str += '<td>' + (element.gender === 'M' ? '남자' : '여자') + '</td>';		
						str += '<td>' + element.address + '</td>';
						str += '<td><input type="button" value="조회" class="btn_detail" onclick="fnGetMember('+ element.memberNo +')"></td>';  // 회원번호를 전달하는 호출
						memberList.append(str);
					})
				}
			}
		})
	}
	
	function fnAddMember(){
		$.ajax({
			// 요청
			type: 'post',
			url: '${contextPath}/add.do',
			data: $('#frm_member').serialize(),    // 폼의 모든 입력 요소를 파라미터로 전송한다. (파라미터로 전송하기 위해서는 입력 요소에는 name 속성이 필요하다)
			
			// 응답
			dataType: 'json',  // 응답의 타입은 성공했을 때를 가정해서 적어준다.
			success: function(resData){    // try문의 응답이 resData에 저장된다. resData ={"insertResult": 1}
				if(resData.insertResult === 1) {
					alert('신규 회원이 등록되었습니다.');
					// 목록이 바뀌어 줘야 하니까 함수 재호출 ajax하고 또 ajax해주는 거라 보면 된다.
					fnInit();    	   // 입력란 초기화
					fnGetAllMember();  // 새로운 회원 목록으로 갱신하기
				} else {
					alert('신규 회원 등록이 실패했습니다.');
				}
			},
			error: function(jqXHR){    // jqXHR 객체에는 예외코드(응답코드 : 404, 500 등)와 예외메시지 등이 저장된다.
									   // catch문의 응답 코드는 jqXHR 객체의 status 속성에 저장된다.					   
									   // catch문의 응답메시지는 jqXHR 객체의 responseText 속성(응답한 모든 텍스트가 저장)에 저장된다. 우리가 적은 msg  
				alert(jqXHR.responseText + '(' + jqXHR.status + ')');
			}
			
		})
	}
	
	// onclick="fnGetMember(element.memberNo)"  <- 번호를 아는 곳(목록)에서 해당 번호를 그대로 전달 받음
	// fnGetMember() 함수를 호출할 때 회원번호(element.memberNo)를 인수로 전달하면 매개변수 memberNo가 받는다. 
	function fnGetMember(memberNo){  // (인수로 회원번호를 받음)매개변수로 받는다 그래서 이름 우리마음대로 써줌 (memeberNo가 구분하기 쉽겠지?), 자바스크립트는 변수타입 지정X
		// 버튼에 속성으로 onclick="fnGetMember()" 로 처리해줄 수도 있다. 우리는 클래스 활용해서 온클릭 해줄 예정아니었나보다
		$.ajax({
			// 요청
			type: 'get',
			url: '${contextPath}/detail.do',
			data: 'memberNo=' + memberNo,
			// 응답
			dataType: 'json',
			success: function(resData){    // resData = {"member": {"memberNo":회원번호,...}}
				alert('회원 정보가 조회되었습니다.');
				// 조회로 들어가면 .prop로 id 못바꾸게 만들기.prop('readonly', true) / .prop('disabled', true)
				$('#id').val(resData.member.id).prop('disabled', true);
				$('#name').val(resData.member.name);
				// :radio[name=gender]는 2개다. 이걸 1개로 만드는 걸 시작이라 생각해라. [value="M/F"]
				$(':radio[name=gender][value='+ resData.member.gender +']').prop('checked', true);
				$('#address').val(resData.member.address);
				$('#memberNo').val(resData.member.memberNo);   // <input type="hidden">에 저장하는 값
				// 회원번호는 표시하지는 않았음.
			}
		})
	}
	
	function fnModifyMember() {
		$.ajax({
			// 요청
			type: 'post',
			url: '${contextPath}/modify.do',
			data: $('#frm_member').serialize(),
			// 응답
			dataType : 'json',
			success: function(resData){	 // resData = {"updateResult": 1}
			// 수정이 실패하는 경우는 이름,주소,성별 중에서 사이즈를 길게 적으면 catch블록으로 빠져나간다.
				if(resData.updateResult === 1) {
					alert('회원 정보가 수정되었습니다.');
					fnGetAllMember();   // 새로운 회원 목록으로 갱신
				} else {
					alert('회원 정보 수정이 실패했습니다.');
				}
			},
			error: function(jqXHR){
				alert(jqXHR.responseText + '(' + jqXHR.status + ')' );
			}
		})
		
		
	}
	
	function fnRemoveMember() {
		
	}
	
</script>
	
</body>
</html>