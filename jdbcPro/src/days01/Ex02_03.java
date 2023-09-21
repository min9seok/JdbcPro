package days01;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;

public class Ex02_03 {

	public static void main(String[] args) {
		
		OracleDataSource ds;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user =  "SCOTT";
		String password ="tiger";
		Connection conn = null;
		try {
			ds = new OracleDataSource();
			ds.setURL(url);
			conn = ds.getConnection(user, password);
//			ds.setUser(user);
//			ds.setPassword(password);
			
			System.out.println( conn.isClosed());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}//main

}//class
