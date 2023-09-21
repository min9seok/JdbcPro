package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.util.DBConn;

import domain.BoardDTO;
import domain.SalgradeVO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class BoardDAOImpl implements BoardDAO {

	private Connection conn = null;	
//	private PreparedStatement pstmt = null;

	@Override
	public ArrayList<BoardDTO> select() throws Exception {
		String sql = "SELECT seq, title,writer, email, writedate, readed "
				+ "FROM tbl_cstvsboard "				
				+ "order by seq desc ";		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		ArrayList<BoardDTO> list = null;			
		BoardDTO vo = null;		
		int seq;
		String writer;			
		String email;
		String title;
		Date writedate;
		int readed;					
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list = new ArrayList();
				do {	
					seq = rs.getInt("seq");
					title = rs.getString("title");
					writer = rs.getString("writer");
					email = rs.getString("email");
					writedate = rs.getDate("writedate");
					readed = rs.getInt("readed");
					vo = BoardDTO.builder()
							.seq(seq)
							.writer(writer)
							.email(email)
							.title(title)
							.writedate(writedate)
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
	public int insert(BoardDTO dto) throws Exception {
		int rowCount = 0;
		String sql = 
				"INSERT INTO tbl_cstvsboard ( seq, writer, pwd, email, title, tag, content ) "
						+ " VALUES (SEQ_TBL_CSTVSBOARD.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;

		pstmt = this.conn.prepareStatement(sql);	
		pstmt.setString(1,  dto.getWriter() );
		pstmt.setString(2,  dto.getPwd() );
		pstmt.setString(3,  dto.getEmail() );
		pstmt.setString(4,  dto.getTitle() );
		pstmt.setInt(5, dto.getTag());
		pstmt.setString(6,  dto.getContent() );
		rowCount = pstmt.executeUpdate();	         		
		pstmt.close();			
		return rowCount;				
	}
}
