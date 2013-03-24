package ch.gibb.project.activity;

import android.app.Activity;
import android.os.Bundle;
import ch.gibb.project.R;

public class Welcome extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Fullscreen enabled in AndroidManifest (keep it simple)
		setContentView(R.layout.activity_main);

		// TODO: Add elements like startbutton, highscorebutton
	}

}
