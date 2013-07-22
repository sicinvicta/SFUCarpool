package com.example.carpoolapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;


public class ViewTripDetailsActivity extends Activity {
	
	Button buttonSendSMS, buttonSendEmail, buttonCall;

	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_trip_details_layout);
        
        buttonSendSMS = (Button)findViewById(R.id.buttonSendSMS);
        buttonSendEmail = (Button)findViewById(R.id.buttonSendEmail);
        buttonCall = (Button)findViewById(R.id.buttonCall);
        
		buttonSendSMS.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
 
				try {
 
					String number = "12346556";  // The number on which you want to send SMS  
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)));
 
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),
						"SMS faild, please try again later!",
						Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			}
		});
		
		buttonSendEmail.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);  
				String aEmailList[] = { "jennifer.c.lui@gmail.com"};  
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);  ;  
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "SFU CARPOOL");  
				emailIntent.setType("plain/text");  
				//emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Message");  
				startActivity(emailIntent);
				
			}
		});
		
		buttonCall.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:6042668695"));
				startActivity(callIntent);
				
			}
		});
		
		
	}

}
