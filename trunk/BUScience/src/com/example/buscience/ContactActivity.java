package com.example.buscience;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.Button;

public class ContactActivity extends Activity
{
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_layout);
        
        final WebView browserEboard = (WebView)findViewById(R.id.eboard);
		browserEboard.setWebViewClient(new WebViewClient());
		//browserEboard.getSettings().setUserAgentString("Mozilla/5.0");
		browserEboard.setInitialScale(165);
		browserEboard.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
	    browserEboard.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY); 
		browserEboard.loadUrl("https://docs.google.com/document/preview?hgd=1&id=1ah8eefcuLkjBZ07CPhLgW51N2Ck6osb4E9S4KGR3pIA&pli=1");
		
		final Button clearButton = MainActivity.getClearButton();
		clearButton.setVisibility(View.VISIBLE);
		clearButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) {
				browserEboard.loadUrl("https://docs.google.com/document/preview?hgd=1&id=1ah8eefcuLkjBZ07CPhLgW51N2Ck6osb4E9S4KGR3pIA&pli=1");
			}
		});
    }

}