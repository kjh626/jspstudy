package domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GesipanDTO {
	private int gesipan_no;
	private String title;
	private String content;
	private Date modified_date;
	private Date created_date;
	
}
