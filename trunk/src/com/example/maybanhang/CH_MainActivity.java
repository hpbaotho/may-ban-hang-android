package com.example.maybanhang;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class CH_MainActivity extends Activity {

	private TextView tv_time, tv_sologun;
	private Button ch_exit;
	
	private String mayinhd_arr[] = {"Microsoft XPS Document Write","Fax","Không Sử Dụng"};
	private String khogiay_mihd1_arr[] = {"A4","A5"};
	private String  khogiay_mihd2_arr[] = {"K80","K57"};
	private String solien_arr[] = {"1","2","3","4"};
	
	private String mayinbarbep_arr[] = {"Microsoft XPS Document Write","Fax","Không Sử Dụng"};
	private String solienBaBep_arr[] = {"1","2","3","4"};
	
	private String tdk_Arr[] = {"Có", "Không"};
	private String tdk_td_arr[] = {"100","500","800","1000","1500","2000"};
	private String tdk_tr_arr[] = {"0","1","5","10","15","20","30"};
	
	private Spinner sp_MIHD1, sp_MIHD1_KGiay, sp_MIHD1_SLien, sp_MIHD2, sp_MIHD2_KGiay, sp_MIHD2_SLien;
	private Spinner sp_MIBa, sp_MIBa_SLBa, sp_MIBa_SLTN, sp_MIBe, sp_MIBe_SLBe, sp_MIBe_SLTN;
	private Spinner sp_tdk, sp_tdk_td, sp_tdk_tr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//Remove notification bar
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	   //set content view AFTER ABOVE sequence (to avoid crash)
		this.setContentView(R.layout.setting_main);
	    
	    //setting may in hoa don
	    sp_MIHD1 = (Spinner)findViewById(R.id.sp_MIHD1);
	    sp_MIHD2 = (Spinner)findViewById(R.id.sp_MIHD2);
	    sp_MIHD1_KGiay = (Spinner)findViewById(R.id.sp_MIHD1_KGiay);
	    sp_MIHD2_KGiay = (Spinner)findViewById(R.id.sp_MIHD2_KGiay);
	    sp_MIHD1_SLien = (Spinner)findViewById(R.id.sp_MIHD1_SLien);
	    sp_MIHD2_SLien = (Spinner)findViewById(R.id.sp_MIHD2_SLien);
	    
	    ch_exit = (Button)findViewById(R.id.ch_exit);
	    
	    ArrayAdapter<String> mihd1_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, mayinhd_arr);
	    mihd1_adt.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
	    sp_MIHD1.setAdapter(mihd1_adt);
	    
	    ArrayAdapter<String> mihd2_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, mayinhd_arr);
	    mihd2_adt.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
	    sp_MIHD2.setAdapter(mihd2_adt);
	    
	    ArrayAdapter<String> mihd1_khogiay_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, khogiay_mihd1_arr);
	    mihd1_khogiay_adt.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
	    sp_MIHD1_KGiay.setAdapter(mihd1_khogiay_adt);
	    
	    ArrayAdapter<String> mihd2_khogiay_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, khogiay_mihd2_arr);
	    mihd2_khogiay_adt.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
	    sp_MIHD2_KGiay.setAdapter(mihd2_khogiay_adt);
	    
	    ArrayAdapter<String> mihd1_solien_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, solien_arr);
	    mihd1_solien_adt.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
	    sp_MIHD1_SLien.setAdapter(mihd1_solien_adt);
	    
	    ArrayAdapter<String> mihd2_solien_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, solien_arr);
	    mihd2_solien_adt.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
	    sp_MIHD2_SLien.setAdapter(mihd2_solien_adt);
	    
	    //setting may in bar bep
	    sp_MIBa = (Spinner)findViewById(R.id.sp_MIBa);
	    sp_MIBe = (Spinner)findViewById(R.id.sp_MIBe);
	    sp_MIBa_SLBa = (Spinner)findViewById(R.id.sp_MIBa_SLBa);
	    sp_MIBe_SLBe = (Spinner)findViewById(R.id.sp_MIBe_SLBe);
	    sp_MIBa_SLTN = (Spinner)findViewById(R.id.sp_MIBa_SLTN);
	    sp_MIBe_SLTN = (Spinner)findViewById(R.id.sp_MIBe_SLTN);
	    
	    ArrayAdapter<String> miba_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, mayinbarbep_arr);
	    miba_adt.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
	    sp_MIBa.setAdapter(miba_adt);
	    
	    ArrayAdapter<String> mibe_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, mayinbarbep_arr);
	    mibe_adt.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
	    sp_MIBe.setAdapter(mibe_adt);
	    
	    ArrayAdapter<String> miba_slba_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, solienBaBep_arr);
	    miba_slba_adt.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
	    sp_MIBa_SLBa.setAdapter(miba_slba_adt);
	    
	    ArrayAdapter<String> mibe_slbe_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, solienBaBep_arr);
	    mibe_slbe_adt.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
	    sp_MIBe_SLBe.setAdapter(mibe_slbe_adt);
	    
	    ArrayAdapter<String> miba_sltn_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, solienBaBep_arr);
	    miba_sltn_adt.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
	    sp_MIBa_SLTN.setAdapter(miba_sltn_adt);
	    
	    ArrayAdapter<String> mibe_sltn_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, solienBaBep_arr);
	    mibe_sltn_adt.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
	    sp_MIBe_SLTN.setAdapter(mibe_sltn_adt);
	    
	    //setting tu dieu khien
	    sp_tdk = (Spinner)findViewById(R.id.sp_tdk);
	    sp_tdk_td = (Spinner)findViewById(R.id.sp_tdk_td);
	    sp_tdk_tr = (Spinner)findViewById(R.id.sp_tdk_tr);
	    
	    ArrayAdapter<String> tdk_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, tdk_Arr);
	    tdk_adt.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
	    sp_tdk.setAdapter(tdk_adt);
	    
	    ArrayAdapter<String> tdk_td_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, tdk_td_arr);
	    tdk_td_adt.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
	    sp_tdk_td.setAdapter(tdk_td_adt);
	    
	    ArrayAdapter<String> tdk_tr_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, tdk_tr_arr);
	    tdk_tr_adt.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
	    sp_tdk_tr.setAdapter(tdk_tr_adt);
	    		
		loadTabs();
	}
	
	private class MyEvent implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.ch_exit:
				finish();
				break;
			default:
				break;
			}
		}
	}

	// Cấu hình tab
	public void loadTabs() {

		final TabHost tab = (TabHost) findViewById(android.R.id.tabhost);

		// gọi lệnh setup
		tab.setup();
		TabHost.TabSpec spec;

		// Tạo tab1
		spec = tab.newTabSpec("t1");
		spec.setContent(R.id.tab1);
		spec.setIndicator("Kết Nối Máy Chủ");
		tab.addTab(spec);

		// Tạo tab2
		spec = tab.newTabSpec("t2");
		spec.setContent(R.id.tab2);
		spec.setIndicator("Máy In Hóa Đơn");
		tab.addTab(spec);

		// Tạo tab3
		spec = tab.newTabSpec("t3");
		spec.setContent(R.id.tab3);
		spec.setIndicator("Máy In Bar Bếp");
		tab.addTab(spec);

		// Tạo tab4
		spec = tab.newTabSpec("t4");
		spec.setContent(R.id.tab4);
		spec.setIndicator("Kết Nối Tủ Điều Khiển");
		tab.addTab(spec);

		tab.setCurrentTab(0);

		// Ví dụ tab1 chưa nhập thông tin xong mà lại qua tab 2 thì báo...
		tab.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
			public void onTabChanged(String arg0) {
				String s = "Tab tag =" + arg0 + "; index ="
						+ tab.getCurrentTab();
				//Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG)
					//	.show();
			}
		});
	}
}
