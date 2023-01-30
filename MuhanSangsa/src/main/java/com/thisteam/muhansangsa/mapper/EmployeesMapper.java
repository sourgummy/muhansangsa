package com.thisteam.muhansangsa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thisteam.muhansangsa.vo.DepartmentVO;
import com.thisteam.muhansangsa.vo.EmployeesVO;
import com.thisteam.muhansangsa.vo.Emp_viewVO;
import com.thisteam.muhansangsa.vo.GradeVO;

public interface EmployeesMapper {

	List<Emp_viewVO> selectMemberList(@Param("searchType") String searchType, 
										@Param("keyword") String keyword);
	   
	//���� ��ȸ
	String getPrivilege(@Param("id")String sId);

	int updateMember(EmployeesVO emp);

	//�������ȸ
	Emp_viewVO selectEmployee(String emp_email);

	//�μ���ȸ
	DepartmentVO selectDepartmentName(String dept_cd);
	
	//������ȸ
	GradeVO selectGradeName(String grade_cd);



}













