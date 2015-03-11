package com.example.project.fragments;

import com.example.project.R;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseFragment extends Fragment{
	protected ProgressDialog mDialog;
	protected SharedPreferences mPreferances;
	private final String PREFERANCE ="MyApp";
	protected AlertDialog.Builder Adialog;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	public boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager  = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	     return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	    }
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mPreferances = getActivity().getSharedPreferences(PREFERANCE, 0);
		mDialog = new ProgressDialog(getActivity());
		mDialog.setTitle(getResources().getString(R.string.app_name));
		mDialog.setMessage(getResources().getString(R.string.message));
		Adialog=new AlertDialog.Builder(getActivity());
		Adialog.setTitle("network info");
		Adialog.setMessage("No Network Connection");
		Adialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				
			}
		});
		
	}
	public void showalertdialog()
	{
		Adialog.show();
	}

}
