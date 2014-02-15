package com.buscience.activities;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.buscience.fragments.LessonFilesFragment;
import com.buscience.fragments.LessonPlansFragment;

public class LessonActivity extends Activity{
	
	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.lesson_activity);
		this.setTitle("Lesson Plan");
		
        ActionBar actionbar = getActionBar();
        
        if ( Build.VERSION.SDK_INT >= 11)
        {
        	actionbar.setHomeButtonEnabled(true);
        }
        actionbar.setDisplayHomeAsUpEnabled(true);
        
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.Tab plansTab = actionbar.newTab().setText("Plans");
        ActionBar.Tab filesTab = actionbar.newTab().setText("Files");
        
        Fragment lessonFilesFragment = new LessonFilesFragment();
        Fragment lessonPlansFragment = new LessonPlansFragment();
        
        plansTab.setTabListener(new MyTabsListener(lessonPlansFragment));
        filesTab.setTabListener(new MyTabsListener(lessonFilesFragment));
        
        actionbar.addTab(plansTab);
        actionbar.addTab(filesTab);
        
        if (savedInstanceState != null && (savedInstanceState.getInt("TAB_KEY_INDEX") == 1) )
        {
        	actionbar.selectTab(filesTab);
        }

	}
	
        	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
                super.onSaveInstanceState(outState);
                outState.putInt("TAB_KEY_INDEX", getActionBar().getSelectedNavigationIndex());
    }
    
    
	//Refresh button restarts this activity with added animation
    public boolean onRefreshClicked(MenuItem item)
    {
    	finish();
    	startActivity(getIntent());
    	overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    	
    	return true;
    }
    
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
    
    private class MyTabsListener implements ActionBar.TabListener {
        public Fragment fragment;

        public MyTabsListener(Fragment fragment) {
                    this.fragment = fragment;
        }

        @Override
        public void onTabReselected(Tab tab, FragmentTransaction ft) {

        }

        @Override
        public void onTabSelected(Tab tab, FragmentTransaction ft) {
                    ft.replace(R.id.lesson_container, fragment);
        }

        @Override
        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
                    ft.remove(fragment);
        }
        
    }
        

}
