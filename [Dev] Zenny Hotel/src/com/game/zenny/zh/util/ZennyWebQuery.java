package com.game.zenny.zh.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.game.zenny.zh.AppClient;

public class ZennyWebQuery {

	/**
	 * @param url
	 * @return web page content
	 */
	public static String query(String url) {
		try {
			URL uri = null;
			uri = new URL(AppClient.serverDomain+url);
			
			URLConnection ec = uri.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(ec.getInputStream(), "UTF-8"));
	        String inputLine;
	        StringBuilder querySB = new StringBuilder();
	        while ((inputLine = in.readLine()) != null)
	        	querySB.append(inputLine);
	        in.close();
	        
	        return unslashUnicode(querySB.toString());
	        
		} catch (IOException e1) {
			return "query-error";
		}
	}
	
	/**
	 * @param slashed
	 * @return replace correctly unicode characters
	 */
	private static String unslashUnicode(String slashed){
		ArrayList<String> pieces = new ArrayList<String>();
		while(true){
			if(slashed.contains("\\u")){
				pieces.add(slashed.substring(0,slashed.indexOf("\\u")));
				char c = (char) Integer.parseInt(slashed.substring(slashed.indexOf("\\u")+2,slashed.indexOf("\\u")+6), 16);
				slashed = slashed.substring(slashed.indexOf("\\u")+6,slashed.length());
				pieces.add(c+"");
			}
			else{
				break;
			}
		}
		String temp = "";
		for(String s : pieces){
			temp = temp + s;
		}
		slashed = temp + slashed;
		return slashed;
	}
	
}
