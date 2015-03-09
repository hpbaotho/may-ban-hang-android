package com.example.maybanhang;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.example.maybanhang.R;
import com.example.maybanhang.R.id;
import com.example.maybanhang.R.layout;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity{

	private TextView tv_time, tv_sologun;
	private Button btn_user,btn_logout;
	static public ImageView img_ca;
	static public Button btn_ca;
	static public TextView tv_welcome;
	static public String username;
	static public Calendar calendar;
	private List<CTN_CaThuNgan> allCTN;
	
	static public ArrayList<Object> arr_status = new ArrayList<Object>();
	private DatabaseHandler database = new DatabaseHandler(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    //Remove notification bar
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	   //set content view AFTER ABOVE sequence (to avoid crash)
	    this.setContentView(R.layout.main_activity); 
	   	    
	    tv_welcome = (TextView)findViewById(R.id.tv_welcome);
	    tv_time = (TextView)findViewById(R.id.tv_time);
	    btn_logout = (Button)findViewById(R.id.btn_exitQLC);
	    btn_ca = (Button)findViewById(R.id.btn_ca);
	    btn_user = (Button)findViewById(R.id.btn_user);
	    img_ca = (ImageView)findViewById(R.id.imv_ca);
	    
	    //get & set current time
	    calendar = Calendar.getInstance();
	    //SimpleDateFormat sdf = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    tv_time.setText(sdf.format(calendar.getTime()));
	    
	    
	    //get username from login
	    try {
	    	Intent intent = getIntent();
	 	    Bundle bd = intent.getBundleExtra("data");
	 	    username = bd.getString("username");
		} catch (Exception e) {
			// TODO: handle exception
		}
	   
	    //update interface basic user
	    SimpleDateFormat sdfw = new SimpleDateFormat("dd/MM/yyyy   HH:mm a");
	    btn_user.setText(username);
	    try {
	    	if (database.getLastCTNByUser(username).getTdKetThuc().isEmpty()) {
		    	btn_ca.setText("Kết thúc ca");
				btn_ca.setBackgroundColor(Color.parseColor("#A11A1A"));
				img_ca.setBackgroundColor(Color.parseColor("#A11A1A"));
				tv_welcome.setText("Xin chào " + username + ". Ca làm việc của bạn bắt đầu lúc " + database.getLastCTNByUser(username).getTdBatDau());
			}	    
		    else
		    	tv_welcome.setText("Xin chào "+username + ". Bạn chưa bắt đầu ca làm việc của mình! ");
		} catch (Exception e) {
			// TODO: handle exception
			tv_welcome.setText("Xin chào "+username + ". Bạn chưa bắt đầu ca làm việc của mình! ");
		}
	    
	    loadtabs();
	    
	    //set click event fro buttons
	    btn_ca.setOnClickListener(new MyEvent());
	    btn_logout.setOnClickListener(new MyEvent());
	    btn_user.setOnClickListener(new MyEvent());
	    
	    
	}
	private class MyEvent implements OnClickListener {
    	@Override
    	public void onClick(View v) {
    		// TODO Auto-generated method stub
    		switch (v.getId()) {
    		case R.id.btn_ca:
    			if(btn_ca.getText().toString().equals("Bắt đầu ca"))
    			{
    				AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
    				b.setTitle("Thông báo");
    				b.setMessage("Bạn có chắc chắn muốn bắt đầu ca làm việc mới?");
    				b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							btn_ca.setText("Kết thúc ca");
							btn_ca.setBackgroundColor(Color.parseColor("#A11A1A"));
							img_ca.setBackgroundColor(Color.parseColor("#A11A1A"));
							
							SimpleDateFormat sdfw = new SimpleDateFormat("dd/MM/yyyy   HH:mm a");
							tv_welcome.setText("Xin chào " + username + ". Ca làm việc của bạn bắt đầu lúc " + sdfw.format(calendar.getTime()));
							btn_user.setText(username);
							//arr_status.set(0, "yes");
							
							CTN_CaThuNgan ctn_bd = new CTN_CaThuNgan(username, sdfw.format(calendar.getTime()), "", "", "", "", "", "", "");
							database.addNewCTN(ctn_bd);
						}
					});
    				b.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.cancel();
						}
					});
    				b.create().show();
    			}
    			else if(btn_ca.getText().toString().equals("Kết thúc ca"))
    			{
    				AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
    				b.setTitle("Thông báo");
    				b.setMessage("Bạn có chắc chắn muốn kết thúc ca hiện tại?");
    				b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							btn_ca.setText("Bắt đầu ca");
							btn_ca.setBackgroundColor(Color.parseColor("#389538"));
							img_ca.setBackgroundColor(Color.parseColor("#389538"));
							SimpleDateFormat sdfw = new SimpleDateFormat("dd/MM/yyyy  HH:mm a");
							tv_welcome.setText("Xin chào " + username + ". Ca làm việc của bạn kết thúc lúc " + sdfw.format(calendar.getTime()));
							//arr_status.set(0, "no");
							
							CTN_CaThuNgan ctn_kt = new CTN_CaThuNgan(username, "", sdfw.format(calendar.getTime()), "", "", "", "", "", "");
							ctn_kt.setID(database.getLastCTNByUser(username).getID());
							int k = database.updateCTN(ctn_kt);
						}
					});
    				b.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.cancel();
						}
					});
    				b.create().show();
    			}
    			break;
    		case R.id.btn_user:
    			NV_NhanVien nhanvien = database.getNhanvienByUser(username);
    			Intent intent = new Intent(MainActivity.this,NV_SuaTTin_Activity.class);
    			intent.putExtra("nhanvien", nhanvien);
    			startActivity(intent);
    			break;
    		case R.id.btn_exitQLC:
    			Intent intent_exit  = new Intent(MainActivity.this,LoginActivity.class);
    			startActivity(intent_exit);
    			finish();
    		default:
    			break;
    		}
    	}
    }
	
	//Cau hinh tab
	public void loadtabs()
	{
		final TabHost tabHost = getTabHost();
		
        
        // Tab for Photos
        TabSpec layngay = tabHost.newTabSpec("");
        layngay.setIndicator("Hóa Đơn Lấy Ngay");//, getResources().getDrawable(R.drawable.icon_photos_tab));
        Intent photosIntent = new Intent(this, Main_HoaDonLayNgayActivity.class);
        layngay.setContent(photosIntent);
        
        // Tab for Songs
        TabSpec codinh = tabHost.newTabSpec("");
        // setting Title and Icon for the Tab
        codinh.setIndicator("Khu Vực Cố Định");//, getResources().getDrawable(R.drawable.icon_songs_tab));
        Intent songsIntent = new Intent(this, Main_KhuVucCoDinhActivity.class);
        codinh.setContent(songsIntent);
        
        // Tab for Videos
        TabSpec quanlychung = tabHost.newTabSpec("");
        quanlychung.setIndicator("Quản Lý Chung");//, getResources().getDrawable(R.drawable.icon_videos_tab));
        Intent videosIntent = new Intent(this, Main_QuanLyChungActivity.class);
        quanlychung.setContent(videosIntent);
        
        // Adding all TabSpec to TabHost
        try {
        	tabHost.addTab(layngay); 
            tabHost.addTab(codinh); 
            tabHost.addTab(quanlychung); 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        
        
        tabHost.setCurrentTab(1);
        
        tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
			}
		});
	}
}
