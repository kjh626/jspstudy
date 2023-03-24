package ex10_Cookie_practice;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieServletpractice2")
public class CookieServletpractice2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			for(int i = 0; i < cookies.length; i++) {
				System.out.println("CookieServletp2 쿠키 이름: " + cookies[i].getName() + ", 쿠키값: " + URLDecoder.decode(cookies[i].getValue(), "UTF-8"));
			}
		}
		
		Cookie cookie1 = new Cookie("name", "");
		Cookie cookie2 = new Cookie("job", "");
		cookie1.setMaxAge(0);
		cookie2.setMaxAge(0);
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		
		response.sendRedirect("/01_Servlet/CookieServletpractice3");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
