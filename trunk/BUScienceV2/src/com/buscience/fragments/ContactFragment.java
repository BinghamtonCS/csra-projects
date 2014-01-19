package com.buscience.fragments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
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
    public void onActivityCreated(Bundle savedInstanceState) 
    {    
    	super.onActivityCreated(savedInstanceState);
    	
    	this.getActivity().setTitle("Contact");
        MainActivity act = ((MainActivity)(this.getActivity()));

        act.getMDrawerList().setItemChecked(4, true);
        act.getMDrawerList().setSelection(4);
        act.setCurrentPosition(4); 
    }
    
    public void loadContactPage(String url)
    {
    	final ProgressBar pbar = (ProgressBar)view.findViewById(R.id.progressBar);
    	//pbar.setVisibility(ProgressBar.VISIBLE);
  
        WebView web = (WebView)view.findViewById(R.id.eboard);
        web.setWebViewClient(new WebViewClient()
        {
        	@Override
        	public void onPageFinished(WebView view, String ur)
        	{
        		pbar.setVisibility(ProgressBar.GONE);
        	}
        });

        web.loadUrl(url);
    }
    
	public void getEboard(String url)
	{
		try
		{
			HttpClient c = new DefaultHttpClient();
			HttpGet get = new HttpGet();
			get.setURI(new URI(url));

			HttpResponse response = c.execute(get);
			BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			String s;
			while((s=in.readLine())!=null){
				//layout.add(text field)
				//look for eboard info
				//frames
				//textviews
				//pictures
				//email links
			}
			in.close();
		}catch(Exception e){

		}
	}
    
    private class ContactLoader extends AsyncTask<String, Integer, String> 
    {
		protected String doInBackground(String... urls) 
	    {
			Document doc;
			String str = "";
			
			try {
				//getEboard("https://docs.google.com/document/preview?hgd=1&id=1ah8eefcuLkjBZ07CPhLgW51N2Ck6osb4E9S4KGR3pIA&pli=1");
				//		or following two lines
				doc = Jsoup.connect(urls[0]).get();
				str = doc.select("iframe").attr("src");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
	        return str;
	    }

	    protected void onProgressUpdate(Integer... progress) {}

	    protected void onPostExecute(String result) 
	    {
	    	//dynamically add components to view
	    	// 		or the follwoing
	    	loadContactPage(result);
	    }
	}
}
