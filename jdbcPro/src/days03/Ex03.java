package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import com.util.DBConn;

import oracle.jdbc.internal.OracleTypes;
import oracle.jdbc.oracore.OracleType;

public class Ex03 {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.print("> 아이디(empno) 를 입력하세용~");
		int pid = scanner.nextInt();

		String sql = "{ call UP_IDCHECK(?,?) }";
		Connection conn = null;
		CallableStatement cstmt = null;
		int idcheck = 0;		
		try {
			conn = DBConn.getConnection();			
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1,pid);
			//			cstmt.registerOutParameter(2, Types.INTEGER);
			cstmt.registerOutParameter(2, OracleTypes.INTEGER);
			cstmt.executeQuery();
			idcheck = cstmt.getInt(2);
			if( idcheck == 0 ) {
				System.out.println(" 사용 가능 ID 입니다");
			}else {
				System.out.println(" 이미 사용 중인 ID 입니다");
			}
		} catch (SQLException e) {		
			e.printStackTrace();
		}finally {
			try {
				cstmt.close();
				DBConn.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
		System.out.println(" end ");
	}//main

}//class

//CREATE OR REPlACE PROCEDURE up_idcheck
//( pid IN emp.empno%type 
// ,pcheck OUT NUMBER )
//is 
//begin
// SELECT count(*) INTO pcheck
// FROM emp
// WHERE empno = pid;
//end;
