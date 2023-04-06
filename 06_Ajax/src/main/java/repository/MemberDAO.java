package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Member;

public class MemberDAO {
	
	private SqlSessionFactory factory;
	
	private static MemberDAO dao = new MemberDAO();
	private MemberDAO() {
		// 여기가 뭐하는 곳? 팩토리 만드는 곳
		// 공장 짓는 코드 ( 마이바티스 사이트에서 복붙했으나 오늘은 직접 적어보기 )
		try {
			// 마이바티스컨피그.xml이(여기서 뽑아쓴다) resource임. 점(.)이 아니라 슬래시로 구분
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream in = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static MemberDAO getInstance() {
		return dao;
	}
	
	// mapper's namespace (어떤 mapper인지 인식하기 위함)
	private final String NS = "mybatis.mapper.member.";
	
	// 메소드명은 쿼리의 id와 동일한 이름을 사용하자
	
	// 목록
	public List<Member> selectAllMembers() {
		SqlSession ss = factory.openSession();
		List<Member> members = ss.selectList(NS + "selectAllMembers");  // mybatis.mapper.member.selectAllMembers.  member.xml(매퍼)에 짜둔 selectAllMembers 셀렉트문을 호출한다.
		ss.close();
		return members;
	}
	
	// 전체 회원수
	public int getMemberCount() {
		SqlSession ss = factory.openSession();
		int count = ss.selectOne(NS + "getMemberCount");    // mybatis.mapper.member.getMemberCount
		ss.close();
		return count;
	}
	
	// 상세
	// 상세에서는 셀렉트를 위한 회원번호 받아옴(매개변수) 이걸 쿼리문에 전달해야 한다.(selectOne의 두번째 매개변수로 memberNo 써준다) → 쿼리문에 파라미터타입 필요(int)
	// => 쿼리문으로 전달하는 파라미터
	public Member selectMemberByNo(int memberNo) {
		SqlSession ss = factory.openSession();
		Member member = ss.selectOne(NS + "selectMemberByNo", memberNo);
		ss.close();
		return member;
	}
	
	// 삽입
	public int insertMember(Member member) {
		SqlSession ss = factory.openSession(false);
		int insertResult = ss.insert(NS + "insertMember", member);
		if(insertResult == 1) {
			ss.commit();
		}
		ss.close();
		return insertResult;
	}
	
	// 수정
	public int updateMember(Member member) {
		SqlSession ss = factory.openSession(false);
		int updateResult = ss.update(NS + "updateMember", member);
		if(updateResult == 1) {
			ss.commit();
		}
		ss.close();
		return updateResult;
	}
	
	// 삭제
	public int deleteMember(int memberNo) {
		SqlSession ss = factory.openSession(false);
		int deleteResult = ss.delete(NS + "deleteMember", memberNo);
		if(deleteResult == 1) {
			ss.commit();
		}
		ss.close();
		return deleteResult;
	}
}
