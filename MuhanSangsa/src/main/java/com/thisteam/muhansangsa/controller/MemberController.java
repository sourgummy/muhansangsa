package com.thisteam.muhansangsa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.thisteam.muhansangsa.service.MemberService;
import com.thisteam.muhansangsa.vo.EmployeesVO;
import com.thisteam.muhansangsa.vo.Privilege;

@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@GetMapping(value = "/employees")
	public String emp_view(Model model, HttpSession session){
		
		session.getAttribute("sId");
		String sId = (String)session.getAttribute("sId");
		//TODO: �α��� ��� �ϼ��Ǹ� ���� ��
		sId="admin@muhan.com";
		if(sId != null) { 
			//1. ���� Ȯ��
			 Boolean priv = service.getPrivilege(sId, Privilege.�������);
		
			//2.
			service.getMemberList();
			
			
			model.addAttribute("sId", sId);
			System.out.println("sId   : "+sId);
			
		}
		
		return "emp_List";
	}
	
	@GetMapping(value = "/employeesUpdate")
	public String emp_update(Model model, HttpSession session){
		
		session.getAttribute("sId");
		
		String sId = (String)session.getAttribute("sId");
		sId="admin@muhan.com";
		if(sId != null) {
		
			 
			 
		}
		
		return "";
	}
	

	
	@PostMapping(value = "/employeesUpdate")
	public String emp_updatePro(Model model, HttpSession session, EmployeesVO employees){
		
		session.getAttribute("sId");
		
		String sId = (String)session.getAttribute("sId");
		if(sId != null) {
			
			
			//1. ���� Ȯ��
				 
			int updateCount  = service.updateMember(employees);
			return "";
			
			 
		}else {
			model.addAttribute("msg", "�߸��� �����Դϴ�.");
			return "fail_back";
		}
	
	}
	
	//��� ����ȸ ��������
	@GetMapping(value = "/employeesDetail")
	public String emp_detail(Model model, HttpSession session){
		
	
		//��ȸ�� ����� �ĺ��� �̸���
		String emp_email = (String)model.getAttribute("emp_email");
		
		//������ ��� ���Ǿ��̵�
		String sId = (String)session.getAttribute("sId");
		
		sId="admin@muhan.com";
		System.out.println("sId   : "+sId);
		
		if(sId != null) {
			
				//1. ���� Ȯ��
				boolean isRightUser = service.getPrivilege(sId,Privilege.�����ȸ);
			
				if(isRightUser) {//������ ���� ���
					
					//2.��� ��ȸ
					EmployeesVO emp = service.getEmployee(emp_email);
					model.addAttribute("emp", emp);
					return "emp_detail";
					
					
				}else {
					model.addAttribute("msg", "������ �����ϴ�");
					return "fail_back";
				}

			
		}else {
			
			model.addAttribute("msg", "�߸��� �����Դϴ�.");
			return "fail_back";
	
		}
	}
	
	
}//end of MemberController













