package ex07_ajax;

import java.io.IOException;
import java.io.PrintWriter;

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
	
		try {  // 예외 처리를 위해
		
			// 요청 인코딩
			request.setCharacterEncoding("UTF-8");
			
			// 요청 파라미터  <- serialize는 name속성을 통해 작동함.
			String name = request.getParameter("name");
			String strAge = request.getParameter("age");
			
			int age = 0;
			if(strAge != null && strAge.isEmpty() == false) {
				age = Integer.parseInt(strAge);
			}
			
			// 나이 예외 처리
			if(age < 0 || age > 100) {
				throw new AgeHandleException(age + "살은 잘못된 나이입니다.", 600); // (메시지, 코드값) 전달
			}
			// 이름 예외 처리
			if(name.length() > 6 || name.length() < 2) {
				throw new RuntimeException(name + "은 잘못된 이름입니다.");
			}
			/* 
				똑같이 런타임익셉션으로 catch로 던지면 어떻게 구분해서 별도로 처리할까..?
				일반적으로 쓰는 방법은 AgeException, NameException 이렇게 예외를 만들어준다.
				
			*/
			
			// 응답을 제이슨요청으로 할 거니까 응답에서는 이전과 좀 달라진다.
			
			// 응답할 JSON 데이터  <- 라이브러리로 제공하기 때문에 라이브러리(jar) 추가해줘야한다.(위에 적어놓음)
			JSONObject obj = new JSONObject();
			obj.put("name", name);
			obj.put("age", age);
			
			// System.out.println(obj.toString());   // {"name": 마돈나,"age": 50}
			
			// 응답 데이터 타입
			response.setContentType("application/json; charset=UTF-8");    // ★★암기★★
			
			// 출력 스트림 생성  ※ 출력=응답
			PrintWriter out = response.getWriter();
			
			// 출력
			String resData = obj.toString();
			out.println(resData);    // 텍스트 형식(문자열 형식)으로 된 JSON 데이터를 응답한다.
			// out.println(obj);   이렇게 주면은(그냥 객체로 주면) 여기 라이브러리에서만 가능하다. 그래서 텍스트형식으로 바꿔서 응답하는 것이 정석이고 좋다. 
			out.flush();
			out.close();
		
		} catch(AgeHandleException e) {	// 위에서 한 new AgeHandleException 이 여기로 던져짐. e.getMessage(), e.getErrorCode() 이 2개를 꺼내쓸 수 있다.
			
			response.setContentType("text/plain; charset=UTF-8");
			
			response.setStatus(e.getErrorCode());   // 600이 꺼내짐
			
			// 응답할 내용이 많지 않으면
			response.getWriter().println(e.getMessage());
			/*  원래는 응답메시지 출력할 때 이렇게 적어줬음.
				PrintWriter out = response.getWriter();
				out.println(e.getMessage());
				out.flush();
				out.close();
			*/
			// catch블록에서 작성한 응답은 error로 넘어간다.
			
		}
		
		
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
