package com.example.carpoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MyTripsActivity extends Fragment {
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        View rootView = inflater.inflate(R.layout.my_trips_layout, container, false);
	     // Demonstration of a collection-browsing activity.
            rootView.findViewById(R.id.tableRow4)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), CreateNewTripActivity.class);
                            startActivity(intent);
                        }
                    });
	        
	        
	        return rootView;
	 }

}
