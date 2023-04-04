package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
	
	
	
	// 자원(Connection, PreparedStatement, ResultSet) 반납하기
	private void close() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 게시글 목록 반환하기
	public List<BoardDTO> selectBoardList() {

		// 1. 반환할 ArrayList 생성
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		
		try {
			
			// 2. DataSource로부터 Connection 얻어 오기
			con = dataSource.getConnection();
			
			// 3. 실행할 쿼리문 (쿼리문은 sql필드에 저장하기로 약속)
			sql = "SELECT BOARD_NO, TITLE, CONTENT, MODIFIED_DATE, CREATED_DATE FROM BOARD ORDER BY BOARD_NO DESC";
			
			// 4. 쿼리문을 실행할 PreparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
			// 5. PreparedStatement 객체를 이용해 쿼리문 실행(SELECT문 실행은 executeQuery 메소드로 한다.)
			rs = ps.executeQuery();
			
			// 6. ResultSet 객체(결과 집합)를 이용해서 ArrayList로 만들어주는 작업
			// rs.next() 메소드를 호출하면 결과 집합의 내용물이 있는지 확인할 수 있다. (1번행이 있는지 없는지. 있으면 true) 
			// 결과가 있으면 rs.getInt("BOARD_NO") 이렇게 정보를 가지고 올 수 있고, rs.getString("TITLE"), rs.getString("Content"), rs.getDate("Modified_date"), rs.getDate("Created_date") 이렇게 한땀한땀 데이터 가져오기 가능.
			// -> 이 한땀한땀 가져온 데이터를 BoardDTO board로 만들어서 ArrayList에 추가해줘야한다.  (ArrayList add) 계속 반복..
			while(rs.next()) {
				// Step1. Board 테이블의 결과 행(ROW)을 읽는다.
				int board_no = rs.getInt("BOARD_NO");
				String title = rs.getString("TITLE");
				String content = rs.getString("Content");
				Date modified_date = rs.getDate("MODIFIED_DATE");
				Date created_date = rs.getDate("CREATED_DATE");
				
				// Step2. 읽은 정보를 이용해서 BoardDTO 객체를 만든다.
				BoardDTO board = new BoardDTO(board_no, title, content, modified_date, created_date);
				
				// Step3. BoardDTO 객체를 ArrayList에 추가한다.
				boardList.add(board);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// 예외 발생 여부와 상관없이 항상 자원의 반납을 해야 한다.( finally 블록에 close() )
			close();
		}
		
		// 7. ArrayList 반환
		return boardList;
	}
	
	// 게시글 반환하기
	public BoardDTO selectBoardByNo(int board_no) {
		
		return null;
	}
	
	// 게시글 삽입하기
	public int insertBoard(BoardDTO board) {
		
		// 1. 삽입 결과 변수 선언
		int insertResult = 0;
		
		try {
			// 2~4과정은 위에 거 반복 
			// 2. DataSource로부터 Connection 얻어 오기
			con = dataSource.getConnection();
			
			// 3. 실행할 쿼리문
			sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, NULL, SYSDATE)";
			
			// 4. 쿼리문을 실행할 PreparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
			// 5. 쿼리문에 변수 값 전달하기
			ps.setString(1, board.getTitle());     // 1번째 물음표(?)에 title 전달하기
			ps.setString(2, board.getContent());   // 2번째 물음표(?)에 content 전달하기
			
			// 6. PreparedStatement 객체를 이용해 쿼리문 실행(INSERT문 실행은 executeUpdate 메소드로 한다.)
			insertResult = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 예외 발생 여부와 상관 없이 항상 자원의 반납을 해야 한다.
			close();
		}
		
		// 7. 삽입 결과 반환
		return insertResult;
	}
	
	// 게시글 수정하기
	public int updateBoard(BoardDTO board) {
		
		return 0;
	}
	
	// 게시글 삭제하기
	public int deleteBoard(int board_no) {
		
		// 1. 삭제 결과 변수 선언
		int deleteResult = 0;
		
		try {
			
			// 2. DataSource로부터 Connection 얻어 오기
			con = dataSource.getConnection();
			
			// 3. 실행할 쿼리문
			sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
			
			// 4. 쿼리문을 실행할 PreparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
			// 5. 쿼리문에 변수 값 전달하기
			ps.setInt(1, board_no);     // 1번째 물음표(?)에 board_no 전달하기
			
			// 6. PreparedStatement 객체를 이용해 쿼리문 실행(DELETE문 실행은 executeUpdate 메소드로 한다.)
			deleteResult = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 예외 발생 여부와 상관 없이 항상 자원의 반납을 해야 한다.
			close();
		}
		
		// 7. 삭제 결과 반환
		return deleteResult;
	}
}
