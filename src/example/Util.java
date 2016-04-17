package example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Util {
	
	private static final String URL = "https://api.yamareco.com/api/v1/";
	
	public static String apiGet(String path, String accessToken){
		String ret = "";
		BufferedReader r = null;
		HttpURLConnection con = null;
		try {
		    URL url = new URL(URL + path);
		    con = (HttpURLConnection)url.openConnection();
		    con.setRequestMethod("GET");
		    if (accessToken != null){
		    	con.setRequestProperty("Authorization", "OAuth " + accessToken);
		    }
		    r = new BufferedReader(new InputStreamReader(con.getInputStream()));
		    String l;
		    while ((l = r.readLine()) != null) {
		        ret += l;
		    }
		} catch (Exception e1) {
			ret = "{\"err\":1}";
		} finally {
		    if (r != null){
		    	try{r.close();}catch(IOException e2){}
		    }
		    if (con != null){con.disconnect();}
		}
		return ret;
	}
	
	public static String apiPost(String path, String param, String accessToken){
		String ret = "";
		BufferedReader r = null;
		HttpURLConnection con = null;
		try {
		    URL url = new URL(URL + path);
		    con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
		    if (accessToken != null){
		    	con.setRequestProperty("Authorization", "OAuth " + accessToken);
		    }
		    
		    OutputStream out = con.getOutputStream();
		    out.write(param.getBytes());
		    out.flush();
		    out.close();
		    
		    r = new BufferedReader(new InputStreamReader(con.getInputStream()));
		    String l;
		    while ((l = r.readLine()) != null) {
		        ret += l;
		    }
		} catch (Exception e1) {
			ret = "{\"err\":1}";
		} finally {
		    if (r != null){
		    	try{r.close();}catch(IOException e2){}
		    }
		    if (con != null){con.disconnect();}
		}
		return ret;
	}

}
