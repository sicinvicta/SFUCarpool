package com.example.carpoolapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class CreateNewTripActivity extends Activity {
	
	TextView textViewCampusStart, textViewCampusEnd;
	Button testDialogButton, testDialogButton2;
	TableRow rowCampusStart;
	
	public final static int alertDialogCampusStart = 1;
	public final static int alertDialogCampusEnd = 2;
	public final static int alertDialog = 3;
	
	CharSequence[] campusListRun = {"Burnaby", "Surrey", "Vancouver"};
	final CharSequence[] campusList = {"Burnaby", "Surrey", "Vancouver"};
	final CharSequence[] campusListB = {"Surrey", "Vancouver"};
	final CharSequence[] campusListS = {"Burnaby", "Vancouver"};
	final CharSequence[] campusListV = {"Burnaby", "Surrey",};

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_trip_layout);
        
        textViewCampusStart = (TextView) findViewById(R.id.textViewCreateTripCampusStart);
        textViewCampusEnd = (TextView) findViewById(R.id.textViewCreateTripCampusEnd);
        
        findViewById(R.id.tableRowCreateTripCampusStart)
        .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	CreateNewTripActivity.this.showDialog(alertDialogCampusStart);
            }
        });
        
        findViewById(R.id.tableRowCreateTripCampusEnd)
        .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	CreateNewTripActivity.this.showDialog(alertDialogCampusEnd);
            }
        });
        
        testDialogButton = (Button) findViewById(R.id.buttonDialogTest);
        testDialogButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				CreateNewTripActivity.this.showDialog(alertDialogCampusStart);
				
			}
		});
        
        testDialogButton2 = (Button) findViewById(R.id.buttonDialogTest2);
        testDialogButton2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				CreateNewTripActivity.this.showDialog(alertDialogCampusEnd);
				
			}
		});
        
	 }

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		switch(id) {
		case alertDialogCampusStart:
			builder.setTitle("Select Start Campus")
			.setItems(campusListRun, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int campusPicked) {
					Toast.makeText(CreateNewTripActivity.this, "CAMPUS SELECTED:" + campusListRun[campusPicked], Toast.LENGTH_SHORT).show();
					textViewCampusStart.setText(campusList[campusPicked]);
					if (campusList[campusPicked].length() == 7) {
						campusListRun = campusListB;
						
					}
					if (campusList[campusPicked].length() == 9) {
						campusListRun = campusListV;
					}
					if (campusList[campusPicked].length() == 6) {
						campusListRun = campusListS;
					}
					else {

					}
				}

			});
			dialog = builder.create();
			
			break;
		
		case alertDialogCampusEnd:
			builder.setTitle("Select End Campus")
			.setItems(campusListRun, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int campusPicked) {
					Toast.makeText(CreateNewTripActivity.this, "CAMPUS SELECTED:" + campusListRun[campusPicked], Toast.LENGTH_SHORT).show();
					textViewCampusEnd.setText(campusListRun[campusPicked]);
					if (campusList[campusPicked].length() == 7) {
						campusListRun = campusListB;
						
					}
					if (campusList[campusPicked].length() == 9) {
						campusListRun = campusListV;
					}
					if (campusList[campusPicked].length() == 6) {
						campusListRun = campusListS;
					}
					else {
						campusListRun = campusList;
					}
				}

			});
			dialog = builder.create();
			
			break;
		case alertDialog:
			builder.setMessage("THIS IS AN ALERT MESSAGE")
			.setPositiveButton("YES", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(CreateNewTripActivity.this, "Button Clicked YES YES", Toast.LENGTH_LONG).show();
					
				}
			})
			.setNegativeButton("NO", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(CreateNewTripActivity.this, "Button Clicked NO NO", Toast.LENGTH_LONG).show();
					dialog.cancel();
				}
			})
			.setCancelable(false);
			dialog = builder.create();
			
			break;
			
			
		default:
			
		}
		return dialog;
	}


}
