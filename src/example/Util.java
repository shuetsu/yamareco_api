package example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Util {
	
	private static final String URL = "http://api.yamareco.com/api/v1/";
	
	public static String apiGet(String path){
		String ret = "";
		BufferedReader r = null;
		HttpURLConnection con = null;
		try {
		    URL url = new URL(URL + path);
		    con = (HttpURLConnection)url.openConnection();
		    r = new BufferedReader(new InputStreamReader(con.getInputStream()));
		    String l;
		    while ((l = r.readLine()) != null) {
		        ret += l;
		    }
		} catch (Exception e1) {
			ret = "{err:1}";
		} finally {
		    if (r != null){
		    	try{r.close();}catch(IOException e2){}
		    }
		    if (con != null){con.disconnect();}
		}
		return ret;
	}

}
