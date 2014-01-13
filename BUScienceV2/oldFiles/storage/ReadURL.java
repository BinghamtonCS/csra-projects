package storage;
import java.net.*;
import java.io.*;

public class ReadURL {

	//finds keyword "seek" on page urlStr
	public String read(String urlStr, String seek){
		try{
			// get the requested URL
			URL u = new URL(urlStr); 
			// define an input stream and connect it to the URL
			InputStream in = u.openStream();
			// connect it to a stream reader 
			InputStreamReader isr = new InputStreamReader(in);
			// connected it to a buffered reader to help in case of a slow URL 
			BufferedReader br = new BufferedReader(isr);	// can use new BufferedReader(new InputStreamReader(u.openStream())), which combine all 3 lines into 1

			// read it a line at a time
			String line;
			while ((line = br.readLine()) != null)
			{	
				if(line.contains(seek)){
					//find just the URL part of the line
					//System.out.println(line);
					int beg = line.indexOf("src");
					//System.out.println(beg);
					//System.out.println(line.charAt(beg));
					String line2 = line.substring(beg);
					//System.out.println(line2);
					int end = line.indexOf('"', beg+6);
					//System.out.println(end);
					String retval = line.substring(beg, end+1);
					//System.out.println(retval);
					return retval;
				}
			}
		}
		catch (IOException e) {
			System.err.println(e);
		}
		return urlStr;
	}

}
