package Fragments;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.svcdev.R;


public class LaunchpadSectionFragment extends Fragment {
	
	View rootView;
	ImageView showMeTheMoney;
	ImageView showMeTheMoney2;
	ImageView showMeTheMoney3;
	ImageView showMeTheMoney4;
	ImageView showMeTheMoney5;
	ImageView showMeTheMoney6;
	Bitmap bmp2;
	Bitmap bmp3;
	Bitmap bmp4;
	Bitmap bmp5;
	Bitmap bmp6;
	final String URL = "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=binghamtonsvc&include_rts=1";
	final String APIKEY = "NzUMT4UjB1bpHfAXO707Q";
	final String APISECRET = "vvNBeseEzWlfZMCwMMMbqE9XQiLAmu1XdvLJwXJUM";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_section_launchpad, container, false);
		
		showMeTheMoney = (ImageView) rootView.findViewById(R.id.iv1);
		showMeTheMoney2 = (ImageView) rootView.findViewById(R.id.iv2);
		showMeTheMoney3 = (ImageView) rootView.findViewById(R.id.iv3);
		showMeTheMoney4 = (ImageView) rootView.findViewById(R.id.iv4);
		showMeTheMoney5 = (ImageView) rootView.findViewById(R.id.iv5);
		showMeTheMoney6 = (ImageView) rootView.findViewById(R.id.iv6);
		
		Button btn_bearer_token = (Button) rootView.findViewById(R.id.bGetBearerToken);
		btn_bearer_token.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new GetBearerTokenTask().execute();
			}
		});
		new DownloadImageTask().execute("https://dl.dropboxusercontent.com/u/93134791/SVC%20Images/1.jpg");
		btn_bearer_token.performClick();
		return rootView;
	}
	
	public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		
		protected Bitmap doInBackground(String... urls) {
			
			return loadImageFromNetwork(urls[0]);
		}
		
		protected void onPostExecute(Bitmap result) {
			showMeTheMoney.setImageBitmap(result);
			showMeTheMoney2.setImageBitmap(bmp2);
			showMeTheMoney3.setImageBitmap(bmp3);
			showMeTheMoney4.setImageBitmap(bmp4);
			showMeTheMoney5.setImageBitmap(bmp5);
			showMeTheMoney6.setImageBitmap(bmp6);
			
			 new CountDownTimer(3000, 1) { 
				 	
	        public void onTick(long millisUntilFinished) { 
	        		HorizontalScrollView hv = (HorizontalScrollView) rootView.findViewById(R.id.hsv);
	            hv.scrollTo((int) (2000 - millisUntilFinished), 0); 
	        } 

	        public void onFinish() { 

	        } 
	     }.start();
		}

		private Bitmap loadImageFromNetwork(String url) {
			try{
				Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
				bmp2 = BitmapFactory.decodeStream((InputStream)new URL("https://dl.dropboxusercontent.com/u/93134791/SVC%20Images/2.jpg").getContent());
				bmp3 = BitmapFactory.decodeStream((InputStream)new URL("https://dl.dropboxusercontent.com/u/93134791/SVC%20Images/3.jpg").getContent());
				bmp4 = BitmapFactory.decodeStream((InputStream)new URL("https://dl.dropboxusercontent.com/u/93134791/SVC%20Images/4.jpg").getContent());
				bmp5 = BitmapFactory.decodeStream((InputStream)new URL("https://dl.dropboxusercontent.com/u/93134791/SVC%20Images/5.jpg").getContent());
				bmp6 = BitmapFactory.decodeStream((InputStream)new URL("https://dl.dropboxusercontent.com/u/93134791/SVC%20Images/6.jpg").getContent());
				return bitmap;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
protected class GetBearerTokenTask extends AsyncTask<Void, Void, String> {
  	
    @Override
protected String doInBackground(Void... params) {
	
	try {
		DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
		HttpPost httppost = new HttpPost("https://api.twitter.com/oauth2/token");
		
		String apiString = APIKEY + ":" + APISECRET;
		String authorization = "Basic " + Base64.encodeToString(apiString.getBytes(), Base64.NO_WRAP);

		httppost.setHeader("Authorization", authorization);
		httppost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		httppost.setEntity(new StringEntity("grant_type=client_credentials"));

		InputStream inputStream = null;
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();

		inputStream = entity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
		StringBuilder sb = new StringBuilder();

		String line = null;
		while ((line = reader.readLine()) != null)
		{
		    sb.append(line + "\n");
		}
		
		return sb.toString();
		
		
	}catch (Exception e){
		Log.e("GetBearerTokenTask", "Error:" + e.getMessage());
		return null;
	}
}

@Override
protected void onPostExecute(String jsonText){
	try {
		JSONObject root = new JSONObject(jsonText);
		String bearer_token = root.getString("access_token");	
		new GetFeedTask().execute(bearer_token, URL);
	}catch (Exception e){
		Log.e("GetBearerTokenTask", "Error:" + e.getMessage());
	}
}
}

protected class GetFeedTask extends AsyncTask<String, Void, String> {

  @Override
protected String doInBackground(String... params) {
	
	try {
		DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
		HttpGet httpget = new HttpGet(params[1]);
		httpget.setHeader("Authorization", "Bearer " + params[0]);
		httpget.setHeader("Content-type", "application/json");

		InputStream inputStream = null;
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();

		inputStream = entity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
		StringBuilder sb = new StringBuilder();

		String line = null;
		while ((line = reader.readLine()) != null)
		{
			sb.append(line + "\n");
		}
		return sb.toString();
	}catch (Exception e){
		Log.e("GetFeedTask", "Error:" + e.getMessage());
		return null;
	}
}

@Override
protected void onPostExecute(String jsonText){
	try {
		//TextView txt = (TextView) rootView.findViewById(R.id.tvTwitter);
		LinearLayout ll = (LinearLayout) rootView.findViewById(R.id.linearloftweets);
		if(((LinearLayout) ll).getChildCount() > 0) 
	    ((LinearLayout) ll).removeAllViews(); 
		ArrayList<String> tweetList = new ArrayList<String>();
		JSONArray yourjsonarray = new JSONArray(jsonText);
		for(int i =0;i<yourjsonarray.length();i++)
		{
		String tweet =yourjsonarray.getJSONObject(i).getString("text");
		
		tweetList.add(tweet);
		}
		for (int i = 0; i < tweetList.size(); i++)
		{
				TextView temp = new TextView(getActivity());
				temp.setText(tweetList.get(i));
				temp.setTypeface(Typeface.SERIF);
				if (i % 2 == 0)
					temp.setBackgroundColor(Color.LTGRAY);
				else
					temp.setBackgroundColor(Color.WHITE);
				temp.setPadding(10, 10, 10, 10);
				ll.addView(temp);
		}
	}catch (Exception e){
		Log.e("GetFeedTask", "Error:" + e.getMessage());
	}
}
}
	
	
}