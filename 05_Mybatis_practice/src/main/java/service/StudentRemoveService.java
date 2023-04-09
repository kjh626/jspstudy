package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.StudentDAO;

public class StudentRemoveService implements IStudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		if(request.getMethod().equalsIgnoreCase("get")) {
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 요청입니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.flush();
				out.close();
				return null;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		String strStuNo = request.getParameter("stuNo");
		int stuNo = Integer.parseInt(strStuNo.isEmpty() ? "0" : strStuNo);
		
		int deleteResult = StudentDAO.getInstance().deleteStudent(stuNo);
		
		if(deleteResult == 1) {
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('학생이 삭제되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/list.do'");
				out.println("</script>");
				out.flush();
				out.close();
				return null;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		// redirect로 이동 . (삽입,수정,삭제)
		return new ActionForward(request.getContextPath() + "/list.do", true);
	}

}
