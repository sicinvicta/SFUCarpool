package com.example.carpoolapp;

import android.provider.BaseColumns;

public final class CarpoolDatabase {
	// this is a class that encapsulates the database schema 

	private CarpoolDatabase() {}
	
	public static final class Users implements BaseColumns
    //name of the user
	{
		private Users() {}
		public static final String USERS_TABLE_NAME = "users_table";
		public static final String USER_NAME = "user_name";
		public static final String USER_TYPE_ID = "user_type_id";  //primary key
		public static final String DEFAULT_SORT_ORDER = "user_name ASC";				
	}
	
	public static final class UserType implements BaseColumns
	//type of the users
	{
		private UserType() {}
		public static final String USERTYPE_TABLE_NAME = "userType_table";
		public static final String USER_TYPE = "user_type";
		public static final String DEFAULT_SORT_ORDER = "user_type ASC";				
	}
	
	public static final class UserDescription implements BaseColumns
	//description of the users
	{
		private UserDescription() {}
		public static final String USERDESCRIPTION_TABLE_NAME = "userDescription_table";
		public static final String USER_DESCRIPTION = "user_description";
		public static final String DEFAULT_SORT_ORDER = "user_description ASC";				
	}

}
