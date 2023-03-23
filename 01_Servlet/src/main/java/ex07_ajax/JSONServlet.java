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
	
		try {  // 예외 처리를 위해 try-catch
		
			// 요청 인코딩
			request.setCharacterEncoding("UTF-8");
			
			// 요청 파라미터  <- serialize는 name속성을 통해 작동함.
			String name = request.getParameter("name");
			String strAge = request.getParameter("age");
			
			int age = 0;
			if(strAge != null && strAge.isEmpty() == false) {
				age = Integer.parseInt(strAge);
			}
			
			// 이름 예외 처리
			if(name.length() > 6 || name.length() < 2) {
				throw new NameHandleException(name + "은(는) 잘못된 이름입니다.", 601);
			}
			// 나이 예외 처리
			if(age < 0 || age > 100) {
				throw new AgeHandleException(age + "살은 잘못된 나이입니다.", 600); // (메시지, 코드값) 전달
			}
			/* 
				똑같이 런타임익셉션으로 catch로 던지면 어떻게 구분해서 별도로 처리할까..?
				일반적으로 쓰는 방법은 AgeException, NameException 이렇게 예외를 만들어준다.

				※ 상속이 왜 필요한가? 코드의 재활용!! <- AgeException, NameException 코드 완전 복붙이잖아!? + catch블록의 코드도 똑같...
				둘의 부모(슈퍼클래스)를 만들어서 하나에 다 만들어 주면 2개의 예외(복붙코드를 가진)를 각각 다른 클래스로 만들어 줄 필요가 없어진다. => 상속으로 해결
				-> MyHandleException을 만들어 주고 AgeException, NameException 의 슈퍼클래스(Exception)를 MyHandleException으로 바꿔준다. 
				   그리고 생성자(super(message, errorCode);)만 남겨준다. 다 부모로 넘김.
				동일한 부모가 이제 둘 다 처리할 수 있게 되었다. -> JSONServlet에서 catch블록을 하나를 지워 하나만 남기고 catch(MyHandleException e) 로 바꿔준다.
				이렇게 하면 이제 개발자가 만든 예외를 catch블록 하나로 처리할 수 있게 됨.
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
		
		} catch(MyHandleException e) {	// 위에서 한 new AgeHandleException 이 여기로 던져짐. e.getMessage(), e.getErrorCode() 이 2개를 꺼내쓸 수 있다.
										// 마지막에 MyHandleException(두 예외의 슈퍼클래스)으로 바꿔주어 두 예외를 합쳐서 처리할 수 있도록 해줬다.
			response.setContentType("text/plain; charset=UTF-8");
			
			response.setStatus(e.getErrorCode());   // 600이나 601이 꺼내짐
			
			// 응답할 내용이 많지 않으면 이렇게 적어줘도 된다.
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
