<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--
		파라미터 확인을 위한 EL 객체
		1. param
		2. paramValues -> 거의 쓸 일 없음.
	--%>
	<%-- application에 저장된 a값 제거하려면 톰캣에서 02_Jsp를 지우면 된다. --%>
	<%-- 파라미터가 전송되었지만, ${a}로는 웹페이지에 보이게 할 수 없다. param 써야됨 --%>
	
	<%-- 파라미터 호출하는 방법 --%>
	<h1>${param.a}</h1>
	<h1>${paramValues.b[0]}</h1>
	<h1>${paramValues.b[1]}</h1>
	<h1>${paramValues.b[2]}</h1>


	<%-- 파라미터 x, y의 크기 비교(파라미터는 String이기 때문에 사전 편찬 순으로 크기를 비교한다. --%>
	<ul>
		<li>${param.x lt param.y}</li>
		<li>${param.x le param.y}</li>
		<li>${param.x gt param.y}</li>
		<li>${param.x ge param.y}</li>
	</ul>
	<%--
		파라미터의 특징으로 봐라 (중요하진 않음, 해결책도 중요하지 않음)
		true 2개 false 2개로 나온다. 뒤바뀌어서 나온다.
		그 이유는 parameter의 타입때문이다. parameter의 타입은 무조건(100%) String이라서
		String으로 크기비교 하는 것은 사전편찬순으로 비교하는 것이다. 
		"10", "5" 사전편찬순으로는 1이 먼저 나와서 10이 더 작다고 나오고 5가 더 크다고 나온다.
		숫자 크기 비교가 아니라 알파벳 비교임 "100"이 나와도 "5"보다 작다고 나온다.
	--%>

	<%-- 파라미터 x, y의 크기 비교 해결(EL에서 "10"-"5"==5이다.) --%>
	<ul>
		<li>${param.x - param.y lt 0}</li>
		<li>${param.x - param.y le 0}</li>
		<li>${param.x - param.y gt 0}</li>
		<li>${param.x - param.y ge 0}</li>
	</ul>

</body>
</html>