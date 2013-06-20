package com.example.binghamtonsvc;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class SplashScreen extends Activity{
	
	MediaPlayer aTune;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		aTune = MediaPlayer.create(SplashScreen.this, R.raw.tune);
		aTune.start();
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(2000);
				}
				catch (InterruptedException e){
					e.printStackTrace();
				}
				finally{
					Intent openTheHomeDisplay = new Intent("com.example.binghamtonsvc.MAINACTIVITY");
					startActivity(openTheHomeDisplay);
				}
			}
		};
		timer.start();
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		aTune.release();
		finish();
	}
}
