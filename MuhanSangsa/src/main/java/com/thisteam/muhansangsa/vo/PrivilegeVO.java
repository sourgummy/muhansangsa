package com.thisteam.muhansangsa.vo;

public class PrivilegeVO {

//	PRIV_CD	�����ڵ�
//	PRIV_TYPE	��������
	
	String priv_cd;
	String type; //�⺻���(0), �����ȸ(1), �������(2), �����ȸ(3), ������(4)
	public String getPriv_cd() {
		return priv_cd;
	}
	public void setPriv_cd(String priv_cd) {
		this.priv_cd = priv_cd;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "PrivilegeVO [priv_cd=" + priv_cd + ", type=" + type + "]";
	}
	

	
}














