package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.BbsDAO;

public class BbsListService implements IBbsService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// 요청 받으면 목록을 가져와서 bbs/list.jsp로 forward 해줘야 한다.
		
		request.setAttribute("bbslist", BbsDAO.getInstance().selectAllBbsList());
		return new ActionForward("bbs/list.jsp", false);
		// 전달 완료
	}

}
