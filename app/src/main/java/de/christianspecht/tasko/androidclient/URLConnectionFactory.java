package de.christianspecht.tasko.androidclient;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;

/**
 * Creates the correct URLConnection (http or https) based on the URL
 */
public class URLConnectionFactory {

	public HttpURLConnection GetConnection(String url) {

		try {
			URL tmpUrl = new URL(url);

			if (url.startsWith("https://")) {
				return (HttpsURLConnection) tmpUrl.openConnection();
			} else {
				return (HttpURLConnection) tmpUrl.openConnection();
			}

		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
