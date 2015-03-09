package com.example.maybanhang;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HD_ThemDV extends Activity {

	private TextView tv_themdv;
	private Button btn_okDvHD, btn_exitDvHD;
	private EditText edt_soluongDv;
	public static ArrayList<DV_DichVu> arr_dv_chon = new ArrayList<DV_DichVu>();
	public static ArrayList<String> tdpv = new ArrayList<String>();
	//public static ArrayList<Integer> soLuong = new ArrayList<Integer>();
	static private Calendar calendar;
	private DatabaseHandler database = new DatabaseHandler(this);
	private DV_DichVu dv_chon;
	private DMDV_DanhMucDV dmdv_chon;
	private String maDv_chon;
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.hd_them_dv);

		tv_themdv = (TextView) findViewById(R.id.tv_themdv);
		btn_okDvHD = (Button) findViewById(R.id.btn_okDvHD);
		btn_exitDvHD = (Button) findViewById(R.id.btn_exitDvHD);
		edt_soluongDv = (EditText) findViewById(R.id.edt_soluongDv);

		calendar = Calendar.getInstance();
		final SimpleDateFormat sdf = new SimpleDateFormat(
				"dd:MM:yyyy HH:mm:ss a");
		// final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("data");
		maDv_chon = bundle.getString("dv_chon_ma");
		dmdv_chon = database.getDMDVByName(bundle.getString("dv_chon"));
		tv_themdv.setText("Thêm dịch vụ " + dmdv_chon.getTenDV().toUpperCase()
					+ " vào hóa đơn");

		btn_okDvHD.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean isExistDv = false;
				if(!edt_soluongDv.getText().toString().equals(""))// || Integer.parseInt(edt_soluongDv.getText().toString()) != 0)
				{
					ArrayList<DV_DichVu> arr_dichvu = database.getDVByHD(HD_MainActivity.hd_id + "");
					dv_chon = new DV_DichVu(dmdv_chon.getMaDV(), dmdv_chon
							.getTenDV(), edt_soluongDv.getText().toString(),
							dmdv_chon.getDonGia(), dmdv_chon.getDonGiaCheBien(),
							dmdv_chon.getDonVi(), dmdv_chon.getFtGiamGia(),
							HD_MainActivity.hd_id + "", MainActivity.username, "",
							"", sdf.format(calendar.getTime()), "Đang PV", "", "");
					for (int i = 0; i < arr_dichvu.size(); i++) {
						if (arr_dichvu.get(i).getMaDV().equals(maDv_chon)) {
							dv_chon.setSoLuong(Integer.parseInt(database.getDVbyMaDV(maDv_chon).getSoLuong()) + Integer.parseInt(edt_soluongDv.getText().toString()) + "");
							database.updateDV(dv_chon);
							isExistDv = true;
							break;
						}
					}
					if(!isExistDv)
						database.addNewDV(dv_chon);
					 Toast.makeText(getApplicationContext(), "Đã thêm dịch vụ " + dmdv_chon.getTenDV() + " vào hóa đơn!", Toast.LENGTH_SHORT).show();
					finish();
				}
				else
					Toast.makeText(getApplicationContext(), "Bạn chưa nhập số lượng dịch vụ", Toast.LENGTH_SHORT).show();
				InputMethodManager imm = (InputMethodManager) getSystemService(
					    INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
			}
		});
		btn_exitDvHD.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				InputMethodManager imm = (InputMethodManager) getSystemService(
					    INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
				finish();
			}
		});
	}
}
