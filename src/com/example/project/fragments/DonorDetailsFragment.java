package com.example.project.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.TextView;

import com.example.project.R;

public class DonorDetailsFragment extends BaseFragment{
	
	private TextView tv,tv1,tv2,tv3,tv4;
	
	@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	return inflater.inflate(R.layout.donordetails, null);
}

	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	
	tv=(TextView) getView().findViewById(R.id.donorage);
	tv1=(TextView) getView().findViewById(R.id.bloodgroup);
	tv2=(TextView) getView().findViewById(R.id.donorgender);
	tv3=(TextView) getView().findViewById(R.id.Name);
	tv4=(TextView) getView().findViewById(R.id.phonenumber);
	
			Bundle bundle= getArguments();
	tv.setText("Age  :"+bundle.getString("AGE"));
tv1.setText("Bloodgroup  :"+bundle.getString("BG"));
	
	tv2.setText("Gender :"+bundle.getString("GENDER"));
	tv3.setText("Name  :"+bundle.getString("NAME"));
	tv4.setText("Phonenum  :"+bundle.getString("PHONE"));
	
	
	
		}

}

		 


