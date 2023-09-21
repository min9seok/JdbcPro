package days03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.util.DBConn;

import domain.SalgradeVO;

public class Ex01 {

	public static void main(String[] args) {

		String sql = "SELECT grade, losal, hisal ,count(*) cnt "
				+ "FROM salgrade s join emp e on sal between losal and hisal "
				+ "group by grade,losal,hisal "
				+ "order by grade";

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		ArrayList<SalgradeVO> list = null;			
		SalgradeVO vo = null;
		conn = DBConn.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list = new ArrayList();
				do {															
					vo = new SalgradeVO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
					list.add(vo);
				} while (rs.next());
				printSalgrade(list);
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