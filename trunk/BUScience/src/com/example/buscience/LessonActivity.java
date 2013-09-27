package com.example.buscience;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class LessonActivity extends Activity{
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lesson_layout);
		
		final WebView grid = (WebView)findViewById(R.id.lesson_grid);
		grid.setWebViewClient(new WebViewClient());
		grid.getSettings().setUserAgentString("Mozilla/5.0");
		grid.getSettings().setJavaScriptEnabled(true);
		grid.setInitialScale(165);
		grid.loadUrl("https://spreadsheets.google.com/spreadsheet/ccc?chrome=false&key=0AphPhvpO0nOPdEhPampJeW9KMHV2dVRBYTFNbW9fd1E&output=html&pubredirect=true&widget=true");

		//http://www.buscience.org/home/lesson-plans/fall-2011
		Button backButton = MainActivity.getBackButton();
		backButton.setVisibility(View.VISIBLE);
		MainActivity.setShowBackButton(true);
		backButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) {
        		setContentView(R.layout.home_layout);
                //recreateView();
        		MainActivity.setTitle("Home");                		
				v.setVisibility(View.GONE);
				MainActivity.setShowBackButton(false);
			}
		});
	}

}
