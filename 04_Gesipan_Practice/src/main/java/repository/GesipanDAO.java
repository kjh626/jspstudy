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

import domain.GesipanDTO;

public class GesipanDAO {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	private DataSource dataSource;
	
	private static GesipanDAO dao = new GesipanDAO();
	private GesipanDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/GDJ61");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	public static GesipanDAO getinstance() {
		return dao;
	}
	
	// 자원 반납하기
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
	public List<GesipanDTO> selectGesipanList(){
		
		List<GesipanDTO> gesipanList = new ArrayList<GesipanDTO>();
		
		try {
			
			con = dataSource.getConnection();
			
			sql = "SELECT GESIPAN_NO, TITLE, CONTENT, MODIFIED_DATE, CREATED_DATE FROM GESIPAN ORDER BY GESIPAN_NO DESC";
			
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int gesipan_no = rs.getInt("GESIPAN_NO");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				Date modified_date = rs.getDate("MODIFIED_DATE");
				Date created_date = rs.getDate("CREATED_DATE");
				
				GesipanDTO gesipan = new GesipanDTO(gesipan_no, title, content, modified_date, created_date);
				
				gesipanList.add(gesipan);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return gesipanList;
	}
	
	// 게시글 반환하기
	public GesipanDTO selectGesipanByNo(int gesipan_no) {
		
		GesipanDTO gesipan = null;
		
		try {
			
			con = dataSource.getConnection();
			
			sql = "SELECT GESIPAN_NO, TITLE, CONTENT, MODIFIED_DATE, CREATED_DATE FROM GESIPAN WHERE GESIPAN_NO = ?";
			
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, gesipan_no);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				Date modified_date = rs.getDate("MODIFIED_DATE");
				Date created_date = rs.getDate("CREATED_DATE");
				
				gesipan = new GesipanDTO(gesipan_no, title, content, modified_date, created_date);
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return gesipan;
	}
	// 게시글 삽입하기
	public int insertGesipan(GesipanDTO gesipan) {
		
		return 0;
	}
	
	// 게시글 수정하기
	public int updateGesipan(GesipanDTO gesipan) {
		
		return 0;
	}
	// 게시글 삭제하기
	public int deleteGesipan(GesipanDTO gesipan) {
		
		return 0;
	}

}
