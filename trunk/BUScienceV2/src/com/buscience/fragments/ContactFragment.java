package com.buscience.fragments;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.buscience.activities.MainActivity;
import com.buscience.activities.R;

public class ContactFragment extends Fragment {
    
	View view;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
    	view = inflater.inflate(R.layout.contact_layout, container, false);
    	new ContactLoader().execute("http://www.buscience.org/Contact");
        
    	return view;
    }
    
    @Override
    public void onResume()
    {
    	super.onResume();
    	this.getActivity().setTitle("Contact");
    	
    	MainActivity act = ((MainActivity)(this.getActivity()));
         act.getMDrawerList().setItemChecked(4, true);
         act.getMDrawerList().setSelection(4);
         act.setCurrentPosition(4);
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        
    	super.onActivityCreated(savedInstanceState);
    	
    	this.getActivity().setTitle("Contact");
        MainActivity act = ((MainActivity)(this.getActivity()));

        act.getMDrawerList().setItemChecked(4, true);
        act.getMDrawerList().setSelection(4);
        act.setCurrentPosition(4);
        	        
    }
    
    public void loadContactPage(final String pageUrl)
    {
    	final ProgressBar pbar = (ProgressBar)view.findViewById(R.id.progressBar);
    	//pbar.setVisibility(ProgressBar.VISIBLE);
  
        
        WebView web = (WebView)view.findViewById(R.id.eboard);
        web.setWebViewClient(new WebViewClient()
        {
        	
        	@Override
        	public void onPageFinished(WebView view, String url)
        	{
        		pbar.setVisibility(ProgressBar.GONE);
        	}
        	
        	 public boolean shouldOverrideUrlLoading(WebView view, String url)  {                  

        		 final String fUrl = url;
        		 
        		 if  ( url.contains("mailto:")){
                  
        			 AlertDialog.Builder builder = new AlertDialog.Builder(ContactFragment.this.getActivity());
                  	// Add the buttons
        			 
                   	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
           	           public void onClick(DialogInterface dialog, int id) {
                         	Intent intent = new Intent(Intent.ACTION_VIEW);
                         	intent.setData(Uri.parse(fUrl));
                         	startActivity(intent);
                         	
                         	/*Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                         	emailIntent.setType("text/plain");
                         	startActivity(emailIntent); */
           	           }
           	       });
                   	
                  	builder.setNeutralButton("No", new DialogInterface.OnClickListener() {
                  	           public void onClick(DialogInterface dialog, int id) {
                  	        	   
                  	           }
                  	       });
                  	
                  	builder.setMessage("Would you like to send an email to " + url.replace("mailto:", "") + "?");

                  	// Create the AlertDialog
                      AlertDialog dialog = builder.create();
                      dialog.show();

                     
        		 }
        		 else{
        			 view.loadUrl(url);

        		 }
        		 
                 return true;    
             }
        	
      
        	/*@Override
        	public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
				
        		AlertDialog.Builder builder = new AlertDialog.Builder(ContactFragment.this.getActivity());
            	// Add the buttons
            	builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            	           public void onClick(DialogInterface dialog, int id) {
            	        	   
            	           }
            	       });
            	
            	builder.setMessage("BU Science is a Binghamton University Club in which our student teachers teach science and engineering concepts to elementary school students. We teach half an hour lessons once a week at George F. Johnson and Charles F. Johnson Elementary Schools in the Union-Endicott School district. ");

            	// Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
        		
                
                
        		return true;
        	}*/
        	        	
        	
        });
        
        /*web.addEventListener("load",function(){
            Ti.API.debug('The URL changed to '+web.url);
        });*/

        web.loadUrl(pageUrl);
    }
    
    private class ContactLoader extends AsyncTask<String, Integer, String> {
	     protected String doInBackground(String... urls) {
				Document doc;
				
				String str = "";
				
				try {
					doc = Jsoup.connect(urls[0]).get();
					str = doc.select("iframe").attr("src");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	         return str;
	     }

	     protected void onProgressUpdate(Integer... progress) {

	    	 
	     }

	     protected void onPostExecute(String result) {
	    	 loadContactPage(result);
	     }
  }
}
