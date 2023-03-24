package ex09_binding_practice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BindingServletpractice2")
public class BindingServletpractice2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// HttpServletRequest에 저장된 속성 확인
		System.out.println(request.getAttribute("b"));
		
		// HttpSession에 저장된 속성 확인
		System.out.println((int)request.getSession().getAttribute("b"));
		
		// ServletContext에 저장된 속성 확인
		System.out.println((int)request.getServletContext().getAttribute("b"));
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
