package de.christianspecht.tasko.androidclient;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Helper class for authentication
 */
public class Authenticator extends AsyncTask<String, Void, String> {

	private Context context;
	private String user;
	private String pass;
	private AuthenticatorResponse delegate;

	/**
	 * Authenticate with user and password
	 * @param user The username
	 * @param pass The password
	 * @param delegate The delegate to call when authentication is finished
	 */
	public Authenticator(Context context, String user, String pass, AuthenticatorResponse delegate){
		this.context = context;
		this.user = user;
		this.pass = pass;
		this.delegate = delegate;
	}

	public void Authenticate(){
		String url = new UrlBuilder(this.context).Build("token");
		this.execute(url);
	}

	@Override
	protected String doInBackground(String... urls){

		if (this.user.equals("test")){
			return "token";
		}

		return "";
	}

	@Override
	protected void onPostExecute(String result) {
		this.delegate.processToken(result);
	}
}
