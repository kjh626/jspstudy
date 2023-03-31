package model;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public class MyAgeService implements MyService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		String strBirthyear = request.getParameter("birthyear");
		int birthyear = Integer.parseInt(strBirthyear);
		int nowyear = Calendar.getInstance().get(Calendar.YEAR);   // get(1) 이렇게 적은 거랑 똑같다. 1이라고 써줘야 연도가 나온다. 그래서 사람들이 생각하기 쉽게 필드값으로 만들어 놓은 것이다. 
		
		request.setAttribute("age", nowyear - birthyear);
		
		// 어디로 어떻게 갈 것인가?
		ActionForward actionForward = new ActionForward();
		actionForward.setPath("view/output.jsp");
		actionForward.setRedirect(false);    // forward
		return actionForward; 
		
	}

}
