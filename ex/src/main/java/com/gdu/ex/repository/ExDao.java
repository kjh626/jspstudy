package com.gdu.ex.repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.gdu.ex.domain.ExDto;

public class ExDao {

	// field
	private SqlSessionFactory factory;  // SqlSession을 만드는 공장
	
	// Singleton Pattern
	private static ExDao dao = new ExDao();
	private ExDao() {
		try {
			String resource = "com/gdu/ex/config/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static ExDao getInstance() {
		return dao;
	}
	
	// SqlSession이란?
	// MyBatis에서 사용하는 인터페이스
	// mapper에 있는 쿼리문을 읽어서 실행하는 객체
	
	// 메소드 1개가 쿼리문 1개를 실행한다.
	
	private final String NS = "com.gdu.ex.repository.ex.";  // mapper's namespace
	
	/*
		ss.selectList() : SELECT 결과 행이 2개 이상일 때 사용한다.
		ss.selectOne()  : SELECT 결과 행이 1개일 때 사용한다.
		ss.insert()     : INSERT 실행할 때 사용한다.
		ss.update()     : UPDATE 실행할 때 사용한다.
		ss.delete()     : DELETE 실행할 때 사용한다.
	*/
	
	public List<ExDto> list() {
		SqlSession ss = factory.openSession();
		List<ExDto> result = ss.selectList(NS + "list");  // com.gdu.ex.repository.ex.list
		ss.close();  // 사용한 자원(con, ps, rs) 반납
		return result;
	}
	
	public ExDto detail(int exNo) {
		SqlSession ss = factory.openSession();
		ExDto result = ss.selectOne(NS + "detail", exNo);  // exNo가 detail 쿼리로 전달되는 parameter이다.
		ss.close();
		return result;
	}
	
	public int save(ExDto ex) {
		SqlSession ss = factory.openSession(false);  // commit 코드를 직접 넣겠다.
		int result = ss.insert(NS + "save", ex);
		if(result == 1) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	public int remove(int exNo) {
		SqlSession ss = factory.openSession(false);
		int result = ss.delete(NS + "remove", exNo);
		if(result == 1) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
}
