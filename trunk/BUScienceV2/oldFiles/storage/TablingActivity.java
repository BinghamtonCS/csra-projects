package storage;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

public class TablingActivity extends Activity implements TabHost.OnTabChangeListener {
	
	private TabHost tablingTabs;
	WebView tablingSchedule;
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		super.onCreate(savedInstanceState);
		
		boolean requestPassed = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.tabling_layout);
		
		tablingTabs = (TabHost)findViewById(R.id.tablingTabs);
		tablingTabs.setup();
		addTab(tablingTabs, "Info", R.id.tabInfo);
		addTab(tablingTabs, "Registration", R.id.tabRegistration);
		
		setTabBackground(tablingTabs.getCurrentTab());
		tablingTabs.setOnTabChangedListener(this);
		
		tablingSchedule = (WebView) findViewById(R.id.tablingSchedule);
		tablingSchedule.setWebViewClient(new WebViewClient());
		
		tablingSchedule.getSettings().setBuiltInZoomControls(true);
		
		tablingSchedule.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		tablingSchedule.getSettings().setJavaScriptEnabled(true);
		tablingSchedule.getSettings().setUserAgentString("Mozilla/5.0 (Android; Tablet; rv:24.0) Gecko/24.0 Firefox/24.0");
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
	
	@Override
	public void onTabChanged(String arg0) {
		setTabBackground(tablingTabs.getCurrentTab());	
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
		for(int i = 0; i < tablingTabs.getTabWidget().getChildCount();i++) {
			tablingTabs.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#000000")); //unselected
	    }
		tablingTabs.getTabWidget().getChildAt(index).setBackgroundColor(Color.parseColor("#808080")); // selected
	}

}
