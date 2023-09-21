package persistence;

import java.util.ArrayList;

import domain.BoardDTO;

public interface BoardDAO {
	
	// 1. 게시글 목록 조회
	ArrayList<BoardDTO> select() throws Exception;
	
	// 2. 게시글 쓰기 
	int insert(BoardDTO dto) throws Exception;
	
	
}
