package ex07_ajax_practice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.XML;

@WebServlet("/XMLServletpractice")
public class XMLServletpractice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 요청 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 요청 파라미터
		String title = request.getParameter("title");
		String professor = request.getParameter("professor");
		String strNumber = request.getParameter("number");
		int number = 0;
		if(strNumber != null && strNumber.isEmpty() == false) {
			number = Integer.parseInt(strNumber);
		}
	
		JSONObject obj = new JSONObject();
		obj.put("title", title);
		obj.put("professor", professor);
		obj.put("number", number);
		JSONObject obj2 = new JSONObject();
		obj2.put("lecture", obj);
		
		String resData = XML.toString(obj2);
		
		response.setContentType("application/xml; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println(resData);
		out.flush();
		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
