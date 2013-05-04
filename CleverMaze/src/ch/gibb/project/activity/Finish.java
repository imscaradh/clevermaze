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
	private TextView rankDescription;
	private String key;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_finish);

		persist();
		initObjects();
	}

	private void initObjects() {
		String timeText = new SimpleDateFormat("mm:ss:SSS").format(new Date(
				Text.usedTime));
		String pointText = String.valueOf(Text.pointcount);
		int rank = HighscoreUtil.getInstance(this).getPosition(key);
		String rankText = String.format("You reached the %d place!", rank);

		reachedPoints = (TextView) findViewById(R.id.reachedPoints);
		usedTime = (TextView) findViewById(R.id.usedTime);
		rankDescription = (TextView) findViewById(R.id.rankDescription);
		reachedPoints.setText(pointText);
		usedTime.setText(timeText);
		rankDescription.setText(rankText);

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
		String stopMillis = String.valueOf(System.currentTimeMillis());
		final String data = String.format("%s;%s;%s", stopMillis,
				Text.pointcount, Text.usedTime);
		key = String.valueOf(((float) Text.pointcount + 1)
				/ (float) Text.usedTime * 100);
		HighscoreUtil.getInstance(this).persist(key, data);
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
