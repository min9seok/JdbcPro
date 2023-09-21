package days02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.util.DBConn;

import domain.EmpVO;

public class Test03 {

	public static void main(String[] args) throws SQLException {
		// emp 테이블에서 사원명(LIKE),부서번호(10),잡(LIKE) 검색 
		Scanner scanner = new Scanner(System.in);
		int searchCondition ;
		String searchWord;
		Connection conn;
		ResultSet rs;
		Statement stmt = null;
		int empno=0;
		 String ename="";
		 String job="";
		 int mgr=0;
		 String hiredate="";
		 int sal=0;
		 int comm=0;
		 int deptno=0;
		EmpVO vo;
		ArrayList<EmpVO> list = null;

		System.out.print("> 검색조건 입력 ? ");
		searchCondition = scanner.nextInt();
		System.out.print("> 검색어 입력 ? ");
		searchWord = scanner.next();
		String sql = " SELECT * "
				+ " FROM EMP "
				+ " WHERE ";
		if(searchCondition==1) {
			sql += String.format(" REGEXP_LIKE (ename,'%s','i') ", searchWord);
		}else if(searchCondition==2) {			
			sql += String.format(" deptno IN (%s) ", searchWord);
		}else if(searchCondition==3) {
			sql += String.format(" REGEXP_LIKE (job,'%s','i') ", searchWord);
		}
		System.out.println(sql);
				 conn = DBConn.getConnection();							
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
							sal = rs.getInt("sal");
							comm = rs.getInt("comm");
							deptno = rs.getInt("deptno");
							 vo = new EmpVO(empno, ename, job, mgr, hiredate, sal, comm, deptno);
							 list.add(vo);
						}while (rs.next()); 											
					}
					printEmpList(list);							
						stmt.close();
						rs.close();
						DBConn.close();				
				System.out.println(" end ");			
	}//main
	private static void printEmpList(ArrayList<EmpVO> list) {
		if( list == null) {
			System.out.println("> 사원 존재 노노염~~~~");
			return;
		}
		System.out.printf("> 검색 결과 : %d 개\n",list.size());
		Iterator<EmpVO> ir = list.iterator();
		while (ir.hasNext()) {
			EmpVO vo = ir.next();
			System.out.println(vo);
			
		}
	}

}//class
