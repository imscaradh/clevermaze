package ch.gibb.project.impl.controller;

import java.io.Serializable;

import android.app.Activity;
import android.content.Intent;
import ch.gibb.project.controller.ActivityController;

public class ActivityControllerImpl implements ActivityController, Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public void nextActivity(Activity acutual, Class<?> nextOne) {
		Intent nextActivity = new Intent(acutual.getApplicationContext(),
				nextOne);
		nextActivity.putExtra("ActivityController", this);
		acutual.startActivity(nextActivity);

	}
}
