package com.buscience.fragments;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.buscience.activities.MainActivity;
import com.buscience.activities.R;

public class CertificationFragment extends Fragment {
	
	public int selectedTab;
	ActionBar actionbar;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
       
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.certification_layout, container, false);
        
        actionbar = this.getActivity().getActionBar();
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.Tab presTab = actionbar.newTab().setText("Presentation");
        ActionBar.Tab quesTab = actionbar.newTab().setText("Questions");
                
    	final ProgressBar pbar = (ProgressBar)v.findViewById(R.id.progressBar);
    	
        final WebView web = (WebView)v.findViewById(R.id.certView);
        web.setWebViewClient(new WebViewClient(){
        	
        	@Override
        	public void onPageStarted(WebView view, String url, Bitmap favicon)
        	{
        		pbar.setVisibility(ProgressBar.VISIBLE);
        		web.setVisibility(View.GONE);
        	}
        	
        	@Override
        	public void onPageFinished(WebView view, String url)
        	{
        		pbar.setVisibility(ProgressBar.GONE);
        		web.setVisibility(View.VISIBLE);
        		
        	}
        	

        });
        web.getSettings().setJavaScriptEnabled(true);
        
        String presentationUrl = "http://docs.google.com/presentation/d/1zboHLCHmEIn1DTxSJM4GbL_4LQmlqN9URu39I52CG1c/embed?hl=en&amp;size=l";
        String questionUrl = "https://docs.google.com/spreadsheet/embeddedform?bc=transparent&f=%2522Georgia%2522%252C%2Bserif&formkey=dHpJbk1WWjljVFdJQzNBZ0FQTFpzNVE6MA&hl=en&htc=%2523666666&lc=%2523135355&pli=1&tc=%2523565555&ttl=0";
        
        presTab.setTabListener(new CertTabsListener(web, presentationUrl));
        quesTab.setTabListener(new CertTabsListener(web, questionUrl));
        
        actionbar.addTab(presTab);
        actionbar.addTab(quesTab);
        
        if (savedInstanceState != null) {
            // Select the tab that was selected before orientation change
            int index = savedInstanceState.getInt("SELECTED_TAB_INDEX");
            actionbar.setSelectedNavigationItem(index);
            
        }

        
        return v;
        
        
    }
    
    @Override
    public void onResume()
    {
    	super.onResume();
    	this.getActivity().setTitle("Certification");
    	
    	MainActivity act = ((MainActivity)(this.getActivity()));
         act.getMDrawerList().setItemChecked(2, true);
         act.getMDrawerList().setSelection(2);
         act.setCurrentPosition(2);
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        
    	super.onActivityCreated(savedInstanceState);
    	
    	this.getActivity().setTitle("Certification");
        MainActivity act = ((MainActivity)(this.getActivity()));

        act.getMDrawerList().setItemChecked(2, true);
        act.getMDrawerList().setSelection(2);
        act.setCurrentPosition(2);
        	        
    }
    
    @Override
    public void onStop()
    {
    	super.onStop();
    	actionbar.removeAllTabs();
    	actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    	
    	
    }
    
    @Override
    public void onSaveInstanceState(Bundle outState) {   
        super.onSaveInstanceState(outState);
        int i = this.getActivity().getActionBar().getSelectedNavigationIndex();
        outState.putInt("SELECTED_TAB_INDEX", i);
        
    }
    
    public class CertTabsListener implements TabListener {

        public WebView web;
        public String url;

        public CertTabsListener(WebView web, String url){
        	this.web = web;
        	this.url = url;
        	
        }

        @Override
        public void onTabReselected(Tab tab, FragmentTransaction ft) {
        	web.loadUrl(url);
        	

        }

        @Override
        public void onTabSelected(Tab tab, FragmentTransaction ft) {
        	web.loadUrl(url);
        	
                   
        }

        @Override
        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        	
        }
        
}
    

    

}
