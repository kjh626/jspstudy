<%@page import="java.io.FileWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.File"%>
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
	
		String createdDate = request.getParameter("created_date");
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 작성 IP
		String ip = request.getRemoteAddr();
		
		// real path
		// String realPath = request.getServletContext().getRealPath("storage");
			// ServletContext == application 이라는 이름의 객체로 만들어 놓음
		String realPath = application.getRealPath("storage");    // ServletContext 객체 == application 내장 객체
		
		// 디렉터리
		File dir = new File(realPath);
		if(dir.exists() == false){
			dir.mkdirs();
		}
		
		/* IP주소는 세팅에 따라서 2가지 버전의 IP가 있을 수 있다.
			IPv4 : 127.0.0.1        => 127_0_0_1
			IPv6 : 0:0:0:0:0:0:0:1  => 0_0_0_0_0_0_0_1
		*/
		// 파일명 : 작성IP_작성일.txt
		String filename = ip.replace('.', '_').replace(':', '_') + "_" + createdDate.replace("-", "") + ".txt";  // 작은따옴표, 큰따옴표 둘 다 가능(통일해서 쓰면 됨)
		// String filename = ip.replaceAll("[.:]", "_")+ "_" + createdDate.replace("-", "") + ".txt";  // replaceAll 은 정규식으로 넣어줘야 한다.
		
		// 파일 객체
		File file = new File(dir, filename);
		
		// 문자 파일 출력 스트림 생성
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		
		// 파일 생성
		bw.write("작성일자 : " + createdDate);
		bw.newLine();
		bw.write("작성자 : " + writer);
		bw.newLine();
		bw.write("제목 : " + title);
		bw.newLine();
		bw.write("내용");
		bw.newLine();
		bw.write(content);
		bw.flush();
		bw.close();
		
		// persistent하게.. 나중에는 파일로 저장하지 않고 DB에 저장한다
	%>
	
	<script>
	
		var isCreated = <%=file.exists()%>;  // var isCreated = false; var isCreated = true;
		if(isCreated) {
			alert('<%=filename%> 파일이 생성되었습니다.');
			location.href = '<%=request.getContextPath()%>/ex02_builtin_object/application/write_form.jsp';
		} else {
			alert('<%=filename%> 파일이 생성되지 않았습니다.');
			history.back();
		}
		// 히스토리백, 로케이션 차이점 : 작성내용 남아있냐 남아있지 않냐 의 차이
		
	</script>
	
	

</body>
</html>