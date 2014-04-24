package com.buscience.activities;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.AsyncTask;
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
import android.widget.PopupWindow;

import com.buscience.fragments.CertificationFragment;
import com.buscience.fragments.ContactFragment;
import com.buscience.fragments.EvaluationFragment;
import com.buscience.fragments.HomeFragment;
import com.buscience.fragments.RegistrationFragment;
import com.buscience.slidingmenu.NavDrawerItem;
import com.buscience.slidingmenu.NavDrawerListAdapter;

public class MainActivity extends Activity
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
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		mTitle = mDrawerTitle = getTitle();
 
        // nav drawer icons from resources
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
 
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
 
        navDrawerItems = new ArrayList<NavDrawerItem>();
 
        // Home
        navDrawerItems.add(new NavDrawerItem("\nHome\n", navMenuIcons.getResourceId(0, -1)));
        // Find People
        navDrawerItems.add(new NavDrawerItem("\nRegistration\n", navMenuIcons.getResourceId(1, -1)));
        // Photos
        navDrawerItems.add(new NavDrawerItem("\nCertification\n", navMenuIcons.getResourceId(2, -1)));
        // Communities, Will add a counter here
        navDrawerItems.add(new NavDrawerItem("\nEvaluation\n", navMenuIcons.getResourceId(3, -1)));
        // Pages
        navDrawerItems.add(new NavDrawerItem("\nContact\n", navMenuIcons.getResourceId(4, -1)));
         
        // Recycle the typed array
        navMenuIcons.recycle();
 
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
            
            @Override
            public void onDrawerSlide(View drawerView, float offset)
            {
                getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
 
        if (savedInstanceState == null) {
            // on first time display view for first nav item
        	fragment = new HomeFragment();
        	
        	FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            
            ft.replace(R.id.fragment_container, fragment).commit();
            
            mDrawerLayout.closeDrawer(mDrawerList);
        }
        
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
        //case R.id.action_settings:
          //  return true;
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
        //menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
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
    
    /**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                long id) {
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
            fragment = new HomeFragment();
            break;
        case 1:
            fragment = new RegistrationFragment();
            break;
        case 2:
            fragment = new CertificationFragment();
            break;
        case 3:
            fragment = new EvaluationFragment();
            break;
        case 4:
            fragment = new ContactFragment();
            break;
        case 5:
            fragment = new RegistrationFragment();
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
            
            ft.replace(R.id.fragment_container, fragment).commit();
 
            // update selected item and title, then close the drawer
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
        
        
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
   
    @Override
    public void onSaveInstanceState(Bundle outState) {   
        super.onSaveInstanceState(outState);        
    }
    
    
    //When refresh is clicked, start a new fragment that is of the same type already running
    @SuppressLint("NewApi")
	public boolean onRefreshClicked(MenuItem item)
    {

    	switch (currentPosition) {
        case 0:
            fragment = new HomeFragment();
            break;
        case 1:
            fragment = new RegistrationFragment();
            break;
        case 2:
            fragment = new CertificationFragment();
            break;
        case 3:
            fragment = new EvaluationFragment();
            break;
        case 4:
            fragment = new ContactFragment();
            break;
        case 5:
            fragment = new RegistrationFragment();
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
        
        ft.replace(R.id.fragment_container, fragment).commit();  
        
    	return true;
    }
    
    //Generates an AlertDialog when the help button is clicked
    public boolean onHelpClicked(MenuItem item)
    {    	    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	// Add the buttons
    	builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   
    	           }
    	       });
    	
    	builder.setMessage("BU Science is a Binghamton University Club in which our student teachers teach science and engineering concepts to elementary school students. We teach half an hour lessons once a week at George F. Johnson and Charles F. Johnson Elementary Schools in the Union-Endicott School district. ");

    	// Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    	
    	return true;
    	
    	
    }
    
    //Generates an AlertDialog when the user button is clicked
    public boolean onUserClicked(MenuItem item)
    {    	    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	// Add the buttons
    	builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   
    	           }
    	       });
    	
    	builder.setTitle("Developers");
    	builder.setMessage("The project is maintained as open-source by members of the Binghamton ACM Chapter.\n\nDevelopers:\n\nCheng Lin (khuang13@binghamton.edu)\n\nAnna Borovitcky (aborovi1@binghamton.edu)\n\nJames Edouard (jedouar1@binghamton.edu)\n\n\nPlease email us with bugs, fixes, or improvements you would like to see.\n\nNew project ideas or proposals are also welcome.\n\n\nContact acm.projects@binghamton.edu");

    	// Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    	
    	return true;
    	
    }
    
    //Overrides back button so that it resumes home fragment if not already there, otherwise
    //MainActivity stops.
    @Override
    public void onBackPressed()
    {    	
    	if(  currentPosition == 0 )
    	{
    		super.onBackPressed();
    	}
    	else
    	{
        	finish();
        	startActivity(getIntent());
        	overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    	}
    }
    
}
    
    
