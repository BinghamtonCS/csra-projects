package com.gabeochoa.wib.jsoup;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.google.gdata.client.calendar.CalendarQuery;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.extensions.EventEntry;
import com.google.gdata.data.extensions.EventFeed;
import com.google.gdata.util.ServiceException;

public class GetCalendar {

	public static void main(String[] args)
	{
		get();	
	}
	
	public static ArrayList<Event> get()
	{
		URL feedUrl = null;
		try {
			feedUrl = new URL(" http://www.google.com/calendar/feeds/WinB%40binghamtonsa.org/public/full");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				CalendarService myService = new CalendarService("WIB Calendar");
				//CalendarQuery cQ = new CalendarQuery(feedUrl);
				
				//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd=HH:mm:ss");
				//Calendar cal = Calendar.getInstance();

				//cQ.setMinimumStartTime(DateTime.parseDateTime(dateFormat.format(cal.getTime())));
				// Send the request and receive the response:
				EventFeed myFeed = null;
				try {
				//	myFeed = myService.query(cQ, EventFeed.class);
					myFeed = myService.getFeed(feedUrl, EventFeed.class);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				List<EventEntry> list = myFeed.getEntries();
				ArrayList<Event> events = new ArrayList<Event>();
				for(EventEntry ee: list)
				{
					String t = ee.getTitle().getPlainText();
					String c = ee.getPlainTextContent();
					String d = "NODATE";//dates arent working correctly right now
					//System.out.println("Start Time: "+ee.getTimes().get(0).getStartTime());
					//System.out.println("End Time: "+ee.getTimes().get(0).getEndTime());
					events.add(new Event(t,c,d));	
					
				}
				return events;
	}
}
