package ex07_ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/*
	Dynamic Web Project에서 외부 라이브러리(*.jar)를 사용하는 방법 ( 두 가지 방법 중 하나만 하면 된다. 알아서 확인한다. )
	
	방법1. CATALINA_HOME\lib 디렉터리에 사용할 라이브러리를 추가한다.
	방법2. 컨텍스트(프로젝트)에 사용할 라이브러리를 추가한다. (수업에서 사용할 방법)
	  src/main/webapp/WEB-INF/lib 디렉터리에 외부 라이브러리 추가 (파일 드래그해서 이클립스의 WEB-INF\lib에 넣어주기)
*/

@WebServlet("/JSONServlet")
public class JSONServlet extends HttpServlet {
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
		
		// 응답을 제이슨요청으로 할 거니까 응답에서는 이전과 좀 달라진다.
		
		// 응답할 JSON 데이터
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("age", age);
		
		System.out.println(obj.toString());   // {"name": ,"age": }
		
		
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
