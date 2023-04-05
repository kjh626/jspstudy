package test;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.BbsDTO;
import repository.BbsDAO;

public class BbsTest {

	// BbsDAO의 메소드 단위로 테스트를 진행한다.
	private BbsDAO dao = BbsDAO.getInstance();
	
	// 한번 테스트하고 문제가 없으면 @Test 테스트애너테이션을 주석처리(//@Test)해서 날려준다.
	@Test
	public void 목록테스트() {
		/* 
		사용법 : 첫번째 는 기댓값, 두번째는 실제로 발생한 값 => 우리는 목록의 개수가 2개이길 기대한다. 실제로는 몇개였따?(실제로는 DAO의 select결과를 호출한 결과에 size처리 해보면 됨. = 실제 database에서 가져온 목록의 개수)
		이거 2개 비교해봐서 같으면 테스트성공(초록불), 다르면 테스트실패(빨간불) 
		*/
		assertEquals(2, dao.selectAllBbsList().size());
	}
	
	@Test
	public void 상세테스트() {
		// 널이 아니면 통과
		assertNotNull(dao.selectBbsByNo(1));
	}
	
	@Test
	public void 삽입테스트() {
		// 테스트에 사용할 bean만들어주기
		BbsDTO bbs = new BbsDTO();
		bbs.setTitle("테스트제목");
		bbs.setContent("테스트내용");
		assertEquals(1, dao.insertBbs(bbs));
	}
	
	// 수정할 bbs만들어서 돌려보고
	// 삭제는 그냥 삭제하면 되는 거
}
