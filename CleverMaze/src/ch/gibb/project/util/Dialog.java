package ch.gibb.project.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;

public class Dialog {
	public static final int DIALOG_EXIT = 1;
	private static Dialog singleton;

	private Dialog() {
	}

	public static synchronized Dialog getInstance() {
		if (singleton == null) {
			singleton = new Dialog();
		}
		return singleton;
	}

	public void createAlertDialog(int id, final Activity clazz) {
		switch (id) {
		case DIALOG_EXIT:
			Builder builder = new AlertDialog.Builder(clazz);
			builder.setMessage("Are you sure to close the app?");
			builder.setCancelable(true);
			builder.setNegativeButton("Cancel", null);
			builder.setPositiveButton("Exit",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							clazz.finish();
						}

					});
			AlertDialog dialog = builder.create();
			dialog.show();

		default:

		}

	}

	public void createToastDialog(int id, final Activity clazz) {

	}
}
