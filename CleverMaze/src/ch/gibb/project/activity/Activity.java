package ch.gibb.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public abstract class Activity extends android.app.Activity {

	protected void onCreate(Bundle savedInstanceState, int activity) {
		super.onCreate(savedInstanceState);
		setContentView(activity);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		initObjects();
	}

	protected abstract void initObjects();

	public void nextActivity(Class<?> nextOne) {
		Intent nextActivity = new Intent(this.getApplicationContext(), nextOne);
		this.startActivity(nextActivity);
		this.finish();
	}

}
