package com.example.svcdev;

import Parsers.GetMembershipInfo;
import Parsers.GetMissionStatement;
import Parsers.GetVolunteerHours;
import android.content.Context;
import android.graphics.Typeface;
import android.os.StrictMode;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseExpandableListAdapter {
	
	private Context context;
	private String[] parentList;
	private String[][] childList;
	
	public void initialize() {
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build();	
		StrictMode.setThreadPolicy(policy);
		
		GetMissionStatement getMS = new GetMissionStatement();
		GetMembershipInfo getMI = new GetMembershipInfo();
		GetVolunteerHours getVH = new GetVolunteerHours();
		
		String missionStatement = "Loading...";
		String membershipInfo = "Loading...";
		String volunteerHours = "Loading...";
		String eboard ="See Contact Tab";
		
		try {
			missionStatement = getMS.getInternetData();		
			membershipInfo = getMI.getInternetData();
			volunteerHours = getVH.getInternetData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		parentList = new String[]{"Mission Statement", "Membership", "Volunteer Hours", "Current Executive Board"};
		childList = new String[][]{
				{
					missionStatement
				},
				{
					membershipInfo
				},
				{
					volunteerHours
				},
				{
					eboard
				}
		};
	}
	
	
	public MyAdapter(Context c) {
		initialize();
		context = c;
	}

	@Override
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		TextView tv = new TextView(context);
		tv.setText(Html.fromHtml(childList[groupPosition][childPosition]));
		tv.setPadding(80, 10, 10, 10);
		tv.setTextSize(25);
		return tv;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return childList[groupPosition].length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return parentList.length;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		TextView tv = new TextView(context);
		tv.setText(parentList[groupPosition]);
		tv.setPadding(50, 10, 10, 10);
		tv.setTextSize(30);
		tv.setTypeface(null, Typeface.BOLD);
		return tv;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}
