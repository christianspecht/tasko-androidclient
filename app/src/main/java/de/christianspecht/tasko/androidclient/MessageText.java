package de.christianspecht.tasko.androidclient;

import android.content.Context;
import android.view.Gravity;
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
		Toast toast = Toast.makeText(this.context, message, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.show();
	}
}
