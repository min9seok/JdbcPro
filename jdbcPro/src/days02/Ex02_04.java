package days02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.util.DBConn;

import domain.DeptVO;

public class Ex02_04 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Connection conn;
		Statement stmt = null;
		String deptno;
		int rowcount;





		System.out.print("> 삭제할 부서번호 입력 ? ");
		deptno = scanner.next();

		String sql = " DELETE FROM DEPT  "				
				+ " WHERE deptno IN ( "+ deptno +")";		
		System.out.println(sql);
		conn = DBConn.getConnection();							
		try {
			stmt = conn.createStatement();
			rowcount = stmt.executeUpdate(sql);
			if( rowcount > 0) {
				System.out.println("부서 삭제 해스요");
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
