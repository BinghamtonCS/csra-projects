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
	private TabHost tabHost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
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
		for(int i = 0; i < tabHost.getTabWidget().getChildCount();i++)
	    {
	        tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#000000")); //unselected
	    }
	    tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#808080")); // selected
	}
}
