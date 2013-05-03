package ch.gibb.project.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.widget.Toast;
import ch.gibb.project.R;
import ch.gibb.project.activity.Highscore;
import ch.gibb.project.activity.Level;
import ch.gibb.project.activity.Welcome;
import ch.gibb.project.elements.Text;

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

	public void createAlertMessage(final Activity activity, int level) {
		switch (level) {
		case DIALOG_EXIT:
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
			break;
		case DIALOG_LEVELEXIT:
			Builder builderLvl = new AlertDialog.Builder(activity);
			builderLvl.setMessage("Are you sure to exit the stage?");
			builderLvl.setCancelable(true);
			builderLvl
					.setOnCancelListener(new DialogInterface.OnCancelListener() {
						@Override
						public void onCancel(DialogInterface dialog) {
							// TODO Auto-generated method stub
							Sensor accelerometer = ((Level) activity).sensorManager
									.getSensorList(Sensor.TYPE_ACCELEROMETER)
									.get(0);
							((Level) activity).sensorManager.registerListener(
									(Level) activity, accelerometer,
									SensorManager.SENSOR_DELAY_FASTEST);
						}
					});
			builderLvl.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Implement better > Don't works when user
							// don't clicks on the button
							Sensor accelerometer = ((Level) activity).sensorManager
									.getSensorList(Sensor.TYPE_ACCELEROMETER)
									.get(0);
							((Level) activity).sensorManager.registerListener(
									(Level) activity, accelerometer,
									SensorManager.SENSOR_DELAY_FASTEST);
						}

					});
			builderLvl.setPositiveButton("Home",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							((Level) activity).nextActivity(Welcome.class);
							((Level) activity).overridePendingTransition(
									R.anim.slide_in_left,
									R.anim.slide_out_right);
							((Level) activity).sensorManager
									.unregisterListener((Level) activity);
							((Level) activity).getTextElement().pointcount = 0;
						}

					});
			AlertDialog dialogLvl = builderLvl.create();
			dialogLvl.show();
			break;
		case DIALOG_HIGHSCORE:
			// Get Points + Time from Level
			Builder builderScore = new AlertDialog.Builder(activity);
			builderScore.setMessage("You finished CleverMaze with "
					+ Text.pointcount + " points in " + Text.usedTime);
			builderScore.setCancelable(true);
			builderScore.setNegativeButton("Home",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							((Level) activity).nextActivity(Welcome.class);
							((Level) activity).overridePendingTransition(
									R.anim.slide_in_left,
									R.anim.slide_out_right);
							((Level) activity).sensorManager
									.unregisterListener((Level) activity);
						}

					});
			builderScore.setPositiveButton("Highscore",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							((Level) activity).nextActivity(Highscore.class);
							((Level) activity).overridePendingTransition(
									R.anim.slide_in_left,
									R.anim.slide_out_right);
							((Level) activity).sensorManager
									.unregisterListener((Level) activity);
						}

					});
			AlertDialog dialogScore = builderScore.create();
			dialogScore.show();
			break;
		default:

		}

	}

	public void createShortToastMessage(final Activity activity, String message) {
		Toast.makeText(activity.getApplicationContext(), message, 3).show();
	}

	public void createLongToastMessage(final Activity activity, String message) {
		Toast.makeText(activity.getApplicationContext(), message, 5).show();
	}
}
