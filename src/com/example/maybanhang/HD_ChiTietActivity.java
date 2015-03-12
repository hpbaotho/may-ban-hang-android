package com.example.maybanhang;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TableRow.LayoutParams;

public class HD_ChiTietActivity extends Activity {

	private TableLayout table;
	private TextView tv_tongtienHD;
	private Button btn_thanhtoanHD, btn_xoaDvHd, btn_suaDvHd, btn_chuyenDvHd,
			btn_khuyenmaiDvHd, btn_chuyenHD;
	private String giamgia_arr[] = { "0%", "5%", "10%", "20%" };
	private Spinner sp_giamgia;
	private String tdpv = "";
	private String dichvu_chon, hd_giamgia;
	private int soLuong = 0;
	private int tongTien_codinh = 0, tongTien_giamgia = 0;
	private DV_DichVu dv_selected;
	private int row_selected = -1;
	private int update_soluong = 0, update_khuyenmai = 1;
	private HD_HoaDon hoadon;
	private ArrayList<DV_DichVu> arr_dichvu = new ArrayList<DV_DichVu>();
	private ArrayList<DV_DichVu> arr_dichvu_afterDel = new ArrayList<DV_DichVu>();
	public static final int REQUEST_CODE_INPUT_SOLUONG = 100;
	public static final int REQUEST_CODE_INPUT_KHUYENMAI = 101;
	public static final int REQUEST_CODE_INPUT_CHUYENDV = 102;
	public static final int REQUEST_CODE_INPUT_CHUYENHD = 103;

	private DatabaseHandler database = new DatabaseHandler(this);
	DecimalFormat formatMoney = new DecimalFormat("#,###,##0");
	
	public static int isCauhinh = 0;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hd_hoadon_chitiet);

		table = (TableLayout) findViewById(R.id.tableLayout);
		btn_thanhtoanHD = (Button) findViewById(R.id.btn_thanhtoanHd);
		btn_xoaDvHd = (Button) findViewById(R.id.btn_xoaDvHd);
		btn_suaDvHd = (Button) findViewById(R.id.btn_suaDVHd);
		btn_chuyenDvHd = (Button) findViewById(R.id.btn_chuyenDvHd);
		btn_khuyenmaiDvHd = (Button) findViewById(R.id.btn_khuyenmaiDvHd);
		btn_chuyenHD = (Button)findViewById(R.id.btn_chuyenHD);
		tv_tongtienHD = (TextView) findViewById(R.id.tv_tongtienHD);

		btn_thanhtoanHD.setOnClickListener(new MyEvent());
		btn_xoaDvHd.setOnClickListener(new MyEvent());
		btn_suaDvHd.setOnClickListener(new MyEvent());
		btn_chuyenDvHd.setOnClickListener(new MyEvent());
		btn_khuyenmaiDvHd.setOnClickListener(new MyEvent());
		btn_chuyenHD.setOnClickListener(new MyEvent());

		buidTableTitle();
		hoadon = database.getHoaDon(HD_MainActivity.hd_id);

		sp_giamgia = (Spinner) findViewById(R.id.sp_dieuchinh);
		ArrayAdapter<String> giamgia_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, giamgia_arr);
		giamgia_adt
				.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		sp_giamgia.setAdapter(giamgia_adt);

		arr_dichvu = database.getDVByHD(HD_MainActivity.hd_id + "");
//		database.deleteAllDV();
		for (int i = 0; i < arr_dichvu.size(); i++) {
			try {
				buildTableData(arr_dichvu.get(i));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			tongTien_codinh += Integer.parseInt(arr_dichvu.get(i).getSoLuong())
					* Integer.parseInt(arr_dichvu.get(i).getDonGiaBan()) * (100 - Integer.parseInt(arr_dichvu.get(i).getFtGiaGia())) / 100;
		}
		tongTien_giamgia = tongTien_codinh;
		hd_giamgia = hoadon.getFtDieuChinh();
		for (int i = 0; i < giamgia_arr.length; i++) {
			if(giamgia_arr[i].equals(hd_giamgia)){
				sp_giamgia.setSelection(i);
				break;
			}				
		}
				
		sp_giamgia.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String giamgia = sp_giamgia.getItemAtPosition(position).toString();
				String giamgia_arr[] = giamgia.split("%");
				tongTien_giamgia = tongTien_codinh - tongTien_codinh*Integer.parseInt(giamgia_arr[0])/100;
				if(!giamgia.equals(hd_giamgia)){
					hoadon.setTongTienPhaiThu(tongTien_giamgia + "");
					hoadon.setFtDieuChinh(sp_giamgia.getItemAtPosition(position).toString());
					database.updateHoaDon(hoadon);
				}
				tv_tongtienHD.setText(formatMoney.format(tongTien_giamgia));				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private class MyEvent implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_thanhtoanHd:
				if (!database.getLastCTNByUser(MainActivity.username).getTdKetThuc().isEmpty()) {

					AlertDialog.Builder b = new AlertDialog.Builder(
							HD_ChiTietActivity.this);
					b.setTitle("Thông báo");
					b.setMessage("Bạn không làm ca hiện tại, không thể thanh toán HĐ này!");
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
					AlertDialog.Builder b = new AlertDialog.Builder(
							HD_ChiTietActivity.this);
					b.setTitle("Thông báo");
					b.setMessage("Bạn có chắc chắn muốn thanh toán hóa đơn?");
					b.setPositiveButton("Đồng ý",
							new DialogInterface.OnClickListener() {
	
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
	//								database.deleteHoaDon(HD_MainActivity.hd_id);
									HD_HoaDon hoadon = database.getHoaDon(HD_MainActivity.hd_id);
									hoadon.setTrangThai("DaTT");
									database.updateHoaDon(hoadon);
									
									if(isCauhinh == 1)
									{
										Intent myIntent = new Intent(HD_ChiTietActivity.this, Printer_Handle.class);
										Bundle bd = new Bundle();
										bd.putString("action", "Print");
										bd.putInt("id", HD_MainActivity.hd_id);
										bd.putString("khuvuc", HD_MainActivity.khuvuc);
										bd.putString("tdbd", HD_MainActivity.tdBD);
										bd.putString("hoadon", 
												"Thao Trang Spa            NVPV: " + MainActivity.username + "\n"
							                    +"So 20, Cau Giay, Ha Noi   NVTN: " + MainActivity.username + "\n"
							                    +"Tel: 01688975245          http://thaotrangspa.vn\n"
							                    +"thaotrangspa@gmail.com    Ma HD: " + HD_MainActivity.hd_id +"\n"
							                    +"------------------------------------------------\n\n"
							                    +"              HOA DON "+ HD_MainActivity.khuvuc.toUpperCase() + "\n"
							                    +"Ma the: KL\n"
							                    +"Ten chu the: Khach le\n\n"
							                    +"Gio bat dau:  22:38:09 08/Sep/2014\n"
							                    +"Gio ket thuc: 21:31:42 08/Sep/2014\n\n"
							                    +"       DANH SACH DICH VU SU DUNG\n\n"
							                    +"Ten DV       SL      D.Gia    KM(%)    T.Tien\n"
							                    +"------------------------------------------------\n"
							                    +"Bot cung     1       20.000            20.000\n"
							                    +"Cat toc      1       30.000            30.000\n"
							                    +"Goi dau      1       50.000            50.000\n"
							                    +"------------------------------------------------\n"
							                    +"                       Tong tien:     100.000\n"
							                    +"                       Chiet khau(%): 0\n"
							                    +"			THANH TIEN: 100.000vnd\n"
							                    +"			Mot tram ngan dong\n\n"
							                    +"Ghi chu: Don gia da bao gom VAT\n\n"
							                    +"********Han hanh phuc vu quy khach!*********");
										
										myIntent.putExtra("data", bd);
										startActivity(myIntent);
										finish();
									}
									
									
//									Intent intent = new Intent(
//											HD_ChiTietActivity.this,
//											MainActivity.class);
//									startActivity(intent);
//									finish();
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
				break;
			case R.id.btn_chuyenHD:
				Intent intent = new Intent(HD_ChiTietActivity.this, HD_ChuyenHD.class);
				startActivityForResult(intent, REQUEST_CODE_INPUT_CHUYENHD);
				break;
			case R.id.btn_xoaDvHd:
				if (row_selected != -1) {
					AlertDialog.Builder b1 = new AlertDialog.Builder(
							HD_ChiTietActivity.this);
					b1.setTitle("Thông báo");
					b1.setMessage("Bạn có chắc chắn muốn xóa dịch vụ đã chọn?");
					b1.setPositiveButton("Đồng ý",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									database.deleteDVbyMaDV(dv_selected.getMaDV());
									arr_dichvu_afterDel = database.getDVByHD(HD_MainActivity.hd_id + "");
									table.removeAllViews();
									buidTableTitle();
									tongTien_codinh = 0;
									for (int i = 0; i < arr_dichvu_afterDel.size(); i++)
									{
										buildTableData(arr_dichvu_afterDel.get(i));										
										tongTien_codinh += Integer.parseInt(arr_dichvu_afterDel.get(i)
												.getSoLuong())
												* Integer.parseInt(arr_dichvu_afterDel.get(i)
														.getDonGiaBan());
										tongTien_giamgia = tongTien_codinh;
									}
									tv_tongtienHD.setText(formatMoney.format(tongTien_giamgia));
									Toast toast = Toast.makeText(
											HD_ChiTietActivity.this,
											"Đã xóa dịch vụ!",
											Toast.LENGTH_SHORT);
									toast.show();
									row_selected = -1;
								}
							});
					b1.setNegativeButton("Thoát",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									dialog.cancel();
								}
							});
					b1.create().show();
				}
				else
				{
//						AlertDialog.Builder b2 = new AlertDialog.Builder(
//								HD_ChiTietActivity.this);
//						b2.setTitle("Thông báo");
//						b2.setMessage("Vui lòng chọn một dịch vụ để thực hiện chức năng này!");
//						b2.setPositiveButton("Đồng ý",
//								new DialogInterface.OnClickListener() {
//
//									@Override
//									public void onClick(DialogInterface dialog,
//											int which) {
//										// TODO Auto-generated method stub
//										dialog.cancel();
//									}
//								});
//						b2.create().show();
					Toast.makeText(getApplicationContext(), "Chọn một dịch vụ để thực hiện chức năng này!", Toast.LENGTH_LONG).show();
				}
				break;
			case R.id.btn_suaDVHd:
				if(row_selected != -1)
				{
					Intent intent1 = new Intent(HD_ChiTietActivity.this, HD_Update_SL_KM.class);
					Bundle bundle1 = new Bundle();
					bundle1.putInt("type", update_soluong);
					intent1.putExtra("dichvu", dv_selected);
					intent1.putExtra("data", bundle1);
					startActivityForResult(intent1, REQUEST_CODE_INPUT_SOLUONG);
				}
				else
				{
//					AlertDialog.Builder b2 = new AlertDialog.Builder(
//							HD_ChiTietActivity.this);
//					b2.setTitle("Thông báo");
//					b2.setMessage("Vui lòng chọn một dịch vụ để thực hiện chức năng này!");
//					b2.setPositiveButton("Đồng ý",
//							new DialogInterface.OnClickListener() {
//
//								@Override
//								public void onClick(DialogInterface dialog,
//										int which) {
//									// TODO Auto-generated method stub
//									dialog.cancel();
//								}
//							});
//					b2.create().show();
					Toast.makeText(getApplicationContext(), "Chọn một dịch vụ để thực hiện chức năng này!", Toast.LENGTH_LONG).show();
				}
				break;
			case R.id.btn_chuyenDvHd:
				if(row_selected != -1){
					Intent intent3 = new Intent(HD_ChiTietActivity.this, HD_ChuyenDV.class);
					Bundle bundle3 = new Bundle();
					intent3.putExtra("dichvu", dv_selected);
					intent3.putExtra("data", bundle3);
					startActivityForResult(intent3, REQUEST_CODE_INPUT_CHUYENDV);
				}
				else{
					Toast.makeText(getApplicationContext(), "Chọn một dịch vụ để thực hiện chức năng này!", Toast.LENGTH_LONG).show();
				}
				break;
			case R.id.btn_khuyenmaiDvHd:
				if(row_selected != -1)
				{
					Intent intent2 = new Intent(HD_ChiTietActivity.this, HD_Update_SL_KM.class);
					Bundle bundle2 = new Bundle();
					bundle2.putInt("type", update_khuyenmai);
					intent2.putExtra("dichvu", dv_selected);
					intent2.putExtra("data", bundle2);
					startActivityForResult(intent2, REQUEST_CODE_INPUT_KHUYENMAI);
				}
				else
				{
//					AlertDialog.Builder b2 = new AlertDialog.Builder(
//							HD_ChiTietActivity.this);
//					b2.setTitle("Thông báo");
//					b2.setMessage("Vui lòng chọn một dịch vụ để thực hiện chức năng này!");
//					b2.setPositiveButton("Đồng ý",
//							new DialogInterface.OnClickListener() {
//
//								@Override
//								public void onClick(DialogInterface dialog,
//										int which) {
//									// TODO Auto-generated method stub
//									dialog.cancel();
//								}
//							});
//					b2.create().show();
					Toast.makeText(getApplicationContext(), "Chọn một dịch vụ để thực hiện chức năng này!", Toast.LENGTH_LONG).show();
				}
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
		 case HD_Update_SL_KM.FINISH_CODE_UPDATE_SOLUONG:
		 case HD_Update_SL_KM.FINISH_CODE_UPDATE_KHUYENMAI:
		 case HD_ChuyenDV.FINISH_CODE_UPDATE_CHUYENDV:
			table.removeAllViews();
			buidTableTitle();
		 	arr_dichvu = database.getDVByHD(HD_MainActivity.hd_id + "");
		 	tongTien_codinh = 0;
		 	for (int i = 0; i < arr_dichvu.size(); i++) {
		 		buildTableData(arr_dichvu.get(i));
		 		tongTien_codinh += Integer.parseInt(arr_dichvu.get(i).getSoLuong())
					* Integer.parseInt(arr_dichvu.get(i).getDonGiaBan()) * (100 - Integer.parseInt(arr_dichvu.get(i).getFtGiaGia())) / 100;
		 		tongTien_giamgia = tongTien_codinh;
		 	}
		 	tv_tongtienHD.setText(formatMoney.format(tongTien_giamgia));
		 	break;
		 case HD_ChuyenHD.FINISH_CODE_UPDATE_CHUYENHD:
			 HD_MainActivity.edt_khuvucHD.setText(database.getHoaDon(HD_MainActivity.hd_id).getKhuVuc());
			 HD_MainActivity.tv_khuvucHD.setText(database.getHoaDon(HD_MainActivity.hd_id).getKhuVuc());
			 break;
		 default:
			 break;
		}
	}

	private void buidTableTitle() {
		TableRow row = new TableRow(this);
		row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		row.setBackgroundColor(Color.BLACK);

//		TextView tv1 = new TextView(this);
//		LayoutParams lo1 = new LayoutParams(150, 40);
//		lo1.setMargins(0, 0, 2, 0);
//		tv1.setLayoutParams(lo1);
//		// tv1.setBackgroundResource(R.drawable.cell_shape);
//		tv1.setGravity(Gravity.CENTER);
//		tv1.setText("Mã DV");
//		tv1.setBackgroundColor(Color.GRAY);
//		row.addView(tv1);

		TextView tv2 = new TextView(this);
		LayoutParams lo2 = new LayoutParams(250, 40);
		lo2.setMargins(0, 0, 2, 0);
		tv2.setLayoutParams(lo2);
		// tv2.setBackgroundResource(R.drawable.cell_shape);
		tv2.setGravity(Gravity.CENTER);
		tv2.setText("Tên Dịch Vụ");
		tv2.setBackgroundColor(Color.GRAY);
		row.addView(tv2);

		TextView tv3 = new TextView(this);
		LayoutParams lo3 = new LayoutParams(100, 40);
		lo3.setMargins(0, 0, 2, 0);
		tv3.setLayoutParams(lo3);
		// tv3.setBackgroundResource(R.drawable.cell_shape);
		tv3.setGravity(Gravity.CENTER);
		tv3.setText("Số Lượng");
		tv3.setBackgroundColor(Color.GRAY);
		row.addView(tv3);

		TextView tv4 = new TextView(this);
		LayoutParams lo4 = new LayoutParams(150, 40);
		lo4.setMargins(0, 0, 2, 0);
		tv4.setLayoutParams(lo4);
		// tv4.setBackgroundResource(R.drawable.cell_shape);
		tv4.setGravity(Gravity.CENTER);
		tv4.setText("Đơn Giá");
		tv4.setBackgroundColor(Color.GRAY);
		row.addView(tv4);

		TextView tv5 = new TextView(this);
		LayoutParams lo5 = new LayoutParams(100, 40);
		lo5.setMargins(0, 0, 2, 0);
		tv5.setLayoutParams(lo5);
		// tv5.setBackgroundResource(R.drawable.cell_shape);
		tv5.setGravity(Gravity.CENTER);
		tv5.setText("K.Mại(%)");
		tv5.setBackgroundColor(Color.GRAY);
		row.addView(tv5);

		TextView tv6 = new TextView(this);
		LayoutParams lo6 = new LayoutParams(100, 40);
		lo6.setMargins(0, 0, 2, 0);
		tv6.setLayoutParams(lo6);
		// tv6.setBackgroundResource(R.drawable.cell_shape);
		tv6.setGravity(Gravity.CENTER);
		tv6.setText("ĐVT");
		tv6.setBackgroundColor(Color.GRAY);
		row.addView(tv6);

		TextView tv7 = new TextView(this);
		LayoutParams lo7 = new LayoutParams(150, 40);
		lo7.setMargins(0, 0, 2, 0);
		tv7.setLayoutParams(lo7);
		// tv7.setBackgroundResource(R.drawable.cell_shape);
		tv7.setGravity(Gravity.CENTER);
		tv7.setText("Tổng Tiền");
		tv7.setBackgroundColor(Color.GRAY);
		row.addView(tv7);

		TextView tv8 = new TextView(this);
		LayoutParams lo8 = new LayoutParams(200, 40);
		lo8.setMargins(0, 0, 2, 0);
		tv8.setLayoutParams(lo8);
		// tv8.setBackgroundResource(R.drawable.cell_shape);
		tv8.setGravity(Gravity.CENTER);
		tv8.setText("NVPV");
		tv8.setBackgroundColor(Color.GRAY);
		row.addView(tv8);

		TextView tv9 = new TextView(this);
		LayoutParams lo9 = new LayoutParams(200, 40);
		lo9.setMargins(0, 0, 2, 0);
		tv9.setLayoutParams(lo9);
		// tv9.setBackgroundResource(R.drawable.cell_shape);
		tv9.setGravity(Gravity.CENTER);
		tv9.setText("NVCB");
		tv9.setBackgroundColor(Color.GRAY);
		row.addView(tv9);

				
		TextView tv11 = new TextView(this);
		LayoutParams lo11 = new LayoutParams(150, 40);
		lo11.setMargins(0, 0, 2, 0);
		tv11.setLayoutParams(lo11);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv11.setGravity(Gravity.CENTER);
		tv11.setText("Trạng Thái");
		tv11.setBackgroundColor(Color.GRAY);
		row.addView(tv11);
		
		TextView tv10 = new TextView(this);
		LayoutParams lo10 = new LayoutParams(200, 40);
		// lo10.setMargins(0, 0, 2, 0);
		tv10.setLayoutParams(lo10);
		// tv10.setBackgroundResource(R.drawable.cell_shape);
		tv10.setGravity(Gravity.CENTER);
		tv10.setText("T.Điểm PV");
		tv10.setBackgroundColor(Color.GRAY);
		row.addView(tv10);

		table.addView(row);
	}

	private void buildTableData(DV_DichVu dichvu) {

		final TableRow row = new TableRow(this);
		row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		// registerForContextMenu(row);

		row.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				for (int i = 1; i < table.getChildCount(); i++) {
					TableRow item = (TableRow) table.getChildAt(i);
					item.setBackgroundResource(R.drawable.tr_select);
				}
				row.setBackgroundResource(R.drawable.tr_selected);
				row_selected = table.indexOfChild(row);
				dv_selected = arr_dichvu.get(row_selected - 1);
			}
		});
		try {
//			TextView tv1 = new TextView(this);
//			LayoutParams lo1 = new LayoutParams(150, 50);
//			lo1.setMargins(0, 0, 2, 0);
//			tv1.setLayoutParams(lo1);
//			tv1.setGravity(Gravity.CENTER);
//			tv1.setTypeface(null, Typeface.BOLD);
//			tv1.setBackgroundResource(R.drawable.cell_shape);
//			// tv1.setClickable(true);
//			// tv1.setBackgroundResource(R.drawable.table_row_selector);
//			// tv1.setPadding(5, 5, 5, 5);
//			tv1.setText(dichvu.getMaDV());
//			row.addView(tv1);

			TextView tv2 = new TextView(this);
			LayoutParams lo2 = new LayoutParams(250, 50);
			lo2.setMargins(0, 0, 2, 0);
			tv2.setLayoutParams(lo2);
			tv2.setGravity(Gravity.CENTER);
			tv2.setBackgroundResource(R.drawable.cell_shape);
			// tv2.setPadding(5, 5, 5, 5);
			tv2.setText(dichvu.getTenDV());
			row.addView(tv2);

			TextView tv3 = new TextView(this);
			LayoutParams lo3 = new LayoutParams(100, 50);
			lo3.setMargins(0, 0, 2, 0);
			tv3.setLayoutParams(lo3);
			// tv3.setBackgroundResource(R.drawable.cell_shape);
			tv3.setGravity(Gravity.CENTER);
			tv3.setBackgroundResource(R.drawable.cell_shape);
			// tv3.setPadding(5, 5, 5, 5);
			tv3.setText(dichvu.getSoLuong());
			tv3.setTypeface(null, Typeface.BOLD);
			row.addView(tv3);

			TextView tv4 = new TextView(this);
			LayoutParams lo4 = new LayoutParams(150, 50);
			lo4.setMargins(0, 0, 2, 0);
			tv4.setLayoutParams(lo4);
			// tv4.setBackgroundResource(R.drawable.cell_shape);
			tv4.setGravity(Gravity.CENTER);
			tv4.setBackgroundResource(R.drawable.cell_shape);
			// tv4.setPadding(5, 5, 5, 5);
			tv4.setText(dichvu.getDonGiaBan());
			row.addView(tv4);

			TextView tv5 = new TextView(this);
			LayoutParams lo5 = new LayoutParams(100, 50);
			lo5.setMargins(0, 0, 2, 0);
			tv5.setLayoutParams(lo5);
			// tv5.setBackgroundResource(R.drawable.cell_shape);
			tv5.setGravity(Gravity.CENTER);
			tv5.setBackgroundResource(R.drawable.cell_shape);
			// tv5.setPadding(5, 5, 5, 5);
			tv5.setText(dichvu.getFtGiaGia());
			row.addView(tv5);

			TextView tv6 = new TextView(this);
			LayoutParams lo6 = new LayoutParams(150, 50);
			lo6.setMargins(0, 0, 2, 0);
			tv6.setLayoutParams(lo6);
			// tv6.setBackgroundResource(R.drawable.cell_shape);
			tv6.setGravity(Gravity.CENTER);
			tv6.setBackgroundResource(R.drawable.cell_shape);
			// tv6.setPadding(5, 5, 5, 5);
			tv6.setText(dichvu.getDonViTinh());
			row.addView(tv6);

			TextView tv7 = new TextView(this);
			LayoutParams lo7 = new LayoutParams(150, 50);
			lo7.setMargins(0, 0, 2, 0);
			tv7.setLayoutParams(lo7);
			// tv7.setBackgroundResource(R.drawable.cell_shape);
			tv7.setGravity(Gravity.CENTER);
			tv7.setBackgroundResource(R.drawable.cell_shape);
			// tv7.setPadding(5, 5, 5, 5);
			//String giamgia_item[] = sp_giamgia.getItemAtPosition(position).toString().split("%");
			//tongTien_giamgia = tongTien_codinh - tongTien_codinh*Integer.parseInt(giamgia[0])/100;
			int tongTien_item = Integer.parseInt(dichvu.getSoLuong()) * Integer
					.parseInt(dichvu.getDonGiaBan()) * (100 - Integer.parseInt(dichvu.getFtGiaGia())) / 100;
			tv7.setText(formatMoney.format(tongTien_item));
			tv7.setTypeface(null, Typeface.BOLD);
			row.addView(tv7);

			TextView tv8 = new TextView(this);
			LayoutParams lo8 = new LayoutParams(200, 50);
			lo8.setMargins(0, 0, 2, 0);
			tv8.setLayoutParams(lo8);
			// tv8.setBackgroundResource(R.drawable.cell_shape);
			tv8.setGravity(Gravity.CENTER);
			tv8.setBackgroundResource(R.drawable.cell_shape);
			// tv8.setPadding(5, 5, 5, 5);
			tv8.setText(dichvu.getMaNVPV());
			row.addView(tv8);

			TextView tv9 = new TextView(this);
			LayoutParams lo9 = new LayoutParams(200, 50);
			lo9.setMargins(0, 0, 2, 0);
			tv9.setLayoutParams(lo9);
			// tv9.setBackgroundResource(R.drawable.cell_shape);
			tv9.setGravity(Gravity.CENTER);
			tv9.setBackgroundResource(R.drawable.cell_shape);
			// tv9.setPadding(5, 5, 5, 5);
			tv9.setText("");
			row.addView(tv9);

			TextView tv11 = new TextView(this);
			LayoutParams lo11 = new LayoutParams(150, 50);
			lo11.setMargins(0, 0, 2, 0);
			tv11.setLayoutParams(lo11);
			// tv10.setBackgroundResource(R.drawable.cell_shape);
			tv11.setGravity(Gravity.CENTER);
			tv11.setBackgroundResource(R.drawable.cell_shape);
			// tv10.setPadding(5, 5, 5, 5);
			tv11.setText(dichvu.getTrangThai());
			row.addView(tv11);
			
			TextView tv10 = new TextView(this);
			LayoutParams lo10 = new LayoutParams(230, 50);
			// lo10.setMargins(0, 0, 2, 0);
			tv10.setLayoutParams(lo10);
			// tv10.setBackgroundResource(R.drawable.cell_shape);
			tv10.setGravity(Gravity.CENTER);
			tv10.setBackgroundResource(R.drawable.cell_shape);
			// tv10.setPadding(5, 5, 5, 5);
			tv10.setText(dichvu.getThoiDiemPV());
			row.addView(tv10);

			
			table.addView(row);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
