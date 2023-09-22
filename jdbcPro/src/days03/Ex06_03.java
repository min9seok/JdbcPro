package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.util.DBConn;

import domain.EmpVO;

public class Ex06_03 {

	public static void main(String[] args) {
		// Callablestatement 를 사용해서
		// emp 테이블에서 CRUD 작업
		// Ex05_03 > Ex06_03 UPDATE
		String sql = "{ call UP_UPDEMP(?,?,?,?,?,?,?) }";
		Connection conn = null;
		CallableStatement cstmt = null;
		EmpVO vo = null;		
		int empno= 9999;
		String ename= "MIN";
		String job= "SALESMAN";		
		int mgr = 7698;
		int sal = 500;
		int comm = 0;
		int deptno = 20;
		int rowcount = 0;
		try {
			conn = DBConn.getConnection();			
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, empno);
			cstmt.setString(2, ename);
			cstmt.setString(3, job);
			cstmt.setInt(4, mgr);
			cstmt.setInt(5, sal);
			cstmt.setInt(6, comm);
			cstmt.setInt(7, deptno);		
			rowcount = cstmt.executeUpdate();
			if(rowcount==1)System.out.println(" 사원 수정 해부럿다 ");
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

//CREATE OR REPlACE PROCEDURE up_updemp
//(  pempno emp.empno%type
//  ,pename emp.ename%type := NULL 
//  ,pjob emp.job%type := NULL 
//  ,pmgr emp.mgr%type := NULL 
//  ,psal emp.sal%type := NULL 
//  ,pcomm emp.comm%type := NULL 
//  ,pdeptno emp.deptno%type := NULL 
//)
//is 
//begin
//UPDATE emp
//SET ename = nvl(pename,ename)
//   ,job   = nvl(pjob,job)
//   ,mgr   = nvl(pmgr,mgr)
//   ,sal   = nvl(psal,sal)
//   ,comm   = nvl(pcomm,comm)
//   ,deptno   = nvl(pdeptno,deptno)
//WHERE empno = pempno;
// COMMIT;
//end;