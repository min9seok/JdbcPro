package days02;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.util.DBConn;

public class Test05 {

	public static void main(String[] args) {
		// emp 테이블에서 사원의 정보를 삭제 삭제할 사원번호 입력 받아 삭제  
		Scanner scanner = new Scanner(System.in);
		Connection conn;
		Statement stmt = null;
		String empno;
		int rowcount;

		System.out.print("> 삭제할 사원번호 입력 ? ");
		empno = scanner.next();

		String sql = " DELETE FROM EMP  "				
				+ " WHERE empno IN ( "+ empno +")";		
		System.out.println(sql);
		conn = DBConn.getConnection();							
		try {
			stmt = conn.createStatement();
			rowcount = stmt.executeUpdate(sql);
			if( rowcount > 0) {
				System.out.println("사원 삭제 해스요");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
		DBConn.close();				
		System.out.println(" end ");	

	}//main

}//class
