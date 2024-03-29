package com.buscience.activities;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class CalendarActivity extends Activity{

	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar_layout);

    	new CalendarLoader().execute("http://www.buscience.org/home/calendar");

	}
	
	public void loadCalendar(String url)
	{
		final ProgressBar pbar = (ProgressBar)findViewById(R.id.progressBar);
		
		final WebView calendar = (WebView)findViewById(R.id.calendar);
		calendar.setWebViewClient(new WebViewClient()
		{
			@Override
        	public void onPageFinished(WebView view, String ur)
        	{
        		pbar.setVisibility(ProgressBar.GONE);
        	}
			
		});
		calendar.getSettings().setUserAgentString("Mozilla/5.0");
		calendar.getSettings().setBuiltInZoomControls(true);
		calendar.getSettings().setJavaScriptEnabled(true);
		calendar.setInitialScale(165);
		
		calendar.loadUrl(url);
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        this.setTitle("Calendar");
        return true;
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
    	
    	builder.setMessage("Please add our calendar to your google calendar to keep updated on meeting times and events held by BU Science!");
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
    
    private class CalendarLoader extends AsyncTask<String, Integer, String> {
	     protected String doInBackground(String... urls) {
		String link = "";
				
				
				
				try {
					Document doc = Jsoup.connect(urls[0]).get();
					link = doc.select("iframe").attr("src");
	
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	         return link;
	     }

	     protected void onProgressUpdate(Integer... progress) {

	    	 
	     }

	     protected void onPostExecute(String link) {
	    	 loadCalendar(link);
	     }
  }

}