package com.example.balltest;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.app.Activity;
import android.hardware.SensorManager;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorEventListener {
	private SensorManager sensorManager;
	Bitmap ball;
	float x, y, sensorX, sensorY;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//Full-Screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	            WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		if (sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
			Sensor s = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			sensorManager.registerListener(this, s, SensorManager.SENSOR_DELAY_GAME);
		}
		ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
		x = y = sensorX = sensorY = 0;
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
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		sensorX = event.values[0];
		sensorY = event.values[1];
		TextView textX = (TextView)findViewById(R.id.textX);
		TextView textY = (TextView)findViewById(R.id.textY);
		textX.setText("x:" + sensorX);
		textY.setText("y:" + sensorY);
		ImageView ball = (ImageView)findViewById(R.id.ball);
		ball.setX(ball.getX() - (sensorX * 2f));
		ball.setY(ball.getY() + (sensorY * 2f));
	}
	
	private void getAccelerometer(SensorEvent event){
		Toast.makeText(this, "tey", Toast.LENGTH_LONG);
		List<Sensor> sensor = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
		TextView textX = (TextView)findViewById(R.id.textX);
		TextView textY = (TextView)findViewById(R.id.textY);
		textX.setText("x:" + sensor.get(0));
		textY.setText("y:" + sensor.get(1));
		//Gets the accelerometer values
		float[] values = event.values;
	    float x = values[0];
	    float y = values[1];
	    float z = values[2]; // Not needed for our project
	    setBallPosition(x,y);

	}
	
	private void setBallPosition(float x, float y) {
		//textX.setText(Float.toString(x));
		//textY.setText(Float.toString(y));
	}

}
