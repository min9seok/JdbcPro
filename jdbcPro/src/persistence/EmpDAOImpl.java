package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.util.DBConn;

import domain.EmpVO;

public class EmpDAOImpl implements EmpDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;	
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public EmpDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}
	// 조회
	public ArrayList<EmpVO> getSelect() throws Exception{
		ArrayList<EmpVO> list = null;			
		String sql = "SELECT * "
				+ "FROM EMP ";			
		int empno;
		String ename;
		String job;
		int mgr;
		String hiredate;
		double sal;
		double comm;
		int deptno;
		EmpVO vo;						
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			list = new ArrayList();
			do {
				empno = rs.getInt("empno");
				ename = rs.getString("ename");
				job = rs.getString("job");
				mgr = rs.getInt("mgr");
				hiredate = rs.getString("hiredate");
				sal = rs.getDouble("sal");
				comm = rs.getDouble("comm");
				deptno = rs.getInt("deptno");
				vo = new EmpVO(empno, ename, job, mgr, hiredate, empno, mgr, deptno);
				list.add(vo);

			}while(rs.next());
		}//if				

		pstmt.close();
		rs.close();
		//		DBConn.close();	
		return list;		
	}
	// 검색
	public ArrayList<EmpVO> getSelect(int searchCondition, String searchWord) throws Exception{
		ArrayList<EmpVO> list = null;			
		String sql = "SELECT * "
				+ "FROM EMP "
				+ "WHERE ";	
		if(searchCondition==1) {
			sql += " REGEXP_LIKE (ename,?,'i') ";
		}else if(searchCondition==2) {			
			sql += " deptno IN ? ";
		}else if(searchCondition==3) {
			sql += " REGEXP_LIKE (job,?,'i') ";
		}
		int empno;
		String ename;
		String job;
		int mgr;
		String hiredate;
		double sal;
		double comm;
		int deptno;
		EmpVO vo;						
		pstmt = conn.prepareStatement(sql);
		if(searchCondition==1) {
			pstmt.setString(1, searchWord);
		}else if(searchCondition==2) {			
			pstmt.setString(1, searchWord);
		}else if(searchCondition==3) {
			pstmt.setString(1, searchWord);
		}
		rs = pstmt.executeQuery();
		if(rs.next()) {
			list = new ArrayList();
			do {
				empno = rs.getInt("empno");
				ename = rs.getString("ename");
				job = rs.getString("job");
				mgr = rs.getInt("mgr");
				hiredate = rs.getString("hiredate");
				sal = rs.getDouble("sal");
				comm = rs.getDouble("comm");
				deptno = rs.getInt("deptno");
				vo = new EmpVO(empno, ename, job, mgr, hiredate, empno, mgr, deptno);
				list.add(vo);

			}while(rs.next());
		}//if				

		pstmt.close();
		rs.close();
		//		DBConn.close();	
		return list;		
	}
	// 추가
	public int add(EmpVO vo) {
		int rowCount = 0;
		String sql = 
				"INSERT INTO emp ( empno, ename, job, mgr, hiredate, sal, comm, deptno ) "
						+ " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
		try {	         
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getEmpno());
			pstmt.setString(2, vo.getEname());
			pstmt.setString(3, vo.getJob());
			pstmt.setInt(4, vo.getMgr());
			pstmt.setInt(5, vo.getSal());
			pstmt.setInt(6, vo.getComm());
			pstmt.setInt(7, vo.getDeptno());			
			rowCount = pstmt.executeUpdate();	         
		} catch (SQLException e) { 
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) { 
				e.printStackTrace();
			}
		}
		return rowCount;		
	}
	// 수정
	public EmpVO get(int empno) throws Exception {		
		String sql = "SELECT * "
				+ "FROM EMP "
				+ "WHERE empno = ?";
		System.out.println(sql);
		String ename;
		String job;
		int mgr;
		String hiredate;
		int sal;
		int comm;
		int deptno;
		EmpVO vo = null;						
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,empno);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			empno = rs.getInt("empno");
			ename = rs.getString("ename");
			job = rs.getString("job");
			mgr = rs.getInt("mgr");
			hiredate = rs.getString("hiredate");
			sal = rs.getInt("sal");
			comm = rs.getInt("comm");
			deptno = rs.getInt("deptno");
			vo = new EmpVO(empno, ename, job, mgr, hiredate, sal, comm, deptno);								
		}//if				
		pstmt.close();
		rs.close();
		//		DBConn.close();	
		return vo;		
	}
	public int update(EmpVO vo) {
		int rowCount = 0;
		String sql = 
				          "UPDATE EMP "
						+ " SET ename = ? "
						+ ", job = ? "
						+ ", mgr = ? "						
						+ ", sal = ? "
						+ ", comm = ? "
						+ ", deptno = ? "						
						+ "WHERE empno = ? ";
		try {	         
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getEname());
			pstmt.setString(2, vo.getJob());
			pstmt.setInt(3, vo.getMgr());
			pstmt.setInt(4, vo.getSal());
			pstmt.setInt(5, vo.getComm());
			pstmt.setInt(6, vo.getDeptno());
			pstmt.setInt(7, vo.getEmpno());
			rowCount = pstmt.executeUpdate();	         
		} catch (SQLException e) { 
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) { 
				e.printStackTrace();
			}
		}
		return rowCount;		
	}
	// 삭제
	public int delete(int empno) {	
		int rowcount = 0;
		String sql = " DELETE FROM EMP  "				
				+ " WHERE empno = ?";		
		System.out.println(sql);								
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			rowcount = pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
		return rowcount;
	}


}
