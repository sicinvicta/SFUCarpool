package com.example.carpoolapp;


import com.example.carpoolapp.CarpoolDatabase.UserDescription;
import com.example.carpoolapp.CarpoolDatabase.UserType;
import com.example.carpoolapp.CarpoolDatabase.Users;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CarpoolDatabaseHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "myUsers.db";
	private static final int DATABASE_VERSION = 1; 

	public CarpoolDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create the Users table
		db.execSQL("CREATE TABLE " + Users.USERS_TABLE_NAME + " ("
		    + Users._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
		    + Users.USER_NAME + " TEXT,"
		    + Users.USER_TYPE_ID + " INTEGER" // this is a foreign key to the pet type table
		    + ");"); 
		
		// Create the PetType table
		db.execSQL("CREATE TABLE " + UserType.USERTYPE_TABLE_NAME+ " ("
		    + UserType._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
		    + UserType.USER_TYPE + " TEXT"
		    + ");");
		
		// Create the UserDescription table
		db.execSQL("CREATE TABLE " + UserDescription.USERDESCRIPTION_TABLE_NAME + " ("
			+ UserDescription._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
		    + UserDescription.USER_DESCRIPTION + " TEXT"
			+ ");");				
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}
}

