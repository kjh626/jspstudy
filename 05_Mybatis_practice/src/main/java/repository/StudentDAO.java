package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.StudentDTO;

public class StudentDAO {
	
	// 필드
	private SqlSessionFactory factory;
	
	// Singleton Pattern
	private static StudentDAO dao = new StudentDAO();
	private StudentDAO() {
		try {
			// 이거 내용 마이바티스-시작하기 XML에서 SqlSessionFactory 빌드하기 참고
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static StudentDAO getInstance() {
		return dao;
	}
	
	/* 메소드명과 쿼리문의 id를 맞추자.(우리끼리의 약속) */
	
	// mapper의 namespace
	private final String NS = "mybatis.mapper.student.";
	
	// 1. 목록
	public List<StudentDTO> selectAllStudentList() {
		// ↓ 얘가 DB와 연결해주는 거다
		SqlSession ss = factory.openSession();
		List<StudentDTO> studentList = ss.selectList(NS + "selectAllStudentList");  // mapper's namespace + query's id = mybatis.mapper.bbs.selectAllBbsList
		ss.close();
		return studentList;
	}
	
	// 2. 삽입
	public int insertStudent(StudentDTO student) {
		SqlSession ss = factory.openSession(false);    // autoCommit을 하지 않고, 직접 Commit 할 거다.
		// 매개변수 2개짜리 쓸 거다. bbs 전달해줘야 하기 때문에
		int insertResult = ss.insert(NS + "insertStudent", student);
		if(insertResult == 1) {  // 삽입 성공 시
			ss.commit();         // commit 하시오.
		}
		ss.close();
		return insertResult;
	}

	// 3. 상세
	public StudentDTO selectStudentByNo(int stuNo) {
		// SqlSession은 공장에서 뽑아준다.
		SqlSession ss = factory.openSession();
		StudentDTO student = ss.selectOne(NS + "selectStudentByNo", stuNo);    // parameter 전달이 있음을 주의!
		ss.close();
		return student;
	}
	
	
	// 4. 수정
	public int updateStudent(StudentDTO student) {
		SqlSession ss = factory.openSession(false);
		int updateResult = ss.update(NS + "updateStudent", student);
		if(updateResult == 1) {
			ss.commit();
		}
		ss.close();
		return updateResult;
	}
	
	// 5. 삭제
	public int deleteStudent(int stuNo) {
		SqlSession ss = factory.openSession(false);
		int deleteResult = ss.delete(NS + "deleteStudent", stuNo);
		if(deleteResult == 1) {
			ss.commit();
		}
		ss.close();
		return deleteResult;
	}
	
	
	
	
	
	
	
	
	
	
	
}
