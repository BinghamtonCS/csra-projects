package com.gabeochoa.wib.jsoup;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class GetPeople {

	@SuppressWarnings("unused")
	public static ArrayList<Person> main(String[] args)
	{
		
		Document wibSite = null;
		
		//Example of their website's person format
		
		/*
		 <div id="attachment_176" class="wp-caption alignleft" style="width: 160px">
			 <a href="http://binghamtonwib.com/wp-content/uploads/2012/12/561342_2271814399364_1399963296_n.jpg"
			    class="thickbox no_icon" 
			    rel="gallery-171"
			    title="&lt;b&gt;Name:&lt;/b&gt; Tiffany Choi&lt;br&gt;&lt;b&gt;Year:&lt;/b&gt; Junior&lt;br&gt;&lt;b&gt;Academic Study:&lt;/b&gt; Finance and MIS Concentration">
			    	<img class="size-thumbnail wp-image-176" 
			    	     style="border: 1px solid black;" 
			    	     title="&lt;b&gt;Name:&lt;/b&gt; Tiffany Choi&lt;br&gt;&lt;b&gt;Year:&lt;/b&gt; Junior&lt;br&gt;&lt;b&gt;Academic Study:&lt;/b&gt; Finance and MIS Concentration"
			    	     alt="Tiffany Choi" 
			    	     src="http://binghamtonwib.com/wp-content/uploads/2012/12/561342_2271814399364_1399963296_n-150x150.jpg" width="150" height="150" />
			  </a>
			  		<p class="wp-caption-text">
			  			<center>Tiffany Choi<br/> President</center>
			  		</p>
		  </div>
		 		 
		 * */
		try {
			wibSite = Jsoup.connect("http://www.binghamtonwib.com/about-us/current-members/").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements pictureURL = wibSite.select("a[href^=http://binghamtonwib.com/wp-content/uploads/]");
		ArrayList<String> urls = getURLs(pictureURL);

		
		try {
			wibSite = Jsoup.connect("http://www.binghamtonwib.com/about-us/current-members/").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements peoples = wibSite.select("div[id^=attachment");
		ArrayList<Person> womenIB = getPeople(peoples,urls);


		
		
//		for(Person p: womenIB)
//		{
//			System.out.println(p.getName());
//			System.out.println(p.getPosition());
//			System.out.println(p.getPictureUrl());
//			System.out.println("\n");
//		}
		
		return womenIB;
	}
	
	/*
	 * This method goes through the nodes extracted by Jsoups 'select' function and creates a list of URLs for
	 * the pictures of the members of WIB.
	 * 
	 * @param Elements the Elements object returned by Jsoups select funtion
	 * @return ArrayList the list of urls
	 * */
	public static ArrayList<String> getURLs(Elements imgs)
	{
		ArrayList<String> URLS = new ArrayList<String>();
		
		for (Element iA : imgs) {
			URLS.add(iA.attr("href"));
		}
		return URLS;
	}
	
	/*
	 * This method goes through the nodes extracted by Jsoups 'select' function and creates a list of people
	 * that are members of WIB.
	 * 
	 * @param Elements the Elements object returned by Jsoups select funtion
	 * @return ArrayList the list of people that are current members of WIB
	 * */
	public static ArrayList<Person> getPeople(Elements peoples, ArrayList<String> urls)
	{
		String[] nameAndPosition = null;
		String URL = "";
		ArrayList<Person> womenIB = new ArrayList<Person>();
		int index = 0;
		
		for (Element pA : peoples) {
			for (Node node : pA.childNodes()) {
				if (node.nodeName().equals("center")) {
					nameAndPosition = ((Element) node).text().split("\\s+");

					if(nameAndPosition.length > 2)
					{
						nameAndPosition[0] = nameAndPosition[0]+" "+nameAndPosition[1];

						if(nameAndPosition.length > 3)
						{
							String position = "";
							for(int i=2; i<nameAndPosition.length; i++)
							{
								position += nameAndPosition[i]+" ";
							}
							nameAndPosition[2] = position;
						}
					}
					
					if(urls.size() <= index || URL == null)
						URL = "";
					else
						URL = urls.get(index);
					
					womenIB.add(new Person(nameAndPosition[0], nameAndPosition[2], URL));
					index++;
				}
			}
		}
		return womenIB;
	}
	
}
