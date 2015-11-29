package de.christianspecht.tasko.androidclient;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

/**
 * Make requests to the Tasko API
 */
public class ApiRequest extends AsyncTask<ApiRequestRequest, Void, ApiRequestResult> {

	private Context context;
	private ApiRequestCallback delegate;
	private String token;

	public ApiRequest(Context context, ApiRequestCallback delegate) {
		this.context = context;
		this.delegate = delegate;
		this.token = new Prefs(context).getAuthToken();
	}

	public void GET(String url) {
		ApiRequestRequest request = new ApiRequestRequest();
		request.requestMethod = "GET";
		request.url = url;
		this.execute(request);
	}

	public void POST(String url, String json) {
		ApiRequestRequest request = new ApiRequestRequest();
		request.requestMethod = "POST";
		request.url = url;
		request.json = json;
		this.execute(request);
	}

	@Override
	protected ApiRequestResult doInBackground(ApiRequestRequest... apiRequestRequests) {

		ApiRequestResult apiResult = new ApiRequestResult();
		HttpURLConnection urlConnection = null;

		try {
			ApiRequestRequest request = apiRequestRequests[0];

			URLConnectionFactory factory = new URLConnectionFactory();
			urlConnection = factory.GetConnection(request.url);
			urlConnection.setRequestMethod(request.requestMethod);

			String auth = "Session " + this.token;
			urlConnection.setRequestProperty("Authorization", auth);
			urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");

			if (request.requestMethod == "POST" && !request.json.equals("")){
				urlConnection.setDoOutput(true);
				urlConnection.setChunkedStreamingMode(0);
				byte[] outputBytes = request.json.getBytes("UTF-8");
				OutputStream os = urlConnection.getOutputStream();
				os.write(outputBytes);
			}

			apiResult.httpStatusCode = urlConnection.getResponseCode();

			InputStream in = new BufferedInputStream(urlConnection.getInputStream());

			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder result = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}

			apiResult.json = result.toString();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}

		return apiResult;
	}

	@Override
	protected void onPostExecute(ApiRequestResult result) {
		this.delegate.processResponse(result);
	}
}
