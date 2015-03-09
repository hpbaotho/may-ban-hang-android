package com.example.maybanhang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HD_Update_SL_KM extends Activity {

	private TextView tv_KmaiDv, tv_title_KmaiDv;
	private EditText edt_KmaiDv;
	private Button btn_okKmaiDv, btn_exitKmaiDv;
	private DV_DichVu dv_chon, dv_chon_update;
	private int type;// 0: update soluong, 1: update khuyen mai
	public static final int FINISH_CODE_UPDATE_SOLUONG = 105;
	public static final int FINISH_CODE_UPDATE_KHUYENMAI = 106;

	DatabaseHandler database = new DatabaseHandler(this);

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.hd_khuyenmai);

		tv_KmaiDv = (TextView) findViewById(R.id.tv_KmaiDv);
		tv_title_KmaiDv = (TextView)findViewById(R.id.tv_title_KmaiDv);
		edt_KmaiDv = (EditText) findViewById(R.id.edt_KmaiDv);
		btn_okKmaiDv = (Button) findViewById(R.id.btn_okKmaiDv);
		btn_exitKmaiDv = (Button) findViewById(R.id.btn_exitKmaiDv);

		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("data");
		type = bundle.getInt("type");
		dv_chon = (DV_DichVu) intent.getSerializableExtra("dichvu");
		if (type == 0) {
			tv_title_KmaiDv.setText("Thay đổi số lượng dịch vụ "
					+ dv_chon.getTenDV().toUpperCase() + " trong hóa đơn");
			tv_KmaiDv.setText("Số lượng");
			edt_KmaiDv.append(dv_chon.getSoLuong());
		} else {
			tv_title_KmaiDv.setText("Thay đổi khuyến mại dịch vụ "
					+ dv_chon.getTenDV().toUpperCase());
			tv_KmaiDv.setText("% khuyến mại");
			edt_KmaiDv.append(dv_chon.getFtGiaGia());
		}

		btn_okKmaiDv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dv_chon_update = dv_chon;
				if(type == 0){
					dv_chon_update.setSoLuong(edt_KmaiDv.getText().toString());
					Intent intent = getIntent();
					// intent.putExtra("data", FINISH_CODE_UPDATE);
					setResult(FINISH_CODE_UPDATE_SOLUONG, intent);
					Toast.makeText(getApplicationContext(), "Đã thay đổi số lượng dịch vụ", Toast.LENGTH_SHORT).show();
				}else{
					dv_chon_update.setFtGiaGia(edt_KmaiDv.getText().toString());
					Intent intent = getIntent();
					// intent.putExtra("data", FINISH_CODE_UPDATE);
					setResult(FINISH_CODE_UPDATE_KHUYENMAI, intent);
					Toast.makeText(getApplicationContext(), "Đã thay đổi phần trăm khuyến mại dịch vụ", Toast.LENGTH_SHORT).show();
				}
				database.updateDV(dv_chon_update);
				
				InputMethodManager imm = (InputMethodManager) getSystemService(
					    INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
				finish();
			}
		});
		btn_exitKmaiDv.setOnClickListener(new OnClickListener() {
			
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
