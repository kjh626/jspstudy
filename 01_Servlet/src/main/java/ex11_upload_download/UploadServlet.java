package ex11_upload_download;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		/*
			파일 업로드를 위한 작업
			1. http://servlets.com에 방문
			2. COS File Upload Library 메뉴에 있는 cos-22.05.zip 파일을 다운로드 받는다.
			3. 압축 해제 후 lib 디렉터리에 있는 cos.jar 파일을 프로젝트의 lib로 등록한다. (파일 드래그해서 이클립스에)
				※ 라이브러리가 추가된 거면 새로운 클래스가 추가되는 것이다. cos는 어떤 클래스를 추가해줬을까?
				  MultipartRequest를 추가해줬음(지원해준다).(HttpRequest는 파일첨부를 못한다.)
		*/
		
		/*
			cos.jar
			1. 파일 업로드가 필요한 경우에 사용하는 라이브러리이다.
			2. HttpServletRequest 클래스는 파일 업로드를 처리할 수 없다.
			3. MultipartRequest 클래스를 이용해서 파일 업로드를 처리할 수 있는데, cos.jar가 MultipartRequest 클래스를 지원한다.
		*/
		// 1. 경로 , 2. 이름 을 설정해줘야 한다.  (IO 배운 거)
		// RealPath를 써볼 것이다. storage 폴더를 만들어 줄 예정  => 지금은 local에만 만들어 줌. 나중에는 배포할 때의 경로를 설정해줘야 함.
		
		// 업로드 경로 (서버의 real path를 사용하거나 일반 경로를 사용할 수 있다.)
		String realPath = request.getServletContext().getRealPath("storage");
		File dir = new File(realPath);
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		
		// 파일의 이름 굳이 만들지 않고, COS에 맡길 거다. (문제 생기는 경우: 똑같은 파일 2개 올릴 때. 이때 중복 처리를 어떻게 해줄 것인가..?)
		// 파일이름 설정할 때 원래는 => 파일이름 깨지는 인코딩문제, 중복파일 등 고려해야 한다
		
		// 업로드 진행하기 (HttpServletRequest request를 이용해서 MultipartRequest 객체를 생성하면 업로드가 곧바로 진행된다.) 그냥 new MultipartRequest해주면 업로드 됨
		// new MultipartRequest 생성자()
		MultipartRequest multipartRequest = new MultipartRequest(
				request,         				 // HttpServletRequest 를 전달해준다.
				realPath,        				 // 업로드 경로 전달(String을 전달). dir이 아니라..
				1024 * 1024 * 10,				 // 최대 크기
				"UTF-8",        				 // 인코딩
				new DefaultFileRenamePolicy()    // 파일명 중복 처리 정책 (파일명 뒤에 넘버링)
		);
		
		// 요청 파라미터 (조심할 것 : MultipartRequest multipartRequest를 이용해서 파라미터를 가져와야 한다.)
		String uploader = multipartRequest.getParameter("uploader");
		String originName = multipartRequest.getOriginalFileName("filename");    // 원래 첨부 파일명    --> DB에 들어갈 칼럼이 된다.
		String filesystemName = multipartRequest.getFilesystemName("filename");  // 저장된 첨부 파일명  --> DB에 들어갈 칼럼이 된다. (DB 쓰면)
		
		// 첨부 파일 정보 (<= 파일 객체를 통해 제공된다. (파일의 정보확인:file.getName(), getParent(), getPath(), lastModified(), length() 등등)
		// 파일명, 경로, 최종수정일(yyyy-MM-dd), 파일크기(KB : 1KB가 안 넘으면 1KB로 표기)
		File file = multipartRequest.getFile("filename");    // 파일 객체를 얻어내는 방법
		String name = file.getName();
		String parent = file.getParent();
		String lastModified= new SimpleDateFormat("yyyy-MM-dd").format(file.lastModified());
		String size = new DecimalFormat("#,##0").format(file.length() / 1024 + (file.length() % 1024 == 0 ? 0 : 1));
		
		// 응답
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<ul style=\"list-style-type: circle; font-size: 24px;\">");    // ul태그에 css 직접 지정
		out.println("<li>작성자 : " + uploader + "</li>");
		out.println("<li>원래 첨부 파일명 : " + originName + "</li>");
		out.println("<li>저장된 첨부 파일명 : " + filesystemName + "</li>");
		out.println("<li>파일명 : " + name + "</li>");
		out.println("<li>경로 : " + parent + "</li>");
		out.println("<li>최종수정일 : " + lastModified + "</li>");
		out.println("<li>파일크기 : " + size + "KB</li>");
		out.println("<li><a href=\"/01_Servlet/FileListServlet\">첨부된 파일목록 보기</a></li>");
		out.println("</ul>");
		out.flush();
		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
