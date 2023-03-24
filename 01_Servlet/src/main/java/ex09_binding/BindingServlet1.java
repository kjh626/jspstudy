package ex09_binding;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/BindingServlet1")
public class BindingServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet 은 request를 쓸 수 있다. 서블릿은 요청을 직접적으로 받는 입장.
		/*
			1. stateless
				1) 상태 없음
				2) 웹 페이지 간의 이동은 stateless한 이동이다.
				3) 현재 페이지에는 이전 페이지의 정보가 없다.
			
			2. binding
				1) 값을 저장할 수 있는 영역에 속성(Attribute)의 형태로 값을 저장하는 것을 말한다.
				2) 저장 영역은 서버가 제공한다.
				3) 3개 영역
					(1) HttpServletRequset : 하나의 요청 내에서 값을 저장할 수 있다. (1회용)
					(2) HttpSession        : 웹 브라우저 종료 전까지 값을 저장할 수 있다. (시간 지정 가능)
					(3) ServletContext     : 컨텍스트(프로젝트, 애플리케이션) 종료 전까지 값을 저장할 수 있다.
				4) 속성(Attribute) 관련 메소드 (※ 속성을 변수이름이라고 생각하면 편하다.)
					(1) getAttribute('속성')     : 값 가져오기 (캐스팅이 필요할 수 있다.)
					(2) setAttribute('속성', 값) : 값 저장하기 (Object 타입으로 저장한다. 가리지 않고 모두 저장 가능, 그래서 꺼내서 쓸 때 캐스팅이 필요할 수 있다.)
					(3) removeAttribute('속성')  : 값 제거하기
					// (1),(2)를 많이 쓴다.
		*/
		
		// HttpServletRequest에 저장하기
		request.setAttribute("a", 100);    // request를 저장소로도 쓴다. 그래서 속성도 살아남지 못함. 넘어가면 null이라고 나옴. ※주의※ request에 속성을 저장했으면 redirect했을 때 값이 전달이 되지 않는다.
		
		// HttpSession에 저장하기
		HttpSession session = request.getSession();
		session.setAttribute("a", 200);    // 저장소가 다르니까 똑같이 "a"라는 이름으로 저장 가능!
		
		// ServletContext에 저장하기
		ServletContext ctx = request.getServletContext();
		ctx.setAttribute("a", 300);
		
		// 페이지 이동하기
		// redirect는 그냥 이동하는 것임.(stateless한 이동. 값없이 이동) redirect로 이동하는 것은 <a href="">랑 똑같다. + 자바스크립트의 location.href랑도 똑같다.
		// request 전달하는 이동방법은 forward. / request 전달 없는 그냥 이동은 redirect
		
		// 1. HttpServletRequest의 전달이 없는 이동 : redirect, <a href="">, location.href=;
		// response.sendRedirect("/01_Servlet/BindingServlet2");    // forward를 확인해보려면 redirect코드는 주석 처리
		
		// 2. HttpServletRequest의 전달이 있는 이동 : forward
		// forward는 내부이동. 서버 내에서 이동(그래서 대표주소 적어줄 필요가 없음)
		request.getRequestDispatcher("/BindingServlet2").forward(request, response);     // 첫 번째 거 전달된다. 100,200,300 나온다.
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
