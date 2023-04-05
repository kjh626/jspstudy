package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.GesipanDTO;
import repository.GesipanDAO;

public class GesipanDetailService implements IGesipanService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		Optional<String> opt = Optional.ofNullable(request.getParameter("gesipan_no"));
		int gesipan_no = Integer.parseInt(opt.orElse("0"));
		
		GesipanDTO gesipan = GesipanDAO.getinstance().selectGesipanByNo(gesipan_no);
	
		try {
			
			if(gesipan == null){
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('존재하지 않는 게시글입니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.flush();
				out.close();
				return null;  
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("gesipan", gesipan);
		
		return new ActionForward("gesipan/detail.jsp", false);
	}

}
