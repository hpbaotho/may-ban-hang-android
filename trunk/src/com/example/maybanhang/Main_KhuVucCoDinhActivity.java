package com.example.maybanhang;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.maybanhang.R;
import com.example.maybanhang.R.id;
import com.example.maybanhang.R.layout;

import android.R.bool;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class Main_KhuVucCoDinhActivity extends Activity {

	private Button btn_cd1, btn_cd2, btn_cd3, btn_cd4, btn_cd5, btn_cd6,
			btn_cd7, btn_cd8, btn_cd9, btn_cd10, btn_cd11, btn_cd12, btn_cd13,
			btn_cd14, btn_cd15, btn_cd16, btn_cd17, btn_cd18, btn_cd19,
			btn_cd20, btn_cd21;
	private LinearLayout lyo_kvcd_1, lyo_kvcd_2, lyo_kvcd_3;
	private Button arr_btn[] = { btn_cd1, btn_cd2, btn_cd3, btn_cd4, btn_cd5,
			btn_cd6, btn_cd7, btn_cd8, btn_cd9, btn_cd10, btn_cd11, btn_cd12,
			btn_cd13, btn_cd14, btn_cd15, btn_cd16, btn_cd17, btn_cd18,
			btn_cd19, btn_cd20, btn_cd21 };

	private DatabaseHandler database = new DatabaseHandler(this);
	static public int hdcd_count = 0;
	static public ArrayList<String> arr_hdcd = new ArrayList<String>();
	static public ArrayList<String> arr_hdcd_id = new ArrayList<String>();

	private List<HD_HoaDon> listHdChuaTT;
	private List<HD_HoaDon> allHoaDon;
	private ArrayList<String> listKVChuaTT = new ArrayList<String>();
	private String[] listKVCoDinh;
	private ArrayList<String> listIDChuaTT = new ArrayList<String>();
	private String chiNhanh, cauhinhCN;
	private HD_HoaDon newHoaDon;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_khuvuc_codinh);

		lyo_kvcd_1 = (LinearLayout) findViewById(R.id.lyo_kvcd_1);
		lyo_kvcd_2 = (LinearLayout) findViewById(R.id.lyo_kvcd_2);
		lyo_kvcd_3 = (LinearLayout) findViewById(R.id.lyo_kvcd_3);

		// get hoadon CTT basic user
		chiNhanh = database.getNhanvienByUser(MainActivity.username)
				.getChiNhanh();
		listHdChuaTT = database.getHoaDonCD_CTT(chiNhanh);
		for (int i = 0; i < listHdChuaTT.size(); i++) {
			listKVChuaTT.add(listHdChuaTT.get(i).getKhuVuc());
			listIDChuaTT.add(listHdChuaTT.get(i).getID() + "");
		}
		// get cau hinh khu vuc
		cauhinhCN = database.getCNbyName(chiNhanh).getCauHinh();
		listKVCoDinh = cauhinhCN.substring(9).split(",");

		// create button co dinh
		for (int i = 0; i < listKVCoDinh.length; i++) {
			arr_btn[i] = new Button(this);
			createButtonCoDinh(arr_btn[i], listKVCoDinh[i], i);
		}
	}

	@Override
	public void onBackPressed() {
		// do nothing.
	}

	private void createButtonCoDinh(final Button btn_cd, final String khuvuc, int ID) {
		final SimpleDateFormat sdfw = new SimpleDateFormat("dd/MM/yyyy   HH:mm a");
		boolean existCTT = false;
		//btn_cd = new Button(this);
		btn_cd.setId(ID);
		btn_cd.setHeight(70);
		btn_cd.setWidth(120);
		btn_cd.setBackgroundColor(Color.parseColor("#8d8d8d"));
		btn_cd.setTextColor(Color.parseColor("#FFFFFF"));
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.setMargins(15, 0, 0, 0);
		btn_cd.setLayoutParams(params);
		btn_cd.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.square, 0);
		btn_cd.setPadding(0, 0, 3, 0);		
		
		for (int i = 0; i < listKVChuaTT.size(); i++) {
			if (listKVChuaTT.get(i).equals(khuvuc)) {
				Spanned text = Html.fromHtml("<font size=\"5\" color=#FFFFFF>"+ khuvuc+ "</font>\n <font size=\"2\" color=#7FFF00><i>"+ listIDChuaTT.get(i) + "</i></font>");
				btn_cd.setText(text);
				btn_cd.setBackgroundColor(Color.parseColor("#985555"));
				existCTT = true;
				break;
			}
		}
		if(!existCTT)
			btn_cd.setText(khuvuc);
		
		if (lyo_kvcd_1.getChildCount() < 6)
			lyo_kvcd_1.addView(btn_cd);
		else if (lyo_kvcd_2.getChildCount() < 6)
			lyo_kvcd_2.addView(btn_cd);
		else if (lyo_kvcd_3.getChildCount() < 6)
			lyo_kvcd_3.addView(btn_cd);
		
		btn_cd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setInterface(btn_cd, khuvuc);				
				}
		});
	}

	private void setInterface(final Button btn_cd, final String khuvuc) {
		final SimpleDateFormat sdfw = new SimpleDateFormat("dd/MM/yyyy   HH:mm a");
		
		try {
			if (btn_cd.getText().length() > 4) {
				int id = Integer.parseInt(btn_cd.getText().toString().substring(5));
				Intent intent = new Intent(Main_KhuVucCoDinhActivity.this,HD_MainActivity.class);
				Bundle bundle  = new Bundle();
				bundle.putInt("id", id);
				bundle.putString("khuvuc", khuvuc);
				bundle.putString("tdbd", database.getHoaDon(id).getTdBatDau());
				intent.putExtra("data", bundle);
				startActivity(intent);
				finish();
			} 
			
			else if (!database.getLastCTNByUser(MainActivity.username).getTdKetThuc().isEmpty()) {

				AlertDialog.Builder b = new AlertDialog.Builder(
						Main_KhuVucCoDinhActivity.this);
				b.setTitle("Thông báo");
				b.setMessage("Bạn không làm ca hiện tại, không thể tạo mới hóa đơn!");
				b.setPositiveButton("Đồng ý",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								dialog.cancel();							
							}
						});			
				b.create().show();
			} else {
				AlertDialog.Builder a = new AlertDialog.Builder(
						Main_KhuVucCoDinhActivity.this);
				a.setTitle("Khu vực " + khuvuc);
				a.setMessage("Bạn có chắc chắn muốn tạo mới hóa ?");
				a.setPositiveButton("Đồng ý",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								String hd_id_s, chiNhanh;
								chiNhanh = database.getNhanvienByUser(
										MainActivity.username).getChiNhanh();
								HD_HoaDon hoadon = new HD_HoaDon("CD", chiNhanh, khuvuc, "", "", sdfw.format(MainActivity.calendar.getTime()), "", "", "", "", "","", "", "", "", "", "", "", "", "ChuaTT","", "");
								database.addNewHD(hoadon);
								//List<HD_HoaDon> list = database.getAllHoaDon();
								String hd_id = database.getLastHoaDon().getID() + "";

								Spanned text = Html.fromHtml("<font size=\"5\" color=#FFFFFF>"+ khuvuc+ "</font>\n <font size=\"2\" color=#7FFF00><i>"+ hd_id + "</i></font>");
								btn_cd.setText(text);
								btn_cd.setBackgroundColor(Color.parseColor("#985555"));
							}
						});
				a.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.cancel();
					}
				});
				a.create().show();
			}
		} catch (Exception e) {
			// TODO: handle exception
			AlertDialog.Builder b = new AlertDialog.Builder(
					Main_KhuVucCoDinhActivity.this);
			b.setTitle("Thông báo");
			b.setMessage("Bạn không làm ca hiện tại, không thể tạo mới hóa đơn!");
			b.setPositiveButton("Đồng ý",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.cancel();							
						}
					});			
			b.create().show();
		}
		
	}
}
