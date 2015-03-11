package com.example.project.fragments;


import org.json.JSONObject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.project.R;
import com.example.project.RegistrationActivity;
import com.example.project.service.MyService;

public class Loginfragment extends BaseFragment{
	private EditText et1,et2;
	private Button bt1,bt2,bt3;
	private MyReceiver mReceiver;
	private final String ACTIOn ="LoginFragment";
	private Editor mEditor;
	
	@Override


	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view= inflater.inflate(R.layout.login,null);
		return view;	

	}
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);          
		bt1 = (Button) getView().findViewById(R.id.bt1);
		bt2 = (Button) getView().findViewById(R.id.bt2);
		bt3 = (Button) getView().findViewById(R.id.b3);
		et1=(EditText) getView().findViewById(R.id.editText1);
		et2=(EditText) getView().findViewById(R.id.editText2);
		
		
		
		mReceiver = new MyReceiver();
		
		et1.setText(mPreferances.getString("User", ""));

		bt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(isNetworkAvailable()){ 
					
					mDialog.show();

					String user = et1.getText().toString();
					String password = et2.getText().toString();
					String loginUrl = "https://login.salesforce.com/services/oauth2/token?grant_type=password&" +
					"client_id=3MVG9Y6d_Btp4xp6aEkEMnRIQkoIjfP3MEejXf0gJWU7PONoICDPZcxWTkLzNGi8xo5eFVJd_ZrehVPgfjIST&" +
					"client_secret=3951004059433787576&username="+user+"&password="+password+"MYbAx88L8wtfXpIaTpi8bqvnp";
					
					Intent i = new Intent(getActivity(), MyService.class);
					i.putExtra("Action", ACTIOn);
					i.putExtra("URL", loginUrl);
					getActivity().startService(i);
					}
				
				else{
					showalertdialog();
}
			}
		});

		bt2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				et1.setText("");
				et2.setText("");
			}
		});
		bt3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {


				

			}
		});

	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		getActivity().registerReceiver(mReceiver, new IntentFilter(ACTIOn));
	}
	

	class MyReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			Log.i("NCS","Response is   :"+arg1.getStringExtra("RES"));
			mDialog.dismiss();
			try{
				JSONObject jObjct = new JSONObject(arg1.getStringExtra("RES"));
				String accesstoken = jObjct.getString("access_token");
				String instanceUrl = jObjct.getString("instance_url");
				
				mEditor = mPreferances.edit();
				mEditor.putString("Access", accesstoken);
				mEditor.putString("Instance", instanceUrl);
				mEditor.putString("User", et1.getText().toString());
				mEditor.commit();
				
				startActivity(new Intent(getActivity(),RegistrationActivity.class));
				/*
				 * do start navigation drawer
				 */
				
				}catch(Exception ex){
					
				}
		}
		
		
		
	}
}
