package com.buscience.activities;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.buscience.fragments.ETScheduleFragment;
import com.buscience.fragments.ETSignupFragment;
import com.buscience.fragments.ETUpdateFragment;
import com.buscience.slidingmenu.NavDrawerItem;
import com.buscience.slidingmenu.NavDrawerListAdapter;

public class ElemTeacherRegActivity extends Activity
{
	
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
 
    // nav drawer title
    private CharSequence mDrawerTitle;
 
    // used to store app title
    private CharSequence mTitle;
 
    // slide menu items
    private TypedArray navMenuIcons;
 
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
    
    private int currentPosition;
    Fragment fragment;
 
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elem_teacher_reg_layout);
 
        mTitle = mDrawerTitle = "Elementary Teacher Registration";
 
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_reg1);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu_reg1);
        
 
        navDrawerItems = new ArrayList<NavDrawerItem>();
 
        // adding nav drawer items to array
        // Sign-up
        navDrawerItems.add(new NavDrawerItem("\nSign-Up\n"));
        // Update
        navDrawerItems.add(new NavDrawerItem("\nUpdate\n"));
        // CurrentSchedule
        navDrawerItems.add(new NavDrawerItem("\nCurrent Schedule\n"));
 
        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);
 
        // enabling action bar app icon and behaving it as toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        if ( Build.VERSION.SDK_INT >= 14)
        {
        	getActionBar().setHomeButtonEnabled(true);
        }
 
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ){
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
                
                
            }
 
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
 
        if (savedInstanceState == null) {
            // on first time display view for first nav item
        	fragment = new ETSignupFragment();
        	
        	FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            //ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left);
            
            ft.replace(R.id.reg_fragment_container_one, fragment).commit();
            
            mDrawerLayout.closeDrawer(mDrawerList);
            
            currentPosition = 0;
            
            //Log.e("Error", "I'm in");
            
        }
        //Log.e("Error", "I'm out");
        
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
        case R.id.action_settings:
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
 
    /***
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        menu.findItem(R.id.help_item).setVisible(!drawerOpen);
        menu.findItem(R.id.refresh_item).setVisible(!drawerOpen);
        menu.findItem(R.id.user_item).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
 
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }
 
    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
 
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
 
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    // When refresh is clicked, create a new fragment that is the same as the currently running
    @SuppressLint("NewApi")
	public boolean onRefreshClicked(MenuItem item)
    {
    	switch (currentPosition) {
        case 0:
            fragment = new ETSignupFragment();
            break;
        case 1:
            fragment = new ETUpdateFragment();
            break;
        case 2:
            fragment = new ETScheduleFragment();
            break;
 
        default:
            break;
        }
    	
        FragmentManager fragmentManager = getFragmentManager();
    	FragmentTransaction ft = fragmentManager.beginTransaction();
        if ( Build.VERSION.SDK_INT >= 13)
        {
        	ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left);
        }
        
        ft.replace(R.id.reg_fragment_container_one, fragment).commit();  
        
    	return true;
    }
    
    //When is is clicked, create an AlertDialog with message
    public boolean onHelpClicked(MenuItem item)
    {    	    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	// Add the buttons
    	builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   
    	           }
    	       });
    	
    	builder.setMessage("Filler message. To be edited");
    	// Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    	return true;
    	
    	
    }
    
    
  //Used by fragment objects to update position information
    public void setCurrentPosition(int position)
    {
    	currentPosition = position;
    }
    
    public ListView getMDrawerList()
    {
    	return mDrawerList;
    	
    }
    
	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// display view for selected nav drawer item
        	if ( currentPosition != position )
        	{
        		displayView(position);
        	}
		}
	}
	
	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	@SuppressLint("NewApi")
	private void displayView(int position) {
		// update the main content by replacing fragments

		switch (position) {
		case 0:
			fragment = new ETSignupFragment();
			break;
		case 1:
			fragment = new ETUpdateFragment();
			break;
		case 2:
			fragment = new ETScheduleFragment();
			break;

		default:
			break;
		}

		if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if ( Build.VERSION.SDK_INT >= 13)
            {
            	ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left);
            }
            
            ft.replace(R.id.reg_fragment_container_one, fragment).commit();
 
            // update selected item and title, then close the drawer
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("ElemTeacherRegActivity", "Error in creating fragment");
		}
	}
}
