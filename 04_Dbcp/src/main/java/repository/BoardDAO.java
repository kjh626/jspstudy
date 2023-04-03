package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.BoardDTO;

public class BoardDAO {

	// 모든 메소드가 사용할 공통 필드
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	// Connection 관리를 위한 DataSource 필드  (8개 만들어두고 필요하면 빌려주기)
	// dataSource 현재 null. context.xml에서 <resource> 읽어서..
	private DataSource dataSource;
	
	// DAO 동시성.. new BoardDAO를 허용해주면 , new BoardDAO(), new BoardDAO() 한 객체는 insert하러가고, 한 객체는 select하러 가고 그래서 동시 접근할 수 있어서 문제가 발생할 수 있다.
	// 그래서 데이터베이스 접근 객체(DAO)는 언제나 new DAO()를 못하게 막아줘야한다.
	// => 그래서 singleton Pattern 을 배움. 객체를 하나만 만들어 놓고 쓰겠다.
	// Singleton Pattern으로 DAO 생성하기
	private static BoardDAO dao = new BoardDAO();
	// 아무도 호출할 수 없게 생성자를 private 처리. 어떤 서비스들도 new DAO를 호출할 수 없다
	
	private BoardDAO() {
		// name기준으로 구분. (JNDI)
		// context.xml에서 <Resource name="jdbc/GDJ61" />인 Resource를 읽어서 DataSource 객체 생성하기 (JNDI 방식)
		try {
			// javax.naming 임포트
			Context context = new InitialContext();
			// resource를 읽어들이는 Context. 약속된 이름(java:comp/env) , Context로 캐스팅도 해줘야함
			Context envContext = (Context) context.lookup("java:comp/env");
			// 톰캣의 context 읽을 준비 완료. 항상 lookup은 Object를 반환, 데이터소스니까 데이터소스 캐스팅 해주기. 
			dataSource = (DataSource) envContext.lookup("jdbc/GDJ61");
			/* 		위 3줄과 아래 2줄은 같은 코드
				Context context = new InitialContext();
				dataSource = (DataSource)context.lookup("java:comp/env/jdbc/GDJ61");
			*/
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	// 하나는 가져다 쓸 수 있게 공개. new DAO() 자기가 하고 그걸 반환시켜주는 getInstance메소드를 만들어줌
	public static BoardDAO getInstance() {
		return dao;    // 빨간 줄 -> 메소드 static 처리되려면 필드도 static처리해야 한다.
	};
	
	// 게시글 목록 반환하기
	public List<BoardDTO> selectBoardList() {
		
		return null;
	}
	
	// 게시글 반환하기
	public BoardDTO selectBoardByNo(int board_no) {
		
		return null;
	}
	
	// 게시글 삽입하기
	public int insertBoard(BoardDTO board) {
		
		return 0;
	}
	
	// 게시글 수정하기
	public int updateBoard(BoardDTO board) {
		
		return 0;
	}
	
	// 게시글 삭제하기
	public int deleteBoard(int board_no) {
		
		return 0;
	}
}
