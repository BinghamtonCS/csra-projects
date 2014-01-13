package com.buscience.fragments;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

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

import com.buscience.activities.R;
import com.buscience.activities.UnivStudentRegActivity;

public class USUpdateFragment extends Fragment {
	View view; 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	view = inflater.inflate(R.layout.us_regi_layout, container, false);        
    	new USUpdateLoader().execute("http://www.buscience.org/Registration/university-student-sign-up");
    	
        return view;       
        
    }
    
    public void loadUSUpdatePage(String data, String baseUrl)
    {
    	final ProgressBar pbar = (ProgressBar)view.findViewById(R.id.progressBar);
    	
        WebView web = (WebView)view.findViewById(R.id.web_us_regi);
        pbar.setVisibility(ProgressBar.VISIBLE);
        
        
        web.setWebViewClient(new WebViewClient()
        {
        	
        	@Override
        	public void onPageFinished(WebView view, String baseUrl)
        	{
        		pbar.setVisibility(ProgressBar.GONE);
        	}
        	
        });
        web.loadDataWithBaseURL(baseUrl, data, "text/html", "utf-8", null);
        
        
    }
    
    @Override
    public void onResume()
    {
    	super.onResume();
    	this.getActivity().setTitle("Update");
    	
    	UnivStudentRegActivity act = ((UnivStudentRegActivity)(this.getActivity()));
         act.getMDrawerList().setItemChecked(1, true);
         act.getMDrawerList().setSelection(1);
         act.setCurrentPosition(1);
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        
    	super.onActivityCreated(savedInstanceState);
    	
    	this.getActivity().setTitle("Update");
    	UnivStudentRegActivity act = ((UnivStudentRegActivity)(this.getActivity()));

        act.getMDrawerList().setItemChecked(1, true);
        act.getMDrawerList().setSelection(1);
        act.setCurrentPosition(1);
        	        
    }
    
    private class USUpdateLoader extends AsyncTask<String, Integer, String[]> {
	     protected String[] doInBackground(String... urls) {
				String data = "";
				String baseUrl = "";
				
				try {
					Document doc = Jsoup.connect(urls[0]).get();
					Log.e("Parse", doc.toString());
					baseUrl = doc.select("iframe[title*=Update]").attr("src");
					Log.e("Parse2", baseUrl);
					
					Document updatePage = Jsoup.connect(baseUrl).get();
					Log.e("Parse3", updatePage.toString());
					
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
	    	 
	    	 loadUSUpdatePage(data, baseUrl);
	     }
   }

}
