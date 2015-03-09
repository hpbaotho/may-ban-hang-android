package com.example.maybanhang;

import java.util.ArrayList;

import org.w3c.dom.ls.LSInput;

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

/**
 * @author sunt
 * 
 */
public class HD_ChuyenHD extends Activity {

	private TextView tv_chuyenhd;
	private Button btn_okChuyenHD, btn_exitChuyenHD;
	private Spinner sp_ChuyenHD;
	private DatabaseHandler database = new DatabaseHandler(this);
	private String chiNhanh;
	private String[] listKVCoDinh;
	private ArrayList<String> listKV_NotActive = new ArrayList<String>();
	private ArrayList<String> listKVCD_CTT = new ArrayList<String>();
	public static final int FINISH_CODE_UPDATE_CHUYENHD = 108;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.hd_chuyenhd);

		tv_chuyenhd = (TextView) findViewById(R.id.tv_chuyenhd);
		btn_exitChuyenHD = (Button) findViewById(R.id.btn_exitChuyenHD);
		btn_okChuyenHD = (Button) findViewById(R.id.btn_okChuyenHD);
		sp_ChuyenHD = (Spinner) findViewById(R.id.sp_ChuyenHD);

		tv_chuyenhd
				.setText("Chuyển HĐ " + HD_MainActivity.hd_id + " từ khu vực "
						+ HD_MainActivity.khuvuc + " tới khu vực khác");

		// get cau hinh khu vuc
		chiNhanh = database.getNhanvienByUser(MainActivity.username)
				.getChiNhanh();
		listKVCoDinh = database.getCNbyName(chiNhanh).getCauHinh().substring(9)
				.split(",");

		for (int i = 0; i < database.getHoaDonCD_CTT(chiNhanh).size(); i++) {
			listKVCD_CTT.add(database.getHoaDonCD_CTT(chiNhanh).get(i)
					.getKhuVuc());
		}

		for (int i = 0; i < listKVCoDinh.length; i++) {
			boolean isExist = false;
			for (int j = 0; j < listKVCD_CTT.size(); j++) {
				if (listKVCoDinh[i].equals(listKVCD_CTT.get(j))) {
					isExist = true;
					break;
				}
			}
			if (!isExist) {
				listKV_NotActive.add(listKVCoDinh[i]);
			}
		}

		ArrayAdapter<String> khuvuc_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, listKV_NotActive);
		khuvuc_adt
				.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		sp_ChuyenHD.setAdapter(khuvuc_adt);

		btn_okChuyenHD.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				HD_HoaDon hoadon = database.getHoaDon(HD_MainActivity.hd_id);
				hoadon.setKhuVuc(sp_ChuyenHD.getSelectedItem().toString());
				database.updateHoaDon(hoadon);

				Intent intent = getIntent();
				setResult(FINISH_CODE_UPDATE_CHUYENHD, intent);
				Toast.makeText(
						getApplicationContext(),
						"Đã chuyển hóa đơn " + HD_MainActivity.hd_id
								+ " sang khu vực "
								+ sp_ChuyenHD.getSelectedItem().toString(),
						Toast.LENGTH_LONG).show();
				finish();
			}
		});

		btn_exitChuyenHD.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
