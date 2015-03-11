package com.example.project.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyService extends IntentService{

	public MyService() {
		super("IntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i("NCS","in Onhandle Intent :"+intent.getStringExtra("URL"));
		try{
			URL url = new URL(intent.getStringExtra("URL"));
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Content-type","application/json");
			
			if(intent.getStringExtra("Screen")!=null && intent.getStringExtra("Screen").equalsIgnoreCase("Registration")){
				httpConnection.setRequestProperty("Authorization", "OAuth "+intent.getStringExtra("TOken"));
				//Body
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("Name",intent.getStringExtra("FN"));
				jsonObject.put("Age__c",intent.getStringExtra("Age"));
				jsonObject.put("BloodGroup__c",intent.getStringExtra("BGroup"));
				jsonObject.put("City__c",intent.getStringExtra("City"));
				jsonObject.put("Email__c",intent.getStringExtra("Email"));
				jsonObject.put("Gender__c",intent.getStringExtra("gender"));
				jsonObject.put("LastName__c",intent.getStringExtra("Ln"));
				jsonObject.put("MiddleName__c",intent.getStringExtra("MN"));
				jsonObject.put("Phone__c",intent.getStringExtra("Phno"));
				jsonObject.put("State__c",intent.getStringExtra("State"));
				jsonObject.put("ZipCode__c",intent.getStringExtra("Zip"));
				Log.i("NCS","JsonResponse: "+jsonObject.toString());	

				httpConnection.setUseCaches(false);
				httpConnection.setDoInput(true);
				httpConnection.setDoOutput(true);

				DataOutputStream wr = new DataOutputStream (httpConnection.getOutputStream ());
				wr.writeBytes (jsonObject.toString());
				wr.flush ();
				wr.close ();
				
				
			}
			
			if(intent.getStringExtra("Screen")!=null && intent.getStringExtra("Screen").equalsIgnoreCase("ViewDonorsFragment")){
				httpConnection.setRequestMethod("GET");
				httpConnection.setRequestProperty("Authorization", "OAuth "+intent.getStringExtra("TOken"));
			}
			if(intent.getStringExtra("Screen")!=null && intent.getStringExtra("Screen").equalsIgnoreCase("SearchDonorFragment")){
				httpConnection.setRequestMethod("GET");
				httpConnection.setRequestProperty("Authorization", "OAuth "+intent.getStringExtra("TOken"));
				Log.i("NCS","Token "+intent.getStringExtra("TOken"));
			}
			if(intent.getStringExtra("Screen")!=null && intent.getStringExtra("Screen").equalsIgnoreCase("DonorDetailsFragment")){
				httpConnection.setRequestMethod("GET");
				httpConnection.setRequestProperty("Authorization", "OAuth "+intent.getStringExtra("TOken"));
			}
			Log.i("NCS","Reespone code :"+httpConnection.getResponseCode());
			String response = convert_To_String(httpConnection.getInputStream());
			
			String action = intent.getStringExtra("Action");
			Intent i = new Intent(action);
			i.putExtra("RES", response);
			sendBroadcast(i);
		}catch(Exception ex){
			Log.i("NCS","Exception is  :"+ex.getMessage());
		}
		
		
	}
	
	private   String convert_To_String(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {

				sb.append(line + "\n");
				Log.i("TPT", "Line is  :" + line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {                 
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}     

}
