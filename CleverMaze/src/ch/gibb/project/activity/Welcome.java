package ch.gibb.project.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import ch.gibb.project.R;
import ch.gibb.project.util.Dialog;

public class Welcome extends Activity {

	private Button start;
	private Button highscore;
	private Button close;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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
			Intent level = new Intent(v.getContext(), Level.class);
			startActivity(level);
		}
	}

	class highscoreListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Log.v(this.getClass().toString(), "Button Highscore clicked");
			Intent highscore = new Intent(v.getContext(), Highscore.class);
			startActivity(highscore);
		}

	}

	class closeListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Log.v(this.getClass().toString(), "Button Close clicked");
			Dialog.getInstance().createAlertDialog(Dialog.DIALOG_EXIT,
					Welcome.this);
		}
	}

}
