package Fragments;

import com.example.svcdev.R;

import Parsers.GetEBoard;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContactSectionFragment extends Fragment {
	
	View rootView;
	TextView eboard;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build();	
		StrictMode.setThreadPolicy(policy);
		rootView = inflater.inflate(R.layout.fragment_section_contact, container, false);	
		eboard = (TextView) rootView.findViewById(R.id.tvEboard);
		GetEBoard getter = new GetEBoard();
		try {
			String returned = getter.getInternetData();
			eboard.setText(Html.fromHtml(returned));
			eboard.setTextSize(19);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rootView;
	}
}
