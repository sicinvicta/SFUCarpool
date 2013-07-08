package com.example.carpoolapp;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class CreateTripActivity extends Fragment{
	
	EditText editTextCampusStart, editTextCampusEnd;
	Button testDialogButton;

	 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.create_trip_layout, container, false);

        

        return rootView;
	 }
	 public Dialog onCreateDialog(Bundle savedInstanceState) {
		 
		return null;
		    
		}

}
