package com.example.maybanhang;

public class HD_HoaDon {
	protected int ID;
	protected String Type, ChiNhanh, KhuVuc, MaCaBD, MaCaKT, TdBatDau,
			TdKetThuc, MaNVTnBD, MaNVTnKT, MaKH, FtDieuChinh, TongTienPhaiThu,
			TtTienMat_VND, TtTienMat_USD, TienMatTT_EUR, TtThe_TongTien,
			TtThe_Loai, TtThe_MaGiaoDich, TtThe_NgoaiTe, TrangThai,
			SoLanTachHD, GhiChu;
	
	public HD_HoaDon(){};
	
	public HD_HoaDon(String type, String chiNhanh, String khuVuc,
			String maCaBD, String maCaKT, String tdBatDau, String tdKetThuc,
			String maNVTnBD, String maNVTnKT, String maKH, String ftDieuChinh,
			String tongTienPhaiThu, String ttTienMat_VND, String ttTienMat_USD,
			String tienMatTT_EUR, String ttThe_TongTien, String ttThe_Loai,
			String ttThe_MaGiaoDich, String ttThe_NgoaiTe, String trangThai,
			String soLanTachHD, String ghiChu) {
		super();
		Type = type;
		ChiNhanh = chiNhanh;
		KhuVuc = khuVuc;
		MaCaBD = maCaBD;
		MaCaKT = maCaKT;
		TdBatDau = tdBatDau;
		TdKetThuc = tdKetThuc;
		MaNVTnBD = maNVTnBD;
		MaNVTnKT = maNVTnKT;
		MaKH = maKH;
		FtDieuChinh = ftDieuChinh;
		TongTienPhaiThu = tongTienPhaiThu;
		TtTienMat_VND = ttTienMat_VND;
		TtTienMat_USD = ttTienMat_USD;
		TienMatTT_EUR = tienMatTT_EUR;
		TtThe_TongTien = ttThe_TongTien;
		TtThe_Loai = ttThe_Loai;
		TtThe_MaGiaoDich = ttThe_MaGiaoDich;
		TtThe_NgoaiTe = ttThe_NgoaiTe;
		TrangThai = trangThai;
		SoLanTachHD = soLanTachHD;
		GhiChu = ghiChu;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
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
	public String getMaCaBD() {
		return MaCaBD;
	}
	public void setMaCaBD(String maCaBD) {
		MaCaBD = maCaBD;
	}
	public String getMaCaKT() {
		return MaCaKT;
	}
	public void setMaCaKT(String maCaKT) {
		MaCaKT = maCaKT;
	}
	public String getTdBatDau() {
		return TdBatDau;
	}
	public void setTdBatDau(String tdBatDau) {
		TdBatDau = tdBatDau;
	}
	public String getTdKetThuc() {
		return TdKetThuc;
	}
	public void setTdKetThuc(String tdKetThuc) {
		TdKetThuc = tdKetThuc;
	}
	public String getMaNVTnBD() {
		return MaNVTnBD;
	}
	public void setMaNVTnBD(String maNVTnBD) {
		MaNVTnBD = maNVTnBD;
	}
	public String getMaNVTnKT() {
		return MaNVTnKT;
	}
	public void setMaNVTnKT(String maNVTnKT) {
		MaNVTnKT = maNVTnKT;
	}
	public String getMaKH() {
		return MaKH;
	}
	public void setMaKH(String maKH) {
		MaKH = maKH;
	}
	public String getFtDieuChinh() {
		return FtDieuChinh;
	}
	public void setFtDieuChinh(String ftDieuChinh) {
		FtDieuChinh = ftDieuChinh;
	}
	public String getTongTienPhaiThu() {
		return TongTienPhaiThu;
	}
	public void setTongTienPhaiThu(String tongTienPhaiThu) {
		TongTienPhaiThu = tongTienPhaiThu;
	}
	public String getTtTienMat_VND() {
		return TtTienMat_VND;
	}
	public void setTtTienMat_VND(String ttTienMat_VND) {
		TtTienMat_VND = ttTienMat_VND;
	}
	public String getTtTienMat_USD() {
		return TtTienMat_USD;
	}
	public void setTtTienMat_USD(String ttTienMat_USD) {
		TtTienMat_USD = ttTienMat_USD;
	}
	public String getTienMatTT_EUR() {
		return TienMatTT_EUR;
	}
	public void setTienMatTT_EUR(String tienMatTT_EUR) {
		TienMatTT_EUR = tienMatTT_EUR;
	}
	public String getTtThe_TongTien() {
		return TtThe_TongTien;
	}
	public void setTtThe_TongTien(String ttThe_TongTien) {
		TtThe_TongTien = ttThe_TongTien;
	}
	public String getTtThe_Loai() {
		return TtThe_Loai;
	}
	public void setTtThe_Loai(String ttThe_Loai) {
		TtThe_Loai = ttThe_Loai;
	}
	public String getTtThe_MaGiaoDich() {
		return TtThe_MaGiaoDich;
	}
	public void setTtThe_MaGiaoDich(String ttThe_MaGiaoDich) {
		TtThe_MaGiaoDich = ttThe_MaGiaoDich;
	}
	public String getTtThe_NgoaiTe() {
		return TtThe_NgoaiTe;
	}
	public void setTtThe_NgoaiTe(String ttThe_NgoaiTe) {
		TtThe_NgoaiTe = ttThe_NgoaiTe;
	}
	public String getTrangThai() {
		return TrangThai;
	}
	public void setTrangThai(String trangThai) {
		TrangThai = trangThai;
	}
	public String getSoLanTachHD() {
		return SoLanTachHD;
	}
	public void setSoLanTachHD(String soLanTachHD) {
		SoLanTachHD = soLanTachHD;
	}
	public String getGhiChu() {
		return GhiChu;
	}
	public void setGhiChu(String ghiChu) {
		GhiChu = ghiChu;
	}
	
	
}
