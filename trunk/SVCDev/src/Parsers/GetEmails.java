package Parsers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class GetEmails {
	
	public String getInternetData() throws Exception{
		BufferedReader in = null;
		String data = null;
		try{
			HttpClient client = new DefaultHttpClient();
			URI website = new URI("http://www.busvc.blogspot.com/p/about.html");
			HttpGet request = new HttpGet();
			request.setURI(website);
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer("");
			String l = "";
			String nl = System.getProperty("line.separator");
			boolean start = false;
			while ((l = in.readLine()) !=null){
				if (l.contains("Executive Board</span>"))
				{
					start = true;
				}
				if (start)
				{	
					if (l.contains("div class"))
					{
						start = false;
					}
					sb.append(l + nl);
				}
			}
			in.close();
			data = sb.toString();
			data.replaceAll("\\<.*?>","");	
			return data;
			
		} finally {
			if (in != null) {
				try {
					in.close();
					return data;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	

}
