package com.example.binghamtonsvc;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Tabs extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		TabHost myTabHost = (TabHost) findViewById(R.id.tabhost);
		myTabHost.setup();
		TabSpec specs = myTabHost.newTabSpec("tag1");
		specs.setContent(R.id.tabHome);
		specs.setIndicator("Home", Tabs.this.getResources().getDrawable(R.drawable.home));
		myTabHost.addTab(specs);
		specs = myTabHost.newTabSpec("tag2");
		specs.setContent(R.id.tabAbout);
		specs.setIndicator("About");
		myTabHost.addTab(specs);
		specs = myTabHost.newTabSpec("tag3");
		specs.setContent(R.id.tabEvents);
		specs.setIndicator("Events");
		myTabHost.addTab(specs);
		specs = myTabHost.newTabSpec("tag4");
		specs.setContent(R.id.tabSignup);
		specs.setIndicator("Signup");
		myTabHost.addTab(specs);
		
		WebView browser = (WebView) findViewById(R.id.wvEventsPage);
		browser.setWebViewClient(new WebViewClient()       
	    {
	         @Override
	        public boolean shouldOverrideUrlLoading(WebView view, String url) 
	        {
	            return false;
	        }
	    });
		browser.loadUrl("https://docs.google.com/document/d/1nrSm8XzFZsVlylTug4BgEssALIQTE2Vcg21kFTOzc8A/preview");
	}
}
