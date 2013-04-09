package com.gabeochoa.wib.jsoup;

public class FacebookPost {

	public String title = null;
	public String[] content = null;
	
	public FacebookPost()
	{
		
	}
	
	public FacebookPost(String t, String[] cont)
	{
		title = t;
		content = cont;
	}

	public String getTitle() {
		return title;
	}

	public String[] getContent() {
		return content;
	}

}
