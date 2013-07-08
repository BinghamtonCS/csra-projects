package com.example.svcdev;

import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

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
	ImageButton goFB;
	ImageButton goTwit;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_section_launchpad, container, false);
		
		showMeTheMoney = (ImageView) rootView.findViewById(R.id.iv1);
		showMeTheMoney2 = (ImageView) rootView.findViewById(R.id.iv2);
		showMeTheMoney3 = (ImageView) rootView.findViewById(R.id.iv3);
		showMeTheMoney4 = (ImageView) rootView.findViewById(R.id.iv4);
		showMeTheMoney5 = (ImageView) rootView.findViewById(R.id.iv5);
		showMeTheMoney6 = (ImageView) rootView.findViewById(R.id.iv6);
		goFB = (ImageButton) rootView.findViewById(R.id.ibFacebook);
		goTwit = (ImageButton) rootView.findViewById(R.id.ibTwitter);
		goFB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = getOpenFacebookIntent(rootView.getContext());
				startActivity(i);
			}
		});
		goTwit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = getOpenTwitterIntent(rootView.getContext());
				startActivity(i);
			}
		});
		new DownloadImageTask().execute("https://dl.dropboxusercontent.com/u/93134791/SVC%20Images/1.jpg");
		return rootView;
	}
	
	public static Intent getOpenFacebookIntent(Context context) {
		   try {
		    return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/127668970580820"));
		   } catch (Exception e) {
		    return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/binghamtonsvc"));
		   }
		}
	
	public static Intent getOpenTwitterIntent(Context context) {
		try {
			return new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=binghamtonsvc"));
		}catch (Exception e) {
			return new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!/binghamtonsvc")); 
			} 
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
}