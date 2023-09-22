package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.util.DBConn;

import domain.EmpVO;
import oracle.jdbc.internal.OracleTypes;

public class Ex06 {

	public static void main(String[] args) {
		// Callablestatement 를 사용해서
		// emp 테이블에서 CRUD 작업
		// Ex05 > Ex06 SELECT
		String sql = "{ call UP_SELEMP(?) }";
		Connection conn = null;
		CallableStatement cstmt = null;
		EmpVO vo = null;
		ArrayList<EmpVO> list = null;
		ResultSet rs = null;
		 int empno;
		 String ename;
		 String job;
		 int mgr;
		 String hiredate;
		 int sal;
		 int comm;
		 int deptno;
		try {
			conn = DBConn.getConnection();			
			cstmt = conn.prepareCall(sql);			
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.execute();
			rs = (ResultSet) cstmt.getObject(1);
			while (rs.next()) {
				 empno = rs.getInt("empno");
				 ename = rs.getString("ename");;
				 job = rs.getString("job");;;
				 mgr = rs.getInt("mgr");;
				 hiredate = rs.getString("hiredate");;;
				 sal = rs.getInt("sal");;
				 comm = rs.getInt("comm");;
				 deptno = rs.getInt("deptno");;
				 vo = new EmpVO(empno, ename, job, mgr, hiredate, sal, comm, deptno);
				System.out.println(vo);
			}
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

//create or replace PROCEDURE up_selemp
//( 
// pcursor OUT SYS_REFCURSOR
//)
//is 
//begin
// OPEN pcursor FOR 
// SELECT *
// FROM emp;
//end;