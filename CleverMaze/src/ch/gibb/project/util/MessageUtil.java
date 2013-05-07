package ch.gibb.project.util;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.widget.Toast;
import ch.gibb.project.R;
import ch.gibb.project.activity.Activity;
import ch.gibb.project.activity.Level;
import ch.gibb.project.activity.Welcome;

public class MessageUtil {
	public static final int DIALOG_EXIT = 1;
	public static final int DIALOG_LEVELEXIT = 2;
	public static final int DIALOG_HIGHSCORE = 3;
	private static MessageUtil singleton;

	private MessageUtil() {
	}

	public static synchronized MessageUtil getInstance() {
		if (singleton == null) {
			singleton = new MessageUtil();
		}
		return singleton;
	}

	public void showAppExitDialog(final Activity activity) {
		Builder builder = new AlertDialog.Builder(activity);
		builder.setMessage("Are you sure to close the app?");
		builder.setCancelable(true);
		builder.setNegativeButton("Cancel", null);
		builder.setPositiveButton("Exit",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						activity.finish();
					}

				});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public void showLevelExitDialog(final Activity activity) {
		Builder builder = new AlertDialog.Builder(activity);
		builder.setMessage("Are you sure to exit the stage?");
		builder.setCancelable(true);
		builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				((Level) activity).createTimer();
				Sensor accelerometer = ((Level) activity).sensorManager
						.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
				((Level) activity).sensorManager.registerListener(
						(Level) activity, accelerometer,
						SensorManager.SENSOR_DELAY_FASTEST);
			}
		});
		builder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						((Level) activity).createTimer();
						Sensor accelerometer = ((Level) activity).sensorManager
								.getSensorList(Sensor.TYPE_ACCELEROMETER)
								.get(0);
						((Level) activity).sensorManager.registerListener(
								(Level) activity, accelerometer,
								SensorManager.SENSOR_DELAY_FASTEST);
					}

				});
		builder.setPositiveButton("Home",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						((Level) activity).nextActivity(Welcome.class);
						((Level) activity).overridePendingTransition(
								R.anim.slide_in_left, R.anim.slide_out_right);
						((Level) activity).sensorManager
								.unregisterListener((Level) activity);
					}

				});
		AlertDialog dialogLvl = builder.create();
		dialogLvl.show();
	}

	public void createShortToastMessage(final Activity activity, String message) {
		Toast.makeText(activity.getApplicationContext(), message,
				Toast.LENGTH_SHORT).show();
	}

	public void createLongToastMessage(final Activity activity, String message) {
		Toast.makeText(activity.getApplicationContext(), message,
				Toast.LENGTH_LONG).show();
	}
}
