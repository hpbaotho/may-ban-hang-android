package com.example.maybanhang;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class HD_DvKhac extends Activity {

	private EditText edt_tenDvKhacHd, edt_SoluongDvKhacHd, edt_DongiaDvKhacHd;
	private Spinner sp_DonviDvKhacHd, sp_KhuvucDvKhacHd;
	private Button btn_okDvKhacHD, btn_exitDvKhacHD;

	static private Calendar calendar;
	private boolean isDMDVvExis = false;

	private String donvi_arr[] = { "Suất", "Lượt", "Bộ", "Hộp" };
	private String khuvuc_arr[] = { "KHO 1", "KHO 2" };
	private ArrayList<DMDV_DanhMucDV> arrDMDV = new ArrayList<DMDV_DanhMucDV>();
	
	//private HD_MyArrayAdapter adt_danhmucsp = null;
	
	DatabaseHandler database = new DatabaseHandler(this);

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.hd_dvkhac);

		calendar = Calendar.getInstance();
		final SimpleDateFormat sdf_tdpv = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss a");
		final SimpleDateFormat sdf_ma = new SimpleDateFormat("ddMMyy_HHmmss");

		edt_tenDvKhacHd = (EditText) findViewById(R.id.edt_tenDvKhacHd);
		edt_SoluongDvKhacHd = (EditText) findViewById(R.id.edt_SoluongDvKhacHd);
		edt_DongiaDvKhacHd = (EditText) findViewById(R.id.edt_DongiaDvKhacHd);
		sp_DonviDvKhacHd = (Spinner) findViewById(R.id.sp_DonviDvKhacHd);
		sp_KhuvucDvKhacHd = (Spinner) findViewById(R.id.sp_KhuvucDvKhacHd);
		btn_okDvKhacHD = (Button) findViewById(R.id.btn_okDvKhacHD);
		btn_exitDvKhacHD = (Button) findViewById(R.id.btn_exitDvKhacHD);

		// khu vuc
		ArrayAdapter<String> khuvuc_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, khuvuc_arr);
		khuvuc_adt
				.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		sp_KhuvucDvKhacHd.setAdapter(khuvuc_adt);

		// Don vi tinh
		ArrayAdapter<String> donvi_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, donvi_arr);
		donvi_adt
				.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		sp_DonviDvKhacHd.setAdapter(donvi_adt);

		btn_okDvKhacHD.setOnClickListener(new OnClickListener() {

			ArrayList<DMDV_DanhMucDV> arrL_dmdv = new ArrayList<DMDV_DanhMucDV>();

			@Override
			public void onClick(View v) {
				if (edt_tenDvKhacHd.getText().toString().equals("")
						|| edt_SoluongDvKhacHd.getText().toString().equals("")
						|| edt_DongiaDvKhacHd.getText().toString().equals("")
						|| sp_DonviDvKhacHd.getSelectedItem().toString().equals(""))
				{
					Toast.makeText(HD_DvKhac.this,
							"Vui lòng điền đầy đủ thông tin!",
							Toast.LENGTH_LONG).show();
				}else if(Integer.parseInt(edt_SoluongDvKhacHd.getText().toString()) > 100 || Integer.parseInt(edt_DongiaDvKhacHd.getText().toString()) > 10000000){
					Toast.makeText(HD_DvKhac.this,
							"Số lượng DV không quá 100 và đơn giá không quá 10 triệu!",
							Toast.LENGTH_LONG).show();
				}
				else {
					String arr[] = edt_tenDvKhacHd.getText().toString()
							.split(" ");
					String maDV = "";
					for (int i = 0; i < arr.length; i++) {
						maDV = maDV + arr[i].substring(0, 1).toUpperCase();
					}
					maDV = maDV + sdf_ma.format(calendar.getTime());
					
			
					DV_DichVu newDV = new DV_DichVu(maDV, edt_tenDvKhacHd
							.getText().toString(), edt_SoluongDvKhacHd
							.getText().toString(), edt_DongiaDvKhacHd.getText()
							.toString(), "", sp_DonviDvKhacHd.getSelectedItem()
							.toString(), "000", HD_MainActivity.hd_id + "",
							MainActivity.username, "", "", sdf_tdpv.format(calendar
									.getTime()), "Đang PV", "", "");
					database.addNewDV(newDV);
					
					Toast.makeText(getApplicationContext(), "Đã thêm dịch vụ " + newDV.getTenDV() + " vào hóa đơn!", Toast.LENGTH_SHORT).show();
					finish();
			
				}
				InputMethodManager imm = (InputMethodManager) getSystemService(
					    INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
			}
		});
		btn_exitDvKhacHD.setOnClickListener(new OnClickListener() {
			
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
