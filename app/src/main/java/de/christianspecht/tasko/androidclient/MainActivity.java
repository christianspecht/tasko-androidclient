package de.christianspecht.tasko.androidclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements ApiRequestCallback {

	private static boolean StartupFinished;
	private MessageText message;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.message = new MessageText(this);

		if (!this.StartupFinished) {
			this.LoadTasks();
			this.StartupFinished = true;
		}
	}

	public void LoadTasks() {
		String url = new UrlBuilder(this).Build("tasks/search");
		ApiRequest request = new ApiRequest(this, this);
		request.POST(url, "{}");
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		MenuOpener menu = new MenuOpener(this, this.message);

		if (menu.Open(item.getItemId())){
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void processResponse(ApiRequestResult result) {
		this.message.Show(result.json);
	}
    
}
