package com.gdu.ex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.ex.common.ActionForward;
import com.gdu.ex.service.ExDetailService;
import com.gdu.ex.service.ExListService;
import com.gdu.ex.service.ExRemoveService;
import com.gdu.ex.service.ExSaveService;
import com.gdu.ex.service.ExService;

@WebServlet("*.do")

public class ExController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String requestURI = request.getRequestURI();                    /*   /ex/list.do   */
		String contextPath = request.getContextPath();                  /*   /ex           */
		String urlMapping = requestURI.substring(contextPath.length()); /*   /list.do      */
		
		ExService service = null;
		
		ActionForward af = null;
		
		switch(urlMapping) {
		case "/list.do":
			service = new ExListService();
			break;
		case "/detail.do":
			service = new ExDetailService();
			break;
		case "/write.do":
			// ex/write.jsp로 forward하기(단순이동) - ActionForward 객체 만들기
			af = new ActionForward("ex/write.jsp", false);
			break;
		case "/save.do":
			service = new ExSaveService();
			break;
		case "/remove.do":
			service = new ExRemoveService();
			break;
		}
		
		if(service != null) {
			af = service.execute(request, response);
		}
		
		if(af.isRedirect()) {
			response.sendRedirect(af.getPath());
		} else {
			request.getRequestDispatcher(af.getPath()).forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
