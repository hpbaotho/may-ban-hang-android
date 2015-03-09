package com.example.maybanhang;

import android.app.TabActivity;
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
import android.widget.TextView;
import android.widget.Toast;

public class DM_SuaTT_Activity extends TabActivity {

	private Spinner sp_chinhanhDM, sp_khuvucDM, sp_nhomDM, sp_donvitinhDM;
	private String khuvuc_arr[] = { "KHO 1", "KHO 2" };
	private String nhom_arr[] = { "Tạo kiểu tóc", "Dầu Gội", "Dầu Hấp", "Dưỡng Tóc",
			"Mặt Nạ", "Dầu xả", "Làm đẹp", "Dịch vụ khác" };
	private String chinhanh_arr[] = { "Hà Nội", "Hồ Chí Minh", "Thái Bình" };
	private String donvi_arr[] = { "Suất", "Lượt", "Bộ", "Hộp" };
	private Button btn_suaTtDM, btn_exitSuaDM;
	private EditText edt_dongiabanDM, edt_giabanKm1DM, edt_giabanKm2DM,
			edt_giabanKmPtDM, edt_dongiachebienDM, edt_madvDM, edt_mavachDM,
			edt_tendvDM, edt_tenTaDM, edt_ghichuDM;
	private RadioButton rdb_layngayDM, rdb_laysauDM, rdb_layriengDM;
	private CheckBox cb_chophepbanDM;
	private RadioGroup rdg_inphieuDM;
	
	private DatabaseHandler database = new DatabaseHandler(this);
	private DMDV_DanhMucDV dmdv_get;
	private int position_sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// set content view AFTER ABOVE sequence (to avoid crash)
		this.setContentView(R.layout.dm_suatt_main);
		
		btn_suaTtDM = (Button) findViewById(R.id.btn_suaTtDM);
		btn_exitSuaDM = (Button) findViewById(R.id.btn_exitSuaDM);
		sp_chinhanhDM = (Spinner) findViewById(R.id.sp_chinhanhDM);
		sp_khuvucDM = (Spinner)findViewById(R.id.sp_khuvucDM);
		sp_nhomDM = (Spinner)findViewById(R.id.sp_nhomDM);
		sp_donvitinhDM = (Spinner)findViewById(R.id.sp_donvitinhDM);
		edt_dongiabanDM =(EditText)findViewById(R.id.edt_dongiabanDM);
		edt_giabanKm1DM = (EditText)findViewById(R.id.edt_giabanKm1DM);
		edt_giabanKm2DM = (EditText)findViewById(R.id.edt_giabanKm2DM);
		edt_giabanKmPtDM = (EditText)findViewById(R.id.edt_giabanKmPtDM);
		edt_dongiachebienDM = (EditText)findViewById(R.id.edt_dongiachebienDM);
		edt_madvDM = (EditText)findViewById(R.id.edt_madvDM);
		edt_mavachDM = (EditText)findViewById(R.id.edt_mavachDM);
		edt_tendvDM = (EditText)findViewById(R.id.edt_tendvDM);
		edt_tenTaDM = (EditText)findViewById(R.id.edt_tenTaDM);
		edt_ghichuDM = (EditText)findViewById(R.id.edt_ghichuDM);
		rdb_layngayDM = (RadioButton)findViewById(R.id.rdb_layngayDM);
		rdb_laysauDM = (RadioButton)findViewById(R.id.rdb_laysauDM);
		rdb_layriengDM = (RadioButton)findViewById(R.id.rdb_layriengDM);
		rdg_inphieuDM = (RadioGroup)findViewById(R.id.rdg_inphieuDM);
		cb_chophepbanDM = (CheckBox)findViewById(R.id.cb_chophepbanDM);
		
		loadtabs();
		btn_suaTtDM.setOnClickListener(new MyEvent());
		btn_exitSuaDM.setOnClickListener(new MyEvent());
		
		Intent intent = getIntent();
		dmdv_get = (DMDV_DanhMucDV)intent.getSerializableExtra("dmdv");

		// chi nhanh
		ArrayAdapter<String> chinhanh_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, chinhanh_arr);
		chinhanh_adt
				.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		sp_chinhanhDM.setAdapter(chinhanh_adt);
		for (int i = 0; i < chinhanh_arr.length; i++) {
			if(dmdv_get.getChiNhanh().equals(chinhanh_arr[i]))
				position_sp = i;
		}
		sp_chinhanhDM.setSelection(position_sp);

		// Khu vuc
		ArrayAdapter<String> khuvuc_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, khuvuc_arr);
		khuvuc_adt
				.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		sp_khuvucDM.setAdapter(khuvuc_adt);
		for (int i = 0; i < khuvuc_arr.length; i++) {
			if(dmdv_get.getKhuVuc().equals(khuvuc_arr[i]))
				position_sp = i;
		}
		sp_khuvucDM.setSelection(position_sp);

		// Nhom
		ArrayAdapter<String> nhom_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, nhom_arr);
		nhom_adt.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		sp_nhomDM.setAdapter(nhom_adt);
		for (int i = 0; i < nhom_arr.length; i++) {
			if(dmdv_get.getNhomDV().equals(nhom_arr[i]))
				position_sp = i;
		}
		sp_nhomDM.setSelection(position_sp);

		// Don vi tinh
		ArrayAdapter<String> donvi_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, donvi_arr);
		donvi_adt.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		sp_donvitinhDM.setAdapter(donvi_adt);
		for (int i = 0; i < donvi_arr.length; i++) {
			if(dmdv_get.getDonVi().equals(donvi_arr[i]))
				position_sp = i;
		}
		sp_donvitinhDM.setSelection(position_sp);
		
		//set infor display
		edt_madvDM.setText(dmdv_get.getMaDV());
		edt_mavachDM.setText(dmdv_get.getMaVach());
		edt_tendvDM.setText(dmdv_get.getTenDV());
		edt_tenTaDM.setText(dmdv_get.getTenTAnh());
		edt_dongiabanDM.setText(dmdv_get.getDonGia());
		edt_giabanKm1DM.setText(dmdv_get.getGiaKM1());
		edt_giabanKm2DM.setText(dmdv_get.getGiaKM2());
		edt_giabanKmPtDM.setText(dmdv_get.getFtGiamGia());
		edt_dongiachebienDM.setText(dmdv_get.getDonGiaCheBien());
		if(dmdv_get.getThuocTinhIn().contains("Lấy Ngay"))
			rdb_layngayDM.setChecked(true);
		else if(dmdv_get.getThuocTinhIn().contains("Lấy Sau"))
			rdb_laysauDM.setChecked(true);
		else
			rdb_layriengDM.setChecked(true);
		edt_ghichuDM.setText(dmdv_get.getGhiChu());
	}

	@Override
	public void onBackPressed() {
		// do nothing.
	}
	
	private class MyEvent implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_suaTtDM:
				Sua_ThongTin_DanhMuc();
				break;
			case R.id.btn_exitSuaDM:
				Intent intent = new Intent(DM_SuaTT_Activity.this,
						DM_MainActivity.class);
				startActivity(intent);
				break;
			}
		}
	}
	
	private void Sua_ThongTin_DanhMuc()
	{
		DMDV_DanhMucDV dmdv = new DMDV_DanhMucDV();
		dmdv.setID(dmdv_get.getID());
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
		if(cb_chophepbanDM.isChecked())
			dmdv.setChoPhepBan("Yes");
		else
			dmdv.setChoPhepBan("No");
		if (database.updateDMDV(dmdv) > 0) {
			Intent intent = new Intent(DM_SuaTT_Activity.this,DM_MainActivity.class);
			startActivity(intent);
			Toast toast = Toast.makeText(this, "Đã cập nhật thông tin danh mục thành công!",
							Toast.LENGTH_SHORT);
			toast.show();
		} else {
			Toast toast = Toast.makeText(this,"Không thể cập nhật thông tin danh mục lúc này, thử lại sau!",
					Toast.LENGTH_SHORT);
			toast.show();
		}
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
}
