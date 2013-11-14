package com.example.buscience;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.AsyncTask;
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
		
		browser = (WebView)findViewById(R.id.evaluation); 
		
		final Button clearButton = MainActivity.getClearButton();
		clearButton.setVisibility(View.VISIBLE);
		clearButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				//use global variablr to set url
				//browser.loadUrl("https://docs.google.com/spreadsheet/embeddedform?bc=transparent&f=%2522Georgia%2522%252C%2Bserif&hl=en&htc=%2523666666&key=0AphPhvpO0nOPdGZIU0JaVEYxaTh2UjNaNk1rRWpOenc&lc=%2523135355&pli=1&tc=%2523565555&ttl=0");
			}
		}
		);

		new Load().execute("http://www.buscience.org/Evaluation");
	}
	
	public void loadURL(String strUrl){
		((WebView)findViewById(R.id.evaluation)).loadUrl(strUrl);
	}

	private class Load extends AsyncTask <String, Integer, String> {
		String url;

		@Override
		protected String doInBackground(String... params) {
			try{
				url = new ReadURL().read("http://www.buscience.org/Evaluation", "docs.google.com");
			}catch (Exception e){
				return null;
			}
			return null;
		}
		
		protected void onPostExecute(String str){
			loadURL(url);
		}

	}
}


/*in on create: super.onCreate(savedInstanceState);
		setContentView(R.layout.evaluation_layout);

		MainActivity.setTitle("Student/Teacher Evaluation");

		final WebView browser = (WebView) findViewById(R.id.evaluation);
		browser.setWebViewClient(new WebViewClient());
		browser.getSettings().setBuiltInZoomControls(true);
		browser.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

//		String url = new ReadURL().read("http://www.buscience.org/Evaluation", "docs.google.com");
//		browser.loadUrl(url);
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
		);	*/
