package days02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.util.DBConn;

import domain.SalgradeVO;

public class Ex06 {

	public static void main(String[] args) throws Exception {
		// EmpDAIImpl pstmt 수정 후 ex04 확인 
	    // 2. 
		/*
		      [실행결과]
		      1등급   (     700~1200 ) - 2명
		            20   RESEARCH   7369   SMITH   800
		            30   SALES         7900   JAMES   950
		      2등급   (   1201~1400 ) - 2명
		         30   SALES   7654   MARTIN   2650
		         30   SALES   7521   WARD      1750   
		      3등급   (   1401~2000 ) - 2명
		         30   SALES   7499   ALLEN      1900
		         30   SALES   7844   TURNER   1500
		      4등급   (   2001~3000 ) - 4명
		          10   ACCOUNTING   7782   CLARK   2450
		         20   RESEARCH   7902   FORD   3000
		         20   RESEARCH   7566   JONES   2975
		         30   SALES   7698   BLAKE   2850
		      5등급   (   3001~9999 ) - 1명   
		         10   ACCOUNTING   7839   KING   5000
		*/ 
		String sql = "SELECT grade, losal, hisal ,count(*) cnt\r\n"
				+ "FROM salgrade s join emp e on sal between losal and hisal\r\n"
				+ "group by grade,losal,hisal\r\n"
				+ "order by grade";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
	}//main
}//class
