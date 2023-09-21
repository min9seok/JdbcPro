package days01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ex02 {

	public static void main(String[] args) {
//		1. JDBC driver 로딩
//		2. Connection DriverManager.getConnection()
//		3. Statement CRUD
//		4. Connection Close()
		
		
		
		String className = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user =  "SCOTT";
		String password ="tiger";
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(url,user,password);			
			// 3. Statement CRUD
			System.out.println( conn.isClosed() );			
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}//main

}//class
