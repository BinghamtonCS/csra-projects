package com.buscience.fragments;

import java.io.IOException;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.buscience.activities.R;
import com.buscience.activities.UnivStudentRegActivity;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;

public class USDriverFragment extends Fragment{
	/**/
	private TabHost driverTabs;
	View view;
	TextView driverInfo;
	//button "next" to go to Driver Results (which has a link back to different Driver Registration page)
	//zoomable calendar - JSoup
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		this.getActivity().setTitle("Driver's Schedule/Time Request");
		view = inflater.inflate(R.layout.us_driver_registration, container, false);
		
		//tabhost within a fragment
		//driverTabs = (TabHost)findViewById(R.id.tabHostDriver);
		driverTabs.setup();
		driverTabs.addTab(driverTabs.newTabSpec("driverInfo").setIndicator("Driver Info").setContent(R.id.tabTablingSchedule));
		driverTabs.addTab(driverTabs.newTabSpec("tabTablingRegis").setIndicator("Calendar").setContent(R.id.tabTablingRegis));
		
		new USDriverLoader().execute("http://www.buscience.org/Registration/university-student-sign-up/driver_schedule");

		driverInfo = (TextView)view.findViewById(R.id.driverInfoText);

		driverInfo.setText("Do you own or have access to a vehicle? As a BU Science teacher and a driver you will be rewarded:" + 
				"\n   %25 off uniform purchase." +
				"\n   No fundraising obligations." +"\n   Early Registration." + "\n   Apply to any time slot that will fit your schedule." +
				"\n   Be able to invite friends to your group before general registration. \nTell us what your schedule is like for this semester." +
				"Select a preference color and paint a time that is good for you to teach BU Science." +
				"\n  1. Allow 15 minutes of travel before and 45 minutes of teaching and travel after " +
				"(i.e. if you highlight 10:00 am, you will need to leave campus at 9:45 am and will come back at 10:45 am." +
				"\n  2. Write your \"Full Name\" + [space] + \"Current Semester's Initial (S or F)\" + \"year\" (i.e. \"Britney Smith S2013\")"
				+"\n  3. Comment \"Driver\" or leave blank if you are here just to share your schedule to a driver." +
				"\n  4. Click Send Response");
		return view;       

	}

	public void loadUSDriverPage(String data, String baseUrl)
	{
		final ProgressBar pbar = (ProgressBar)view.findViewById(R.id.progressBar);

		WebView web = (WebView)view.findViewById(R.id.driverWebview);
		pbar.setVisibility(ProgressBar.VISIBLE);


		web.setWebViewClient(new WebViewClient()
		{

			@Override
			public void onPageFinished(WebView view, String baseUrl)
			{
				pbar.setVisibility(ProgressBar.GONE);
			}

		});
		web.loadUrl("http://whenisgood.net/BU_Science/results/3bgnm2");
	}
	
	@Override
    public void onResume()
    {
    	super.onResume();
    	this.getActivity().setTitle("Driver's Schedule/Time Request");
    	
    	UnivStudentRegActivity act = ((UnivStudentRegActivity)(this.getActivity()));
         act.getMDrawerList().setItemChecked(1, true);
         act.getMDrawerList().setSelection(1);
         act.setCurrentPosition(1);
    }
	
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        
    	super.onActivityCreated(savedInstanceState);
    	
    	this.getActivity().setTitle("Driver's Schedule/Time Request");
    	UnivStudentRegActivity act = ((UnivStudentRegActivity)(this.getActivity()));

        act.getMDrawerList().setItemChecked(1, true);
        act.getMDrawerList().setSelection(1);
        act.setCurrentPosition(1);
        	        
    }

	private class USDriverLoader extends AsyncTask<String, Integer, String[]> {
		protected String[] doInBackground(String... urls) {
			String data = "";
			String baseUrl = "";

			try {
				Document doc = Jsoup.connect(urls[0]).get();
				//Log.e("Parse", doc.toString());
				baseUrl = doc.select("iframe[title*=Update]").attr("src");
				Log.e("Parse2", baseUrl);

				Document updatePage = Jsoup.connect(baseUrl).get();
				//Log.e("Parse3", updatePage.toString());

				updatePage.body().attr("style", "margin-left:-10em");
				data = updatePage.toString();


			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("Error", "failed");
			}

			String[] result = { data, baseUrl };

			return result;
		}
		
		protected void onProgressUpdate(Integer... progress) {

	    	 
	     }

	     protected void onPostExecute(String[] result) {
	    	 String data = result[0];
	    	 String baseUrl = result[1];
	    	 
	    	 loadUSDriverPage(data, baseUrl);
	     }
	}
}
