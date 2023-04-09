package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.StudentDTO;
import repository.StudentDAO;

public class StudentAddService implements IStudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		StudentDTO student = new StudentDTO();
		student.setName(request.getParameter("name"));
		student.setKor(Integer.parseInt(request.getParameter("kor")));
		student.setEng(Integer.parseInt(request.getParameter("eng")));
		student.setMath(Integer.parseInt(request.getParameter("math")));
		
		int insertResult = StudentDAO.getInstance().insertStudent(student);
		
		try {
			PrintWriter out = response.getWriter();
			if(insertResult == 1) {
				out.println("<script>");
				out.println("alert('학생이 등록되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/list.do'");
				out.println("</script>");
				out.flush();
				out.close();  
				return null;  
			} 
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// BBS 등록 실패
		return new ActionForward(request.getContextPath() + "/list.do", true);
	}

}
