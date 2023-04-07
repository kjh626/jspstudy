package service;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.Member;
import repository.MemberDAO;

public class MemberListService implements IMemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 서비스에서 응답을 만들어줘야 한다. 일단은 DB로 부터 데이터를 받아와서 만들어야 한다.
		// 하나의 서비스는 여러 개의 DAO 메소드를 호출할 수 있다.(목록, 전체회원수 2개 메소드)
		MemberDAO dao = MemberDAO.getInstance();
		List<Member> memberList = dao.selectAllMembers();
		int memberCount = dao.getMemberCount();
		
		// 응답할 JSON 데이터 만들기
		/* JSON데이터 예시로 보기 (객체로..)
			{
				"memberCount": 2 (2명이라고 가정)
				"memberList":[
					{
						"memberNo": 회원번호,
						"id": "회원아이디",
						"name": "회원명",
						"gender": "회원성별",
						"address": "회원주소"
					},
				 	{
				 		"memberNo": 회원번호,
						"id": "회원아이디",
						"name": "회원명",
						"gender": "회원성별",
						"address": "회원주소"
				 	}
				 ]
			}
		*/
		// 라이브러리가 쉽게 만들게 도와줄 거
		JSONObject obj = new JSONObject();
		obj.put("memberCount", memberCount);
		obj.put("memberList", memberList);    // JSON라이브러리가 Java의 ArrayList를 JavaScript 배열([])로 바꾸고, Java의 Member 객체를 JavaScript의 객체({})로 바꾼다.
		
		// 응답 (요청한 곳으로 그대로 응답된다. 즉 ajax() 메소드로 응답 처리된다.)  => 응답은 JSON  => JSON의 마임타입:application/json (http표준이기 때문에 스프링에서도 똑같이 처리해야 함)
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		// 보내주기만 하면 돼서 출력메소드 아무거나 써도 됨. (append, println 등 아무거나)
		// 1장의 제이슨서블릿 복습하면 되겠다 obj 반환할 때 toString붙여서..
		out.println(obj.toString());    // JSON 데이터를 텍스트 형식으로 변경해서 반환하기
		out.flush();
		out.close();
		// 이제는 return null 안 함. 이제 컨트롤러는 서비스 실행하면 이동 자체가 없기 때문에 없어도 됨
	}

}
