package com.example.buscience;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class HomeActivity extends Activity
{
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 100;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;

    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.home_layout);
        recreateView();
    }
    
    private void recreateView()
    {
    	Display display = getWindowManager().getDefaultDisplay();
        int[] slideImgs = {R.drawable.img001, R.drawable.img002, R.drawable.img003,
        		R.drawable.img004, R.drawable.img005, R.drawable.img006,
        		R.drawable.img007, R.drawable.img008, R.drawable.img009};
        
        ViewFlipper flipper = ((ViewFlipper)findViewById(R.id.viewFlipper1));
        flipper.getLayoutParams().height = (int)(display.getWidth() * .75 + .5);
        
        for (int i = 0; i < slideImgs.length; i++)
        {
            ImageView image = new ImageView(getApplicationContext());
            image.setBackgroundResource(slideImgs[i]);
            flipper.addView(image);
        }        
        flipper.startFlipping();

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
    
    public void goToFacebook(View view) {
    	startActivity(new Intent(this, FacebookActivity.class));
    }
    
    public void joinUsClicked(View view) {
    	MainActivity.getCurrentTabHost().setCurrentTab(1);
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
}
