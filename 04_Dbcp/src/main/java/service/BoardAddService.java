package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.BoardDTO;
import repository.BoardDAO;

public class BoardAddService implements IBoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. 요청 파라미터
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 2. BoardDTO 객체 생성  (파라미터를 사용해서 BoardDTO 객체를 만들어준다)
		// 객체(Bean) 아니면 Map으로 가능하다.(title,content 를 넣는 방법)
		BoardDTO board = new BoardDTO();  // 디폴트 생성자를 사용하여 title과 content만 setter로 넣어준다
		board.setTitle(title);
		board.setContent(content);
		
		// 3. 삽입을 위해서 DB로 BoardDTO를 전달(BoardDAO의 insertBoard 메소드)  <- 서비스가 직접 DB로 가지는 않는다.
		//      -> insert메소드를 호출해서 DB로 전달한다.  / BoardDAO.getInstance() ==> dao임.
		int insertResult = BoardDAO.getInstance().insertBoard(board);   // 0(실패) 또는 1(성공)
		System.out.println(insertResult == 1 ? "삽입성공" : "삽입실패");
		
		// 4. 어디로 and 어떻게 이동
		// 코드 더 줄임 , 게시글작성하면 보통 목록으로 이동한다.
		return new ActionForward(request.getContextPath() + "/getAllBoardList.do", true);
		// 왜 redirect를 이용? -> insert, update, delete(DML작업) 이후에는 redirect를 한다.
		// boardList가 없기 때문에 본문이 텅텅 비게 나온다. 제대로 이동하는 방법은 /getAllBoardList.do 을 요청하면 된다. contextpath 붙여서 매핑 연결해주면 주소 완성!
	}

}
