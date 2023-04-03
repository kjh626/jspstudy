package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.BoardDAO;

public class BoardAddService implements IBoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// BoardDAO의 getInstance() 호출하기
		/*
			메소드 호출 2가지 방법
			1. 객체로 호출하기  --> DAO는 이 방법을 막았기 때문에 못한다.(생성자 못부르게)
				객체.메소드()
			2. 클래스로 호출하기
				클래스.메소드()  => ★static 붙여놔야 클래스로 부를 수 있다.
		*/
		BoardDAO dao = BoardDAO.getInstance();
		// 이러면 BoardDAO 타입의 다오가 만들어진다. 동시성 문제 발생X, 다른 서비스에 만든 dao는 이 dao와 같은 dao다
		// 모든 서비스에 이 코드 넣어줘야 한다.
		
		// TODO Auto-generated method stub
		return null;
	}

}
