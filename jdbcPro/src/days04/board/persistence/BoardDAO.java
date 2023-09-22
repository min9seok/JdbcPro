package days04.board.persistence;

import java.util.ArrayList;

import days04.board.domain.BoardDTO;

public interface BoardDAO {
	
	// 1. 게시글 목록 조회 + 페이징 처리 X
	ArrayList<BoardDTO> select() throws Exception;
	// 9. 게시글 목록 조회 + 페이징 처리 O
	ArrayList<BoardDTO> select(int currentPage, int numberPerPage) throws Exception;
	// 2. 게시글 쓰기 
	int insert(BoardDTO dto) throws Exception;
	
	// 3. 조회수 증가
	void increaseReaded(int seq) throws Exception;
	
	// 4. 게시글 상세보기
	BoardDTO view(int seq) throws Exception;
	
	// 5. 게시글 삭제
	int delete(int seq) throws Exception;
	
	// 6. 게시글 수정
	int update(BoardDTO dto) throws Exception;

	// 7. 게시글 검색
	ArrayList<BoardDTO> search(int searchCondition, String searchWord) throws Exception;
	
	// 8-1. 총 레코드 수
	int gettotalRecords() throws Exception;
	// 8-2. 총 페이지 수 
	int gettotalPages(int numberPerPage) throws Exception;
	
	
}
