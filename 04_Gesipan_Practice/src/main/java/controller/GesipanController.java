package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.GesipanAddService;
import service.GesipanDetailService;
import service.GesipanListService;
import service.GesipanModifyService;
import service.GesipanRemoveService;
import service.IGesipanService;

@WebServlet("*.ho")
public class GesipanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 요청과 응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// URLMapping 확인
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		// 모든 서비스의 공통 타입 선언
		IGesipanService service = null;
		
		// ActionForward 선언
		ActionForward af = null;
		
		// URLMapping에 따른 서비스 생성
		switch(urlMapping) {
		case "/getAllGesipanList.ho":
			service = new GesipanListService();
			break;
		case "/getGesipanByNo.ho":
			service = new GesipanDetailService();
			break;
		case "/addGesipan.ho":
			service = new GesipanAddService();
			break;
		case "/modifyGesipan.ho":
			service = new GesipanModifyService();
			break;
		case "/removeGesipan.ho":
			service = new GesipanRemoveService();
			break;
		case "/writeGesipan.ho":
			af = new ActionForward("gesipan/write.jsp", false);
			break;
		}
		
		// 서비스 실행
		if(service != null) {
			af = service.execute(request, response);
		}
		
		// 응답 View로 이동
		if(af != null) {
			if(af.isRedirect()) {
				response.sendRedirect(af.getPath());
			} else {
				request.getRequestDispatcher(af.getPath()).forward(request, response);
			}
		}
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
