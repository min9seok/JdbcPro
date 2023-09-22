package days04;

import java.sql.Connection;

import com.util.DBConn;

import days04.board.controller.BoardController;
import days04.board.persistence.BoardDAO;
import days04.board.persistence.BoardDAOImpl;
import days04.board.service.BoardService;

//게시판
//모델1방식 - 글쓰기,목록,수정,삭제 > 로직 처리 
//모델2방식(MVC)  
//view > 글쓰기(컨트롤러) > 서비스 > DAO > DB처리 > 서비스 > 글쓰기(컨트롤러) > view
public class Ex01 {

	public static void main(String[] args) {		 
//		1. 테이블 생성 , 시퀀스 생성
//		2. domain.boardDTO 생성
//		3. persistence.BoardDAO.java
//		   persistence.BoardDAOImpl.java
//		4. 단위 테스트 select(), insert()
//		   days04.test.BoardDAOImplTest.java
//		5. 서비스 - 트랜잭션 처리 
//		 하나의 게시글 보기
//		     ㄴ 1) 로그 기록 
//           ㄴ 2) 조회수 증가
//           ㄴ 3) 게시글 정보
//		6. 단위테스트 BoardServiceTest.jva
//		7. days04.board.controller.BoardController.java
//		   입력 / 출력 (View)
//		8. days04.Ex01.java 테스트
		Connection conn = DBConn.getConnection();
		BoardDAO dao = new BoardDAOImpl(conn);
		BoardService service = new BoardService(dao);
		BoardController controller = new BoardController(service);
		
		controller.boardStart();
//		9. 목록, 새글쓰기, 상세보기
//		10. 삭제
//		11. 수정, 페이징처리 , 검색
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
