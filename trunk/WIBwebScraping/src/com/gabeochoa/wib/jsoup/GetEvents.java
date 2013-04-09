package com.gabeochoa.wib.jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class GetEvents {

	//past events
	//    http://www.binghamtonwib.com/events/
	//future events
	//    http://www.google.com/calendar/feeds/WinB%40binghamtonsa.org/public/basic
	
	/*
	 
	 <div
		class="su-heading su-heading-style-1"><div
		class="su-heading-shell">Past Events</div></div>
	 
	 >
	 	<p style="text-align: center;">
			<em>Check out these past events that we&#8217;ve been having! Check out our 
				<a title="Home" href="http://www.binghamtonwib.com/">home page</a> 
				to see what we&#8217;re doing next!
			</em>
		</p>
		
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<p>
			<img 
				class="size-medium wp-image-1359 alignright"
				style="border: 2px solid black;" 
				alt="Thrift Shop Workshop" 
				src="http://binghamtonwib.com/wp-content/uploads/2013/03/Thrift-Shop-Workshop-300x225.jpg" 
				width="300"
				height="225"
			/>
		</p>
		
		<p>&nbsp;</p>
		
		<p>March 21, 2013</p>
		<p><strong>Dance Workshop</strong></p>
		
		<p>From dancing to &#8220;Thrift Shop&#8221; to salsa dancing, we bonded together learning different kind of dances &#8211; 
			moving our bodies in ways we didn&#8217;t know they could move!
		</p>
		
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		
		<p style="text-align: right;">
			<a href="http://binghamtonwib.com/wp-content/uploads/2013/03/international-womens-day.jpg" 
				class="thickbox no_icon" 
				rel="gallery-1356" 
				title="international womens day">
				
				<img class="alignleft size-medium wp-image-1358" 
					 style="border: 2px solid black;" 
					 alt="international womens day" 
					 src="http://binghamtonwib.com/wp-content/uploads/2013/03/international-womens-day-300x225.jpg"
					 width="300"
					 height="225" 
				/>
			</a>
		</p>
		<p style="text-align: right;">  March 7, 2013</p>
		<p style="text-align: right;">
			<strong>International Women&#8217;s Day Discussion</strong>
		</p>
		<p style="text-align: right;">
			In honor of International Women&#8217;s Day, 
			we all drew a poster of women that inspire us &#8211; 
			our moms, teachers, business women. 
			Afterward, a couple of us shared these
			important ladies that have impacted our lives.
		</p>
		
		</div>
		</div>
		</div>
		</div>
		</div>
		</div>
		<div id='content-bottom' class='container'></div>
	 */
	
	public static void main(String[] args)
	{
		Document wibSite = null;
		
		try {
			wibSite = Jsoup.connect("http://www.binghamtonwib.com/about-us/current-members/").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements pictureURL = wibSite.select("a[href^=http://binghamtonwib.com/wp-content/uploads/]");	
	}
	
}
