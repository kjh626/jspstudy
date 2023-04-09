package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.StudentDTO;
import repository.StudentDAO;

public class StudentDetailService implements IStudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		Optional<String> opt = Optional.ofNullable(request.getParameter("stuNo"));
		int stuNo = Integer.parseInt(opt.orElse("0"));
		// 주소창에 파라미터(?bbsNo= ) 없으면 0이 나온다.
		
		StudentDTO student = StudentDAO.getInstance().selectStudentByNo(stuNo);
		
		if(student == null) {
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('존재하지 않는 BBS입니다')");
				out.println("history.back()");
				out.println("</script>");
				out.flush();
				out.close();
				return null;    // 이미 응답이 끝남(이동했음) 컨트롤러로 이용한 이동 방지를 위해 return null 처리
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		request.setAttribute("student", student);
		return new ActionForward("student/detail.jsp", false);
	}

}
