package days02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

import domain.DeptVO;

public class Test04 {

	public static void main(String[] args) throws Exception {
		// emp 테이블에서 사원의 정보를 수정 수정할 사원번호, 기타 사원정보를 입력 받아 수정  		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int empno=0;
		 String ename="";
		 String job="";
		 Integer mgr=null;
		 String hiredate="";
		 Integer sal=0;
		 Integer comm=0;
		 Integer deptno=0;			
		String originalename = null,originaljob=null,originalhiredate=null;
		int originalmgr=0,originalsal=0,originalcomm=0,originaldeptno=0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql;
		int rowcount;
		DeptVO vo = null;
		ArrayList<DeptVO> list = null;
		System.out.print("> 수정할 empno를 입력 ? ");
		empno = Integer.parseInt(br.readLine());
		System.out.print("> 수정할 ename를 입력 ? ");
		ename = br.readLine();
		System.out.print("> 수정할 job를 입력 ? ");
		job = br.readLine();
		System.out.print("> 수정할 mgr를 입력 ? ");
		mgr = br.read();
		System.out.print("> 수정할 hiredate를 입력 ? ");
		hiredate = br.readLine();
		System.out.print("> 수정할 sal를 입력 ? ");
		sal = br.read();
		System.out.println();
		System.out.print("> 수정할 comm를 입력 ? ");
		comm = br.read();
		System.out.println();
		System.out.print("> 수정할 deptno를 입력 ? ");
		deptno = br.read();
		
		sql = String.format("SELECT * FROM EMP WHERE empno = %d ",empno);
		System.out.println(sql);
		conn = DBConn.getConnection();	
		stmt = conn.createStatement();			
		rs = stmt.executeQuery(sql);		
		if(rs.next()) {
			list = new ArrayList();
			originalename = rs.getString("ename");				 
			originaljob = rs.getString("job");
			originalmgr = rs.getInt("mgr");				 
			originalhiredate = rs.getString("hiredate");
			originalsal = rs.getInt("sal");				 
			originalcomm = rs.getInt("comm");
			originaldeptno = rs.getInt("deptno");			
		}else {
			System.out.println(" 사원 없는디 ");
			stmt.close();
			rs.close();
		}				
//---------------------------------------------------------
		if(ename.equals("")) ename = originalename;
		if(job.equals(""))	 job = originaljob;
		if(mgr.equals("") ) mgr = originalmgr;
		if(hiredate.equals(""))	 hiredate = originalhiredate;
		if(sal.equals("")) sal = originalsal;
		if(comm.equals(""))	 comm = originalcomm;
		if(deptno.equals("")) deptno = originaldeptno;
		
		sql = String.format(" UPDATE EMP "
				+ " SET ename = '%s' "
				+ " ,job   = '%s' "
				+ " ,mgr   =  %d "
				+ " ,hiredate   = '%s' "
				+ " ,sal   = %d "
				+ " ,comm   = %d "
				+ " ,deptno   = %d "
				+ " WHERE empno = %d ",ename,job,mgr,hiredate,sal,comm,deptno,empno);
//		System.out.println(sql);
		rowcount = stmt.executeUpdate(sql);
		if(rowcount == 1) {
			System.out.println(" 수정 해보아용 ");
		}
		stmt.close();
		rs.close();
		DBConn.close();				
		System.out.println(" end ");				
	}//main
}//class
