package com.example.maybanhang;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "BanHang13";

	/* Table Nhanvien */
	private static final String TABLE_NV = "tbNhanVien";
	private static final String NV_ID = "id";
	private static final String NV_MANV = "MaNV";
	private static final String NV_TEN = "HoTen";
	private static final String NV_MATKHAU = "MatKhau";
	private static final String NV_MATHE = "MaThe";
	private static final String NV_CHINHANH = "ChiNhanh";
	private static final String NV_BOPHAN = "BoPhan";
	private static final String NV_CHUCDANH = "ChucDanh";
	private static final String NV_GIOITINH = "GioiTinh";
	private static final String NV_NGAYSINH = "NgaySinh";
	private static final String NV_QUEQUAN = "QueQuan";
	private static final String NV_CMTND = "CMTND";
	private static final String NV_NGAYCAP = "NgayCap";
	private static final String NV_NOICAP = "NoiCap";
	private static final String NV_HESOLUONGCUNG = "HSLuongCung";
	private static final String NV_LUONGTHUONGTHANG = "LuongThuongThang";
	private static final String NV_LUONGPHUCAP = "LuongPhuCap";
	private static final String NV_PHATTHANG = "PhatThang";
	private static final String NV_DATCOC = "DatCoc";
	private static final String NV_NGAYVAOLAM = "NgayVaoLam";
	private static final String NV_NGAYNGHIVIEC = "NgayNghiViec";
	private static final String NV_LANTRUYCAPCUOI = "LanTruyCapCuoi";
	private static final String NV_TRANGTHAI = "TrangThai";
	private static final String NV_LOAIHOPDONG = "LoaiHopDong";
	private static final String NV_GHICHU = "GhiChu";
	private static final String NV_CAUHINH = "CauHinh";

	// Table Hoadon
	private static final String TABLE_HD = "tbHoaDon";
	private static final String HD_ID = "id";
	private static final String HD_MAQUAY = "MaHD";
	private static final String HD_LOAIHD = "LoaiHD";
	private static final String HD_CHINHANH = "ChiNhanh";
	private static final String HD_KHUVUC = "KhuVuc";
	private static final String HD_MACABD = "MaCaBD";
	private static final String HD_MACAKT = "MaCaKT";
	private static final String HD_TDBATDAU = "TDBatDau";
	private static final String HD_TDKETHUC = "TDKetThuc";
	private static final String HD_MANVTNBD = "MaNVTnBD";
	private static final String HD_MANVTNKT = "MaNVTnKT";
	private static final String HD_MAKH = "MaKH";
	private static final String HD_FTDIEUCHINH = "FtDieuChinh";
	private static final String HD_TONGTIENTHU = "TongTienThu";
	private static final String HD_TTTIENMAT_VND = "TtTienMat_VND";
	private static final String HD_TTTIENMAT_USD = "TtTienMat_USD";
	private static final String HD_TTTIENMAT_EUR = "TtTienMat_EUR";
	private static final String HD_TTTHE_TONGTIEN = "TtThe_TongTien";
	private static final String HD_TTTHE_THELOAI = "TtThe_Loai";
	private static final String HD_TTHE_MAGIAODICH = "TtThe_MaGiaoDich";
	private static final String HD_TTHE_NGOAITE = "TtThe_NgoaiTe";
	private static final String HD_TRANGTHAI = "TrangThai";
	private static final String HD_SOLANTACHHD = "SoLanTachHD";
	private static final String HD_GHICHU = "GhiChu";

	// Table CaThuNgan
	private static final String TABLE_CTN = "tbCaThuNgan";
	private static final String CTN_ID = "id";
	private static final String CTN_MANV = "MaNV";
	private static final String CTN_TDBATDAU = "TdBatDau";
	private static final String CTN_TDKETTHUC = "TdKetThuc";
	private static final String CTN_TIENMATDAUCA = "TienMatDauCa";
	private static final String CTN_TIENMATCUOICA = "TienMatCuoiCa";
	private static final String CTN_DSHDCHUATHU = "DsHdChuaThu";
	private static final String CTN_TIENCHUATHU = "TienChuaThu";
	private static final String CTN_MANV_CASAU = "MaNV_CaSau";
	private static final String CTN_GHICHU = "GhiChu";

	// Table CauHinh
	private static final String TABLE_CH = "tbCauHinh";
	private static final String CH_ID = "id";
	private static final String CH_TEN = "Ten";
	private static final String CH_NOIDUNG = "NoiDung";

	// Table ChiNhanh
	private static final String TABLE_CN = "tbChiNhanh";
	private static final String CN_ID = "id";
	private static final String CN_CHINHANH = "ChiNhanh";
	private static final String CN_DIACHI = "DiaChi";
	private static final String CN_SODT = "SoDT";
	private static final String CN_THONGTINKHAC = "ThongTinKhac";
	private static final String CN_CAUHINH = "CauHinh";

	// Table ChucDanh
	private static final String TABLE_CD = "tbChucDanh";
	private static final String CD_ID = "id";
	private static final String CD_CHUCDANH = "ChucDanh";
	private static final String CD_QUYENHAN = "QuyenHan";

	// Table DichVu
	private static final String TABLE_DV = "tbDichVu";
	private static final String DV_ID = "id";
	private static final String DV_MADV = "MaDV";
	private static final String DV_TENDV = "TenDV";
	private static final String DV_SOLUONG = "SoLuong";
	private static final String DV_DONGIABAN = "DonGiaBan";
	private static final String DV_DONGIACHEBIEN = "DonGiaCheBien";
	private static final String DV_DONVI = "DonVi";
	private static final String DV_FTGIAMGIA = "FtGiamGia";
	private static final String DV_MAHD = "MaHD";
	private static final String DV_MANVPV = "MaNVPV";
	private static final String DV_MANVCB = "MaNVCB";
	private static final String DV_MACAPV = "MaCaPV";
	private static final String DV_THOIDIEMPV = "ThoiDiemPV";
	private static final String DV_TRANGTHAI = "TrangThai";
	private static final String DV_INYC = "InYC";
	private static final String DV_GHICHU = "GhiChu";

	// Table DMDichVu
	private static final String TABLE_DMDV = "tbDMDichVu";
	private static final String DMDV_ID = "id";
	private static final String DMDV_MADV = "MaDV";
	private static final String DMDV_MAVACH = "MaVach";
	private static final String DMDV_TENDV = "TenDV";
	private static final String DMDV_TENTANH = "TenTAnh";
	private static final String DMDV_CHINHANH = "ChiNhanh";
	private static final String DMDV_KHUVUC = "KhuVuc";
	private static final String DMDV_NHOMDV = "NhomDV";
	private static final String DMDV_DONGIA = "DonGia";
	private static final String DMDV_GIAKM1 = "GiaKM1";
	private static final String DMDV_GIAKM2 = "GiaKM2";
	private static final String DMDV_FTGIAMGIA = "FtGiamGia";
	private static final String DMDV_CAUHINHGIAMGIA = "CauHinhGiamGia";
	private static final String DMDV_DONGIACHEBIEN = "DonGiaCheBien";
	private static final String DMDV_DONVI = "DonVi";
	private static final String DMDV_THUOCTINHIN = "ThuocTinhIn";
	private static final String DMDV_CHOPHEPBAN = "ChoPhepBan";
	private static final String DMDV_ANH = "Anh";
	private static final String DMDV_MANV = "MaNV";
	private static final String DMDV_NGAYSUAGIA = "NgaySuaGia";
	private static final String DMDV_GHICHU = "GhiChu";

	// Table KhachHang
	private static final String TABLE_KH = "tbKhachHang";
	private static final String KH_ID = "id";
	private static final String KH_TENKH = "TenKH";
	private static final String KH_NGAYSINH = "NgaySinh";
	private static final String KH_DIENTHOAI = "DienThoai";
	private static final String KH_DIACHI = "DiaChi";
	private static final String KH_NHOMKH = "NhomKH";
	private static final String KH_CAPBAC = "CapBac";
	private static final String KH_DIEMTICHLUYHIENTAI = "DiemTichLuyHienTai";
	private static final String KH_NGAYDANGKY = "NgayDangKy";
	private static final String KH_NGAYHETHAN = "NgayHetHan";
	private static final String KH_ANHDAIDIEN = "AnhDaiDien";
	private static final String KH_FTGIAMDVTINHGIO = "FtGiamDvTinhGio";
	private static final String KH_FTGIAMDVKHAC = "FtGiamDvKhac";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sqlCreate_TableNV = "CREATE TABLE " + TABLE_NV + " (" + NV_ID
				+ " INTEGER PRIMARY KEY," + NV_MANV + " TEXT, " + NV_TEN
				+ " TEXT NOT NULL, " + NV_MATKHAU + " TEXT , " + NV_MATHE
				+ " TEXT , " + NV_CHINHANH + " TEXT ," + NV_BOPHAN + " TEXT ,"
				+ NV_CHUCDANH + " TEXT ," + NV_GIOITINH + " TEXT, "
				+ NV_NGAYSINH + " TEXT," + NV_QUEQUAN + " TEXT," + NV_CMTND
				+ " TEXT," + NV_NGAYCAP + " TEXT," + NV_NOICAP + " TEXT,"
				+ NV_HESOLUONGCUNG + " TEXT," + NV_LUONGTHUONGTHANG + " TEXT,"
				+ NV_LUONGPHUCAP + " TEXT," + NV_PHATTHANG + " TEXT,"
				+ NV_DATCOC + " TEXT," + NV_NGAYVAOLAM + " TEXT,"
				+ NV_NGAYNGHIVIEC + " TEXT," + NV_LANTRUYCAPCUOI + " TEXT,"
				+ NV_TRANGTHAI + " TEXT," + NV_LOAIHOPDONG + " TEXT,"
				+ NV_GHICHU + " TEXT," + NV_CAUHINH + " TEXT" + ")";
		db.execSQL(sqlCreate_TableNV);

		String sqlCreate_TableHD = "CREATE TABLE " + TABLE_HD + " (" + HD_ID
				+ " INTEGER PRIMARY KEY," + HD_LOAIHD + " TEXT NOT NULL, "
				+ HD_CHINHANH + " TEXT NOT NULL, "
				+ HD_KHUVUC + " TEXT , " + HD_MACABD + " TEXT , " + HD_MACAKT
				+ " TEXT ," + HD_TDBATDAU + " TEXT ," + HD_TDKETHUC + " TEXT ,"
				+ HD_MANVTNBD + " TEXT, " + HD_MANVTNKT + " TEXT," + HD_MAKH
				+ " TEXT," + HD_FTDIEUCHINH + " TEXT," + HD_TONGTIENTHU
				+ " TEXT," + HD_TTTIENMAT_VND + " TEXT," + HD_TTTIENMAT_USD
				+ " TEXT," + HD_TTTIENMAT_EUR + " TEXT," + HD_TTTHE_TONGTIEN
				+ " TEXT," + HD_TTTHE_THELOAI + " TEXT," + HD_TTHE_MAGIAODICH
				+ " TEXT," + HD_TTHE_NGOAITE + " TEXT," + HD_TRANGTHAI
				+ " TEXT," + HD_SOLANTACHHD + " TEXT," + HD_GHICHU + " TEXT"
				+ ")";
		db.execSQL(sqlCreate_TableHD);

		String sqlCreate_TableCTN = "CREATE TABLE " + TABLE_CTN + " (" + CTN_ID
				+ " INTEGER PRIMARY KEY," + CTN_MANV + " TEXT NOT NULL, "
				+ CTN_TDBATDAU + " TEXT , " + CTN_TDKETTHUC + " TEXT , "
				+ CTN_TIENMATDAUCA + " TEXT ," + CTN_TIENMATCUOICA + " TEXT ,"
				+ CTN_DSHDCHUATHU + " TEXT ," + CTN_TIENCHUATHU + " TEXT, "
				+ CTN_MANV_CASAU + " TEXT," + CTN_GHICHU + " TEXT" + ")";
		db.execSQL(sqlCreate_TableCTN);

		String sqlCreate_TableCH = "CREATE TABLE " + TABLE_CH + " (" + CH_ID
				+ " INTEGER PRIMARY KEY," + CH_TEN + " TEXT, " + CH_NOIDUNG
				+ " TEXT" + ")";
		db.execSQL(sqlCreate_TableCH);

		String sqlCreate_TableCN = "CREATE TABLE " + TABLE_CN + " (" + CN_ID
				+ " INTEGER PRIMARY KEY," + CN_CHINHANH + " TEXT, " + CN_DIACHI
				+ " TEXT NOT NULL, " + CN_SODT + " TEXT , " + CN_THONGTINKHAC
				+ " TEXT , " + CN_CAUHINH + " TEXT" + ")";
		db.execSQL(sqlCreate_TableCN);

		String sqlCreate_TableCD = "CREATE TABLE " + TABLE_CD + " (" + CD_ID
				+ " INTEGER PRIMARY KEY," + CD_CHUCDANH + " TEXT, "
				+ CD_QUYENHAN + " TEXT" + ")";
		db.execSQL(sqlCreate_TableCD);

		String sqlCreate_TableDV = "CREATE TABLE " + TABLE_DV + " (" + DV_ID
				+ " INTEGER PRIMARY KEY," + DV_MADV + " TEXT NOT NULL, "
				+ DV_TENDV + " TEXT , " + DV_SOLUONG + " TEXT , "
				+ DV_DONGIABAN + " TEXT ," + DV_DONGIACHEBIEN + " TEXT ,"
				+ DV_DONVI + " TEXT ," + DV_FTGIAMGIA + " TEXT, " + DV_MAHD
				+ " TEXT," + DV_MANVPV + " TEXT," + DV_MANVCB + " TEXT,"
				+ DV_MACAPV + " TEXT," + DV_THOIDIEMPV + " TEXT,"
				+ DV_TRANGTHAI + " TEXT," + DV_INYC + " TEXT," + DV_GHICHU
				+ " TEXT" + ")";
		db.execSQL(sqlCreate_TableDV);

		String sqlCreate_TableDMDV = "CREATE TABLE " + TABLE_DMDV + " ("
				+ DMDV_ID + " INTEGER PRIMARY KEY," + DMDV_MADV + " TEXT,"
				+ DMDV_MAVACH + " TEXT , " + DMDV_TENDV + " TEXT , "
				+ DMDV_TENTANH + " TEXT , " + DMDV_CHINHANH + " TEXT ,"
				+ DMDV_KHUVUC + " TEXT ," + DMDV_NHOMDV + " TEXT ,"
				+ DMDV_DONGIA + " TEXT, " + DMDV_GIAKM1 + " TEXT,"
				+ DMDV_GIAKM2 + " TEXT," + DMDV_FTGIAMGIA + " TEXT,"
				+ DMDV_CAUHINHGIAMGIA + " TEXT," + DMDV_DONGIACHEBIEN
				+ " TEXT," + DMDV_DONVI + " TEXT," + DMDV_THUOCTINHIN
				+ " TEXT," + DMDV_CHOPHEPBAN + " TEXT," + DMDV_ANH + " TEXT,"
				+ DMDV_MANV + " TEXT," + DMDV_NGAYSUAGIA + " TEXT,"
				+ DMDV_GHICHU + " TEXT" + ")";
		db.execSQL(sqlCreate_TableDMDV);

		String sqlCreate_TableKH = "CREATE TABLE " + TABLE_KH + " (" + KH_ID
				+ " INTEGER PRIMARY KEY," + KH_TENKH + " TEXT NOT NULL, "
				+ KH_NGAYSINH + " TEXT , " + KH_DIENTHOAI + " TEXT , "
				+ KH_DIACHI + " TEXT ," + KH_NHOMKH + " TEXT ," + KH_CAPBAC
				+ " TEXT ," + KH_DIEMTICHLUYHIENTAI + " TEXT, " + KH_NGAYDANGKY
				+ " TEXT," + KH_NGAYHETHAN + " TEXT," + KH_ANHDAIDIEN
				+ " TEXT," + KH_FTGIAMDVTINHGIO + " TEXT," + KH_FTGIAMDVKHAC
				+ " TEXT" + ")";
		db.execSQL(sqlCreate_TableKH);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NV);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_KH);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CTN);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DV);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DMDV);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_HD);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CD);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CH);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CN);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new NhanVien
	public int addNhanVien(NV_NhanVien nhanvien) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		int insert_stt = 0;

		values.put(NV_MANV, nhanvien.getMaNV());
		values.put(NV_MATKHAU, nhanvien.getMatKhau());
		values.put(NV_MATHE, nhanvien.getMaThe());
		values.put(NV_TRANGTHAI, nhanvien.getTrangThai());
		values.put(NV_TEN, nhanvien.getHoTen());
		values.put(NV_NGAYSINH, nhanvien.getNgaySinh());
		values.put(NV_QUEQUAN, nhanvien.getQueQuan());
		values.put(NV_GIOITINH, nhanvien.getGioiTinh());
		values.put(NV_CMTND, nhanvien.getCMTND());
		values.put(NV_NGAYCAP, nhanvien.getNgayCap());
		values.put(NV_NOICAP, nhanvien.getNoiCap());
		values.put(NV_GHICHU, nhanvien.getGhiChu());
		values.put(NV_CHINHANH, nhanvien.getChiNhanh());
		values.put(NV_CHUCDANH, nhanvien.getChucDanh());
		values.put(NV_BOPHAN, nhanvien.getBoPhan());
		values.put(NV_LOAIHOPDONG, nhanvien.getLoaiHopDong());
		values.put(NV_NGAYVAOLAM, nhanvien.getNgayLam());
		values.put(NV_NGAYNGHIVIEC, nhanvien.getNgayNghi());
		values.put(NV_HESOLUONGCUNG, nhanvien.getHSLuong());
		values.put(NV_LUONGPHUCAP, nhanvien.getTroCap());
		values.put(NV_LUONGTHUONGTHANG, nhanvien.getThangThuong());
		values.put(NV_PHATTHANG, nhanvien.getThangPhat());
		values.put(NV_DATCOC, nhanvien.getDatCoc());
		values.put(NV_LANTRUYCAPCUOI, "");
		values.put(NV_CAUHINH, nhanvien.getCauHinh());

		// Inserting Row
		insert_stt = (int) db.insert(TABLE_NV, null, values);
		db.close(); // Closing database connection
		return insert_stt;
	}

	// Getting All Nhanvien
	public List<NV_NhanVien> getAllNhanVien() {
		List<NV_NhanVien> nhanvienList = new ArrayList<NV_NhanVien>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_NV;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_NV, null, null, null, null, null, null);

		int k = cursor.getCount();
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				NV_NhanVien nhanvien = new NV_NhanVien();
				nhanvien.setID(Integer.parseInt(cursor.getString(0)));
				nhanvien.setMaNV(cursor.getString(cursor
						.getColumnIndex(NV_MANV)));
				nhanvien.setMatKhau(cursor.getString(cursor
						.getColumnIndex(NV_MATKHAU)));
				nhanvien.setMaThe(cursor.getString(cursor
						.getColumnIndex(NV_MATHE)));
				nhanvien.setTrangThai(cursor.getString(cursor
						.getColumnIndex(NV_TRANGTHAI)));
				nhanvien.setHoTen(cursor.getString(cursor
						.getColumnIndex(NV_TEN)));
				nhanvien.setNgaySinh(cursor.getString(cursor
						.getColumnIndex(NV_NGAYSINH)));
				nhanvien.setQueQuan(cursor.getString(cursor
						.getColumnIndex(NV_QUEQUAN)));
				nhanvien.setGioiTinh(cursor.getString(cursor
						.getColumnIndex(NV_GIOITINH)));
				nhanvien.setCMTND(cursor.getString(cursor
						.getColumnIndex(NV_CMTND)));
				nhanvien.setNgayCap(cursor.getString(cursor
						.getColumnIndex(NV_NGAYCAP)));
				nhanvien.setNoiCap(cursor.getString(cursor
						.getColumnIndex(NV_NOICAP)));
				nhanvien.setGhiChu(cursor.getString(cursor
						.getColumnIndex(NV_GHICHU)));
				nhanvien.setChiNhanh(cursor.getString(cursor
						.getColumnIndex(NV_CHINHANH)));
				nhanvien.setChucDanh(cursor.getString(cursor
						.getColumnIndex(NV_CHUCDANH)));
				nhanvien.setBoPhan(cursor.getString(cursor
						.getColumnIndex(NV_BOPHAN)));
				nhanvien.setLoaiHopDong(cursor.getString(cursor
						.getColumnIndex(NV_LOAIHOPDONG)));
				nhanvien.setNgayLam(cursor.getString(cursor
						.getColumnIndex(NV_NGAYVAOLAM)));
				nhanvien.setNgayNghi(cursor.getString(cursor
						.getColumnIndex(NV_NGAYNGHIVIEC)));
				nhanvien.setHSLuong(cursor.getString(cursor
						.getColumnIndex(NV_HESOLUONGCUNG)));
				nhanvien.setTroCap(cursor.getString(cursor
						.getColumnIndex(NV_LUONGPHUCAP)));
				nhanvien.setThangThuong(cursor.getString(cursor
						.getColumnIndex(NV_LUONGTHUONGTHANG)));
				nhanvien.setThangPhat(cursor.getString(cursor
						.getColumnIndex(NV_PHATTHANG)));
				nhanvien.setDatCoc(cursor.getString(cursor
						.getColumnIndex(NV_DATCOC)));
				nhanvien.setLanTruyCapCuoi(cursor.getString(cursor
						.getColumnIndex(NV_LANTRUYCAPCUOI)));
				nhanvien.setCauHinh(cursor.getString(cursor
						.getColumnIndex(NV_CAUHINH)));
				// Adding contact to list
				nhanvienList.add(nhanvien);
			} while (cursor.moveToNext());
		}

		// return contact list
		return nhanvienList;
	}

	// Getting single nhanvien byID
	public NV_NhanVien getNhanvienByID(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NV, new String[] { NV_ID, NV_MANV,
				NV_MATKHAU, NV_MATHE, NV_TRANGTHAI, NV_TEN, NV_NGAYSINH,
				NV_QUEQUAN, NV_GIOITINH, NV_CMTND, NV_NGAYCAP, NV_NOICAP,
				NV_GHICHU, NV_CHINHANH, NV_CHUCDANH, NV_BOPHAN, NV_LOAIHOPDONG,
				NV_LANTRUYCAPCUOI, NV_NGAYVAOLAM, NV_NGAYNGHIVIEC,
				NV_HESOLUONGCUNG, NV_LUONGPHUCAP, NV_LUONGTHUONGTHANG,
				NV_PHATTHANG, NV_DATCOC, NV_CAUHINH }, NV_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		NV_NhanVien nhanvien = new NV_NhanVien();
		nhanvien.setID(cursor.getInt(cursor.getColumnIndex(NV_ID)));
		nhanvien.setMaNV(cursor.getString(cursor.getColumnIndex(NV_MANV)));
		nhanvien.setHoTen(cursor.getString(cursor.getColumnIndex(NV_TEN)));
		nhanvien.setMatKhau(cursor.getString(cursor.getColumnIndex(NV_MATKHAU)));
		nhanvien.setMaThe(cursor.getString(cursor.getColumnIndex(NV_MATHE)));
		nhanvien.setChiNhanh(cursor.getString(cursor
				.getColumnIndex(NV_CHINHANH)));
		nhanvien.setBoPhan(cursor.getString(cursor.getColumnIndex(NV_BOPHAN)));
		nhanvien.setChucDanh(cursor.getString(cursor
				.getColumnIndex(NV_CHUCDANH)));
		nhanvien.setGioiTinh(cursor.getString(cursor
				.getColumnIndex(NV_GIOITINH)));
		nhanvien.setNgaySinh(cursor.getString(cursor
				.getColumnIndex(NV_NGAYSINH)));
		nhanvien.setQueQuan(cursor.getString(cursor.getColumnIndex(NV_QUEQUAN)));
		nhanvien.setCMTND(cursor.getString(cursor.getColumnIndex(NV_CMTND)));
		nhanvien.setNgayCap(cursor.getString(cursor.getColumnIndex(NV_NGAYCAP)));
		nhanvien.setNoiCap(cursor.getString(cursor.getColumnIndex(NV_NOICAP)));
		nhanvien.setHSLuong(cursor.getString(cursor
				.getColumnIndex(NV_HESOLUONGCUNG)));
		nhanvien.setTroCap(cursor.getString(cursor
				.getColumnIndex(NV_LUONGPHUCAP)));
		nhanvien.setThangThuong(cursor.getString(cursor
				.getColumnIndex(NV_LUONGTHUONGTHANG)));
		nhanvien.setThangPhat(cursor.getString(cursor
				.getColumnIndex(NV_PHATTHANG)));
		nhanvien.setDatCoc(cursor.getString(cursor.getColumnIndex(NV_DATCOC)));
		nhanvien.setNgayLam(cursor.getString(cursor
				.getColumnIndex(NV_NGAYVAOLAM)));
		nhanvien.setNgayNghi(cursor.getString(cursor
				.getColumnIndex(NV_NGAYNGHIVIEC)));
		nhanvien.setTrangThai(cursor.getString(cursor
				.getColumnIndex(NV_TRANGTHAI)));
		nhanvien.setLoaiHopDong(cursor.getString(cursor
				.getColumnIndex(NV_LOAIHOPDONG)));
		nhanvien.setLanTruyCapCuoi(cursor.getString(cursor
				.getColumnIndex(NV_LANTRUYCAPCUOI)));
		nhanvien.setGhiChu(cursor.getString(cursor.getColumnIndex(NV_GHICHU)));
		nhanvien.setCauHinh(cursor.getString(cursor.getColumnIndex(NV_CAUHINH)));

		cursor.close();
		return nhanvien;
	}

	// Getting single nhanvien byUser
	public NV_NhanVien getNhanvienByUser(String user) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NV, new String[] { NV_ID, NV_MANV,
				NV_MATKHAU, NV_MATHE, NV_TRANGTHAI, NV_TEN, NV_NGAYSINH,
				NV_QUEQUAN, NV_GIOITINH, NV_CMTND, NV_NGAYCAP, NV_NOICAP,
				NV_GHICHU, NV_CHINHANH, NV_CHUCDANH, NV_BOPHAN, NV_LOAIHOPDONG,
				NV_LANTRUYCAPCUOI, NV_NGAYVAOLAM, NV_NGAYNGHIVIEC,
				NV_HESOLUONGCUNG, NV_LUONGPHUCAP, NV_LUONGTHUONGTHANG,
				NV_PHATTHANG, NV_DATCOC, NV_CAUHINH }, NV_MANV + "=?",
				new String[] { String.valueOf(user) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		NV_NhanVien nhanvien = new NV_NhanVien();
		nhanvien.setID(cursor.getInt(cursor.getColumnIndex(NV_ID)));
		nhanvien.setMaNV(cursor.getString(cursor.getColumnIndex(NV_MANV)));
		nhanvien.setHoTen(cursor.getString(cursor.getColumnIndex(NV_TEN)));
		nhanvien.setMatKhau(cursor.getString(cursor.getColumnIndex(NV_MATKHAU)));
		nhanvien.setMaThe(cursor.getString(cursor.getColumnIndex(NV_MATHE)));
		nhanvien.setChiNhanh(cursor.getString(cursor
				.getColumnIndex(NV_CHINHANH)));
		nhanvien.setBoPhan(cursor.getString(cursor.getColumnIndex(NV_BOPHAN)));
		nhanvien.setChucDanh(cursor.getString(cursor
				.getColumnIndex(NV_CHUCDANH)));
		nhanvien.setGioiTinh(cursor.getString(cursor
				.getColumnIndex(NV_GIOITINH)));
		nhanvien.setNgaySinh(cursor.getString(cursor
				.getColumnIndex(NV_NGAYSINH)));
		nhanvien.setQueQuan(cursor.getString(cursor.getColumnIndex(NV_QUEQUAN)));
		nhanvien.setCMTND(cursor.getString(cursor.getColumnIndex(NV_CMTND)));
		nhanvien.setNgayCap(cursor.getString(cursor.getColumnIndex(NV_NGAYCAP)));
		nhanvien.setNoiCap(cursor.getString(cursor.getColumnIndex(NV_NOICAP)));
		nhanvien.setHSLuong(cursor.getString(cursor
				.getColumnIndex(NV_HESOLUONGCUNG)));
		nhanvien.setTroCap(cursor.getString(cursor
				.getColumnIndex(NV_LUONGPHUCAP)));
		nhanvien.setThangThuong(cursor.getString(cursor
				.getColumnIndex(NV_LUONGTHUONGTHANG)));
		nhanvien.setThangPhat(cursor.getString(cursor
				.getColumnIndex(NV_PHATTHANG)));
		nhanvien.setDatCoc(cursor.getString(cursor.getColumnIndex(NV_DATCOC)));
		nhanvien.setNgayLam(cursor.getString(cursor
				.getColumnIndex(NV_NGAYVAOLAM)));
		nhanvien.setNgayNghi(cursor.getString(cursor
				.getColumnIndex(NV_NGAYNGHIVIEC)));
		nhanvien.setTrangThai(cursor.getString(cursor
				.getColumnIndex(NV_TRANGTHAI)));
		nhanvien.setLoaiHopDong(cursor.getString(cursor
				.getColumnIndex(NV_LOAIHOPDONG)));
		nhanvien.setLanTruyCapCuoi(cursor.getString(cursor
				.getColumnIndex(NV_LANTRUYCAPCUOI)));
		nhanvien.setGhiChu(cursor.getString(cursor.getColumnIndex(NV_GHICHU)));
		nhanvien.setCauHinh(cursor.getString(cursor.getColumnIndex(NV_CAUHINH)));

		cursor.close();
		return nhanvien;
	}

	// Updating single nhanvien
	public int updateNhanVien(NV_NhanVien nhanvien) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(NV_MANV, nhanvien.getMaNV());
		values.put(NV_MATKHAU, nhanvien.getMatKhau());
		values.put(NV_MATHE, nhanvien.getMaThe());
		values.put(NV_TRANGTHAI, nhanvien.getTrangThai());
		values.put(NV_TEN, nhanvien.getHoTen());
		values.put(NV_NGAYSINH, nhanvien.getNgaySinh());
		values.put(NV_QUEQUAN, nhanvien.getQueQuan());
		values.put(NV_GIOITINH, nhanvien.getGioiTinh());
		values.put(NV_CMTND, nhanvien.getCMTND());
		values.put(NV_NGAYCAP, nhanvien.getNgayCap());
		values.put(NV_NOICAP, nhanvien.getNoiCap());
		values.put(NV_GHICHU, nhanvien.getGhiChu());
		values.put(NV_CHINHANH, nhanvien.getChiNhanh());
		values.put(NV_CHUCDANH, nhanvien.getChucDanh());
		values.put(NV_BOPHAN, nhanvien.getBoPhan());
		values.put(NV_LOAIHOPDONG, nhanvien.getLoaiHopDong());
		values.put(NV_NGAYVAOLAM, nhanvien.getNgayLam());
		values.put(NV_NGAYNGHIVIEC, nhanvien.getNgayNghi());
		values.put(NV_HESOLUONGCUNG, nhanvien.getHSLuong());
		values.put(NV_LUONGPHUCAP, nhanvien.getTroCap());
		values.put(NV_LUONGTHUONGTHANG, nhanvien.getThangThuong());
		values.put(NV_PHATTHANG, nhanvien.getThangPhat());
		values.put(NV_DATCOC, nhanvien.getDatCoc());
		values.put(NV_LANTRUYCAPCUOI, "");
		values.put(NV_CAUHINH, nhanvien.getCauHinh());

		// updating row
		return db.update(TABLE_NV, values, NV_ID + " = ?",
				new String[] { String.valueOf(nhanvien.getID()) });
	}

	// Deleting single nhanvien
	public void deleteNhanvien(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_NV, NV_ID + " = ?", new String[] { String.valueOf(id) });
		db.close();
	}

	// Getting nhanvien Count
	public int getNhanvienCount() {
		String countQuery = "SELECT  * FROM " + TABLE_NV;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int nv_count = cursor.getCount();
		cursor.close();

		return nv_count;
	}

	// /////////////////////////////////////////////////////////////////////
	// Adding new HD
	void addNewHD(HD_HoaDon hoadon) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(HD_LOAIHD, hoadon.getType());
		values.put(HD_CHINHANH, hoadon.getChiNhanh());
		values.put(HD_KHUVUC, hoadon.getKhuVuc());
		values.put(HD_MACABD, hoadon.getMaCaBD());
		values.put(HD_MACAKT, hoadon.getMaCaKT());
		values.put(HD_TDBATDAU, hoadon.getTdBatDau());
		values.put(HD_TDKETHUC, hoadon.getTdKetThuc());
		values.put(HD_MANVTNBD, hoadon.getMaNVTnBD());
		values.put(HD_MANVTNKT, hoadon.getMaCaKT());
		values.put(HD_MAKH, hoadon.getMaKH());
		values.put(HD_FTDIEUCHINH, hoadon.getFtDieuChinh());
		values.put(HD_TONGTIENTHU, hoadon.getTongTienPhaiThu());
		values.put(HD_TTTIENMAT_VND, hoadon.getTtTienMat_VND());
		values.put(HD_TTTIENMAT_USD, hoadon.getTtTienMat_USD());
		values.put(HD_TTTIENMAT_EUR, hoadon.getTienMatTT_EUR());
		values.put(HD_TTTHE_TONGTIEN, hoadon.getTtThe_TongTien());
		values.put(HD_TTTHE_THELOAI, hoadon.getTtThe_Loai());
		values.put(HD_TTHE_MAGIAODICH, hoadon.getTtThe_MaGiaoDich());
		values.put(HD_TTHE_NGOAITE, hoadon.getTtThe_NgoaiTe());
		values.put(HD_TRANGTHAI, hoadon.getTrangThai());
		values.put(HD_SOLANTACHHD, hoadon.getSoLanTachHD());
		values.put(HD_GHICHU, hoadon.getGhiChu());

		// Inserting Row
		int k = (int) db.insert(TABLE_HD, null, values);
		db.close(); // Closing database connection
	}

	// Getting All HoaDon
	public List<HD_HoaDon> getAllHoaDon() {
		List<HD_HoaDon> hoadonList = new ArrayList<HD_HoaDon>();
		// Select All Query
		// String selectQuery = "SELECT  * FROM " + TABLE_HD;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_HD, null, null, null, null, null, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				HD_HoaDon hoadon = new HD_HoaDon();
				hoadon.setID(Integer.parseInt(cursor.getString(0)));
				hoadon.setType(cursor.getString(cursor
						.getColumnIndex(HD_LOAIHD)));
				hoadon.setChiNhanh(cursor.getString(cursor
						.getColumnIndex(HD_CHINHANH)));
				hoadon.setKhuVuc(cursor.getString(cursor
						.getColumnIndex(HD_KHUVUC)));
				hoadon.setMaCaBD(cursor.getString(cursor
						.getColumnIndex(HD_MACABD)));
				hoadon.setMaCaKT(cursor.getString(cursor
						.getColumnIndex(HD_MACAKT)));
				hoadon.setTdBatDau(cursor.getString(cursor
						.getColumnIndex(HD_TDBATDAU)));
				hoadon.setTdKetThuc(cursor.getString(cursor
						.getColumnIndex(HD_TDKETHUC)));
				hoadon.setMaNVTnBD(cursor.getString(cursor
						.getColumnIndex(HD_MANVTNBD)));
				hoadon.setMaNVTnKT(cursor.getString(cursor
						.getColumnIndex(HD_MANVTNKT)));
				hoadon.setMaKH(cursor.getString(cursor.getColumnIndex(HD_MAKH)));
				hoadon.setFtDieuChinh(cursor.getString(cursor
						.getColumnIndex(HD_FTDIEUCHINH)));
				hoadon.setTongTienPhaiThu(cursor.getString(cursor
						.getColumnIndex(HD_TONGTIENTHU)));
				hoadon.setTtTienMat_VND(cursor.getString(cursor
						.getColumnIndex(HD_TTTIENMAT_VND)));
				hoadon.setTtTienMat_USD(cursor.getString(cursor
						.getColumnIndex(HD_TTTIENMAT_USD)));
				hoadon.setTienMatTT_EUR(cursor.getString(cursor
						.getColumnIndex(HD_TTTIENMAT_EUR)));
				hoadon.setTtThe_TongTien(cursor.getString(cursor
						.getColumnIndex(HD_TTTHE_TONGTIEN)));
				hoadon.setTtThe_Loai(cursor.getString(cursor
						.getColumnIndex(HD_TTTHE_THELOAI)));
				hoadon.setTtThe_MaGiaoDich(cursor.getString(cursor
						.getColumnIndex(HD_TTHE_MAGIAODICH)));
				hoadon.setTtThe_NgoaiTe(cursor.getString(cursor
						.getColumnIndex(HD_TTHE_NGOAITE)));
				hoadon.setTrangThai(cursor.getString(cursor
						.getColumnIndex(HD_TRANGTHAI)));
				hoadon.setSoLanTachHD(cursor.getString(cursor
						.getColumnIndex(HD_SOLANTACHHD)));
				hoadon.setGhiChu(cursor.getString(cursor
						.getColumnIndex(HD_GHICHU)));

				// Adding contact to list
				hoadonList.add(hoadon);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return hoadonList;
	}

	// Getting single hoadon
	public HD_HoaDon getHoaDon(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_HD, null, HD_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		HD_HoaDon hoadon = new HD_HoaDon();
		hoadon.setID(cursor.getInt(cursor.getColumnIndex(HD_ID)));
		hoadon.setType(cursor.getString(cursor.getColumnIndex(HD_LOAIHD)));
		hoadon.setChiNhanh(cursor.getString(cursor.getColumnIndex(HD_CHINHANH)));
		hoadon.setKhuVuc(cursor.getString(cursor.getColumnIndex(HD_KHUVUC)));
		hoadon.setMaCaBD(cursor.getString(cursor.getColumnIndex(HD_MACABD)));
		hoadon.setMaCaKT(cursor.getString(cursor.getColumnIndex(HD_MACAKT)));
		hoadon.setTdBatDau(cursor.getString(cursor.getColumnIndex(HD_TDBATDAU)));
		hoadon.setTdKetThuc(cursor.getString(cursor.getColumnIndex(HD_TDKETHUC)));
		hoadon.setMaNVTnBD(cursor.getString(cursor.getColumnIndex(HD_MANVTNBD)));
		hoadon.setMaNVTnKT(cursor.getString(cursor.getColumnIndex(HD_MANVTNKT)));
		hoadon.setMaKH(cursor.getString(cursor.getColumnIndex(HD_MAKH)));
		hoadon.setFtDieuChinh(cursor.getString(cursor
				.getColumnIndex(HD_FTDIEUCHINH)));
		hoadon.setTongTienPhaiThu(cursor.getString(cursor
				.getColumnIndex(HD_TONGTIENTHU)));
		hoadon.setTtTienMat_VND(cursor.getString(cursor
				.getColumnIndex(HD_TTTIENMAT_VND)));
		hoadon.setTtTienMat_USD(cursor.getString(cursor
				.getColumnIndex(HD_TTTIENMAT_USD)));
		hoadon.setTienMatTT_EUR(cursor.getString(cursor
				.getColumnIndex(HD_TTTIENMAT_EUR)));
		hoadon.setTtThe_TongTien(cursor.getString(cursor
				.getColumnIndex(HD_TTTHE_TONGTIEN)));
		hoadon.setTtThe_Loai(cursor.getString(cursor
				.getColumnIndex(HD_TTTHE_THELOAI)));
		hoadon.setTtThe_MaGiaoDich(cursor.getString(cursor
				.getColumnIndex(HD_TTHE_MAGIAODICH)));
		hoadon.setTtThe_NgoaiTe(cursor.getString(cursor
				.getColumnIndex(HD_TTHE_NGOAITE)));
		hoadon.setTrangThai(cursor.getString(cursor
				.getColumnIndex(HD_TRANGTHAI)));
		hoadon.setSoLanTachHD(cursor.getString(cursor
				.getColumnIndex(HD_SOLANTACHHD)));
		hoadon.setGhiChu(cursor.getString(cursor.getColumnIndex(HD_GHICHU)));

		cursor.close();
		return hoadon;
	}

	// Getting single hoadon
	public HD_HoaDon getLastHoaDon() {

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_HD, null, null, null, null, null, HD_ID
				+ " DESC LIMIT 1");
		if (cursor != null)
			cursor.moveToFirst();

		HD_HoaDon hoadon = new HD_HoaDon();
		hoadon.setID(cursor.getInt(cursor.getColumnIndex(HD_ID)));
		hoadon.setChiNhanh(cursor.getString(cursor.getColumnIndex(HD_LOAIHD)));
		hoadon.setChiNhanh(cursor.getString(cursor.getColumnIndex(HD_CHINHANH)));
		hoadon.setKhuVuc(cursor.getString(cursor.getColumnIndex(HD_KHUVUC)));
		hoadon.setMaCaBD(cursor.getString(cursor.getColumnIndex(HD_MACABD)));
		hoadon.setMaCaKT(cursor.getString(cursor.getColumnIndex(HD_MACAKT)));
		hoadon.setTdBatDau(cursor.getString(cursor.getColumnIndex(HD_TDBATDAU)));
		hoadon.setTdKetThuc(cursor.getString(cursor.getColumnIndex(HD_TDKETHUC)));
		hoadon.setMaNVTnBD(cursor.getString(cursor.getColumnIndex(HD_MANVTNBD)));
		hoadon.setMaNVTnKT(cursor.getString(cursor.getColumnIndex(HD_MANVTNKT)));
		hoadon.setMaKH(cursor.getString(cursor.getColumnIndex(HD_MAKH)));
		hoadon.setFtDieuChinh(cursor.getString(cursor
				.getColumnIndex(HD_FTDIEUCHINH)));
		hoadon.setTongTienPhaiThu(cursor.getString(cursor
				.getColumnIndex(HD_TONGTIENTHU)));
		hoadon.setTtTienMat_VND(cursor.getString(cursor
				.getColumnIndex(HD_TTTIENMAT_VND)));
		hoadon.setTtTienMat_USD(cursor.getString(cursor
				.getColumnIndex(HD_TTTIENMAT_USD)));
		hoadon.setTienMatTT_EUR(cursor.getString(cursor
				.getColumnIndex(HD_TTTIENMAT_EUR)));
		hoadon.setTtThe_TongTien(cursor.getString(cursor
				.getColumnIndex(HD_TTTHE_TONGTIEN)));
		hoadon.setTtThe_Loai(cursor.getString(cursor
				.getColumnIndex(HD_TTTHE_THELOAI)));
		hoadon.setTtThe_MaGiaoDich(cursor.getString(cursor
				.getColumnIndex(HD_TTHE_MAGIAODICH)));
		hoadon.setTtThe_NgoaiTe(cursor.getString(cursor
				.getColumnIndex(HD_TTHE_NGOAITE)));
		hoadon.setTrangThai(cursor.getString(cursor
				.getColumnIndex(HD_TRANGTHAI)));
		hoadon.setSoLanTachHD(cursor.getString(cursor
				.getColumnIndex(HD_SOLANTACHHD)));
		hoadon.setGhiChu(cursor.getString(cursor.getColumnIndex(HD_GHICHU)));

		cursor.close();
		return hoadon;
	}

	// Getting single hoadon
		public HD_HoaDon getHoaDonByKV_CTT(String khuvuc) {
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery("select * from " + TABLE_HD + " where "
					+ HD_TRANGTHAI + "='ChuaTT' and " + HD_KHUVUC + "='"
					+ khuvuc + "'", null);
			if (cursor != null)
				cursor.moveToFirst();

			HD_HoaDon hoadon = new HD_HoaDon();
			hoadon.setID(cursor.getInt(cursor.getColumnIndex(HD_ID)));
			hoadon.setChiNhanh(cursor.getString(cursor.getColumnIndex(HD_LOAIHD)));
			hoadon.setChiNhanh(cursor.getString(cursor.getColumnIndex(HD_CHINHANH)));
			hoadon.setKhuVuc(cursor.getString(cursor.getColumnIndex(HD_KHUVUC)));
			hoadon.setMaCaBD(cursor.getString(cursor.getColumnIndex(HD_MACABD)));
			hoadon.setMaCaKT(cursor.getString(cursor.getColumnIndex(HD_MACAKT)));
			hoadon.setTdBatDau(cursor.getString(cursor.getColumnIndex(HD_TDBATDAU)));
			hoadon.setTdKetThuc(cursor.getString(cursor.getColumnIndex(HD_TDKETHUC)));
			hoadon.setMaNVTnBD(cursor.getString(cursor.getColumnIndex(HD_MANVTNBD)));
			hoadon.setMaNVTnKT(cursor.getString(cursor.getColumnIndex(HD_MANVTNKT)));
			hoadon.setMaKH(cursor.getString(cursor.getColumnIndex(HD_MAKH)));
			hoadon.setFtDieuChinh(cursor.getString(cursor
					.getColumnIndex(HD_FTDIEUCHINH)));
			hoadon.setTongTienPhaiThu(cursor.getString(cursor
					.getColumnIndex(HD_TONGTIENTHU)));
			hoadon.setTtTienMat_VND(cursor.getString(cursor
					.getColumnIndex(HD_TTTIENMAT_VND)));
			hoadon.setTtTienMat_USD(cursor.getString(cursor
					.getColumnIndex(HD_TTTIENMAT_USD)));
			hoadon.setTienMatTT_EUR(cursor.getString(cursor
					.getColumnIndex(HD_TTTIENMAT_EUR)));
			hoadon.setTtThe_TongTien(cursor.getString(cursor
					.getColumnIndex(HD_TTTHE_TONGTIEN)));
			hoadon.setTtThe_Loai(cursor.getString(cursor
					.getColumnIndex(HD_TTTHE_THELOAI)));
			hoadon.setTtThe_MaGiaoDich(cursor.getString(cursor
					.getColumnIndex(HD_TTHE_MAGIAODICH)));
			hoadon.setTtThe_NgoaiTe(cursor.getString(cursor
					.getColumnIndex(HD_TTHE_NGOAITE)));
			hoadon.setTrangThai(cursor.getString(cursor
					.getColumnIndex(HD_TRANGTHAI)));
			hoadon.setSoLanTachHD(cursor.getString(cursor
					.getColumnIndex(HD_SOLANTACHHD)));
			hoadon.setGhiChu(cursor.getString(cursor.getColumnIndex(HD_GHICHU)));

			cursor.close();
			return hoadon;
		}
	
	// get hoadon co dinh ChuaThanhToan by chinhanh
	public List<HD_HoaDon> getHoaDonCD_CTT(String chinhanh) {
		List<HD_HoaDon> hoadonList = new ArrayList<HD_HoaDon>();
		String status = "ChuaTT";

		SQLiteDatabase db = this.getWritableDatabase();
		try {
			// Cursor cursor = db.query(false, TABLE_HD, null, HD_TRANGTHAI +
			// "=" + status , null, null, null, null, null);
			Cursor cursor = db.rawQuery("select * from " + TABLE_HD + " where "
					+ HD_TRANGTHAI + "='ChuaTT' and  " + HD_LOAIHD + "='CD'" + "and " + HD_CHINHANH + "='"
					+ chinhanh + "'", null);
			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					HD_HoaDon hoadon = new HD_HoaDon();
					hoadon.setID(Integer.parseInt(cursor.getString(0)));
					hoadon.setChiNhanh(cursor.getString(cursor
							.getColumnIndex(HD_LOAIHD)));
					hoadon.setChiNhanh(cursor.getString(cursor
							.getColumnIndex(HD_CHINHANH)));
					hoadon.setKhuVuc(cursor.getString(cursor
							.getColumnIndex(HD_KHUVUC)));
					hoadon.setMaCaBD(cursor.getString(cursor
							.getColumnIndex(HD_MACABD)));
					hoadon.setMaCaKT(cursor.getString(cursor
							.getColumnIndex(HD_MACAKT)));
					hoadon.setTdBatDau(cursor.getString(cursor
							.getColumnIndex(HD_TDBATDAU)));
					hoadon.setTdKetThuc(cursor.getString(cursor
							.getColumnIndex(HD_TDKETHUC)));
					hoadon.setMaNVTnBD(cursor.getString(cursor
							.getColumnIndex(HD_MANVTNBD)));
					hoadon.setMaNVTnKT(cursor.getString(cursor
							.getColumnIndex(HD_MANVTNKT)));
					hoadon.setMaKH(cursor.getString(cursor
							.getColumnIndex(HD_MAKH)));
					hoadon.setFtDieuChinh(cursor.getString(cursor
							.getColumnIndex(HD_FTDIEUCHINH)));
					hoadon.setTongTienPhaiThu(cursor.getString(cursor
							.getColumnIndex(HD_TONGTIENTHU)));
					hoadon.setTtTienMat_VND(cursor.getString(cursor
							.getColumnIndex(HD_TTTIENMAT_VND)));
					hoadon.setTtTienMat_USD(cursor.getString(cursor
							.getColumnIndex(HD_TTTIENMAT_USD)));
					hoadon.setTienMatTT_EUR(cursor.getString(cursor
							.getColumnIndex(HD_TTTIENMAT_EUR)));
					hoadon.setTtThe_TongTien(cursor.getString(cursor
							.getColumnIndex(HD_TTTHE_TONGTIEN)));
					hoadon.setTtThe_Loai(cursor.getString(cursor
							.getColumnIndex(HD_TTTHE_THELOAI)));
					hoadon.setTtThe_MaGiaoDich(cursor.getString(cursor
							.getColumnIndex(HD_TTHE_MAGIAODICH)));
					hoadon.setTtThe_NgoaiTe(cursor.getString(cursor
							.getColumnIndex(HD_TTHE_NGOAITE)));
					hoadon.setTrangThai(cursor.getString(cursor
							.getColumnIndex(HD_TRANGTHAI)));
					hoadon.setSoLanTachHD(cursor.getString(cursor
							.getColumnIndex(HD_SOLANTACHHD)));
					hoadon.setGhiChu(cursor.getString(cursor
							.getColumnIndex(HD_GHICHU)));

					// Adding contact to list
					hoadonList.add(hoadon);
				} while (cursor.moveToNext());
			}
			cursor.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return hoadonList;
	}

	// get hoadon co dinh ChuaThanhToan by chinhanh
		public List<HD_HoaDon> getHoaDonLN_CTT(String chinhanh) {
			List<HD_HoaDon> hoadonList = new ArrayList<HD_HoaDon>();
			String status = "ChuaTT";

			SQLiteDatabase db = this.getWritableDatabase();
			try {
				// Cursor cursor = db.query(false, TABLE_HD, null, HD_TRANGTHAI +
				// "=" + status , null, null, null, null, null);
				Cursor cursor = db.rawQuery("select * from " + TABLE_HD + " where "
						+ HD_TRANGTHAI + "='ChuaTT' and  " + HD_LOAIHD + "='LN'" + "and " + HD_CHINHANH + "='"
						+ chinhanh + "'", null);
				// looping through all rows and adding to list
				if (cursor.moveToFirst()) {
					do {
						HD_HoaDon hoadon = new HD_HoaDon();
						hoadon.setID(Integer.parseInt(cursor.getString(0)));
						hoadon.setChiNhanh(cursor.getString(cursor
								.getColumnIndex(HD_LOAIHD)));
						hoadon.setChiNhanh(cursor.getString(cursor
								.getColumnIndex(HD_CHINHANH)));
						hoadon.setKhuVuc(cursor.getString(cursor
								.getColumnIndex(HD_KHUVUC)));
						hoadon.setMaCaBD(cursor.getString(cursor
								.getColumnIndex(HD_MACABD)));
						hoadon.setMaCaKT(cursor.getString(cursor
								.getColumnIndex(HD_MACAKT)));
						hoadon.setTdBatDau(cursor.getString(cursor
								.getColumnIndex(HD_TDBATDAU)));
						hoadon.setTdKetThuc(cursor.getString(cursor
								.getColumnIndex(HD_TDKETHUC)));
						hoadon.setMaNVTnBD(cursor.getString(cursor
								.getColumnIndex(HD_MANVTNBD)));
						hoadon.setMaNVTnKT(cursor.getString(cursor
								.getColumnIndex(HD_MANVTNKT)));
						hoadon.setMaKH(cursor.getString(cursor
								.getColumnIndex(HD_MAKH)));
						hoadon.setFtDieuChinh(cursor.getString(cursor
								.getColumnIndex(HD_FTDIEUCHINH)));
						hoadon.setTongTienPhaiThu(cursor.getString(cursor
								.getColumnIndex(HD_TONGTIENTHU)));
						hoadon.setTtTienMat_VND(cursor.getString(cursor
								.getColumnIndex(HD_TTTIENMAT_VND)));
						hoadon.setTtTienMat_USD(cursor.getString(cursor
								.getColumnIndex(HD_TTTIENMAT_USD)));
						hoadon.setTienMatTT_EUR(cursor.getString(cursor
								.getColumnIndex(HD_TTTIENMAT_EUR)));
						hoadon.setTtThe_TongTien(cursor.getString(cursor
								.getColumnIndex(HD_TTTHE_TONGTIEN)));
						hoadon.setTtThe_Loai(cursor.getString(cursor
								.getColumnIndex(HD_TTTHE_THELOAI)));
						hoadon.setTtThe_MaGiaoDich(cursor.getString(cursor
								.getColumnIndex(HD_TTHE_MAGIAODICH)));
						hoadon.setTtThe_NgoaiTe(cursor.getString(cursor
								.getColumnIndex(HD_TTHE_NGOAITE)));
						hoadon.setTrangThai(cursor.getString(cursor
								.getColumnIndex(HD_TRANGTHAI)));
						hoadon.setSoLanTachHD(cursor.getString(cursor
								.getColumnIndex(HD_SOLANTACHHD)));
						hoadon.setGhiChu(cursor.getString(cursor
								.getColumnIndex(HD_GHICHU)));

						// Adding contact to list
						hoadonList.add(hoadon);
					} while (cursor.moveToNext());
				}
				cursor.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			return hoadonList;
		}
	
	// Updating single hoadon
	public int updateHoaDon(HD_HoaDon hoadon) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put(HD_LOAIHD, hoadon.getType());
		values.put(HD_CHINHANH, hoadon.getChiNhanh());
		values.put(HD_KHUVUC, hoadon.getKhuVuc());
		values.put(HD_MACABD, hoadon.getMaCaBD());
		values.put(HD_MACAKT, hoadon.getMaCaKT());
		values.put(HD_TDBATDAU, hoadon.getTdBatDau());
		values.put(HD_TDKETHUC, hoadon.getTdKetThuc());
		values.put(HD_MANVTNBD, hoadon.getMaNVTnBD());
		values.put(HD_MANVTNKT, hoadon.getMaCaKT());
		values.put(HD_MAKH, hoadon.getMaKH());
		values.put(HD_FTDIEUCHINH, hoadon.getFtDieuChinh());
		values.put(HD_TONGTIENTHU, hoadon.getTongTienPhaiThu());
		values.put(HD_TTTIENMAT_VND, hoadon.getTtTienMat_VND());
		values.put(HD_TTTIENMAT_USD, hoadon.getTtTienMat_USD());
		values.put(HD_TTTIENMAT_EUR, hoadon.getTienMatTT_EUR());
		values.put(HD_TTTHE_TONGTIEN, hoadon.getTtThe_TongTien());
		values.put(HD_TTTHE_THELOAI, hoadon.getTtThe_Loai());
		values.put(HD_TTHE_MAGIAODICH, hoadon.getTtThe_MaGiaoDich());
		values.put(HD_TTHE_NGOAITE, hoadon.getTtThe_NgoaiTe());
		values.put(HD_TRANGTHAI, hoadon.getTrangThai());
		values.put(HD_SOLANTACHHD, hoadon.getSoLanTachHD());
		values.put(HD_GHICHU, hoadon.getGhiChu());

		// updating row
		return db.update(TABLE_HD, values, HD_ID + " = ?",
				new String[] { String.valueOf(hoadon.getID()) });
	}

	// Deleting single hoadon
	public void deleteHoaDon(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_HD, HD_ID + " = ?", new String[] { String.valueOf(id) });
		db.close();
	}

	// Delete all hoadon
	public void deleteAllHoaDon() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_HD, null, null);
		db.close();
	}

	// Getting hoadon Count
	public int getHoaDonCount() {
		String countQuery = "SELECT  * FROM " + TABLE_HD;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		return cursor.getCount();
	}

	// /////////////////////////////////////////////////////////////////////
	// Adding new DMDV
	public int addNewDMDV(DMDV_DanhMucDV dichvu) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		int insert_stt = 0;

		String a = dichvu.getThuocTinhIn();
		values.put(DMDV_MADV, dichvu.getMaDV());
		values.put(DMDV_MAVACH, dichvu.getMaVach());
		values.put(DMDV_TENDV, dichvu.getTenDV());
		values.put(DMDV_TENTANH, dichvu.getTenTAnh());
		values.put(DMDV_CHINHANH, dichvu.getChiNhanh());
		values.put(DMDV_KHUVUC, dichvu.getKhuVuc());
		values.put(DMDV_NHOMDV, dichvu.getNhomDV());
		values.put(DMDV_DONGIA, dichvu.getDonGia());
		values.put(DMDV_GIAKM1, dichvu.getGiaKM1());
		values.put(DMDV_GIAKM2, dichvu.getGiaKM2());
		values.put(DMDV_FTGIAMGIA, dichvu.getFtGiamGia());
		values.put(DMDV_CAUHINHGIAMGIA, dichvu.getCauHinhGiamGia());
		values.put(DMDV_DONGIACHEBIEN, dichvu.getDonGiaCheBien());
		values.put(DMDV_DONVI, dichvu.getDonVi());
		values.put(DMDV_THUOCTINHIN, dichvu.getThuocTinhIn());
		values.put(DMDV_CHOPHEPBAN, dichvu.getChoPhepBan());
		values.put(DMDV_ANH, dichvu.getAnh());
		values.put(DMDV_MANV, dichvu.getMaNV());
		values.put(DMDV_NGAYSUAGIA, dichvu.getNgaySuaGia());
		values.put(DMDV_GHICHU, dichvu.getGhiChu());

		// Inserting Row
		insert_stt = (int) db.insert(TABLE_DMDV, null, values);
		db.close(); // Closing database connection
		return insert_stt;
	}

	// Getting All DMDV
	public List<DMDV_DanhMucDV> getAllDMDV() {
		List<DMDV_DanhMucDV> dichvuList = new ArrayList<DMDV_DanhMucDV>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_DMDV;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db
				.query(TABLE_DMDV, null, null, null, null, null, null);

		int k = cursor.getCount();
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				DMDV_DanhMucDV dichvu = new DMDV_DanhMucDV();
				dichvu.setID(Integer.parseInt(cursor.getString(0)));
				dichvu.setMaDV(cursor.getString(cursor
						.getColumnIndex(DMDV_MADV)));
				dichvu.setMaVach(cursor.getString(cursor
						.getColumnIndex(DMDV_MAVACH)));
				dichvu.setTenDV(cursor.getString(cursor
						.getColumnIndex(DMDV_TENDV)));
				dichvu.setTenTAnh(cursor.getString(cursor
						.getColumnIndex(DMDV_TENTANH)));
				dichvu.setChiNhanh(cursor.getString(cursor
						.getColumnIndex(DMDV_CHINHANH)));
				dichvu.setKhuVuc(cursor.getString(cursor
						.getColumnIndex(DMDV_KHUVUC)));
				dichvu.setNhomDV(cursor.getString(cursor
						.getColumnIndex(DMDV_NHOMDV)));
				dichvu.setDonGia(cursor.getString(cursor
						.getColumnIndex(DMDV_DONGIA)));
				dichvu.setGiaKM1(cursor.getString(cursor
						.getColumnIndex(DMDV_GIAKM1)));
				dichvu.setGiaKM2(cursor.getString(cursor
						.getColumnIndex(DMDV_GIAKM2)));
				dichvu.setFtGiamGia(cursor.getString(cursor
						.getColumnIndex(DMDV_FTGIAMGIA)));
				dichvu.setCauHinhGiamGia(cursor.getString(cursor
						.getColumnIndex(DMDV_CAUHINHGIAMGIA)));
				dichvu.setDonGiaCheBien(cursor.getString(cursor
						.getColumnIndex(DMDV_DONGIACHEBIEN)));
				dichvu.setDonVi(cursor.getString(cursor
						.getColumnIndex(DMDV_DONVI)));
				dichvu.setThuocTinhIn(cursor.getString(cursor
						.getColumnIndex(DMDV_THUOCTINHIN)));
				dichvu.setChoPhepBan(cursor.getString(cursor
						.getColumnIndex(DMDV_CHOPHEPBAN)));
				dichvu.setAnh(cursor.getString(cursor.getColumnIndex(DMDV_ANH)));
				dichvu.setMaNV(cursor.getString(cursor
						.getColumnIndex(DMDV_MANV)));
				dichvu.setNgaySuaGia(cursor.getString(cursor
						.getColumnIndex(DMDV_NGAYSUAGIA)));
				dichvu.setGhiChu(cursor.getString(cursor
						.getColumnIndex(DMDV_GHICHU)));

				// Adding contact to list
				dichvuList.add(dichvu);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return dichvuList;
	}

	// Getting single DMDV
	public DMDV_DanhMucDV getDMDV(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_DMDV, new String[] { DMDV_ID,
				DMDV_MAVACH, DMDV_TENDV, DMDV_TENTANH, DMDV_CHINHANH,
				DMDV_KHUVUC, DMDV_NHOMDV, DMDV_DONGIA, DMDV_GIAKM1,
				DMDV_GIAKM2, DMDV_FTGIAMGIA, DMDV_CAUHINHGIAMGIA,
				DMDV_DONGIACHEBIEN, DMDV_DONVI, DMDV_THUOCTINHIN,
				DMDV_CHOPHEPBAN, DMDV_ANH, DMDV_MANV, DMDV_NGAYSUAGIA,
				DMDV_GHICHU }, DMDV_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		DMDV_DanhMucDV dichvu = new DMDV_DanhMucDV();
		dichvu.setID(cursor.getInt(cursor.getColumnIndex(DMDV_ID)));
		dichvu.setMaVach(cursor.getString(cursor.getColumnIndex(DMDV_MAVACH)));
		dichvu.setTenDV(cursor.getString(cursor.getColumnIndex(DMDV_TENDV)));
		dichvu.setTenTAnh(cursor.getString(cursor.getColumnIndex(DMDV_TENTANH)));
		dichvu.setChiNhanh(cursor.getString(cursor
				.getColumnIndex(DMDV_CHINHANH)));
		dichvu.setKhuVuc(cursor.getString(cursor.getColumnIndex(DMDV_KHUVUC)));
		dichvu.setNhomDV(cursor.getString(cursor.getColumnIndex(DMDV_NHOMDV)));
		dichvu.setDonGia(cursor.getString(cursor.getColumnIndex(DMDV_DONGIA)));
		dichvu.setGiaKM1(cursor.getString(cursor.getColumnIndex(DMDV_GIAKM1)));
		dichvu.setGiaKM2(cursor.getString(cursor.getColumnIndex(DMDV_GIAKM2)));
		dichvu.setFtGiamGia(cursor.getString(cursor
				.getColumnIndex(DMDV_FTGIAMGIA)));
		dichvu.setCauHinhGiamGia(cursor.getString(cursor
				.getColumnIndex(DMDV_CAUHINHGIAMGIA)));
		dichvu.setDonGiaCheBien(cursor.getString(cursor
				.getColumnIndex(DMDV_DONGIACHEBIEN)));
		dichvu.setDonVi(cursor.getString(cursor.getColumnIndex(DMDV_DONVI)));
		dichvu.setThuocTinhIn(cursor.getString(cursor
				.getColumnIndex(DMDV_THUOCTINHIN)));
		dichvu.setChoPhepBan(cursor.getString(cursor
				.getColumnIndex(DMDV_CHOPHEPBAN)));
		dichvu.setAnh(cursor.getString(cursor.getColumnIndex(DMDV_ANH)));
		dichvu.setMaNV(cursor.getString(cursor.getColumnIndex(DMDV_MANV)));
		dichvu.setNgaySuaGia(cursor.getString(cursor
				.getColumnIndex(DMDV_NGAYSUAGIA)));
		dichvu.setGhiChu(cursor.getString(cursor.getColumnIndex(DMDV_GHICHU)));

		cursor.close();
		return dichvu;
	}

	// Getting single DMDV
	public DMDV_DanhMucDV getDMDVByName(String name) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_DMDV, new String[] { DMDV_ID, DMDV_MADV,
				DMDV_MAVACH, DMDV_TENDV, DMDV_TENTANH, DMDV_CHINHANH,
				DMDV_KHUVUC, DMDV_NHOMDV, DMDV_DONGIA, DMDV_GIAKM1,
				DMDV_GIAKM2, DMDV_FTGIAMGIA, DMDV_CAUHINHGIAMGIA,
				DMDV_DONGIACHEBIEN, DMDV_DONVI, DMDV_THUOCTINHIN,
				DMDV_CHOPHEPBAN, DMDV_ANH, DMDV_MANV, DMDV_NGAYSUAGIA,
				DMDV_GHICHU }, DMDV_TENDV + "=?",
				new String[] { String.valueOf(name) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		DMDV_DanhMucDV dichvu = new DMDV_DanhMucDV();
		dichvu.setID(cursor.getInt(cursor.getColumnIndex(DMDV_ID)));
		dichvu.setMaDV(cursor.getString(cursor.getColumnIndex(DMDV_MADV)));
		dichvu.setMaVach(cursor.getString(cursor.getColumnIndex(DMDV_MAVACH)));
		dichvu.setTenDV(cursor.getString(cursor.getColumnIndex(DMDV_TENDV)));
		dichvu.setTenTAnh(cursor.getString(cursor.getColumnIndex(DMDV_TENTANH)));
		dichvu.setChiNhanh(cursor.getString(cursor
				.getColumnIndex(DMDV_CHINHANH)));
		dichvu.setKhuVuc(cursor.getString(cursor.getColumnIndex(DMDV_KHUVUC)));
		dichvu.setNhomDV(cursor.getString(cursor.getColumnIndex(DMDV_NHOMDV)));
		dichvu.setDonGia(cursor.getString(cursor.getColumnIndex(DMDV_DONGIA)));
		dichvu.setGiaKM1(cursor.getString(cursor.getColumnIndex(DMDV_GIAKM1)));
		dichvu.setGiaKM2(cursor.getString(cursor.getColumnIndex(DMDV_GIAKM2)));
		dichvu.setFtGiamGia(cursor.getString(cursor
				.getColumnIndex(DMDV_FTGIAMGIA)));
		dichvu.setCauHinhGiamGia(cursor.getString(cursor
				.getColumnIndex(DMDV_CAUHINHGIAMGIA)));
		dichvu.setDonGiaCheBien(cursor.getString(cursor
				.getColumnIndex(DMDV_DONGIACHEBIEN)));
		dichvu.setDonVi(cursor.getString(cursor.getColumnIndex(DMDV_DONVI)));
		dichvu.setThuocTinhIn(cursor.getString(cursor
				.getColumnIndex(DMDV_THUOCTINHIN)));
		dichvu.setChoPhepBan(cursor.getString(cursor
				.getColumnIndex(DMDV_CHOPHEPBAN)));
		dichvu.setAnh(cursor.getString(cursor.getColumnIndex(DMDV_ANH)));
		dichvu.setMaNV(cursor.getString(cursor.getColumnIndex(DMDV_MANV)));
		dichvu.setNgaySuaGia(cursor.getString(cursor
				.getColumnIndex(DMDV_NGAYSUAGIA)));
		dichvu.setGhiChu(cursor.getString(cursor.getColumnIndex(DMDV_GHICHU)));

		cursor.close();
		return dichvu;
	}

	// Updating single DMDV
	public int updateDMDV(DMDV_DanhMucDV dichvu) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put(DMDV_MAVACH, dichvu.getMaVach());
		values.put(DMDV_TENDV, dichvu.getTenDV());
		values.put(DMDV_TENTANH, dichvu.getTenTAnh());
		values.put(DMDV_CHINHANH, dichvu.getChiNhanh());
		values.put(DMDV_KHUVUC, dichvu.getKhuVuc());
		values.put(DMDV_NHOMDV, dichvu.getNhomDV());
		values.put(DMDV_DONGIA, dichvu.getDonGia());
		values.put(DMDV_GIAKM1, dichvu.getGiaKM1());
		values.put(DMDV_GIAKM2, dichvu.getGiaKM2());
		values.put(DMDV_FTGIAMGIA, dichvu.getFtGiamGia());
		values.put(DMDV_CAUHINHGIAMGIA, dichvu.getCauHinhGiamGia());
		values.put(DMDV_DONGIACHEBIEN, dichvu.getDonGiaCheBien());
		values.put(DMDV_DONVI, dichvu.getDonVi());
		values.put(DMDV_THUOCTINHIN, dichvu.getThuocTinhIn());
		values.put(DMDV_CHOPHEPBAN, dichvu.getChoPhepBan());
		values.put(DMDV_ANH, dichvu.getAnh());
		values.put(DMDV_MANV, dichvu.getMaNV());
		values.put(DMDV_NGAYSUAGIA, dichvu.getNgaySuaGia());
		values.put(DMDV_GHICHU, dichvu.getGhiChu());

		// updating row
		return db.update(TABLE_DMDV, values, DMDV_ID + " = ?",
				new String[] { String.valueOf(dichvu.getID()) });
	}

	// Deleting single DMDV
	public int deleteDMDV(int id) {
		int result;
		SQLiteDatabase db = this.getWritableDatabase();
		result = db.delete(TABLE_DMDV, DMDV_ID + " = ?",
				new String[] { String.valueOf(id) });
		db.close();
		return result;
	}

	// Getting DMDV Count
	public int getDMDVCount() {
		String countQuery = "SELECT  * FROM " + TABLE_DMDV;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int count = cursor.getCount();
		cursor.close();

		return count;
	}

	// /////////////////////////////////////////////////////////////////////
	// Adding new DV
	public int addNewDV(DV_DichVu dichvu) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		int insert_stt = 0;
		values.put(DV_MADV, dichvu.getMaDV());
		values.put(DV_TENDV, dichvu.getTenDV());
		values.put(DV_SOLUONG, dichvu.getSoLuong());
		values.put(DV_DONGIABAN, dichvu.getDonGiaBan());
		values.put(DV_DONGIACHEBIEN, dichvu.getDonGiaCB());
		values.put(DV_DONVI, dichvu.getDonViTinh());
		values.put(DV_FTGIAMGIA, dichvu.getFtGiaGia());
		values.put(DV_MAHD, dichvu.getMaHD());
		values.put(DV_MANVPV, dichvu.getMaNVPV());
		values.put(DV_MANVCB, dichvu.getMaNVCB());
		values.put(DV_MACAPV, dichvu.getMaCaPV());
		values.put(DV_THOIDIEMPV, dichvu.getThoiDiemPV());
		values.put(DV_TRANGTHAI, dichvu.getTrangThai());
		values.put(DV_INYC, dichvu.getInYC());
		values.put(DV_GHICHU, dichvu.getGhiChu());

		// Inserting Row
		insert_stt = (int) db.insert(TABLE_DV, null, values);
		db.close(); // Closing database connection
		return insert_stt;
	}

	// Getting All dichvu
	public List<DV_DichVu> getAllDV() {
		List<DV_DichVu> dichvuList = new ArrayList<DV_DichVu>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_DV;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_DV, null, null, null, null, null, null);
		;
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				DV_DichVu dichvu = new DV_DichVu();
				dichvu.setID(Integer.parseInt(cursor.getString(0)));
				dichvu.setMaDV(cursor.getString(cursor.getColumnIndex(DV_MADV)));
				dichvu.setTenDV(cursor.getString(cursor
						.getColumnIndex(DV_TENDV)));
				dichvu.setSoLuong(cursor.getString(cursor
						.getColumnIndex(DV_SOLUONG)));
				dichvu.setDonGiaBan(cursor.getString(cursor
						.getColumnIndex(DV_DONGIABAN)));
				dichvu.setDonGiaCB(cursor.getString(cursor
						.getColumnIndex(DV_DONGIACHEBIEN)));
				dichvu.setDonViTinh(cursor.getString(cursor
						.getColumnIndex(DV_DONVI)));
				dichvu.setFtGiaGia(cursor.getString(cursor
						.getColumnIndex(DV_FTGIAMGIA)));
				dichvu.setMaHD(cursor.getString(cursor.getColumnIndex(DV_MAHD)));
				dichvu.setMaNVPV(cursor.getString(cursor
						.getColumnIndex(DV_MANVPV)));
				dichvu.setMaNVCB(cursor.getString(cursor
						.getColumnIndex(DV_MANVCB)));
				dichvu.setMaCaPV(cursor.getString(cursor
						.getColumnIndex(DV_MACAPV)));
				dichvu.setThoiDiemPV(cursor.getString(cursor
						.getColumnIndex(DV_THOIDIEMPV)));
				dichvu.setTrangThai(cursor.getString(cursor
						.getColumnIndex(DV_TRANGTHAI)));
				dichvu.setInYC(cursor.getString(cursor.getColumnIndex(DV_INYC)));
				dichvu.setGhiChu(cursor.getString(cursor
						.getColumnIndex(DV_GHICHU)));

				// Adding dichvu to list
				dichvuList.add(dichvu);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return dichvuList;
	}

	// Getting single dichvu
	public DV_DichVu getDV(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_DV, new String[] { DV_ID, DV_TENDV,
				DV_SOLUONG, DV_DONGIABAN, DV_DONGIACHEBIEN, DV_DONVI,
				DV_FTGIAMGIA, DV_MAHD, DV_MANVPV, DV_MANVCB, DV_MACAPV,
				DV_THOIDIEMPV, DV_TRANGTHAI, DV_INYC, DV_GHICHU },
				DV_ID + "=?", new String[] { String.valueOf(id) }, null, null,
				null, null);
		if (cursor != null)
			cursor.moveToFirst();

		DV_DichVu dichvu = new DV_DichVu();
		dichvu.setMaDV(cursor.getString(cursor.getColumnIndex(DV_MADV)));
		dichvu.setTenDV(cursor.getString(cursor.getColumnIndex(DV_TENDV)));
		dichvu.setSoLuong(cursor.getString(cursor.getColumnIndex(DV_SOLUONG)));
		dichvu.setDonGiaBan(cursor.getString(cursor
				.getColumnIndex(DV_DONGIABAN)));
		dichvu.setDonGiaCB(cursor.getString(cursor
				.getColumnIndex(DV_DONGIACHEBIEN)));
		dichvu.setDonViTinh(cursor.getString(cursor.getColumnIndex(DV_DONVI)));
		dichvu.setFtGiaGia(cursor.getString(cursor.getColumnIndex(DV_FTGIAMGIA)));
		dichvu.setMaHD(cursor.getString(cursor.getColumnIndex(DV_MAHD)));
		dichvu.setMaNVPV(cursor.getString(cursor.getColumnIndex(DV_MANVPV)));
		dichvu.setMaNVCB(cursor.getString(cursor.getColumnIndex(DV_MANVCB)));
		dichvu.setMaCaPV(cursor.getString(cursor.getColumnIndex(DV_MACAPV)));
		dichvu.setThoiDiemPV(cursor.getString(cursor
				.getColumnIndex(DV_THOIDIEMPV)));
		dichvu.setTrangThai(cursor.getString(cursor
				.getColumnIndex(DV_TRANGTHAI)));
		dichvu.setInYC(cursor.getString(cursor.getColumnIndex(DV_INYC)));
		dichvu.setGhiChu(cursor.getString(cursor.getColumnIndex(DV_GHICHU)));

		cursor.close();
		return dichvu;
	}
	
	// Getting single dichvu
		public DV_DichVu getDVbyMaDV(String MaDV) {
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.query(TABLE_DV, new String[] { DV_ID, DV_MADV, DV_TENDV,
					DV_SOLUONG, DV_DONGIABAN, DV_DONGIACHEBIEN, DV_DONVI,
					DV_FTGIAMGIA, DV_MAHD, DV_MANVPV, DV_MANVCB, DV_MACAPV,
					DV_THOIDIEMPV, DV_TRANGTHAI, DV_INYC, DV_GHICHU },
					DV_MADV + "=?", new String[] { String.valueOf(MaDV) }, null, null,
					null, null);
			if (cursor != null)
				cursor.moveToFirst();

			DV_DichVu dichvu = new DV_DichVu();
			dichvu.setMaDV(cursor.getString(cursor.getColumnIndex(DV_MADV)));
			dichvu.setTenDV(cursor.getString(cursor.getColumnIndex(DV_TENDV)));
			dichvu.setSoLuong(cursor.getString(cursor.getColumnIndex(DV_SOLUONG)));
			dichvu.setDonGiaBan(cursor.getString(cursor
					.getColumnIndex(DV_DONGIABAN)));
			dichvu.setDonGiaCB(cursor.getString(cursor
					.getColumnIndex(DV_DONGIACHEBIEN)));
			dichvu.setDonViTinh(cursor.getString(cursor.getColumnIndex(DV_DONVI)));
			dichvu.setFtGiaGia(cursor.getString(cursor.getColumnIndex(DV_FTGIAMGIA)));
			dichvu.setMaHD(cursor.getString(cursor.getColumnIndex(DV_MAHD)));
			dichvu.setMaNVPV(cursor.getString(cursor.getColumnIndex(DV_MANVPV)));
			dichvu.setMaNVCB(cursor.getString(cursor.getColumnIndex(DV_MANVCB)));
			dichvu.setMaCaPV(cursor.getString(cursor.getColumnIndex(DV_MACAPV)));
			dichvu.setThoiDiemPV(cursor.getString(cursor
					.getColumnIndex(DV_THOIDIEMPV)));
			dichvu.setTrangThai(cursor.getString(cursor
					.getColumnIndex(DV_TRANGTHAI)));
			dichvu.setInYC(cursor.getString(cursor.getColumnIndex(DV_INYC)));
			dichvu.setGhiChu(cursor.getString(cursor.getColumnIndex(DV_GHICHU)));

			cursor.close();
			return dichvu;
		}

	// Getting dichvu by HD
	public ArrayList<DV_DichVu> getDVByHD(String maHD) {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<DV_DichVu> dichvuList = new ArrayList<DV_DichVu>();
		Cursor cursor = db.query(TABLE_DV, new String[] { DV_ID, DV_TENDV,
				DV_MADV, DV_SOLUONG, DV_DONGIABAN, DV_DONGIACHEBIEN, DV_DONVI,
				DV_FTGIAMGIA, DV_MAHD, DV_MANVPV, DV_MANVCB, DV_MACAPV,
				DV_THOIDIEMPV, DV_TRANGTHAI, DV_INYC, DV_GHICHU }, DV_MAHD
				+ "=?", new String[] { String.valueOf(maHD) }, null, null,
				null, null);
		if (cursor != null)
			cursor.moveToFirst();

		if (cursor.moveToFirst()) {
			do {
				DV_DichVu dichvu = new DV_DichVu();
				dichvu.setMaDV(cursor.getString(cursor.getColumnIndex(DV_MADV)));
				dichvu.setTenDV(cursor.getString(cursor
						.getColumnIndex(DV_TENDV)));
				dichvu.setSoLuong(cursor.getString(cursor
						.getColumnIndex(DV_SOLUONG)));
				dichvu.setDonGiaBan(cursor.getString(cursor
						.getColumnIndex(DV_DONGIABAN)));
				dichvu.setDonGiaCB(cursor.getString(cursor
						.getColumnIndex(DV_DONGIACHEBIEN)));
				dichvu.setDonViTinh(cursor.getString(cursor
						.getColumnIndex(DV_DONVI)));
				dichvu.setFtGiaGia(cursor.getString(cursor
						.getColumnIndex(DV_FTGIAMGIA)));
				dichvu.setMaHD(cursor.getString(cursor.getColumnIndex(DV_MAHD)));
				dichvu.setMaNVPV(cursor.getString(cursor
						.getColumnIndex(DV_MANVPV)));
				dichvu.setMaNVCB(cursor.getString(cursor
						.getColumnIndex(DV_MANVCB)));
				dichvu.setMaCaPV(cursor.getString(cursor
						.getColumnIndex(DV_MACAPV)));
				dichvu.setThoiDiemPV(cursor.getString(cursor
						.getColumnIndex(DV_THOIDIEMPV)));
				dichvu.setTrangThai(cursor.getString(cursor
						.getColumnIndex(DV_TRANGTHAI)));
				dichvu.setInYC(cursor.getString(cursor.getColumnIndex(DV_INYC)));
				dichvu.setGhiChu(cursor.getString(cursor
						.getColumnIndex(DV_GHICHU)));

				// Adding contact to list
				dichvuList.add(dichvu);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return dichvuList;
	}

	// Updating single dichvu
	public int updateDV(DV_DichVu dichvu) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put(DV_MADV, dichvu.getMaDV());
		values.put(DV_TENDV, dichvu.getTenDV());
		values.put(DV_SOLUONG, dichvu.getSoLuong());
		values.put(DV_DONGIABAN, dichvu.getDonGiaBan());
		values.put(DV_DONGIACHEBIEN, dichvu.getDonGiaCB());
		values.put(DV_DONVI, dichvu.getDonViTinh());
		values.put(DV_FTGIAMGIA, dichvu.getFtGiaGia());
		values.put(DV_MAHD, dichvu.getMaHD());
		values.put(DV_MANVPV, dichvu.getMaNVPV());
		values.put(DV_MANVCB, dichvu.getMaNVCB());
		values.put(DV_MACAPV, dichvu.getMaCaPV());
		values.put(DV_THOIDIEMPV, dichvu.getThoiDiemPV());
		values.put(DV_TRANGTHAI, dichvu.getTrangThai());
		values.put(DV_INYC, dichvu.getInYC());
		values.put(DV_GHICHU, dichvu.getGhiChu());

		// updating row
		return db.update(TABLE_DV, values, DV_MADV + " = ?",
				new String[] { String.valueOf(dichvu.getMaDV()) });
	}

	// Deleting single dichvu
	public void deleteDV(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_DV, DV_ID + " = ?", new String[] { String.valueOf(id) });
		db.close();
	}
	
	// Deleting single dichvu
		public void deleteDVbyMaDV(String MaDv) {
			SQLiteDatabase db = this.getWritableDatabase();
			db.delete(TABLE_DV, DV_MADV + " = ?", new String[] { String.valueOf(MaDv) });
			db.close();
		}

	public void deleteAllDV() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_DV, null, null);
		db.close();
	}

	// Getting dichvu Count
	public int getDVCount() {
		String countQuery = "SELECT  * FROM " + TABLE_DV;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int count = cursor.getCount();
		cursor.close();
		return count;
	}

	// Getting dichvu Count
	public int getDVCountByHD(String maHD) {
		String countQuery = "SELECT  * FROM " + TABLE_DV + " WHERE " + DV_MAHD
				+ " = " + maHD;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int count = cursor.getCount();
		cursor.close();
		return count;
	}

	// /////////////////////////////////////////////////////////////////////
	// Adding new CaThuNgan
	void addNewCTN(CTN_CaThuNgan ctn) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(CTN_MANV, ctn.getMaNV());
		values.put(CTN_TDBATDAU, ctn.getTdBatDau());
		values.put(CTN_TDKETTHUC, ctn.getTdKetThuc());
		values.put(CTN_TIENMATDAUCA, ctn.getTienMatDauCa());
		values.put(CTN_TIENMATCUOICA, ctn.getTienMatCuoiCa());
		values.put(CTN_DSHDCHUATHU, ctn.getDsHDChuaThu());
		values.put(CTN_TIENCHUATHU, ctn.getTienChuaThu());
		values.put(CTN_MANV_CASAU, ctn.getMaNV_CaSau());
		values.put(CTN_GHICHU, ctn.getGhiChu());

		// Inserting Row
		int k = (int) db.insert(TABLE_CTN, null, values);
		db.close(); // Closing database connection
	}

	// Getting All ctn
	public List<CTN_CaThuNgan> getAllCTN() {
		List<CTN_CaThuNgan> ctnList = new ArrayList<CTN_CaThuNgan>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CTN;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_CTN, null, null, null, null, null, null);

		int k = cursor.getCount();
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				CTN_CaThuNgan ctn = new CTN_CaThuNgan();
				ctn.setID(Integer.parseInt(cursor.getString(0)));
				ctn.setMaNV(cursor.getString(cursor.getColumnIndex(CTN_MANV)));
				ctn.setTdBatDau(cursor.getString(cursor
						.getColumnIndex(CTN_TDBATDAU)));
				ctn.setTdKetThuc(cursor.getString(cursor
						.getColumnIndex(CTN_TDKETTHUC)));
				ctn.setTienMatDauCa(cursor.getString(cursor
						.getColumnIndex(CTN_TIENMATDAUCA)));
				ctn.setTienMatCuoiCa(cursor.getString(cursor
						.getColumnIndex(CTN_TIENMATCUOICA)));
				ctn.setDsHDChuaThu(cursor.getString(cursor
						.getColumnIndex(CTN_DSHDCHUATHU)));
				ctn.setTienChuaThu(cursor.getString(cursor
						.getColumnIndex(CTN_TIENCHUATHU)));
				ctn.setMaNV_CaSau(cursor.getString(cursor
						.getColumnIndex(CTN_MANV_CASAU)));
				ctn.setGhiChu(cursor.getString(cursor
						.getColumnIndex(CTN_GHICHU)));
				// Adding contact to list
				ctnList.add(ctn);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return ctnList;
	}

	// Getting single ctn by id
	public CTN_CaThuNgan getCTNByID(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_CTN, null, CTN_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		CTN_CaThuNgan ctn = new CTN_CaThuNgan();
		ctn.setID(cursor.getInt(cursor.getColumnIndex(CTN_ID)));
		ctn.setMaNV(cursor.getString(cursor.getColumnIndex(CTN_MANV)));
		ctn.setTdBatDau(cursor.getString(cursor.getColumnIndex(CTN_TDBATDAU)));
		ctn.setTdKetThuc(cursor.getString(cursor.getColumnIndex(CTN_TDKETTHUC)));
		ctn.setTienMatDauCa(cursor.getString(cursor
				.getColumnIndex(CTN_TIENMATDAUCA)));
		ctn.setTienMatCuoiCa(cursor.getString(cursor
				.getColumnIndex(CTN_TIENMATCUOICA)));
		ctn.setDsHDChuaThu(cursor.getString(cursor
				.getColumnIndex(CTN_DSHDCHUATHU)));
		ctn.setTienChuaThu(cursor.getString(cursor
				.getColumnIndex(CTN_TIENCHUATHU)));
		ctn.setMaNV_CaSau(cursor.getString(cursor
				.getColumnIndex(CTN_MANV_CASAU)));
		ctn.setGhiChu(cursor.getString(cursor.getColumnIndex(CTN_GHICHU)));

		cursor.close();
		return ctn;
	}

	// Getting single ctn by user
	public CTN_CaThuNgan getLastCTNByUser(String user) {
		SQLiteDatabase db = this.getReadableDatabase();
		CTN_CaThuNgan ctn = new CTN_CaThuNgan();
		try {
			Cursor cursor = db.query(TABLE_CTN, null, CTN_MANV + "=?",
					new String[] { String.valueOf(user) }, null, null, CTN_ID
							+ " DESC LIMIT 1");

			if (cursor != null)
				cursor.moveToFirst();

			ctn.setID(cursor.getInt(cursor.getColumnIndex(CTN_ID)));
			ctn.setMaNV(cursor.getString(cursor.getColumnIndex(CTN_MANV)));
			ctn.setTdBatDau(cursor.getString(cursor
					.getColumnIndex(CTN_TDBATDAU)));
			ctn.setTdKetThuc(cursor.getString(cursor
					.getColumnIndex(CTN_TDKETTHUC)));
			ctn.setTienMatDauCa(cursor.getString(cursor
					.getColumnIndex(CTN_TIENMATDAUCA)));
			ctn.setTienMatCuoiCa(cursor.getString(cursor
					.getColumnIndex(CTN_TIENMATCUOICA)));
			ctn.setDsHDChuaThu(cursor.getString(cursor
					.getColumnIndex(CTN_DSHDCHUATHU)));
			ctn.setTienChuaThu(cursor.getString(cursor
					.getColumnIndex(CTN_TIENCHUATHU)));
			ctn.setMaNV_CaSau(cursor.getString(cursor
					.getColumnIndex(CTN_MANV_CASAU)));
			ctn.setGhiChu(cursor.getString(cursor.getColumnIndex(CTN_GHICHU)));
			cursor.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ctn;
	}

	// Updating single ctn
	public int updateCTN(CTN_CaThuNgan ctn) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put(CTN_MANV, ctn.getMaNV());
		values.put(CTN_TDKETTHUC, ctn.getTdKetThuc());
		values.put(CTN_TIENMATDAUCA, ctn.getTienMatDauCa());
		values.put(CTN_TIENMATCUOICA, ctn.getTienMatCuoiCa());
		values.put(CTN_DSHDCHUATHU, ctn.getDsHDChuaThu());
		values.put(CTN_TIENCHUATHU, ctn.getTienChuaThu());
		values.put(CTN_MANV_CASAU, ctn.getMaNV_CaSau());
		values.put(CTN_GHICHU, ctn.getGhiChu());

		// updating row
		return db.update(TABLE_CTN, values, CTN_ID + " = ?",
				new String[] { String.valueOf(ctn.getID()) });
	}

	// Deleting single nhanvien
	public void deleteCTN(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CTN, CTN_ID + " = ?",
				new String[] { String.valueOf(id) });
		db.close();
	}

	// Getting nhanvien Count
	public int getCTNCount() {
		String countQuery = "SELECT  * FROM " + TABLE_CTN;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		return cursor.getCount();
	}

	// /////////////////////////////////////////////////////////////////////
	// Adding new CauHinh
	void addNewCauHinh(CH_CauHinh cauhinh) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(CH_TEN, cauhinh.getTenCH());
		values.put(CH_NOIDUNG, cauhinh.getNoiDungCH());

		// Inserting Row
		int k = (int) db.insert(TABLE_CH, null, values);
		db.close(); // Closing database connection
	}

	// Getting All cauhinh
	public List<CH_CauHinh> getAllCauHinh() {
		List<CH_CauHinh> chList = new ArrayList<CH_CauHinh>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CH;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_CH, null, null, null, null, null, null);

		int k = cursor.getCount();
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				CH_CauHinh cauhinh = new CH_CauHinh();
				cauhinh.setID(Integer.parseInt(cursor.getString(0)));
				cauhinh.setTenCH(cursor.getString(cursor.getColumnIndex(CH_TEN)));
				cauhinh.setNoiDungCH(cursor.getString(cursor
						.getColumnIndex(CH_NOIDUNG)));

				// Adding contact to list
				chList.add(cauhinh);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return chList;
	}

	// Getting single cauhinh
	public CH_CauHinh getCauHinh(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_CH, new String[] { CH_ID, CH_TEN,
				CH_NOIDUNG }, CH_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		CH_CauHinh cauhinh = new CH_CauHinh();
		cauhinh.setID(cursor.getColumnIndex(CH_ID));
		cauhinh.setTenCH(cursor.getString(cursor.getColumnIndex(CH_TEN)));
		cauhinh.setNoiDungCH(cursor.getString(cursor.getColumnIndex(CH_NOIDUNG)));

		cursor.close();
		return cauhinh;
	}

	// Updating single nhanvien
	public int updateCauHinh(CH_CauHinh cauhinh) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(CH_TEN, cauhinh.getTenCH());
		values.put(CH_NOIDUNG, cauhinh.getNoiDungCH());

		// updating row
		return db.update(TABLE_CH, values, CH_ID + " = ?",
				new String[] { String.valueOf(cauhinh.getID()) });
	}

	// Deleting single cauhinh
	public void deleteCauHinh(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CH, CH_ID + " = ?", new String[] { String.valueOf(id) });
		db.close();
	}

	// Getting cauhinh Count
	public int getCauHinhCount() {
		String countQuery = "SELECT  * FROM " + TABLE_CH;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		return cursor.getCount();
	}

	// /////////////////////////////////////////////////////////////////////
	// Adding new ChiNhanh
	void addNewChiNhanhh(CN_ChiNhanh chinhanh) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(CN_CHINHANH, chinhanh.getTenCN());
		values.put(CN_DIACHI, chinhanh.getDiaChi());
		values.put(CN_SODT, chinhanh.getSoDT());
		values.put(CN_THONGTINKHAC, chinhanh.getThongTinKhac());
		values.put(CN_CAUHINH, chinhanh.getCauHinh());

		// Inserting Row
		int k = (int) db.insert(TABLE_CN, null, values);
		db.close(); // Closing database connection
	}

	// Getting All ChiNhanh
	public List<CN_ChiNhanh> getAllChiNhanh() {
		List<CN_ChiNhanh> cnList = new ArrayList<CN_ChiNhanh>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CN;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_CN, null, null, null, null, null, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				CN_ChiNhanh chinhanh = new CN_ChiNhanh();
				chinhanh.setID(Integer.parseInt(cursor.getString(0)));
				chinhanh.setTenCN(cursor.getString(cursor
						.getColumnIndex(CN_CHINHANH)));
				chinhanh.setDiaChi(cursor.getString(cursor
						.getColumnIndex(CN_DIACHI)));
				chinhanh.setThongTinKhac(cursor.getString(cursor
						.getColumnIndex(CN_THONGTINKHAC)));
				chinhanh.setCauHinh(cursor.getString(cursor
						.getColumnIndex(CN_CAUHINH)));

				// Adding contact to list
				cnList.add(chinhanh);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return cnList;
	}

	// Getting single chiNhanh by ID
	public CN_ChiNhanh getChiNhanh(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_CN, null, CN_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		CN_ChiNhanh chinhanh = new CN_ChiNhanh();
		chinhanh.setID(Integer.parseInt(cursor.getString(0)));
		chinhanh.setTenCN(cursor.getString(cursor.getColumnIndex(CN_CHINHANH)));
		chinhanh.setDiaChi(cursor.getString(cursor.getColumnIndex(CN_DIACHI)));
		chinhanh.setThongTinKhac(cursor.getString(cursor
				.getColumnIndex(CN_THONGTINKHAC)));
		chinhanh.setCauHinh(cursor.getString(cursor.getColumnIndex(CN_CAUHINH)));

		cursor.close();
		return chinhanh;
	}

	// Getting all chinhanh features by name
	public CN_ChiNhanh getCNbyName(String name) {
		SQLiteDatabase db = this.getReadableDatabase();
		CN_ChiNhanh chinhanh = new CN_ChiNhanh();
		try {
			Cursor cursor = db.query(TABLE_CN, null, CN_CHINHANH + "=?",
					new String[] { String.valueOf(name) }, null, null, null);

			if (cursor != null)
				cursor.moveToFirst();
			chinhanh.setID(Integer.parseInt(cursor.getString(0)));
			chinhanh.setTenCN(cursor.getString(cursor
					.getColumnIndex(CN_CHINHANH)));
			chinhanh.setDiaChi(cursor.getString(cursor
					.getColumnIndex(CN_DIACHI)));
			chinhanh.setThongTinKhac(cursor.getString(cursor
					.getColumnIndex(CN_THONGTINKHAC)));
			chinhanh.setCauHinh(cursor.getString(cursor
					.getColumnIndex(CN_CAUHINH)));
			chinhanh.setSoDT(cursor.getString(cursor
					.getColumnIndex(CN_SODT)));

			cursor.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return chinhanh;
	}

	// Updating single chinhanh
	public int updateChiNhanh(CN_ChiNhanh chinhanh) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(CN_CHINHANH, chinhanh.getTenCN());
		values.put(CN_DIACHI, chinhanh.getDiaChi());
		values.put(CN_SODT, chinhanh.getSoDT());
		values.put(CN_THONGTINKHAC, chinhanh.getThongTinKhac());
		values.put(CN_CAUHINH, chinhanh.getCauHinh());

		// updating row
		return db.update(TABLE_CN, values, CN_ID + " = ?",
				new String[] { String.valueOf(chinhanh.getID()) });
	}

	// Deleting single cauhinh
	public void deleteChiNhanh(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CN, CN_ID + " = ?", new String[] { String.valueOf(id) });
		db.close();
	}
	
	// Delete all hoadon
	public void deleteAllChiNhanh() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CN, null, null);
		db.close();
	}

	// Getting cauhinh Count
	public int getChiNhanhCount() {
		int count = 0;
		String countQuery = "SELECT  * FROM " + TABLE_CN;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		count = cursor.getCount();
		cursor.close();

		return count;
	}
}
