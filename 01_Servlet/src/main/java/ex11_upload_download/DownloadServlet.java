package ex11_upload_download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 요청 파라미터
		request.setCharacterEncoding("UTF-8");
		String path = URLDecoder.decode(request.getParameter("path"), "UTF-8");
		
		// 다운로드 해야 할 File 객체
		File file = new File(path);
		
		// 다운로드 해야 할 파일을 읽어들일 입력 스트림
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		
		// 내보내야 하는데 사용자한테 내보내야 한다. => 응답! (다운로드한다)
		
		// mozilla.org 검색 -> http에 약속으로 등록된 값 : Content-Disposition - attachment로써 다운로드..
		// 다운로드용 응답 헤더 작업
		response.setHeader("Content-Disposition", "attachment; filename=" + path.substring(path.lastIndexOf("\\") + 1));
		response.setHeader("Content-Legnth", file.length() + "");    // 파일의 길이는 크기 말하는 것. 오류나는데 Header는 Header 값을 모두 String으로 받기 때문에 String으로 줘야한다.
	
		// 응답 스트림(출력 스트림)
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());   // 응답객체의 스트림은 바이트스트림만 지원했음.
		
		// 파일 복사 (in에서 1024바이트 단위로 읽은 다음 out으로 보내기)  ※ 코드 보고 최대한 이해하려고 해봐라. 외울 필요 없음.
		byte[] b = new byte[1024];    // 입력 단위
		int readByte = 0;             // 실제로 읽은 바이트
		while((readByte = in.read(b)) != -1) {
			out.write(b, 0, readByte);  // 인덱스 0부터 readByte만큼만 읽어들여 write하겠다 
		}
		
		out.close();
		in.close();
		
		
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
