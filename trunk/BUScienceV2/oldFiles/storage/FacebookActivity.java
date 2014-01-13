package storage;

import java.net.URL;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.PluginState;
import android.widget.Button;
import android.widget.TextView;

public class FacebookActivity extends Activity 
{
	WebView facebook;
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		super.onCreate(savedInstanceState);
		
		boolean requestPassed = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_facebook);
		
		facebook = (WebView) findViewById(R.id.facebook);
		facebook.setWebViewClient(new WebViewClient());
		
		facebook.getSettings().setBuiltInZoomControls(true);
		
		facebook.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

		facebook.loadUrl("https://www.facebook.com/bu.scyence");
//		String html = "<html><body>Hello :D\"<embed type=\"application/x-shockwave-flash\" src=\"https://static.googleusercontent.com/external_content/picasaweb.googleusercontent.com/slideshow.swf\" width=\"400\" height=\"267\" flashvars=\"host=picasaweb.google.com&hl=en_US&feat=flashalbum&RGB=0x000000&feed=https%3A%2F%2Fpicasaweb.google.com%2Fdata%2Ffeed%2Fapi%2Fuser%2F103982079452833410347%2Falbumid%2F5941097132524449857%3Falt%3Drss%26kind%3Dphoto%26hl%3Den_US\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\"></embed></body></html>";
//		String mime = "text/html";
//		String encoding = "utf-8";

//		facebook.getSettings().setPluginState(PluginState.ON);
//		facebook.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//		facebook.getSettings().setJavaScriptEnabled(true);
//		facebook.loadDataWithBaseURL(null, html, mime, encoding, null);
	
		
		if (requestPassed) {
			getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar_layout);
			((TextView)findViewById(R.id.titleHeading1)).setText("Facebook");
		}

		
		((Button)findViewById(R.id.btnBack)).setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) {
				onBackPressed();				
		    }
		});
	    
		/*
		((Button)findViewById(R.id.btnReload)).setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) {	

			}
		});*/
	}
}