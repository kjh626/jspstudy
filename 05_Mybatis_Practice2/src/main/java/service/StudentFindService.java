package service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.StudentDAO;

public class StudentFindService implements IStudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 파라미터와 매퍼의 관계를 주목해라!
		double begin = Double.parseDouble(request.getParameter("begin"));
		double end = Double.parseDouble(request.getParameter("end"));
		
		// 파라미터 둘을 가지고 맵을 만들었다. begin과 end를 하나로 합쳐주기 위해서
		// 맵의 키값이 begin과 end
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("begin", begin);
		map.put("end", end);
		
		// dao의 세 개의 매소드에 맵을 전달하고 있다.
		StudentDAO dao = StudentDAO.getInstance();
		request.setAttribute("students", dao.findStudentList(map));
		request.setAttribute("count", dao.getFindStudentCount(map));
		request.setAttribute("average", dao.getFindStudentAverage(map));
		// 목록과 같은 이름으로 맞춰놓는다. ("student", "count", "average" 같이)
		// 조회 기능에서는 top 3가 빠졌다.
		
		return new ActionForward("student/list.jsp", false);
		
	}

}
