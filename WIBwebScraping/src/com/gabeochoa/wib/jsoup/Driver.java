package com.gabeochoa.wib.jsoup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Driver {

	public static void main(String[] args)
	{
		DataGatherer da = new DataGatherer();
		
		//ArrayList<String> aboutContent = da.getAbout();
		
		ArrayList<Person> members = da.getPeople();
		for(Person p: members)
		{
			System.out.println(p.getName());
			System.out.println(p.getPosition());
			System.out.println(p.getPictureUrl());
			System.out.println("\n");
		}
		
		ArrayList<FacebookPost> posts = da.getFacebookFeed();
		
		for(FacebookPost fbP: posts)
		{
			System.out.print("\n");
			System.out.print(fbP.getTitle());
				for(String str: fbP.getContent())
					System.out.print(str+" ");
		}
		
		ArrayList<Event> futureEvents = da.getEvents();
		for(Event e: futureEvents)
		{
			System.out.println(e.getTitle());
			System.out.println(e.getContent());
		}
		
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
 
			System.out.println("Done");
 
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
			 
			file = new File("./text/CalendarOutput.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			
			for(Event e: futureEvents)
			{
				bw.write("\n");
				bw.write(e.getTitle());
				bw.write(e.getContent());
			}
			
					bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
