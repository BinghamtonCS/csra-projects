package com.buscience.fragments;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.buscience.activities.AboutUsActivity;
import com.buscience.activities.CalendarActivity;
import com.buscience.activities.LessonActivity;
import com.buscience.activities.MainActivity;
import com.buscience.activities.R;
import com.google.gdata.client.Query;
import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.data.photos.GphotoEntry;
import com.google.gdata.data.photos.PhotoEntry;
import com.google.gdata.data.photos.UserFeed;
import com.google.gdata.util.ServiceException;

public class HomeFragment extends Fragment {
	
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 100;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;
	
    View v;
	ViewFlipper flipper;
	
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        v = inflater.inflate(R.layout.home_layout, container, false);
	        
	        TextView aboutUs = (TextView)v.findViewById(R.id.aboutUs);
	        aboutUs.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(HomeFragment.this.getActivity(), AboutUsActivity.class);
					HomeFragment.this.startActivity(intent);	
					HomeFragment.this.getActivity().overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);

				}       	
	        	
	        });
	        
	        
	        TextView joinUs = (TextView)v.findViewById(R.id.joinUs);
	        joinUs.setOnClickListener(new OnClickListener(){

				@SuppressLint("NewApi")
				@Override
				public void onClick(View arg0) {
					Fragment fragment = new RegistrationFragment();
					
		            FragmentManager fragmentManager = getFragmentManager();
		            FragmentTransaction ft = fragmentManager.beginTransaction();
		            if ( Build.VERSION.SDK_INT >= 13)
		            {
		            	ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left);
		            }
		            ft.addToBackStack(null);
		            
		            ft.replace(R.id.fragment_container, fragment).commit();

				}       	
	        	
	        });
	        
	        
	        TextView lessonPlans = (TextView)v.findViewById(R.id.lessonPlan);
	        lessonPlans.setOnClickListener(new OnClickListener(){

				
	        	@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(HomeFragment.this.getActivity(), LessonActivity.class);
					HomeFragment.this.startActivity(intent);	
					HomeFragment.this.getActivity().overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);

				}       	  	
	        	
	        });
	        
	        TextView calendar = (TextView)v.findViewById(R.id.calendarText);
	        calendar.setOnClickListener(new OnClickListener(){

	        	@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(HomeFragment.this.getActivity(), CalendarActivity.class);
					HomeFragment.this.startActivity(intent);	
					HomeFragment.this.getActivity().overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);

				}       	  	
	        	
	        });
	        
	        ImageButton facebook = (ImageButton)v.findViewById(R.id.facebookButton);
	        facebook.setOnClickListener(new OnClickListener(){

				
	        	@Override
				public void onClick(View arg0) {
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/bu.scyence"));
					startActivity(browserIntent);
				}       	  	
	        	
	        });
	        
	        Display display = this.getActivity().getWindowManager().getDefaultDisplay();
	    
	        flipper = ((ViewFlipper)v.findViewById(R.id.viewFlipper1));
	        flipper.getLayoutParams().height = (int)(display.getWidth() * .75 + .5);
	        
	        //flipper.setInAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.slide_in_left));
	        //flipper.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.slide_out_right));
	        
	        final GestureDetector myGestureDetector = new GestureDetector(v.getContext(), new MySwipeDetector());
	        flipper.setOnTouchListener(new View.OnTouchListener() 
	        {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					return myGestureDetector.onTouchEvent(event);
				}
			});
	        
	    	new PicassaFetcher().execute("http://www.buscience.org/Contact");
	    	//new PicassaFetcher().doInBackground("lol");

	    	
	        
	   return v;
	 
	 }
	 	 
	    @Override
	    public void onResume()
	    {
	    	super.onResume();
	    	this.getActivity().setTitle("BU Science");
	    	
	    	MainActivity act = ((MainActivity)(this.getActivity()));
	         act.getMDrawerList().setItemChecked(0, true);
	         act.getMDrawerList().setSelection(0);
	         act.setCurrentPosition(0);
	    }
	    
	    @Override
	    public void onActivityCreated(Bundle savedInstanceState) {
	        
	    	super.onActivityCreated(savedInstanceState);
	    	
	    	this.getActivity().setTitle("BU Science");
	        MainActivity act = ((MainActivity)(this.getActivity()));

	        act.getMDrawerList().setItemChecked(0, true);
            act.getMDrawerList().setSelection(0);
            act.setCurrentPosition(0);
	        	        
	    }
	    
	    public void testResults(ArrayList<Bitmap> array)
	    {
	    	for (Bitmap bmp: array)
	    	{
	    		//System.out.println(urls);
	    		//Log.v("TEST", url);
	    		
	    		ImageView imview = new ImageView(v.getContext());
	    		imview.setImageBitmap(bmp);
	    		imview.setScaleType(ScaleType.FIT_XY);
	    		flipper.addView(imview);
	    	}
	    	flipper.startFlipping();
	    	
	    	
	    }
	    
	    class MySwipeDetector extends GestureDetector.SimpleOnGestureListener
	    {
	    	@Override
	        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
	    	{
	    		try {
	                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
	                    return false;
	                
	                //ViewFlipper flipper = ((ViewFlipper)findViewById(R.id.viewFlipper1));
	                flipper.stopFlipping();
	                if(e1.getX() - e2.getX() >= SWIPE_MIN_DISTANCE && Math.abs(velocityX) >= SWIPE_THRESHOLD_VELOCITY) {
	                    flipper.showPrevious();
	                }  else if (e2.getX() - e1.getX() >= SWIPE_MIN_DISTANCE && Math.abs(velocityX) >= SWIPE_THRESHOLD_VELOCITY) {
	                	flipper.showNext();
	                }
	                flipper.startFlipping();
	            } catch (Exception e) {
	                // Do Nothing
	            }
	            return true;
	    	}
	    }
	    
	    
	    private class PicassaFetcher extends AsyncTask<String, Integer, ArrayList<Bitmap>> {
		     protected ArrayList<Bitmap> doInBackground(String... urls) {


		    	PicasawebService myService = new PicasawebService("BUScience");
		    	ArrayList<Bitmap> array = new ArrayList<Bitmap>();

		 		try {
		 			
					URL feedUrl;
					feedUrl = new URL("https://picasaweb.google.com/data/feed/api/user/112346653609704971745");
					Query myQuery = new Query(feedUrl);
					myQuery.setStringCustomParameter("kind", "photo");
					AlbumFeed searchResultsFeed = myService.query(myQuery, AlbumFeed.class);

					for (GphotoEntry photo : searchResultsFeed.getEntries()) {
						String sPhotoId = photo.getId();

						PhotoEntry p = new PhotoEntry(photo);
						String photoUrl = p.getMediaContents().get(0).getUrl();
						
						URL url = new URL(photoUrl);
						Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
						
						array.add(bmp);
						
					}
				
				} catch (IOException e1) {

				} catch (ServiceException e) {

				} 
		 		
		 		return array;
		 		
		     }

		     protected void onProgressUpdate(Integer... progress) {

		    	 
		     }

		     protected void onPostExecute(ArrayList<Bitmap> array) {
		    	 testResults(array);
		     }
	  }

	 
	 

}
