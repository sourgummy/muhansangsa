package com.thisteam.muhansangsa.vo;
//�⺻���(0), �����ȸ(1), �������(2), �����ȸ(3), ������(4);
public enum Privilege {
	
	�⺻���(0), �����ȸ(1), �������(2), �����ȸ(3), ������(4);

	//�ܺο��� ���� ���ϵ��� private���� ����
	private int code ;
	private Privilege(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
//	public String getBinaryCode() {
//		String BinaryCode = "ABCDE";
//		BinaryCode.charAt(code);
//		BinaryCode.replace(BinaryCode.charAt(code), '1').replaceAll("[A-Z]", "0");
//		return BinaryCode.replace(BinaryCode.charAt(code), '1').replaceAll("[A-Z]", "0");
//	}
	

	
	
}
