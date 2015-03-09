package com.example.maybanhang;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TableRow.LayoutParams;

public class BC_MainActivity extends Activity {

	private String tuychon_arr[] = { "BC HD: Tổng Hợp", "--------------------",
			"BC DV theo: Thời Điểm Phát Sinh",
			"BC DV theo: Thời Điểm Thanh Toán", "-------------------",
			"BC DT theo: NVTN: Từng Người", "BC DT theo: NVTN: Tổng Hợp" };
	private Spinner sp_bc_tuychon;
	private Button bc_exit;
	private TableLayout table;
	
	private EditText edt_bc_timefrom, edt_bc_timeto, edt_bc_datefrom, edt_bc_dateto;
	static final int DATE_DIALOG_TIMEFROM = 999;
	static final int DATE_DIALOG_TIMETO = 998;
	static final int DATE_DIALOG_DATEFROM = 997;
	static final int DATE_DIALOG_DATETO = 996;
	private int year, month, day, hour, minute, second;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// set content view AFTER ABOVE sequence (to avoid crash)
		this.setContentView(R.layout.bc_main);

		loadtabs();
		table = (TableLayout) findViewById(R.id.tableLayout);
		bc_exit = (Button)findViewById(R.id.bc_exit);
		
		// Tuy chon bao cao
		sp_bc_tuychon = (Spinner) findViewById(R.id.sp_bc_tuychon);
		ArrayAdapter<String> tuychon_adt = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, tuychon_arr);
		tuychon_adt
				.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		sp_bc_tuychon.setAdapter(tuychon_adt);

		bc_exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		//choose datetime
		edt_bc_timefrom = (EditText)findViewById(R.id.edt_bc_timefrom);
		edt_bc_timeto = (EditText)findViewById(R.id.edt_bc_timeto);
		edt_bc_datefrom = (EditText)findViewById(R.id.edt_bc_datefrom);
		edt_bc_dateto = (EditText)findViewById(R.id.edt_bc_dateto);
		
		edt_bc_datefrom.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_DATEFROM);
			}
		});
		
		edt_bc_dateto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_DATETO);
			}
		});
		
		edt_bc_timefrom.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_TIMEFROM);
			}
		});
		
		edt_bc_timeto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_TIMETO);
			}
		});
		
		//get current datetime
		setCurrentDateOnView();
		
		buidTableTitle();
		buildTableData();
		buildTableData();
		buildTableData();
		buildTableData();
		buildTableData();
		buildTableData();
		buildTableData();
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
		tv1.setText("Mã Ca");
		tv1.setBackgroundColor(Color.GRAY);
		row.addView(tv1);

		TextView tv2 = new TextView(this);
		LayoutParams lo2 = new LayoutParams(200, 40);
		lo2.setMargins(0, 0, 2, 0);
		tv2.setLayoutParams(lo2);
		// tv2.setBackgroundResource(R.drawable.cell_shape);
		tv2.setGravity(Gravity.CENTER);
		tv2.setText("NV Làm Ca");
		tv2.setBackgroundColor(Color.GRAY);
		row.addView(tv2);

		TextView tv3 = new TextView(this);
		LayoutParams lo3 = new LayoutParams(200, 40);
		lo3.setMargins(0, 0, 2, 0);
		tv3.setLayoutParams(lo3);
		// tv3.setBackgroundResource(R.drawable.cell_shape);
		tv3.setGravity(Gravity.CENTER);
		tv3.setText("TĐ Bắt Đầu");
		tv3.setBackgroundColor(Color.GRAY);
		row.addView(tv3);

		TextView tv4 = new TextView(this);
		LayoutParams lo4 = new LayoutParams(200, 40);
		lo4.setMargins(0, 0, 2, 0);
		tv4.setLayoutParams(lo4);
		// tv4.setBackgroundResource(R.drawable.cell_shape);
		tv4.setGravity(Gravity.CENTER);
		tv4.setText("TĐ Kết Thúc");
		tv4.setBackgroundColor(Color.GRAY);
		row.addView(tv4);

		TextView tv5 = new TextView(this);
		LayoutParams lo5 = new LayoutParams(150, 40);
		lo5.setMargins(0, 0, 2, 0);
		tv5.setLayoutParams(lo5);
		// tv5.setBackgroundResource(R.drawable.cell_shape);
		tv5.setGravity(Gravity.CENTER);
		tv5.setText("Số Lượng HĐ");
		tv5.setBackgroundColor(Color.GRAY);
		row.addView(tv5);

		TextView tv6 = new TextView(this);
		LayoutParams lo6 = new LayoutParams(200, 40);
		lo6.setMargins(0, 0, 2, 0);
		tv6.setLayoutParams(lo6);
		// tv6.setBackgroundResource(R.drawable.cell_shape);
		tv6.setGravity(Gravity.CENTER);
		tv6.setText("Tổng Tiền");
		tv6.setBackgroundColor(Color.GRAY);
		row.addView(tv6);

		TextView tv7 = new TextView(this);
		LayoutParams lo7 = new LayoutParams(150, 40);
		lo7.setMargins(0, 0, 2, 0);
		tv7.setLayoutParams(lo7);
		// tv7.setBackgroundResource(R.drawable.cell_shape);
		tv7.setGravity(Gravity.CENTER);
		tv7.setText("Ghi Chú");
		tv7.setBackgroundColor(Color.GRAY);
		row.addView(tv7);

		table.addView(row);
	}

	private void buildTableData() {

		final TableRow row = new TableRow(this);
		row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		registerForContextMenu(row);
		
		row.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//row.setBackgroundResource(R.drawable.table_row_selector);
				for (int i = 1; i < table.getChildCount(); i++) {
					TableRow row_item = (TableRow) table.getChildAt(i);
					row_item.setBackgroundResource(R.drawable.tr_select);
					
				}
				row.setBackgroundResource(R.drawable.tr_selected);
			}
		});

		TextView tv1 = new TextView(this);
		LayoutParams lo1 = new LayoutParams(100, 50);
		lo1.setMargins(0, 0, 2, 0);
		tv1.setLayoutParams(lo1);
		tv1.setGravity(Gravity.CENTER);
		tv1.setBackgroundResource(R.drawable.cell_shape);
		// tv1.setPadding(5, 5, 5, 5);
		tv1.setText("33");
		row.addView(tv1);

		TextView tv2 = new TextView(this);
		LayoutParams lo2 = new LayoutParams(200, 50);
		lo2.setMargins(0, 0, 2, 0);
		tv2.setLayoutParams(lo2);
		tv2.setGravity(Gravity.CENTER);
		tv2.setBackgroundResource(R.drawable.cell_shape);
		// tv2.setPadding(5, 5, 5, 5);
		tv2.setText("Nguyễn Thị Lụa");
		row.addView(tv2);

		TextView tv3 = new TextView(this);
		LayoutParams lo3 = new LayoutParams(200, 50);
		lo3.setMargins(0, 0, 2, 0);
		tv3.setLayoutParams(lo3);
		// tv3.setBackgroundResource(R.drawable.cell_shape);
		tv3.setGravity(Gravity.CENTER);
		tv3.setBackgroundResource(R.drawable.cell_shape);
		// tv3.setPadding(5, 5, 5, 5);
		tv3.setText("17/Apr/2014 20:41:12");
		row.addView(tv3);

		TextView tv4 = new TextView(this);
		LayoutParams lo4 = new LayoutParams(200, 50);
		lo4.setMargins(0, 0, 2, 0);
		tv4.setLayoutParams(lo4);
		// tv4.setBackgroundResource(R.drawable.cell_shape);
		tv4.setGravity(Gravity.CENTER);
		tv4.setBackgroundResource(R.drawable.cell_shape);
		// tv4.setPadding(5, 5, 5, 5);
		tv4.setText("17/Apr/2014 22:41:12");
		row.addView(tv4);

		TextView tv5 = new TextView(this);
		LayoutParams lo5 = new LayoutParams(150, 50);
		lo5.setMargins(0, 0, 2, 0);
		tv5.setLayoutParams(lo5);
		// tv5.setBackgroundResource(R.drawable.cell_shape);
		tv5.setGravity(Gravity.CENTER);
		tv5.setBackgroundResource(R.drawable.cell_shape);
		// tv5.setPadding(5, 5, 5, 5);
		tv5.setText("20");
		row.addView(tv5);

		TextView tv6 = new TextView(this);
		LayoutParams lo6 = new LayoutParams(200, 50);
		lo6.setMargins(0, 0, 2, 0);
		tv6.setLayoutParams(lo6);
		// tv6.setBackgroundResource(R.drawable.cell_shape);
		tv6.setGravity(Gravity.CENTER);
		tv6.setBackgroundResource(R.drawable.cell_shape);
		// tv6.setPadding(5, 5, 5, 5);
		tv6.setText("28.000.000");
		row.addView(tv6);

		TextView tv7 = new TextView(this);
		LayoutParams lo7 = new LayoutParams(150, 50);
		lo7.setMargins(0, 0, 2, 0);
		tv7.setLayoutParams(lo7);
		// tv7.setBackgroundResource(R.drawable.cell_shape);
		tv7.setGravity(Gravity.CENTER);
		tv7.setBackgroundResource(R.drawable.cell_shape);
		// tv7.setPadding(5, 5, 5, 5);
		tv7.setText("");
		row.addView(tv7);
		
		table.addView(row);
	}
	

	// display current date
		public void setCurrentDateOnView() {


			Calendar cal = Calendar.getInstance();
			SimpleDateFormat dft = null;
			// Định dạng ngày / tháng /năm
			dft = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
			String strDate = dft.format(cal.getTime());
			
			String current_time = strDate.substring(11,19);
			String current_date = strDate.substring(0,10);
			
			// hiển thị lên giao diện
			edt_bc_timefrom.setText(current_time);
			edt_bc_timeto.setText(current_time);
			edt_bc_datefrom.setText(current_date);
			edt_bc_dateto.setText(current_date);
			
			
			day = Integer.parseInt(strDate.substring(0,2));
			month = Integer.parseInt(strDate.substring(3,5));
			year = Integer.parseInt(strDate.substring(6,10));

		}

		// show datePicker
		@Override
		protected Dialog onCreateDialog(int id) {
			switch (id) {
			case DATE_DIALOG_DATEFROM:
				// set date picker as current date
				return new DatePickerDialog(this, datePickerListener_datefrom, year,
						month, day);
			case DATE_DIALOG_DATETO:
				return new DatePickerDialog(this, datePickerListener_dateto, year,
						month, day);
			case DATE_DIALOG_TIMEFROM:
				return new TimePickerDialog(this, timePickerListener_timefrom, hour,
						minute, true);
			case DATE_DIALOG_TIMETO:
				return new TimePickerDialog(this, timePickerListener_timeto, hour,
						minute, true);
			}
			return null;
		}

		private DatePickerDialog.OnDateSetListener datePickerListener_datefrom = new DatePickerDialog.OnDateSetListener() {

			// when dialog box is closed, below method will be called.
			public void onDateSet(DatePicker view, int selectedYear,
					int selectedMonth, int selectedDay) {
				year = selectedYear;
				month = selectedMonth;
				day = selectedDay;

				// set selected date into textview
				String da = day + "";
				String mon = (month + 1) + "";
				String yea = year + "";
				if(da.length() == 1) da = "0" + da;
				if(mon.length() == 1) mon = "0" + mon;
				edt_bc_datefrom.setText(da + "/" + mon + "/" + yea);

			}
		};

		private DatePickerDialog.OnDateSetListener datePickerListener_dateto = new DatePickerDialog.OnDateSetListener() {

			// when dialog box is closed, below method will be called.
			public void onDateSet(DatePicker view, int selectedYear,
					int selectedMonth, int selectedDay) {
				year = selectedYear;
				month = selectedMonth;
				day = selectedDay;

				// set selected date into textview
				String da = day + "";
				String mon = (month + 1) + "";
				String yea = year + "";
				if(da.length() == 1) da = "0" + da;
				if(mon.length() == 1) mon = "0" + mon;
				edt_bc_dateto.setText(da + "/" + mon + "/" + yea);

			}
		};
		
		private TimePickerDialog.OnTimeSetListener timePickerListener_timefrom = new TimePickerDialog.OnTimeSetListener() {

			// when dialog box is closed, below method will be called.
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfDay) {
				// TODO Auto-generated method stub
				
				hour = hourOfDay;
				minute = minuteOfDay;
				
				String ho = hour + "";
				String mi = minute + "";
				if(ho.length() == 1) ho = "0" + ho;
				if(mi.length() == 1) mi = "0" + mi;
				
				edt_bc_timefrom.setText(ho + ":" + mi);
			}
		};
		
		private TimePickerDialog.OnTimeSetListener timePickerListener_timeto = new TimePickerDialog.OnTimeSetListener() {

			// when dialog box is closed, below method will be called.
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfDay) {
				// TODO Auto-generated method stub
				
				hour = hourOfDay;
				minute = minuteOfDay;
				
				String ho = hour + "";
				String mi = minute + "";
				if(ho.length() == 1) ho = "0" + ho;
				if(mi.length() == 1) mi = "0" + mi;
				
				edt_bc_timeto.setText(ho + ":" + mi);
			}
		};
		
		
		
	// Cau hinh tab
	public void loadtabs() {
		final TabHost tab = (TabHost) findViewById(android.R.id.tabhost);

		// gọi lệnh setup
		tab.setup();
		TabHost.TabSpec spec;

		// Tạo tab1
		spec = tab.newTabSpec("t1");
		spec.setContent(R.id.tab1);
		spec.setIndicator("Lịch Sử Ca");
		tab.addTab(spec);

		// Tạo tab2
		spec = tab.newTabSpec("t2");
		spec.setContent(R.id.tab2);
		spec.setIndicator("Tùy Chọn");
		tab.addTab(spec);

		tab.setCurrentTab(0);
	}
}
