package com.buscience.fragments;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.buscience.activities.MainActivity;
import com.buscience.activities.R;

public class EvaluationFragment extends Fragment {
	
	View view; 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	view = inflater.inflate(R.layout.evaluation_layout, container, false);        
    	new EvaluationLoader().execute("http://www.buscience.org/Evaluation");
    	
        return view;       
        
    }
    
    public void loadEvaluationPage(String url)
    {
    	final ProgressBar pbar = (ProgressBar)view.findViewById(R.id.progressBar);
    	
        WebView web = (WebView)view.findViewById(R.id.evaluation);
        web.setWebViewClient(new WebViewClient()
        {
        	
        	@Override
        	public void onPageFinished(WebView view, String ur)
        	{
        		pbar.setVisibility(ProgressBar.GONE);
        	}
        	
        });
        web.loadDataWithBaseURL("https://docs.google.com/spreadsheet/embeddedform?bc=transparent&f=%2522Georgia%2522%252C%2Bserif&hl=en&htc=%2523666666&key=0AphPhvpO0nOPdGZIU0JaVEYxaTh2UjNaNk1rRWpOenc&lc=%2523135355&pli=1&tc=%2523565555&ttl=0", url, "text/html", "utf-8", null);
    }
    
    @Override
    public void onResume()
    {
    	super.onResume();
    	this.getActivity().setTitle("Evaluation");
    	
    	MainActivity act = ((MainActivity)(this.getActivity()));
         act.getMDrawerList().setItemChecked(3, true);
         act.getMDrawerList().setSelection(3);
         act.setCurrentPosition(3);
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        
    	super.onActivityCreated(savedInstanceState);
    	
    	this.getActivity().setTitle("Evaluation");
        MainActivity act = ((MainActivity)(this.getActivity()));

        act.getMDrawerList().setItemChecked(3, true);
        act.getMDrawerList().setSelection(3);
        act.setCurrentPosition(3);
        	        
    }
    
    private class EvaluationLoader extends AsyncTask<String, Integer, String> {
	     protected String doInBackground(String... urls) {
				Document doc;
				Document evalPage;
				
				String str = "";
				
				
				
				try {
					doc = Jsoup.connect(urls[0]).get();
					str = doc.select("iframe").attr("src");
					
					evalPage = Jsoup.connect(str).get();
					//evalPage.getElementById("entry_4").append("Test").toString();
					evalPage.body().attr("style", "margin-left:-10em");
					str = evalPage.toString();
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	         return str;
	     }

	     protected void onProgressUpdate(Integer... progress) {

	    	 
	     }

	     protected void onPostExecute(String result) {
	    	 loadEvaluationPage(result);
	     }
   }

}
