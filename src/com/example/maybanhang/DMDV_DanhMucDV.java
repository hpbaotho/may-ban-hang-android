package com.example.maybanhang;

import java.io.Serializable;

public class DMDV_DanhMucDV implements Serializable{

	public int ID;
	public String MaDV, MaVach, TenDV, TenTAnh, ChiNhanh, KhuVuc, NhomDV,
			DonGia, GiaKM1, GiaKM2, FtGiamGia, CauHinhGiamGia, DonGiaCheBien,
			DonVi, ThuocTinhIn, ChoPhepBan, Anh, MaNV, NgaySuaGia, GhiChu;
	
	public DMDV_DanhMucDV(){};

	public DMDV_DanhMucDV(String maDV, String maVach, String tenDV,
			String tenTAnh, String chiNhanh, String khuVuc, String nhomDV,
			String donGia, String giaKM1, String giaKM2, String ftGiamGia,
			String cauHinhGiamGia, String donGiaCheBien, String donVi,
			String thuocTinhIn, String choPhepBan, String anh, String maNV,
			String ngaySuaGia, String ghiChu) {
		super();
		MaDV = maDV;
		MaVach = maVach;
		TenDV = tenDV;
		TenTAnh = tenTAnh;
		ChiNhanh = chiNhanh;
		KhuVuc = khuVuc;
		NhomDV = nhomDV;
		DonGia = donGia;
		GiaKM1 = giaKM1;
		GiaKM2 = giaKM2;
		FtGiamGia = ftGiamGia;
		CauHinhGiamGia = cauHinhGiamGia;
		DonGiaCheBien = donGiaCheBien;
		DonVi = donVi;
		ThuocTinhIn = thuocTinhIn;
		ChoPhepBan = choPhepBan;
		Anh = anh;
		MaNV = maNV;
		NgaySuaGia = ngaySuaGia;
		GhiChu = ghiChu;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	public String getMaDV() {
		return MaDV;
	}

	public void setMaDV(String maDV) {
		MaDV = maDV;
	}

	public String getMaVach() {
		return MaVach;
	}

	public void setMaVach(String maVach) {
		MaVach = maVach;
	}

	public String getTenDV() {
		return TenDV;
	}

	public void setTenDV(String tenDV) {
		TenDV = tenDV;
	}

	public String getTenTAnh() {
		return TenTAnh;
	}

	public void setTenTAnh(String tenTAnh) {
		TenTAnh = tenTAnh;
	}

	public String getChiNhanh() {
		return ChiNhanh;
	}

	public void setChiNhanh(String chiNhanh) {
		ChiNhanh = chiNhanh;
	}

	public String getKhuVuc() {
		return KhuVuc;
	}

	public void setKhuVuc(String khuVuc) {
		KhuVuc = khuVuc;
	}

	public String getNhomDV() {
		return NhomDV;
	}

	public void setNhomDV(String nhomDV) {
		NhomDV = nhomDV;
	}

	public String getDonGia() {
		return DonGia;
	}

	public void setDonGia(String donGia) {
		DonGia = donGia;
	}

	public String getGiaKM1() {
		return GiaKM1;
	}

	public void setGiaKM1(String giaKM1) {
		GiaKM1 = giaKM1;
	}

	public String getGiaKM2() {
		return GiaKM2;
	}

	public void setGiaKM2(String giaKM2) {
		GiaKM2 = giaKM2;
	}

	public String getFtGiamGia() {
		return FtGiamGia;
	}

	public void setFtGiamGia(String ftGiamGia) {
		FtGiamGia = ftGiamGia;
	}

	public String getCauHinhGiamGia() {
		return CauHinhGiamGia;
	}

	public void setCauHinhGiamGia(String cauHinhGiamGia) {
		CauHinhGiamGia = cauHinhGiamGia;
	}

	public String getDonGiaCheBien() {
		return DonGiaCheBien;
	}

	public void setDonGiaCheBien(String donGiaCheBien) {
		DonGiaCheBien = donGiaCheBien;
	}

	public String getDonVi() {
		return DonVi;
	}

	public void setDonVi(String donVi) {
		DonVi = donVi;
	}

	public String getThuocTinhIn() {
		return ThuocTinhIn;
	}

	public void setThuocTinhIn(String thuocTinhIn) {
		ThuocTinhIn = thuocTinhIn;
	}

	public String getChoPhepBan() {
		return ChoPhepBan;
	}

	public void setChoPhepBan(String choPhepBan) {
		ChoPhepBan = choPhepBan;
	}

	public String getAnh() {
		return Anh;
	}

	public void setAnh(String anh) {
		Anh = anh;
	}

	public String getMaNV() {
		return MaNV;
	}

	public void setMaNV(String maNV) {
		MaNV = maNV;
	}

	public String getNgaySuaGia() {
		return NgaySuaGia;
	}

	public void setNgaySuaGia(String ngaySuaGia) {
		NgaySuaGia = ngaySuaGia;
	}

	public String getGhiChu() {
		return GhiChu;
	}

	public void setGhiChu(String ghiChu) {
		GhiChu = ghiChu;
	};
	
	
}
