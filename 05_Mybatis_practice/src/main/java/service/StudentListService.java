package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.StudentDTO;
import repository.StudentDAO;

public class StudentListService implements IStudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// 요청 받으면 목록을 가져와서 bbs/list.jsp로 forward 해줘야 한다.
		
		List<StudentDTO> studentlist =  StudentDAO.getInstance().selectAllStudentList();
		
		request.setAttribute("studentlist", studentlist);
		request.setAttribute("studentlistCount", studentlist.size());
		return new ActionForward("student/list.jsp", false);
		// 전달 완료
	}

}
