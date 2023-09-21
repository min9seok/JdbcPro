package days02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.util.DBConn;

import domain.DeptVO;

public class Ex02_03 {

	public static void main(String[] args) throws Exception{
		// 부서정보 수정
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int deptno;
		String dname = null,loc = null;		
		String originaldname = null,originalloc=null; 
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql;
		int rowcount;
		DeptVO vo = null;
		ArrayList<DeptVO> list = null;
		System.out.print("> 수정할 부서번호를 입력 ? ");
		deptno = Integer.parseInt(br.readLine());
		System.out.print("> 수정할 부서명를 입력 ? ");
		dname = br.readLine();
		System.out.print("> 수정할 지역명를 입력 ? ");
		loc = br.readLine();
		
		sql = String.format("SELECT * FROM DEPT WHERE deptno = %d ",deptno);
		System.out.println(sql);
		conn = DBConn.getConnection();	
		stmt = conn.createStatement();			
		rs = stmt.executeQuery(sql);		
		if(rs.next()) {
			list = new ArrayList();
			originaldname = rs.getString("dname");				 
			originalloc = rs.getString("loc");
			System.out.println(originaldname+originalloc);
		}else {
			System.out.println(" 부서 없는디 ");
			stmt.close();
			rs.close();
		}				
//---------------------------------------------------------
		if(dname.equals("")) dname = originaldname;
		if(loc.equals(""))	 loc = originalloc;
		
		sql = String.format(" UPDATE DEPT "
				+ " SET dname = '%s' "
				+ " ,loc   = '%s' "
				+ " WHERE deptno = %d ",dname,loc,deptno);
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
