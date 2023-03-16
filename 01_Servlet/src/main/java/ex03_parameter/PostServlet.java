package ex03_parameter;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String model = request.getParameter("model");
		String strPrice = request.getParameter("price");
		
		// <form> 태그에 포함된 입력 요소들이 name 속성을 가지고 있다면, null 처리를 할 수 없다. 빈 문자열로("")로 처리해야 한다.-> 자바에서 빈문자열 비교할 때 전용 메소드: isEmpty()
		// ※ null : 데이터가 없다.......
		int price = 0;
		// 고전적으로 처리할 때는 ir(str == null || str.isEmpty()) 이렇게 null과 isEmpty를 둘다 넣어준다. 이렇게 하면 깔끔.
		if(strPrice.isEmpty() == false){   // 빈 문자열 점검 ( 지금 코드는 빈 문자열이 아니면 if문 실행 )
			price = Integer.parseInt(strPrice);
		}
		
		
		response.getWriter().append("model: " + model).append(", price: " + price);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST 요청이 들어옴");
		doGet(request, response);
	}

}
