//package com.buscience.fragments;
//
//import java.io.IOException;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//
//import android.app.Fragment;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.ProgressBar;
//
//import com.buscience.activities.ElemTeacherRegActivity;
//import com.buscience.activities.R;
//
//public class ETScheduleFragment extends Fragment {
//	View view; 
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//    	view = inflater.inflate(R.layout.et_regi_layout, container, false);        
//    	new ETScheduleLoader().execute("http://www.buscience.org/Registration/school-teacher-sign-up");
//    	
//        return view;       
//        
//    }
//    
//    public void loadETSchedulePage(String data, String baseUrl)
//    {
//    	final ProgressBar pbar = (ProgressBar)view.findViewById(R.id.progressBar);
//    	
//        WebView web = (WebView)view.findViewById(R.id.web_et_regi);
//        pbar.setVisibility(ProgressBar.VISIBLE);
//        web.setWebViewClient(new WebViewClient()
//        {
//        	
//        	@Override
//        	public void onPageFinished(WebView view, String baseUrl)
//        	{
//        		pbar.setVisibility(ProgressBar.GONE);
//        	}
//        	
//        });
//        web.loadUrl(baseUrl);
//    }
//    
//    @Override
//    public void onResume()
//    {
//    	super.onResume();
//    	this.getActivity().setTitle("Current Schedule");
//    	
//    	ElemTeacherRegActivity act = ((ElemTeacherRegActivity)(this.getActivity()));
//         act.getMDrawerList().setItemChecked(2, true);
//         act.getMDrawerList().setSelection(2);
//         act.setCurrentPosition(2);
//    }
//    
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        
//    	super.onActivityCreated(savedInstanceState);
//    	
//    	this.getActivity().setTitle("Current Schedule");
//    	ElemTeacherRegActivity act = ((ElemTeacherRegActivity)(this.getActivity()));
//
//        act.getMDrawerList().setItemChecked(2, true);
//        act.getMDrawerList().setSelection(2);
//        act.setCurrentPosition(2);
//        	        
//    }
//    
//    private class ETScheduleLoader extends AsyncTask<String, Integer, String[]> {
//	     protected String[] doInBackground(String... urls) {
//				String data = "";
//				String baseUrl = "";
//				
//				try {
//					Document doc = Jsoup.connect(urls[0]).get();
//					baseUrl = doc.select("iframe[title*=Display]").attr("src");
//					Log.e("Error", baseUrl);
//					
//					Document schedule = Jsoup.connect(baseUrl).get();
//					data = schedule.toString();
//					
//					
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			
//			String[] result = { data, baseUrl };
//				
//	         return result;
//	     }
//
//	     protected void onProgressUpdate(Integer... progress) {
//
//	    	 
//	     }
//
//	     protected void onPostExecute(String[] result) {
//	    	 String data = result[0];
//	    	 String baseUrl = result[1];
//	    	 
//	    	 loadETSchedulePage(data, baseUrl);
//	     }
//   }
//}
