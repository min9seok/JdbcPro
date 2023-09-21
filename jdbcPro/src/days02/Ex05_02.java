package days02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.util.DBConn;

import domain.DeptVO;

public class Ex05_02 {
	// days02.Ex02_02.java > PreparedStatement 수정
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		int searchCondition ;
		String searchWord;
		Connection conn;
		ResultSet rs;
		PreparedStatement pstmt = null;
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
			sql += " deptno = ? ";
		}else if(searchCondition==2) {
			//			sql += " REGEXP_LIKE (dname,?,'i') ";
			//			sql += " dname LIKE '%검색어%' ";
			sql += " dname LIKE ? ";
		}else if(searchCondition==3) {
			sql += " REGEXP_LIKE (loc,?,'i') ";
		}
		System.out.println(sql);
		conn = DBConn.getConnection();							
		pstmt = conn.prepareStatement(sql);
		if(searchCondition == 2) {
			pstmt.setString(1,"%"+searchWord.toUpperCase()+"%");
		}else{
			pstmt.setString(1,searchWord);
		}
		rs = pstmt.executeQuery();
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
		pstmt.close();
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
