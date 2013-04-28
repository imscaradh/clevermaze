package ch.gibb.project.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import ch.gibb.project.R;

public class Highscore extends Activity {
	private ImageButton close;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_highscore);
		initObjects();
	}

	protected void initObjects() {
		close = (ImageButton) findViewById(R.id.btn_close);
		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Highscore.this.nextActivity(Welcome.class);
				Highscore.this.overridePendingTransition(R.anim.slide_in_left,
						R.anim.slide_out_right);

			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			Highscore.this.nextActivity(Welcome.class);
			Highscore.this.overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_right);
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}
}
