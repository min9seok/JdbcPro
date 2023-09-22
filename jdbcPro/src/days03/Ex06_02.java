package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import com.util.DBConn;

import domain.EmpVO;

public class Ex06_02 {

	public static void main(String[] args) {
		// Callablestatement 를 사용해서
		// emp 테이블에서 CRUD 작업
		// Ex05_02 > Ex06_02 INSERT
		String sql = "{ call UP_INSEMP(?,?,?,?,?,?,?,?) }";
		Connection conn = null;
		CallableStatement cstmt = null;
		EmpVO vo = null;		
		int empno= 9999;
		String ename= "PARK";
		String job= "SALESMAN";
		int mgr = 7698;
		String hiredate = "1981-09-29";
		int sal = 800;
		int comm = 300;
		int deptno = 20;
		int rowcount = 0;
		try {
			conn = DBConn.getConnection();			
			cstmt = conn.prepareCall(sql);		
			cstmt.setInt(1, empno);
			cstmt.setString(2, ename);
			cstmt.setString(3, job);
			cstmt.setInt(4, mgr);
			cstmt.setString(5, hiredate);
			cstmt.setInt(6, sal);
			cstmt.setInt(7, comm);
			cstmt.setInt(8, deptno);			
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

//CREATE OR REPlACE PROCEDURE up_insemp
//( pempno emp.empno%type
//  ,pename emp.ename%type := NULL 
//  ,pjob emp.job%type := NULL 
//  ,pmgr emp.mgr%type := NULL 
//  ,phiredate emp.hiredate%type := NULL 
//  ,psal emp.sal%type := NULL 
//  ,pcomm emp.comm%type := NULL 
// ,pdeptno emp.deptno%type := NULL 
//)
//is 
//begin
// INSERT INTO emp VALUES(pempno,pename,pjob,pmgr,phiredate,psal,pcomm,pdeptno);
// COMMIT;
//end;
