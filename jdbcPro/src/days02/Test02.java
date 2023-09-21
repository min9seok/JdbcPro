package days02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Scanner;

import com.util.DBConn;

import domain.EmpVO;

public class Test02 {

	public static void main(String[] args) throws Exception {
		//emp 테이블에서 한 사원의 정보를 입력받아서 추가 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Connection conn = DBConn.getConnection();
		Statement stmt = null;	
		ResultSet rs = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String strDate = dateFormat.format(Calendar.getInstance().getTime());
		 int empno=0;
		 String ename="";
		 String job="";
		 int mgr=0;
		 String hiredate="";
		 int sal=0;
		 int comm=0;
		 int deptno=0;
		 String sql = "";
		 int rowcount; 

		 System.out.print("사원정보를 입력하세요. ");
		 empno = Integer.parseInt(br.readLine());		 
		 ename = br.readLine();
		 job = br.readLine();
		 mgr = Integer.parseInt(br.readLine());		 
		 hiredate = br.readLine();
		 sal = Integer.parseInt(br.readLine());
		 comm = Integer.parseInt(br.readLine());		
		 deptno = Integer.parseInt(br.readLine());		 
		 sql = String.format("INSERT INTO EMP values(%d,'%s','%s',%d,'%s',%d,%d,%d)"
				 ,empno,ename,job,mgr,hiredate,sal,comm,deptno);			
			conn = DBConn.getConnection();
			stmt = conn.createStatement();
			rowcount = stmt.executeUpdate(sql);
			if(rowcount==1) {
				System.out.println("> 부서 추가 함");
			}			
			stmt.close();
			conn.close();
			DBConn.close();
			System.out.println(" end ");			

	}//main
}//class
