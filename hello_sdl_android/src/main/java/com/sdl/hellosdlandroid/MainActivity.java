package com.sdl.hellosdlandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Console;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "MainActivity";

	final FirebaseDatabase database = FirebaseDatabase.getInstance();
	DatabaseReference ref = database.getReference();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//If we are connected to a module we want to start our SdlService
		SdlReceiver.queryForConnectedService(this);

		//button
		Button submitButton = findViewById(R.id.submitButton);
		submitButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
			// Write a message to the database
				Log.d(TAG, "test1");
				newEntry("test4",1,2,3);
			}
		});
	}

	@IgnoreExtraProperties
	public class Driver {

		public double currentSpeed;
		public int distanceDriven;
		public int timeMoving;

		public Driver() {
			// Default constructor required for calls to DataSnapshot.getValue(User.class)
			Log.d(TAG, "test2");
		}

		public Driver(double speed, int distanceDriven, int timeMoving) {
			this.currentSpeed = speed;
			this.distanceDriven = distanceDriven;
			this.timeMoving = timeMoving;
		}

	}

	private void newEntry(String name, double currentSpeed, int distanceDriven, int timeMoving) {
		Log.d(TAG, "test3");

		Driver driver = new Driver(currentSpeed, distanceDriven, timeMoving);

		ref.child(name).setValue(driver);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
