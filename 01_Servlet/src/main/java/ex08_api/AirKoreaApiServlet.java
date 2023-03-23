package ex08_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AirKoreaApiServlet")
public class AirKoreaApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 요청 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 요청 파라미터
		String sidoName = request.getParameter("sidoName");
		String returnType = request.getParameter("returnType");
		
		// 서비스키 (마이페이지 디코딩된 주소 복사)
		String serviceKey = "/crf+XWfbPub+yJFATeJmDpsQiWP4ffIHn3Fa2CFRpEwxkb+iSoDidUPYuNuoiVX7msyy1xzyz/yQ0dUbGZx6w==";
		
		// 사이트의 요청 주소 복사
		String apiURL = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		apiURL += "?serviceKey=" + URLEncoder.encode(serviceKey, "UTF-8");  // 예외처리 안 해도 되는 이유는 doGet에서 IOException 예외처리 해주고 있음.
		apiURL += "&sidoName=" + URLEncoder.encode(sidoName, "UTF-8");  // 시도네임 한글로 되어있음. 인코딩 필요!
		apiURL += "&returnType=" + URLEncoder.encode(returnType, "UTF-8");  // returnType은 영어로 되어있음. 해도되고 안 해도 된다. => 일반적으로 통일하는 편
	
		// URL
		URL url = new URL(apiURL);
		
		// HttpURLConnection
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		// 요청 메소드 ※ 주의할 점 대문자로 전달해야 한다.
		con.setRequestMethod("GET");
	
		// returnType에 따른 Content-Type 여기서 returnType은 문서에 소문자로 적어달라고 함. 그래서 밸류값에 소문자로 줌
		con.setRequestProperty("Content-Type", "application/" + returnType + "; charset=UTF-8");
		/* 여기까지가 요청할 때 필요한 정보 */
		
		
		// 입력 스트림 생성 <- api가 전달해주는 것을 읽어야 한다.
		BufferedReader reader = null;
		if(con.getResponseCode() == 200) {
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));  // 바이트스트림을 텍스트로 읽어주는 inputStreamreader로..
		} else {
			reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		
		// 입력 (API의 응답 결과를 StringBuilder에 저장)
		StringBuilder sb = new StringBuilder();
		String line = null;
		while((line = reader.readLine()) != null) {    // 읽을 라인이 남아있다면
			sb.append(line);
		}
		
		// 입력 스트림, 접속 종료
		reader.close();
		con.disconnect();
		
		System.out.println(sb.toString());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
