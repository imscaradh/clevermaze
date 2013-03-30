package ch.gibb.project.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.widget.Toast;

public class MessageUtil {
	public static final int DIALOG_EXIT = 1;
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
		default:

		}

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
