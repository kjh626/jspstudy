package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.BbsDTO;
import repository.BbsDAO;

public class BbsEditService implements IBbsService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		Optional<String> opt = Optional.ofNullable(request.getParameter("bbsNo"));
		int bbsNo = Integer.parseInt(opt.orElse("0"));
		
		// 똑같이 select를 한번 더 해준다 . 없으면 편집하러 못 간다
		BbsDTO bbs = BbsDAO.getInstance().selectBbsByNo(bbsNo);
		
		if(bbs == null) {
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('존재하지 않는 BBS입니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.flush();
				out.close();
				return null;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("bbs", bbs);
		
		// 상세보기와 경로만 다르다
		return new ActionForward("bbs/edit.jsp", false);
	}

}
