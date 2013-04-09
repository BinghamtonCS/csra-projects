package com.gabeochoa.wib.jsoup;

public class Event {

	private String title;
	private String content;
	private String date;
	
	public Event(String title, String content, String date) {
		super();
		this.title = title;
		this.content = content;
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getDate() {
		return date;
	}

}
