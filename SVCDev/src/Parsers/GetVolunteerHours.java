package Parsers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class GetVolunteerHours {

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
			while ((l = in.readLine()) !=null) {
				if (l.contains("Hours</span>"))
				{
					start = true;
					l = in.readLine();
				}
				if (start)
				{	
					if (l.contains("<br /></div>"))
					{
						start = false;
					}
					else
					{
						sb.append(l + nl);
					}
				}
			}
			in.close();
			sb.insert(0, "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">" + nl + "<html>" + nl);
			sb.append("</html>");
			data = sb.toString();
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
