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
		String timeText = new SimpleDateFormat("mm:ss:SSS").format(new Date(
				Text.usedTime));
		String pointText = String.valueOf(Text.pointcount);

		reachedPoints = (TextView) findViewById(R.id.reachedPoints);
		usedTime = (TextView) findViewById(R.id.usedTime);
		reachedPoints.setText(pointText);
		usedTime.setText(timeText);

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
		float rank = (float) Text.pointcount / (float) Text.usedTime * 10000;
		final String data = String.format("%s;%s;%f", Text.pointcount,
				Text.usedTime, rank);
		HighscoreUtil.getInstance(this).persist(
				String.valueOf(System.currentTimeMillis()), data);

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
