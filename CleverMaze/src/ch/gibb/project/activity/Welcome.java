package ch.gibb.project.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import ch.gibb.project.R;
import ch.gibb.project.util.MessageUtil;

public class Welcome extends Activity {

	private ImageButton start;
	private ImageButton highscore;
	private ImageButton close;
	private ImageButton about;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_main);
		initObjects();
	}

	protected void initObjects() {
		start = (ImageButton) findViewById(R.id.btn_play);
		start.setOnClickListener(new startListener());

		highscore = (ImageButton) findViewById(R.id.btn_highscore);
		highscore.setOnClickListener(new highscoreListener());

		close = (ImageButton) findViewById(R.id.btn_close);
		close.setOnClickListener(new closeListener());

		about = (ImageButton) findViewById(R.id.btn_about);
		about.setOnClickListener(new aboutListener());
	}

	class startListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Log.v(this.getClass().toString(), "Button Play clicked");
			Welcome.this.nextActivity(Level.class);
			Welcome.this.overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
		}
	}

	class highscoreListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Log.v(this.getClass().toString(), "Button Highscore clicked");
			Welcome.this.nextActivity(Highscore.class);
			Welcome.this.overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
		}
	}

	class aboutListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Log.v(this.getClass().toString(), "Button About clicked");
			Welcome.this.nextActivity(About.class);
			Welcome.this.overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
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
