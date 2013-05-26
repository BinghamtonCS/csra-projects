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
//		tabHost.addTab(tabHost.newTabSpec("tab_Home").setIndicator("Home", getResources().getDrawable(R.drawable.icon_home_tab)).setContent(new Intent(this, HomeActivity.class)));
//		tabHost.addTab(tabHost.newTabSpec("tab_Reg").setIndicator("Registration", getResources().getDrawable(R.drawable.icon_registration_tab)).setContent(new Intent(this, RegistrationActivity.class)));
//		tabHost.addTab(tabHost.newTabSpec("tab_Cert").setIndicator("Certification", getResources().getDrawable(R.drawable.icon_certification_tab)).setContent(new Intent(this, CertificationActivity.class)));
//		tabHost.addTab(tabHost.newTabSpec("tab_Eval").setIndicator("Evaluation", getResources().getDrawable(R.drawable.icon_evaluation_tab)).setContent(new Intent(this, EvaluationActivity.class)));
//		tabHost.addTab(tabHost.newTabSpec("tab_Contact").setIndicator("Contact", getResources().getDrawable(R.drawable.icon_contact_tab)).setContent(new Intent(this, ContactActivity.class)));
//		
//	    for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) 
//	    {			
//	    	View v = tabHost.getTabWidget().getChildAt(i);
//	    	v.setPadding(0, 0, 0, 0);
//	        TextView tv = (TextView)v.findViewById(android.R.id.title);
//	        tv.setTextColor(Color.parseColor("#808080"));
//	        tv.setTextSize(8.5f);
//	    }
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
