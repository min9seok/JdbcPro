package days04;
//게시판
//모델1방식 - 글쓰기,목록,수정,삭제 > 로직 처리 
//모델2방식(MVC)  
//view > 글쓰기(컨트롤러) > 서비스 > DAO > DB처리 > 서비스 > 글쓰기(컨트롤러) > view
public class Ex01 {

	public static void main(String[] args) {		 
//		1. 테이블 생성 , 시퀀스 생성
//		2. boardDTO 생성
//		3. persistence.BoardDAO.java
//		   persistence.BoardDAOImpl.java
		
	}//main
}//class



//CREATE TABLE tbl_cstVSBoard (
//		  seq NUMBER not null primary key ,
//		  writer varchar2 (20) not null ,
//		  pwd varchar2 (20) not null ,
//		  email varchar2 (100) ,
//		  title varchar2 (200) not null ,
//		  writedate DATE Default (SYSDATE),
//		  readed NUMBER DEFAULT (0),
//		  tag NUMBER(1) DEFAULT (0),
//		  content CLOB 
//		)
