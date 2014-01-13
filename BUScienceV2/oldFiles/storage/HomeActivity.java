package storage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class HomeActivity extends Activity
{
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 100;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;
    
    public ViewFlipper flipper;
    
    static ArrayList<String> links;
	


    public void onCreate(Bundle savedInstanceState) 
    {    	
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.home_layout);
        flipper = ((ViewFlipper)findViewById(R.id.viewFlipper1));
        recreateView();
    }
    
    private void recreateView()
    {
    	String webUrl = "https://picasaweb.google.com/103982079452833410347/Untitled";
    	
    	Display display = getWindowManager().getDefaultDisplay();
        /*int[] slideImgs = {R.drawable.img001, R.drawable.img002, R.drawable.img003,
        				   R.drawable.img004, R.drawable.img005, R.drawable.img006,
        				   R.drawable.img007, R.drawable.img008, R.drawable.img009};*/
        

        flipper.getLayoutParams().height = (int)(display.getWidth() * .75 + .5);
        
        //for (int i = 0; i < slideImgs.length; i++)*/
        
    	ReadingTask task = new ReadingTask(links);
    	task.execute(webUrl);       

        final GestureDetector myGestureDetector = new GestureDetector(this, new MySwipeDetector());
        flipper.setOnTouchListener(new View.OnTouchListener() 
        {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return myGestureDetector.onTouchEvent(event);
			}
		});
        
//      flipper.setOnClickListener(new OnClickListener() 
//      {
//          public void onClick(View v) 
//          {
//          	ViewFlipper flipper = ((ViewFlipper)findViewById(R.id.viewFlipper1));
//          	flipper.stopFlipping();
//          	flipper.showNext();
//          	flipper.startFlipping();
//          }
//      });
    }
    
    public void editFlipper(ImageView image)
    {
    	flipper.addView(image);
    }
    
    public void startFlipper()
    {
    	flipper.startFlipping();
    }
    
    public void aboutUsClicked(View view) 
    {
    	setContentView(R.layout.about_us_layout);
		MainActivity.setTitle("About Us");
		Button backButton = MainActivity.getBackButton();
		backButton.setVisibility(View.VISIBLE);
		MainActivity.setShowBackButton(true);
		backButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) {
        		setContentView(R.layout.home_layout);
                recreateView();
        		MainActivity.setTitle("Home");                		
				v.setVisibility(View.GONE);
				MainActivity.setShowBackButton(false);
			}
		});
    }
    
    public void joinUsClicked(View view) {
    	MainActivity.getCurrentTabHost().setCurrentTab(1);
    }
    
    public void goToFacebook(View view) {
    	startActivity(new Intent(this, FacebookActivity.class));
    }
    
    public void lessonClicked(View view) 
    {
    	setContentView(R.layout.lesson_layout);
		MainActivity.setTitle("Lesson Plans");
		
//		final WebView grid = (WebView)findViewById(R.id.lesson_grid);
//		grid.setWebViewClient(new WebViewClient());
//		grid.getSettings().setUserAgentString("Mozilla/5.0");
//		grid.getSettings().setJavaScriptEnabled(true);
//		grid.setInitialScale(165);
//		grid.loadUrl("https://spreadsheets.google.com/a/binghamton.edu/spreadsheet/preview?key=0AphPhvpO0nOPdEhPampJeW9KMHV2dVRBYTFNbW9fd1E");
		
		Button backButton = MainActivity.getBackButton();
		backButton.setVisibility(View.VISIBLE);
		MainActivity.setShowBackButton(true);
		backButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) {
        		setContentView(R.layout.home_layout);
                recreateView();
        		MainActivity.setTitle("Home");                		
				v.setVisibility(View.GONE);
				MainActivity.setShowBackButton(false);
			}
		});
    }
    
    public void calendarClicked(View view) 
    {    	
    	setContentView(R.layout.calendar_layout);
		MainActivity.setTitle("Calendar");
		
		final WebView calendar = (WebView)findViewById(R.id.calendar);
		calendar.setWebViewClient(new WebViewClient());
		calendar.getSettings().setUserAgentString("Mozilla/5.0");
		calendar.getSettings().setBuiltInZoomControls(true);
		calendar.getSettings().setJavaScriptEnabled(true);
		calendar.setInitialScale(165);
		
		calendar.loadUrl("https://www.google.com/calendar/embed?src=buscience1@gmail.com&color=%23711616&src=binghamton.edu_gtmjlqts6rnihne0lr72b9vbak@group.calendar.google.com&color=%232F6309&src=en.usa%23holiday@group.v.calendar.google.com&color=%23182C57&src=m87bdov769fit6s55mv5q505rs@group.calendar.google.com&color=%23668CD9&src=ns2lclo7qg3kut3cp2cvssat30@group.calendar.google.com&color=%23D96666&ctz=America/New_York&showTitle=0&showNav=1&showDate=1&showTabs=1&showCalendars=0&hl=en");
		
		Button backButton = MainActivity.getBackButton();
		backButton.setVisibility(View.VISIBLE);
		MainActivity.setShowBackButton(true);
		backButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) {
        		setContentView(R.layout.home_layout);
                recreateView();
        		MainActivity.setTitle("Home");                		
				v.setVisibility(View.GONE);
				MainActivity.setShowBackButton(false);
			}
		});
    }
   
    class MySwipeDetector extends GestureDetector.SimpleOnGestureListener
    {
    	@Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
    	{
    		try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                    return false;
                
                ViewFlipper flipper = ((ViewFlipper)findViewById(R.id.viewFlipper1));
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
    
    
    private class ReadingTask extends AsyncTask<String, ViewFlipper, ArrayList<ImageView>> {
   
        public ArrayList<String> links;

        public ReadingTask(ArrayList<String> array) {
            links = new ArrayList<String>();

        }

        @Override
        protected ArrayList<ImageView> doInBackground(String... urlStr) {
        	
        	ArrayList<ImageView> images = new ArrayList<ImageView>();
        	
    		try{
    			// get the requested URL
    			URL u = new URL(urlStr[0]); 
    			// define an input stream and connect it to the URL
    			InputStream in = u.openStream();
    			// connect it to a stream reader 
    			InputStreamReader isr = new InputStreamReader(in);
    			// connected it to a buffered reader to help in case of a slow URL 
    			BufferedReader br = new BufferedReader(isr);	// can use new BufferedReader(new InputStreamReader(u.openStream())), which combine all 3 lines into 1

    			// read it a line at a time
    			String line = "";
    			
    			line = br.readLine();
    			while ( line != null )
    			{
    				feed(links, line);	
    				line = br.readLine();
    				
    			}

    		}
    		catch (IOException e) {
    			System.err.println(e);
    		}
    		
            ImageView image;
            for( int i = 0; i < links.size(); i++)
            {
            	image = new ImageView(getApplicationContext());
            	
                String urldisplay = links.get(i);
                Bitmap bitmap = null;
                try {
                    InputStream in = new java.net.URL(urldisplay).openStream();
                    bitmap = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                
                image.setImageBitmap(bitmap);           	
            
            	images.add(image);
            	
            }        
   
            
    		return images;
        }
        
    	public void feed(ArrayList<String> links, String aLine)
    	{
    		String line = aLine;
    		System.out.println("1.) It is " + line.contains("jpg\",\"height\""));
    		if (line.contains("jpg\",\"height\""))
    		{

    			//System.out.println(line);
    			
    			String beg = "https://lh";
    			String end = ".jpg";
    			String end2 = ".JPG";
    			
    			int begInt = line.indexOf(beg);
    			System.out.println("2.) Beg: " + begInt);
    			int nextInt = -1;
    			
    			while ( nextInt < begInt  )
    			{
    				//System.out.println(2);
    				int endInt = line.indexOf(end);
    				int endInt2 = line.indexOf(end2);
    				
    				if ( endInt == -1 && endInt2 == -1 )
    				{ 
    					break;
    				}
    				else if ( endInt == -1 )
    				{
    					if (endInt2 < begInt)
    					{
    						line = line.replaceFirst(end2, "");
    					}
    					else
    					{
    						nextInt = endInt2;
    					}
    				}
    				else if ( endInt2 == -1)
    				{
    					if (endInt < begInt)
    					{
    						line = line.replaceFirst(end, "");
    					}
    					else
    					{
    						nextInt = endInt;
    					}
    				}
    				else
    				{
    					if ( endInt < endInt2 )
    					{
    						
    						if (endInt < begInt)
    						{
    							line = line.replaceFirst(end, "");

    						}
    						else
    						{
    							nextInt = endInt;
    						}
    					}
    					else
    					{
    						if (endInt2 < begInt)
    						{
    							line = line.replaceFirst(end2, "");
    						}
    						else
    						{
    							nextInt = endInt2;
    						}
    					}
    					System.out.println(endInt);
    					System.out.println(endInt2);
    				}
    				
    				
    				
    			}
    			
    			begInt = line.indexOf(beg);
    			
    			System.out.println("3.) End: " + nextInt);		

    			
    			String subLine = line.substring(begInt, nextInt+4);
    			
    			//System.out.println(subLine);
    			
    			links.add(subLine);
    			
    			String newLine = line.replaceFirst(subLine, "");
    			
    			feed(links, newLine);
    			
    			//System.out.println("SubLine:" + subLine);
    		}
    		
    	}

        protected void onPostExecute(ArrayList<ImageView> images) {
        	
        	for ( ImageView image : images )
        	{
        		HomeActivity.this.editFlipper(image);
        	}
        	
        	HomeActivity.this.startFlipper();
        	
        }

    }
    
}
