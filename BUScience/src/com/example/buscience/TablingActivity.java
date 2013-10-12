package com.example.buscience;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class TablingActivity extends Activity {
	
	WebView tablingSchedule;
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		super.onCreate(savedInstanceState);
		
		boolean requestPassed = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.tabling_layout);
		
		tablingSchedule = (WebView) findViewById(R.id.tablingSchedule);
		tablingSchedule.setWebViewClient(new WebViewClient());
		
		tablingSchedule.getSettings().setBuiltInZoomControls(true);
		
		tablingSchedule.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		tablingSchedule.getSettings().setJavaScriptEnabled(true);
		tablingSchedule.loadUrl("https://spreadsheets.google.com/a/binghamton.edu/spreadsheet/preview?key=0AphPhvpO0nOPdEp1REwzRHNBQm9FMWdpMDFreTZyRWc");
		
		if (requestPassed) {
			getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar_layout);
			((TextView)findViewById(R.id.titleHeading1)).setText("Tabling");
		}

		
		((Button)findViewById(R.id.btnBack)).setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) {
				onBackPressed();				
		    }
		});
	}

}
