package de.christianspecht.tasko.androidclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	public void LoadTasks() {
		Toast.makeText(this, "load tasks here", Toast.LENGTH_SHORT).show();
	}

	private static boolean StartupFinished;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (!this.StartupFinished) {
			this.LoadTasks();
			this.StartupFinished = true;
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()) {
			
			case R.id.action_newtask:
				Toast.makeText(this, getString(R.string.action_newtask) , Toast.LENGTH_SHORT).show();
				return true;
				
			case R.id.action_settings:
				Intent settingsIntent = new Intent(this, SettingsActivity.class);
				startActivity(settingsIntent);
				return true;
				
			case R.id.action_about:
				Intent intent = new Intent(this, AboutActivity.class);
				startActivity(intent);
				return true;
				
			default:
				return super.onOptionsItemSelected(item);	
				
		}
	}

    
}
