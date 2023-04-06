package test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.Member;
import repository.MemberDAO;

public class MemberUnitTest {

	@BeforeClass  // MemberUnitTest 클래스(현재 테스트 파일) 실행 이전에 한 번 먼저 실행된다. (static 처리가 되어 있어야 한다.)
	// 우리 계획은 테스트 전에 일단 삽입이 되는지 보겠다. 한번에 5개 다 테스트 할 거다. 그리고 다 끝나면 테스트한 거 삭제하겠다.
	public static void 삽입테스트() {
		Member member = new Member(0, "admin", "관리자", "M", "seoul");  // 1이란 번호는 시퀀스가 집어넣으므로 굳이 필요하지 않다.
		assertEquals(1, MemberDAO.getInstance().insertMember(member));
	}
	
	@Test
	public void 목록테스트() {
		// 목록의 개수를 구해야해서 size()
		assertEquals(1, MemberDAO.getInstance().selectAllMembers().size());
	}
	
	@Test
	public void 상세테스트() {
		// 객체 비교 (아래 생성한 멤버와 번호 1번으로 가져온 멤버가 같은지..?)
		Member member = new Member(1, "admin", "관리자", "M", "seoul");
		assertEquals(member, MemberDAO.getInstance().selectMemberByNo(1));    // 1번 회원의 데이터를 가져오는 코드
	}
	
	@Test
	public void 수정테스트() {
		// 수정다오에서 아이디는 전달할 필요 없으니 null
		// 번호 필요.  이름,성별,주소 수정 가능 
		Member member = new Member(1, null, "new관리자", "F", "newseoul");
		assertEquals(1, MemberDAO.getInstance().updateMember(member));
	}
	
	@AfterClass    // MemberUnitTest 클래스(현재 테스트 파일) 실행 이후에 한 번 먼저 실행된다. (static 처리가 되어 있어야 한다.)
	public static void 삭제테스트() {
		// 삭제 결과도 1 기대한다
		assertEquals(1, MemberDAO.getInstance().deleteMember(1));   // 멤버넘버 1인 회원 삭제할 거다.
	}
	
	// 한 번에 다 테스트하는 게 목적이라 테스트 뻑나면 sql문 얼른 실행하고 돌아와라 . 한번에 하는 것을 권장하는 게 아니다. 새 애너테이션 연습해본 것이다.
	
}
