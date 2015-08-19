package de.christianspecht.tasko.androidclient;

import android.content.Context;
import android.net.Uri;

/**
 * Creates full URLs
 */
public class UrlBuilder {

	private String baseUrl;

	public UrlBuilder(Context context){

		Prefs prefs = new Prefs(context);
		baseUrl = prefs.getServerUrl();
	}

	public String Build(String path){

		// use base URL from the settings
		Uri.Builder builder = Uri.parse(this.baseUrl).buildUpon();

		// OVERWRITE the path, so it doesn't matter if the URL in the preferences ends with /api or not
		builder.path("api");

		// append new path
		builder.appendPath(path);
		return builder.build().toString();
	}
}
