package com.example.project.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.model.DonorModel;

public class DonorAdapter extends BaseAdapter{
	private Context mContext;
	private ArrayList<DonorModel> list;

	public DonorAdapter(Context context, ArrayList<DonorModel> listOfDonors) {
		// TODO Auto-generated constructor stub
		mContext = context;
		list = listOfDonors;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View v, ViewGroup arg2) {
		
		if(v == null){
			
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(android.R.layout.simple_list_item_2, null);
			TextView name = (TextView) v.findViewById(android.R.id.text1);
			TextView phone = (TextView) v.findViewById(android.R.id.text2);
			
			DonorModel model = list.get(position);
			name.setText(model.getName());
			phone.setText(model.getPhone());
		}
		// TODO Auto-generated method stub
		return v;
	}
 
}
