package service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.BoardDAO;

public class BoardRemoveService implements IBoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		// 1. 요청 파라미터
		// 파라미터 전달 안 해줄 경우를 대비해서 Optional
		Optional<String> opt = Optional.ofNullable(request.getParameter("board_no"));
		int board_no = Integer.parseInt(opt.orElse("0"));  // null일 경우 delete가 동작하지 않게끔 0값을 준다.
		
		// 2. BoardDAO의 deleteBoard 메소드 호출
		int deleteResult = BoardDAO.getInstance().deleteBoard(board_no);
		System.out.println(deleteResult == 1 ? "삭제성공" : "삭제실패");

		// 3. 어디로 and 어떻게 이동
		return new ActionForward(request.getContextPath() + "/getAllBoardList.do",true);
	}

}
