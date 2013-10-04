package com.example.buscience;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class CalendarActivity extends Activity{

	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar_layout);

		final WebView calendar = (WebView)findViewById(R.id.calendar);
		calendar.setWebViewClient(new WebViewClient());
		calendar.getSettings().setUserAgentString("Mozilla/5.0");
		calendar.getSettings().setBuiltInZoomControls(true);
		calendar.getSettings().setJavaScriptEnabled(true);
		calendar.setInitialScale(165);
		
		calendar.loadUrl("https://www.google.com/calendar/embed?src=buscience1@gmail.com&color=%23711616&src=binghamton.edu_gtmjlqts6rnihne0lr72b9vbak@group.calendar.google.com&color=%232F6309&src=en.usa%23holiday@group.v.calendar.google.com&color=%23182C57&src=m87bdov769fit6s55mv5q505rs@group.calendar.google.com&color=%23668CD9&src=ns2lclo7qg3kut3cp2cvssat30@group.calendar.google.com&color=%23D96666&ctz=America/New_York&showTitle=0&showNav=1&showDate=1&showTabs=1&showCalendars=0&hl=en");

		Button backButton = MainActivity.getBackButton();
		backButton.setVisibility(View.VISIBLE);
		MainActivity.setShowBackButton(true);
		backButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) {
				setContentView(R.layout.home_layout);
				//MainActivity.setTitle("");                		
				v.setVisibility(View.GONE);
				MainActivity.setShowBackButton(false);
				
			}
		});
		
		
	}
}