package com.example.navigationapplication;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Button buttonLeft;
	private Button buttonRight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initializeUI();
	}

	private void initializeUI() {
		buttonLeft = (Button) findViewById(R.id.navigationButtonLeft);
		buttonRight = (Button) findViewById(R.id.navigationButtonRight);
		buttonLeft.setOnClickListener(this);
		buttonRight.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) 
	{
		Intent i;
		switch(v.getId())
		{
		case R.id.navigationButtonLeft:
			i = new Intent(this, LeftActivity.class);
			startActivity(i);
			break;
		case R.id.navigationButtonRight:
			Toast.makeText(this, "Wrong Button!", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}	
	}
}
