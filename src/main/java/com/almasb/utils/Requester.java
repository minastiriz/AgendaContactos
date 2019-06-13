package com.almasb.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Requester {
	
	private String baseUri = "http://34.66.56.13:8080/";
	
	private String request(String method, String uri, String jsonBody){
		URL url;
		try {
			String formedUri = baseUri + uri;
			url = new URL(formedUri);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod(method);
			
			if (jsonBody != null){
				con.setRequestProperty("Content-Type", "application/json; utf-8");
				con.setRequestProperty("Accept", "application/json");
				con.setDoOutput(true);
				try(OutputStream os = con.getOutputStream()) {
				    byte[] input = jsonBody.getBytes("utf-8");
				    os.write(input, 0, input.length);           
				}
				
			}
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
			    content.append(inputLine);
			}
			in.close();
			
			return content.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String requestGet(String uri){
		return request("GET", uri, null);
	}
	
	public String requestPost(String uri, String parameters){
		return request("POST", uri, parameters);
	}

}
