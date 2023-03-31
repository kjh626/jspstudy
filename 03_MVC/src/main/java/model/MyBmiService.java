package model;


import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public class MyBmiService implements MyService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		// 파라미터가 ~~&~~ 줄줄이 오면 null 처리할 수 있다.
		// height가 올텐데 null일 수도 있어 만약 null이면 0으로 해줘
		Optional<String> opt1 = Optional.ofNullable(request.getParameter("height"));
		double height = Double.parseDouble(opt1.orElse("0"));
		Optional<String> opt2 = Optional.ofNullable(request.getParameter("weight"));
		double weight = Double.parseDouble(opt2.orElse("1"));
		// 0 나누기 0하면 안 되니까. 몸무게는 1로 설정
		
		double bmi = weight / (height * height * 0.0001);
		String health = "";
		if(bmi < 20) {
			health = "저체중";
		} else if(bmi < 25) {
			health = "정상";
		} else {
			health = "비만";
		}
		
		request.setAttribute("bmi", bmi);
		request.setAttribute("health", health);
		// 어디로 어떻게 갈 것인가?
		ActionForward actionForward = new ActionForward();
		actionForward.setPath("view/output.jsp");
		actionForward.setRedirect(false);    // forward
		return actionForward; 
		
	}

}
