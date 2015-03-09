package com.example.maybanhang;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.maybanhang.R;
import com.example.maybanhang.R.id;
import com.example.maybanhang.R.layout;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
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

public class Main_HoaDonLayNgayActivity extends Activity {

	private Button btn_plus, btn_hd1, btn_hd2, btn_hd3, btn_hd4, btn_hd5,
			btn_hd6, btn_hd7, btn_hd8, btn_hd9, btn_hd10, btn_hd11, btn_hd12,
			btn_hd13, btn_hd14, btn_hd15, btn_hd16, btn_hd17, btn_hd18;
	private Button[] arr_btn = {btn_hd1, btn_hd2, btn_hd3, btn_hd4, btn_hd5,
			btn_hd6, btn_hd7, btn_hd8, btn_hd9, btn_hd10, btn_hd11, btn_hd12,
			btn_hd13, btn_hd14, btn_hd15, btn_hd16, btn_hd17, btn_hd18};

	public static int hdln_count = 0;
	public static ArrayList<String> arr_hdln_id = new ArrayList<String>();
	
	private DatabaseHandler database = new DatabaseHandler(this);
	
	private List<HD_HoaDon> listHdChuaTT;
	private List<HD_HoaDon> allHoaDon;
	private ArrayList<String> listKVChuaTT = new ArrayList<String>();
	private ArrayList<String> listIDChuaTT = new ArrayList<String>();
	private String chiNhanh;
	private HD_HoaDon newHoaDon;
	
	private LinearLayout lyo_hdln_1, lyo_hdln_2, lyo_hdln_3;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_hoadon_layngay);
		
		//database.deleteAllHoaDon();
		//database.deleteHoaDon(2);

		btn_plus = (Button) findViewById(R.id.btn_plus);
		
		lyo_hdln_1 = (LinearLayout)findViewById(R.id.lyo_hdln_1);
		lyo_hdln_2 = (LinearLayout)findViewById(R.id.lyo_hdln_2);
		lyo_hdln_3 = (LinearLayout)findViewById(R.id.lyo_hdln_3);
		
		 //get hoadon basic user
	    chiNhanh = database.getNhanvienByUser(MainActivity.username).getChiNhanh(); 	   
	    listHdChuaTT = database.getHoaDonLN_CTT(chiNhanh);
	    for (int i = 0; i < listHdChuaTT.size(); i++) {
    		listKVChuaTT.add(listHdChuaTT.get(i).getKhuVuc());
    		listIDChuaTT.add(listHdChuaTT.get(i).getID() + "");	  
	    }
	    
	    for (int i = 0; i < listKVChuaTT.size(); i++) {
				createButton(arr_btn[i], listKVChuaTT.get(i), listIDChuaTT.get(i), i);
	    }

		btn_plus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final SimpleDateFormat sdfw = new SimpleDateFormat("dd/MM/yyyy   HH:mm a");
				String newKhuVuc = "";
				String newID;
				int newIDbtn = 0;
				Button newButton = btn_hd1;
				listHdChuaTT = database.getHoaDonLN_CTT(chiNhanh);
			    for (int i = 0; i < listHdChuaTT.size(); i++) {
		    		listKVChuaTT.add(listHdChuaTT.get(i).getKhuVuc());
		    		listIDChuaTT.add(listHdChuaTT.get(i).getID() + "");	
			    }
			    
		    try {					
				if (database.getLastCTNByUser(MainActivity.username).getTdKetThuc().isEmpty()) 
				 {
					//ArrayList<String> a = listKVChuaTT;
					if(!isExistKV(listKVChuaTT, "NG.01"))
					{
						newKhuVuc = "NG.01";
						newButton = btn_hd1;
						newIDbtn = 1;
					}
					else if (!isExistKV(listKVChuaTT, "NG.02")) {
						newKhuVuc = "NG.02";
						newButton = btn_hd2;
						newIDbtn = 2;
					}
					else if (!isExistKV(listKVChuaTT, "NG.03")) {
						newKhuVuc = "NG.03";
						newButton = btn_hd3;
						newIDbtn = 3;
					}
					else if (!isExistKV(listKVChuaTT, "NG.04")) {
						newKhuVuc = "NG.04";
						newButton = btn_hd4;
						newIDbtn = 4;
					}
					else if (!isExistKV(listKVChuaTT, "NG.05")) {
						newKhuVuc = "NG.05";
						newButton = btn_hd5;
						newIDbtn = 5;
					}
					else if (!isExistKV(listKVChuaTT, "NG.06")) {
						newKhuVuc = "NG.06";
						newButton = btn_hd6;
						newIDbtn = 6;
					}
					else if (!isExistKV(listKVChuaTT, "NG.07")) {
						newKhuVuc = "NG.07";
						newButton = btn_hd7;
						newIDbtn = 7;
					}
					else if (!isExistKV(listKVChuaTT, "NG.08")) {
						newKhuVuc = "NG.08";
						newButton = btn_hd8;
						newIDbtn = 8;
					}
					else if (!isExistKV(listKVChuaTT, "NG.09")) {
						newKhuVuc = "NG.09";
						newButton = btn_hd9;
						newIDbtn = 9;
					}
					else if (!isExistKV(listKVChuaTT, "NG.10")) {
						newKhuVuc = "NG.10";
						newButton = btn_hd10;
						newIDbtn = 10;
					}
					else if (!isExistKV(listKVChuaTT, "NG.11")) {
						newKhuVuc = "NG.11";
						newButton = btn_hd11;
						newIDbtn = 11;
					}
					else if (!isExistKV(listKVChuaTT, "NG.12")) {
						newKhuVuc = "NG.12";
						newButton = btn_hd12;
						newIDbtn = 12;
					}
					else if (!isExistKV(listKVChuaTT, "NG.13")) {
						newKhuVuc = "NG.13";
						newButton = btn_hd13;
						newIDbtn = 13;
					}
					else if (!isExistKV(listKVChuaTT, "NG.14")) {
						newKhuVuc = "NG.14";
						newButton = btn_hd14;
						newIDbtn = 14;
					}
					else if (!isExistKV(listKVChuaTT, "NG.15")) {
						newKhuVuc = "NG.15";
						newButton = btn_hd15;
						newIDbtn = 15;
					}
					else if (!isExistKV(listKVChuaTT, "NG.16")) {
						newKhuVuc = "NG.16";
						newButton = btn_hd16;
						newIDbtn = 16;
					}
					else if (!isExistKV(listKVChuaTT, "NG.17")) {
						newKhuVuc = "NG.17";
						newButton = btn_hd17;
						newIDbtn = 17;
					}
					else if (!isExistKV(listKVChuaTT, "NG.18")) {
						newKhuVuc = "NG.18";
						newButton = btn_hd18;
						newIDbtn = 18;
					}
					if (newIDbtn != 0) {
						newHoaDon = new HD_HoaDon("LN", chiNhanh, newKhuVuc, "", "", sdfw.format(MainActivity.calendar.getTime()),
								"", "", "", "", "", "", "", "", "", "", "", "", "", "ChuaTT", "", "");					 
						database.addNewHD(newHoaDon);
						int a = database.getHoaDonLN_CTT(chiNhanh).size();
						newID = database.getLastHoaDon().getID() + "";
						createButton(newButton, newKhuVuc, newID, newIDbtn);
						
					}
					
				 }
				else
				{
					AlertDialog.Builder b = new AlertDialog.Builder(Main_HoaDonLayNgayActivity.this);
					b.setTitle("Thông báo");
					b.setMessage("Bạn không làm ca hiện tại, không thể tạo mới hóa đơn!");
					b.setPositiveButton("Đồng ý",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.cancel();							
						}
					});			
					b.create().show();
				}
			    } catch (Exception e) {
					// TODO: handle exception
			    	AlertDialog.Builder b = new AlertDialog.Builder(Main_HoaDonLayNgayActivity.this);
					b.setTitle("Thông báo");
					b.setMessage("Bạn không làm ca hiện tại, không thể tạo mới hóa đơn!");
					b.setPositiveButton("Đồng ý",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.cancel();							
						}
					});			
					b.create().show();
				}
			}
			
		});
		
	}
	
	private boolean isExistKV(List<String> listKV, String khuVuc)
	{
		boolean exist = false;
		for (int i = 0; i < listKV.size(); i++) {
			if (listKV.get(i).equals(khuVuc)) {
				exist = true;
				return exist;
			}
		}
		return exist;
	}
	
	private void createButton(Button btn_hd, final String khuvuc, final String hd_id, int ID)
	{
		btn_hd = new Button(this);
		btn_hd.setId(ID);
		btn_hd.setHeight(70);
		btn_hd.setWidth(120);
		btn_hd.setBackgroundColor(Color.parseColor("#E4A0A0"));
		btn_hd.setTextColor(Color.parseColor("#FFFFFF"));
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.setMargins(15, 0, 0, 0);
		btn_hd.setLayoutParams(params);
		Spanned text = Html.fromHtml("<font size=\"5\" color=#FFFFFF>" + khuvuc + "</font>\n <font size=\"2\" color=#7FFF00><i>" + hd_id + "</i></font>");
		btn_hd.setText(text);
		if(lyo_hdln_1.getChildCount() < 7)
			lyo_hdln_1.addView(btn_hd);
		else if(lyo_hdln_2.getChildCount() < 6)
			lyo_hdln_2.addView(btn_hd);
		else if(lyo_hdln_3.getChildCount() < 6)
			lyo_hdln_3.addView(btn_hd);
		
		btn_hd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(Main_HoaDonLayNgayActivity.this,
						HD_MainActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt("id", Integer.parseInt(hd_id));
				bundle.putString("khuvuc", khuvuc);
				bundle.putString("tdbd", database.getHoaDon(Integer.parseInt(hd_id)).getTdBatDau());
				intent.putExtra("data", bundle);
				startActivity(intent);
				finish();
			}
		});
	}
}
