package com.example.svcdev;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
 
public class SplashScreen extends Activity 
{
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 500;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getActionBar().hide();
        new Handler().postDelayed(new Runnable() 
        {

            @Override
            public void run() 
            {
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                
                finish();
            }
        }, SPLASH_TIME_OUT);
    }    
}