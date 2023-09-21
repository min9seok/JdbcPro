package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.util.DBConn;

import oracle.jdbc.internal.OracleTypes;

public class Ex04 {

	public static void main(String[] args) {
		/*
		 * 1.  로그인 처리
		 *      아이디    : [    kenik    ]
		 *      비밀번호 : [    1234     ]
		 *      
		 *      [로그인]   [회원가입]
		 * 2.  up_logon
		 *     회원테이블 = 아이디(PK), 비밀번호 X
		 *     emp      = empno(PK), ename    
		 * 3.      로그인 성공 : 0
		 *          로그인 실패
		 *                 ㄴ 아이디 존재하지 않으면  : 1
		 *                 ㄴ 비밀번호 틀리면               : -1
		 * */
		Scanner scanner = new Scanner(System.in);

		System.out.print("> 아이디(empno)  비밀번호를 입력하세용~");
		int pid = scanner.nextInt();
		String ppw = scanner.next();

		String sql = "{ call UP_LOGON(?,?,?) }";
		Connection conn = null;
		CallableStatement cstmt = null;
		int logoncheck = 0;		
		try {
			conn = DBConn.getConnection();			
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1,pid);
			cstmt.setString(2,ppw);
			cstmt.registerOutParameter(3, OracleTypes.INTEGER);
			cstmt.executeQuery();
			logoncheck = cstmt.getInt(3);
			if( logoncheck == 0 ) {
				System.out.println(" 로그인 햇수다");
			}else if (logoncheck == 1) {
				System.out.println(" 로그인 실패넹 ID 확인좀");
			}else {
				System.out.println(" 로그인 실패넹 PW 확인좀");
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
//CREATE OR REPlACE PROCEDURE up_logon
//( pid IN emp.empno%type 
// ,ppw IN emp.ename%type 
// ,pcheck OUT NUMBER )
//is 
// vpw emp.ename%type;
//begin
// SELECT count(*) INTO pcheck
// FROM emp
// WHERE empno = pid;
// IF pcheck = 0 THEN -- ID존재여부
//   SELECT ename INTO vpw 
//   FROM emp
//   WHERE empno = pid;
//   IF vpw = ppw THEN 
//   pcheck := 0;
//   ELSE
//   pcheck := -1;
//   END IF;
// ELSE 
// pcheck := 1;
// END IF;
//end;