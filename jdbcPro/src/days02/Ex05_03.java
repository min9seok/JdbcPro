package days02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.util.DBConn;

import domain.DeptVO;

public class Ex05_03 {
	// days02.Ex02_03.java > PreparedStatement 수정
	public static void main(String[] args) throws Exception{				
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int deptno;
		String dname = null,loc = null;		
		String originaldname = null,originalloc=null; 
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
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
		pstmt = conn.prepareStatement(sql);			
		rs = pstmt.executeQuery();		
		if(rs.next()) {
			list = new ArrayList();
			originaldname = rs.getString("dname");				 
			originalloc = rs.getString("loc");
//			System.out.println(originaldname+originalloc);
		}else {
			System.out.println(" 부서 없는디 ");
			pstmt.close();
			rs.close();
		}				
//---------------------------------------------------------
		if(dname.equals("")) dname = originaldname;
		if(loc.equals(""))	 loc = originalloc;
		
		sql =     " UPDATE DEPT "
				+ " SET dname = ? "
				+ " ,loc   = ? "
				+ " WHERE deptno = ? ";
		pstmt2 = conn.prepareStatement(sql);		
		pstmt2.setString(1, dname);
		pstmt2.setString(2, loc);
		pstmt2.setInt(3, deptno);
		rowcount = pstmt2.executeUpdate();
		if(rowcount == 1) {
			System.out.println(" 수정 해보아용 ");
		}
		pstmt2.close();
		rs.close();
		DBConn.close();				
		System.out.println(" end ");		
	}//main

}//class
