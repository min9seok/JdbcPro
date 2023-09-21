package days02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.util.DBConn;

import domain.DeptVO;

public class Ex05_04 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Connection conn;
		PreparedStatement pstmt = null;
		String deptno;
		int rowcount;

		System.out.print("> 삭제할 부서번호 입력 ? ");
		deptno = scanner.next();

		String sql = " DELETE FROM DEPT  "				
				+ " WHERE deptno = ?";		
		System.out.println(sql);
		conn = DBConn.getConnection();							
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,deptno);
			rowcount = pstmt.executeUpdate();
			if( rowcount > 0) {
				System.out.println("부서 삭제 해스요");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
		DBConn.close();				
		System.out.println(" end ");	

	}//main

}//class
