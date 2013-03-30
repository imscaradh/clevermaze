package ch.gibb.project.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import ch.gibb.project.R;
import ch.gibb.project.controller.ActivityController;

public class Level extends Activity {
	private ActivityController activityController;
	private Button close;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_level);
		stageSetup();
		ballSetup();
		start();
	}

	@Override
	protected void initObjects() {
		close = (Button) findViewById(R.id.btn_close);
		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Level.this.activityController.nextActivity(Level.this,
						Welcome.class);

			}
		});
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
