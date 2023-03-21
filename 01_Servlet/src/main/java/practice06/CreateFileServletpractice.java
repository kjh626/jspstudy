package practice06;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateFileServletpractice")
public class CreateFileServletpractice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		String filename = LocalDate.now().toString() + "-" + writer + "-" + title + ".txt";
		File dir = new File(request.getServletContext().getRealPath("storage"));
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		
		File file = new File(dir, filename);
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write(content);
		bw.flush();
		bw.close();
		
		response.sendRedirect("/01_Servlet/FileResponseServletpractice?filename=" + URLEncoder.encode(filename, "UTF-8"));
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
