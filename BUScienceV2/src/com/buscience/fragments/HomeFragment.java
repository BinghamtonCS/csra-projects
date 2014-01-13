package com.buscience.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.buscience.activities.AboutUsActivity;
import com.buscience.activities.CalendarActivity;
import com.buscience.activities.LessonActivity;
import com.buscience.activities.MainActivity;
import com.buscience.activities.R;

public class HomeFragment extends Fragment {
	
	
	
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        View v = inflater.inflate(R.layout.home_layout, container, false);
	        
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

	 
	 

}
