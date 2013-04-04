package com.example.balltest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity implements SensorEventListener {
	private SensorManager sensorManager;
	// Bitmap ball;
	RenderView view;
	float x, y, accelX, accelY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Full-Screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// Prevent that the device goes into standby
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		super.onCreate(savedInstanceState);

		// Returns the display-value
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);

		// Creates a new View
		view = new RenderView(this, size.x, size.y);

		setContentView(view);
		// setContentView(R.layout.activity_main);
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		if (sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() == 0) {
			Log.d("Message", "No accelerometer installed");
		} else {
			Sensor accelerometer = sensorManager.getSensorList(
					Sensor.TYPE_ACCELEROMETER).get(0);
			if (!sensorManager.registerListener(this, accelerometer,
					SensorManager.SENSOR_DELAY_FASTEST)) {
				Log.d("Message", "Couldn't register sensor listener");
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		// Testing Yanu
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		accelX = event.values[0];
		accelY = event.values[1];
		boolean ballInHole = view.ballInHole();
		updateBall(accelX, accelY);
	}

	private void updateBall(float accelX, float accelY) {

		// view.checkStarTouch();
		if (view.containsBallX(accelX)) {
			view.b.x = view.b.x - (accelX * 2f);
		} else {
			if (view.touchOnLeft()) {
				view.b.x = view.playGround.left;
			} else {
				view.b.x = (view.playGround.right - view.ball.getWidth());
			}
		}
		if (view.containsBallY(accelY)) {
			view.b.y = view.b.y + (accelY * 2f);
		} else {
			if (view.touchOnTop()) {
				view.b.y = view.playGround.top;
			} else {
				view.b.y = (view.playGround.bottom - view.ball.getHeight());
			}
		}
	}
}
