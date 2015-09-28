package org.dynalogic.webchat;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class Server
{
	static Map<String,Object> postPayload;
	private static HttpURLConnection conn;
	public static boolean connect() throws IOException
	{
		postPayload = new LinkedHashMap<>();
		URL website = new URL("http://logan.waldman.ro/Chat/ServerLogic.php");
		conn = (HttpURLConnection) website.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		conn.setRequestProperty( "charset", "utf-8");
		conn.setUseCaches( false );
		return true;
	}
	
	private static void send() throws IOException
	{
		 StringBuilder postData = new StringBuilder();
		 for (Map.Entry<String,Object> param : postPayload.entrySet()) {
	            if (postData.length() != 0) postData.append('&');
	            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	            postData.append('=');
	            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
	        }
		 conn.getOutputStream().write(postData.toString().getBytes("UTF-8"));
		 postPayload.clear();
	}
	
	public static String join(String username)
	{
		postPayload.put("request","user_join");
		postPayload.put("name", username);
		return "";
	}
