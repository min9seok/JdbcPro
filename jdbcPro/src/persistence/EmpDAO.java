package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.util.DBConn;

import domain.EmpVO;

public interface EmpDAO {
	
	// 조회
	ArrayList<EmpVO> getSelect() throws Exception;
	// 검색
	ArrayList<EmpVO> getSelect(int searchCondition, String searchWord)throws Exception;
	// 추가
	 int add(EmpVO vo)throws Exception; 		
	// 수정
	 EmpVO get(int empno)throws Exception;
	 int update(EmpVO vo)throws Exception;
	// 삭제
	 int delete(int empno)throws Exception;


}
