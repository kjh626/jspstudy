package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.PostVO;
import repository.PostDAO;

public class PostEditService implements IPostService {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Optional<String> opt = Optional.ofNullable(request.getParameter("post_no"));
		int post_no = Integer.parseInt(opt.orElse("0"));
		
		// 똑같이 select를 한번 더 해준다 . 없으면 편집하러 못 간다
		PostVO post = PostDAO.getInstance().getPostByNo(post_no);
		
		if(post == null) {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('존재하지 않는 게시글입니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.flush();
				out.close();
				return null;
		} else {	
			request.setAttribute("post", post);
			return "post/edit.jsp";
		}
		
	}

}
