package com.example.maybanhang;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class HD_ChuyenDV extends Activity {

	private TextView tv_chuyendv;
	private Button btn_okChuyenDV, btn_exitChuyenDV;
	private Spinner sp_ChuyenDV;
	private DatabaseHandler database = new DatabaseHandler(this);
	private ArrayList<String> khuvucCD_arr = new ArrayList<String>();
	private ArrayList<HD_HoaDon> hdCD_CTT = new ArrayList<HD_HoaDon>();
	private String chiNhanh;
	private DV_DichVu dv_chon;
	private HD_HoaDon hoadon;
	public static final int FINISH_CODE_UPDATE_CHUYENDV = 107;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.hd_chuyendv);

		tv_chuyendv = (TextView) findViewById(R.id.tv_chuyendv);
		btn_exitChuyenDV = (Button) findViewById(R.id.btn_exitChuyenDV);
		btn_okChuyenDV = (Button) findViewById(R.id.btn_okChuyenDV);
		sp_ChuyenDV = (Spinner) findViewById(R.id.sp_ChuyenDV);

		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("data");
		dv_chon = (DV_DichVu) intent.getSerializableExtra("dichvu");
		
		chiNhanh = database.getNhanvienByUser(MainActivity.username)
				.getChiNhanh();
		hdCD_CTT = (ArrayList<HD_HoaDon>) database.getHoaDonCD_CTT(chiNhanh);
		for (int i = 0; i < hdCD_CTT.size(); i++) {
			if(!hdCD_CTT.get(i).getKhuVuc().equals(HD_MainActivity.khuvuc))
				khuvucCD_arr.add(hdCD_CTT.get(i).getKhuVuc());
		}

		ArrayAdapter<String> khuvuc_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, khuvucCD_arr);
		khuvuc_adt
				.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		sp_ChuyenDV.setAdapter(khuvuc_adt);

		

		tv_chuyendv.setText("Chuyển dịch vụ " + dv_chon.getTenDV() + " sang HĐ khác");
		
		btn_okChuyenDV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int to_idHD = database.getHoaDonByKV_CTT(
						sp_ChuyenDV.getSelectedItem().toString()).getID();
				dv_chon.setMaHD(to_idHD + "");
				database.updateDV(dv_chon);
				Toast.makeText(getApplicationContext(), "Đã chuyển dịch vụ "
						+ dv_chon.getTenDV().toUpperCase() + " sang khu vực "
						+ sp_ChuyenDV.getSelectedItem().toString(),
						Toast.LENGTH_LONG).show();
				Intent intent = getIntent();
				setResult(FINISH_CODE_UPDATE_CHUYENDV, intent);
				finish();
			}
		});
		btn_exitChuyenDV.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
