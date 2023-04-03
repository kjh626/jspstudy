package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.BoardAddService;
import service.BoardDetailService;
import service.BoardListService;
import service.BoardModifyService;
import service.BoardRemoveService;
import service.IBoardService;
//.do 로 끝나는 매핑. 끝나는 서픽스값은 꼭 do로 써야하는 건 아님
@WebServlet("*.do")  // getAllBoardList.do  , getBoardByNo.do  ,  addBoard.do  ,  mofiyBoard.do  , removeBoard.do  우리가 쓸 5개의 매핑

public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 요청과 응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	
		// URLMapping
		String requestURI = request.getRequestURI();      				 /*    /04_Dbcp/getAllBoardList.do   */
		String contextPath = request.getContextPath();    				 /*    /04_Dbcp    (8글자)           */
		String urlMapping = requestURI.substring(contextPath.length());  /*    /getAllBoardList.do           */
		
		// 모든 서비스의 공통 타입 선언
		IBoardService service = null;
		
		// ActionForward 선언
		ActionForward af = null;
		
		// URLMapping에 따른 서비스 생성
		switch(urlMapping) {
		case "/getAllBoardList.do":
			service = new BoardListService();
			break;
		case "/getBoardByNo.do":
			service = new BoardDetailService();
			break;
		case "/addBoard.do":
			service = new BoardAddService();
			break;
		case "/modifyBoard.do":
			service = new BoardModifyService();
			break;
		case "/removeBoard.do":
			service = new BoardRemoveService();
			break;
		}
		
		// 서비스 실행 (service가 위에 null로 선언되어 있어서 nullpointerException고려해서 코드 작성해야한다.)
		if(service != null) {
			af = service.execute(request, response);
		}
		// 응답 View로 이동
		if(af != null) {
			if(af.isRedirect()) {
				response.sendRedirect(af.getPath());
			} else {
				request.getRequestDispatcher(af.getPath()).forward(request, response);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
