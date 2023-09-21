package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.util.DBConn;

import domain.DeptVO;

public class Ex05_04 {

	public static void main(String[] args) {
		// DEPT DELETE
		String sql = "{ call UP_DELDEPT(?) }";
		Connection conn = null;
		CallableStatement cstmt = null;
		DeptVO vo = null;				
		int rowcount = 0;
		try {
			conn = DBConn.getConnection();			
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, 50);			
			rowcount = cstmt.executeUpdate();
			if(rowcount==1)System.out.println(" 부서 삭제 해부럿다 ");
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

//CREATE OR REPlACE PROCEDURE UP_DELDEPT
//( pdeptno dept.deptno%type
//)
//is 
//begin
//DELETE FROM  dept
//WHERE deptno = pdeptno;
// COMMIT;
//end;