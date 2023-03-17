package ex06_forward;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ForwardServlet1")
public class ForwardServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 포워드 이전(첫 번째 요청) 파라미터 확인
		String model = request.getParameter("model");
		System.out.println("ForwardServlet1 : " + model);
		
		// 포워드(전달) - request객체가 한다. ↔ 리다이렉트는 response썼음
		request.getRequestDispatcher("/ForwardServlet2").forward(request, response);  // Urlmapping만 적어준다   . forward(request, response): request와 response를 그대로 전달하겠다.
	
		// ※ 포워드는 a링크에서 요청 1번만 했다. 그래서 주소창에 ForwardServlet1이 뜸. 자기들끼리(서버 내부에서) 돌리기 때문에..
		//   그래서 서버 내부의 mapping만 적어줌 <- request.getRequestDispatcher("/ForwardServlet2")  . 포워드는 ContextPath 안 적어야 한다.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
