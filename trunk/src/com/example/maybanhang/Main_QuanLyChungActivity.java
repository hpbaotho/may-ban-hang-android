package com.example.maybanhang;

import com.example.maybanhang.R;
import com.example.maybanhang.R.id;
import com.example.maybanhang.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main_QuanLyChungActivity extends Activity {

	private Button btn_setting, btn_qlnv, btn_dm, btn_baocao;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_quanlychung);

		btn_setting = (Button) findViewById(R.id.btn_setting);
		btn_qlnv = (Button) findViewById(R.id.btn_qlnv);
		btn_dm = (Button) findViewById(R.id.btn_dm);
		btn_baocao = (Button) findViewById(R.id.btn_baocao);

		btn_setting.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Main_QuanLyChungActivity.this,
						CH_MainActivity.class);
				startActivity(intent);
			}
		});

		btn_qlnv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Main_QuanLyChungActivity.this,
						NV_MainActivity.class);
				startActivity(intent);
			}
		});

		btn_dm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Main_QuanLyChungActivity.this,
						DM_MainActivity.class);
				startActivity(intent);
			}
		});

		btn_baocao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Main_QuanLyChungActivity.this,
						BC_MainActivity.class);
				startActivity(intent);
			}
		});
	}
}
