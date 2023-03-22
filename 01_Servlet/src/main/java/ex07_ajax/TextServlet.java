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

		// 요청 인코딩(고민하지 말고 그냥 해라)
		request.setCharacterEncoding("UTF-8");
		
		// 요청 파라미터
		String model = request.getParameter("model");
		String strPrice = request.getParameter("price");
		int price = 0;
		if(strPrice != null && strPrice.isEmpty() == false) {
			price = Integer.parseInt(strPrice);
		}

		// 응답 데이터 타입
		response.setContentType("text/plain; charset=UTF-8");
		
		// 출력 스트림 생성  - 여기서 지원하는 게 PrintWriter ... 이거 써야 됨.
		PrintWriter out = response.getWriter();
		
		// 출력
		String resData = "모델은 " + model + "이고, 가격은 " + price + "원입니다.";
		out.println(resData);
		out.flush();
		out.close();
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
