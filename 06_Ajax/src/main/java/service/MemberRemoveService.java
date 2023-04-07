package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.Member;
import repository.MemberDAO;

public class MemberRemoveService implements IMemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		
		int deleteResult = MemberDAO.getInstance().deleteMember(memberNo);
		
		response.setContentType("application/json; charset=UTF-8");
		
		JSONObject obj = new JSONObject();
		obj.put("deleteResult", deleteResult);
		PrintWriter out = response.getWriter();
		out.println(obj.toString());
		out.flush();
		out.close();
	}

}
