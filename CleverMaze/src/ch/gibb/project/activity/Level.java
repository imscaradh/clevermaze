package ch.gibb.project.activity;

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
import android.widget.RelativeLayout;
import ch.gibb.project.R;
import ch.gibb.project.elements.Ball;
import ch.gibb.project.elements.Check;
import ch.gibb.project.elements.Maze;

public class Level extends Activity implements SensorEventListener {
	private SensorManager sensorManager;
	// Bitmap ball;
	RelativeLayout layout;
	Maze mazeView;
	Ball ballView;
	BackView backView;
	Check check;
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
		mazeView = new Maze(this, size.x, size.y);
		ballView = new Ball(this);
		backView = new BackView(this);
		check = new Check(ballView, mazeView);
		layout = new RelativeLayout(this);
		layout.addView(backView, 0);
		layout.addView(mazeView, 1);
		layout.addView(ballView, 2);
		setContentView(layout);

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
		// boolean ballInHole = view.ballInHole();
		updateBall(accelX, accelY);
	}

	private void updateBall(float accelX, float accelY) {
		// TODO: Add Boucing
		// TODO: Add Acceleration (Speed)
		// TODO: Implement WallTouch
		if (check.containsBallX(accelX)) {
			ballView.getB().x = ballView.getB().x - (accelX * 2f);
		} else {
			if (check.touchOnLeft()) {
				ballView.getB().x = mazeView.getPlayGround().left;
			} else {
				ballView.getB().x = (mazeView.getPlayGround().right - (ballView
						.getRadius() * 2));
			}
		}
		if (check.containsBallY(accelY)) {
			ballView.getB().y = ballView.getB().y + (accelY * 2f);
		} else {
			if (check.touchOnTop()) {
				ballView.getB().y = mazeView.getPlayGround().top;
			} else {
				ballView.getB().y = (mazeView.getPlayGround().bottom - (ballView
						.getRadius() * 2));
			}
		}
		if (check.ballInHole()) {
			// TODO: Replace with nicer code?
			layout.removeView(mazeView);
			layout.addView(mazeView);
		}

		check.checkStarTouch();

	}

	@Override
	protected void initObjects() {
		// TODO Auto-generated method stub

	}
}
