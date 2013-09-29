package com.example.buscience;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.Button;

public class EvaluationActivity extends Activity
{
	
	WebView browser;	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.evaluation_layout);
		
		MainActivity.setTitle("Student/Teacher Evaluation");
		
		final WebView browser = (WebView) findViewById(R.id.webView1);
		browser.setWebViewClient(new WebViewClient());
		
		browser.getSettings().setBuiltInZoomControls(true);
		
		browser.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
	
		browser.loadUrl("https://docs.google.com/spreadsheet/embeddedform?bc=transparent&f=%2522Georgia%2522%252C%2Bserif&hl=en&htc=%2523666666&key=0AphPhvpO0nOPdGZIU0JaVEYxaTh2UjNaNk1rRWpOenc&lc=%2523135355&pli=1&tc=%2523565555&ttl=0");

		final Button clearButton = MainActivity.getClearButton();
		clearButton.setVisibility(View.VISIBLE);
		clearButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				browser.loadUrl("https://docs.google.com/spreadsheet/embeddedform?bc=transparent&f=%2522Georgia%2522%252C%2Bserif&hl=en&htc=%2523666666&key=0AphPhvpO0nOPdGZIU0JaVEYxaTh2UjNaNk1rRWpOenc&lc=%2523135355&pli=1&tc=%2523565555&ttl=0");
			}
		}
		);	
		
	}
}