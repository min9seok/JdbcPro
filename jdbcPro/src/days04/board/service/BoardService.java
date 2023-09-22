package days04.board.service;

import java.sql.SQLException;
import java.util.ArrayList;

import days04.board.domain.BoardDTO;
import days04.board.persistence.BoardDAO;
import days04.board.persistence.BoardDAOImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// 사용자 -> BoardController -> dto -> BoardService -> dto -> BoardDAOImpl > DB
@Data
@AllArgsConstructor
@Builder
public class BoardService {
	private BoardDAO dao = null;

	// 1. 게시글 목록 조회
	public ArrayList<BoardDTO> selectService(){
		ArrayList<BoardDTO> list = null;

		// 1) 로그기록
		System.out.println("> 게시글 목록 조회 -> 로그 기록 작업 ");
		// 2)
		try {
			list = this.dao.select();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	// 2. 게시글 쓰기 
	public int insertService(BoardDTO dto) {
		int rowCount = 0;

		// 1) 로그기록
		System.out.println("> 게시글 쓰기 -> 로그 기록 작업 ");
		// 2)
		try {
			rowCount = this.dao.insert(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rowCount;
	}
	// 3. 게시글 상세보기 서비스 	
	// ㄱ+ㄴ 트랙잭션 처리
	public BoardDTO viewService(int seq) {
		BoardDTO dto = null;

		try {
			((BoardDAOImpl)this.dao).getConn().setAutoCommit(false);
			// ㄱ. 해당 게시글의 조회수 1증가
			this.dao.increaseReaded(seq);
			// ㄴ. 해당 게시글 정보 조회
			dto = this.dao.view(seq);
			// ㄷ. 로그기록
			System.out.println("> 게시글 상세 보기 -> 로그 기록 작업 ");
			((BoardDAOImpl)this.dao).getConn().commit();
		} catch (Exception e) {
			try {
				((BoardDAOImpl)this.dao).getConn().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return dto;
	}

	public int deleteService(int seq) {
		int rowCount = 0;		
		try {
			((BoardDAOImpl)this.dao).getConn().setAutoCommit(false);
			rowCount = this.dao.delete(seq);
			if(rowCount==1)System.out.println("> 게시글 삭제 -> 로그 기록 작업 ");			
			((BoardDAOImpl)this.dao).getConn().commit();
		} catch (Exception e) {	
			try {
				((BoardDAOImpl)this.dao).getConn().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return rowCount;

	}

	public int updateService(BoardDTO dto) {
		int rowCount = 0;	
		try {			
			rowCount = this.dao.update(dto);
			if(rowCount==1)System.out.println("> 게시글 수정 -> 로그 기록 작업 ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rowCount;
	}

	public ArrayList<BoardDTO> searchService(int searchCondition, String searchWord) {
		ArrayList<BoardDTO> list = null;

		// 1) 로그기록
		System.out.println("> 게시글 목록 검색 -> 로그 기록 작업 ");
		// 2)
		try {
			list = this.dao.search(searchCondition,searchWord);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<BoardDTO> selectService(int currentPage, int numberPerPage) {
		ArrayList<BoardDTO> list = null;

		// 1) 로그기록
		System.out.println("> 게시글 목록 조회 -> 로그 기록 작업 ");
		// 2)
		try {
			list = this.dao.select(currentPage,numberPerPage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public String pageService(int currentPage, int numberPerPage, int numberOfPageBlock) {
		String pagingBlock = "\t\t\t";
		int totalPages;
		try {
			totalPages = this.dao.gettotalPages(numberPerPage);
			int start = (currentPage -1) /numberOfPageBlock * numberOfPageBlock +1 ;
	        int end= start + numberOfPageBlock -1;         
	        end =   end > totalPages ? totalPages : end;

	        if( start != 1 ) pagingBlock +=" < ";          
	        for (int j = start; j <= end; j++) {
	        	pagingBlock += String.format(currentPage==j?"[%d] " : "%d " , j);
	        }         
	        if( end != totalPages ) pagingBlock +=" > ";
		} catch (Exception e) {		
			e.printStackTrace();
		}
		
		
		return pagingBlock;
	}

}
