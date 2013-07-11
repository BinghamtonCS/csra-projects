package Fragments;

import java.util.Calendar;

import com.example.svcdev.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

public class SignupSectionFragment extends Fragment {
	
	WebView browser;
	TextView lastUpdated;
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_section_signup, container, false);
		lastUpdated = (TextView) rootView.findViewById(R.id.tTimeUpdatedSU);
		browser = (WebView) rootView.findViewById(R.id.wvSignUpPage);
		browser.setWebViewClient(new WebViewClient()       
	    {
	         @Override
	        public boolean shouldOverrideUrlLoading(WebView view, String url) 
	        {
	            return false;
	        }
	    });
		browser.loadUrl("https://docs.google.com/spreadsheet/embeddedform?formkey=dFQ1eFNYckJkeVJhQjdYaGxlRjJnaHc6MQ");
		lastUpdated.setText("Most Recent Update Attempt: \n" + java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));
		
		ImageButton refresherB = (ImageButton) rootView.findViewById(R.id.ibRefreshSU);
		refresherB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				browser.loadUrl("https://docs.google.com/spreadsheet/embeddedform?formkey=dFQ1eFNYckJkeVJhQjdYaGxlRjJnaHc6MQ");
				lastUpdated.setText("Most Recent Update Attempt: \n" + java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));
				}
			});
			return rootView;
		}
	}