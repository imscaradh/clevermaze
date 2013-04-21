package ch.gibb.project.activity;

import android.content.Intent;
import android.os.Bundle;

public abstract class About extends android.app.Activity {

	protected void onCreate(Bundle savedInstanceState, int activity) {
		super.onCreate(savedInstanceState);
		setContentView(activity);
		initObjects();
	}

	protected abstract void initObjects();

	public void nextActivity(Class<?> nextOne) {
		Intent nextActivity = new Intent(this.getApplicationContext(), nextOne);
		this.startActivity(nextActivity);
		this.finish();
	}

}
