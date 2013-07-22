package com.example.carpoolapp;

import com.example.carpoolapp.CarpoolDatabase.UserType;
import com.example.carpoolapp.CarpoolDatabase.Users;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class MyUserEntryActivity extends MainActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userentry);
	
		// Fill AutoComplete word list from database 
		fillAutoCompleteFromDatabase();
	
		// Handle Save Button
		final Button savePet = (Button) findViewById(R.id.ButtonSave);
		savePet.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				final EditText userNameF = (EditText) findViewById(R.id.EditTextNameF);
				final EditText userNameL = (EditText) findViewById(R.id.EditTextNameL);
				final EditText userEmail = (EditText) findViewById(R.id.EditTextSpecies);

				// Save new records
				myDB.beginTransaction();
				try {

					// check if species type exists already
					long rowId = 0;
					String strUserType = userEmail.getText().toString()
							.toLowerCase();

					// SQL Query
					SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
					queryBuilder.setTables(UserType.USERTYPE_TABLE_NAME);
					queryBuilder.appendWhere(UserType.USER_TYPE + "='"
							+ strUserType + "'");

					// run the query 
					Cursor c = queryBuilder.query(myDB, null, null, null, null,
							null, null);

					if (c.getCount() == 0) {
						// add the new type to our list
						ContentValues typeRecordToAdd = new ContentValues();
						typeRecordToAdd.put(UserType.USER_TYPE, strUserType);
						rowId = myDB.insert(UserType.USERTYPE_TABLE_NAME,
								UserType.USER_TYPE, typeRecordToAdd);
						
						// Update autocomplete with new record
						fillAutoCompleteFromDatabase();						
						
					} else {
						c.moveToFirst();
						rowId = c.getLong(c.getColumnIndex(UserType._ID));
					}

					c.close();
					
					String strUserNameF = userNameF.getText().toString();

					// Always insert new pet records, even if the names clash
					ContentValues userRecordToAdd = new ContentValues();
					userRecordToAdd.put(Users.USER_NAME, strUserNameF);
					userRecordToAdd.put(Users.USER_TYPE_ID, rowId);
					myDB.insert(Users.USERS_TABLE_NAME, Users.USER_NAME,
							userRecordToAdd);

					myDB.setTransactionSuccessful();
				} finally {
					myDB.endTransaction();
				}

				// reset form
				userNameF.setText(null);
				userNameL.setText(null);
				userEmail.setText(null);
			}
		});

		// Handle Go to List button
		final Button gotoList = (Button) findViewById(R.id.ButtonShowUsers);
		gotoList.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// Go to other activity that displays pet list
				Intent intent = new Intent(MyUserEntryActivity.this, MyUserListActivity.class);
				startActivity(intent);
			}
		});

	}
	
	void fillAutoCompleteFromDatabase()
	{
		myCursor = myDB.query(UserType.USERTYPE_TABLE_NAME, new String[] {UserType.USER_TYPE, UserType._ID}, null, null,
				null, null, UserType.DEFAULT_SORT_ORDER);

		// Have the Activity manage the cursor for us 
		startManagingCursor(myCursor);
		
		// this method is not using database data-binding, instead, we spin through the Cursor and make an Array Adapter
		int iNumberOfTypes = myCursor.getCount();
		String astrAutoTextOptions[] = new String[iNumberOfTypes];
		if((iNumberOfTypes > 0) && (myCursor.moveToFirst()))
		{
			for(int i = 0; i < iNumberOfTypes; i++)
			{
				astrAutoTextOptions[i] = myCursor.getString(myCursor.getColumnIndex(UserType.USER_TYPE));
				myCursor.moveToNext();
			}

			ArrayAdapter<String> adapter =
		        new ArrayAdapter<String>(
		            this,
		            android.R.layout.simple_dropdown_item_1line,
		            astrAutoTextOptions);

			AutoCompleteTextView text = (AutoCompleteTextView) findViewById(R.id.EditTextSpecies);
			text.setAdapter(adapter);
		}
		
	}}
	


