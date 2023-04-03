package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IBoardService;
//.do 로 끝나는 매핑. 끝나는 서픽스값은 꼭 do로 써야하는 건 아님
@WebServlet("*.do")  // getAllBoardList.do  , getBoardByNo.do  ,  addBoard.do  ,  MofiyBoard.do  , RemoveBoard.do  우리가 쓸 5개의 매핑

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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
