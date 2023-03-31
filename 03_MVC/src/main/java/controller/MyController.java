package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MyAgeService;
import model.MyService;
import model.MyTodayService;

@WebServlet("*.do")     // .do 로 끝나는 모든 요청
public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 요청 인코딩 & 응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset-UTF-8");
		
		// URLMapping 확인 (/today.do, /age-do)
		String requestURI = request.getRequestURI();   				     /* /02_Mvc/today.do */
		String contextPath = request.getContextPath();  				 /* /03_Mvc          */
		String urlMapping = requestURI.substring(contextPath.length());  /* /today.do        */
		
		// MyService 인터페이스 타입의 myService 객체 선언하기
		MyService myService = null;     // 선언부. 아직 어떤서비스가 들어올 지는 정해지지 않았다. 바로 위의 urlMapping의 값에 따라 달라질 것이다.
		
		// URLMapping에 따른 모델(서비스) 생성하기 (2가지 코드 방식이있다. -> if 또는 switch : ※ switch 쓸 거면 break 빼 먹으면 망한다. 꼭꼭 써줘야 한다.)
		switch(urlMapping) {
		case "/today.do":
			myService = new MyTodayService();
			break;
		case "/age.do":
			myService = new MyAgeService();
			break;
		}
		
		// 모델(서비스) 실행하기
		String path = myService.execute(request, response);
		
		// 응답 View로 이동하기
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
