package com.example.maybanhang;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class HD_MyArrayAdapter extends ArrayAdapter<DMDV_DanhMucDV>{
	
	private Activity activity;
	private ArrayList<DMDV_DanhMucDV> arrL_dmdv;
	private int layoutID;
	private TextView tv_lv_hd_nameDM, tv_lv_hd_giacaDM, tv_lv_hd_madm;

	public HD_MyArrayAdapter(Activity activity, int layoutID, ArrayList<DMDV_DanhMucDV> arrL_dmdv) {
		super(activity, layoutID, arrL_dmdv);
		// TODO Auto-generated constructor stub
		
		this.activity = activity;
		this.layoutID = layoutID;
		this.arrL_dmdv = arrL_dmdv;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		convertView = activity.getLayoutInflater().inflate(layoutID, null);
		tv_lv_hd_nameDM = (TextView)convertView.findViewById(R.id.tv_lv_hd_nameDM);
		tv_lv_hd_giacaDM = (TextView)convertView.findViewById(R.id.tv_lv_hd_giacaDM);
		tv_lv_hd_madm = (TextView)convertView.findViewById(R.id.tv_lv_hd_madm);
		
		tv_lv_hd_nameDM.setText(arrL_dmdv.get(position).getTenDV());		
		tv_lv_hd_giacaDM.setText(arrL_dmdv.get(position).getDonGia());
		tv_lv_hd_madm.setText(arrL_dmdv.get(position).getMaDV());
		return convertView;
		
	}

}
