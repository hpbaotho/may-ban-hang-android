package com.example.maybanhang;

import java.io.Serializable;

public class NV_NhanVien implements Serializable{
	String MaNV, HoTen, MatKhau, MaThe, ChiNhanh, BoPhan, ChucDanh, GioiTinh,
			NgaySinh, QueQuan, CMTND, NgayCap, NoiCap, HSLuong, TroCap,
			ThangThuong, ThangPhat, DatCoc, NgayLam, NgayNghi, TrangThai,
			LoaiHopDong, LanTruyCapCuoi, GhiChu, CauHinh;
	int ID;

	public NV_NhanVien(String maNV, String hoTen, String matKhau, String maThe,
			String chiNhanh, String boPhan, String chucDanh, String gioiTinh,
			String ngaySinh, String queQuan, String cMTND, String ngayCap,
			String noiCap, String hSLuong, String troCap, String thangThuong,
			String thangPhat, String datCoc, String ngayLam, String ngayNghi,
			String trangThai, String loaiHopDong, String lanTruyCapCuoi,
			String ghiChu, String cauHinh) {
		super();
		MaNV = maNV;
		HoTen = hoTen;
		MatKhau = matKhau;
		MaThe = maThe;
		ChiNhanh = chiNhanh;
		BoPhan = boPhan;
		ChucDanh = chucDanh;
		GioiTinh = gioiTinh;
		NgaySinh = ngaySinh;
		QueQuan = queQuan;
		CMTND = cMTND;
		NgayCap = ngayCap;
		NoiCap = noiCap;
		HSLuong = hSLuong;
		TroCap = troCap;
		ThangThuong = thangThuong;
		ThangPhat = thangPhat;
		DatCoc = datCoc;
		NgayLam = ngayLam;
		NgayNghi = ngayNghi;
		TrangThai = trangThai;
		LoaiHopDong = loaiHopDong;
		LanTruyCapCuoi = lanTruyCapCuoi;
		GhiChu = ghiChu;
		CauHinh = cauHinh;
	}
	
	public NV_NhanVien()
	{};

	public int getID(){
		return ID;
	}
	
	public void setID(int id){
		ID = id;
	}
	
	public String getMaNV() {
		return MaNV;
	}

	public void setMaNV(String maNV) {
		MaNV = maNV;
	}

	public String getHoTen() {
		return HoTen;
	}

	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}

	public String getMatKhau() {
		return MatKhau;
	}

	public void setMatKhau(String matKhau) {
		MatKhau = matKhau;
	}

	public String getMaThe() {
		return MaThe;
	}

	public void setMaThe(String maThe) {
		MaThe = maThe;
	}

	public String getChiNhanh() {
		return ChiNhanh;
	}

	public void setChiNhanh(String chiNhanh) {
		ChiNhanh = chiNhanh;
	}

	public String getBoPhan() {
		return BoPhan;
	}

	public void setBoPhan(String boPhan) {
		BoPhan = boPhan;
	}

	public String getChucDanh() {
		return ChucDanh;
	}

	public void setChucDanh(String chucDanh) {
		ChucDanh = chucDanh;
	}

	public String getGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}

	public String getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		NgaySinh = ngaySinh;
	}

	public String getQueQuan() {
		return QueQuan;
	}

	public void setQueQuan(String queQuan) {
		QueQuan = queQuan;
	}

	public String getCMTND() {
		return CMTND;
	}

	public void setCMTND(String cMTND) {
		CMTND = cMTND;
	}

	public String getNgayCap() {
		return NgayCap;
	}

	public void setNgayCap(String ngayCap) {
		NgayCap = ngayCap;
	}

	public String getNoiCap() {
		return NoiCap;
	}

	public void setNoiCap(String noiCap) {
		NoiCap = noiCap;
	}

	public String getHSLuong() {
		return HSLuong;
	}

	public void setHSLuong(String hSLuong) {
		HSLuong = hSLuong;
	}

	public String getTroCap() {
		return TroCap;
	}

	public void setTroCap(String troCap) {
		TroCap = troCap;
	}

	public String getThangThuong() {
		return ThangThuong;
	}

	public void setThangThuong(String thangThuong) {
		ThangThuong = thangThuong;
	}

	public String getThangPhat() {
		return ThangPhat;
	}

	public void setThangPhat(String thangPhat) {
		ThangPhat = thangPhat;
	}

	public String getDatCoc() {
		return DatCoc;
	}

	public void setDatCoc(String datCoc) {
		DatCoc = datCoc;
	}

	public String getNgayLam() {
		return NgayLam;
	}

	public void setNgayLam(String ngayLam) {
		NgayLam = ngayLam;
	}

	public String getNgayNghi() {
		return NgayNghi;
	}

	public void setNgayNghi(String ngayNghi) {
		NgayNghi = ngayNghi;
	}

	public String getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(String trangThai) {
		TrangThai = trangThai;
	}

	public String getLoaiHopDong() {
		return LoaiHopDong;
	}

	public void setLoaiHopDong(String loaiHopDong) {
		LoaiHopDong = loaiHopDong;
	}

	public String getLanTruyCapCuoi() {
		return LanTruyCapCuoi;
	}

	public void setLanTruyCapCuoi(String lanTruyCapCuoi) {
		LanTruyCapCuoi = lanTruyCapCuoi;
	}

	public String getGhiChu() {
		return GhiChu;
	}

	public void setGhiChu(String ghiChu) {
		GhiChu = ghiChu;
	}

	public String getCauHinh() {
		return CauHinh;
	}

	public void setCauHinh(String cauHinh) {
		CauHinh = cauHinh;
	}
}
