package ex07_ajax_practice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/JSONServletpractice")
public class JSONServletpractice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 요청 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 요청 파라미터
		String name = request.getParameter("name");
		String strAge = request.getParameter("age");
		int age = 0;
		if(strAge != null && strAge.isEmpty() == false) {
			age = Integer.parseInt(strAge);
		}
		
		// 이름 예외 처리
		// 나이 예외 처리
		
		// 응답할 JSON 데이터
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("age", age);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
