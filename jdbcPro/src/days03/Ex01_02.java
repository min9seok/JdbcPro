package days03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

import com.util.DBConn;

import domain.DeptEmpVO;
import domain.SalgradeVO;

public class Ex01_02 {

	public static void main(String[] args) {

		String sql = "SELECT grade, losal, hisal ,count(*) cnt "
				+ "FROM salgrade s join emp e on sal between losal and hisal "
				+ "group by grade,losal,hisal "
				+ "order by grade";
		String empsql ="SELECT d.deptno, dname, empno, ename, sal "
				+ "From dept d right join emp e on d.deptno = e.deptno "
				+ "            JOIN salgrade s on sal between losal and hisal "
				+ "WHERE grade = ? "
				+ "order by d.deptno ";
		Connection conn = null;
		ResultSet rs = null, emprs = null;
		PreparedStatement pstmt = null, emppstmt = null;
		LinkedHashMap<SalgradeVO, ArrayList<DeptEmpVO>> map = new LinkedHashMap();			
		SalgradeVO vo = null;
		ArrayList<DeptEmpVO> emplist = null;			
		DeptEmpVO empvo = null;
		conn = DBConn.getConnection();
		int deptno;
		String dname;
		int empno;
		String ename;
		double sal;
		int grade;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {				
				do {			
					grade = rs.getInt(1);
					vo = new SalgradeVO(grade, rs.getInt(2), rs.getInt(3), rs.getInt(4));

					emppstmt = conn.prepareStatement(empsql);
					emppstmt.setInt(1, grade);
					emprs = emppstmt.executeQuery();
					if(emprs.next()) {
						emplist = new ArrayList();
						do {
							deptno = emprs.getInt(1);
							dname  = emprs.getString(2);
							empno  = emprs.getInt(3);
							ename  = emprs.getString(4);
							sal    = emprs.getDouble(5);							 
							empvo = new DeptEmpVO(deptno, dname, empno, ename, sal);
							emplist.add(empvo);
						} while (emprs.next());
					}
					map.put(vo, emplist);
					emprs.close();
					emppstmt.close();
				} while (rs.next());
				printSalgrade(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();			
				pstmt.close();				
				DBConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(" end ");
	}//main

	private static void printSalgrade(LinkedHashMap<SalgradeVO, ArrayList<DeptEmpVO>> map) {
		Set<Entry<SalgradeVO, ArrayList<DeptEmpVO>>> set = map.entrySet();
		Iterator<Entry<SalgradeVO, ArrayList<DeptEmpVO>>> ir = set.iterator();
		while (ir.hasNext()) {
			Entry<SalgradeVO,ArrayList<DeptEmpVO>> entry = ir.next();
			SalgradeVO vo = entry.getKey();
			ArrayList<DeptEmpVO> list = entry.getValue();

			System.out.printf("%d등급   ( %d~%d ) - %d명\n"
					,vo.getGrade(),vo.getLosal(),vo.getHisal(),vo.getCnt());
			//			20   RESEARCH   7369   SMITH   800
			Iterator<DeptEmpVO> ir2 = list.iterator();
			while (ir2.hasNext()) {
				DeptEmpVO empvo = ir2.next();
				System.out.printf(" %d\t%s\t%d\t%s\t%.2f\n"
						,empvo.getDeptno(),empvo.getDname(),empvo.getEmpno(),empvo.getEname(),empvo.getSal());
			}
		}

	}

	private static void printSalgrade(ArrayList<SalgradeVO> list) {
		Iterator<SalgradeVO> ir =	list.iterator();
		while (ir.hasNext()) {
			SalgradeVO vo = ir.next();
			System.out.printf("%d등급   ( %d~%d ) - %d명\n"
					,vo.getGrade(),vo.getLosal(),vo.getHisal(),vo.getCnt());
		}		
	}

}//class

//SalgradeVO vo = new SalgradeVO();
//vo.setGrade(1);
//vo.setLosal(700);
//vo.setHisal(1200);
//vo.setCnt(2);
//SalgradeVO vo = SalgradeVO.builder().grade(1).losal(700).hisal(1200).cnt(2).build();