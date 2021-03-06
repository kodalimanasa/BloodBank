package com.example.project.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.service.MyService;


public class RegistrationFragment extends BaseFragment {
	private EditText etFirstName,etMiddleName,etLastname,etAge,etPhno,etEmailId,etState,etCity,etZipCode;
	private Spinner spBloodGroup;
	private RadioGroup rg;
	private Button btSubmit; 
	private MyReceiver mReceiver;
	private final String ACTION ="Registration";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view= inflater.inflate(R.layout.registration,null);
		return view;	
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onActivityCreated(savedInstanceState);
		mReceiver = new MyReceiver();
		
		etFirstName = (EditText) getView().findViewById(R.id.firstname);
		etMiddleName = (EditText) getView().findViewById(R.id.middlename);
		etLastname = (EditText) getView().findViewById(R.id.lastname);
		etAge = (EditText) getView().findViewById(R.id.age);
		etPhno = (EditText) getView().findViewById(R.id.Phone);
		etEmailId = (EditText) getView().findViewById(R.id.emailid);
		etState = (EditText) getView().findViewById(R.id.state);
		etCity = (EditText) getView().findViewById(R.id.city);
		etZipCode = (EditText) getView().findViewById(R.id.zipcode);
		
		rg = (RadioGroup) getView().findViewById(R.id.gender);
		
		spBloodGroup = (Spinner) getView().findViewById(R.id.bgroup);
		
		btSubmit = (Button) getView().findViewById(R.id.submit);
		
		btSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				mDialog.show();
				registerDonor();
				ClearList();
				
		}
	
	});

}
	
	
	private void registerDonor(){
		
		String fn = etFirstName.getText().toString();
		String ln = etLastname.getText().toString();
		String mn = etMiddleName.getText().toString();
		String age = etAge.getText().toString();
		String phone = etPhno.getText().toString();
		String email = etEmailId.getText().toString();
		String state = etState.getText().toString();
		String city = etCity.getText().toString();
		String zipcode = etZipCode.getText().toString();
		
		String bgroup = spBloodGroup.getSelectedItem().toString();
		
		String gender = null;
		
		if(rg.getCheckedRadioButtonId() == R.id.male){
			gender ="Male";
		}else{
			gender ="FeMale";
	}

		String instanceUrl = mPreferances.getString("Instance", "");
		String registrationUrl = instanceUrl+"/services/data/v29.0/sobjects/DonarRegistration__c";
		Intent intent = new Intent(getActivity(),MyService.class);
		intent.putExtra("Action", ACTION);
		intent.putExtra("URL", registrationUrl);
		intent.putExtra("FN", fn);
		intent.putExtra("Ln", ln);
		intent.putExtra("MN", mn);
		intent.putExtra("Age", age);
		intent.putExtra("Phno", phone);
		intent.putExtra("BGroup", bgroup);
		intent.putExtra("State", state);
		intent.putExtra("City", city);
		intent.putExtra("Email", email);
		intent.putExtra("gender", gender);
		intent.putExtra("Zip", zipcode);
		intent.putExtra("TOken", mPreferances.getString("Access", ""));
		intent.putExtra("Screen", "Registration");
	    getActivity().startService(intent);
	}
	
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		getActivity().registerReceiver(mReceiver, new IntentFilter(ACTION));
	}
	
	@Override
	public void onDestroy() {
		getActivity().unregisterReceiver(mReceiver);
		super.onDestroy();
	}
	public void ClearList(){
		etFirstName.setText("");
		etMiddleName.setText("");
		etLastname .setText("");
		etAge.setText("");
		etPhno.setText("");
		etEmailId.setText("");
		etState.setText("");
		etCity.setText("");
		etZipCode.setText("");
		rg.clearCheck();
		spBloodGroup.clearFocus();
		
	}
	
	
	class MyReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			
			mDialog.dismiss();
			Toast.makeText(getActivity(), "Successfully Registered", 1000).show();
			
			
		}
		
		
	}
	
}
