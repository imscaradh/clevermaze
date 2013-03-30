package ch.gibb.project.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import ch.gibb.project.R;
import ch.gibb.project.util.MessageUtil;

public class Welcome extends Activity {

	private Button start;
	private Button highscore;
	private Button close;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_main);
	}

	@Override
	protected void initObjects() {
		start = (Button) findViewById(R.id.btn_play);
		start.setOnClickListener(new startListener());

		highscore = (Button) findViewById(R.id.btn_highscore);
		highscore.setOnClickListener(new highscoreListener());

		close = (Button) findViewById(R.id.btn_close);
		close.setOnClickListener(new closeListener());
	}

	class startListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Log.v(this.getClass().toString(), "Button Play clicked");
			activityController.nextActivity(Welcome.this, Level.class);

		}
	}

	class highscoreListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Log.v(this.getClass().toString(), "Button Highscore clicked");
			activityController.nextActivity(Welcome.this, Highscore.class);
		}

	}

	class closeListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Log.v(this.getClass().toString(), "Button Close clicked");
			MessageUtil.getInstance().createAlertMessage(Welcome.this,
					MessageUtil.DIALOG_EXIT);
		}
	}

}
