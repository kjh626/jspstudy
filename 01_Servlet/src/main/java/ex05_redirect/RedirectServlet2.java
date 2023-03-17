package ex05_redirect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RedirectServlet2")
public class RedirectServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 리다이렉트 이후(두 번째 요청) 파라미터 확인  ☆ 왜 null이 뜰까? 를 알아둬라
		// 두 번째 요청 : /01_Servlet/RedirectServlet2   
		String model = request.getParameter("model");    // 두 번째 요청에 파라미터 model이 없기 때문에 null 값이 저장된다.
		System.out.println("RedirectServlet2 : " + model);  //  null 이 나온다.
		/* 
		   리다이렉트는 요청이 2번 첫번째 요청에서는 model을 보냈지만 
	 	   두번째 요청에서는 model을 보내지 않음. 리다이렉트 이동할 때 파라미터는 최종목적지로 전달되지 않는다. 중간에 끊어진다. 
	 	   리다이렉트의 주요특징 중 하나이다.
		*/
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
