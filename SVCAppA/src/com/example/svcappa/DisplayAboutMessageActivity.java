package com.example.svcappa;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class DisplayAboutMessageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_about_message);
	}
	
	public void displayHome(View view)
	{
		// Do something in response to button
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}


}
