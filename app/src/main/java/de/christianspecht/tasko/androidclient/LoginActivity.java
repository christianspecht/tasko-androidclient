package de.christianspecht.tasko.androidclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class LoginActivity extends Activity {

	private Prefs prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		this.prefs = new Prefs(this);
		this.TryToStartMainActivity();
	}

	public void TryToStartMainActivity(){
		// try to load auth token from settings
		// if there's a token in the settings, directly go to main screen
		// if not, let the user login on this screen
		if (!this.prefs.getAuthToken().equals("")) {
			Intent mainIntent = new Intent(this, MainActivity.class);
			startActivity(mainIntent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		MenuOpener menu = new MenuOpener(this);

		if (menu.Open(id)) {
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
	}

	public void LoginUser(View view) {

		EditText userText = (EditText)findViewById(R.id.login_username);
		String user = userText.getText().toString();

		EditText passText = (EditText)findViewById(R.id.login_password);
		String pass = passText.getText().toString();

		Authenticator auth = new Authenticator(this);
		String token = auth.Authenticate(user, pass);

		if (!token.equals("")) {
			this.prefs.setAuthToken(token);
			this.TryToStartMainActivity();
		}
	}
}
