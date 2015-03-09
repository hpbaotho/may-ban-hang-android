package com.example.maybanhang;

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
import android.widget.Switch;
import android.widget.TextView;

public class HD_AddFav extends Activity{

	private TextView tv_addFav;
	private Spinner sp_vitriFav;
	private Button btn_okDvHD, btn_exitDvHD;
	private String dichvu;
	private String arrVitri[] = {"1", "2", "3", "4", "5", "6" ,"7" ,"8", "9", "A", "B", "C"};
	public static final int FINISH_CODE_UPDATE_ADD_FAV = 91;
	private DatabaseHandler database = new DatabaseHandler(this);
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.hd_addfav);
		
		tv_addFav = (TextView)findViewById(R.id.tv_addFav);
		sp_vitriFav = (Spinner)findViewById(R.id.sp_vitriFav);
		btn_okDvHD = (Button)findViewById(R.id.btn_okDvHD);
		btn_exitDvHD = (Button)findViewById(R.id.btn_exitDvHD);
		
		ArrayAdapter<String> vitri_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, arrVitri);
		vitri_adt
				.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		sp_vitriFav.setAdapter(vitri_adt);
		
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("data");
		dichvu = bundle.getString("dichvu");
		
		tv_addFav.setText("Gán dịch vụ " + dichvu.toUpperCase() + " vào danh sách yêu thích");
		
		btn_okDvHD.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean isExistFav = false;
				Intent intent = getIntent();
				intent.putExtra("dichvu", dichvu);
				intent.putExtra("vitri", sp_vitriFav.getSelectedItem().toString());
				
				NV_NhanVien nhanvien = database.getNhanvienByUser(MainActivity.username);
				
				String cauhinh = nhanvien.getCauHinh();
				String cauhinh_arr[] = cauhinh.split("#");
				String position = sp_vitriFav.getSelectedItem().toString();
				
				for (int i = 0; i < cauhinh_arr.length; i++) {					
					if(cauhinh_arr[i].contains(position)){
						cauhinh_arr[i] = position + ". " + dichvu;
						isExistFav = true;
					}
				}
				if(!isExistFav)
					if(cauhinh.equals("")) cauhinh = position + ". " + dichvu;
					else cauhinh = cauhinh + "#" + position + ". " + dichvu;
				else{
					cauhinh = cauhinh_arr[0];
					for (int j = 0; j < cauhinh_arr.length - 1; j++) {
						cauhinh = cauhinh + "#" + cauhinh_arr[j+1];
					}
				}
				nhanvien.setCauHinh(cauhinh);
				database.updateNhanVien(nhanvien);
				setResult(FINISH_CODE_UPDATE_ADD_FAV, intent);
				finish();
			}
		});
		
		btn_exitDvHD.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	}
}
