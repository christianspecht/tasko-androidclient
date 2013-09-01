package de.christianspecht.tasko.androidclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
				Toast.makeText(this, getString(R.string.action_settings), Toast.LENGTH_SHORT).show();
				return true;
				
			case R.id.action_about:
				Toast.makeText(this, getString(R.string.action_about), Toast.LENGTH_SHORT).show();
				
			default:
				return super.onOptionsItemSelected(item);	
				
		}
	}

    
}
