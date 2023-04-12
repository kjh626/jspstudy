package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.BbsDTO;
import repository.BbsDAO;

public class BbsModifyService implements IBbsService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		BbsDTO bbs = new BbsDTO();
		// input으로 값을 넘겨줄 때는 값이 입력이 안되어 있을 땐 공백으로 넘겨진다. (null이 아니라)
		/* <input name=""> input으로 넘길 때만 공백으로 처리해주면 된다.
			나머지는 다 옵셔널로 null 체크 가능*/
		// 널 체크가 아닌 공백 체크이기 때문에 optional 안 씀 
		int bbsNo = Integer.parseInt(request.getParameter("bbsNo").isEmpty() ? "0" : request.getParameter("bbsNo"));
		bbs.setBbsNo(bbsNo);
		bbs.setTitle(request.getParameter("title"));
		bbs.setContent(request.getParameter("content"));
		
		int updateResult = BbsDAO.getInstance().updateBbs(bbs);
		
		if(updateResult == 1) {
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('BBS가 수정되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/detail.do?bbsNo=" + bbsNo + "'");
				out.println("</script>");
				out.flush();
				out.close();
				return null;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return new ActionForward(request.getContextPath() + "/list.do", true);
	}

}
