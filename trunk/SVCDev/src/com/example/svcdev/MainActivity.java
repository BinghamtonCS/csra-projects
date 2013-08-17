package com.example.svcdev;

import Fragments.AboutSectionFragment;
import Fragments.ContactSectionFragment;
import Fragments.EventsSectionFragment;
import Fragments.LaunchpadSectionFragment;
import Fragments.SignupSectionFragment;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {
	
	AppSectionsPagerAdapter mAppSectionsPagerAdapter;
	ViewPager mViewPager;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());
        final ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        mViewPager = (ViewPager) findViewById(R.id.pager);
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
  		case R.id.itemRefresh:
  			//do something when this button is pressed
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
