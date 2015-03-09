package com.example.maybanhang;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TableRow.LayoutParams;

public class DM_MainActivity extends Activity {

	private TableLayout table;
	private TextView tv_time, tv_sologun, tv_qldm;
	private Button btn_tmdm, btn_suadm, btn_xoadm, btn_exitDM;
	private DatabaseHandler database = new DatabaseHandler(this);
	private List<DMDV_DanhMucDV> dmdvList, dmdvList_afterDel;
	private DMDV_DanhMucDV dmdv_selected;
	public int row_selected = -1;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// set content view AFTER ABOVE sequence (to avoid crash)
		this.setContentView(R.layout.dm_main);

		tv_qldm = (TextView) findViewById(R.id.tv_qldm);
		Typeface tf2 = Typeface
				.createFromAsset(getAssets(), "fonts/Tahoma.ttf");
		tv_qldm.setTypeface(tf2);

		table = (TableLayout) findViewById(R.id.tableLayout);
		btn_suadm = (Button) findViewById(R.id.btn_suadm);
		btn_tmdm = (Button) findViewById(R.id.btn_tmdm);
		btn_xoadm = (Button) findViewById(R.id.btn_xoadm);
		btn_exitDM = (Button) findViewById(R.id.btn_exitDM);

		//int id = 1;
		// DMDV_DanhMucDV dmdv_sample = new DMDV_DanhMucDV(id, "001", "001",
		// "001", "001", "001", "001", "001", "001", "001", "001", "001", "001",
		// "001", "001", "001", "001", "001", "001", "001", "001");
		// database.addNewDV(dmdv_sample);
		try {
			dmdvList = database.getAllDMDV();
			buidTableTitle();
			for (int i = 0; i < database.getDMDVCount(); i++) {
				buildTableData(dmdvList.get(i));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

		btn_tmdm.setOnClickListener(new MyEvent());
		btn_suadm.setOnClickListener(new MyEvent());
		btn_xoadm.setOnClickListener(new MyEvent());
		btn_exitDM.setOnClickListener(new MyEvent());
	}

	@Override
	public void onBackPressed() {
		// do nothing.
	}

	private class MyEvent implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_tmdm:
				Intent intent_tmdm = new Intent(DM_MainActivity.this,
						DM_ThemMoi_Activity.class);
				startActivity(intent_tmdm);
				break;
			case R.id.btn_suadm:
				if (row_selected != -1) {
					Intent intent_suadm = new Intent(DM_MainActivity.this,
							DM_SuaTT_Activity.class);
					intent_suadm.putExtra("dmdv", dmdv_selected);
					startActivity(intent_suadm);
				} else {
					Toast.makeText(getApplicationContext(), "Chọn một DMDV để thực hiện chức năng này!", Toast.LENGTH_LONG).show();
				}
				break;
			case R.id.btn_xoadm:
				if (row_selected != -1) {
					AlertDialog.Builder b = new AlertDialog.Builder(
							DM_MainActivity.this);
					b.setTitle("Thông báo");
					b.setMessage("Bạn có chắc chắn muốn xóa dịch vụ đã chọn?");
					b.setPositiveButton("Đồng ý",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									if(database.deleteDMDV(dmdv_selected.getID()) <= 0){
										Toast.makeText(getApplicationContext(), "Chưa xóa được dịch vụ " + dmdv_selected.getTenDV() + ". Thử lại sau!", Toast.LENGTH_LONG);
									}
									else{
										dmdvList_afterDel = database.getAllDMDV();
										table.removeAllViews();
										buidTableTitle();
										for (int i = 0; i < database
												.getDMDVCount(); i++) {
											buildTableData(dmdvList_afterDel.get(i));
										}
										Toast toast = Toast.makeText(
												DM_MainActivity.this,
												"Đã xóa dịch vụ!",
												Toast.LENGTH_SHORT);
										toast.show();
										row_selected = -1;
									}
								}
							});
					b.setNegativeButton("Thoát",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									dialog.cancel();
								}
							});
					b.create().show();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Chọn một DMDV để thực hiện chức năng này!", Toast.LENGTH_LONG).show();
				}
				break;
			case R.id.btn_exitDM:
				Intent intent = new Intent(DM_MainActivity.this,
						MainActivity.class);
				startActivity(intent);
				finish();
				break;
			default:
				break;
			}
		}
	}

	private void buidTableTitle() {
		TableRow row = new TableRow(this);
		row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		row.setBackgroundColor(Color.BLACK);

		TextView tv1 = new TextView(this);
		LayoutParams lo1 = new LayoutParams(100, 40);
		lo1.setMargins(0, 0, 2, 0);
		tv1.setLayoutParams(lo1);
		// tv1.setBackgroundResource(R.drawable.cell_shape);
		tv1.setGravity(Gravity.CENTER);
		tv1.setText("Mã DV");
		tv1.setBackgroundColor(Color.GRAY);
		row.addView(tv1);

		TextView tv2 = new TextView(this);
		LayoutParams lo2 = new LayoutParams(250, 40);
		lo2.setMargins(0, 0, 2, 0);
		tv2.setLayoutParams(lo2);
		// tv2.setBackgroundResource(R.drawable.cell_shape);
		tv2.setGravity(Gravity.CENTER);
		tv2.setText("Tên DV");
		tv2.setBackgroundColor(Color.GRAY);
		row.addView(tv2);

		TextView tv3 = new TextView(this);
		LayoutParams lo3 = new LayoutParams(200, 40);
		lo3.setMargins(0, 0, 2, 0);
		tv3.setLayoutParams(lo3);
		// tv3.setBackgroundResource(R.drawable.cell_shape);
		tv3.setGravity(Gravity.CENTER);
		tv3.setText("Tên Tiếng Anh");
		tv3.setBackgroundColor(Color.GRAY);
		row.addView(tv3);

		TextView tv4 = new TextView(this);
		LayoutParams lo4 = new LayoutParams(150, 40);
		lo4.setMargins(0, 0, 2, 0);
		tv4.setLayoutParams(lo4);
		// tv4.setBackgroundResource(R.drawable.cell_shape);
		tv4.setGravity(Gravity.CENTER);
		tv4.setText("Mã Vạch");
		tv4.setBackgroundColor(Color.GRAY);
		row.addView(tv4);

		TextView tv5 = new TextView(this);
		LayoutParams lo5 = new LayoutParams(150, 40);
		lo5.setMargins(0, 0, 2, 0);
		tv5.setLayoutParams(lo5);
		// tv5.setBackgroundResource(R.drawable.cell_shape);
		tv5.setGravity(Gravity.CENTER);
		tv5.setText("Chi Nhánh Bán");
		tv5.setBackgroundColor(Color.GRAY);
		row.addView(tv5);

		TextView tv6 = new TextView(this);
		LayoutParams lo6 = new LayoutParams(150, 40);
		lo6.setMargins(0, 0, 2, 0);
		tv6.setLayoutParams(lo6);
		// tv6.setBackgroundResource(R.drawable.cell_shape);
		tv6.setGravity(Gravity.CENTER);
		tv6.setText("Khu Vực Bán");
		tv6.setBackgroundColor(Color.GRAY);
		row.addView(tv6);

		TextView tv7 = new TextView(this);
		LayoutParams lo7 = new LayoutParams(200, 40);
		lo7.setMargins(0, 0, 2, 0);
		tv7.setLayoutParams(lo7);
		// tv7.setBackgroundResource(R.drawable.cell_shape);
		tv7.setGravity(Gravity.CENTER);
		tv7.setText("Nhóm");
		tv7.setBackgroundColor(Color.GRAY);
		row.addView(tv7);

		TextView tv8 = new TextView(this);
		LayoutParams lo8 = new LayoutParams(150, 40);
		lo8.setMargins(0, 0, 2, 0);
		tv8.setLayoutParams(lo8);
		// tv8.setBackgroundResource(R.drawable.cell_shape);
		tv8.setGravity(Gravity.CENTER);
		tv8.setText("Đơn Giá");
		tv8.setBackgroundColor(Color.GRAY);
		row.addView(tv8);

		TextView tv9 = new TextView(this);
		LayoutParams lo9 = new LayoutParams(150, 40);
		lo9.setMargins(0, 0, 2, 0);
		tv9.setLayoutParams(lo9);
		// tv9.setBackgroundResource(R.drawable.cell_shape);
		tv9.setGravity(Gravity.CENTER);
		tv9.setText("Giá KM1");
		tv9.setBackgroundColor(Color.GRAY);
		row.addView(tv9);

		TextView tv10 = new TextView(this);
		LayoutParams lo10 = new LayoutParams(150, 40);
		lo10.setMargins(0, 0, 2, 0);
		tv10.setLayoutParams(lo10);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv10.setGravity(Gravity.CENTER);
		tv10.setText("Giá KM2");
		tv10.setBackgroundColor(Color.GRAY);
		row.addView(tv10);

		TextView tv11 = new TextView(this);
		LayoutParams lo11 = new LayoutParams(150, 40);
		lo11.setMargins(0, 0, 2, 0);
		tv11.setLayoutParams(lo11);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv11.setGravity(Gravity.CENTER);
		tv11.setText("Giá KM%");
		tv11.setBackgroundColor(Color.GRAY);
		row.addView(tv11);

		TextView tv12 = new TextView(this);
		LayoutParams lo12 = new LayoutParams(150, 40);
		lo12.setMargins(0, 0, 2, 0);
		tv12.setLayoutParams(lo12);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv12.setGravity(Gravity.CENTER);
		tv12.setText("Giá Chế Biến");
		tv12.setBackgroundColor(Color.GRAY);
		row.addView(tv12);

		TextView tv13 = new TextView(this);
		LayoutParams lo13 = new LayoutParams(100, 40);
		lo13.setMargins(0, 0, 2, 0);
		tv13.setLayoutParams(lo13);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv13.setGravity(Gravity.CENTER);
		tv13.setText("ĐVT");
		tv13.setBackgroundColor(Color.GRAY);
		row.addView(tv13);

		TextView tv14 = new TextView(this);
		LayoutParams lo14 = new LayoutParams(150, 40);
		lo14.setMargins(0, 0, 2, 0);
		tv14.setLayoutParams(lo14);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv14.setGravity(Gravity.CENTER);
		tv14.setText("In Phiếu");
		tv14.setBackgroundColor(Color.GRAY);
		row.addView(tv14);

		TextView tv15 = new TextView(this);
		LayoutParams lo15 = new LayoutParams(150, 40);
		lo15.setMargins(0, 0, 2, 0);
		tv15.setLayoutParams(lo15);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv15.setGravity(Gravity.CENTER);
		tv15.setText("Cho Phép Bán");
		tv15.setBackgroundColor(Color.GRAY);
		row.addView(tv15);

		TextView tv16 = new TextView(this);
		LayoutParams lo16 = new LayoutParams(150, 40);
		lo16.setMargins(0, 0, 2, 0);
		tv16.setLayoutParams(lo16);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv16.setGravity(Gravity.CENTER);
		tv16.setText("NV Đã Sửa");
		tv16.setBackgroundColor(Color.GRAY);
		row.addView(tv16);

		TextView tv17 = new TextView(this);
		LayoutParams lo17 = new LayoutParams(200, 40);
		lo17.setMargins(0, 0, 2, 0);
		tv17.setLayoutParams(lo17);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv17.setGravity(Gravity.CENTER);
		tv17.setText("Ngày Cập Nhật");
		tv17.setBackgroundColor(Color.GRAY);
		row.addView(tv17);

		table.addView(row);
	}

	private void buildTableData(DMDV_DanhMucDV dmdv) {

		final TableRow row = new TableRow(this);
		row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		registerForContextMenu(row);

		row.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// row.setBackgroundResource(R.drawable.table_row_selector);
				for (int i = 1; i < table.getChildCount(); i++) {
					TableRow row_item = (TableRow) table.getChildAt(i);
					row_item.setBackgroundResource(R.drawable.tr_select);
				}
				row.setBackgroundResource(R.drawable.tr_selected);
				row_selected = table.indexOfChild(row);
				dmdv_selected = dmdvList.get(row_selected - 1);
			}
		});

		TextView tv1 = new TextView(this);
		LayoutParams lo1 = new LayoutParams(100, 50);
		lo1.setMargins(0, 0, 2, 0);
		tv1.setLayoutParams(lo1);
		tv1.setGravity(Gravity.CENTER);
		tv1.setBackgroundResource(R.drawable.cell_shape);
		tv1.setText(dmdv.getMaDV());
		row.addView(tv1);

		TextView tv2 = new TextView(this);
		LayoutParams lo2 = new LayoutParams(250, 50);
		lo2.setMargins(0, 0, 2, 0);
		tv2.setLayoutParams(lo2);
		tv2.setGravity(Gravity.CENTER);
		tv2.setBackgroundResource(R.drawable.cell_shape);
		tv2.setText(dmdv.getTenDV());
		row.addView(tv2);

		TextView tv3 = new TextView(this);
		LayoutParams lo3 = new LayoutParams(200, 50);
		lo3.setMargins(0, 0, 2, 0);
		tv3.setLayoutParams(lo3);
		tv3.setGravity(Gravity.CENTER);
		tv3.setBackgroundResource(R.drawable.cell_shape);
		tv3.setText(dmdv.getTenTAnh());
		row.addView(tv3);

		TextView tv4 = new TextView(this);
		LayoutParams lo4 = new LayoutParams(150, 50);
		lo4.setMargins(0, 0, 2, 0);
		tv4.setLayoutParams(lo4);
		tv4.setGravity(Gravity.CENTER);
		tv4.setBackgroundResource(R.drawable.cell_shape);
		tv4.setText(dmdv.getMaVach());
		row.addView(tv4);

		TextView tv5 = new TextView(this);
		LayoutParams lo5 = new LayoutParams(150, 50);
		lo5.setMargins(0, 0, 2, 0);
		tv5.setLayoutParams(lo5);
		tv5.setGravity(Gravity.CENTER);
		tv5.setBackgroundResource(R.drawable.cell_shape);
		tv5.setText(dmdv.getChiNhanh());
		row.addView(tv5);

		TextView tv6 = new TextView(this);
		LayoutParams lo6 = new LayoutParams(150, 50);
		lo6.setMargins(0, 0, 2, 0);
		tv6.setLayoutParams(lo6);
		tv6.setGravity(Gravity.CENTER);
		tv6.setBackgroundResource(R.drawable.cell_shape);
		tv6.setText(dmdv.getKhuVuc());
		row.addView(tv6);

		TextView tv7 = new TextView(this);
		LayoutParams lo7 = new LayoutParams(200, 50);
		lo7.setMargins(0, 0, 2, 0);
		tv7.setLayoutParams(lo7);
		tv7.setGravity(Gravity.CENTER);
		tv7.setBackgroundResource(R.drawable.cell_shape);
		tv7.setText(dmdv.getNhomDV());
		row.addView(tv7);

		TextView tv8 = new TextView(this);
		LayoutParams lo8 = new LayoutParams(150, 50);
		lo8.setMargins(0, 0, 2, 0);
		tv8.setLayoutParams(lo8);
		tv8.setGravity(Gravity.CENTER);
		tv8.setBackgroundResource(R.drawable.cell_shape);
		tv8.setText(dmdv.getDonGia());
		row.addView(tv8);

		TextView tv9 = new TextView(this);
		LayoutParams lo9 = new LayoutParams(150, 50);
		lo9.setMargins(0, 0, 2, 0);
		tv9.setLayoutParams(lo9);
		tv9.setGravity(Gravity.CENTER);
		tv9.setBackgroundResource(R.drawable.cell_shape);
		tv9.setText(dmdv.getGiaKM1());
		row.addView(tv9);

		TextView tv10 = new TextView(this);
		LayoutParams lo10 = new LayoutParams(150, 50);
		lo10.setMargins(0, 0, 2, 0);
		tv10.setLayoutParams(lo10);
		tv10.setGravity(Gravity.CENTER);
		tv10.setBackgroundResource(R.drawable.cell_shape);
		tv10.setText(dmdv.getGiaKM2());
		row.addView(tv10);

		TextView tv11 = new TextView(this);
		LayoutParams lo11 = new LayoutParams(150, 50);
		lo11.setMargins(0, 0, 2, 0);
		tv11.setLayoutParams(lo11);
		tv11.setGravity(Gravity.CENTER);
		tv11.setBackgroundResource(R.drawable.cell_shape);
		tv11.setText(dmdv.getFtGiamGia());
		row.addView(tv11);

		TextView tv12 = new TextView(this);
		LayoutParams lo12 = new LayoutParams(150, 50);
		lo12.setMargins(0, 0, 2, 0);
		tv12.setLayoutParams(lo12);
		tv12.setGravity(Gravity.CENTER);
		tv12.setBackgroundResource(R.drawable.cell_shape);
		tv12.setText(dmdv.getDonGiaCheBien());
		row.addView(tv12);

		TextView tv13 = new TextView(this);
		LayoutParams lo13 = new LayoutParams(100, 50);
		lo13.setMargins(0, 0, 2, 0);
		tv13.setLayoutParams(lo13);
		tv13.setGravity(Gravity.CENTER);
		tv13.setBackgroundResource(R.drawable.cell_shape);
		tv13.setText(dmdv.getDonVi());
		row.addView(tv13);

		TextView tv14 = new TextView(this);
		LayoutParams lo14 = new LayoutParams(250, 50);
		lo14.setMargins(0, 0, 2, 0);
		tv14.setLayoutParams(lo14);
		tv14.setGravity(Gravity.CENTER);
		tv14.setBackgroundResource(R.drawable.cell_shape);
		tv14.setText(dmdv.getThuocTinhIn());
		row.addView(tv14);

		TextView tv15 = new TextView(this);
		LayoutParams lo15 = new LayoutParams(150, 50);
		lo15.setMargins(0, 0, 2, 0);
		tv15.setLayoutParams(lo15);
		tv15.setGravity(Gravity.CENTER);
		tv15.setBackgroundResource(R.drawable.cell_shape);
		tv15.setText(dmdv.getChoPhepBan());
		row.addView(tv15);

		TextView tv16 = new TextView(this);
		LayoutParams lo16 = new LayoutParams(200, 50);
		lo16.setMargins(0, 0, 2, 0);
		tv16.setLayoutParams(lo16);
		tv16.setGravity(Gravity.CENTER);
		tv16.setBackgroundResource(R.drawable.cell_shape);
		tv16.setText("N/A");
		row.addView(tv16);

		TextView tv17 = new TextView(this);
		LayoutParams lo17 = new LayoutParams(150, 50);
		lo17.setMargins(0, 0, 2, 0);
		tv17.setLayoutParams(lo17);
		tv17.setGravity(Gravity.CENTER);
		tv17.setBackgroundResource(R.drawable.cell_shape);
		tv17.setText(dmdv.getNgaySuaGia());
		row.addView(tv17);

		table.addView(row);
	}
}
