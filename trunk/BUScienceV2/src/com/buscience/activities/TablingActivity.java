package com.buscience.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;

public class TablingActivity extends Activity{ 
	//implements TabHost.OnTabChangeListener {

	private TabHost tablingTabs;
	private WebView webSchedule, webRegistration; 
	private final String scheduleURL= "https://spreadsheets.google.com/a/binghamton.edu/spreadsheet/ccc?chrome=false&key=0AphPhvpO0nOPdEp1REwzRHNBQm9FMWdpMDFreTZyRWc&output=html&pubredirect=true&widget=true";
	//"http://spreadsheets.google.com/spreadsheet/loadredirect?chrome=false&amp;key=0AphPhvpO0nOPdEp1REwzRHNBQm9FMWdpMDFreTZyRWc&amp;output=html&amp;pubredirect=true&amp;widget=true";
	private final String registrationURL = "https://spreadsheets.google.com/a/binghamton.edu/spreadsheet/ccc?chrome=false&key=0AphPhvpO0nOPdEp1REwzRHNBQm9FMWdpMDFreTZyRWc&output=html&pubredirect=true&widget=true";
			//"calendar_link"; //get this link

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabling);
		this.setTitle("Tabling");
		
		tablingTabs = (TabHost)findViewById(R.id.tabHostTabling);
		tablingTabs.setup();
		tablingTabs.addTab(tablingTabs.newTabSpec("tabTablingSchedule").setIndicator("Schedule").setContent(R.id.tabTablingSchedule));
		tablingTabs.addTab(tablingTabs.newTabSpec("tabTablingRegis").setIndicator("Register").setContent(R.id.tabTablingRegis));
		
		webSchedule = (WebView)findViewById(R.id.webTablingSchedule);
		final ProgressBar progSchedule = (ProgressBar)findViewById(R.id.progTablingSchedule);
		webSchedule.setWebViewClient(new WebViewClient(){
        	@Override
        	public void onPageStarted(WebView view, String url, Bitmap favicon)
        	{
        		progSchedule.setVisibility(ProgressBar.VISIBLE);
        		webSchedule.setVisibility(View.GONE);
        	}
        	@Override
        	public void onPageFinished(WebView view, String url)
        	{
        		progSchedule.setVisibility(ProgressBar.GONE);
        		webSchedule.setVisibility(View.VISIBLE);
        	}
		});
		//browserSignUp.getSettings().setBuiltInZoomControls(true);
		webSchedule.getSettings().setJavaScriptEnabled(true);
		//browserSignUp.setInitialScale(165);
		webSchedule.loadUrl(scheduleURL);
		
		webRegistration = (WebView)findViewById(R.id.webTablingRegis);
		final ProgressBar progRegistration = (ProgressBar)findViewById(R.id.progTablingRegis);
		webRegistration.setWebViewClient(new WebViewClient(){
        	@Override
        	public void onPageStarted(WebView view, String url, Bitmap favicon)
        	{
        		progRegistration.setVisibility(ProgressBar.VISIBLE);
        		webRegistration.setVisibility(View.GONE);
        	}
        	@Override
        	public void onPageFinished(WebView view, String url)
        	{
        		progRegistration.setVisibility(ProgressBar.GONE);
        		webRegistration.setVisibility(View.VISIBLE);
        	}			
		});
		//browserUpdate.getSettings().setBuiltInZoomControls(true);
		webRegistration.getSettings().setJavaScriptEnabled(true);
		//browserUpdate.setInitialScale(165);
		webRegistration.loadUrl(registrationURL);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		this.setTitle("Tabling");
		return true;
	}
	
	public boolean onRefreshClicked(MenuItem item)
    {
//    	finish();
//    	startActivity(getIntent());
    	switch(tablingTabs.getCurrentTab()) {
    		case 0:
    			webSchedule.loadUrl(scheduleURL);
    			break;
    		case 1:
    			webRegistration.loadUrl(registrationURL);
    			break;
    	}
    	overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    	
    	return true;
    }
	
	public boolean onHelpClicked(MenuItem item)
    {    	    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	// Add the buttons
    	builder.setNeutralButton("OK", new DialogInterface.OnClickListener(){
    		public void onClick(DialogInterface dialog, int id) {
    	        	   
    	    }
    	});
    	
    	builder.setMessage("Filler message. To be edited");
    	// Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();	
    	
    	return true;
    }

}
