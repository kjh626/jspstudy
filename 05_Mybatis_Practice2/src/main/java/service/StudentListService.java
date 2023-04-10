package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.StudentDAO;

public class StudentListService implements IStudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		StudentDAO dao = StudentDAO.getInstance();
		// db로부터 select해서 가져온다.
		request.setAttribute("students", dao.selectAllStudentList());
		request.setAttribute("count", dao.getAllStudentCount());
		request.setAttribute("average", dao.getAllStudentAverage());
		request.setAttribute("top3", dao.selectTop3());
		
		return new ActionForward("student/list.jsp", false);
		
	}

}
