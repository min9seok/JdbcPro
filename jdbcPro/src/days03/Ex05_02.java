package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.util.DBConn;

import domain.DeptVO;
import oracle.jdbc.internal.OracleTypes;

public class Ex05_02 {

	public static void main(String[] args) {
		// DEPT INSERT
		String sql = "{ call UP_INSDEPT(?,?) }";
		Connection conn = null;
		CallableStatement cstmt = null;
		DeptVO vo = null;		
		int deptno;
		String dname="QC",loc="SEOUL";
		int rowcount = 0;
		try {
			conn = DBConn.getConnection();			
			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, dname);
			cstmt.setString(2, loc);
			rowcount = cstmt.executeUpdate();
			if(rowcount==1)System.out.println(" 부서 추가 해부럿다 ");
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

//SELECT LAST_NUMBER - increment_by
//FROM user_sequences;

//CREATE OR REPlACE PROCEDURE up_insdept
//( pdname dept.dname%type := NULL 
// ,ploc dept.loc%type := NULL 
//)
//is 
//begin
// INSERT INTO dept VALUES(SEQ_DEPT.nextval,pdname,ploc);
// COMMIT;
//end;