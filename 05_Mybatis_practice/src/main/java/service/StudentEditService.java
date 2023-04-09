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
		
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
		double ave = (kor + eng + math) / 3.00;
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
				out.println("alert('학생 정보가 수정되었습니다.')");
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
