package survey.service;

import java.sql.SQLException;
import java.util.ArrayList;

import days04.board.domain.BoardDTO;
import survey.domain.SurveyDTO;
import survey.persistence.SurveyDAOImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import survey.persistence.SurveyDAO;

@Data
@AllArgsConstructor
@Builder
public class SurveyService {
	private SurveyDAO dao = null;
	
//	public ArrayList<SurveyDTO> selectService(){
//		ArrayList<SurveyDTO> list = null;
//
//		// 1) 로그기록
//		System.out.println("> 게시글 목록 조회 -> 로그 기록 작업 ");
//		// 2)
//		try {
//			list = this.dao.select();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return list;
//	}

	public int insertService(SurveyDTO dto) {
		int rowCount = 0;

		try {
			rowCount = this.dao.insert(dto);
			if(rowCount==1) {
				System.out.println("> 설문 작성 -> 로그 기록 작업 ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rowCount;
	}

	public SurveyDTO viewService(int seq) {
		SurveyDTO dto = null;	

		try {
			((SurveyDAOImpl)this.dao).getConn().setAutoCommit(false);
			// ㄱ. 해당 게시글의 조회수 1증가
			this.dao.increaseReaded(seq);
			// ㄴ. 해당 게시글 정보 조회
			dto = this.dao.view(seq);
			// ㄷ. 로그기록
			System.out.println("> 설문 상세 보기 -> 로그 기록 작업 ");
			((SurveyDAOImpl)this.dao).getConn().commit();
		} catch (Exception e) {
			try {
				((SurveyDAOImpl)this.dao).getConn().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return dto;
	}

	public int deleteService(int seq) {
		int rowCount = 0;		
		try {
			((SurveyDAOImpl)this.dao).getConn().setAutoCommit(false);
			rowCount = this.dao.delete(seq);
			if(rowCount==1)System.out.println("> 설문 삭제 -> 로그 기록 작업 ");			
			((SurveyDAOImpl)this.dao).getConn().commit();
		} catch (Exception e) {	
			try {
				((SurveyDAOImpl)this.dao).getConn().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return rowCount;

	}

	public int updateService(SurveyDTO dto) {
		int rowCount = 0;	
		try {			
			rowCount = this.dao.update(dto);
			if(rowCount==1)System.out.println("> 설문 수정 -> 로그 기록 작업 ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rowCount;
	}

	public ArrayList<SurveyDTO> searchService(int searchCondition, String searchWord) {
		ArrayList<SurveyDTO> list = null;

		// 1) 로그기록
		System.out.println("> 설문 목록 검색 -> 로그 기록 작업 ");
		// 2)
		try {
			list = this.dao.search(searchCondition,searchWord);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<SurveyDTO> selectService(int currentPage, int numberPerPage) {
		ArrayList<SurveyDTO> list = null;

		// 1) 로그기록
		System.out.println("> 설문 목록 조회 -> 로그 기록 작업 ");
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
