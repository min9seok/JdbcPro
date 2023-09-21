package days03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.util.DBConn;

import domain.SalgradeVO;

public class Ex07 {

	public static void main(String[] args) {
		// 리플렉션 reflection - 반사, 상 , 반영
		// JDBC 리플렉션 - 결과물(rs)에 대한 정보를 추출해서 사용할 수 있는 기술

		String sql = "SELECT table_name "
				+ " FROM tabs ";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		ArrayList<String> tnlist = null;		
		String tablename;
		conn = DBConn.getConnection();
		//	 	1.
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				tnlist = new ArrayList();
				do {															
					tablename = rs.getString(1);
					tnlist.add(tablename);
				} while (rs.next());
				printTableName(tnlist);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		//		2.
		Scanner scanner = new Scanner(System.in);
		System.out.print(" 테이블명 입력 좀 해줭 ");
		tablename = scanner.next();
		sql = String.format("SELECT * FROM %s ", tablename);
		try {			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd =  rs.getMetaData();
			//			System.out.println(" 컬럼 수 : "+rsmd.getColumnCount());
			int columncount = rsmd.getColumnCount();
			//			for (int i = 1; i <= columncount; i++) {
			//				String columnname = rsmd.getColumnName(i);
			//				int columntype = rsmd.getColumnType(i);
			//				String columntpyename = rsmd.getColumnTypeName(i);
			//				int p = rsmd.getPrecision(i);
			//				int s = rsmd.getScale(i);
			//				System.out.println(columnname+ " / "+columntype+" / "+columntpyename+"("+p+","+s+")");
			//			}
			System.out.println("-".repeat(7*columncount));
			for (int i = 1; i <= columncount; i++) {
				System.out.printf("%s\t",rsmd.getColumnName(i));
			}
			System.out.println();
			System.out.println("-".repeat(7*columncount));

			if(rs.next()) {
				do {
					for (int i = 1; i <= columncount; i++) {
						int columntype = rsmd.getColumnType(i);
						int p = rsmd.getPrecision(i);
						int s = rsmd.getScale(i);
						if(columntype==2 && s== 0) {
							System.out.printf("%d / ",rs.getInt(i)); 
						}else if(columntype==2 && s != 0) { 
							System.out.printf("%.2f / ",rs.getDouble(i));
						}else if(columntype==12) { 
							System.out.printf("%s / ",rs.getString(i));
						}else if(columntype==93) { 
							System.out.printf("%tF / ",rs.getDate(i));
						}
					}
					System.out.println();
				} while (rs.next());
			}else {
				System.out.println(" 있는거 출력하슈 ");
			}

			System.out.println("-".repeat(7*columncount));
			//			if(rs.next()) {
			//				do {															
			//					
			//				} while (rs.next());				
			//			}
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

	private static void printTableName(ArrayList<String> tnlist) {
		System.out.println("[SCOTT 테이블 목록]");
		Iterator<String> ir = tnlist.iterator();
		int count=1;
		while (ir.hasNext()) {
			String tablename =  ir.next();
			System.out.printf("%d. %s    ",count,tablename);
			if(count % 5 == 0)System.out.println();
			count++;
		}		
		System.out.println();
	}
}//class
