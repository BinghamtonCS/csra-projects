package com.gabeochoa.wib.jsoup;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetEvents {

	//past events
	//    http://www.binghamtonwib.com/events/
	//future events
	//    http://www.google.com/calendar/feeds/WinB%40binghamtonsa.org/public/basic
	
	/* 
	
		<div class="row-fluid">
			<div class="wpb_content_element span8 column_container">
				<div class="wpb_wrapper">
					<div class="row-fluid">
						<div class="wpb_content_element span12 wpb_text_column">
							<div class="wpb_wrapper">
								<p>March 21, 2013</p>
								<p><strong>Dance Workshop</strong></p>
								<p>From dancing to &#8220;Thrift Shop&#8221; to 
									salsa dancing, we bonded together learning 
									different kind of dances &#8211; moving our 
									bodies in ways we didn&#8217;t know they could move!
								</p>
							</div> </p>
						</div></p>
					</div></p>
				</div></p>
			</div>
			<div class="wpb_content_element span4 column_container">
				<div class="wpb_wrapper">
					<div class="row-fluid">
						<div class="wpb_content_element span12 wpb_text_column">
							<div class="wpb_wrapper">
								<p>
									<img class="hoverZoomLink" style="border: 2px solid black;"
									 	 alt="Thrift Shop Workshop" 
									 	 src="http://binghamtonwib.com/wp-content/uploads/2013/03/Thrift-Shop-Workshop-300x225.jpg" 
									 	 width="300" height="225" />
								</p>
								<p>&nbsp;</p>
							</div></p>
						</div></p>
					</div></p>
				</div></p>
			</div></p>
		</div>
	 */
	
	public static void main(String[] args)
	{
		
		for(Event e: returnList())
		{
			System.out.println(e.print());
		}
		
		
	}
	
	public static ArrayList<Event> returnList()
	{
		Document wibSite = null;
		
		try {
			wibSite = Jsoup.connect("http://www.binghamtonwib.com/past-events/").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements ev = wibSite.select("div[class=wpb_wrapper]");
		Elements eve = ev.select("div:not(:contains(Events))");
		Elements even = eve.select("p");
		Elements pictureURL = wibSite.select("a[href^=http://binghamtonwib.com/wp-content/uploads/]");	
		
		ArrayList<String> text = new ArrayList<String>();
		boolean match = false;
		for(Element e : even)
		{
			for(String str: text)
				{
					if(str.equals(e.text()))
						match = true;
				}
			if(!match)
				text.add(e.text());
			match = false;
		}
		
		String date = "";
		String title = "";
		String body = "";
		
		ArrayList<Event> pastEvents = new ArrayList<Event>();
		
		for(int i=0; i<text.size(); i+=3)
		{
			date = text.get(i);
			if(i+1<text.size())
				title = text.get(i+1);
			if(i+2<text.size())
				body = text.get(i+2);
			pastEvents.add(new Event(date, title, body));
			
			date = "";
			title = "";
			body = "";
		}
		
		return pastEvents;
	}
	
}
