package days01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import com.util.DBConn;

import domain.DeptVO;

public class Ex03 {

	public static void main(String[] args) {
		// Scott.DEPT 테이블 - CRUD
		// domain.DeptVO
		// [ dept 테이블 조회 ]
		Connection conn = DBConn.getConnection();
		Statement stmt = null;	
		ResultSet rs = null;
		int deptno = 0;
		String dname = "";
		String loc = "";
		DeptVO vo;
		ArrayList<DeptVO> list = null;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM DEPT";
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				list = new ArrayList();				
				do {
					deptno = rs.getInt("deptno");
					 dname = rs.getString("dname");
					 loc = rs.getString("loc");
					 vo = new DeptVO(deptno, dname, loc);
					 list.add(vo);
				}while (rs.next()); 											
			}
			printDeptList(list);
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

	private static void printDeptList(ArrayList<DeptVO> list) {
		if( list == null) {
			System.out.println("> 부서 존재 노노염~~");
			return;
		}
		Iterator<DeptVO> ir = list.iterator();
		while (ir.hasNext()) {
			DeptVO vo = ir.next();
			System.out.println(vo);
			
		}
	}

}//class
