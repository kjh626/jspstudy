package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.BbsDTO;

public class BbsDAO {
	
	// 필드
	private SqlSessionFactory factory;
	
	// Singleton Pattern
	private static BbsDAO dao = new BbsDAO();
	private BbsDAO() {
		try {
			// 이거 내용 마이바티스-시작하기 XML에서 SqlSessionFactory 빌드하기 참고
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static BbsDAO getInstance() {
		return dao;
	}
	
	/* 메소드명과 쿼리문의 id를 맞추자.(우리끼리의 약속) */
	
	// mapper의 namespace
	private final String NS = "mybatis.mapper.bbs.";
	
	// 1. 목록
	public List<BbsDTO> selectAllBbsList() {
		// ↓ 얘가 DB와 연결해주는 거다
		SqlSession ss = factory.openSession();
		List<BbsDTO> bbsList = ss.selectList(NS + "selectAllBbsList");  // mapper's namespace + query's id
		ss.close();
		return bbsList;
		// 바로 return하지 않고 저장한 이유는? close()를 해주기 위해서
	}

	// 2. 상세
	public BbsDTO selectBbsByNo(int bbsNo) {
		// SqlSession은 공장에서 뽑아준다.
		SqlSession ss = factory.openSession();
		BbsDTO bbs = ss.selectOne(NS + "selectBbsByNo", bbsNo);    // parameter 전달이 있음을 주의!
		ss.close();
		return bbs;
	}
	
	// 3. 삽입
	public int insertBbs(BbsDTO bbs) {
		// 오토 커밋 여부를 물어보는 boolean타입 매개변수
		// 우리는 수동으로 커밋해줄 거라 false줬다
		SqlSession ss = factory.openSession(false);    // autoCommit을 하지 않고, 직접 Commit 할 거다.
		// 매개변수 2개짜리 쓸 거다. bbs 전달해줘야 하기 때문에
		int insertResult = ss.insert(NS + "insertBbs", bbs);
		if(insertResult == 1) {  // 삽입 성공 시
			ss.commit();         // commit 하시오.
		}
		ss.close();
		return insertResult;
	}
	
	// 4. 수정
	public int updateBbs(BbsDTO bbs) {
		SqlSession ss = factory.openSession(false);
		int updateResult = ss.update(NS + "updateBbs", bbs);
		if(updateResult == 1) {
			ss.commit();
		}
		ss.close();
		return updateResult;
	}
	
	// 5. 삭제
	public int deleteBbs(int bbsNo) {
		SqlSession ss = factory.openSession(false);
		int deleteResult = ss.delete(NS + "deleteBbs", bbsNo);
		if(deleteResult == 1) {
			ss.commit();
		}
		ss.close();
		return deleteResult;
	}
	
	
	
	
	
	
	
	
	
	
	
}
