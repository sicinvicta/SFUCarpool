package com.example.carpoolapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class CreateNewTripActivity extends Activity {
	
	TextView textViewDate, textViewTime ,textViewCampusStart, textViewCampusEnd; 
	EditText editTextDetails;
	Button buttonCancel, buttonCreate;
	
	public final static int alertDialogCampusStart = 1;
	public final static int alertDialogCampusEnd = 2;
	public final static int alertDialog = 3;
	private final static int alertDialogDate = 4;
	
	CharSequence[] campusListRun = {"Burnaby", "Surrey", "Vancouver"};
	final CharSequence[] campusList = {"Burnaby", "Surrey", "Vancouver"};
	final CharSequence[] campusListB = {"Surrey", "Vancouver"};
	final CharSequence[] campusListS = {"Burnaby", "Vancouver"};
	final CharSequence[] campusListV = {"Burnaby", "Surrey"};
	
	private int year;
	private int month;
	private int day;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_trip_layout);
        
        buttonCreate = (Button) findViewById(R.id.buttonCreateTripCreate);
        buttonCancel = (Button) findViewById(R.id.buttonCreateTripCancel);
        
        textViewCampusStart = (TextView) findViewById(R.id.textViewCreateTripCampusStart);
        textViewCampusEnd = (TextView) findViewById(R.id.textViewCreateTripCampusEnd);
        textViewDate = (TextView) findViewById(R.id.textViewCreateTripPickDate);
        textViewTime = (TextView) findViewById(R.id.textViewCreateTripPickTime);
        
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	CreateNewTripActivity.this.showDialog(alertDialog);
            }
        });
        
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	CreateNewTripActivity.this.showDialog(alertDialog);
            }
        });
        textViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	CreateNewTripActivity.this.showDialog(alertDialogDate);
            }
        });
        
        textViewCampusStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	CreateNewTripActivity.this.showDialog(alertDialogCampusStart);
            }
        });
        
        textViewCampusEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	CreateNewTripActivity.this.showDialog(alertDialogCampusEnd);
            }
        });
       
        setCurrentDateOnView();
        
	 }
	
	public void setCurrentDateOnView() {
 
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMMM dd, yyyy");
		Date d = new Date();
		String currentdate = sdf.format(d);

		// set current date into textview
		textViewDate.setText(new StringBuilder()
			.append(currentdate));
			 
	}

	@SuppressLint("NewApi")
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
					textViewCampusStart.setText(campusListRun[campusPicked]);
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
		case alertDialogDate:
			// set date picker as current date
			   return new DatePickerDialog(this, datePickerListener, 
	                         year, month,day);
			
		}
		return dialog;
	}
	private DatePickerDialog.OnDateSetListener datePickerListener 
	    = new DatePickerDialog.OnDateSetListener() {
	
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
			int selectedMonth, int selectedDay) {
		year = selectedYear;
		month = selectedMonth;
		day = selectedDay;
		
		SimpleDateFormat simpledateformat = new SimpleDateFormat("EEE, MMMM dd");
	      Date date = new Date(year, month, day);
	      String fulldate = simpledateformat.format(date);
		
		// set selected date into textview
		Toast.makeText(CreateNewTripActivity.this, "DATE ENTERED", Toast.LENGTH_LONG).show();
		textViewDate.setText(new StringBuilder().append(fulldate).append(", ").append(year));
		
		}
	};

}
