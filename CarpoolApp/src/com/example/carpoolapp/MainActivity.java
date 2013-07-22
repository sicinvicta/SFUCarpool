package com.example.carpoolapp;


import com.example.carpoolapp.CarpoolDatabaseHelper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;



@SuppressLint("NewApi")
public class MainActivity extends Activity {

	protected CarpoolDatabaseHelper myDatabase = null; 
	protected Cursor myCursor = null;
	protected SQLiteDatabase myDB = null;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//create a member variable for our Plants database: 
		myDatabase = new CarpoolDatabaseHelper(this.getApplicationContext());
		
		//request a valid database object where we can write to:
		myDB = myDatabase.getWritableDatabase();
		
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		if(myDB != null)
		{
			myDB.close();
		}
		
		if(myDatabase != null)
		{
			myDatabase.close();
		}
	}
	
	

}