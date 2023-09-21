package days04.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import com.util.DBConn;

import domain.BoardDTO;
import persistence.BoardDAOImpl;

class BoardDAOImplTest {

	@Test
	void selectTest() {
		
		Connection conn = DBConn.getConnection();				
		BoardDAOImpl dao = new BoardDAOImpl(conn);
//		try {
//			
//			ArrayList<BoardDTO> list = dao.select();
//			if(list == null) {
//				System.out.println(" 게시글 좀 작성하고와 ");
//				return;
//			}
//			Iterator<BoardDTO> ir = list.iterator();
//			while (ir.hasNext()) {
//				BoardDTO dto = ir.next();
//				System.out.println(dto);				
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			DBConn.close();	
//		}
		try {
			BoardDTO dto = BoardDTO.builder()
					.writer("홍길동").pwd("1234").email("hong@naver.com").title("첫 번째 게시글")
					.tag(0).content("첫 번째 게시글 내용").build();
			int rowcount = dao.insert(dto);
			ArrayList<BoardDTO> list = dao.select();
			if(rowcount == 1) {
				System.out.println(" 드디어 작성 ");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBConn.close();	
		}
		
			
		
	}

}
