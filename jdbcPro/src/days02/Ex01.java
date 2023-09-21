package days02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import com.util.DBConn;

import domain.EmpVO;

public class Ex01 {

	public static void main(String[] args) throws Exception {
		
		Connection conn;
		int deptno = 10;
		String sql = String.format("SELECT * "
				   + "FROM EMP"
				   + "WHERE deptno = %d",deptno);
		Statement stmt;
		ResultSet rs;
		ArrayList<EmpVO> list = null;
		 int empno;
		 String ename;
		 String job;
		 int mgr;
		 String hiredate;
		 double sal;
		 double comm;
		 EmpVO vo;
		
		conn =  DBConn.getConnection();
		
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		if(rs.next()) {
			list = new ArrayList();
			do {
				empno = rs.getInt("empno");
				ename = rs.getString("ename");
				job = rs.getString("job");
				mgr = rs.getInt("mgr");
				hiredate = rs.getString("hiredate");
				sal = rs.getDouble("sal");
				comm = rs.getDouble("comm");
				
				vo = new EmpVO(empno, ename, job, mgr, hiredate, empno, mgr, deptno);
				list.add(vo);
				
			}while(rs.next());
		}
		printEmpList( list );
		
		
		stmt.close();
		rs.close();
		DBConn.close();
	}//main

	private static void printEmpList(ArrayList<EmpVO> list) {
		if ( list == null ){
			System.out.println(" 사원 노노염~~");
			return;
		}
		int count = list.size();
		int deptno = 10;
		System.out.printf("> %d 부서의 사원수 : %d 명\n",deptno,count);
		Iterator<EmpVO> ir = list.iterator();
		while (ir.hasNext()) {
			EmpVO vo = ir.next();
			System.out.println(vo);			
		}
		
	}

}//class
