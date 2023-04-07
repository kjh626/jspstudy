package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IPostService;
import service.PostDeleteService;
import service.PostDetailService;
import service.PostListService;
import service.PostSaveService;

@WebServlet("*.post")    // /list.post  /detail.post  /save.post  /change.post  /edit.post  /delete.post

public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 요청, 응답 인코딩
		// 여기서 한번 하고 서비스로 넘어가면 서비스에서 인코딩할 필요가 없어짐 
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// urlMapping 확인
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		// 서비스 타입 선언
		IPostService service = null;
		
		// forward 경로
		String path = null;

		// urlMapping에 따른 서비스 선택(생성)
		switch(urlMapping) {
		case "/list.post":
			service = new PostListService();
			break;
		case "/save.post":
			service = new PostSaveService();
			break;
		case "/write.post":
			path = "post/write.jsp";
			// 이러면 service는 null인 상태라 if문(service != null)은 넘어가고, if(path !=null) 실행된다.
			break;
		case "/detail.post":
			service = new PostDetailService();
			break;
		case "/delete.post":
			service = new PostDeleteService();
			break;
		}

		// 선택된 서비스 실행
		if(service != null) {
			// 모든 execute메소드는 throws Exception 하기 때문에 여기서 try catch가 필요하다 
			try {
				// redirect가 필요한 서비스(삽입,수정,삭제)는 서비스 내에서 직접 redirect하고(location.href를 이용) path에 null을 반환한다.
				path = service.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
			
		// 이동할 경로(path)로 forward  <-- 여기서는 redirect 이동없이 forward로만 이동하려고 한다.
		if(path != null) {
			request.getRequestDispatcher(path).forward(request, response);
		}
		// redirect를 하면 path에 null값 반환 -> 그러면 if문 충족못해서 forward안 하고 그냥 넘어간다. 그래서 액션포워드, 리다이렉트용 코드 빼버린 것이다.
		// a태그를 통한 이동, loaction.href 를 통한 이동 둘 다 리다이렉트 이동이다.
	
	
	
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
