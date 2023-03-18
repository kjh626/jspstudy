package ex01_lifecycle;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloServletpractice")
public class HelloServletpractice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HelloServletpractice() {
        super();
        System.out.println("생성자 호출");
    }
    
    public void init(ServletConfig config) throws ServletException {
		System.out.println("init() 호출");
	}
    
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("service() 호출");
		
		// HTTP Method(요청 메소드)에 따른 doGet() 또는 doPost() 메소드 호출하기
		switch(request.getMethod()) {
		case "GET":
			doGet(request, response);
			break;
		case "POST":
			doPost(request, response);
			break;
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
