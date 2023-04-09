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
		
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
		double ave = (kor + eng + math) / 3.0;
		String grade = "";
		if(ave >= 90) {
			grade = "A";
		} else if (ave >= 80) {
			grade = "B";
		} else if (ave >= 70) {
			grade = "C";
		} else if (ave >= 60) {
			grade = "D";
		} else {
			grade = "F";
		}
		
		
		student.setName(request.getParameter("name"));
		student.setKor(kor);
		student.setEng(eng);
		student.setMath(math);
		student.setAve(ave);
		student.setGrade(grade);
		
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
