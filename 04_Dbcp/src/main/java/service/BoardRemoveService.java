package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.BoardDAO;

public class BoardRemoveService implements IBoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		// 주소창에 삭제 주소를 입력해서 삭제를 시도하는 경우 잘못된 요청으로 처리한다. -> 버튼을 눌러서 삭제할 수밖에 없다.(버튼은 아무나 누를 수 있지않냐? 나중에 버튼(삭제'x'버튼)은 작성자만 볼 수 있게 만들 수 있다.)
		try {
			
			if(request.getMethod().equalsIgnoreCase("get")){
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 요청입니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.flush();
				out.close();
				return null;  // 컨트롤러로 null값을 반환하면 컨트롤러는 이동(redirect 또는 forward)을 하지 않는다.
				              // 서비스에서 직접 이동하는 경우에 이 방법을 사용한다.
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		// response로 작성했기 때문에 이미 응답이 끝난 것이다. 그래서 응답이 끝나는 경우에 이동하는 코드를 직접 작성해줘야 한다.(location.href, history.back() 같은 걸로)
		/*
			왜 null을 반환했는가? 액션포워드 객체가 null값으로 넘어갔다.
			서비스 실행결과(execute가 null을 반환)로 af로 null값이 넘어감
			그러면 if가 동작하지 않는다. 동작하지 않으면 아무것도 안 한다.=> 이동하지 않는다.(내가 직접 응답을 만들어서 이동시켰음(history.back()) )
			컨트롤러로 null값을 반환해서 이동이 없게 해 줬다.
		  	  => 컨트롤러로 null값을 반환하면 컨트롤러는 이동(redirect 또는 forward)를 하지 않는다. 
		    	 서비스에서 직접 이동하는 경우에 이 방법을 사용한다.
		*/
		
		
		// 1. 요청 파라미터
		// ※ form을 만들고 input요소를 만들었으면 절대 null이 나올 수가 없다. name이 있기 때문에 무조건 있다(무조건 가는 거다). => form요소는 null로 처리하면 안 된다.
		String strBoard_no =  request.getParameter("board_no");
		int board_no = Integer.parseInt(strBoard_no.isEmpty() ? "0" : strBoard_no);  // 빈 문자열일 경우 delete가 동작하지 않게끔 0값을 준다.
		
		// 2. BoardDAO의 deleteBoard 메소드 호출
		int deleteResult = BoardDAO.getInstance().deleteBoard(board_no);
		System.out.println(deleteResult == 1 ? "삭제성공" : "삭제실패");

		// 3. 어디로 and 어떻게 이동
		return new ActionForward(request.getContextPath() + "/getAllBoardList.do",true);
	}

}
