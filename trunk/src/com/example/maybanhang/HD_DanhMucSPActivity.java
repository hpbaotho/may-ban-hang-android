package com.example.maybanhang;

import java.lang.reflect.Array;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class HD_DanhMucSPActivity extends Activity {

	public static ListView lv_danhmucsp;
	private EditText edt_search;
	private Button btn_fav1, btn_fav2, btn_fav3, btn_fav4, btn_fav5, btn_fav6,
			btn_fav7, btn_fav8, btn_fav9, btn_fav10, btn_fav11, btn_fav12;
	private Button btn_addHD, btn_DVkhac, btn_addFAV;
	private int length_search = 0;
	private int addHD = 0;
	private boolean isDvExist = false;
	private String dv_chon = "", maDV_chon;
	private DatabaseHandler database = new DatabaseHandler(this);

	public static ArrayList<DMDV_DanhMucDV> arrL_dmdv = new ArrayList<DMDV_DanhMucDV>();;
	public static ArrayList<DMDV_DanhMucDV> orig_dmdv = new ArrayList<DMDV_DanhMucDV>();;
	public static HD_MyArrayAdapter adt_danhmucsp = null;
	private NV_NhanVien nhanvien;
	private String cauhinhFAV[];
	public int REQUEST_CODE_INPUT_ADD_FAV = 90;
	private Button arrBtn[] = new Button[12];

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hd_danhmuc_sp);

		edt_search = (EditText) findViewById(R.id.edt_search);
		lv_danhmucsp = (ListView) findViewById(R.id.lv_danhmucsp);
		btn_fav1 = (Button) findViewById(R.id.btn_fav1);
		btn_fav2 = (Button) findViewById(R.id.btn_fav2);
		btn_fav3 = (Button) findViewById(R.id.btn_fav3);
		btn_fav4 = (Button) findViewById(R.id.btn_fav4);
		btn_fav5 = (Button) findViewById(R.id.btn_fav5);
		btn_fav6 = (Button) findViewById(R.id.btn_fav6);
		btn_fav7 = (Button) findViewById(R.id.btn_fav7);
		btn_fav8 = (Button) findViewById(R.id.btn_fav8);
		btn_fav9 = (Button) findViewById(R.id.btn_fav9);
		btn_fav10 = (Button) findViewById(R.id.btn_fav10);
		btn_fav11 = (Button) findViewById(R.id.btn_fav11);
		btn_fav12 = (Button) findViewById(R.id.btn_fav12);
		btn_addHD = (Button) findViewById(R.id.btn_addHD);
		btn_DVkhac = (Button)findViewById(R.id.btn_DVkhac);
		btn_addFAV = (Button)findViewById(R.id.btn_addFAV);

		arrBtn[0] = btn_fav1;
		arrBtn[1] = btn_fav2;
		arrBtn[2] = btn_fav3;
		arrBtn[3] = btn_fav4;
		arrBtn[4] = btn_fav5;
		arrBtn[5] = btn_fav6;
		arrBtn[6] = btn_fav7;
		arrBtn[7] = btn_fav8;
		arrBtn[8] = btn_fav9;
		arrBtn[9] = btn_fav10;
		arrBtn[10] = btn_fav11;
		arrBtn[11] = btn_fav12;
		

		orig_dmdv = (ArrayList<DMDV_DanhMucDV>) database.getAllDMDV();
		arrL_dmdv = (ArrayList<DMDV_DanhMucDV>) database.getAllDMDV();

		adt_danhmucsp = new HD_MyArrayAdapter(this,
				R.layout.hd_custom_danhmucsp, arrL_dmdv);
		lv_danhmucsp.setAdapter(adt_danhmucsp);

		lv_danhmucsp.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				for (int a = 0; a < parent.getChildCount(); a++) {
					parent.getChildAt(a).setBackgroundColor(Color.TRANSPARENT);
				}

				view.setBackgroundColor(Color.RED);
				dv_chon = arrL_dmdv.get(position).getTenDV();
				maDV_chon = arrL_dmdv.get(position).getMaDV();
			}
		});
		lv_danhmucsp.setLongClickable(true);
		lv_danhmucsp.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent_addHd = new Intent(HD_DanhMucSPActivity.this, HD_ThemDV.class);
				Bundle bundle_addHd = new Bundle();
				DMDV_DanhMucDV dmdv_chon = (DMDV_DanhMucDV) lv_danhmucsp.getItemAtPosition(position);
				bundle_addHd.putString("dv_chon", dmdv_chon.getTenDV());
				bundle_addHd.putString("dv_chon_ma", dmdv_chon.getMaDV());
				intent_addHd.putExtra("data", bundle_addHd);
				startActivity(intent_addHd);
				return false;
			}
			
		});
		

		edt_search.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				length_search = edt_search.getText().length();
				arrL_dmdv.clear();
				for (int i = 0; i < orig_dmdv.size(); i++) {
					String fasname = orig_dmdv.get(i).getTenDV();
					String fasMa = orig_dmdv.get(i).getMaDV();

					if (length_search <= fasname.length()) {
						if (fasname.toLowerCase().contains(
								edt_search.getText().toString().toLowerCase())) {
							arrL_dmdv.add(orig_dmdv.get(i));
						}
					}

					if (length_search <= fasMa.length()) {
						if (fasMa.toLowerCase().contains(
								edt_search.getText().toString().toLowerCase()))
							arrL_dmdv.add(orig_dmdv.get(i));
					}

				}
				adt_danhmucsp.notifyDataSetChanged();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		/////////////////////////////
		btn_addHD.setOnClickListener(new MyEvent());
		btn_DVkhac.setOnClickListener(new MyEvent());
		btn_addFAV.setOnClickListener(new MyEvent());
		btn_fav1.setOnClickListener(new MyEvent());
		btn_fav2.setOnClickListener(new MyEvent());
		btn_fav3.setOnClickListener(new MyEvent());
		btn_fav4.setOnClickListener(new MyEvent());
		btn_fav5.setOnClickListener(new MyEvent());
		btn_fav6.setOnClickListener(new MyEvent());
		btn_fav7.setOnClickListener(new MyEvent());
		btn_fav8.setOnClickListener(new MyEvent());
		btn_fav9.setOnClickListener(new MyEvent());
		btn_fav10.setOnClickListener(new MyEvent());
		btn_fav11.setOnClickListener(new MyEvent());
		btn_fav12.setOnClickListener(new MyEvent());
		
		//update  FAV button
		updateFAV_Button();		
		
		
	}

	private void updateFAV_Button()
	{
		nhanvien = database.getNhanvienByUser(MainActivity.username);
		cauhinhFAV = nhanvien.getCauHinh().split("#");
		
		if(cauhinhFAV[0] != ""){
			for (int i = 0; i < cauhinhFAV.length; i++) {
				int position;
				if(cauhinhFAV[i].substring(0, 1).contains("A")){ position = 10;}
				else if(cauhinhFAV[i].substring(0, 1).contains("B")){ position = 11;}
				else if(cauhinhFAV[i].substring(0, 1).contains("C")){ position = 12;}
				else position = Integer.parseInt(cauhinhFAV[i].substring(0, 1));
				arrBtn[position - 1].setText(cauhinhFAV[i]);
			}
		}
		
	}

	private class MyEvent implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_addHD:
				if (!database.getLastCTNByUser(MainActivity.username).getTdKetThuc().isEmpty()) {

					AlertDialog.Builder b = new AlertDialog.Builder(
							HD_DanhMucSPActivity.this);
					b.setTitle("Thông báo");
					b.setMessage("Bạn không làm ca hiện tại, không thể thêm mới dịch vụ vào HĐ này!");
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
				else{
					if(dv_chon.equals(""))
						Toast.makeText(HD_DanhMucSPActivity.this, "Chọn một dịch vụ để thưc hiện chức năng này!", Toast.LENGTH_SHORT).show();
					else
					{
						Intent intent_addHd = new Intent(HD_DanhMucSPActivity.this, HD_ThemDV.class);
						Bundle bundle_addHd = new Bundle();
						bundle_addHd.putString("dv_chon", dv_chon);
						bundle_addHd.putString("dv_chon_ma", maDV_chon);
						intent_addHd.putExtra("data", bundle_addHd);
						startActivity(intent_addHd);
					}		
				}
				break;
			case R.id.btn_DVkhac:
				Intent intent_DvKhac = new Intent(HD_DanhMucSPActivity.this, HD_DvKhac.class);
				startActivity(intent_DvKhac);
				break;
			case R.id.btn_addFAV:
				if(dv_chon.equals(""))
					Toast.makeText(HD_DanhMucSPActivity.this, "Vui lòng chọn một dịch vụ!", Toast.LENGTH_SHORT).show();
				else
				{
					Intent intent_fav = new Intent(HD_DanhMucSPActivity.this, HD_AddFav.class);
					Bundle bundle_fav = new Bundle();
					bundle_fav.putString("dichvu", dv_chon);
					intent_fav.putExtra("data", bundle_fav);
					startActivityForResult(intent_fav, REQUEST_CODE_INPUT_ADD_FAV);
				}
				break;
			case R.id.btn_fav1:
				if(btn_fav1.getText().length() > 3){
					Intent intent1 = new Intent(HD_DanhMucSPActivity.this, HD_ThemDV.class);
					Bundle bundle1 = new Bundle();
					bundle1.putString("dv_chon", btn_fav1.getText().toString().substring(3));
					bundle1.putInt("type", addHD);
					intent1.putExtra("data", bundle1);
					startActivity(intent1);
				}
				break;
			case R.id.btn_fav2:
				if(btn_fav2.getText().length() > 3){
					Intent intent2 = new Intent(HD_DanhMucSPActivity.this, HD_ThemDV.class);
					Bundle bundle2 = new Bundle();
					bundle2.putString("dv_chon", btn_fav2.getText().toString().substring(3));
					bundle2.putInt("type", addHD);
					intent2.putExtra("data", bundle2);
					startActivity(intent2);
				}
				break;
			case R.id.btn_fav3:
				if(btn_fav3.getText().length() > 3){
					Intent intent3 = new Intent(HD_DanhMucSPActivity.this, HD_ThemDV.class);
					Bundle bundle3 = new Bundle();
					bundle3.putString("dv_chon", btn_fav3.getText().toString().substring(3));
					bundle3.putInt("type", addHD);
					intent3.putExtra("data", bundle3);
					startActivity(intent3);
				}
				break;
			case R.id.btn_fav4:
				if(btn_fav4.getText().length() > 3){
					Intent intent4 = new Intent(HD_DanhMucSPActivity.this, HD_ThemDV.class);
					Bundle bundle4 = new Bundle();
					bundle4.putString("dv_chon", btn_fav4.getText().toString().substring(3));
					bundle4.putInt("type", addHD);
					intent4.putExtra("data", bundle4);
					startActivity(intent4);
				}
				break;
			case R.id.btn_fav5:
				if(btn_fav5.getText().length() > 3){
					Intent intent5 = new Intent(HD_DanhMucSPActivity.this, HD_ThemDV.class);
					Bundle bundle5 = new Bundle();
					bundle5.putString("dv_chon", btn_fav5.getText().toString().substring(3));
					bundle5.putInt("type", addHD);
					intent5.putExtra("data", bundle5);
					startActivity(intent5);
				}
				break;
			case R.id.btn_fav6:
				if(btn_fav6.getText().length() > 3){
					Intent intent6 = new Intent(HD_DanhMucSPActivity.this, HD_ThemDV.class);
					Bundle bundle6 = new Bundle();
					bundle6.putString("dv_chon", btn_fav6.getText().toString().substring(3));
					bundle6.putInt("type", addHD);
					intent6.putExtra("data", bundle6);
					startActivity(intent6);
				}
				break;
			case R.id.btn_fav7:
				if(btn_fav7.getText().length() > 3){
					Intent intent7 = new Intent(HD_DanhMucSPActivity.this, HD_ThemDV.class);
					Bundle bundle7 = new Bundle();
					bundle7.putString("dv_chon", btn_fav7.getText().toString().substring(3));
					bundle7.putInt("type", addHD);
					intent7.putExtra("data", bundle7);
					startActivity(intent7);
				}
				break;
			case R.id.btn_fav8:
				if(btn_fav9.getText().length() > 3){
					Intent intent8 = new Intent(HD_DanhMucSPActivity.this, HD_ThemDV.class);
					Bundle bundle8 = new Bundle();
					bundle8.putString("dv_chon", btn_fav8.getText().toString().substring(3));
					bundle8.putInt("type", addHD);
					intent8.putExtra("data", bundle8);
					startActivity(intent8);
				}
				break;
			case R.id.btn_fav9:
				if(btn_fav9.getText().length() > 3){
					Intent intent9 = new Intent(HD_DanhMucSPActivity.this, HD_ThemDV.class);
					Bundle bundle9 = new Bundle();
					bundle9.putString("dv_chon", btn_fav9.getText().toString().substring(3));
					bundle9.putInt("type", addHD);
					intent9.putExtra("data", bundle9);
					startActivity(intent9);
				}
				break;
			case R.id.btn_fav10:
				if(btn_fav10.getText().length() > 3){
					Intent intent10 = new Intent(HD_DanhMucSPActivity.this, HD_ThemDV.class);
					Bundle bundle10 = new Bundle();
					bundle10.putString("dv_chon", btn_fav10.getText().toString().substring(3));
					bundle10.putInt("type", addHD);
					intent10.putExtra("data", bundle10);
					startActivity(intent10);
				}
				break;
			case R.id.btn_fav11:
				if(btn_fav11.getText().length() > 3){
					Intent intent11 = new Intent(HD_DanhMucSPActivity.this, HD_ThemDV.class);
					Bundle bundle11 = new Bundle();
					bundle11.putString("dv_chon", btn_fav11.getText().toString().substring(3));
					bundle11.putInt("type", addHD);
					intent11.putExtra("data", bundle11);
					startActivity(intent11);
				}
				break;
			case R.id.btn_fav12:
				if(btn_fav12.getText().length() > 3){
					Intent intent12 = new Intent(HD_DanhMucSPActivity.this, HD_ThemDV.class);
					Bundle bundle12 = new Bundle();
					bundle12.putString("dv_chon", btn_fav12.getText().toString().substring(3));
					bundle12.putInt("type", addHD);
					intent12.putExtra("data", bundle12);
					startActivity(intent12);
				}
				break;
			default:
				break;
			}
		}
	}
	
	@Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	 // TODO Auto-generated method stub
	 
		super.onActivityResult(requestCode, resultCode, data);
		switch(resultCode)
		 {
		 case HD_AddFav.FINISH_CODE_UPDATE_ADD_FAV:
			 updateFAV_Button();
		default:
			break;
		 }
	}
}
