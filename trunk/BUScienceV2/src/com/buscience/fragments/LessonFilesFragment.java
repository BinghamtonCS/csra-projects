package com.buscience.fragments;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.buscience.activities.R;
import com.buscience.slidingmenu.NavDrawerItem;
import com.buscience.slidingmenu.NavDrawerListAdapter;

public class LessonFilesFragment extends Fragment {
	
    private ListView mDrawerList;
 
    // download menu items
    private TypedArray navMenuIcons;
 
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
	
    ArrayList<String> titles;
    ArrayList<String> links;
    ArrayList<String> gdocs;
    
    View v;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.lesson_files, container, false);
        
        return v;
 
    }
    
    @Override
    public void onStart()
    {
    	super.onStart();
    	
		new ParseLinkTask().execute("http://www.buscience.org/home/lesson-plans");
    }
    
    
	@SuppressLint("NewApi")
	public void generateDownloadList()
	{
        mDrawerList = (ListView)v.findViewById(R.id.lesson_downloads);
 
        navDrawerItems = new ArrayList<NavDrawerItem>();
 
        populateNavDrawerItems(navDrawerItems);
 
        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getActivity().getApplicationContext(),
                navDrawerItems);
        
        mDrawerList.setAdapter(adapter);
 
        // enabling action bar app icon and behaving it as toggle button
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
   
        if ( Build.VERSION.SDK_INT >= 14)
        {
        	getActivity().getActionBar().setHomeButtonEnabled(true);
        }
        
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
        
	}
	
	  /**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                long id) {
            // display view for selected nav drawer item
        		promptDownload(position);

        }
    }
    
    //Prompts user for download or viewing. Returns relevant link in the devices default browser
    public void promptDownload(int position)
    {
    	final int currentPosition = position;
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	// Add the buttons
    	builder.setNegativeButton("View File", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(gdocs.get(currentPosition))));

    	           }
    	       });
    	builder.setNeutralButton("Download", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(links.get(currentPosition))));
    	           }
    	       });
    	
    	builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	        	   //Do nothing. Just close dialog.
	           }
	       });
    	
    	builder.setMessage("Would you like to download the file or open it for viewing?");
        builder.setTitle(titles.get(currentPosition));

    	// Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
	
    //Uses generated array lists to populate drawer items 
	public void populateNavDrawerItems(ArrayList<NavDrawerItem> navDrawerItens)
	{
		for ( int i = 0; i < titles.size(); i++ )
		{
			navDrawerItems.add(new NavDrawerItem("\n" + titles.get(i) + "\n", R.drawable.ic_action_download));
		}
	}
	
	// Creates an sSyncTask where Jsoup is used to search for download titles and files at the specified URL
	private class ParseLinkTask extends AsyncTask<String, Integer, ArrayList<ArrayList<String>>> {
	     protected ArrayList<ArrayList<String>> doInBackground(String... urls) {
				ArrayList<String> links = new ArrayList<String>();
				ArrayList<String> titles = new ArrayList<String>();
				ArrayList<String> gdocs = new ArrayList<String>();
				
				String baseUrl = "http://www.buscience.org";
				
				try {
					Document doc = Jsoup.connect(urls[0]).get();
					Elements elements = doc.select("a[title*=Download]");
					for ( Element ele : elements )
					{
						String link = ele.attr("href");
						if (!link.contains(baseUrl))
						{
							link =baseUrl+link;
						}
						links.add(link);
					}
					
					elements = doc.select("a[href*=docs.google.com]");
					for ( Element ele : elements )
					{
						String title = ele.text();
						titles.add(title);
					}
					
					elements = doc.select("a[href*=docs.google.com]");
					for ( Element ele : elements )
					{
						String gdoc = ele.attr("href");
						gdocs.add(gdoc);
					}
					
					Log.e("Test", links.toString());
					Log.e("Test", titles.toString());
					Log.e("Test", gdocs.toString());
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
			array.add(titles);
			array.add(links);
			array.add(gdocs);
				
	        return array;
	     }

	     protected void onProgressUpdate(Integer... progress) {

	    	 
	     }

	     protected void onPostExecute(ArrayList<ArrayList<String>> array) {
	    	 titles = array.get(0);
	    	 links = array.get(1);
	    	 gdocs = array.get(2);
	    	 
	    	 generateDownloadList();	    	 
	    	 
	     }
 }

}
