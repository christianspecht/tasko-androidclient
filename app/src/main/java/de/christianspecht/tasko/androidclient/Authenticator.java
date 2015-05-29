package de.christianspecht.tasko.androidclient;

import android.content.Context;
import android.widget.Toast;

/**
 * Helper class for authentication
 */
public class Authenticator {

	private Context context;

	public Authenticator(Context context) {
		this.context = context;
	}

	/**
	 * Authenticate with user and password
	 * @param user The username
	 * @param password The password
	 * @return The authentication token, if successful
	 */
	public String Authenticate(String user, String password) {
		Toast.makeText(this.context, "Login " + user, Toast.LENGTH_SHORT).show();
		return "";
	}
}
