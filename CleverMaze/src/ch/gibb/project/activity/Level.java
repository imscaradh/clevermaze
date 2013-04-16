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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import ch.gibb.project.R;
import ch.gibb.project.elements.Ball;
import ch.gibb.project.elements.Check;
import ch.gibb.project.elements.Maze;

public class Level extends Activity implements SensorEventListener {
	private Button close;
	private SensorManager sensorManager;
	private RelativeLayout layout;
	private Maze mazeView;
	private Ball ballView;
	private Check check;
	private float accelX, accelY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_level);
	}

	@Override
	protected void initObjects() {
		close = (Button) findViewById(R.id.btn_close);
		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Level.this.nextActivity(Welcome.class);

			}
		});

		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		mazeView = new Maze(this, size.x, size.y);
		ballView = new Ball(this);
		check = new Check(ballView, mazeView);
		layout = new RelativeLayout(this);
		layout.addView(mazeView, 0);
		layout.addView(ballView, 1);
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
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		accelX = event.values[0];
		accelY = event.values[1];
		updateBall(accelX, accelY);
	}

	private void updateBall(float accelX, float accelY) {
		// TODO: Add Boucing
		// TODO: Add Acceleration (Speed)
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
			// FIXME: Replace with nicer code
			layout.removeView(mazeView);
			layout.addView(mazeView);
		}

		check.checkStarTouch();

	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

}
