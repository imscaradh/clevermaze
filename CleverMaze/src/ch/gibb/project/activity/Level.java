package ch.gibb.project.activity;

import java.util.Timer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import ch.gibb.project.R;
import ch.gibb.project.controller.ActionHandler;
import ch.gibb.project.elements.Ball;
import ch.gibb.project.elements.Maze;
import ch.gibb.project.elements.Point;
import ch.gibb.project.elements.Text;
import ch.gibb.project.elements.Wall;
import ch.gibb.project.enums.StageEnum;
import ch.gibb.project.util.MessageUtil;

public class Level extends Activity implements SensorEventListener {
	public SensorManager sensorManager;
	private RelativeLayout layout;
	private Maze mazeElement;
	private Ball ballElement;
	private Wall wallElement;
	private Text textElement;
	private Point pointElement;
	private ActionHandler actionHandler;
	private Timer timer;
	private int stageNumber = 1;
	private long millis;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StageEnum.STAGE_1.init();
		initSensorAndViews();
		createTimer();
	}

	protected void initSensorAndViews() {
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		Text.stage = stageNumber;
		initMazeElements();
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
				Log.d("Message", "Couldn't register sensor listener");
			}
		}
	}

	private void initMazeElements() {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 8;
		mazeElement = new Maze(this);
		ballElement = new Ball(this);
		wallElement = new Wall(this);
		textElement = new Text(this);
		pointElement = new Point(this);

	}

	private void addelementsToView() {
		layout = new RelativeLayout(this);
		layout.addView(mazeElement);
		layout.addView(textElement);
		layout.addView(pointElement);
		layout.addView(wallElement);
		layout.addView(ballElement);
		setContentView(layout);
	}

	public void createTimer() {
		timer = new Timer(false);
		timer.scheduleAtFixedRate(new TimerTask(), 0, 1);
	}

	public class TimerTask extends java.util.TimerTask {
		final Handler handler = new Handler();

		@Override
		public void run() {
			handler.post(new Runnable() {
				@Override
				public void run() {
					millis++;
					Text.usedTime = millis;
					textElement.postInvalidate();
				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
	}

	@Override
	public void onSensorChanged(final SensorEvent event) {
		if (!actionHandler.isGameFinished()) {
			actionHandler.moveAndCheckX(event.values[0]);
			actionHandler.moveAndCheckY(event.values[1]);
			actionHandler.checkStarCollision();
		} else {
			performStageChangeAction();
		}
		if (actionHandler.isBallInHole()) {
			performHoleAction();
		}
	}

	private void performStageChangeAction() {
		if (stageNumber == StageEnum.values().length) {
			sensorManager.unregisterListener(this);
			timer.cancel();
			nextActivity(Finish.class);
			return;
		} else {
			Animation animation = AnimationUtils.loadAnimation(this,
					R.anim.dock_bottom_exit);
			layout.startAnimation(animation);
			stageNumber++;
			initSensorAndViews();
			return;
		}
	}

	private void performHoleAction() {
		MessageUtil.getInstance().createShortToastMessage(Level.this,
				"Oh no! You felt into a hole");
		Animation animation = AnimationUtils.loadAnimation(this,
				R.anim.dock_top_exit);
		layout.startAnimation(animation);
		stageNumber = (stageNumber == 1) ? stageNumber : --stageNumber;
		initSensorAndViews();
	}

	public StageEnum getStage() {
		switch (stageNumber) {
		case 1:
			return StageEnum.STAGE_1;
		case 2:
			return StageEnum.STAGE_2;
		case 3:
			return StageEnum.STAGE_3;
		case 4:
			return StageEnum.STAGE_4;
		default:
			nextActivity(Finish.class);
			return null;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			sensorManager.unregisterListener(this);
			timer.cancel();
			MessageUtil.getInstance().showLevelExitDialog(Level.this);
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	public SensorManager getSensorManager() {
		return sensorManager;
	}

	public Maze getMazeElement() {
		return mazeElement;
	}

	public Ball getBallElement() {
		return ballElement;
	}

	public Wall getWallElement() {
		return wallElement;
	}

	public Point getPointElement() {
		return pointElement;
	}

	public Text getTextElement() {
		return textElement;
	}

	public int getStageNumber() {
		return stageNumber;
	}

	public Timer getTimer() {
		return timer;
	}

	public Bitmap decodeSampledBitmapFromResource(int resId, int reqWidth,
			int reqHeight) {

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(getResources(), resId, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(getResources(), resId, options);
	}

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and
			// width
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will
			// guarantee
			// a final image with both dimensions larger than or equal to the
			// requested height and width.
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}

		return inSampleSize;
	}

}
