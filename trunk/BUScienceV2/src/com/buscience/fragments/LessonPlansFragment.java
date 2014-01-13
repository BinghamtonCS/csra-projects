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

import com.buscience.activities.R;

public class LessonPlansFragment extends Fragment {

	View v;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.lesson_plans, container, false);
		
        //new CalendarLoader().execute("http://www.buscience.org/home/lesson-plans");
        
        return v;
        
    }
    
    public void onStart()
    {
    	super.onStart();
        new CalendarLoader().execute("http://www.buscience.org/home/lesson-plans");
    }
    
    public void loadLesson(String url)
    {
    	final ProgressBar pbar = (ProgressBar)v.findViewById(R.id.progressBar);
		
		final WebView lesson = (WebView)v.findViewById(R.id.lesson_grid);
		lesson.setWebViewClient(new WebViewClient()
		{
			@Override
        	public void onPageFinished(WebView view, String ur)
        	{
        		pbar.setVisibility(ProgressBar.GONE);
        	}
			
		});
		lesson.getSettings().setUserAgentString("Mozilla/5.0");
		lesson.getSettings().setBuiltInZoomControls(true);
		lesson.getSettings().setJavaScriptEnabled(true);
		
		lesson.loadUrl(url);
    }
    
    private class CalendarLoader extends AsyncTask<String, Integer, String> {
	     protected String doInBackground(String... urls) {
		String link = "";
				
				
				
				try {
					Document doc = Jsoup.connect(urls[0]).get();
					link = doc.select("iframe").attr("src");
	
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	         return link;
	     }

	     protected void onProgressUpdate(Integer... progress) {

	    	 
	     }

	     protected void onPostExecute(String link) {
	    	 loadLesson(link);
	     }
 }

}
