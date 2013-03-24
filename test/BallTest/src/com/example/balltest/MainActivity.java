package com.example.balltest;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
	private SensorManager sensorManager;
	Bitmap ball;
	float x, y, sensorX, sensorY;

	// Generates a Colorflash (Caution: Eyecancer)
	class RenderView extends View {
		Random rand = new Random();

		public RenderView(Context context) {
			super(context);
		}

		protected void onDraw(Canvas canvas) {
			canvas.drawRGB(rand.nextInt(256), rand.nextInt(256),
					rand.nextInt(256));
			invalidate();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Full-Screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		super.onCreate(savedInstanceState);
		setContentView(new RenderView(this));
		// setContentView(R.layout.activity_main);

		// sensorManager = (SensorManager)
		// getSystemService(Context.SENSOR_SERVICE);
		// if (sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() ==
		// 0) {
		// Log.d("Message", "No accelerometer installed");
		// } else {
		// Sensor accelerometer = sensorManager.getSensorList(
		// Sensor.TYPE_ACCELEROMETER).get(0);
		// if (!sensorManager.registerListener(this, accelerometer,
		// SensorManager.SENSOR_DELAY_GAME)) {
		// Log.d("Message", "Couldn't register sensor listener");
		// }
		// }

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
		// Testing Yanu
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		sensorX = event.values[0];
		sensorY = event.values[1];
		TextView textX = (TextView) findViewById(R.id.textX);
		TextView textY = (TextView) findViewById(R.id.textY);
		textX.setText("x:" + sensorX);
		textY.setText("y:" + sensorY);
		ImageView ball = (ImageView) findViewById(R.id.ball);
		ball.setX(ball.getX() - (sensorX * 2f));
		ball.setY(ball.getY() + (sensorY * 2f));
	}

}
