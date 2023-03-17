package practice06;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FileResponseServlet")
public class FileResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
			1. 전달 받은 파일을 이용해서 경고창 만들기
				예시) 2023-03-17-민경태-금요일이다.txt 파일이 생성되었습니다.
				
			2. 작성 화면으로 돌아가기
				client.html로 이동하기
		*/
		request.setCharacterEncoding("UTF-8");
		
		String filename = request.getParameter("filename");
		
		// 응답 타입
		response.setContentType("text/html; charset=UTF-8");
		
		// 응답할 출력스트림 생성
		PrintWriter out = response.getWriter();
		
		// 응답하기 => 이젠 응답이 끝나는 거임
		// out.print() 로 라인 생략하고 작성하면 길게 한 줄로 작성된다. 이러면 구분을 위해 ; 이 필요함.
		out.println("<script>");
		out.println("alert('" + filename + " 이 생성되었습니다.')");
		out.println("location.href='/01_Servlet/practice06/client.html'");    // 너무 이 location의 경로에 대해서 공부 안 해도 된다. (이런 경로는 실무 패턴에서는 없다고 보면 됨.)
		out.println("</script>");
		out.flush();
		out.close();
		// 응답이 끝났으니까 여기서는 밑에 더 적어봐야 소용없다.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
