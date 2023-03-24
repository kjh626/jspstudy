package ex10_Cookie;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieServlet1")

public class CookieServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		/*
			Cookie
			
			1. 서버가 만들어서 클라이언트가 저장한다. 
			2. 보안에 취약하다.
		*/
		
		// 쿠키 만들기(name, value)
		Cookie cookie1 = new Cookie("name", "김정현");
		Cookie cookie2 = new Cookie("address", URLEncoder.encode("서울시 금천구 가산동", "UTF-8"));    // 유효하지 않은 문자(대표적으로 공백)는 UTF-8로 인코딩해서 저장한다.
		
		// 쿠키가 저장될 경로 설정하기
		cookie1.setPath(request.getContextPath());   // 컨텍스트 패스 (/01_Servlet)
		cookie2.setPath(request.getRequestURI());    // 전체 경로(서블릿 경로) (/01_Servlet/CookieServlet1)
		// 경로와 이름이 똑같으면 쿠키는 덮어쓰기한다.
		
		// 쿠키 유효시간 설정하기 (초 단위이다.)
		cookie1.setMaxAge(60 * 60 * 24 * 7);    // 7일간 보관되는 쿠키
		cookie2.setMaxAge(60 * 60);             // 1시간 동안 보관되는 쿠키
		
		// 쿠키 저장하기(응답으로 처리해야 한다.)
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		
		// CookieServlet2으로 redirect 이동
		response.sendRedirect("/01_Servlet/CookieServlet2");
		
		/*
			경로가 달라지면 쿠키가 달라진다.
			CookieServlet1에서는 name, address 값 확인가능한데
			CookieServlet2에서는 address값 확인할 수 없고, name만 있다.
				-> name값은 request.getContextPath()를 사용 (/01_Servlet)
		*/
		// 쿠키 삭제하기 : 유효시간을 0으로 설정 -> 쿠키 저장하기 까지 하면 쿠키 삭제임 => 저장과 삭제 똑같은 코드쓴다.
		
		
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
