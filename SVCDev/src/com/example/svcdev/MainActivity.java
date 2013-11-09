package com.example.svcdev;

import Fragments.AboutSectionFragment;
import Fragments.ContactSectionFragment;
import Fragments.EventsSectionFragment;
import Fragments.LaunchpadSectionFragment;
import Fragments.SignupSectionFragment;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {
	
	AppSectionsPagerAdapter mAppSectionsPagerAdapter;
	ViewPager mViewPager;
	WebView eWebView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());
        final ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2E6444")));
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
        	@Override
        	public void onPageSelected(int position) {
        		actionBar.setSelectedNavigationItem(position);
        	}
        });
        
        for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
        	actionBar.addTab(
        			actionBar.newTab()
        					.setText(mAppSectionsPagerAdapter.getPageTitle(i))
        					.setTabListener(this));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true; 
    }
    
    @Override
  	public boolean onOptionsItemSelected(MenuItem item) {
  		// TODO Auto-generated method stub
  		switch (item.getItemId()) {
  		case R.id.itemMeets:
  			Context context = getApplicationContext();
  			CharSequence text = "Office :UUW309 \nsvc@binghamtonsa.org \nMeetings are held bi-weekly\nDates and Locations TBA Check B-Line";
  			int duration = Toast.LENGTH_LONG;
  			Toast toast = Toast.makeText(context, text, duration);
  			toast.setGravity(Gravity.CENTER, 0, 0);
  			toast.show();
  			return true;
  		case R.id.source:
  			new AlertDialog.Builder(this)
  	    .setTitle("Developers")
  	    .setMessage("This project was completed as open-source by members of the Binghamton Computer Science Research Alliance, now known as Binghamton ACM Chapter.\n\nDeveloper: \nChristopher Zhang (czhang44@binghamton.edu) \n\nDesign: \nCheng Lin (khuang13@binghamton.edu) \n\nPlease email us with bugs, fixes, or improvements you would like to see.\n\nNew project ideas or proposals are also welcome.\n\nPlease contact acm.projects@binghamton.edu").show();
  			return true;
  		default: 
  			return true;
  		}	
  		
  	}
  	


	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
	}


	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
		mViewPager.setCurrentItem(arg0.getPosition());
	}


	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
	}
    
	public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {
		
		public AppSectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			switch (arg0) {
			case 0:
				return new LaunchpadSectionFragment();
			case 1:
				return new AboutSectionFragment();
			case 2:
				return new EventsSectionFragment();
			case 3:
				return new SignupSectionFragment();
			default:
				return new ContactSectionFragment();
			}
		}

		@Override
		public int getCount() {
			return 5;
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			String[] tabTitles = {"Home", "About", "Events", "Signup", "Contact"};
			return tabTitles[position];
		}
	}
}
