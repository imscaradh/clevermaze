package ch.gibb.project.activity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.widget.RelativeLayout;
import ch.gibb.project.R;
import ch.gibb.project.controller.ActionHandler;
import ch.gibb.project.elements.Ball;
import ch.gibb.project.elements.Maze;
import ch.gibb.project.elements.Point;
import ch.gibb.project.elements.Text;
import ch.gibb.project.elements.Wall;

public class Level extends About implements SensorEventListener {
	private SensorManager sensorManager;
	private RelativeLayout layout;
	private Maze mazeElement;
	private Ball ballElement;
	private Wall wallElement;
	private Text textElement;
	private Point pointElement;
	private BackView backView;
	private ActionHandler actionHandler;
	private float accelX, accelY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initObjects();
	}

	@Override
	protected void initObjects() {
		Display display = getWindowManager().getDefaultDisplay();
		android.graphics.Point size = new android.graphics.Point();
		display.getSize(size);

		initViews(size.x, size.y);
		addelementsToView();

		actionHandler = new ActionHandler(this);

		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		if (sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() == 0) {
			Log.d("Message", "No accelerometer installed");
		} else {
			Sensor accelerometer = sensorManager.getSensorList(
					Sensor.TYPE_ACCELEROMETER).get(0);
			if (!sensorManager.registerListener(this, accelerometer,
					SensorManager.SENSOR_DELAY_FASTEST)) {
				Log.d("Message", "Cdaouldn't register sensor listener");
			}
		}

	}

	private void initViews(int x, int y) {
		mazeElement = new Maze(this, x, y);
		ballElement = new Ball(this, x, y);
		wallElement = new Wall(this, x, y);
		textElement = new Text(this, x, y);
		pointElement = new Point(this, x, y);
		backView = new BackView(this);
	}

	private void addelementsToView() {
		layout = new RelativeLayout(this);
		layout.addView(backView);
		layout.addView(mazeElement);
		layout.addView(textElement);
		layout.addView(pointElement);
		layout.addView(wallElement);
		layout.addView(ballElement);
		setContentView(layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		accelX = event.values[0];
		accelY = event.values[1];
		updateBall(accelX, accelY);
	}

	private void updateBall(float accelX, float accelY) {
		// TODO: Acceleration
		actionHandler.moveAndCheckX(accelX);
		actionHandler.moveAndCheckY(accelY);
		actionHandler.checkStarTouch();

		if (actionHandler.ballInHole()) {
			// TODO: Replace with nicer code?
			// layout.removeView(mazeElement);
			// layout.addView(mazeElement);
		}

	}

	public SensorManager getSensorManager() {
		return sensorManager;
	}

	public void setSensorManager(SensorManager sensorManager) {
		this.sensorManager = sensorManager;
	}

	public Maze getMazeElement() {
		return mazeElement;
	}

	public void setMazeElement(Maze mazeElement) {
		this.mazeElement = mazeElement;
	}

	public Ball getBallElement() {
		return ballElement;
	}

	public void setBallElement(Ball ballElement) {
		this.ballElement = ballElement;
	}

	public Wall getWallElement() {
		return wallElement;
	}

	public void setWallElement(Wall wallElement) {
		this.wallElement = wallElement;
	}

	public Point getPointElement() {
		return pointElement;
	}

	public void setPointElement(Point pointElement) {
		this.pointElement = pointElement;
	}

	public Text getTextElement() {
		return textElement;
	}

}
