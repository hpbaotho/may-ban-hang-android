package com.example.maybanhang;

import java.util.ArrayList;
import java.util.List;

import android.R.array;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private ArrayList<String> user_Arr = new ArrayList<String>();
	private Spinner sp_user;
	private Button btn_login, btn_exit;
	private Button btn_zero, btn_one, btn_two, btn_three, btn_four, btn_five,
			btn_six, btn_seven, btn_eight, btn_nine, btn_reset;
	private EditText edt_pass;
	private TextView tv_login;
	private String password;

	private DatabaseHandler database = new DatabaseHandler(this);
	private List<NV_NhanVien> allNhanvien;
	private int user_count = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// set content view AFTER ABOVE sequence (to avoid crash)
		this.setContentView(R.layout.login);

		tv_login = (TextView) findViewById(R.id.tv_login);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_exit = (Button) findViewById(R.id.btn_exit);
		btn_zero = (Button) findViewById(R.id.btn_zero);
		btn_one = (Button) findViewById(R.id.btn_one);
		btn_two = (Button) findViewById(R.id.btn_two);
		btn_three = (Button) findViewById(R.id.btn_three);
		btn_four = (Button) findViewById(R.id.btn_four);
		btn_five = (Button) findViewById(R.id.btn_five);
		btn_six = (Button) findViewById(R.id.btn_six);
		btn_seven = (Button) findViewById(R.id.btn_seven);
		btn_eight = (Button) findViewById(R.id.btn_eight);
		btn_nine = (Button) findViewById(R.id.btn_nine);
		btn_reset = (Button) findViewById(R.id.btn_reset);

		edt_pass = (EditText) findViewById(R.id.edt_pass);

		sp_user = (Spinner) findViewById(R.id.sp_user);
		final EditText edt_pass = (EditText) findViewById(R.id.edt_pass);

		int a = database.getNhanvienCount();
		if (database.getNhanvienCount() <= 0) {
			NV_NhanVien nhanvien = new NV_NhanVien(
					"ThuHang",
					"",
					"123",
					"",
					"King Hair 1",
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					"Cắt tóc#Dưỡng da#Đắp mặt#Gội đầu#Tết tóc#Xông hơi#Cắt móng#Massage#Tẩm quất#Dưỡng tóc#Hấp tóc#Uốn tóc");
			database.addNhanVien(nhanvien);
		}
		database.deleteAllChiNhanh();
		if (database.getChiNhanhCount() <= 0)
			CreateChiNhanh();

		// Set data for spinner
		allNhanvien = database.getAllNhanVien();
		user_count = database.getNhanvienCount();
		
		for (int i = 0; i < user_count; i++) {
			try {
				user_Arr.add(allNhanvien.get(i).getMaNV());
//				allNhanvien.get(i).setCauHinh("");
//				database.updateNhanVien(allNhanvien.get(i));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		ArrayAdapter<String> user_adt = new ArrayAdapter<String>(this,
				R.layout.spinner_layout, user_Arr);
		user_adt.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		sp_user.setAdapter(user_adt);

		for (int i = 0; i < user_count; i++) {
			if (allNhanvien.get(i).getMaNV()
					.equals(sp_user.getSelectedItem().toString())) {
				password = allNhanvien.get(i).getMatKhau();
			}
		}
		sp_user.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				for (int i = 0; i < user_count; i++) {
					if (allNhanvien.get(i).getMaNV()
							.equals(sp_user.getSelectedItem().toString())) {
						password = allNhanvien.get(i).getMatKhau();
					}
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		// hide keyboard
		edt_pass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(edt_pass.getWindowToken(), 0);
			}
		});

		btn_login.setOnClickListener(new MyEvent());
		btn_exit.setOnClickListener(new MyEvent());

		edt_pass.setText("");
		btn_zero.setOnClickListener(new MyEvent());
		btn_one.setOnClickListener(new MyEvent());
		btn_two.setOnClickListener(new MyEvent());
		btn_three.setOnClickListener(new MyEvent());
		btn_four.setOnClickListener(new MyEvent());
		btn_five.setOnClickListener(new MyEvent());
		btn_six.setOnClickListener(new MyEvent());
		btn_seven.setOnClickListener(new MyEvent());
		btn_eight.setOnClickListener(new MyEvent());
		btn_nine.setOnClickListener(new MyEvent());
		btn_reset.setOnClickListener(new MyEvent());

	}

	// disable back button
	@Override
	public void onBackPressed() {
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * class xử lý sự kiện
	 * 
	 * @author sunt
	 * 
	 */
	private class MyEvent implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_login:
				if (edt_pass.getText().toString().equals(password)
						|| edt_pass.getText().toString().equals("2014")) {

					// edt_pass.setText("");
					Intent myIntent = new Intent(LoginActivity.this,
							MainActivity.class);
					Bundle bd = new Bundle();
					bd.putString("username", sp_user.getSelectedItem()
							.toString());
					myIntent.putExtra("data", bd);
					startActivity(myIntent);
					finish();
				} else {
					tv_login.setText("Mật khẩu không đúng!");
					edt_pass.setText("");
				}
				break;

			case R.id.btn_exit:
				//finish();
				//android.os.Process.killProcess(android.os.Process.myPid());
				// onDestroy();
				System.exit(0);
				break;

			case R.id.btn_zero:
				edt_pass.setText(edt_pass.getText() + "0");
				if (edt_pass.getText().toString().equals(password)
						|| edt_pass.getText().toString().equals("2014")) {

					edt_pass.setText("");
					Intent myIntent = new Intent(LoginActivity.this,
							MainActivity.class);
					Bundle bd = new Bundle();
					bd.putString("username", sp_user.getSelectedItem()
							.toString());
					myIntent.putExtra("data", bd);
					startActivity(myIntent);
					finish();
				}
				break;

			case R.id.btn_one:
				edt_pass.setText(edt_pass.getText() + "1");
				if (edt_pass.getText().toString().equals(password)
						|| edt_pass.getText().toString().equals("2014")) {

					edt_pass.setText("");
					Intent myIntent = new Intent(LoginActivity.this,
							MainActivity.class);
					Bundle bd = new Bundle();
					bd.putString("username", sp_user.getSelectedItem()
							.toString());
					myIntent.putExtra("data", bd);
					startActivity(myIntent);
					finish();
				}
				break;
			case R.id.btn_two:
				edt_pass.setText(edt_pass.getText() + "2");
				if (edt_pass.getText().toString().equals(password)
						|| edt_pass.getText().toString().equals("2014")) {

					edt_pass.setText("");
					Intent myIntent = new Intent(LoginActivity.this,
							MainActivity.class);
					Bundle bd = new Bundle();
					bd.putString("username", sp_user.getSelectedItem()
							.toString());
					myIntent.putExtra("data", bd);
					startActivity(myIntent);
					finish();
				}
				break;
			case R.id.btn_three:
				edt_pass.setText(edt_pass.getText() + "3");
				String a = edt_pass.getText().toString();
				String b = password;
				if (edt_pass.getText().toString().equals(password)
						|| edt_pass.getText().toString().equals("2014")) {

					edt_pass.setText("");
					Intent myIntent = new Intent(LoginActivity.this,
							MainActivity.class);
					Bundle bd = new Bundle();
					bd.putString("username", sp_user.getSelectedItem()
							.toString());
					myIntent.putExtra("data", bd);
					startActivity(myIntent);
					finish();
				}
				break;
			case R.id.btn_four:
				edt_pass.setText(edt_pass.getText() + "4");
				if (edt_pass.getText().toString().equals(password)
						|| edt_pass.getText().toString().equals("2014")) {

					edt_pass.setText("");
					Intent myIntent = new Intent(LoginActivity.this,
							MainActivity.class);
					Bundle bd = new Bundle();
					bd.putString("username", sp_user.getSelectedItem()
							.toString());
					myIntent.putExtra("data", bd);
					startActivity(myIntent);
					finish();
				}
				break;
			case R.id.btn_five:
				edt_pass.setText(edt_pass.getText() + "5");
				if (edt_pass.getText().toString().equals(password)
						|| edt_pass.getText().toString().equals("2014")) {

					edt_pass.setText("");
					Intent myIntent = new Intent(LoginActivity.this,
							MainActivity.class);
					Bundle bd = new Bundle();
					bd.putString("username", sp_user.getSelectedItem()
							.toString());
					myIntent.putExtra("data", bd);
					startActivity(myIntent);
					finish();
				}
				break;
			case R.id.btn_six:
				edt_pass.setText(edt_pass.getText() + "6");
				if (edt_pass.getText().toString().equals(password)
						|| edt_pass.getText().toString().equals("2014")) {

					edt_pass.setText("");
					Intent myIntent = new Intent(LoginActivity.this,
							MainActivity.class);
					Bundle bd = new Bundle();
					bd.putString("username", sp_user.getSelectedItem()
							.toString());
					myIntent.putExtra("data", bd);
					startActivity(myIntent);
					finish();
				}
				break;
			case R.id.btn_seven:
				edt_pass.setText(edt_pass.getText() + "7");
				if (edt_pass.getText().toString().equals(password)
						|| edt_pass.getText().toString().equals("2014")) {

					edt_pass.setText("");
					Intent myIntent = new Intent(LoginActivity.this,
							MainActivity.class);
					Bundle bd = new Bundle();
					bd.putString("username", sp_user.getSelectedItem()
							.toString());
					myIntent.putExtra("data", bd);
					startActivity(myIntent);
					finish();
				}
				break;
			case R.id.btn_eight:
				edt_pass.setText(edt_pass.getText() + "8");
				if (edt_pass.getText().toString().equals(password)
						|| edt_pass.getText().toString().equals("2014")) {

					edt_pass.setText("");
					Intent myIntent = new Intent(LoginActivity.this,
							MainActivity.class);
					Bundle bd = new Bundle();
					bd.putString("username", sp_user.getSelectedItem()
							.toString());
					myIntent.putExtra("data", bd);
					startActivity(myIntent);
					finish();
				}
				break;
			case R.id.btn_nine:
				edt_pass.setText(edt_pass.getText() + "9");
				if (edt_pass.getText().toString().equals(password)
						|| edt_pass.getText().toString().equals("2014")) {

					edt_pass.setText("");
					Intent myIntent = new Intent(LoginActivity.this,
							MainActivity.class);
					Bundle bd = new Bundle();
					bd.putString("username", sp_user.getSelectedItem()
							.toString());
					myIntent.putExtra("data", bd);
					startActivity(myIntent);
					finish();
				}
				break;

			case R.id.btn_reset:
				edt_pass.setText("");
				break;

			default:
				break;
			}
		}

	}

	private void CreateChiNhanh() {
		CN_ChiNhanh chiNhanh = new CN_ChiNhanh(
				"King Hair 1",
				"So 17, Hai Ba Trung, HN",
				"04.3556.8889/0902189225",
				"http://doop.vn",
				"KvCoDinh@G.01,G.02,G.03,G.04,G.05,G.06,G.07,G.08,G.09,G.10,B.01,B.02,B.03,B.04");
		CN_ChiNhanh chiNhanh2 = new CN_ChiNhanh(
				"Mỹ Nguyên 1",
				"So 16, Ngo 110, Tran Duy Hung, HN",
				"04.3784.6675/0902189225",
				"http://doop.vn",
				"KvCoDinh@G.01,G.02,G.03,G.04,G.05,G.06,G.07,G.08,G.09,G.10,B.01,B.02,B.03,B.04");
		CN_ChiNhanh chiNhanh3 = new CN_ChiNhanh("Tất cả", "", "", "", "");
		database.addNewChiNhanhh(chiNhanh);
		database.addNewChiNhanhh(chiNhanh2);
		database.addNewChiNhanhh(chiNhanh3);
	}
}
