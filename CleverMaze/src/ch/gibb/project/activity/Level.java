package ch.gibb.project.activity;

import android.app.Activity;
import android.os.Bundle;
import ch.gibb.project.R;

public class Level extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level);
		stageSetup();
		ballSetup();
		start();
	}

	private void stageSetup() {
		// TODO: Call here the necessary services/controllers
	}

	private void ballSetup() {
		// TODO:
	}

	private void start() {
		// TODO: Release here the ball
	}

}
