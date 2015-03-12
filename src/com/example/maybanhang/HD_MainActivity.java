package com.example.maybanhang;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.maybanhang.Main_HoaDonLayNgayActivity;
import com.example.maybanhang.Main_KhuVucCoDinhActivity;
import com.example.maybanhang.Main_QuanLyChungActivity;
import com.example.maybanhang.R;
import com.example.maybanhang.R.id;
import com.example.maybanhang.R.layout;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class HD_MainActivity extends TabActivity{

	private Button btn_exitHD;
	public static TextView tv_khuvucHD, tv_timeHD;
	public static EditText edt_khuvucHD, edt_maHD, edt_thunganHD,edt_nhanvienHD;
	public String username;
	public static String khuvuc;
	public static String tdBD;
	public static int hd_id;
	
	private DatabaseHandler database = new DatabaseHandler(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    //Remove notification bar
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	   //set content view AFTER ABOVE sequence (to avoid crash)
	    this.setContentView(R.layout.hd_main); 
	    
	    final SimpleDateFormat sdfw = new SimpleDateFormat("dd/mm/yyyy   HH:mm a");
	    
	    btn_exitHD = (Button)findViewById(R.id.btn_exitHD);
	    tv_khuvucHD = (TextView)findViewById(R.id.tv_khuvucHD);
	    tv_timeHD = (TextView)findViewById(R.id.tv_timeHD);
	    edt_khuvucHD = (EditText)findViewById(R.id.edt_khuvucHD);
	    edt_maHD = (EditText)findViewById(R.id.edt_maHD);
	    edt_thunganHD = (EditText)findViewById(R.id.edt_thunganHD);
	    edt_nhanvienHD = (EditText)findViewById(R.id.edt_nhanvienHD);
	    
	    btn_exitHD.setOnClickListener(new MyEvent());
	    	
	    //get ID
	    Intent intent = getIntent();
	    Bundle bundle = intent.getBundleExtra("data");
	    hd_id = bundle.getInt("id");
	    khuvuc = bundle.getString("khuvuc");
	    tdBD = bundle.getString("tdbd");
	    
	    edt_khuvucHD.setText(khuvuc);
	    edt_maHD.setText(hd_id + "");
	    edt_nhanvienHD.setText(MainActivity.username);
	    edt_thunganHD.setText(MainActivity.username);
	    
	    tv_khuvucHD.setText(khuvuc);
	    tv_timeHD.setText(tdBD);
	    //tv_timeHD.setText(sdfw.format(MainActivity.calendar.getTime()));
	    
	    //load tab
	    loadtabs();   
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
				case R.id.btn_exitHD:
					Intent intent = new Intent(HD_MainActivity.this,MainActivity.class);
					startActivity(intent);
					finish();
					break;
				default:
					break;
			}
		}
	}
	
	//Cau hinh tab
	public void loadtabs()
	{
		TabHost tabHost = getTabHost();
		
        
        // Tab for Photos
        TabSpec photospec = tabHost.newTabSpec("");
        photospec.setIndicator("Thông Tin Hóa Đơn");//, getResources().getDrawable(R.drawable.icon_photos_tab));
        Intent photosIntent = new Intent(this, HD_ThongTinChungActivity.class);
        photospec.setContent(photosIntent);
        
        // Tab for Songs
        TabSpec songspec = tabHost.newTabSpec("");
        // setting Title and Icon for the Tab
        songspec.setIndicator("Danh Mục Sản Phẩm");//, getResources().getDrawable(R.drawable.icon_songs_tab));
        Intent songsIntent = new Intent(this, HD_DanhMucSPActivity.class);
        songspec.setContent(songsIntent);
        
        // Tab for Videos
        TabSpec videospec = tabHost.newTabSpec("");
        videospec.setIndicator("Chi Tiết Hóa Đơn");//, getResources().getDrawable(R.drawable.icon_videos_tab));
        Intent videosIntent = new Intent(this, HD_ChiTietActivity.class);
        videospec.setContent(videosIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(photospec); // Adding photos tab
        tabHost.addTab(songspec); // Adding songs tab
        tabHost.addTab(videospec); // Adding videos tab
        
        tabHost.setCurrentTab(2);
	}
}
