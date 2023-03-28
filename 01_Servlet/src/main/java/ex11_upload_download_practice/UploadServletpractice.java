package ex11_upload_download_practice;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/UploadServletpractice")
public class UploadServletpractice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 업로드 경로
		String realPath = request.getServletContext().getRealPath("storage");
		File dir = new File(realPath);
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		
		// 업로드 진행하기 
		MultipartRequest multipartRequest = new MultipartRequest(
				request,
				realPath,
				1024 * 1024 * 10,
				"UTF-8",
				new DefaultFileRenamePolicy()
				);
		
		// 요청 파라미터
		String uploader = multipartRequest.getParameter("uploader");
		String originName = multipartRequest.getOriginalFileName("filename");
		String filesystemName = multipartRequest.getFilesystemName("filename");
		
		// 첨부 파일 정보
		// 파일명, 경로, 최종수정일(yyyy-MM-dd), 파일크기(KB)
		File file = multipartRequest.getFile("filename");
		String name = file.getName();
		String parent = file.getParent();
		String lastModified = new SimpleDateFormat("yyyy-MM-dd").format(file.lastModified());
		String size = new DecimalFormat("#,##0").format(file.length() / 1024 + (file.length() % 1024 == 0 ? 0 : 1));
		
		// 응답
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<ul style=\"list-style-type: circle; font-size: 24px;\">");
		out.println("<li>작성자 : " + uploader + "</li>");
		out.println("<li>원래 첨부 파일명 : " + originName + "</li>");
		out.println("<li>저장된 첨부 파일명 : " + filesystemName + "</li>");
		out.println("<li>파일명 : " + name + "</li>");
		out.println("<li>경로 : " + parent + "</li>");
		out.println("<li>최종수정일 : " + lastModified + "</li>");
		out.println("<li>파일크기 : " + size + "KB</li>");
		out.println("<li><a href=\"/01_Servlet/FileListServletpractice?parent=" + URLEncoder.encode(parent, "UTF-8") + "\">첨부된 파일 목록 보기</a></li>");
		out.println("</ul>");
		out.flush();
		out.close();
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
