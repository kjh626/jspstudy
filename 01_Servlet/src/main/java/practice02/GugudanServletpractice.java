package practice02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GugudanServletpractice")
public class GugudanServletpractice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String strDan = request.getParameter("dan");
		String strNum = request.getParameter("num");
		String strResult = request.getParameter("result");
		
		int dan = 0, num =0, result = 0;
		if(strDan != null && strDan.isEmpty() == false) {
			dan = Integer.parseInt(strDan);
		}
		if(strNum != null && strNum.isEmpty() == false) {
			num = Integer.parseInt(strNum);
		}
		if(strResult != null && strResult.isEmpty() == false) {
			result = Integer.parseInt(strResult);
		}
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		if(dan * num == result) {
			out.println("alert('정답입니다.')");
		} else {
			out.println("alert('오답입니다.')");
		}
		//out.println("history.back()");
		//out.println("location.href='/01_Servlet/practice02/clientprac1.html'");
		out.println("location.href='/01_Servlet/practice02/clientprac2.html'");
		out.println("</script>");
		out.flush();
		out.close();
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
