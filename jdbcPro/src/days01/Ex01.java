package days01;

public class Ex01 {

	public static void main(String[] args) {
		
		// JAVA <- JDBC 드라이버 -> Oracle, MYSQL , MSSQL 등등
//		(J)AVA + (D)ata(B)ase (C)onnectivity
//		자바의 DB연결 및 CRUD 작업을 하기 위한 표준 인터페이스
//		오라클사 + JDBC => 구현한 클래스 == JDBC 드라이버
//		11g XE  ojdbc6.jar + JDK 1.6 이상
//		MS사   + JDBC => 구현한 클래스 == JDBC 드라이버
		
//		[JDBC 드라이버 종류]
//		1) Type1 ODBC
//      2) Type2  C,C++ 언어로 만든 라이브러리를 사용해서 DB연동
//      3) Type3 미들웨어 서버 
//      *** 4) Type4 : Thin 드라이버, 순수 자바, 가장 많이 사용
		
		// [ DBMS와 연결 방법 ]
//		1. JDBC 드라이버 로딩 - Class.forName() 메서드
//		2. Connection 얻어오는 작업 -  Driver[Manager] 클래스 getConnection() 메서드
//		3. 작업자(Statment) - CRUD 필요한 작업
//		  ㄴ Statment 객체
//		  ㄴ [prepared]Statement 객체 : 성능이 빠르다
//		  ㄴ [Callable]Statement 객체 : 저장 프로시저 , 저장 함수
//		4. DB연결종료 - Connection 객체 Close





	}//main

}//class
