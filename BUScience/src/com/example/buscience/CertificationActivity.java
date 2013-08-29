package com.example.buscience;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;

public class CertificationActivity extends Activity
{
	Button button;
	WebView slideshow;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.certification_layout);
        
        slideshow=(WebView)findViewById(R.id.webViewSlideshow);
        slideshow.loadUrl("http://docs.google.com/presentation/d/1zboHLCHmEIn1DTxSJM4GbL_4LQmlqN9URu39I52CG1c/embed?hl=en&amp;size=l");
        button=(Button)findViewById(R.id.certifyButton);
        button.setOnClickListener(null);//change...this
        
        
    }
}