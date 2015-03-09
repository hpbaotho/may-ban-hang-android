package com.example.maybanhang;

public class CTN_CaThuNgan {

	public int ID;
	public String MaNV, TdBatDau, TdKetThuc, TienMatDauCa, TienMatCuoiCa,
			DsHDChuaThu, TienChuaThu, MaNV_CaSau, GhiChu;
	
	public CTN_CaThuNgan(){}

	public CTN_CaThuNgan(String maNV, String tdBatDau,
			String tdKetThuc, String tienMatDauCa, String tienMatCuoiCa,
			String dsHDChuaThu, String tienChuaThu, String maNV_CaSau,
			String ghiChu) {
		super();
		MaNV = maNV;
		TdBatDau = tdBatDau;
		TdKetThuc = tdKetThuc;
		TienMatDauCa = tienMatDauCa;
		TienMatCuoiCa = tienMatCuoiCa;
		DsHDChuaThu = dsHDChuaThu;
		TienChuaThu = tienChuaThu;
		MaNV_CaSau = maNV_CaSau;
		GhiChu = ghiChu;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getMaNV() {
		return MaNV;
	}

	public void setMaNV(String maNV) {
		MaNV = maNV;
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

	public String getTienMatDauCa() {
		return TienMatDauCa;
	}

	public void setTienMatDauCa(String tienMatDauCa) {
		TienMatDauCa = tienMatDauCa;
	}

	public String getTienMatCuoiCa() {
		return TienMatCuoiCa;
	}

	public void setTienMatCuoiCa(String tienMatCuoiCa) {
		TienMatCuoiCa = tienMatCuoiCa;
	}

	public String getDsHDChuaThu() {
		return DsHDChuaThu;
	}

	public void setDsHDChuaThu(String dsHDChuaThu) {
		DsHDChuaThu = dsHDChuaThu;
	}

	public String getTienChuaThu() {
		return TienChuaThu;
	}

	public void setTienChuaThu(String tienChuaThu) {
		TienChuaThu = tienChuaThu;
	}

	public String getMaNV_CaSau() {
		return MaNV_CaSau;
	}

	public void setMaNV_CaSau(String maNV_CaSau) {
		MaNV_CaSau = maNV_CaSau;
	}

	public String getGhiChu() {
		return GhiChu;
	}

	public void setGhiChu(String ghiChu) {
		GhiChu = ghiChu;
	};
	
	
}
