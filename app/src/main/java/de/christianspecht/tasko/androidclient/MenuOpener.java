package de.christianspecht.tasko.androidclient;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Open menu items
 */
public class MenuOpener {

	private Context context;

	public MenuOpener(Context context){
		this.context = context;
	}

	/**
	 * Opens the menu item with the given ID
	 * @param itemId ID of the menu item
	 * @return True if it exists
	 */
	public boolean Open(int itemId) {

		switch(itemId) {

			case R.id.action_newtask:

				Toast.makeText(this.context, this.context.getString(R.string.action_newtask), Toast.LENGTH_SHORT).show();
				return true;

			case R.id.action_settings:

				Intent settingsIntent = new Intent(this.context, SettingsActivity.class);
				this.context.startActivity(settingsIntent);
				return true;

			case R.id.action_about:

				Intent aboutIntent = new Intent(this.context, AboutActivity.class);
				this.context.startActivity(aboutIntent);
				return true;
		}

		return false;
	}
}
