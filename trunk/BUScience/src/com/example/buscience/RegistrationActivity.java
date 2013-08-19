package com.example.buscience;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class RegistrationActivity extends Activity
{
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);
    }
    
    public void schoolTeacherClicked(View view)
    {
    	setContentView(R.layout.school_teacher_layout);
		MainActivity.setTitle("School-Teacher Sign-Up");
	
		final WebView browserTimeSlot = (WebView)findViewById(R.id.schoolTimeSlot);
		browserTimeSlot.setWebViewClient(new WebViewClient());
		browserTimeSlot.getSettings().setJavaScriptEnabled(true);
		browserTimeSlot.loadUrl("https://spreadsheets.google.com/spreadsheet/ccc?chrome=false&key=0AphPhvpO0nOPdDRHNnVneW5NTjdzSzdJVTYxWndoSXc&output=html&pubredirect=true&widget=true");

		final Button clearButton = MainActivity.getClearButton();
		clearButton.setVisibility(View.VISIBLE);
		clearButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) {
				browserTimeSlot.loadUrl("https://spreadsheets.google.com/spreadsheet/ccc?chrome=false&key=0AphPhvpO0nOPdDRHNnVneW5NTjdzSzdJVTYxWndoSXc&output=html&pubredirect=true&widget=true");
			}
		});
		
		Button backButton = MainActivity.getBackButton();
		backButton.setVisibility(View.VISIBLE);
		backButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) {
        		setContentView(R.layout.registration_layout);
        		MainActivity.setTitle("Registration");                		
				v.setVisibility(View.GONE);
				clearButton.setVisibility(View.GONE);
			}
		});
    }
    
    public void universityTeacherClicked(View view)
    {
    	
    }
    
    public void tablingClicked(View view)
    {
    	
    }
}
