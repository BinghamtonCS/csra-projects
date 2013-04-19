package com.gabeochoa.wib.jsoup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Driver {

	public static ArrayList<Person> members = null;
	public static ArrayList<FacebookPost> posts = null;
	public static ArrayList<Event> pastEvents  = null;
	public static void main(String[] args)
	{
		DataGatherer da = new DataGatherer();
		
		members = da.getPeople();
		posts = da.getFacebookFeed();
		pastEvents = da.getEvents();
		
		//ArrayList<String> aboutContent = da.getAbout();
		
		//printMembers();
		//printFacebook();
		//printPastEvents();
		
		File file = null;
		FileWriter fw;
		BufferedWriter bw;
		
		
		try {
			 
			file = new File("./text/peopleOutput.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			
			for(Person p: members)
			{
				bw.write(p.getName()+"\n");
				bw.write(p.getPosition()+"\n");
				bw.write(p.getPictureUrl()+"\n");
				bw.write("\n");
			}
					bw.close();
 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			 
			file = new File("./text/fbOutput.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			
			for(FacebookPost fbP: posts)
				{
					bw.write("\n");
					bw.write(fbP.getTitle());
						for(String str: fbP.getContent())
							bw.write(str+" ");
				}
					bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			 
			file = new File("./text/PastEvents.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			
			for(Event e: pastEvents)
			{
				bw.write("\n");
				bw.write(e.getDate());
				bw.write("\n");
				bw.write(e.getTitle());
				bw.write("\n");
				bw.write(e.getContent());
			}
			
					bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void printMembers()
	{
		for(Person p: members)
		{
			System.out.println(p.getName());
			System.out.println(p.getPosition());
			System.out.println(p.getPictureUrl());
			System.out.println("\n");
		}
	}
	
	
	public static void printFacebook()
	{
		for(FacebookPost fbP: posts)
		{
			System.out.print("\n");
			System.out.print(fbP.getTitle());
				for(String str: fbP.getContent())
					System.out.print(str+" ");
		}
	}
	public static void printPastEvents()
	{
		for(Event e: pastEvents)
		{
			System.out.println(e.getTitle());
			System.out.println(e.getContent());
		}
	}
	

}
