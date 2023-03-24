package ex10_Cookie_practice;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieServletpractice1")
public class CookieServletpractice1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 쿠키 만들기
		Cookie cookie1 = new Cookie("name", "민경태");
		Cookie cookie2 = new Cookie("address", URLEncoder.encode("서울시 금천구 가산동", "UTF-8"));
		Cookie cookie3 = new Cookie("job", URLEncoder.encode("요양 보호사", "UTF-8"));
		
		cookie1.setPath("/01_Servlet");
		cookie2.setPath("/01_Servlet/CookieServlet1");
		
		cookie1.setMaxAge(60 * 60 * 24);
		cookie2.setMaxAge(60 * 60);
		
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		response.addCookie(cookie3);
		
		response.sendRedirect("/01_Servlet/CookieServletpractice2");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
