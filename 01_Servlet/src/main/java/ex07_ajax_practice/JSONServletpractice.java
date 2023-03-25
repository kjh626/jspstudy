package ex07_ajax_practice;

import java.io.IOException;
import java.io.PrintWriter;

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

		try {
			
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
			if(name.length() < 2 || name.length() > 6) {
				throw new NameHandleException(name + "은(는) 잘못된 이름.", 601);
			}
			// 나이 예외 처리
			if(age < 0 || age > 100) {
				throw new AgeHandleException(age + "살은 잘못된 나이.", 600);
			}
			
			// 응답할 JSON 데이터
			JSONObject obj = new JSONObject();
			obj.put("name", name);
			obj.put("age", age);
			
			// 응답 데이터 타입
			response.setContentType("application/json; charset=UTF-8");
			
			// 출력 스트림 생성
			PrintWriter out = response.getWriter();
			
			// 출력
			String resData = obj.toString();
			out.println(resData);
			out.flush();
			out.close();
		} catch(MyHandleException e) {
			
			response.setContentType("text/plain; charset=UTF-8");
			response.setStatus(e.getErrorCode());
			response.getWriter().println(e.getMessage());
			
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
