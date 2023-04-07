package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.Member;
import repository.MemberDAO;

public class MemberDetailService implements IMemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		
		// DB에서 memberNo값을 가진 회원 정보를 받아오기
		Member member = MemberDAO.getInstance().selectMemberByNo(memberNo);
		
		/*
		// 객체를 제이슨오브젝트로 자동으로 바꿔서 볼 수 있게
		JSONObject obj = new JSONObject(member);
		System.out.println(obj);
		*/
		
		// 응답 데이터 형식 (JSON)
		response.setContentType("application/json; charset=UTF-8");
		
		// 응답 데이터 만들기( 2가지 방법의 차이점은? )
		// 첫 번째 것은 프로퍼티가 5개, ★두 번째 것은 프로퍼티가 1개
		// 1. 객체자체를 전달  			★2. 프로퍼티("member")에 member를 전달
		// 두 번째 방법이 더 쓸 만한 방법이다.
		/*
			JSONObject obj = new JSONObject(member);
			System.out.println(obj.toString());
					{"memberNo":1,"address":"new york","gender":"M","name":"피터파커","id":"spiderman"}
					{"member":"Member(memberNo=1, id=spiderman, name=피터파커, gender=M, address=new york)"}	
		*/
		
		// 응답 데이터 만들기
		/*
 			{
 				"member": {
 					"memberNo": 회원번호,
 					"id": 회원아이디,
 					"name": 회원명,
 					"gender": 성별,
 					"address": 주소
 				}
			}
		*/
		JSONObject obj = new JSONObject();
		obj.put("member", new JSONObject(member));
		// System.out.println(obj.toString());
		// member에 Member정보가 들어있다. member 를 new JSONObject(member) 이렇게 강제로 변환해줘야 한다.

		// 응답
		PrintWriter out = response.getWriter();
		out.println(obj.toString());
		out.flush();
		out.close();
			
		
		
		
	}

}
