package com.buscience.activities;

import android.os.Build;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TabHost;

public class SchoolTeacherActivity extends Activity 
{
	private TabHost elemTabs;
	private WebView webSignUp, webUpdate, webSchedule; 
	private final String signUpURL = "https://docs.google.com/spreadsheet/embeddedform?bc=transparent&f=%2522Georgia%2522%252C%2Bserif&formkey=dHI3Ym94WXFFUmJfdE9HSUdQSENzenc6MQ&hl=en&htc=%2523666666&lc=%2523135355&pli=1&tc=%2523565555&ttl=0";
	private final String updateURL = "https://docs.google.com/spreadsheet/embeddedform?bc=transparent&f=%2522Georgia%2522%252C%2Bserif&formkey=dDQ3Tk5PcjNYX1plb1RSbnVhOWh2bUE6MQ&hl=en&htc=%2523666666&lc=%2523135355&pli=1&tc=%2523565555&ttl=0";
	private final String scheduleURL = "https://spreadsheets.google.com/spreadsheet/ccc?chrome=false&key=0AphPhvpO0nOPdDRHNnVneW5NTjdzSzdJVTYxWndoSXc&output=html&pubredirect=true&widget=true";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_school_teacher);
		
		elemTabs = (TabHost)findViewById(R.id.tabHostElem);
		elemTabs.setup();
		elemTabs.addTab(elemTabs.newTabSpec("tabSchoolSignUp").setIndicator("Sign-Up").setContent(R.id.tabSchoolSignUp));
		elemTabs.addTab(elemTabs.newTabSpec("tabSchoolUpdate").setIndicator("Update").setContent(R.id.tabSchoolUpdate));
		elemTabs.addTab(elemTabs.newTabSpec("tabSchoolSchedule").setIndicator("Schedule").setContent(R.id.tabSchoolSchedule));
		
		webSignUp = (WebView)findViewById(R.id.webSchoolSignUp);
		final ProgressBar progSignUp = (ProgressBar)findViewById(R.id.progSchoolSignUp);
		webSignUp.setWebViewClient(new WebViewClient(){
        	@Override
        	public void onPageStarted(WebView view, String url, Bitmap favicon)
        	{
        		progSignUp.setVisibility(ProgressBar.VISIBLE);
        		webSignUp.setVisibility(View.GONE);
        	}
        	@Override
        	public void onPageFinished(WebView view, String url)
        	{
        		progSignUp.setVisibility(ProgressBar.GONE);
        		webSignUp.setVisibility(View.VISIBLE);
        	}
		});
		//browserSignUp.getSettings().setBuiltInZoomControls(true);
		webSignUp.getSettings().setJavaScriptEnabled(true);
		//browserSignUp.setInitialScale(165);
		webSignUp.loadUrl(signUpURL);
		
		webUpdate = (WebView)findViewById(R.id.webSchoolUpdate);
		final ProgressBar progUpdate = (ProgressBar)findViewById(R.id.progSchoolUpdate);
		webUpdate.setWebViewClient(new WebViewClient(){
        	@Override
        	public void onPageStarted(WebView view, String url, Bitmap favicon)
        	{
        		progUpdate.setVisibility(ProgressBar.VISIBLE);
        		webUpdate.setVisibility(View.GONE);
        	}
        	@Override
        	public void onPageFinished(WebView view, String url)
        	{
        		progUpdate.setVisibility(ProgressBar.GONE);
        		webUpdate.setVisibility(View.VISIBLE);
        	}			
		});
		//browserUpdate.getSettings().setBuiltInZoomControls(true);
		webUpdate.getSettings().setJavaScriptEnabled(true);
		//browserUpdate.setInitialScale(165);
		webUpdate.loadUrl(updateURL);
		
		webSchedule = (WebView)findViewById(R.id.webSchoolSchedule);
		final ProgressBar progSchedule = (ProgressBar)findViewById(R.id.progSchoolSchedule);
		webSchedule.setWebViewClient(new WebViewClient(){
        	@Override
        	public void onPageStarted(WebView view, String url, Bitmap favicon)
        	{
        		progSchedule.setVisibility(ProgressBar.VISIBLE);
        		webUpdate.setVisibility(View.GONE);
        	}
        	@Override
        	public void onPageFinished(WebView view, String url)
        	{
        		progSchedule.setVisibility(ProgressBar.GONE);
        		webUpdate.setVisibility(View.VISIBLE);
        	}		
		});
		webSchedule.getSettings().setUserAgentString("Mozilla/5.0");
		//browserSchedule.getSettings().setBuiltInZoomControls(true);
		webSchedule.getSettings().setJavaScriptEnabled(true);
		//browserSchedule.setInitialScale(165);
		webSchedule.loadUrl(scheduleURL);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		this.setTitle("Elementary Teacher Registration");
		return true;
	}
	
    public boolean onRefreshClicked(MenuItem item)
    {
//    	finish();
//    	startActivity(getIntent());
    	switch (elemTabs.getCurrentTab()) {
    		case 0:
    			webSignUp.loadUrl(signUpURL);
    			break;
    		case 1:
    			webUpdate.loadUrl(updateURL);
    			break;
    		case 2:
    			webSchedule.loadUrl(scheduleURL);
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
    	
    	builder.setMessage("If you are a school teacher, you can use these forms to register or view the current schedule.");
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
}
