package com.example.svcappa;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class DisplayEventsMessageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WebView webview = new WebView(this);
		 setContentView(webview);
		 webview.loadUrl("https://m.docs.google.com/document/d/1nrSm8XzFZsVlylTug4BgEssALIQTE2Vcg21kFTOzc8A/preview");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_events_message, menu);
		return true;
	}

}
