package com.gabeochoa.wib.jsoup;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class GetFacebookFeed {

	//http://www.facebook.com/feeds/page.php?format=atom10&id=249701551756902
	
	@SuppressWarnings("unused")
	public static ArrayList<FacebookPost> main(String[] args)
	{
		
		Document wibSite = null;
		String rssFeedLink = "http://www.facebook.com/feeds/page.php?format=atom10&id=249701551756902";
		Document xml = null;
		try {
			xml = Jsoup.connect(rssFeedLink).ignoreContentType(true).get();
			wibSite = Jsoup.parse(xml.toString(), "", Parser.xmlParser());
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		ArrayList<FacebookPost> posts = new ArrayList<FacebookPost>();
		
		Elements entry = wibSite.select("entry");
		
		Elements titles = entry.select("title");
		String[] fbTitles = new String[entry.size()];
		for(int i=0; i<titles.size(); i++)
		{
			Element e = titles.get(i);
			fbTitles[i] = e.text();
		}
		
		Elements contents = entry.select("content[type^=html");
		ArrayList<String[]> fbContent = new ArrayList<String[]>();
		String w;
		String words[];
		
		for(Element e: contents)
		{
			words = e.text().split("\\s+");
			for(int i=0; i<words.length; i++)
			{
				w = words[i];
				if(w.equals("<br") || w.endsWith("<br"))
				{
					w = w.substring(0,w.length()-3);
					words[i] = w+"\n";
				}
				else if(w.equals("/>"))
				{
					words[i] = "";
				}	
				else if(w.contains("&#039;"))
				{
					char[] a = w.toCharArray();
					for(int j =0; j<a.length; j++)
					{
						if( i+2 < a.length &&  a[i] == '&' && a[i+1] == '#')
						{
							a[i] = '\'';
							a[i+1] = 0;
							a[i+2] = 0;
							a[i+3] = 0;
							a[i+4] = 0;
							a[i+5] = 0;
							break;
						}
					}
					
					w = "";
					
					for(char c: a)
					{
						if(c != 0)
							w += c;
					}
					words[i] = w;
				}	
				fbContent.add(words);
			}
			
		}

		for(int i=0; i<titles.size(); i++)
		{
			posts.add(new FacebookPost(fbTitles[i],fbContent.get(i)));
		}
		
		return posts;
	}	
	
	
}
