package com.thisteam.muhansangsa.vo;

public class DepartmentVO {
//	DEPT_CD	�μ��ڵ�
//	DEPT_NAME	�μ���
	
	String dept_cd;
	String dept_name;
	
	public String getDept_cd() {
		return dept_cd;
	}
	public void setDept_cd(String dept_cd) {
		this.dept_cd = dept_cd;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	@Override
	public String toString() {
		return "DepartmentVO [dept_cd=" + dept_cd + ", dept_name=" + dept_name + "]";
	}
	

	
}














