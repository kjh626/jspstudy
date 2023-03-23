package ex07_ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TextServlet")
public class TextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
		
			// 요청 인코딩(고민하지 말고 그냥 해라)
			request.setCharacterEncoding("UTF-8");
			
			// 요청 파라미터
			String model = request.getParameter("model");
			String strPrice = request.getParameter("price");
			
			// price입력창에 100,000 이렇게 쉼표 찍어서 입력하고 버튼누르면, NumberFormatException이 발생.. 예외로 처리해야 한다.
			// try catch해야함.
			int price = 0;
			if(strPrice != null && strPrice.isEmpty() == false) {
				price = Integer.parseInt(strPrice);	    // NumberFormatException 발생 가능(정수가 아닌 경우)
			}
			
			// 마이너스 금액의 예외 처리
			if(price < 0) {  // 자바 7장 ex03_throw
				throw new RuntimeException(price + "원은 입력이 불가능한 금액입니다.");  // 예외를 만들어서 직접 뽑아서 던질 떄 RuntimeException으로 던져라. 자바가 잡지 못하는 예외는 개발자가 직접 잡아서 던져줘야 한다.
				// 개발자들이 예외 만들 때 RuntimeException 만들어서 쓰면 편하다.
			}
			
	
			// 응답 데이터 타입
			response.setContentType("text/plain; charset=UTF-8");
			
			// 출력 스트림 생성  - 여기서 지원하는 게 PrintWriter ... 이거 써야 됨.
			PrintWriter out = response.getWriter();
			
			// 출력
			String resData = "모델은 " + model + "이고, 가격은 " + price + "원입니다.";
			out.println(resData);    // 여기서 출력한 데이터가 success의 resData(일부러 이름 맞춰준것)(매개변수)로 넘어간다.
			out.flush();
			out.close();
			
		
		} catch(NumberFormatException e) {
			
			// 예외 상황에 따른 응답 만들기
			// 응답코드   : 600 (임의로 작성)
			// 응답메시지 : 가격을 확인하세요
			
			// 응답메시지 타입
			response.setContentType("text/plain; charset=UTF-8");   // §예외 메시지 text로 처리
			
			// 응답코드(status)  ⇔  jqXHR.status 과 대응
			response.setStatus(600);  								// §응답코드는 status값으로 부여
			
			// 응답메시지(responseText)  ⇔  jqXHR.responseText 과 대응
			PrintWriter out = response.getWriter();
			out.println("가격을 확인하세요");  						// §이게 getMessage()
			out.flush();
			out.close();

			// 캐치블록에서 만든 응답코드와 응답메시지는 error쪽으로 넘어가서 사용된다.
			
		} catch(RuntimeException e) {
			
			// 예외 상황에 따른 응답 만들기
			// 응답코드   : 601 (임의로 작성)
			// 응답메시지 : 예외 객체 e에 저장된 message 필드값
			
			// 응답메시지 타입
			response.setContentType("text/plain; charset=UTF-8");
			
			// 응답코드(status) 
			response.setStatus(601);
			
			// 응답메시지(responseText)
			PrintWriter out = response.getWriter();
			out.println(e.getMessage());    // getMessage로 메시지 꺼낼 수 있다.
			out.flush();
			out.close();
		}
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
