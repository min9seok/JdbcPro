package survey.domain;



import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@AllArgsConstructor
@Builder
public class SurveyDTO {

	private int seq;   
	private String user_name;
	private Date regdate;    
	private Date start_date; 
	private Date end_date;   
	private String question;   
	private int cate_count; 
	private int readed;
	
}
