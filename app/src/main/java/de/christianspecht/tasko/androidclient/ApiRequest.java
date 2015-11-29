package de.christianspecht.tasko.androidclient;

import android.content.Context;
import android.os.AsyncTask;

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

		ApiRequestRequest request = apiRequestRequests[0];

		// TODO: HTTP request
		ApiRequestResult result = new ApiRequestResult();
		result.json = request.json;
		return result;
	}

	@Override
	protected void onPostExecute(ApiRequestResult result) {
		this.delegate.processResponse(result);
	}
}
