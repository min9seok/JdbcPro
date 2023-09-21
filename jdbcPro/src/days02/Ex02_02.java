package days02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.util.DBConn;

import domain.DeptVO;

public class Ex02_02 {
	public static void main(String[] args) throws Exception {
		// 부서 정보 수정 + 삭제
//		1. deptno , 2. dname , 3. loc 검색
		Scanner scanner = new Scanner(System.in);
		int searchCondition ;
		String searchWord;
		Connection conn;
		ResultSet rs;
		Statement stmt = null;
		int deptno;
		String dname;
		String loc;
		DeptVO vo;
		ArrayList<DeptVO> list = null;
		
		
		
		
		System.out.print("> 검색조건 입력 ? ");
		searchCondition = scanner.nextInt();
		System.out.print("> 검색어 입력 ? ");
		searchWord = scanner.next();
		String sql = " SELECT * "
				+ " FROM DEPT "
				+ " WHERE ";
		if(searchCondition==1) {
			sql += String.format(" deptno IN (%s) ", searchWord);
		}else if(searchCondition==2) {
			sql += String.format(" REGEXP_LIKE (dname,'%s','i') ", searchWord);
		}else if(searchCondition==3) {
			sql += String.format(" REGEXP_LIKE (loc,'%s','i') ", searchWord);
		}
		System.out.println(sql);
				 conn = DBConn.getConnection();							
					stmt = conn.createStatement();					
					rs = stmt.executeQuery(sql);
					if(rs.next()) {
						list = new ArrayList();				
						do {
							deptno = rs.getInt("deptno");
							 dname = rs.getString("dname");
							 if(searchCondition==2) {
								 searchWord = searchWord.toUpperCase();
								dname = dname.replaceAll(searchWord,"["+searchWord+"]");
							 }
							 loc = rs.getString("loc");
							 vo = new DeptVO(deptno, dname, loc);
							 list.add(vo);
						}while (rs.next()); 											
					}
					printDeptList(list);							
						stmt.close();
						rs.close();
						DBConn.close();				
				System.out.println(" end ");			
	}//main

	private static void printDeptList(ArrayList<DeptVO> list) {
		if( list == null) {
			System.out.println("> 부서 존재 노노염~~");
			return;
		}
		System.out.printf("> 검색 결과 : %d 개\n",list.size());
		Iterator<DeptVO> ir = list.iterator();
		while (ir.hasNext()) {
			DeptVO vo = ir.next();
			System.out.println(vo);
			
		}
	}
}//class
