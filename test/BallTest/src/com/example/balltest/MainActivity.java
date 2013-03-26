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
	Ball ball;
	float x, y, accelX, accelY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Full-Screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		super.onCreate(savedInstanceState);

		// Returns the display-value
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);

		// Creates a new View
		ball = new Ball(this, size.x, size.y);

		setContentView(ball);
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
		updateBall(accelX, accelY);
	}

	private void updateBall(float accelX, float accelY) {
		int displayWidth = ball.getWidth();
		int displayHeight = ball.getHeight();
		// if ((ball.xPosition + (accelX * 2f)) > displayWidth) {
		// } else {
		// boolean test = ball.playGround.contains((int) (ball.xPosition -
		// (accelX * 2f)),(int) (ball.yPosition + (accelY * 2f)));
		if (ball.containsBall(accelX, accelY)) {
			ball.xPosition = ball.xPosition - (accelX * 2f);
			ball.yPosition = ball.yPosition + (accelY * 2f);
		}

	}
}
