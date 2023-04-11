package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.PostVO;
import repository.PostDAO;

public class PostModifyService implements IPostService {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PostVO post  = new PostVO();
		// 널 체크가 아닌 공백 체크이기 때문에 optional 안 씀 
		int post_no = Integer.parseInt(request.getParameter("post_no").isEmpty() ? "0" : request.getParameter("post_no"));
		post.setPost_no(post_no);
		post.setTitle(request.getParameter("title"));
		post.setContent(request.getParameter("content"));
		
		int updateResult = PostDAO.getInstance().updatePost(post);
		
		if(updateResult == 1) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('게시글이 수정되었습니다.')");
			out.println("location.href='" + request.getContextPath() + "/detail.post?post_no=" + post_no + "'");
			out.println("</script>");
			out.flush();
			out.close();
			return null;
		} else {
			request.setAttribute("post", post);
			return "post/list.jsp";
		}
		
	}

}
