package days04.board.persistence;

import java.util.ArrayList;

import days04.board.domain.BoardDTO;

public interface BoardDAO {
	
	// 1. 게시글 목록 조회
	ArrayList<BoardDTO> select() throws Exception;
	
	// 2. 게시글 쓰기 
	int insert(BoardDTO dto) throws Exception;
	
	// 3. 조회수 증가
	void increaseReaded(int seq) throws Exception;	
	// 4. 게시글 상세보기
	BoardDTO view(int seq) throws Exception;
	// 5. 게시글 삭제
	int delete(int seq) throws Exception;	
}
