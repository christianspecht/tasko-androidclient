package de.christianspecht.tasko.androidclient;

import android.content.Context;
import android.widget.Toast;

/**
 * Display messages (toasts)
 */
public class MessageText {

	private Context context;

	public MessageText(Context context) {
		this.context = context;
	}

	public void Show(String message) {
		Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
	}
}
