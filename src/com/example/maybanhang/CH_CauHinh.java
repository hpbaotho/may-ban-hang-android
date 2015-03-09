package com.example.maybanhang;

public class CH_CauHinh {
	
	public int ID;
	public String TenCH,NoiDungCH;
	public CH_CauHinh() {
		super();
	}
	public CH_CauHinh(String tenCH, String noiDungCH) {
		super();
		TenCH = tenCH;
		NoiDungCH = noiDungCH;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getTenCH() {
		return TenCH;
	}
	public void setTenCH(String tenCH) {
		TenCH = tenCH;
	}
	public String getNoiDungCH() {
		return NoiDungCH;
	}
	public void setNoiDungCH(String noiDungCH) {
		NoiDungCH = noiDungCH;
	}
	
	
}
