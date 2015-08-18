package de.christianspecht.tasko.androidclient;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Load and save preferences
 */
public class Prefs {

	private SharedPreferences pref;

	public Prefs(Context context) {
		this.pref = PreferenceManager.getDefaultSharedPreferences(context);
	}

	/**
	 * Loads auth token from the preferences
	 * @return The token
	 */
	public String getAuthToken() {
		return this.pref.getString("AuthToken", "");
	}

	/**
	 * Saves auth token in the preferences
	 * @param token The token
	 */
	public void setAuthToken(String token) {
		this.pref.edit().putString("AuthToken", token).commit();
	}

	/**
	 * Loads server URL from the preferences
	 * @return The URL
	 */
	public String getServerUrl() {
		return this.pref.getString("settings_url", "");
	}
}
