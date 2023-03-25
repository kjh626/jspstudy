package practice07;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PapagoServletpractice")
public class PapagoServletpractice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 요청 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 요청 파라미터
		String source = request.getParameter("source");    // 원본 언어 코드(ko,en,ja 중 하나)
		String target = request.getParameter("target");    // 목적 언어 코드(ko,en,ja 중 하나)
		String text = request.getParameter("text");        // 번역할 텍스트  -> UTF-8로 인코딩 해줘야 한다.
		
		// 클라이언트 아이디, 시크릿 (네이버개발자센터에서 발급 받은 본인 정보를 사용합니다.)
		String clientId = "SNxZ6DGf354PLLcGPlnu";
		String clientSecret = "Lp2f019JKT";
		
		// API 주소 (- 파파고-API레퍼런스-요청 URL)
		String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
		
		// URL
		URL url = new URL(apiURL);
		
		// HttpURLConnection
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		// 요청 메소드 (- API레퍼런스에 나와있음)
		con.setRequestMethod("POST");
		
		// 요청 헤더에 포함하는 내용 (- API레퍼런스-참고사항에 Content-Type, clientId, clientSecret을 추가해야한다고 나와있다.)
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		con.setRequestProperty("X-Naver-Client-Id", clientId);
		con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		
		// client 입장에서는 papago로 보내줘야하니까(con으로 client와 papago 연결) 출력스트림을 써야 한다.   client → papago
		// 결과를 받아올 때는 입력스트림을 써야한다.
		// con으로 연결하는 스트림은 byte기반의 스트림이다. 그래서 한글 보낼 때 100% 깨진다.
		/* string 형태로 보내려면? 
			1. OutputStreamWriter - 얘로 해서 변환해서 보내면 된다.
			2. DataOutputStream   - 얘가 writeUTF 메소드를 지원함. 변환과정을 거칠 필요가 없음
		*/
		
		// Papago API로 보내야 하는 파라미터(source, target, text)
		String params = "source=" + source + "&target=" + target + "&text=" + URLEncoder.encode(text, "UTF-8");

		// Papago API로 파라미터를 보내기 위해서 출력 스트림 생성
		con.setDoOutput(true);   // dos 만들기 전으로 순서를 옮겨줬음.
		DataOutputStream dos = new DataOutputStream(con.getOutputStream());  // con으로부터 얻어올 것은 byte기반 스트림밖에 없다.(한글 깨질 우려있음)
		
		// Papago API로 파라미터 보내기
		dos.write(params.getBytes());    // writeUTF 쓰지 않고 write를 쓰는데 write메소드는 바이트배열을 보낼 수 있다.(getBytes()를 써서 바이트배열로 바꿔준 다음에 보내줌) ※ javastudy 9장 공부해라
		dos.flush();
		dos.close();
		
		// Papago API로부터 번역 결과를 받아올 입력 스트림 생성
		BufferedReader reader = null;
		if(con.getResponseCode() == 200) {
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else {
			reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		
		// Papago API로부터 번역 결과를 받아서 StringBuilder에 저장
		StringBuilder sb = new StringBuilder();
		String line = null;
		while((line = reader.readLine()) != null) {
			sb.append(line);
		}
		
		// StringBuilder의 번역 결과를 client.html의 ajax으로 보내기
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(sb.toString());
		out.flush();
		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
