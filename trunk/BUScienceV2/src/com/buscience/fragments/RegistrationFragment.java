package com.buscience.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.buscience.activities.ElemTeacherRegActivity;
import com.buscience.activities.MainActivity;
import com.buscience.activities.R;
import com.buscience.activities.UnivStudentRegActivity;

public class RegistrationFragment extends Fragment {
    
	Editable ed ;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.registration_layout, container, false);
        
        Button signupOne = (Button)v.findViewById(R.id.elemTeacherSignup);
        
        signupOne.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(RegistrationFragment.this.getActivity(), ElemTeacherRegActivity.class);
				RegistrationFragment.this.startActivity(intent);	
				RegistrationFragment.this.getActivity().overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);

			}       	
        	
        });
        
        
        Button signupTwo = (Button)v.findViewById(R.id.univStudentSignup);
        
        signupTwo.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(RegistrationFragment.this.getActivity(), UnivStudentRegActivity.class);
				RegistrationFragment.this.startActivity(intent);	
				RegistrationFragment.this.getActivity().overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);

			}       	
        	
        });
        

        
        
        
        return v;
    }

    @Override
    public void onResume()
    {
    	super.onResume();
    	this.getActivity().setTitle("Registration");
    	
    	MainActivity act = ((MainActivity)(this.getActivity()));
         act.getMDrawerList().setItemChecked(1, true);
         act.getMDrawerList().setSelection(1);
         act.setCurrentPosition(1);
         
         
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        
    	super.onActivityCreated(savedInstanceState);
    	
    	this.getActivity().setTitle("Registration");
        MainActivity act = ((MainActivity)(this.getActivity()));

        act.getMDrawerList().setItemChecked(1, true);
        act.getMDrawerList().setSelection(1);
        act.setCurrentPosition(1);
        	        
    }
	 
    
}
