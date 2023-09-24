package survey.persistence;

import java.util.ArrayList;

import survey.domain.SurveyDTO;


public interface SurveyDAO {

		// 1. 설문 목록 조회
		ArrayList<SurveyDTO> select(int currentPage, int numberPerPage) throws Exception;
		// 2. 설문 작성 
		int insert(SurveyDTO dto) throws Exception;
		
		// 3. 조회수 증가
		void increaseReaded(int seq) throws Exception;
		
		// 4. 설문 상세보기
		SurveyDTO view(int seq) throws Exception;
		
		// 5. 설문 삭제
		int delete(int seq) throws Exception;
		
		// 6. 설문 수정
		int update(SurveyDTO dto) throws Exception;

		// 7. 설문 검색
		ArrayList<SurveyDTO> search(int searchCondition, String searchWord) throws Exception;
		
		// 8-1. 총 레코드 수
		int gettotalRecords() throws Exception;
		// 8-2. 총 페이지 수 
		int gettotalPages(int numberPerPage) throws Exception;
}
