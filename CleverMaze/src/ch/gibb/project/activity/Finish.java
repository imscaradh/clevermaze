package ch.gibb.project.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import ch.gibb.project.R;
import ch.gibb.project.elements.Text;
import ch.gibb.project.util.HighscoreUtil;

public class Finish extends Activity {
	private ImageButton close;
	private TextView reachedPoints;
	private TextView usedTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_finish);
		initObjects();
		persist();
	}

	private void initObjects() {
		reachedPoints = (TextView) findViewById(R.id.reachedPoints);
		usedTime = (TextView) findViewById(R.id.usedTime);
		reachedPoints.setText(String.valueOf(Text.pointcount));
		usedTime.setText(String.valueOf(Text.usedTime));

		close = (ImageButton) findViewById(R.id.btn_close);
		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				nextActivity(Welcome.class);
				overridePendingTransition(R.anim.slide_in_left,
						R.anim.slide_out_right);

			}
		});

	}

	private void persist() {

		String now = new SimpleDateFormat("dd.MM.yyyy").format(new Date(System
				.currentTimeMillis()));
		final String data = String.format("%s;%s;%f", Text.pointcount,
				Text.usedTime, (float) (Text.pointcount / Text.usedTime));

		HighscoreUtil.getInstance(this).persist(now, data);

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			Finish.this.nextActivity(Welcome.class);
			Finish.this.overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_right);
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}
}
