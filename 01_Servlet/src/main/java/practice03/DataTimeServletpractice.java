package practice03;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/datetimepractice")
public class DataTimeServletpractice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String type = request.getParameter("type");
		
		String result = null;
		switch(type) {
		case "1":
			result = LocalDate.now().toString();
			break;
		case "2":
			result = LocalTime.now().toString();
			result = result.substring(0, result.indexOf("."));
			break;
		}
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		out.println("alert('요청결과는 " + result + "입니다.')");
		out.println("</script>");
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
