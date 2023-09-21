package days02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;

import com.util.DBConn;

public class Ex02 {

	public static void main(String[] args) throws Exception {
		// 1. dept 부서정보 조회  days01.Ex03.java
		// 2. dept 부서추가  auto commit
		int deptno;
		String dname;
		String loc;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("1. 부서번호 입력 ? ");
		deptno = Integer.parseInt(br.readLine());
		System.out.print("2. 부서명 입력 ? ");
		dname = br.readLine();
		System.out.print("3. 지역명 입력 ? ");
		loc = br.readLine();
		int rowcount;
		String sql = String.format("INSERT INTO DEPT(deptno,dname,loc)values(%d,'%s','%s')",deptno,dname,loc);
		
		Connection con;
		
		Statement stmt;
		
		con = DBConn.getConnection();
		stmt = con.createStatement();
		rowcount = stmt.executeUpdate(sql);
		if(rowcount==1) {
			System.out.println("> 부서 추가 함");
		}
		
		stmt.close();
		con.close();
		DBConn.close();
		System.out.println(" end ");
	}//main

}//class
