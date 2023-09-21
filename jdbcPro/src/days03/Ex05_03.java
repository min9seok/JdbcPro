package days03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.util.DBConn;

import domain.DeptVO;

public class Ex05_03 {

	public static void main(String[] args) {
		// DEPT UPDATE		
		String sql = "{ call UP_UPDDEPT(?,?,?) }";
		Connection conn = null;
		CallableStatement cstmt = null;
		DeptVO vo = null;		
		int deptno;
		String dname="EQC",loc="ESEOUL";
		int rowcount = 0;
		try {
			conn = DBConn.getConnection();			
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, 50);
			cstmt.setString(2, dname);
			cstmt.setString(3, loc);
			rowcount = cstmt.executeUpdate();
			if(rowcount==1)System.out.println(" 부서 수정 해부럿다 ");
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

//CREATE OR REPlACE PROCEDURE up_upddept
//( pdeptno dept.deptno%type
// ,pdname dept.dname%type := NULL 
// ,ploc dept.loc%type := NULL 
//)
//is 
//odname dept.dname%type;
//oloc dept.loc%type;
//begin
//-- SELECT dname,loc INTO odname,oloc
//-- FROM dept
//-- WHERE deptno = pdeptno;
//-- IF pdname IS NULL THEN
//--   pdname := odname;
//-- END IF;
//-- IF ploc IS NULL THEN
//--   ploc := oloc;
//-- END IF;
//-- UPDATE DEPT
//-- SET dname = pdname
//--    ,loc   = ploc
//--WHERE deptno = pdeptno;
//UPDATE dept
//SET dname = nvl(pdname,dname)
//   ,loc   = nvl(ploc,loc)
//WHERE deptno = pdeptno;
// COMMIT;
//end;