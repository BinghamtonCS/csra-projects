package com.gabeochoa.wib.jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class GetAbout {

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		
		Document wibSite = null;
	
		//Example of their website's about us format
		/* 	 
		 <h3>
		 	<strong>Mission</strong>
		 </h3>
		 <p> Women in Business (WIB) 
			 <span class="GRcorrect">is</span> 
			 
			 	a female-oriented business organization at Binghamton University. 
			 	Our mission is to instill confidence and drive in female students to pursue
			 
			 <span class="GRcorrect">challenges</span> 
			 
			 	and to enrich and diversify the workplace. 
			 	Women in Business members are ambitious, smart,
			 	 and capable young women or men pursuing an 
			 	 undergraduate or graduate degree in Binghamton University. 
		 </p>
		 <p>Formed in 2011, Women in Business strives for excellence in the four pillars of leadership, service, scholarship, and friendship.</p>
		 
		 */
		try {
			wibSite = Jsoup.connect("http://www.binghamtonwib.com/about-us/").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		Elements titles = wibSite.select("h3");
		
	
	
	
	}	
}
