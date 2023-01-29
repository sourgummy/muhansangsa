package com.thisteam.muhansangsa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thisteam.muhansangsa.vo.EmployeesVO;

public interface EmployeesMapper {

	List selectMemberList();
	
	//���� ��ȸ
	String getPrivilege(@Param("id")String sId);

	int updateMember(EmployeesVO emp);

	//�������ȸ
	EmployeesVO selectEmployee(String emp_email);



}













