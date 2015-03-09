package com.example.maybanhang;

public class CN_ChiNhanh {

	public int ID;
	public String TenCN, DiaChi, SoDT, ThongTinKhac, CauHinh;
	
	
	
	public CN_ChiNhanh() {
		super();
	}



	public CN_ChiNhanh(String tenCN, String diaChi, String soDT,
			String thongTinKhac, String cauHinh) {
		super();
		TenCN = tenCN;
		DiaChi = diaChi;
		SoDT = soDT;
		ThongTinKhac = thongTinKhac;
		CauHinh = cauHinh;
	}



	public int getID() {
		return ID;
	}



	public void setID(int iD) {
		ID = iD;
	}



	public String getTenCN() {
		return TenCN;
	}



	public void setTenCN(String tenCN) {
		TenCN = tenCN;
	}



	public String getDiaChi() {
		return DiaChi;
	}



	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}



	public String getSoDT() {
		return SoDT;
	}



	public void setSoDT(String soDT) {
		SoDT = soDT;
	}



	public String getThongTinKhac() {
		return ThongTinKhac;
	}



	public void setThongTinKhac(String thongTinKhac) {
		ThongTinKhac = thongTinKhac;
	}



	public String getCauHinh() {
		return CauHinh;
	}



	public void setCauHinh(String cauHinh) {
		CauHinh = cauHinh;
	}
	
	
}
