package com.example.carpoolapp;

import com.example.carpoolapp.CarpoolDatabase.UserType;
import com.example.carpoolapp.CarpoolDatabase.Users;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MyUserListActivity extends MainActivity {

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {

	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.showusers);

	        // Fill ListView from database
	        fillUserList();

	        // Handle Go enter more pets button
	        final Button gotoEntry = (Button) findViewById(R.id.ButtonEnterMoreUsers);
	        gotoEntry.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {

	                // We're done here. Finish and return to the entry screen
	                finish();
	            }
	        });
	    }

	    @SuppressWarnings("deprecation")
		public void fillUserList() {
	        // SQL Query
	        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
	        queryBuilder.setTables(Users.USERS_TABLE_NAME + ", "
	                + UserType.USERTYPE_TABLE_NAME);
	        
	        queryBuilder.appendWhere(Users.USERS_TABLE_NAME + "." + Users.USER_TYPE_ID
	                + "=" + UserType.USERTYPE_TABLE_NAME + "." + UserType._ID);

	        // Get the database and run the query
	        String asColumnsToReturn[] = {
	                Users.USERS_TABLE_NAME + "." + Users.USER_NAME,
	                Users.USERS_TABLE_NAME + "." + Users._ID,
	                UserType.USERTYPE_TABLE_NAME + "." + UserType.USER_TYPE };
	        
	        myCursor = queryBuilder.query(myDB, asColumnsToReturn, null, null, null,
	                null, Users.DEFAULT_SORT_ORDER);

	        // Use an adapter to bind the data to a ListView, where each item is
	        // shown as a pet_item layout
	        startManagingCursor(myCursor);
	        ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.user_item,
	                myCursor, new String[] { Users._ID, Users.USER_NAME, UserType.USER_TYPE },
	                new int[] { R.id.TextView_UserID, R.id.TextView_UserName, R.id.TextView_UserType });

	        ListView av = (ListView) findViewById(R.id.userList);
	        av.setAdapter(adapter);

	        av.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	            public void onItemClick(AdapterView<?> parent, View view,
	                    int position, long id) {
	                // Check for delete button
	                final long deleteUserId = id;

	                RelativeLayout item = (RelativeLayout) view;
	                TextView nameView = (TextView) item
	                        .findViewById(R.id.TextView_UserName);
	                String name = nameView.getText().toString();
	                // Use an Alert dialog to confirm delete operation
	                new AlertDialog.Builder(MyUserListActivity.this)
	                        .setMessage("Delete User Record for " + name + "?")
	                        .setPositiveButton("Delete",
	                                new DialogInterface.OnClickListener() {
	                                    public void onClick(DialogInterface dialog,
	                                            int which) {

	                                        // Delete that pet
	                                        deleteUser(deleteUserId);

	                                        // Refresh the data in our cursor and
	                                        // therefore our List
	                                        myCursor.requery();
	                                    }
	                                }).show();
	            }
	        });
	    }

	    public void deleteUser(Long id) {
	        String astrArgs[] = { id.toString() };
	        myDB.delete(Users.USERS_TABLE_NAME, Users._ID + "=?", astrArgs);
	    }

	}


