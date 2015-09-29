package org.dynalogic.webchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class Server
{
    static Map<String,Object> postPayload;
    private static HttpURLConnection conn;

    /**
     * connects to the default server (logan.waldman.ro)
     * @return (connection succesful) ? true : false
     */
    public static boolean connect()
    {
        try {
            postPayload = new LinkedHashMap<>();
            URL website = new URL("http://logan.waldman.ro/Chat/ServerLogic.php");
            conn = (HttpURLConnection) website.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setUseCaches(false);
            return true;
        }catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    //Should this method be public?
    /**
     * send the `postPayload` HashMap to the server
     * @return JSON encoded server response
     */
    private static String send()
    {
         StringBuilder postData = new StringBuilder();
        try {
         for (Map.Entry<String,Object> param : postPayload.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }

            conn.getOutputStream().write(postData.toString().getBytes("UTF-8"));

             postPayload.clear();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            String output = null;
            while((line = rd.readLine()) != null)
            {
                output+=line;
            }
            rd.close();
            return output;

        }catch(IOException e)
        {
            e.printStackTrace();
            return "error: " + e.getMessage();
        }

    }

    /**
     * sends the server a request to join as `username`
     * @param username the username to connect as
     * @return JSON encoded server response
     */
    public static String join(String username)
    {
        postPayload.put("request", "user_join");
        postPayload.put("name", username);
        return send();

    }

    /**
     * poll the server to update the newest version of the log
     * @return JSON encoded return from the server
     */
    public static String getlog(){

        postPayload.put("request", "update_log");
        return send();
    }
}
