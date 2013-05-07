package ch.gibb.project.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import ch.gibb.project.R;
import ch.gibb.project.util.HighscoreUtil;
import ch.gibb.project.util.MessageUtil;

public class Highscore extends Activity {
	private ImageButton close;
	private ImageButton clear;
	private ListView scoreList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_highscore);
		initObjects();
	}

	private void initObjects() {
		close = (ImageButton) findViewById(R.id.btn_close);
		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				nextActivity(Welcome.class);
				overridePendingTransition(R.anim.slide_in_left,
						R.anim.slide_out_right);

			}
		});

		clear = (ImageButton) findViewById(R.id.btn_clear);
		clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				clearPersistence();
				initObjects();
				MessageUtil.getInstance().createShortToastMessage(
						Highscore.this, "All highscores have been removed");
			}
		});

		createScoreList();

	}

	private void clearPersistence() {
		HighscoreUtil.getInstance(this).clear();
	}

	private void createScoreList() {
		Map<String, ?> scores = HighscoreUtil.getInstance(this).loadAllSorted();

		List<String> values = new ArrayList<String>();
		for (String key : scores.keySet()) {
			String[] data = scores.get(key).toString().split(";");
			String date = new SimpleDateFormat("dd.MM.yyyy").format(new Date(
					Long.valueOf(data[0])));
			String points = data[1];
			String usedTime = new SimpleDateFormat("mm:ss:SSS")
					.format(new Date(Long.parseLong(data[2])));

			String display = String.format("%d. %s Points in %s - Date: %s ",
					values.size() + 1, points, usedTime, date);
			values.add(display);
			if (values.size() > 10) {
				break;
			}
		}

		scoreList = (ListView) findViewById(R.id.scoreList);
		ArrayAdapter<String> myarrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, values);
		scoreList.setAdapter(myarrayAdapter);
		scoreList.setTextFilterEnabled(true);
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
