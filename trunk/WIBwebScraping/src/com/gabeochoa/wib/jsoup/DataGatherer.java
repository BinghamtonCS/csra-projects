package com.gabeochoa.wib.jsoup;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class DataGatherer {

	private String rootURL = null;
	private Document wibSite = null;
	private ArrayList<Person> members = null;
	
	private ArrayList<String> facebookTitles = null;
	private ArrayList<String> facebookContent = null;
	private ArrayList<FacebookPost> posts;
	private ArrayList<Event> events;
	
	public DataGatherer()
	{
		super();
	}
	
	public DataGatherer(String ru)
	{
		super();
		rootURL = ru;
		connectTo(ru);
	}
	
	public boolean connectTo(String ru)
	{
		try {
			wibSite = Jsoup.connect(ru).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wibSite == null;
	}
	public void getAbout()
	{
		if(connectTo("http://www.binghamtonwib.com/about-us/"))
		{
			Elements titles = wibSite.select("h3");
			//NotFinished
		}
	}
	public ArrayList<Person> getPeople()
	{
		members = GetPeople.main(null);
		return members;
	}
	
	public ArrayList<FacebookPost> getFacebookFeed()
	{
		posts = GetFacebookFeed.main(null);
		return posts;
	}
	
	public ArrayList<Event> getEvents()
	{
		events = GetCalendar.get();
		return events;
	}
}
