package com.example.project.fragments;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.project.R;
import com.example.project.adapter.DonorAdapter;
import com.example.project.model.DonorModel;
import com.example.project.service.MyService;

public class ViewDonorsFragment extends BaseFragment implements OnItemClickListener{
	private ListView lv;
	private MyReceiver mReceiver;
	private final String ACTION ="ViewDonor";
	private ArrayList<DonorModel> listOfDonors ;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.viewdonorfragment, null);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		lv = (ListView) getView().findViewById(R.id.listview);
		lv.setOnItemClickListener(this);
		mReceiver = new MyReceiver();
		String instanceUrl = mPreferances.getString("Instance", "");
		String retriveDonorList = instanceUrl+"/services/data/v29.0/query/?q=select+Name,Age__c,BloodGroup__c,City__c,Email__c,Gender__c,LastName__c,MiddleName__c,Phone__c,State__c,ZipCode__c+from+DonarRegistration__c";
		Log.i("NCS","URL is :"+retriveDonorList);
		Intent intent = new Intent(getActivity(), MyService.class);
		intent.putExtra("URL", retriveDonorList);
		intent.putExtra("Action", ACTION);
		intent.putExtra("TOken", mPreferances.getString("Access", ""));
		intent.putExtra("Screen", "ViewDonorsFragment");
		getActivity().startService(intent);
		
		
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		getActivity().registerReceiver(mReceiver, new IntentFilter(ACTION));
		super.onResume();
	}
	
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		getActivity().unregisterReceiver(mReceiver);
		super.onDestroy();
	}
	
	class MyReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			String respons = intent.getStringExtra("RES");
			try{
				listOfDonors = new ArrayList<DonorModel>();
				JSONObject jObject = new JSONObject(respons);
				JSONArray records = jObject.getJSONArray("records");
				int count = records.length();
				for(int i = 0;i<count;i++){
					JSONObject jObjet2 = records.getJSONObject(i);
					DonorModel model = new DonorModel();
					model.setName(jObjet2.getString("Name"));
					model.setAge(jObjet2.getString("Age__c"));
					model.setBloodgroup(jObjet2.getString("BloodGroup__c"));
					model.setGender(jObjet2.getString("Gender__c"));
					model.setLastname(jObjet2.getString("LastName__c"));
					model.setPhone(jObjet2.getString("Phone__c"));
					listOfDonors.add(model);
					DonorAdapter adapter = new DonorAdapter(getActivity(),listOfDonors);
					lv.setAdapter(adapter);
				}
				
			}catch(Exception ex){
				
			}
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
		DonorModel model=listOfDonors.get(position);
		Bundle bundle=new Bundle();
		bundle.putString("AGE", model.getAge());
		bundle.putString("BG", model.getBloodgroup());
		bundle.putString("GENDER", model.getGender());
		bundle.putString("LASTNAME", model.getLastname());
		bundle.putString("NAME", model.getName());
		bundle.putString("PHONE", model.getPhone());
	
		DonorDetailsFragment ddf=new DonorDetailsFragment();
		ddf.setArguments(bundle);
		FragmentManager fm=getFragmentManager();
		FragmentTransaction ft=fm.beginTransaction();
		ft.addToBackStack(null);
		ft.replace(R.id.fragment,ddf).commit();

		// TODO Auto-generated method stub

	}
	
	

}
