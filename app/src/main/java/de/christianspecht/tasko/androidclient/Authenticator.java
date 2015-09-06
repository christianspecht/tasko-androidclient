package de.christianspecht.tasko.androidclient;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import de.christianspecht.tasko.androidclient.api.TokenResponse;

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

		HttpURLConnection urlConnection = null;
		String json = "";

		try {
			URL url = new URL(urls[0]);
			urlConnection = (HttpURLConnection)url.openConnection();
			urlConnection.setRequestMethod("GET");

			String auth1 = this.user + ":" + this.pass;
			String auth2 = "Basic " + new String(Base64.encode(auth1.getBytes(), Base64.NO_WRAP));
			urlConnection.setRequestProperty("Authorization", auth2);
			urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");

			InputStream in = new BufferedInputStream(urlConnection.getInputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder result = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}

			json = result.toString();

		} catch(Exception e){
			e.printStackTrace();
		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}

		}

		// get token from JSON
		String token = "";

		if (!json.equals("")) {

			GsonBuilder gsonb = new GsonBuilder();
			Gson gson = gsonb.create();

			try {
				JSONObject j = new JSONObject(json);
				TokenResponse tokenr = gson.fromJson(j.toString(), TokenResponse.class);
				if (tokenr != null) {
					token = tokenr.access_token;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return token;
	}

	@Override
	protected void onPostExecute(String result) {
		this.delegate.processToken(result);
	}
}
