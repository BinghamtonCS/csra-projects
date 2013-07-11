package Fragments;

import com.example.svcdev.MyAdapter;
import com.example.svcdev.R;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class AboutSectionFragment extends Fragment {
	
	ExpandableListView exv;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_section_about, container, false);
		exv = (ExpandableListView) rootView.findViewById(R.id.expandableListView1);
		Context c = getActivity();
		exv.setAdapter(new MyAdapter(c));
		return rootView;
	}
}