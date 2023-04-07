package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IMemberService;
import service.MemberAddService;
import service.MemberDetailService;
import service.MemberListService;
import service.MemberModifyService;
import service.MemberRemoveService;

@WebServlet("*.do")
public class MemberRestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 요청 인코딩만
		request.setCharacterEncoding("UTF-8");
		
		// URLMapping
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		// 컨트롤러 여기까지 하고 서비스를 만들어주고 서비스 타입 잡아주기
		
		// 모든 서비스의 공통 타입(인터페이스)
		IMemberService service = null;
		
		// URLMapping에 따른 서비스 선택
		switch(urlMapping) {
		case "/list.do":
			service = new MemberListService();
			break;
		case "/detail.do":
			service = new MemberDetailService();
			break;
		case "/add.do":
			service = new MemberAddService();
			break;
		case "/modify.do":
			service = new MemberModifyService();
			break;
		case "/remove.do":
			service = new MemberRemoveService();
			break;
		}
		
		// 선택된 서비스 실행
		// 모든 서비스는 예외를 던지기 때문에 호출부(실행하는 영역)에서 트라이 캐치를 해줘야 한다.
		// 서비스 실행할 때 요청(request), 응답(response) 모두 하기 때문에 이제 컨트롤러의 역할은 끝
		if(service != null) {
			try {
				service.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		// 현재 응답의 인코딩은 없다. -> 각 서비스에서 인코딩해줄 것이다. 각 서비스별로 응답을 만들 때 response.setContetType을 해줄 것임.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
