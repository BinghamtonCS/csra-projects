package com.example.buscience;

import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.view.*;
import android.widget.*;

public class MainActivity extends TabActivity
	implements TabHost.OnTabChangeListener
{
	private static TabHost tabHost;
	private static TextView titleText;
	private static Button backButton;
	private static Button clearButton;
	private static boolean[] showBackButton;
	private static boolean[] showClearButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		boolean requestPassed = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		if (requestPassed) {
			getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar_layout);
			backButton = ((Button)findViewById(R.id.btnBack));
			backButton.setVisibility(View.GONE);
			clearButton = ((Button)findViewById(R.id.btnReload));
			clearButton.setVisibility(View.GONE);
			titleText = ((TextView)findViewById(R.id.titleHeading1));
			titleText.setText("Home");
		}
		
		tabHost = (TabHost)findViewById(android.R.id.tabhost);	

		addTab("Home", R.drawable.icon_home_tab, HomeActivity.class);
		addTab("Registration", R.drawable.icon_registration_tab, RegistrationActivity.class);
		addTab("Certification", R.drawable.icon_certification_tab, CertificationActivity.class);
		addTab("Evaluation", R.drawable.icon_evaluation_tab, EvaluationActivity.class);
		addTab("Contact", R.drawable.icon_contact_tab, ContactActivity.class);

//		getTabWidget().setStripEnabled(true);
		tabHost.setCurrentTab(0);
		tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#808080"));
		tabHost.setOnTabChangedListener(this);
		
		showBackButton = new boolean[tabHost.getTabWidget().getChildCount()];
		showClearButton = new boolean[tabHost.getTabWidget().getChildCount()];		
	}
	
	@Override
	public void onTabChanged(String tabId) {
		setTabBackground();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public static TabHost getCurrentTabHost()
	{	return tabHost;		}
	
	public static Button getBackButton()
	{	return backButton;	}
	
	public static void setShowBackButton(boolean visible) {
		showBackButton[tabHost.getCurrentTab()] = visible;
	}
	
	public static Button getClearButton()
	{	return clearButton;	}
	
	public static void setShowClearButton(boolean visible) {
		showClearButton[tabHost.getCurrentTab()] = visible;
	}
	
	public static void setTitle(String title)
	{	titleText.setText(title);	}
	
	private void addTab(String label, int drawableId, Class<?> cls)
	{
		Intent intent = new Intent(this, cls);
		TabHost.TabSpec spec = tabHost.newTabSpec(label);
		View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);
		TextView title = (TextView)tabIndicator.findViewById(R.id.title);
		title.setText(label);
		title.setTextSize(9.5f);
		ImageView icon = (ImageView)tabIndicator.findViewById(R.id.icon);
		icon.setImageResource(drawableId);
		spec.setIndicator(tabIndicator);
		spec.setContent(intent);
		tabHost.addTab(spec);
	}

	private void setTabBackground()
	{
		for(int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
	        tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#000000")); //unselected
	    }
	    tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#808080")); // selected
	    
	    switch(tabHost.getCurrentTab()) 
	    {
	    	case 0:
	    		titleText.setText("Home");
	    		if (!showBackButton[0]) {
	    			backButton.setVisibility(View.GONE);
	    		} else {
	    			backButton.setVisibility(View.VISIBLE);
	    		}
	    		clearButton.setVisibility(View.GONE);
	    		break;
	    	case 1:
	    		titleText.setText("Registration");
	    		if (!showBackButton[1]) {
	    			backButton.setVisibility(View.GONE);
	    		} else {
	    			backButton.setVisibility(View.VISIBLE);
	    		}
	    		if (!showClearButton[1]) {
	    			clearButton.setVisibility(View.GONE);
	    		} else {
	    			clearButton.setVisibility(View.VISIBLE);
	    		}
	    		break;
	    	case 2:
	    		titleText.setText("Certification");
	    		backButton.setVisibility(View.GONE);
	    		break;
	    	case 3:
	    		titleText.setText("Evaluation");
	    		backButton.setVisibility(View.GONE);
	    		break;
	    	case 4:
	    		titleText.setText("Contact");
	    		backButton.setVisibility(View.GONE);
	    		break;
	    }
	}
}
