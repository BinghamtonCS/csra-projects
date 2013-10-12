package com.example.buscience;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

public class CertificationActivity extends Activity 
	implements TabHost.OnTabChangeListener
{
	private TabHost certTabs;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.certification_layout);
		
		certTabs = (TabHost)findViewById(R.id.certTabs);
		certTabs.setup();
		addTab(certTabs, "Presentation", R.id.tabPresentation);
		addTab(certTabs, "Questions", R.id.tabQuestions);
		
		setTabBackground(certTabs.getCurrentTab());
		certTabs.setOnTabChangedListener(this);
		
		final WebView browserPresentation = (WebView)findViewById(R.id.webPresentation);
		browserPresentation.setWebViewClient(new WebViewClient());
		browserPresentation.getSettings().setUserAgentString("Mozilla/5.0");
		browserPresentation.getSettings().setJavaScriptEnabled(true);
		browserPresentation.setInitialScale(165);
		browserPresentation.loadUrl("http://docs.google.com/presentation/d/1zboHLCHmEIn1DTxSJM4GbL_4LQmlqN9URu39I52CG1c/embed?hl=en&amp;size=l");
				
		final WebView browserQuestions = (WebView)findViewById(R.id.webQuestions);
		browserQuestions.setWebViewClient(new WebViewClient());
//		browserQuestions.getSettings().setUserAgentString("Mozilla/5.0");
		browserQuestions.getSettings().setBuiltInZoomControls(true);
		browserQuestions.getSettings().setJavaScriptEnabled(true);
//		browserQuestions.setInitialScale(165);
//		browserQuestions.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN); 
//		browserQuestions.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY); 
		browserQuestions.loadUrl("https://docs.google.com/spreadsheet/embeddedform?bc=transparent&f=%2522Georgia%2522%252C%2Bserif&formkey=dHpJbk1WWjljVFdJQzNBZ0FQTFpzNVE6MA&hl=en&htc=%2523666666&lc=%2523135355&pli=1&tc=%2523565555&ttl=0");
		
		final Button clearButton = MainActivity.getClearButton();
		clearButton.setVisibility(View.VISIBLE);
		clearButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) {
				browserPresentation.loadUrl("http://docs.google.com/presentation/d/1zboHLCHmEIn1DTxSJM4GbL_4LQmlqN9URu39I52CG1c/embed?hl=en&amp;size=l");
				browserQuestions.loadUrl("https://docs.google.com/spreadsheet/embeddedform?bc=transparent&f=%2522Georgia%2522%252C%2Bserif&formkey=dHpJbk1WWjljVFdJQzNBZ0FQTFpzNVE6MA&hl=en&htc=%2523666666&lc=%2523135355&pli=1&tc=%2523565555&ttl=0");				
			}
		});
	}

	@Override
	public void onTabChanged(String arg0) {
		setTabBackground(certTabs.getCurrentTab());
		
	}
	
	private void addTab(TabHost tabHost, String label, int id) 
    {
		TabHost.TabSpec spec = tabHost.newTabSpec(label);
		View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator_reg, tabHost.getTabWidget(), false);
		TextView title = (TextView)tabIndicator.findViewById(R.id.title);
		title.setText(label);
		title.setTextSize(16f);
		spec.setIndicator(tabIndicator);
		spec.setContent(id);
		tabHost.addTab(spec);
    }
	
	private void setTabBackground(int index) 
	{
		for(int i = 0; i < certTabs.getTabWidget().getChildCount();i++) {
			certTabs.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#000000")); //unselected
	    }
		certTabs.getTabWidget().getChildAt(index).setBackgroundColor(Color.parseColor("#808080")); // selected
	}
}