package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.StudentDTO;
import repository.StudentDAO;

public class StudentModifyService implements IStudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		StudentDTO bbs = new StudentDTO();
		// 널 체크가 아닌 공백 체크이기 때문에 optional 안 씀 
		int bbsNo = Integer.parseInt(request.getParameter("bbsNo").isEmpty() ? "0" : request.getParameter("bbsNo"));
		bbs.setBbsNo(bbsNo);
		bbs.setTitle(request.getParameter("title"));
		bbs.setContent(request.getParameter("content"));
		
		int updateResult = StudentDAO.getInstance().updateBbs(bbs);
		
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
