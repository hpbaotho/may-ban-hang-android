package com.example.maybanhang;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.R.bool;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

public class DM_ThemMoi_Activity extends TabActivity {

	private Spinner sp_chinhanhDM, sp_khuvucDM, sp_nhomDM, sp_donvitinhDM;
	private String khuvuc_arr[] = { "KHO 1", "KHO 2" };
	private String nhom_arr[] = { "Tạo kiểu tóc", "Dầu Gội", "Dầu Hấp",
			"Dưỡng Tóc", "Mặt Nạ", "Dầu xả", "Làm đẹp", "Dịch vụ khác" };
	private ArrayList<String> chinhanh_arr = new ArrayList<String>();
	private String donvi_arr[] = { "Suất", "Lượt", "Bộ", "Hộp" };
	private Button btn_saveDM, btn_exitThemDM;
	private EditText edt_dongiabanDM, edt_giabanKm1DM, edt_giabanKm2DM,
			edt_giabanKmPtDM, edt_dongiachebienDM, edt_madvDM, edt_mavachDM,
			edt_tendvDM, edt_tenTaDM, edt_ghichuDM;
	private RadioButton rdb_layngayDM, rdb_laysauDM, rdb_layriengDM;
	private CheckBox cb_chophepbanDM;
	private RadioGroup rdg_inphieuDM;
	static public Calendar calendar;
	private boolean isDMDvExist = false;
	private ArrayList<DMDV_DanhMucDV> arrDMDV = new ArrayList<DMDV_DanhMucDV>();

	private DatabaseHandler database = new DatabaseHandler(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.setContentView(R.layout.dm_themmoi_main);

		btn_saveDM = (Button) findViewById(R.id.btn_saveDM);
		btn_exitThemDM = (Button) findViewById(R.id.btn_exitThemDM);
		sp_chinhanhDM = (Spinner) findViewById(R.id.sp_chinhanhDM);
		sp_khuvucDM = (Spinner) findViewById(R.id.sp_khuvucDM);
		sp_nhomDM = (Spinner) findViewById(R.id.sp_nhomDM);
		sp_donvitinhDM = (Spinner) findViewById(R.id.sp_donvitinhDM);
		edt_dongiabanDM = (EditText) findViewById(R.id.edt_dongiabanDM);
		edt_giabanKm1DM = (EditText) findViewById(R.id.edt_giabanKm1DM);
		edt_giabanKm2DM = (EditText) findViewById(R.id.edt_giabanKm2DM);
		edt_giabanKmPtDM = (EditText) findViewById(R.id.edt_giabanKmPtDM);
		edt_dongiachebienDM = (EditText) findViewById(R.id.edt_dongiachebienDM);
		edt_madvDM = (EditText) findViewById(R.id.edt_madvDM);
		edt_mavachDM = (EditText) findViewById(R.id.edt_mavachDM);
		edt_tendvDM = (EditText) findViewById(R.id.edt_tendvDM);
		edt_tenTaDM = (EditText) findViewById(R.id.edt_tenTaDM);
		edt_ghichuDM = (EditText) findViewById(R.id.edt_ghichuDM);
		rdb_layngayDM = (RadioButton) findViewById(R.id.rdb_layngayDM);
		rdb_laysauDM = (RadioButton) findViewById(R.id.rdb_laysauDM);
		rdb_layriengDM = (RadioButton) findViewById(R.id.rdb_layriengDM);
		rdg_inphieuDM = (RadioGroup) findViewById(R.id.rdg_inphieuDM);
		cb_chophepbanDM = (CheckBox) findViewById(R.id.cb_chophepbanDM);

		loadtabs();
		try {
			btn_saveDM.setOnClickListener(new MyEvent());
			btn_exitThemDM.setOnClickListener(new MyEvent());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		// chi nhanh
		List<CN_ChiNhanh> arl_CN = database.getAllChiNhanh();
		for (int i = 0; i < arl_CN.size(); i++) {
			chinhanh_arr.add(arl_CN.get(i).getTenCN());
		}
		ArrayAdapter<String> chinhanh_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, chinhanh_arr);
		chinhanh_adt
				.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		sp_chinhanhDM.setAdapter(chinhanh_adt);

		// Khu vuc
		ArrayAdapter<String> khuvuc_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, khuvuc_arr);
		khuvuc_adt
				.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		sp_khuvucDM.setAdapter(khuvuc_adt);

		// Nhom
		ArrayAdapter<String> nhom_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, nhom_arr);
		nhom_adt.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		sp_nhomDM.setAdapter(nhom_adt);

		// Don vi tinh
		ArrayAdapter<String> donvi_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, donvi_arr);
		donvi_adt
				.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		sp_donvitinhDM.setAdapter(donvi_adt);
	}

	private class MyEvent implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_saveDM:
				ThemMoi_DanhMucDV();
				break;
			case R.id.btn_exitThemDM:
				Intent intent = new Intent(DM_ThemMoi_Activity.this,
						DM_MainActivity.class);
				startActivity(intent);
				break;
			}
		}
	}

	@Override
	public void onBackPressed() {
		// do nothing.
	}

	// Cau hinh tab
	public void loadtabs() {
		final TabHost tab = (TabHost) findViewById(android.R.id.tabhost);

		// gọi lệnh setup
		tab.setup();
		TabHost.TabSpec spec;

		// Tạo tab1
		spec = tab.newTabSpec("t1");
		spec.setContent(R.id.tab1);
		spec.setIndicator("Thông Tin Chung");
		tab.addTab(spec);

		// Tạo tab2
		spec = tab.newTabSpec("t2");
		spec.setContent(R.id.tab2);
		spec.setIndicator("Khu Vực");
		tab.addTab(spec);

		// Tạo tab4
		spec = tab.newTabSpec("t3");
		spec.setContent(R.id.tab3);
		spec.setIndicator("Giá Cả");
		tab.addTab(spec);

		// Tạo tab5
		spec = tab.newTabSpec("t4");
		spec.setContent(R.id.tab4);
		spec.setIndicator("Chế Độ In");
		tab.addTab(spec);

		tab.setCurrentTab(0);
	}

	private void ThemMoi_DanhMucDV() {
		arrDMDV = (ArrayList<DMDV_DanhMucDV>) database.getAllDMDV();
		for (int i = 0; i < arrDMDV.size(); i++) {
			if (arrDMDV.get(i).getMaDV()
					.equals(edt_madvDM.getText().toString())) {
				isDMDvExist = true;
				AlertDialog.Builder b1 = new AlertDialog.Builder(
						DM_ThemMoi_Activity.this);
				b1.setTitle("Thông báo");
				b1.setMessage("Dịch vụ "
						+ arrDMDV.get(i).getTenDV().toUpperCase()
						+ " đã tồn tại!");
				b1.setPositiveButton("Đồng ý",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								isDMDvExist = false;
								dialog.cancel();
							}
						});
				b1.create().show();
			}
		}
		if (!isDMDvExist) {
			if (edt_madvDM.getText().toString().equals("")) {
				Toast.makeText(getApplicationContext(), "Bạn phải nhập mã DV", Toast.LENGTH_LONG).show();
			}
			else if(edt_tendvDM.getText().toString().equals("")){
				Toast.makeText(getApplicationContext(), "Bạn phải nhập tên DV", Toast.LENGTH_LONG).show();
			}
			else if(edt_dongiabanDM.getText().toString().equals("")){
				Toast.makeText(getApplicationContext(), "Bạn phải nhập đơn giá DV", Toast.LENGTH_LONG).show();
			}
			else if(edt_giabanKmPtDM.getText().toString().equals("")){
				Toast.makeText(getApplicationContext(), "Bạn phải nhập giá khuyến mại DV", Toast.LENGTH_LONG).show();
			}
			else{
				DMDV_DanhMucDV dmdv = new DMDV_DanhMucDV();
				dmdv.setMaDV(edt_madvDM.getText().toString());
				dmdv.setMaVach(edt_mavachDM.getText().toString());
				dmdv.setTenDV(edt_tendvDM.getText().toString());
				dmdv.setTenTAnh(edt_tenTaDM.getText().toString());
				dmdv.setChiNhanh(sp_chinhanhDM.getSelectedItem().toString());
				dmdv.setKhuVuc(sp_khuvucDM.getSelectedItem().toString());
				dmdv.setNhomDV(sp_nhomDM.getSelectedItem().toString());
				dmdv.setDonGia(edt_dongiabanDM.getText().toString());
				dmdv.setGiaKM1(edt_giabanKm1DM.getText().toString());
				dmdv.setGiaKM2(edt_giabanKm2DM.getText().toString());
				dmdv.setFtGiamGia(edt_giabanKmPtDM.getText().toString());
				dmdv.setDonGiaCheBien(edt_dongiachebienDM.getText().toString());
				dmdv.setDonVi(sp_donvitinhDM.getSelectedItem().toString());
				switch (rdg_inphieuDM.getCheckedRadioButtonId()) {
				case R.id.rdb_layngayDM:
					dmdv.setThuocTinhIn(rdb_layngayDM.getText().toString());
					break;
				case R.id.rdb_layriengDM:
					dmdv.setThuocTinhIn(rdb_layriengDM.getText().toString());
					break;
				case R.id.rdb_laysauDM:
					dmdv.setThuocTinhIn(rdb_laysauDM.getText().toString());
					break;
				default:
					break;
				}
				dmdv.setGhiChu(edt_ghichuDM.getText().toString());
				if (cb_chophepbanDM.isChecked())
					dmdv.setChoPhepBan("Có");
				else
					dmdv.setChoPhepBan("Không");

				dmdv.setCauHinhGiamGia("N/A");
				dmdv.setAnh("N/A");
				dmdv.setMaNV(MainActivity.username);
				SimpleDateFormat sdfw = new SimpleDateFormat("dd/MM/yyyy HH:mm a");
				calendar = Calendar.getInstance();
				dmdv.setNgaySuaGia(sdfw.format(calendar.getTime()));

				if (database.addNewDMDV(dmdv) > 0) {
					Intent intent = new Intent(DM_ThemMoi_Activity.this,
							DM_MainActivity.class);
					startActivity(intent);
					Toast toast = Toast.makeText(this,
							"Đã thêm mới một danh mục thành công!",
							Toast.LENGTH_SHORT);
					toast.show();
				} else {
					Toast toast = Toast.makeText(this,
							"Không thể thêm mới danh mục lúc này, thử lại sau!",
							Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		}

	}
}
