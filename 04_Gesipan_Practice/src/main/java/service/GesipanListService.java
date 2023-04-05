package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.GesipanDTO;
import repository.GesipanDAO;

public class GesipanListService implements IGesipanService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		GesipanDAO dao = GesipanDAO.getinstance();
		
		List<GesipanDTO> gesipanList = dao.selectGesipanList();
		
		request.setAttribute("gesipanList", gesipanList);
		request.setAttribute("gesipanListCount", gesipanList.size());
		
		ActionForward af = new ActionForward("gesipan/list.jsp", false);
		
		return af;
	}

}
