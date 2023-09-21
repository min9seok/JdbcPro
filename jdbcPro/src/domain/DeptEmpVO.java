package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeptEmpVO {
	private int deptno;
	private String dname;
	private int empno;
	private String ename;
	private double sal;
}
