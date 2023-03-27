package ex11_upload_download;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FileListServlet")
public class FileListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 보내는 쪽에서 인코드 했으니까 받는 쪽에서는 디코드!
		
		// 요청 파라미터
		String parent = request.getParameter("parent");
		parent = URLDecoder.decode(parent, "UTF-8");
		
		// parent 경로에 저장된 파일 목록
		File dir = new File(parent);
		File[] files = dir.listFiles();    // listFiles 할 때 필터도 넣어줄 수 있다.
		
		// 응답
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		for(File file : files) {
			out.println("<div>" + file.getName() +  " <a href=\"/01_Servlet/DownloadServlet?path=" + URLEncoder.encode(file.getPath(), "UTF-8") + "\">다운로드</a></div>");    // 이름도 전달해주고 경로도 전달해줘야 한다. => getPath()를 이용하면 이름+경로 한 번에 넘길 수 있다. 인코딩도 해줘야 한다.
			// for문 돌릴 때 id속성 함부로 주면 안 된다. id속성이 똑같으면 안 되지.. (주의!)
			// 버튼인데 <a>링크로 바꿔주고, <a>링크를 css로 버튼모양으로 맞춰줄 수도 있다.
			// 부트스트랩이라는 프레임워크 : css 대신 만들어 준다.

			// 여기서는 무엇을 다운로드할 지 정보 넘기기 가 중요 
		}
		out.flush();
		out.close();
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
