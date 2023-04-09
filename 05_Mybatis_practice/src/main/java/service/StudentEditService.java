package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.StudentDTO;
import repository.StudentDAO;

public class StudentEditService implements IStudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		// 똑같이 select를 한번 더 해준다 . 없으면 편집하러 못 간다
		int stuNo = Integer.parseInt(request.getParameter("stuNo").isEmpty() ? "0" : request.getParameter("stuNo"));
		
		StudentDTO student = StudentDAO.getInstance().selectStudentByNo(stuNo);
		
		student.setName(request.getParameter("name"));
		student.setKor(Integer.parseInt(request.getParameter("kor")));
		student.setEng(Integer.parseInt(request.getParameter("eng")));
		student.setMath(Integer.parseInt(request.getParameter("math")));
		
		int updateResult = StudentDAO.getInstance().updateStudent(student);
		
		
		if(student == null) {
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('존재하지 않는 학생입니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.flush();
				out.close();
				return null;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(updateResult == 1) {
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('BBS가 수정되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/detail.do?stuNo=" + stuNo + "'");
				out.println("</script>");
				out.flush();
				out.close();
				return null;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("student", student);
		
		// 상세보기와 경로만 다르다
		return new ActionForward(request.getContextPath() + "/list.do", true);
	}

}
