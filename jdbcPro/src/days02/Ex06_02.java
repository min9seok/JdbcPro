package days02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import com.util.DBConn;

import domain.SalgradeVO;

public class Ex06_02 {

	public static void main(String[] args) {
    
		//등급별 금액 인원수 출력
		String sql2 = "SELECT grade, losal, hisal "
			    +" ,(SELECT count(*) FROM emp WHERE sal between s.losal and s.hisal) cnt "
			    +" FROM salgrade s ";
		
		Connection con = null;
		
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null, rs2 = null;

		con = DBConn.getConnection();
		int  grade;
		int  losal;
		int  hisal;
		int  cnt;
		int deptno;
		String job;
		int empno;
		String ename;
		double sal;
		try {
			pstmt = con.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				do {					
					  grade = rs.getInt("grade");
					  losal = rs.getInt("losal");
					  hisal = rs.getInt("hisal");
					  cnt = rs.getInt("cnt");
					
					System.out.printf("%d등급 ( %d~%d ) - %d명\n"
									,grade, losal, hisal, cnt);
					
					//사원정보 출력 
					String sql3= "select deptno,empno,ename,job,sal "
						+ " from emp e join salgrade s on e.sal between s.losal and s.hisal "
						+ " where grade = ? " 
						+ " order by deptno";
					
					pstmt2 = con.prepareStatement(sql3);
					pstmt2.setInt(1,grade);
					 rs2 = pstmt2.executeQuery();
					 
					 while (rs2.next()) {
						 //d.deptno, dname,empno, ename, sal
						 deptno = rs2.getInt(1);						 
						 empno = rs2.getInt(2);
						 ename  = rs2.getString(3);
						 job = rs2.getString(4);
						 sal = rs2.getDouble(5);
						
						System.out.printf(" %d\t%d\t%s\t%s\t%.2f\n"
								, deptno,empno, ename,job, sal);
					}
				}while(rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				DBConn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			

		}
		
		System.out.println("end");
		
	}

}