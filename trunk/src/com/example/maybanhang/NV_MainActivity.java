package com.example.maybanhang;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

public class NV_MainActivity extends Activity {

	private TableLayout table;
	private TextView tv_time, tv_sologun, tv_qlnv;
	private Button btn_tmnv, btn_suanv, btn_xoanv, btn_exitnvmain;
	private DatabaseHandler db = new DatabaseHandler(this);
	private List<NV_NhanVien> nhanvienList, nhanvienList_afterDel;
	private NV_NhanVien nhanvien_selected;
	public int row_selected = -1;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// set content view AFTER ABOVE sequence (to avoid crash)
		this.setContentView(R.layout.nv_main);

		btn_tmnv = (Button) findViewById(R.id.btn_tmnv);
		btn_suanv = (Button) findViewById(R.id.btn_suanv);
		btn_xoanv = (Button) findViewById(R.id.btn_xoanv);
		btn_exitnvmain = (Button) findViewById(R.id.btn_exitnvmain);
		tv_qlnv = (TextView) findViewById(R.id.tv_qlnv);
		table = (TableLayout) findViewById(R.id.tableLayout);

		Typeface tf2 = Typeface
				.createFromAsset(getAssets(), "fonts/Tahoma.ttf");
		tv_qlnv.setTypeface(tf2);

		nhanvienList = db.getAllNhanVien();

		buidTableTitle();
		for (int i = 0; i < db.getNhanvienCount(); i++) {
			buildTableData(nhanvienList.get(i));
		}

		btn_tmnv.setOnClickListener(new MyEvent());
		btn_suanv.setOnClickListener(new MyEvent());
		btn_xoanv.setOnClickListener(new MyEvent());
		btn_exitnvmain.setOnClickListener(new MyEvent());
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
			case R.id.btn_tmnv:
				Intent intent_them = new Intent(NV_MainActivity.this,
						NV_ThemMoi_Activity.class);
				startActivity(intent_them);
				break;
			case R.id.btn_suanv:
				if (row_selected != -1) {
				Intent intent_sua = new Intent(NV_MainActivity.this,
						NV_SuaTTin_Activity.class);				
					intent_sua.putExtra("nhanvien", nhanvien_selected);
					startActivity(intent_sua);
				}
				else {
					AlertDialog.Builder b = new AlertDialog.Builder(
							NV_MainActivity.this);
					b.setTitle("Thông báo");
					b.setMessage("Vui lòng chọn một nhân viên để thực hiện chức năng này!");
					b.setPositiveButton("Đồng ý",
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
				break;
			case R.id.btn_xoanv:
				if (row_selected != -1) {
					AlertDialog.Builder b = new AlertDialog.Builder(
							NV_MainActivity.this);
					b.setTitle("Thông báo");
					b.setMessage("Bạn có chắc chắn muốn xóa nhân viên đã chọn?");
					b.setPositiveButton("Đồng ý",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									db.deleteNhanvien(nhanvien_selected.getID());
									nhanvienList_afterDel = db.getAllNhanVien();
									table.removeAllViews();
									buidTableTitle();
									for (int i = 0; i < db.getNhanvienCount(); i++) {
										buildTableData(nhanvienList_afterDel
												.get(i));
									}
									Toast toast = Toast.makeText(
											NV_MainActivity.this,
											"Đã xóa nhân viên!",
											Toast.LENGTH_SHORT);
									toast.show();
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
					AlertDialog.Builder b = new AlertDialog.Builder(
							NV_MainActivity.this);
					b.setTitle("Thông báo");
					b.setMessage("Vui lòng chọn một nhân viên để thực hiện chức năng này!");
					b.setPositiveButton("Đồng ý",
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
				break;
			case R.id.btn_exitnvmain:
				Intent intent = new Intent(NV_MainActivity.this,
						MainActivity.class);
				startActivity(intent);
				finish();
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
		LayoutParams lo1 = new LayoutParams(150, 40);
		lo1.setMargins(0, 0, 2, 0);
		tv1.setLayoutParams(lo1);
		// tv1.setBackgroundResource(R.drawable.cell_shape);
		tv1.setGravity(Gravity.CENTER);
		tv1.setText("Tài Khoản");
		tv1.setBackgroundColor(Color.GRAY);
		row.addView(tv1);

		TextView tv2 = new TextView(this);
		LayoutParams lo2 = new LayoutParams(200, 40);
		lo2.setMargins(0, 0, 2, 0);
		tv2.setLayoutParams(lo2);
		// tv2.setBackgroundResource(R.drawable.cell_shape);
		tv2.setGravity(Gravity.CENTER);
		tv2.setText("Họ và Tên");
		tv2.setBackgroundColor(Color.GRAY);
		row.addView(tv2);

		TextView tv3 = new TextView(this);
		LayoutParams lo3 = new LayoutParams(100, 40);
		lo3.setMargins(0, 0, 2, 0);
		tv3.setLayoutParams(lo3);
		// tv3.setBackgroundResource(R.drawable.cell_shape);
		tv3.setGravity(Gravity.CENTER);
		tv3.setText("Mã Thẻ NV");
		tv3.setBackgroundColor(Color.GRAY);
		row.addView(tv3);

		TextView tv4 = new TextView(this);
		LayoutParams lo4 = new LayoutParams(150, 40);
		lo4.setMargins(0, 0, 2, 0);
		tv4.setLayoutParams(lo4);
		// tv4.setBackgroundResource(R.drawable.cell_shape);
		tv4.setGravity(Gravity.CENTER);
		tv4.setText("Trạng Thái");
		tv4.setBackgroundColor(Color.GRAY);
		row.addView(tv4);

		TextView tv5 = new TextView(this);
		LayoutParams lo5 = new LayoutParams(150, 40);
		lo5.setMargins(0, 0, 2, 0);
		tv5.setLayoutParams(lo5);
		// tv5.setBackgroundResource(R.drawable.cell_shape);
		tv5.setGravity(Gravity.CENTER);
		tv5.setText("Chi Nhánh");
		tv5.setBackgroundColor(Color.GRAY);
		row.addView(tv5);

		TextView tv6 = new TextView(this);
		LayoutParams lo6 = new LayoutParams(150, 40);
		lo6.setMargins(0, 0, 2, 0);
		tv6.setLayoutParams(lo6);
		// tv6.setBackgroundResource(R.drawable.cell_shape);
		tv6.setGravity(Gravity.CENTER);
		tv6.setText("Bộ Phận");
		tv6.setBackgroundColor(Color.GRAY);
		row.addView(tv6);

		TextView tv7 = new TextView(this);
		LayoutParams lo7 = new LayoutParams(150, 40);
		lo7.setMargins(0, 0, 2, 0);
		tv7.setLayoutParams(lo7);
		// tv7.setBackgroundResource(R.drawable.cell_shape);
		tv7.setGravity(Gravity.CENTER);
		tv7.setText("Chức Danh");
		tv7.setBackgroundColor(Color.GRAY);
		row.addView(tv7);

		TextView tv8 = new TextView(this);
		LayoutParams lo8 = new LayoutParams(100, 40);
		lo8.setMargins(0, 0, 2, 0);
		tv8.setLayoutParams(lo8);
		// tv8.setBackgroundResource(R.drawable.cell_shape);
		tv8.setGravity(Gravity.CENTER);
		tv8.setText("Giới Tính");
		tv8.setBackgroundColor(Color.GRAY);
		row.addView(tv8);

		TextView tv9 = new TextView(this);
		LayoutParams lo9 = new LayoutParams(150, 40);
		lo9.setMargins(0, 0, 2, 0);
		tv9.setLayoutParams(lo9);
		// tv9.setBackgroundResource(R.drawable.cell_shape);
		tv9.setGravity(Gravity.CENTER);
		tv9.setText("Ngày Sinh");
		tv9.setBackgroundColor(Color.GRAY);
		row.addView(tv9);

		TextView tv10 = new TextView(this);
		LayoutParams lo10 = new LayoutParams(150, 40);
		lo10.setMargins(0, 0, 2, 0);
		tv10.setLayoutParams(lo10);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv10.setGravity(Gravity.CENTER);
		tv10.setText("Quê Quán");
		tv10.setBackgroundColor(Color.GRAY);
		row.addView(tv10);

		TextView tv11 = new TextView(this);
		LayoutParams lo11 = new LayoutParams(150, 40);
		lo11.setMargins(0, 0, 2, 0);
		tv11.setLayoutParams(lo11);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv11.setGravity(Gravity.CENTER);
		tv11.setText("Số CMTND");
		tv11.setBackgroundColor(Color.GRAY);
		row.addView(tv11);

		TextView tv12 = new TextView(this);
		LayoutParams lo12 = new LayoutParams(150, 40);
		lo12.setMargins(0, 0, 2, 0);
		tv12.setLayoutParams(lo12);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv12.setGravity(Gravity.CENTER);
		tv12.setText("Ngày Cấp");
		tv12.setBackgroundColor(Color.GRAY);
		row.addView(tv12);

		TextView tv13 = new TextView(this);
		LayoutParams lo13 = new LayoutParams(150, 40);
		lo13.setMargins(0, 0, 2, 0);
		tv13.setLayoutParams(lo13);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv13.setGravity(Gravity.CENTER);
		tv13.setText("Nơi Cấp");
		tv13.setBackgroundColor(Color.GRAY);
		row.addView(tv13);

		TextView tv14 = new TextView(this);
		LayoutParams lo14 = new LayoutParams(100, 40);
		lo14.setMargins(0, 0, 2, 0);
		tv14.setLayoutParams(lo14);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv14.setGravity(Gravity.CENTER);
		tv14.setText("Hệ Số Lương");
		tv14.setBackgroundColor(Color.GRAY);
		row.addView(tv14);

		TextView tv15 = new TextView(this);
		LayoutParams lo15 = new LayoutParams(100, 40);
		lo15.setMargins(0, 0, 2, 0);
		tv15.setLayoutParams(lo15);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv15.setGravity(Gravity.CENTER);
		tv15.setText("Phụ Cấp");
		tv15.setBackgroundColor(Color.GRAY);
		row.addView(tv15);

		TextView tv16 = new TextView(this);
		LayoutParams lo16 = new LayoutParams(150, 40);
		lo16.setMargins(0, 0, 2, 0);
		tv16.setLayoutParams(lo16);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv16.setGravity(Gravity.CENTER);
		tv16.setText("Thưởng Tháng");
		tv16.setBackgroundColor(Color.GRAY);
		row.addView(tv16);

		TextView tv17 = new TextView(this);
		LayoutParams lo17 = new LayoutParams(150, 40);
		lo17.setMargins(0, 0, 2, 0);
		tv17.setLayoutParams(lo17);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv17.setGravity(Gravity.CENTER);
		tv17.setText("Phạt Tháng");
		tv17.setBackgroundColor(Color.GRAY);
		row.addView(tv17);

		TextView tv18 = new TextView(this);
		LayoutParams lo18 = new LayoutParams(150, 40);
		lo18.setMargins(0, 0, 2, 0);
		tv18.setLayoutParams(lo18);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv18.setGravity(Gravity.CENTER);
		tv18.setText("Đặt Cọc");
		tv18.setBackgroundColor(Color.GRAY);
		row.addView(tv18);

		TextView tv19 = new TextView(this);
		LayoutParams lo19 = new LayoutParams(150, 40);
		lo19.setMargins(0, 0, 2, 0);
		tv19.setLayoutParams(lo19);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv19.setGravity(Gravity.CENTER);
		tv19.setText("Ngày Vào Làm");
		tv19.setBackgroundColor(Color.GRAY);
		row.addView(tv19);

		TextView tv20 = new TextView(this);
		LayoutParams lo20 = new LayoutParams(150, 40);
		lo20.setMargins(0, 0, 2, 0);
		tv20.setLayoutParams(lo20);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv20.setGravity(Gravity.CENTER);
		tv20.setText("Ngày Nghỉ Việc");
		tv20.setBackgroundColor(Color.GRAY);
		row.addView(tv20);

		TextView tv21 = new TextView(this);
		LayoutParams lo21 = new LayoutParams(150, 40);
		lo21.setMargins(0, 0, 2, 0);
		tv21.setLayoutParams(lo21);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv21.setGravity(Gravity.CENTER);
		tv21.setText("Loại Hợp Đồng");
		tv21.setBackgroundColor(Color.GRAY);
		row.addView(tv21);

		TextView tv22 = new TextView(this);
		LayoutParams lo22 = new LayoutParams(150, 40);
		lo22.setMargins(0, 0, 2, 0);
		tv22.setLayoutParams(lo22);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv22.setGravity(Gravity.CENTER);
		tv22.setText("Lần Truy Cập Cuối");
		tv22.setBackgroundColor(Color.GRAY);
		row.addView(tv22);

		TextView tv23 = new TextView(this);
		LayoutParams lo23 = new LayoutParams(200, 40);
		lo23.setMargins(0, 0, 2, 0);
		tv23.setLayoutParams(lo23);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv23.setGravity(Gravity.CENTER);
		tv23.setText("Ghi Chú");
		tv23.setBackgroundColor(Color.GRAY);
		row.addView(tv23);

		table.addView(row);
	}

	private void buildTableData(NV_NhanVien nhanvien) {

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
				nhanvien_selected = nhanvienList.get(row_selected - 1);
			}
		});

		TextView tv1 = new TextView(this);
		LayoutParams lo1 = new LayoutParams(150, 50);
		lo1.setMargins(0, 0, 2, 0);
		tv1.setLayoutParams(lo1);
		tv1.setGravity(Gravity.CENTER);
		tv1.setBackgroundResource(R.drawable.cell_shape);
		// tv1.setPadding(5, 5, 5, 5);
		tv1.setText(nhanvien.getMaNV());
		row.addView(tv1);

		TextView tv2 = new TextView(this);
		LayoutParams lo2 = new LayoutParams(200, 50);
		lo2.setMargins(0, 0, 2, 0);
		tv2.setLayoutParams(lo2);
		tv2.setGravity(Gravity.CENTER);
		tv2.setBackgroundResource(R.drawable.cell_shape);
		// tv2.setPadding(5, 5, 5, 5);
		tv2.setText(nhanvien.getHoTen());
		row.addView(tv2);

		TextView tv3 = new TextView(this);
		LayoutParams lo3 = new LayoutParams(100, 50);
		lo3.setMargins(0, 0, 2, 0);
		tv3.setLayoutParams(lo3);
		// tv3.setBackgroundResource(R.drawable.cell_shape);
		tv3.setGravity(Gravity.CENTER);
		tv3.setBackgroundResource(R.drawable.cell_shape);
		// tv3.setPadding(5, 5, 5, 5);
		tv3.setText(nhanvien.getMaThe());
		row.addView(tv3);

		TextView tv4 = new TextView(this);
		LayoutParams lo4 = new LayoutParams(150, 50);
		lo4.setMargins(0, 0, 2, 0);
		tv4.setLayoutParams(lo4);
		// tv4.setBackgroundResource(R.drawable.cell_shape);
		tv4.setGravity(Gravity.CENTER);
		tv4.setBackgroundResource(R.drawable.cell_shape);
		// tv4.setPadding(5, 5, 5, 5);
		tv4.setText(nhanvien.getTrangThai());
		row.addView(tv4);

		TextView tv5 = new TextView(this);
		LayoutParams lo5 = new LayoutParams(150, 50);
		lo5.setMargins(0, 0, 2, 0);
		tv5.setLayoutParams(lo5);
		// tv5.setBackgroundResource(R.drawable.cell_shape);
		tv5.setGravity(Gravity.CENTER);
		tv5.setBackgroundResource(R.drawable.cell_shape);
		// tv5.setPadding(5, 5, 5, 5);
		tv5.setText(nhanvien.getChiNhanh());
		row.addView(tv5);

		TextView tv6 = new TextView(this);
		LayoutParams lo6 = new LayoutParams(150, 50);
		lo6.setMargins(0, 0, 2, 0);
		tv6.setLayoutParams(lo6);
		// tv6.setBackgroundResource(R.drawable.cell_shape);
		tv6.setGravity(Gravity.CENTER);
		tv6.setBackgroundResource(R.drawable.cell_shape);
		// tv6.setPadding(5, 5, 5, 5);
		tv6.setText(nhanvien.getBoPhan());
		row.addView(tv6);

		TextView tv7 = new TextView(this);
		LayoutParams lo7 = new LayoutParams(150, 50);
		lo7.setMargins(0, 0, 2, 0);
		tv7.setLayoutParams(lo7);
		// tv7.setBackgroundResource(R.drawable.cell_shape);
		tv7.setGravity(Gravity.CENTER);
		tv7.setBackgroundResource(R.drawable.cell_shape);
		// tv7.setPadding(5, 5, 5, 5);
		tv7.setText(nhanvien.getChucDanh());
		row.addView(tv7);

		TextView tv8 = new TextView(this);
		LayoutParams lo8 = new LayoutParams(100, 50);
		lo8.setMargins(0, 0, 2, 0);
		tv8.setLayoutParams(lo8);
		// tv8.setBackgroundResource(R.drawable.cell_shape);
		tv8.setGravity(Gravity.CENTER);
		tv8.setBackgroundResource(R.drawable.cell_shape);
		// tv8.setPadding(5, 5, 5, 5);
		tv8.setText(nhanvien.getGioiTinh());
		row.addView(tv8);

		TextView tv9 = new TextView(this);
		LayoutParams lo9 = new LayoutParams(150, 50);
		lo9.setMargins(0, 0, 2, 0);
		tv9.setLayoutParams(lo9);
		// tv9.setBackgroundResource(R.drawable.cell_shape);
		tv9.setGravity(Gravity.CENTER);
		tv9.setBackgroundResource(R.drawable.cell_shape);
		// tv9.setPadding(5, 5, 5, 5);
		tv9.setText(nhanvien.getNgaySinh());
		row.addView(tv9);

		TextView tv10 = new TextView(this);
		LayoutParams lo10 = new LayoutParams(150, 50);
		lo10.setMargins(0, 0, 2, 0);
		tv10.setLayoutParams(lo10);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv10.setGravity(Gravity.CENTER);
		tv10.setBackgroundResource(R.drawable.cell_shape);
		// tv10.setPadding(5, 5, 5, 5);
		tv10.setText(nhanvien.getQueQuan());
		row.addView(tv10);

		TextView tv11 = new TextView(this);
		LayoutParams lo11 = new LayoutParams(150, 50);
		lo11.setMargins(0, 0, 2, 0);
		tv11.setLayoutParams(lo11);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv11.setGravity(Gravity.CENTER);
		tv11.setBackgroundResource(R.drawable.cell_shape);
		// tv10.setPadding(5, 5, 5, 5);
		tv11.setText(nhanvien.getCMTND());
		row.addView(tv11);

		TextView tv12 = new TextView(this);
		LayoutParams lo12 = new LayoutParams(150, 50);
		lo12.setMargins(0, 0, 2, 0);
		tv12.setLayoutParams(lo12);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv12.setGravity(Gravity.CENTER);
		tv12.setBackgroundResource(R.drawable.cell_shape);
		// tv10.setPadding(5, 5, 5, 5);
		tv12.setText(nhanvien.getNgayCap());
		row.addView(tv12);

		TextView tv13 = new TextView(this);
		LayoutParams lo13 = new LayoutParams(150, 50);
		lo13.setMargins(0, 0, 2, 0);
		tv13.setLayoutParams(lo13);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv13.setGravity(Gravity.CENTER);
		tv13.setBackgroundResource(R.drawable.cell_shape);
		// tv10.setPadding(5, 5, 5, 5);
		tv13.setText(nhanvien.getNoiCap());
		row.addView(tv13);

		TextView tv14 = new TextView(this);
		LayoutParams lo14 = new LayoutParams(100, 50);
		lo14.setMargins(0, 0, 2, 0);
		tv14.setLayoutParams(lo14);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv14.setGravity(Gravity.CENTER);
		tv14.setBackgroundResource(R.drawable.cell_shape);
		// tv10.setPadding(5, 5, 5, 5);
		tv14.setText(nhanvien.getHSLuong());
		row.addView(tv14);

		TextView tv15 = new TextView(this);
		LayoutParams lo15 = new LayoutParams(100, 50);
		lo15.setMargins(0, 0, 2, 0);
		tv15.setLayoutParams(lo15);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv15.setGravity(Gravity.CENTER);
		tv15.setBackgroundResource(R.drawable.cell_shape);
		// tv10.setPadding(5, 5, 5, 5);
		tv15.setText(nhanvien.getTroCap());
		row.addView(tv15);

		TextView tv16 = new TextView(this);
		LayoutParams lo16 = new LayoutParams(150, 50);
		lo16.setMargins(0, 0, 2, 0);
		tv16.setLayoutParams(lo16);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv16.setGravity(Gravity.CENTER);
		tv16.setBackgroundResource(R.drawable.cell_shape);
		// tv10.setPadding(5, 5, 5, 5);
		tv16.setText(nhanvien.getThangThuong());
		row.addView(tv16);

		TextView tv17 = new TextView(this);
		LayoutParams lo17 = new LayoutParams(150, 50);
		lo17.setMargins(0, 0, 2, 0);
		tv17.setLayoutParams(lo17);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv17.setGravity(Gravity.CENTER);
		tv17.setBackgroundResource(R.drawable.cell_shape);
		// tv10.setPadding(5, 5, 5, 5);
		tv17.setText(nhanvien.getThangPhat());
		row.addView(tv17);

		TextView tv18 = new TextView(this);
		LayoutParams lo18 = new LayoutParams(150, 50);
		lo18.setMargins(0, 0, 2, 0);
		tv18.setLayoutParams(lo18);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv18.setGravity(Gravity.CENTER);
		tv18.setBackgroundResource(R.drawable.cell_shape);
		// tv10.setPadding(5, 5, 5, 5);
		tv18.setText(nhanvien.getDatCoc());
		row.addView(tv18);

		TextView tv19 = new TextView(this);
		LayoutParams lo19 = new LayoutParams(150, 50);
		lo19.setMargins(0, 0, 2, 0);
		tv19.setLayoutParams(lo19);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv19.setGravity(Gravity.CENTER);
		tv19.setBackgroundResource(R.drawable.cell_shape);
		// tv10.setPadding(5, 5, 5, 5);
		tv19.setText(nhanvien.getNgayLam());
		row.addView(tv19);

		TextView tv20 = new TextView(this);
		LayoutParams lo20 = new LayoutParams(150, 50);
		lo20.setMargins(0, 0, 2, 0);
		tv20.setLayoutParams(lo20);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv20.setGravity(Gravity.CENTER);
		tv20.setBackgroundResource(R.drawable.cell_shape);
		// tv10.setPadding(5, 5, 5, 5);
		tv20.setText(nhanvien.getNgayNghi());
		row.addView(tv20);

		TextView tv21 = new TextView(this);
		LayoutParams lo21 = new LayoutParams(150, 50);
		lo21.setMargins(0, 0, 2, 0);
		tv21.setLayoutParams(lo21);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv21.setGravity(Gravity.CENTER);
		tv21.setBackgroundResource(R.drawable.cell_shape);
		// tv10.setPadding(5, 5, 5, 5);
		tv21.setText(nhanvien.getLoaiHopDong());
		row.addView(tv21);

		TextView tv22 = new TextView(this);
		LayoutParams lo22 = new LayoutParams(150, 50);
		lo22.setMargins(0, 0, 2, 0);
		tv22.setLayoutParams(lo22);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv22.setGravity(Gravity.CENTER);
		tv22.setBackgroundResource(R.drawable.cell_shape);
		// tv10.setPadding(5, 5, 5, 5);
		tv22.setText(nhanvien.getLanTruyCapCuoi());
		row.addView(tv22);

		TextView tv23 = new TextView(this);
		LayoutParams lo23 = new LayoutParams(200, 50);
		lo23.setMargins(0, 0, 2, 0);
		tv23.setLayoutParams(lo23);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv23.setGravity(Gravity.CENTER);
		tv23.setBackgroundResource(R.drawable.cell_shape);
		// tv10.setPadding(5, 5, 5, 5);
		tv23.setText(nhanvien.getGhiChu());
		row.addView(tv23);

		table.addView(row);
	}
}
