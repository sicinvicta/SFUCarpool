package com.example.carpoolapp;


import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

    AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    ViewPager mViewPager;
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
    	case R.id.menuItemCreatePost:
    		Toast.makeText(MainActivity.this,"CREATE Button Clicked", Toast.LENGTH_SHORT).show();
    		Intent intent = new Intent(MainActivity.this, CreateNewTripActivity.class);
            startActivity(intent);
    		return true;
    	
    	case R.id.menuItemSettings:
    		Toast.makeText(MainActivity.this, "SETTINGS Button Clicked", Toast.LENGTH_SHORT).show();
    		return true;
    		
    	case R.id.menuItemManagePosts:
    		Toast.makeText(MainActivity.this, "SETTINGS Button Clicked", Toast.LENGTH_SHORT).show();
            return true;
            
        default:
        	return super.onOptionsItemSelected(item);
		}
	}

    
    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the three primary sections
        // of the app
        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();

        // Specify that the Home/Up button should not be enabled, since there is no hierarchical
        // parent.
        actionBar.setHomeButtonEnabled(false);

        // Specify that we will be displaying tabs in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Set up the ViewPager, attaching the adapter and setting up a listener for when the
        // user swipes between sections.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between different app sections, select the corresponding tab.
                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
                // Tab.
                actionBar.setSelectedNavigationItem(position);
            }
        });

            actionBar.addTab(
                    actionBar.newTab()
                            .setText("My Trips")
                            .setTabListener(this));
            
            actionBar.addTab(
                    actionBar.newTab()
                            .setText("View Trips")
                            .setTabListener(this));
            
            actionBar.addTab(
                    actionBar.newTab()
                            .setText("Create Trip")
                            .setTabListener(this));
		            
      
    }

	@Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the application.
     */
    public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

        public AppSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    // The first section of the app is the most interesting -- it offers
                    // a launchpad into the other demonstrations in this example application.
                    return new MyTripsActivity();
                    
                case 1:
                	return new ViewTripsActivity();

                default:
                    return new CreateTripActivity();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

    }
}




/* import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;

import android.widget.TabHost;

 public class MainActivity extends TabActivity {

	private TabHost mTabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mTabHost = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;
		
		// My Trip Tab
		intent = new Intent().setClass(this, MyTripsActivity.class);
		spec = mTabHost.newTabSpec("myTrips")
				.setIndicator("My Trips")
				.setContent(intent);
		mTabHost.addTab(spec);
		
		// View Trips Tab
		intent = new Intent().setClass(this, ViewTripsActivity.class);
		spec = mTabHost.newTabSpec("myTrips")
				.setIndicator("View Trip Posts")
				.setContent(intent);
		mTabHost.addTab(spec);
			
		// Create Trip Tab
		intent = new Intent().setClass(this, CreateTripActivity.class);
		spec = mTabHost.newTabSpec("myTrips")
				.setIndicator("Create Trip")
				.setContent(intent);
		mTabHost.addTab(spec);
		
		// set tab to visible when app starts according to tab number 0 1 or 2
		mTabHost.setCurrentTab(0);
	}

} */

