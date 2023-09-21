package days01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.util.DBConn;

import domain.DeptVO;
import domain.EmpVO;

public class Test01 {

	public static void main(String[] args) {
		// EmpVO 클래스 선언 
		// emp 테이블의 모든 사원 정보를 출력하는 코딩.
		// 조건) 부서번호를 입력받아서 
		//      해당 부서원들만 출력하는 코딩.
		// 조건) printEmpList ( ArrayList<EmpVO> list) 출력
		Connection conn = DBConn.getConnection();
		Statement stmt = null;	
		ResultSet rs = null;
		 int empno=0;
		 String ename="";
		 String job="";
		 int mgr=0;
		 String hiredate="";
		 int sal=0;
		 int comm=0;
		 int deptno=0;
		 String sql = "";
		 EmpVO vo;
		 Scanner sc = new Scanner(System.in);
		 System.out.print("부서번호를 입력하세요. ");
		 int input = sc.nextInt();
		 sql = "SELECT * FROM EMP WHERE deptno ="+input;
		 ArrayList<EmpVO> list = null;
			try {
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
			} catch (SQLException e) {			
				e.printStackTrace();
			}finally {
				try {
					stmt.close();
					rs.close();
					DBConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}			
			}
			System.out.println(" end ");

	}//main
	private static void printEmpList(ArrayList<EmpVO> list) {
		if( list == null) {
			System.out.println("> 사원 존재 노노염~~");
			return;
		}
		Iterator<EmpVO> ir = list.iterator();
		while (ir.hasNext()) {
			EmpVO vo = ir.next();
			System.out.println(vo);
			
		}
	}
}//class
