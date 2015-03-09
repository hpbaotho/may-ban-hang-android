package com.example.maybanhang;

public class CD_ChucDanh {

	public int ID;
	public String ChucDanh,QuyenHan;
	
	public CD_ChucDanh() {
		super();
	}

	public CD_ChucDanh(String chucDanh, String quyenHan) {
		super();
		ChucDanh = chucDanh;
		QuyenHan = quyenHan;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getChucDanh() {
		return ChucDanh;
	}

	public void setChucDanh(String chucDanh) {
		ChucDanh = chucDanh;
	}

	public String getQuyenHan() {
		return QuyenHan;
	}

	public void setQuyenHan(String quyenHan) {
		QuyenHan = quyenHan;
	}
	
}
