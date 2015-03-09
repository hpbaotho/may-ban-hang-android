package com.example.maybanhang;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

public class NV_ThemMoi_Activity extends TabActivity {

	private DatabaseHandler database = new DatabaseHandler(this);

	private TextView tv_time, tv_sologun;

	private Spinner sp_trangthaiNV, sp_gioitinhNV, sp_chinhanhNV,
			sp_chucdanhNV, sp_bophanNV, sp_hopdongNV;
	private String ttnv_arr[] = { "Đang Làm", "Nghỉ Việc", "Chưa Đi Làm" };
	private String giotinh_arr[] = { "Nam", "Nữ" };
	private ArrayList<String> chinhanh_arr = new ArrayList<String>();
	private String chucdanh_arr[] = { "Quản Lý", "Thu Ngân", "Phục Vụ" };
	private String bophan_arr[] = { "Quản Lý", "Kế Toán", "Bán Hàng" };
	private String hopdong_arr[] = { "Ngắn Hạn", "Dài Hạn", "Dịch Vụ",
			"Thử Việc", "Học Việc" };

	private EditText edt_ngaysinhNV, edt_ngaycapcmtndDNV, edt_ngaylamNV,
			edt_ngaynghiNV, edt_tkNV, edt_mkNV, edt_matheNV, edt_hotenNV,
			edt_quequanNV, edt_cmtndNV, edt_noicapcmtndNV, edt_ghichuNV,
			edt_hsluongNV, edt_phucapNV, edt_thangthuongNV, edt_thangphatNV,
			edt_datconNV;
	private Button btn_saveNV, btn_tmnv_exit;

	static final int DATE_DIALOG_NS = 999;
	static final int DATE_DIALOG_NC = 998;
	static final int DATE_DIALOG_NL = 997;
	static final int DATE_DIALOG_NN = 996;
	private int year, month, day;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// set content view AFTER ABOVE sequence (to avoid crash)
		this.setContentView(R.layout.nv_themmoi_main);

		// ////////////////////
		edt_tkNV = (EditText) findViewById(R.id.edt_tkNV);
		edt_mkNV = (EditText) findViewById(R.id.edt_mkNV);
		edt_matheNV = (EditText) findViewById(R.id.edt_matheNV);
		edt_hotenNV = (EditText) findViewById(R.id.edt_hotenNV);
		edt_quequanNV = (EditText) findViewById(R.id.edt_quequanNV);
		edt_cmtndNV = (EditText) findViewById(R.id.edt_cmtndNV);
		edt_noicapcmtndNV = (EditText) findViewById(R.id.edt_noicapcmtndNV);
		edt_ghichuNV = (EditText) findViewById(R.id.edt_ghichuNV);
		edt_hsluongNV = (EditText) findViewById(R.id.edt_hsluongNV);
		edt_phucapNV = (EditText) findViewById(R.id.edt_phucapNV);
		edt_thangthuongNV = (EditText) findViewById(R.id.edt_thangthuongNV);
		edt_thangphatNV = (EditText) findViewById(R.id.edt_thangphatNV);
		edt_datconNV = (EditText) findViewById(R.id.edt_datcocNV);
		edt_ngaysinhNV = (EditText) findViewById(R.id.edt_ngaysinhNV);
		edt_ngaycapcmtndDNV = (EditText) findViewById(R.id.edt_ngaycapcmtndNV);
		edt_ngaylamNV = (EditText) findViewById(R.id.edt_ngaylamNV);
		edt_ngaynghiNV = (EditText) findViewById(R.id.edt_ngaynghiNV);
		
		btn_saveNV = (Button) findViewById(R.id.btn_saveNV);
		btn_tmnv_exit = (Button) findViewById(R.id.btn_tmnv_exit);
		
		

		// load tab
		loadtabs();

		// trang thai nv
		sp_trangthaiNV = (Spinner) findViewById(R.id.sp_trangthaiNV);
		ArrayAdapter<String> ttnv_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, ttnv_arr);
		ttnv_adt.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		sp_trangthaiNV.setAdapter(ttnv_adt);

		// gioi tinh
		sp_gioitinhNV = (Spinner) findViewById(R.id.sp_gioitinhNV);
		ArrayAdapter<String> gioitinh_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, giotinh_arr);
		gioitinh_adt
				.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		sp_gioitinhNV.setAdapter(gioitinh_adt);

		// chi nhanh
		sp_chinhanhNV = (Spinner)findViewById(R.id.sp_chinhanhNV);
		try {
			List<CN_ChiNhanh> arl_CN = database.getAllChiNhanh();
			for (int i = 0; i < arl_CN.size(); i++) {
				chinhanh_arr.add(arl_CN.get(i).getTenCN());
			}
			ArrayAdapter<String> chinhanh_adt = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, chinhanh_arr);
			chinhanh_adt.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
			sp_chinhanhNV.setAdapter(chinhanh_adt);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

		// chuc danh
		sp_chucdanhNV = (Spinner) findViewById(R.id.sp_chucdanhNV);
		ArrayAdapter<String> chucdanh_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, chucdanh_arr);
		chucdanh_adt
				.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		sp_chucdanhNV.setAdapter(chucdanh_adt);

		// bo phan
		sp_bophanNV = (Spinner) findViewById(R.id.sp_bophanNV);
		ArrayAdapter<String> bophan_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, bophan_arr);
		bophan_adt
				.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		sp_bophanNV.setAdapter(bophan_adt);

		// loai hopp dong
		sp_hopdongNV = (Spinner) findViewById(R.id.sp_hopdongNV);
		ArrayAdapter<String> hopdong_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, hopdong_arr);
		hopdong_adt
				.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		sp_hopdongNV.setAdapter(hopdong_adt);

		edt_ngaysinhNV.setOnClickListener(new MyEvent());		
		edt_ngaycapcmtndDNV.setOnClickListener(new MyEvent());	
		edt_ngaylamNV.setOnClickListener(new MyEvent());
		edt_ngaynghiNV.setOnClickListener(new MyEvent());
		
		// set current date
		setCurrentDateOnView();

		btn_saveNV.setOnClickListener(new MyEvent());
		btn_tmnv_exit.setOnClickListener(new MyEvent());
	}


	private class MyEvent implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.edt_ngaysinhNV:
				showDialog(DATE_DIALOG_NS);
				break;
			case R.id.edt_ngaycapcmtndNV:
				showDialog(DATE_DIALOG_NC);
				break;
			case R.id.edt_ngaylamNV:
				showDialog(DATE_DIALOG_NL);
				break;
			case R.id.edt_ngaynghiNV:
				showDialog(DATE_DIALOG_NN);
				break;
			case R.id.btn_saveNV:
				ThemMoi_NhanVien();
				break;
			case R.id.btn_tmnv_exit:
				Intent intent = new Intent(NV_ThemMoi_Activity.this, NV_MainActivity.class);
				startActivity(intent);
				break;
			default:
				break;
			}
		}
	}
	
	
	@Override
	public void onBackPressed() {}


	
	// Cau hinh tab
	public void loadtabs() {
		final TabHost tab = (TabHost) findViewById(android.R.id.tabhost);

		// gọi lệnh setup
		tab.setup();
		TabHost.TabSpec spec;

		// Tạo tab1
		spec = tab.newTabSpec("t1");
		spec.setContent(R.id.tab1);
		spec.setIndicator("Tài Khoản");
		tab.addTab(spec);

		// Tạo tab2
		spec = tab.newTabSpec("t2");
		spec.setContent(R.id.tab2);
		spec.setIndicator("Thông Tin Cá Nhân");
		tab.addTab(spec);

		// Tạo tab4
		spec = tab.newTabSpec("t3");
		spec.setContent(R.id.tab3);
		spec.setIndicator("Công Việc");
		tab.addTab(spec);

		// Tạo tab5
		spec = tab.newTabSpec("t4");
		spec.setContent(R.id.tab4);
		spec.setIndicator("Lương Thưởng");
		tab.addTab(spec);

		tab.setCurrentTab(0);
	}

	// display current date
	public void setCurrentDateOnView() {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dft = null;
		// Định dạng ngày / tháng /năm
		dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		String strDate = dft.format(cal.getTime());

		// hiển thị lên giao diện
		edt_ngaycapcmtndDNV.setText(strDate);
		edt_ngaysinhNV.setText(strDate);
		edt_ngaylamNV.setText(strDate);
		edt_ngaynghiNV.setText(strDate);

		day = Integer.parseInt(strDate.substring(0, 2));
		month = Integer.parseInt(strDate.substring(3, 5));
		year = Integer.parseInt(strDate.substring(6, 10));

	}

	// show datePicker
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_NS:
			// set date picker as current date
			return new DatePickerDialog(this, datePickerListener_ns, year,
					month, day);
		case DATE_DIALOG_NC:
			return new DatePickerDialog(this, datePickerListener_nc, year,
					month, day);
		case DATE_DIALOG_NL:
			return new DatePickerDialog(this, datePickerListener_nl, year,
					month, day);
		case DATE_DIALOG_NN:
			return new DatePickerDialog(this, datePickerListener_nn, year,
					month, day);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener datePickerListener_ns = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// set selected date into textview
			String da = day + "";
			String mon = (month + 1) + "";
			String yea = year + "";
			if (da.length() == 1)
				da = "0" + da;
			if (mon.length() == 1)
				mon = "0" + mon;
			edt_ngaysinhNV.setText(da + "/" + mon + "/" + yea);
			/*
			 * edt_nv_ns.setText(new StringBuilder().append(month +
			 * 1).append("/")
			 * .append(day).append("/").append(year).append(" "));
			 */

		}
	};

	private DatePickerDialog.OnDateSetListener datePickerListener_nc = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// set selected date into textview
			String da = day + "";
			String mon = (month + 1) + "";
			String yea = year + "";
			if (da.length() == 1)
				da = "0" + da;
			if (mon.length() == 1)
				mon = "0" + mon;
			edt_ngaycapcmtndDNV.setText(da + "/" + mon + "/" + yea);
			/*
			 * edt_nv_nc.setText(new StringBuilder().append(month +
			 * 1).append("/")
			 * .append(day).append("/").append(year).append(" "));
			 */

		}
	};

	private DatePickerDialog.OnDateSetListener datePickerListener_nl = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// set selected date into textview
			String da = day + "";
			String mon = (month + 1) + "";
			String yea = year + "";
			if (da.length() == 1)
				da = "0" + da;
			if (mon.length() == 1)
				mon = "0" + mon;
			edt_ngaylamNV.setText(da + "/" + mon + "/" + yea);
			/*
			 * edt_nv_nc.setText(new StringBuilder().append(month +
			 * 1).append("/")
			 * .append(day).append("/").append(year).append(" "));
			 */

		}
	};

	private DatePickerDialog.OnDateSetListener datePickerListener_nn = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// set selected date into textview
			String da = day + "";
			String mon = (month + 1) + "";
			String yea = year + "";
			if (da.length() == 1)
				da = "0" + da;
			if (mon.length() == 1)
				mon = "0" + mon;
			edt_ngaynghiNV.setText(da + "/" + mon + "/" + yea);
			/*
			 * edt_nv_nc.setText(new StringBuilder().append(month +
			 * 1).append("/")
			 * .append(day).append("/").append(year).append(" "));
			 */

		}
	};

	private void ThemMoi_NhanVien() {
		NV_NhanVien nhanvien = new NV_NhanVien();
		nhanvien.setMaNV(edt_tkNV.getText().toString());
		nhanvien.setMatKhau(edt_mkNV.getText().toString());
		nhanvien.setMaThe(edt_matheNV.getText().toString());
		nhanvien.setTrangThai(sp_trangthaiNV.getSelectedItem().toString());
		nhanvien.setHoTen(edt_hotenNV.getText().toString());
		nhanvien.setNgaySinh(edt_ngaysinhNV.getText().toString());
		nhanvien.setQueQuan(edt_quequanNV.getText().toString());
		nhanvien.setGioiTinh(sp_gioitinhNV.getSelectedItem().toString());
		nhanvien.setCMTND(edt_cmtndNV.getText().toString());
		nhanvien.setNgayCap(edt_ngaycapcmtndDNV.getText().toString());
		nhanvien.setNoiCap(edt_noicapcmtndNV.getText().toString());
		nhanvien.setGhiChu(edt_ghichuNV.getText().toString());
		nhanvien.setChiNhanh(sp_chinhanhNV.getSelectedItem().toString());
		nhanvien.setChucDanh(sp_chucdanhNV.getSelectedItem().toString());
		nhanvien.setBoPhan(sp_bophanNV.getSelectedItem().toString());
		nhanvien.setLoaiHopDong(sp_hopdongNV.getSelectedItem().toString());
		nhanvien.setNgayLam(edt_ngaylamNV.getText().toString());
		nhanvien.setNgayNghi(edt_ngaynghiNV.getText().toString());
		nhanvien.setHSLuong(edt_hsluongNV.getText().toString());
		nhanvien.setTroCap(edt_phucapNV.getText().toString());
		nhanvien.setThangThuong(edt_thangthuongNV.getText().toString());
		nhanvien.setThangPhat(edt_thangphatNV.getText().toString());
		nhanvien.setDatCoc(edt_datconNV.getText().toString());
		nhanvien.setCauHinh("");

		if (database.addNhanVien(nhanvien) > 0) {
			Intent intent = new Intent(NV_ThemMoi_Activity.this,NV_MainActivity.class);
			startActivity(intent);
			Toast toast = Toast.makeText(this, "Đã thêm mới một nhân viên thành công!",
							Toast.LENGTH_SHORT);
			toast.show();
		} else {
			Toast toast = Toast.makeText(this,"Không thể thêm mới nhân viên lúc này, thử lại sau!",
					Toast.LENGTH_SHORT);
			toast.show();
		}
	}

}
