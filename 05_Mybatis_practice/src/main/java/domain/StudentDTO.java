package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
	private int stuNo;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private double ave;
	private String grade;
}
