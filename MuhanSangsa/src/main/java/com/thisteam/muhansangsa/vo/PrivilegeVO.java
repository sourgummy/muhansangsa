package com.thisteam.muhansangsa.vo;

public class PrivilegeVO {

//	PRIV_CD	�����ڵ�
//	PRIV_TYPE	��������
	
	String priv_cd;
	String priv_type; //�⺻���(0), �����ȸ(1), �������(2), �����ȸ(3), ������(4)
	
	public String getPriv_cd() {
		return priv_cd;
	}
	public void setPriv_cd(String priv_cd) {
		this.priv_cd = priv_cd;
	}
	public String getPriv_type() {
		return priv_type;
	}
	public void setPriv_type(String priv_type) {
		this.priv_type = priv_type;
	}
	@Override
	public String toString() {
		return "PrivilegeVO [priv_cd=" + priv_cd + ", priv_type=" + priv_type + "]";
	}

	

	
}














