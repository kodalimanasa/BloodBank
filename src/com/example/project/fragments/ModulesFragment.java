package com.example.project.fragments;

import com.example.project.R;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ModulesFragment extends BaseFragment implements OnItemClickListener{
	String []strArray = {"DonorRegistration","ViewDonor","SearchDonor","More"};
	private ListView lv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.modules, null);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		lv = (ListView) getView().findViewById(R.id.listview);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,strArray);
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
		if(position == 0){
			 RegistrationFragment fragment = new RegistrationFragment();
		     ft.replace(R.id.tabletfragment, fragment).commit();
		}
		else if(position ==1){
				 ViewDonorsFragment fragment = new ViewDonorsFragment();
			     ft.replace(R.id.tabletfragment, fragment).commit();
			}
		else if(position ==2){
			 SearchDonorFragment fragment = new SearchDonorFragment();
		     ft.replace(R.id.tabletfragment, fragment).commit();
		}
		}
	

}
