package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.util.DBConn;

import domain.EmpVO;

public class Ex06_04 {

	public static void main(String[] args) {
		// Callablestatement 를 사용해서
		// emp 테이블에서 CRUD 작업
		// Ex05_04 > Ex06_04 DELETE
		String sql = "{ call UP_DELEMP(?) }";
		Connection conn = null;
		CallableStatement cstmt = null;
		EmpVO vo = null;				
		int rowcount = 0;
		try {
			conn = DBConn.getConnection();			
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, 9999);			
			rowcount = cstmt.executeUpdate();
			if(rowcount==1)System.out.println(" 사원 삭제 해부럿다 ");
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

//CREATE OR REPlACE PROCEDURE UP_DELEMP
//( pempno emp.empno%type
//)
//is 
//begin
//DELETE FROM  emp
//WHERE empno = pempno;
// COMMIT;
//end;