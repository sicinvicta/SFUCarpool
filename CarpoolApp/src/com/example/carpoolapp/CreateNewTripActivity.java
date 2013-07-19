package com.example.carpoolapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

@SuppressLint("NewApi")
public class CreateNewTripActivity extends Activity {
	
	TextView textViewDate, textViewTime ,textViewCampusStart, textViewCampusEnd; 
	EditText editTextDetails;
	Button buttonCancel, buttonCreate;
	
	CharSequence[] campusListRunS = {"Burnaby", "Surrey", "Vancouver"};
	CharSequence[] campusListRunE = {"Burnaby", "Surrey", "Vancouver"};
	final CharSequence[] campusList = {"Burnaby", "Surrey", "Vancouver"};
	final CharSequence[] campusListB = {"Surrey", "Vancouver"};
	final CharSequence[] campusListS = {"Burnaby", "Vancouver"};
	final CharSequence[] campusListV = {"Burnaby", "Surrey"};
	
	public final static int alertDialogCampusStart = 1;
	public final static int alertDialogCampusEnd = 2;
	public final static int alertDialog = 3;
	private final static int alertDialogDate = 4;
	static final int alertDialogTime = 5;
	
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	
	public final String TAG = "CreateNewPostActivity";
	
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_create_post, menu);
		return true;
	}
  
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
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
        
        textViewTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	CreateNewTripActivity.this.showDialog(alertDialogTime);
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
        setCurrentTimeOnView();
        
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
	
	public void setCurrentTimeOnView() {
 
		final Calendar c = Calendar.getInstance();
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
		
		// reformat 24 hours time to 12 hours time with AM PM
		updateTime(hour,minute);

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
			.setItems(campusListRunS, new DialogInterface.OnClickListener() {
				
				 
				@Override
				public void onClick(DialogInterface dialog, int campusPicked) {
					textViewCampusStart.setText(campusListRunS[campusPicked]);
					updateCampusListS(campusPicked);
				}
			});
			
			dialog = builder.create();
			break;
		
		case alertDialogCampusEnd:
			builder.setTitle("Select End Campus")
			.setItems(campusListRunE, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int campusPicked) {
					textViewCampusEnd.setText(campusListRunE[campusPicked]);
					updateCampusListE(campusPicked);
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
			   return new DatePickerDialog(this, datePickerListener, year, month,day);
		case alertDialogTime:
			// set time picker as current time
			return new TimePickerDialog(this, timePickerListener, hour, minute,false);
			
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
		
		SimpleDateFormat simpledateformat = new SimpleDateFormat("EEE, MMMM");
	      Date date = new Date(year, month, day-1);
	      String fulldate = simpledateformat.format(date);
		
		// set selected date into textview
		textViewDate.setText(new StringBuilder().append(fulldate).append(day).append(", ").append(year));
		
		}
	};
	
	private TimePickerDialog.OnTimeSetListener timePickerListener = 
            new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int selectedHour,
				int selectedMinute) {
			hour = selectedHour;
			minute = selectedMinute;
			
			updateTime(hour,minute);
 
		}
	};
	
	private static String pad(int c) {
		if (c >= 10)
		   return String.valueOf(c);
		else
		   return "0" + String.valueOf(c);
	}
	
	private void updateTime(int hours, int mins) {
        
        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";
 
         
        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);
 
        // Append in a StringBuilder
         String aTime = new StringBuilder().append(hours).append(':')
                .append(minutes).append(" ").append(timeSet).toString();
 
      // set update time into textview
      	textViewTime.setText(aTime);
    }
	
	private void updateCampusListS(int campusPicked ) {

		if (campusListRunS[campusPicked].equals(campusList[0])){
			
			campusListRunE = campusListB;
		}
		else if (campusListRunS[campusPicked].equals(campusList[1])){
			
			campusListRunE = campusListS;
		}
		else {
			
			campusListRunE = campusListV;
		}
		
	}
	private void updateCampusListE(int campusPicked ) {

		if (campusListRunE[campusPicked].equals(campusList[0])){
			
			campusListRunS = campusListB;
		}
		else if (campusListRunE[campusPicked].equals(campusList[1])){
			
			campusListRunS = campusListS;
		}
		else {
			
			campusListRunS = campusListV;
		}
		
	}
	
}
