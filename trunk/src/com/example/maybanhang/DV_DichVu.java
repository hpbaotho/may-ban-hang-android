package com.example.maybanhang;

import java.io.Serializable;

public class DV_DichVu implements Serializable{

	public int ID;
	public String maDV, tenDV, soLuong, donGiaBan, donGiaCB, donViTinh,
			ftGiaGia, maHD, maNVPV, maNVCB, maCaPV, thoiDiemPV, trangThai,
			inYC, ghiChu;
	
	
	
	public DV_DichVu() {
		super();
	}

	public DV_DichVu(String maDV, String tenDV, String soLuong,
			String donGiaBan, String donGiaCB, String donViTinh,
			String ftGiaGia, String maHD, String maNVPV, String maNVCB,
			String maCaPV, String thoiDiemPV, String trangThai, String inYC,
			String ghiChu) {
		super();
		this.maDV = maDV;
		this.tenDV = tenDV;
		this.soLuong = soLuong;
		this.donGiaBan = donGiaBan;
		this.donGiaCB = donGiaCB;
		this.donViTinh = donViTinh;
		this.ftGiaGia = ftGiaGia;
		this.maHD = maHD;
		this.maNVPV = maNVPV;
		this.maNVCB = maNVCB;
		this.maCaPV = maCaPV;
		this.thoiDiemPV = thoiDiemPV;
		this.trangThai = trangThai;
		this.inYC = inYC;
		this.ghiChu = ghiChu;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getMaDV() {
		return maDV;
	}

	public void setMaDV(String maDV) {
		this.maDV = maDV;
	}

	public String getTenDV() {
		return tenDV;
	}

	public void setTenDV(String tenDV) {
		this.tenDV = tenDV;
	}

	public String getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(String soLuong) {
		this.soLuong = soLuong;
	}

	public String getDonGiaBan() {
		return donGiaBan;
	}

	public void setDonGiaBan(String donGiaBan) {
		this.donGiaBan = donGiaBan;
	}

	public String getDonGiaCB() {
		return donGiaCB;
	}

	public void setDonGiaCB(String donGiaCB) {
		this.donGiaCB = donGiaCB;
	}

	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	public String getFtGiaGia() {
		return ftGiaGia;
	}

	public void setFtGiaGia(String ftGiaGia) {
		this.ftGiaGia = ftGiaGia;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public String getMaNVPV() {
		return maNVPV;
	}

	public void setMaNVPV(String maNVPV) {
		this.maNVPV = maNVPV;
	}

	public String getMaNVCB() {
		return maNVCB;
	}

	public void setMaNVCB(String maNVCB) {
		this.maNVCB = maNVCB;
	}

	public String getMaCaPV() {
		return maCaPV;
	}

	public void setMaCaPV(String maCaPV) {
		this.maCaPV = maCaPV;
	}

	public String getThoiDiemPV() {
		return thoiDiemPV;
	}

	public void setThoiDiemPV(String thoiDiemPV) {
		this.thoiDiemPV = thoiDiemPV;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public String getInYC() {
		return inYC;
	}

	public void setInYC(String inYC) {
		this.inYC = inYC;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	
	
	
}
