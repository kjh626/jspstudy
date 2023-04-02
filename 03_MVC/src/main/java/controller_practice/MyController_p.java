package controller_practice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import model.MyAgeService;
import model.MyBmiService;
import model.MyService;
import model.MyTodayService;

@WebServlet("/coc")
public class MyController_p extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		ActionForward actionForward = null;
		
		MyService myService = null;
		
		switch(urlMapping) {
		case "/today.coc":
			myService = new MyTodayService();
			break;
		case "/age.coc":
			myService = new MyAgeService();
			break;
		case "/bmi.coc":
			myService = new MyBmiService();
			break;
		}
		
		if(myService != null) {
			actionForward = myService.execute(request, response);
		}
		if(actionForward != null) {
			if(actionForward.isRedirect()) {
				response.sendRedirect(actionForward.getPath());
			} else {
				request.getRequestDispatcher(actionForward.getPath()).forward(request, response);				
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
