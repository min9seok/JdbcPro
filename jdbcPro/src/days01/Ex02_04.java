package days01;

import java.sql.Connection;
import java.sql.SQLException;

import com.util.DBConn;

public class Ex02_04 {

	public static void main(String[] args) throws Exception {
		
		
		 String url = "jdbc:oracle:thin:@192.168.10.176:1521:xe";
		 String user =  "SCOTT";		 
		 String password ="tiger";
				
		 
		Connection conn = DBConn.getConnection(url, user, password);
		
		System.out.println( conn.isClosed());
		
		DBConn.close();

		System.out.println(" end ");
	}//main

}//class
