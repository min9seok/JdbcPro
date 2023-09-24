package survey.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import survey.domain.SurveyDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import survey.domain.SurveyDTO;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SurveyDAOImpl implements SurveyDAO{
	private Connection conn = null;	
	private PreparedStatement pstmt = null;
	@Override
	public ArrayList<SurveyDTO> select(int currentPage, int numberPerPage) throws Exception {
		int begin = (currentPage-1)*numberPerPage+1;
		int end = begin+numberPerPage-1;
		String sql = "SELECT * "
				+ "FROM ( "
				+ "        SELECT ROWNUM no, t.* "
				+ "        FROM (  "
				+ "                SELECT seq,question,start_date,end_date,user_name,readed "
//				+ " , CASE WHEN SYSDATE < start_date THEN '예정' "
//				+ "           WHEN SYSDATE BETWEEN start_date AND end_date THEN '진행중' "
//				+ "           ELSE '종료' "
//				+ "      END 현황 "
				+ "                FROM tbl_survey "
				+ "                ORDER BY seq DESC "
				+ "        ) t "
				+ ")  b "
				+ "WHERE b.no BETWEEN ? AND ? ";		
		ResultSet rs = null;		
		ArrayList<SurveyDTO> list = null;			
		SurveyDTO vo = null;		
		int seq;
		String question;					
		String user_name;
		Date start_date;
		Date end_date;
		int readed;					
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,begin);
			pstmt.setInt(2,end);;
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list = new ArrayList();
				do {	
					seq = rs.getInt("seq");
					question = rs.getString("question");
					user_name = rs.getString("user_name");
					start_date = rs.getDate("start_date");
					end_date = rs.getDate("end_date");
					readed = rs.getInt("readed");
					vo = SurveyDTO.builder()
							.seq(seq)							
							.question(question)
							.user_name(user_name)
							.start_date(start_date)
							.end_date(end_date)
							.readed(readed).build();
					list.add(vo);
				} while (rs.next());			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return list;		
	
	}
	@Override
	public int insert(SurveyDTO dto) throws Exception {
		int rowCount = 0;
		String sql = 
				"INSERT INTO tbl_survey ( seq, user_name, regdate, start_date, end_date, question, cate_count ) "
						+ " VALUES (SEQ_TBL_SURVEY.NEXTVAL,? , SYSDATE, SYSDATE, SYSDATE, ?, ?)";		
		pstmt = this.conn.prepareStatement(sql);	
		pstmt.setString(1,  dto.getUser_name() );		
		pstmt.setString(2, dto.getQuestion());
		pstmt.setInt(3,  dto.getCate_count() );
		rowCount = pstmt.executeUpdate();	         		
		pstmt.close();			
		return rowCount;				
	}
	@Override
	public void increaseReaded(int seq) throws Exception {

		
	}
	@Override
	public SurveyDTO view(int seq) throws Exception {
//		String sql = "SELECT s.seq,user_name,readed,question,start_date,end_date,cate_content "
//				+ "FROM tbl_survey s JOIN tbl_cate c on s.seq = c.seq "
//				+ "WHERE s.seq = ? ";
		String sql = "SELECT seq,user_name,readed,question,start_date,end_date,cate_count "
				+ "FROM tbl_survey   "
				+ "WHERE seq = ? ";
		System.out.println(sql);
		ResultSet rs = null;
		pstmt = this.conn.prepareStatement(sql);					
		pstmt.setInt(1,seq);		
		SurveyDTO dto = null;
		rs = pstmt.executeQuery();		
		if(rs.next()) {
			dto = SurveyDTO.builder()
					.seq(seq)										
					.user_name(rs.getString("user_name"))
					.question(rs.getString("question"))
					.start_date(rs.getDate("start_date"))
					.end_date(rs.getDate("end_date"))
					.cate_count(rs.getInt("cate_count"))
					.readed(rs.getInt("readed"))
					.build();			
		}
		rs.close();
		pstmt.close();
		return dto;

	}
	@Override
	public int delete(int seq) throws Exception {
		int rowCount = 0;
		String sql = "DELETE FROM tbl_survey "				
				+ "WHERE seq = ? ";
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1,seq);
		rowCount = pstmt.executeUpdate();
		pstmt.close();
		return rowCount;
		
	}
	@Override
	public int update(SurveyDTO dto) throws Exception {
		int rowCount =0;		
		String sql = "UPDATE tbl_survey "
				+ "SET question = ? "
				+ "WHERE seq = ? ";
		System.out.println(sql);
		PreparedStatement pstmt = this.conn.prepareStatement(sql);
		pstmt.setString(1, dto.getQuestion());		
		pstmt.setInt(2, dto.getSeq());

		rowCount = pstmt.executeUpdate();

		pstmt.close();
		return rowCount;
	}
	@Override
	public ArrayList<SurveyDTO> search(int searchCondition, String searchWord) throws Exception {

		String sql = "SELECT seq,question,start_date,end_date,user_name,readed "
				+ "FROM tbl_survey ";
		switch (searchCondition) {
		case 1: //제목
			sql += "WHERE REGEXP_LIKE(question,?,'i') ";	
			break;
		case 2: //작성자
			sql += "WHERE REGEXP_LIKE(user_name,?,'i') ";
			break;	
		}
		sql+= "order by seq desc ";		
		System.out.println(sql);
		ResultSet rs = null;		
		ArrayList<SurveyDTO> list = null;			
		SurveyDTO vo = null;		
		int seq;
		String question;					
		String user_name;
		Date start_date;
		Date end_date;
		int readed;								
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,searchWord);
			if(searchCondition==4) {			
				pstmt.setString(2,searchWord);
			}
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list = new ArrayList();
				do {	
					seq = rs.getInt("seq");
					question = rs.getString("question");
					user_name = rs.getString("user_name");
					start_date = rs.getDate("start_date");
					end_date = rs.getDate("end_date");
					readed = rs.getInt("readed");
					vo = SurveyDTO.builder()
							.seq(seq)							
							.question(question)
							.user_name(user_name)
							.start_date(start_date)
							.end_date(end_date)
							.readed(readed).build();
					list.add(vo);
				} while (rs.next());			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return list;
	}
	
	@Override
	public int gettotalRecords() throws Exception {
		int totalRecords = 0;
		String sql = "SELECT COUNT(*) FROM tbl_survey ";										
		pstmt = this.conn.prepareStatement(sql);
		ResultSet rs = null;
		rs = pstmt.executeQuery();
		if(rs.next())totalRecords = rs.getInt(1);
		rs.close();
		pstmt.close();
		return totalRecords;
	}

	@Override
	public int gettotalPages(int numberPerPage) throws Exception {
		int totalPages = 0;
		String sql = "SELECT CEIL(COUNT(*)/?) "
				+ "FROM tbl_survey ";								
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1,numberPerPage);
		ResultSet rs = null;
		rs = pstmt.executeQuery();
		if(rs.next())totalPages = rs.getInt(1);
		rs.close();
		pstmt.close();
		return totalPages;
		
	}
	
	
	
}
