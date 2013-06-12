package com.example.buscience;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;

public class HomeActivity extends Activity
{
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        
        int[] slideImgs = {R.drawable.img001, R.drawable.img002, R.drawable.img003,
        		R.drawable.img004, R.drawable.img005, R.drawable.img006,
        		R.drawable.img007, R.drawable.img008, R.drawable.img009};
        
        ViewFlipper flipper = ((ViewFlipper)findViewById(R.id.viewFlipper1));
//        LayoutParams params = (LayoutParams)flipper.getLayoutParams();
//        System.out.println(params.width);
//        params.height = (int)(params.width * .75 + .5);
//        flipper.setLayoutParams(params);
        
        for (int i = 0; i < slideImgs.length; i++)
        {
            ImageView image = new ImageView(getApplicationContext());
            image.setBackgroundResource(slideImgs[i]);
            flipper.addView(image);
        }        
        flipper.startFlipping();

        flipper.setOnClickListener(new OnClickListener() 
        {
            public void onClick(View v) 
            {
            	ViewFlipper flipper = ((ViewFlipper)findViewById(R.id.viewFlipper1));
            	flipper.stopFlipping();
            	flipper.showNext();
            	flipper.startFlipping();
            }
        });
    }
}
