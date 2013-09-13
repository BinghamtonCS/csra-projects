package com.example.buscience;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.*;

public class RegistrationActivity extends Activity 
	implements TabHost.OnTabChangeListener
{
	private TabHost schoolTabs;
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);
    }
    
	public void schoolTeacherClicked(View view)
    {
    	setContentView(R.layout.school_teacher_layout);
		MainActivity.setTitle("School-Teacher Sign-Up");
		
		schoolTabs = (TabHost)findViewById(R.id.schoolTabs);
		schoolTabs.setup();
		addTab(schoolTabs, "Time-Slot", R.id.tabSchoolTimeSlot);
		addTab(schoolTabs, "Sign-Up", R.id.tabSchoolSignUp);
		addTab(schoolTabs, "Schedule Change", R.id.tabSchoolUpdate);
		
		setTabBackground(schoolTabs.getCurrentTab());
		schoolTabs.setOnTabChangedListener(this);

		final WebView browserTimeSlot = (WebView)findViewById(R.id.schoolTimeSlot);
		browserTimeSlot.setWebViewClient(new WebViewClient());
		browserTimeSlot.getSettings().setUserAgentString("Mozilla/5.0");
		browserTimeSlot.getSettings().setBuiltInZoomControls(true);
		browserTimeSlot.getSettings().setJavaScriptEnabled(true);
		browserTimeSlot.setInitialScale(165);
		browserTimeSlot.loadUrl("https://spreadsheets.google.com/spreadsheet/ccc?chrome=false&key=0AphPhvpO0nOPdDRHNnVneW5NTjdzSzdJVTYxWndoSXc&output=html&pubredirect=true&widget=true");
		
		final WebView browserSchoolSignUp = (WebView)findViewById(R.id.schoolSignUp);
		browserSchoolSignUp.setWebViewClient(new WebViewClient());
		browserSchoolSignUp.getSettings().setBuiltInZoomControls(true);
		browserSchoolSignUp.getSettings().setJavaScriptEnabled(true);
		browserSchoolSignUp.setInitialScale(165);
		browserSchoolSignUp.loadUrl("https://docs.google.com/spreadsheet/embeddedform?bc=transparent&f=%2522Georgia%2522%252C%2Bserif&formkey=dHI3Ym94WXFFUmJfdE9HSUdQSENzenc6MQ&hl=en&htc=%2523666666&lc=%2523135355&pli=1&tc=%2523565555&ttl=0");
		
		final WebView browserSchoolUpdate = (WebView)findViewById(R.id.schoolUpdate);
		browserSchoolUpdate.setWebViewClient(new WebViewClient());
		browserSchoolUpdate.getSettings().setBuiltInZoomControls(true);
		browserSchoolUpdate.getSettings().setJavaScriptEnabled(true);
		browserSchoolUpdate.setInitialScale(165);
		browserSchoolUpdate.loadUrl("https://docs.google.com/spreadsheet/embeddedform?bc=transparent&f=%2522Georgia%2522%252C%2Bserif&formkey=dDQ3Tk5PcjNYX1plb1RSbnVhOWh2bUE6MQ&hl=en&htc=%2523666666&lc=%2523135355&pli=1&tc=%2523565555&ttl=0");

		final Button clearButton = MainActivity.getClearButton();
		clearButton.setVisibility(View.VISIBLE);
		MainActivity.setShowClearButton(true);
		clearButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) {
				browserTimeSlot.loadUrl("https://spreadsheets.google.com/spreadsheet/ccc?chrome=false&key=0AphPhvpO0nOPdDRHNnVneW5NTjdzSzdJVTYxWndoSXc&output=html&pubredirect=true&widget=true");
				browserSchoolSignUp.loadUrl("https://docs.google.com/spreadsheet/embeddedform?bc=transparent&f=%2522Georgia%2522%252C%2Bserif&formkey=dHI3Ym94WXFFUmJfdE9HSUdQSENzenc6MQ&hl=en&htc=%2523666666&lc=%2523135355&pli=1&tc=%2523565555&ttl=0");
				browserSchoolUpdate.loadUrl("https://docs.google.com/spreadsheet/embeddedform?bc=transparent&f=%2522Georgia%2522%252C%2Bserif&formkey=dDQ3Tk5PcjNYX1plb1RSbnVhOWh2bUE6MQ&hl=en&htc=%2523666666&lc=%2523135355&pli=1&tc=%2523565555&ttl=0");				
			}
		});
		
		Button backButton = MainActivity.getBackButton();
		backButton.setVisibility(View.VISIBLE);
		MainActivity.setShowBackButton(true);
		backButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) {
        		setContentView(R.layout.registration_layout);
        		MainActivity.setTitle("Registration");                		
				v.setVisibility(View.GONE);
				clearButton.setVisibility(View.GONE);
				MainActivity.setShowBackButton(false);
				MainActivity.setShowClearButton(false);
			}
		});
    }
    
    public void universityTeacherClicked(View view)
    {
    	
    }
    
    public void tablingClicked(View view)
    {
    	
    }
    
    private void addTab(TabHost tabHost, String label, int id) 
    {
		TabHost.TabSpec spec = tabHost.newTabSpec(label);
		View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator_reg, tabHost.getTabWidget(), false);
		TextView title = (TextView)tabIndicator.findViewById(R.id.title);
		title.setText(label);
		title.setTextSize(16f);
		spec.setIndicator(tabIndicator);
		spec.setContent(id);
		tabHost.addTab(spec);
    }

	@Override
	public void onTabChanged(String arg0) {
		setTabBackground(schoolTabs.getCurrentTab());
	}
	
	private void setTabBackground(int index) 
	{
		for(int i = 0; i < schoolTabs.getTabWidget().getChildCount();i++) {
			schoolTabs.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#000000")); //unselected
	    }
		schoolTabs.getTabWidget().getChildAt(index).setBackgroundColor(Color.parseColor("#808080")); // selected
	}
}
