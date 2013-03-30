package ch.gibb.project.activity;

import android.os.Bundle;
import ch.gibb.project.controller.ActivityController;
import ch.gibb.project.impl.controller.ActivityControllerImpl;

public abstract class Activity extends android.app.Activity {
	protected ActivityController activityController;

	protected void onCreate(Bundle savedInstanceState, int activity) {
		super.onCreate(savedInstanceState);
		setContentView(activity);
		activityController = (ActivityController) getIntent()
				.getSerializableExtra("ActivityController");
		if (activityController == null) {
			activityController = new ActivityControllerImpl();
		}

		initObjects();
	}

	protected abstract void initObjects();
}
