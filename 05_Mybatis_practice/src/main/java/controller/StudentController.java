package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.StudentAddService;
import service.StudentDetailService;
import service.StudentEditService;
import service.StudentListService;
import service.StudentRemoveService;
import service.IStudentService;

@WebServlet("*.do")    /*   /list.do  /detail.do  /write.do  /add.do  /edit.do  /modify.do  /remove.do  */
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 요청,응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		IStudentService service = null ;
		ActionForward af = null;
		
		switch(urlMapping) {
		case "/write.do":
			af = new ActionForward("student/write.jsp", false);
			// 삽입,수정,삭제가 아니다 => 다 forward로 보내면 된다.
			break;
		case "/add.do":
			service = new StudentAddService();
			break;
		case "/list.do":
			service = new StudentListService();
			break;
		case "/detail.do":
			service = new StudentDetailService();
			break;
		case "/edit.do":
			service = new StudentEditService();
			break;
		case "/remove.do":
			service = new StudentRemoveService();
			break;
		}
		
		if(service != null) {
			af = service.execute(request, response); 
		}
		
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
