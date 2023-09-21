package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.util.DBConn;

import domain.EmpVO;

public class Ex06_02 {

	public static void main(String[] args) {
		// Callablestatement 를 사용해서
		// emp 테이블에서 CRUD 작업
		// Ex05_02 > Ex06_02 INSERT
		String sql = "{ call UP_INSDEPT(?,?) }";
		Connection conn = null;
		CallableStatement cstmt = null;
		EmpVO vo = null;		
		int deptno;
		String dname="QC",loc="SEOUL";
		int rowcount = 0;
		try {
			conn = DBConn.getConnection();			
			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, dname);
			cstmt.setString(2, loc);
			rowcount = cstmt.executeUpdate();
			if(rowcount==1)System.out.println(" 사원 추가 해부럿다 ");
		} catch (SQLException e) {		
			e.printStackTrace();
		}finally {
			try {
				cstmt.close();
				DBConn.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
		System.out.println(" end ");
		
		
		
	}//main

}//class
