package days03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.util.DBConn;

public class Ex02 {

	public static void main(String[] args) {
		// 자바 트랜잭션 처리 
		// 논리적인 작업 단위 
		// 전부 완료. ( commit )
		// 전부 롤백. ( rollback )
		// 예) 계좌 이체
		//      A 통장 돈을 인출 UPDATE : detp 50 부서 추가 O 
		//      B 통장 돈을 입금 UPDATE : detp 50 부서 추가 X
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowcount = 0;
		String sql = "INSERT INTO dept VALUES(?,?,?)";
		
		conn = DBConn.getConnection();
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,50);
			pstmt.setString(2,"QC1");
			pstmt.setString(3,"SEOUL");
			rowcount = pstmt.executeUpdate();
			
			if(rowcount==1)System.out.println("1번 부서 추가 요");
			
			pstmt.setInt(1,50);
			pstmt.setString(2,"QC2");
			pstmt.setString(3,"SEOUL");
			rowcount = pstmt.executeUpdate();
			
			if(rowcount==1)System.out.println("2번 부서 추가 요");
conn.commit();			
		} catch (SQLException e) {					
			try {
				conn.rollback();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {		
				e.printStackTrace();
			}
		}			
		DBConn.close();		
	}//main
}//class
