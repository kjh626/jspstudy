package ex03_parameter_practice;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ArrayServletpractice")
public class ArrayServletpractice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String[] tel = request.getParameterValues("tel");
		String[] hobbies = request.getParameterValues("hobbies");
		
		response.getWriter().append("tel: ").append(tel[0] + "-" + tel[1] + "-" + tel[2]).append(", hobbies: " + Arrays.toString(hobbies));
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
