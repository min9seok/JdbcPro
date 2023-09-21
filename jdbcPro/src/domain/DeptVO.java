package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class DeptVO {
//	DEPTNO NOT NULL NUMBER(2)
//	DNAME           VARCHAR2(14)
//	LOC             VARCHAR2(13)
	
	private int deptno;
	private String dname;
	private String loc;
	@Override
	public String toString() {
		return "DeptVO [deptno=" + deptno + ", dname=" + dname + ", loc=" + loc + "]";
	}
	
}
