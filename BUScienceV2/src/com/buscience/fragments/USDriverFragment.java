package com.buscience.fragments;

import java.io.IOException;
import java.util.Calendar;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.buscience.activities.R;
import com.buscience.activities.UnivStudentRegActivity;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;

public class USDriverFragment extends Fragment
{
	private final String signUpURL = "http://whenisgood.net/BU_Science";
	private final String resultURL = "http://whenisgood.net/BU_Science/results/3bgnm2";
	
	private TabHost driverTabs;
	private TextView txtDriverInfo;
	private WebView webSignUp, webResult; 
	private View view;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		this.getActivity().setTitle("Driver's Schedule/Time Request");
		view = inflater.inflate(R.layout.us_driver_reg_layout, container, false);
		
		//tabhost within a fragment
		driverTabs = (TabHost)view.findViewById(R.id.tabhostDriver);
		driverTabs.setup();
		driverTabs.addTab(driverTabs.newTabSpec("tabDriverInfo").setIndicator("Information").setContent(R.id.tabDriverInfo));
		driverTabs.addTab(driverTabs.newTabSpec("tabDriverSignUp").setIndicator("Time Request").setContent(R.id.tabDriverSignUp));
		driverTabs.addTab(driverTabs.newTabSpec("tabDriverResult").setIndicator("Schedule").setContent(R.id.tabDriverResult));		

		// -------------------------------------------------------------------
		txtDriverInfo = (TextView)view.findViewById(R.id.driverInfoText);
		txtDriverInfo.setText("Do you own or have access to a vehicle? As a BU Science teacher and a driver you will be rewarded:" + 
				"\n   %25 off uniform purchase." +
				"\n   No fundraising obligations." +"\n   Early Registration." + "\n   Apply to any time slot that will fit your schedule." +
				"\n   Be able to invite friends to your group before general registration. \nTell us what your schedule is like for this semester." +
				"Select a preference color and paint a time that is good for you to teach BU Science." +
				"\n  1. Allow 15 minutes of travel before and 45 minutes of teaching and travel after " +
				"(i.e. if you highlight 10:00 am, you will need to leave campus at 9:45 am and will come back at 10:45 am." +
				"\n  2. Write your \"Full Name\" + [space] + \"Current Semester's Initial (S or F)\" + \"year\" (i.e. \"Britney Smith S2013\")"
				+"\n  3. Comment \"Driver\" or leave blank if you are here just to share your schedule to a driver." +
				"\n  4. Click Send Response");

		// -------------------------------------------------------------------
		webSignUp = (WebView)view.findViewById(R.id.webDriverSignUp);
		final ProgressBar progSignUp = (ProgressBar)view.findViewById(R.id.progDriverSignUp);
		webSignUp.setWebViewClient(new WebViewClient(){
        	@Override
        	public void onPageStarted(WebView view, String url, Bitmap favicon)
        	{
        		progSignUp.setVisibility(ProgressBar.VISIBLE);
        		webSignUp.setVisibility(View.GONE);
        	}
        	@Override
        	public void onPageFinished(WebView view, String url)
        	{
        		progSignUp.setVisibility(ProgressBar.GONE);
        		webSignUp.setVisibility(View.VISIBLE);
        	}		
		});
		webSignUp.getSettings().setUserAgentString("Mozilla/5.0");
		//browserSchedule.getSettings().setBuiltInZoomControls(true);
		webSignUp.getSettings().setJavaScriptEnabled(true);
		//browserSchedule.setInitialScale(165);
		webSignUp.loadUrl(signUpURL);

		// -------------------------------------------------------------------
		webResult = (WebView)view.findViewById(R.id.webDriverResult);
		final ProgressBar progResult = (ProgressBar)view.findViewById(R.id.progDriverResult);
		webResult.setWebViewClient(new WebViewClient(){
        	@Override
        	public void onPageStarted(WebView view, String url, Bitmap favicon)
        	{
        		progResult.setVisibility(ProgressBar.VISIBLE);
        		webResult.setVisibility(View.GONE);
        	}
        	@Override
        	public void onPageFinished(WebView view, String url)
        	{
        		progResult.setVisibility(ProgressBar.GONE);
        		webResult.setVisibility(View.VISIBLE);
        	}		
		});
		webResult.getSettings().setUserAgentString("Mozilla/5.0");
		//browserSchedule.getSettings().setBuiltInZoomControls(true);
		webResult.getSettings().setJavaScriptEnabled(true);
		//browserSchedule.setInitialScale(165);
		webResult.loadUrl(resultURL);
		
		ImageButton ibGoBack = (ImageButton)view.findViewById(R.id.ibGoBack);
		ibGoBack.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				webResult.loadUrl(resultURL);
			}
		});
		
		return view;
	}
	
	@Override
    public void onResume()
    {
    	super.onResume();
    	this.getActivity().setTitle("Driver's Schedule/Time Request");
    	
    	UnivStudentRegActivity act = ((UnivStudentRegActivity)(this.getActivity()));
        act.getMDrawerList().setItemChecked(3, true);
        act.getMDrawerList().setSelection(3);
        act.setCurrentPosition(3);
    }
	
	@Override
    public void onActivityCreated(Bundle savedInstanceState) 
	{
     	super.onActivityCreated(savedInstanceState);
    	
    	this.getActivity().setTitle("Driver's Schedule/Time Request");
    	UnivStudentRegActivity act = ((UnivStudentRegActivity)(this.getActivity()));

        act.getMDrawerList().setItemChecked(3, true);
        act.getMDrawerList().setSelection(3);
        act.setCurrentPosition(3); 	        
    }
}