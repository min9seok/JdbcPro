package days01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ex02_02 {

	public static void main(String[] args) throws Exception {
		String className = "oracle.jdbc.driver.OracleDriver"; 
		String url = "jdbc:oracle:thin:scott/tiger@localhost:1521:xe";		
		Connection con = null;
		Class.forName(className);
		con = DriverManager.getConnection(url);		
		System.out.println( con.isClosed() );
		con.close();
	}//main

}//class
