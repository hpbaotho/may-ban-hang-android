package com.example.maybanhang;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.zj.usbsdk.UsbController;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
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
	
	public static String hoadonInfo;
	public static int isCauhinh = 0;
	
	private int[][] u_infor;
    UsbController  usbCtrl = null;
    UsbDevice dev = null;

	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hd_hoadon_chitiet);
		
		usbCtrl = new UsbController(this,mHandler);
		u_infor = new int[4][2];
		u_infor[0][0] = 0x1CBE;
		u_infor[0][1] = 0x0003;
		u_infor[1][0] = 0x1CB0;
		u_infor[1][1] = 0x0003;
		u_infor[2][0] = 0x0483;
		u_infor[2][1] = 0x5740;
		//u_infor[3][0] = 0x0493;
		//u_infor[3][1] = 0x8760;
		u_infor[3][0] = 0x0416;
		u_infor[3][1] = 0x5011;
		
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
	
	/*------------------PRINTER----------------------*/
	public boolean CheckUsbPermission(){
		if( dev != null ){
			if( usbCtrl.isHasPermission(dev)){
				return true;
			}
		}
//		Toast.makeText(getApplicationContext(), "USB device can not access, please re-connect",
//              Toast.LENGTH_SHORT).show();
  	return false;
	}
	
	  private final  Handler mHandler = new Handler() {
			@Override
	        public void handleMessage(Message msg) {
	            switch (msg.what) {
	            case UsbController.USB_CONNECTED:
//	            	Toast.makeText(getApplicationContext(), "Obtaining USB device access permissions success",
//	                        Toast.LENGTH_SHORT).show();
	            	break;
	            default:
	            	break;
	            }
	        }
	    };
	    
	    public void connectPrinter(){
			//usbCtrl.close();
			int  i = 0;
			for( i = 0 ; i < 4 ; i++ ){
				dev = usbCtrl.getDev(u_infor[i][0],u_infor[i][1]);
				if(dev != null)
					break;
			}
			
			if( dev != null ){
				if( !(usbCtrl.isHasPermission(dev))){;
					usbCtrl.getPermission(dev);
				}else{
//	            	Toast.makeText(this, "Obtaining USB device access permissions success",
//	                        Toast.LENGTH_SHORT).show();
				}
			}
	 }
	    /*------------------PRINTER----------------------*/
	    
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
					b.setMessage("Bạn có chắc chắn muốn thanh toán hóa đơn?\n");
					b.setPositiveButton("Đồng ý",
							new DialogInterface.OnClickListener() {
	
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
	//								database.deleteHoaDon(HD_MainActivity.hd_id);
									SimpleDateFormat sdfw = new SimpleDateFormat("dd/MM/yyyy   HH:mm a");
									HD_HoaDon hoadon = database.getHoaDon(HD_MainActivity.hd_id);
									hoadon.setTrangThai("DaTT");
									hoadon.setTdKetThuc(sdfw.format(MainActivity.calendar.getTime()));
									database.updateHoaDon(hoadon);
									CN_ChiNhanh chinhanhs = database.getCNbyName(Main_KhuVucCoDinhActivity.chiNhanh);
									
									if(isCauhinh == 0)
									{
										byte isHasPaper;
										connectPrinter();
										isHasPaper = usbCtrl.revByte(dev);
										byte[] cmd = new byte[4];
										if( isHasPaper == 0x38 ){
											Toast.makeText(getApplicationContext(), "The printer has no paper",
							                     Toast.LENGTH_SHORT).show();
											return;
										}
										String listDV = "";
										String temp = "                                                          ";
										for (int i = 0; i < arr_dichvu.size(); i++) {
											listDV = listDV + "\n"
													+ arr_dichvu.get(i).getTenDV() + temp.substring(0, 20 - arr_dichvu.get(i).getTenDV().length())
													+ arr_dichvu.get(i).getSoLuong() + temp.substring(0, 3 - arr_dichvu.get(i).getSoLuong().length())
													+ formatMoney.format(Integer.parseInt(arr_dichvu.get(i).getDonGiaBan())) + temp.substring(0, 9 - arr_dichvu.get(i).getDonGiaBan().length())
													+ arr_dichvu.get(i).getFtGiaGia() + temp.substring(0, 6 - arr_dichvu.get(i).getFtGiaGia().length())
													+ formatMoney.format(Integer.parseInt(arr_dichvu.get(i).getSoLuong())*Integer.parseInt(arr_dichvu.get(i).getDonGiaBan()));
										}
										
										String ttHD_Header =
												chinhanhs.getTenCN() + temp.substring(0, 32 - chinhanhs.getTenCN().length())
												+"NVPV: " + MainActivity.username + "\n"
							                    +chinhanhs.getDiaChi() + temp.substring(0, 32 - chinhanhs.getDiaChi().length())
							                    +"NVTN: " + MainActivity.username + "\n"
							                    +"Tel: " + chinhanhs.getSoDT() + temp.substring(0, 27 - chinhanhs.getSoDT().length()) 
							                    +"Ma HD: " + HD_MainActivity.hd_id +"\n"
							                    +"Web: " + chinhanhs.getThongTinKhac() + "\n"
							                    +"Email: thaotrangspa@gmail.com\n"       
							                    +"------------------------------------------------\n";
										String ttHD_Title = "              HOA DON "+ HD_MainActivity.khuvuc.toUpperCase() + "\n";
							            String ttHD_Detail = "Ma the: KL\n"
							                    +"Ten chu the: Khach le\n\n"
							                    +"Gio bat dau: " + hoadon.getTdBatDau() + "\n"
							                    +"Gio ket thuc: " + hoadon.getTdKetThuc() + "\n\n"
							                    +"       DANH SACH DICH VU SU DUNG\n\n"
							                    +"Ten DV              SL D.Gia     KM(%) T.Tien\n"
							                    +"------------------------------------------------"
							                    + listDV
							                    +"\n------------------------------------------------\n"
							                    +"                       Tong tien:     " + formatMoney.format(tongTien_codinh) + "\n"
							                    +"                       Chiet khau:    " + hoadon.getFtDieuChinh()+ "\n";
							            String ttHD_Sum = "			Thanh tien: " + formatMoney.format(tongTien_giamgia) + "\n";
							            String ttHD_SumText = "			" + convertNumberToString(tongTien_giamgia) + "\n\n";
							            String ttHD_Footer = "Ghi chu: Don gia da bao gom VAT\n\n"
							                    +"********Han hanh phuc vu quy khach!*********";
											if( CheckUsbPermission() == true ){
									         	cmd[0]=0x1B;
									            cmd[1]=0x42;
									            cmd[2]=0x04;
									            cmd[3]=0x01;                          
									            usbCtrl.sendByte(cmd, dev);	
									             
									         	usbCtrl.sendMsg(ttHD_Header, "GBK", dev);
									         	
									         	cmd[0]=0x1b;
								                cmd[1]=0x21;
								                cmd[2]=0x04;
								                cmd[3]=0x01;  
									         	cmd[2] |= 0x10;
							            		usbCtrl.sendByte(cmd, dev);  
							            		usbCtrl.sendMsg(ttHD_Title, "GBK", dev);
							            		cmd[2] &= 0xEF;        
							            		usbCtrl.sendByte(cmd, dev);
							            		
							            		usbCtrl.sendMsg(ttHD_Detail, "GBK", dev);
							            		
							            		cmd[0]=0x1b;
								                cmd[1]=0x21;
								                cmd[2]=0x04;
								                cmd[3]=0x01;  
									         	cmd[2] |= 0x10;
							            		usbCtrl.sendByte(cmd, dev);  
							            		usbCtrl.sendMsg(ttHD_Sum, "GBK", dev);
							            		cmd[2] &= 0xEF;        
							            		usbCtrl.sendByte(cmd, dev);
							            		
							            		usbCtrl.sendMsg(ttHD_SumText, "GBK", dev);
							            		usbCtrl.sendMsg(ttHD_Footer, "GBK", dev);
							            		
									         	cmd[0]=0x1D;
												cmd[1]=0x56;
												cmd[2]=0x42;
												cmd[3]=90;                        
												usbCtrl.sendByte(cmd, dev);  
									         }
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
	
	private String convertNumberToString(int number)
	{
		String text = "";
		String numb = number + "";
		ArrayList<String> arr_text = new ArrayList<String>();
		for (int i = 0; i < numb.length() - 2; i++) {
			switch (Integer.parseInt(numb.substring(i, i+1))) {
			case 0:
				arr_text.add("muoi");
				break;
			case 1:
				arr_text.add("mot");
				break;
			case 2:
				arr_text.add("hai");
				break;
			case 3:
				arr_text.add("ba");
				break;
			case 4:
				arr_text.add("bon");
				break;
			case 5:
				arr_text.add("nam");
				break;
			case 6:
				arr_text.add("sau");
				break;
			case 7:
				arr_text.add("bay");
				break;
			case 8:
				arr_text.add("tam");
				break;
			case 9:
				arr_text.add("chin");
				break;
			default:
				break;
			}
		}
		if(numb.length() == 8)
		{
//			10....
			if(arr_text.get(0).contains("mot") && arr_text.get(1).contains("muoi"))
			{
				text = "muoi trieu ";
				if(arr_text.get(2).contains("muoi") && arr_text.get(3).contains("muoi")	&& arr_text.get(4).contains("muoi"))
					text = "muoi trieu dong chan";
				
				else if(arr_text.get(3).contains("muoi")	&& arr_text.get(4).contains("muoi"))
					text = text + arr_text.get(2) + " tram ngan dong";
				
				else if(arr_text.get(2).contains("muoi") && arr_text.get(4).contains("muoi"))
					text = text + "khong tram " + arr_text.get(3) + " muoi ngan dong";
				
				else if(arr_text.get(2).contains("muoi") && arr_text.get(3).contains("muoi"))
					text = text + "khong tram linh " + arr_text.get(2) + " ngan dong";
				
				else if(arr_text.get(4).contains("muoi"))
					text = text + arr_text.get(2) + " tram " + arr_text.get(3) + " muoi ngan dong";
				
				else if(arr_text.get(2).contains("muoi"))
					text = text + "khong tram " + arr_text.get(3) + " muoi " + arr_text.get(4) + " ngan dong";
				
				else if(arr_text.get(3).contains("muoi"))
					text = text + arr_text.get(2) + " linh " + arr_text.get(4) + " ngan dong";
				
				else if(arr_text.get(3).contains("mot"))
					text = text + arr_text.get(2) + " tram  muoi " + arr_text.get(4) + " ngan dong";
				
				else if(arr_text.get(4).contains("muoi"))
					text = text + arr_text.get(2) + " tram " + arr_text.get(3) + "muoi mot ngan dong";
				
				else if(arr_text.get(3).contains("muoi"))
					text = text + arr_text.get(2) + " tram " + arr_text.get(3) + " muoi " + arr_text.get(4) + " ngan dong";
				
				text = text.substring(0, 1).toUpperCase() + text.substring(1);
				return text;
			}
			
//			12...
			else if(arr_text.get(0).contains("mot") && !arr_text.get(1).contains("muoi"))
			{
				text = "muoi " + arr_text.get(1) + " trieu ";
				if(arr_text.get(2).contains("muoi") && arr_text.get(3).contains("muoi")	&& arr_text.get(4).contains("muoi"))
					text = text + " dong chan";
				
				else if(arr_text.get(3).contains("muoi") && arr_text.get(4).contains("muoi"))
					text = text + arr_text.get(2) + " tram ngan dong";
				
				else if(arr_text.get(2).contains("muoi") && arr_text.get(4).contains("muoi"))
					text = text + "khong tram " + arr_text.get(3) + " muoi ngan dong";
				
				else if(arr_text.get(2).contains("muoi") && arr_text.get(3).contains("muoi"))
					text = text + "khong tram linh " + arr_text.get(2) + " ngan dong";
				
				else if(arr_text.get(4).contains("muoi"))
					text = text + arr_text.get(2) + " tram " + arr_text.get(3) + " muoi ngan dong";
				
				else if(arr_text.get(2).contains("muoi"))
					text = text + "khong tram " + arr_text.get(3) + " muoi " + arr_text.get(4) + " ngan dong";
				
				else if(arr_text.get(3).contains("muoi"))
					text = text + arr_text.get(2) + " linh " + arr_text.get(4) + " ngan dong";
				
				else if(arr_text.get(3).contains("mot"))
					text = text + arr_text.get(2) + " tram  muoi " + arr_text.get(4) + " ngan dong";
				
				else if(arr_text.get(4).contains("muoi"))
					text = text + arr_text.get(2) + " tram " + arr_text.get(3) + "muoi mot ngan dong";
				
				else if(arr_text.get(3).contains("muoi"))
					text = text + arr_text.get(2) + " tram " + arr_text.get(3) + " muoi " + arr_text.get(4) + " ngan dong";
				
				text = text.substring(0, 1).toUpperCase() + text.substring(1);
				return text;
			}
			
//			21...
			else if(arr_text.get(1).contains("mot"))
			{
				text = arr_text.get(0) + " muoi mot trieu";
				if(arr_text.get(2).contains("muoi") && arr_text.get(3).contains("muoi")	&& arr_text.get(4).contains("muoi"))
					text = text + " dong chan";
				
				else if(arr_text.get(3).contains("muoi") && arr_text.get(4).contains("muoi"))
					text = text + arr_text.get(2) + " tram ngan dong";
				
				else if(arr_text.get(2).contains("muoi") && arr_text.get(4).contains("muoi"))
					text = text + "khong tram " + arr_text.get(3) + " muoi ngan dong";
				
				else if(arr_text.get(2).contains("muoi") && arr_text.get(3).contains("muoi"))
					text = text + "khong tram linh " + arr_text.get(2) + " ngan dong";
				
				else if(arr_text.get(4).contains("muoi"))
					text = text + arr_text.get(2) + " tram " + arr_text.get(3) + " muoi ngan dong";
				
				else if(arr_text.get(2).contains("muoi"))
					text = text + "khong tram " + arr_text.get(3) + " muoi " + arr_text.get(4) + " ngan dong";
				
				else if(arr_text.get(3).contains("muoi"))
					text = text + arr_text.get(2) + " linh " + arr_text.get(4) + " ngan dong";
				
				else if(arr_text.get(3).contains("mot"))
					text = text + arr_text.get(2) + " tram  muoi " + arr_text.get(4) + " ngan dong";
				
				else if(arr_text.get(4).contains("muoi"))
					text = text + arr_text.get(2) + " tram " + arr_text.get(3) + "muoi mot ngan dong";
				
				else if(arr_text.get(3).contains("muoi"))
					text = text + arr_text.get(2) + " tram " + arr_text.get(3) + " muoi " + arr_text.get(4) + " ngan dong";
				
				text = text.substring(0, 1).toUpperCase() + text.substring(1);
				return text;
			}
			
			else
			{
				text = arr_text.get(0) + " muoi " + arr_text.get(1) + " trieu";
				if(arr_text.get(2).contains("muoi") && arr_text.get(3).contains("muoi")	&& arr_text.get(4).contains("muoi"))
					text = text + " dong chan";
				
				else if(arr_text.get(3).contains("muoi") && arr_text.get(4).contains("muoi"))
					text = text + arr_text.get(2) + " tram ngan dong";
				
				else if(arr_text.get(2).contains("muoi") && arr_text.get(4).contains("muoi"))
					text = text + "khong tram " + arr_text.get(3) + " muoi ngan dong";
				
				else if(arr_text.get(2).contains("muoi") && arr_text.get(3).contains("muoi"))
					text = text + "khong tram linh " + arr_text.get(2) + " ngan dong";
				
				else if(arr_text.get(4).contains("muoi"))
					text = text + arr_text.get(2) + " tram " + arr_text.get(3) + " muoi ngan dong";
				
				else if(arr_text.get(2).contains("muoi"))
					text = text + "khong tram " + arr_text.get(3) + " muoi " + arr_text.get(4) + " ngan dong";
				
				else if(arr_text.get(3).contains("muoi"))
					text = text + arr_text.get(2) + " linh " + arr_text.get(4) + " ngan dong";
				
				else if(arr_text.get(3).contains("mot"))
					text = text + arr_text.get(2) + " tram  muoi " + arr_text.get(4) + " ngan dong";
				
				else if(arr_text.get(4).contains("muoi"))
					text = text + arr_text.get(2) + " tram " + arr_text.get(3) + "muoi mot ngan dong";
				
				else if(arr_text.get(3).contains("muoi"))
					text = text + arr_text.get(2) + " tram " + arr_text.get(3) + " muoi " + arr_text.get(4) + " ngan dong";
				
				text = text.substring(0, 1).toUpperCase() + text.substring(1);
				return text;
			}
		}
		
		if(numb.length() == 7)
		{
			if(arr_text.get(4).contains("muoi"))
			{
				text = arr_text.get(0) + " trieu ";
				if(arr_text.get(1).contains("muoi") && arr_text.get(2).contains("muoi")	&& arr_text.get(3).contains("muoi"))
					text = text + "dong chan";
				
				else if(arr_text.get(2).contains("muoi")	&& arr_text.get(3).contains("muoi"))
					text = text + arr_text.get(1) + " tram ngan dong";
				
				else if(arr_text.get(1).contains("muoi") && arr_text.get(3).contains("muoi"))
					text = text + "khong tram " + arr_text.get(2) + " muoi ngan dong";
				
				else if(arr_text.get(1).contains("muoi") && arr_text.get(2).contains("muoi"))
					text = text + "khong tram linh " + arr_text.get(2) + " ngan dong";
				
				else if(arr_text.get(3).contains("muoi"))
					text = text + arr_text.get(1) + " tram " + arr_text.get(2) + " muoi ngan dong";
				
				else if(arr_text.get(1).contains("muoi"))
					text = text + "khong tram " + arr_text.get(2) + " muoi " + arr_text.get(3) + " ngan dong";
				
				else if(arr_text.get(2).contains("muoi"))
					text = text + arr_text.get(1) + " linh " + arr_text.get(3) + " ngan dong";
				
				else if(arr_text.get(2).contains("mot"))
					text = text + arr_text.get(1) + " tram  muoi " + arr_text.get(3) + " ngan dong";
				
				else if(arr_text.get(3).contains("muoi"))
					text = text + arr_text.get(1) + " tram " + arr_text.get(2) + "muoi mot ngan dong";
				
				else if(arr_text.get(2).contains("muoi"))
					text = text + arr_text.get(1) + " tram " + arr_text.get(2) + " muoi " + arr_text.get(3) + " ngan dong";
				
				else
					text = text + arr_text.get(1) +  " tram " + arr_text.get(2) + " muoi " + arr_text.get(3) + " ngan dong";
			}
			else
			{
				text = arr_text.get(0) + " trieu ";
				if(arr_text.get(1).contains("muoi") && arr_text.get(2).contains("muoi")	&& arr_text.get(3).contains("muoi"))
					text = text + arr_text.get(4) + " tram dong";
				
				else if(arr_text.get(2).contains("muoi") && arr_text.get(3).contains("muoi"))
					text = text + arr_text.get(1) + " tram ngan " + arr_text.get(4) + " tram dong";
				
				else if(arr_text.get(1).contains("muoi") && arr_text.get(3).contains("muoi"))
					text = text + "khong tram " + arr_text.get(2) + " muoi ngan " + arr_text.get(4) + "tram dong";
				
				else if(arr_text.get(1).contains("muoi") && arr_text.get(2).contains("muoi"))
					text = text + "khong tram linh " + arr_text.get(2) + " ngan " +  arr_text.get(4) + " tram dong";
				
				else if(arr_text.get(3).contains("muoi"))
					text = text + arr_text.get(1) + " tram " + arr_text.get(2) + " muoi ngan " + arr_text.get(4) + " tram dong";
				
				else if(arr_text.get(1).contains("muoi"))
					text = text + "khong tram " + arr_text.get(2) + " muoi " + arr_text.get(3) + " ngan " + arr_text.get(4) + "tram dong";
				
				else if(arr_text.get(2).contains("muoi"))
					text = text + arr_text.get(1) + "tram linh " + arr_text.get(3) + " ngan " + arr_text.get(4) + " tram dong";
				
				else if(arr_text.get(2).contains("mot"))
					text = text + arr_text.get(1) + " tram  muoi " + arr_text.get(3) + " ngan " + arr_text.get(4) + " tram dong";
				
				else if(arr_text.get(3).contains("muoi"))
					text = text + arr_text.get(1) + " tram " + arr_text.get(2) + "muoi mot ngan " + arr_text.get(4) + " tram dong";
				
				else if(arr_text.get(2).contains("muoi"))
					text = text + arr_text.get(1) + " tram " + arr_text.get(2) + " muoi " + arr_text.get(3) + " ngan " + arr_text.get(4) + " tram dong";
				
				else
					text = text + arr_text.get(1) +  " tram " + arr_text.get(2) + " muoi " + arr_text.get(3) + " ngan " + arr_text.get(4) + " tram dong";

			}
						
			text = text.substring(0, 1).toUpperCase() + text.substring(1);
			return text;
		}
		
		if(numb.length() == 6)
		{
			if(arr_text.get(3).contains("muoi"))
			{
				text = arr_text.get(0) + " tram ";
				if(arr_text.get(1).contains("muoi") && arr_text.get(2).contains("muoi"))
					text = text + "ngan dong chan";
				
				else if(arr_text.get(2).contains("muoi"))
					text = text + arr_text.get(1) + " muoi ngan dong";
				
				else if(arr_text.get(1).contains("muoi"))
					text = text + "linh " + arr_text.get(2) + " ngan dong";
				
				else if(arr_text.get(1).contains("mot"))
					text = text + "muoi " + arr_text.get(2) + " ngan dong";
				
				else
					text = text + arr_text.get(1) + " muoi " + arr_text.get(2) + " ngan dong";
			}
			else
			{
				text = arr_text.get(0) + " tram ";
				if(arr_text.get(1).contains("muoi") && arr_text.get(2).contains("muoi"))
					text = text + "ngan " + arr_text.get(3) + " tram dong";
				
				else if(arr_text.get(2).contains("muoi"))
					text = text + arr_text.get(1) + " muoi ngan " + arr_text.get(3) + " tram dong";
				
				else if(arr_text.get(1).contains("muoi"))
					text = text + "linh " + arr_text.get(2) + " ngan " + arr_text.get(3) + " tram dong";
				
				else if(arr_text.get(1).contains("mot"))
					text = text + "muoi " + arr_text.get(2) + " ngan " + arr_text.get(3) + " tram dong";
				
				else
					text = text + arr_text.get(1) + " muoi " + arr_text.get(2) + " ngan " + arr_text.get(3) + " tram dong";
			}
			
			text = text.substring(0, 1).toUpperCase() + text.substring(1);
			return text;
		}
		
		if(numb.length() == 5)
		{
			if(arr_text.get(2).contains("muoi"))
			{
				if(arr_text.get(0).contains("mot"))
				{
					text = "muoi ";
					if(arr_text.get(1).contains("muoi"))
						text = text + "ngan dong chan";
					else
						text = text + arr_text.get(1) + " ngan dong";
				}
				else if(!arr_text.get(0).contains("mot") && arr_text.get(1).contains("mot"))
					text = arr_text.get(0) + " muoi mot ngan dong";
				
				else if(!arr_text.get(0).contains("mot") && arr_text.get(1).contains("muoi"))
					text = arr_text.get(0) + " muoi ngan dong";
				
				else
					text = arr_text.get(0) + " muoi " + arr_text.get(1) + " ngan dong";
			}
			else
			{
				if(arr_text.get(0).contains("mot"))
				{
					text = "muoi ";
					if(arr_text.get(1).contains("muoi"))
						text = text + "ngan " + arr_text.get(2) + " tram dong";
					else
						text = text + arr_text.get(1) + " ngan " + arr_text.get(2) + " tram dong";
				}
				else if(!arr_text.get(0).contains("mot") && arr_text.get(1).contains("mot"))
					text = arr_text.get(0) + " muoi mot ngan " + arr_text.get(2) + " tram dong";
				
				else if(!arr_text.get(0).contains("mot") && arr_text.get(1).contains("muoi"))
					text = arr_text.get(0) + " muoi ngan" + arr_text.get(2) +" tram dong";
				
				else
					text = arr_text.get(0) + " muoi " + arr_text.get(1) + " ngan " + arr_text.get(2) + " tram dong";
			}
						
			text = text.substring(0, 1).toUpperCase() + text.substring(1);
			return text;
		}
		return text;
	}
}
