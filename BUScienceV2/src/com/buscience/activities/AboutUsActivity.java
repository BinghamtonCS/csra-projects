package com.buscience.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AboutUsActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_us_layout);	
		
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        this.setTitle("About");
        return true;
    }
    
	//Refresh button restarts this activity with added animation
    public boolean onRefreshClicked(MenuItem item)
    {
    	finish();
    	startActivity(getIntent());
    	overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    	
    	return true;
    }
    
    //Creates alert dialog when help button is clicked.
    public boolean onHelpClicked(MenuItem item)
    {    	    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	// Add the buttons
    	builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   
    	           }
    	       });
    	
    	builder.setMessage("If you would like to learn more, contact BU Science at buscience1@gmail.com");
    	// Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    	
    	return true;
    }
    
    //Generates an AlertDialog when the user button is clicked
    public boolean onUserClicked(MenuItem item)
    {    	    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	// Add the buttons
    	builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   
    	           }
    	       });
    	
    	builder.setTitle("Developers");
    	builder.setMessage("The project is maintained as open-source by members of the Binghamton ACM Chapter.\n\nDevelopers:\n\nCheng Lin (khuang13@binghamton.edu)\n\nAnna Borovitcky (aborovi1@binghamton.edu)\n\nJames Edouard (jedouar1@binghamton.edu)\n\n\nPlease email us with bugs, fixes, or improvements you would like to see.\n\nNew project ideas or proposals are also welcome.\n\n\nContact acm.projects@binghamton.edu");

    	// Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    	
    	return true;
    	
    }
    
    

}
