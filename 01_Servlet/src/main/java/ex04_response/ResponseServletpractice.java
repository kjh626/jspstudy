package ex04_response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ResponseServletpractice")
public class ResponseServletpractice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String model = request.getParameter("model");
		String strPrice = request.getParameter("price");
		
		int price = 0;
		if(strPrice != null && strPrice.isEmpty() == false) {
			price = Integer.parseInt(strPrice);
		}
		
		// 응답 만들기
		
		// 1. 응답할 데이터의 MIME 타입과 UTF-8 인코딩
		response.setContentType("text/html; charset=UTF-8");
		
		// 2. 응답 스트림 생성(IOException 처리가 필요하다. -> 이미 처리되어 있다.)
		PrintWriter out = response.getWriter();
	
		// 3. 응답 만들기
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"ko\">");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>나의 첫 응답</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>모델명: " + model + "</h1>");
		out.println("<h1>가격: " + price + "원</h1>");
		out.println("</body>");
		out.println("</html>");
		out.flush();
		out.close();
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
