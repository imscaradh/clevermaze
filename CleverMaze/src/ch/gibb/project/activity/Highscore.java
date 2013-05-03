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

public class Highscore extends Activity {
	private ImageButton close;
	private ListView scoreList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_highscore);
		initObjects();
	}

	private void initObjects() {
		Map<String, ?> scores = HighscoreUtil.getInstance(this).loadAll();

		List<String> values = new ArrayList<String>();
		for (String key : scores.keySet()) {
			String[] data = scores.get(key).toString().split(";");
			float rank = Float.parseFloat(data[2]);
			String date = new SimpleDateFormat("dd.MM.yyyy").format(new Date(
					Long.valueOf(key)));
			String points = data[0];
			String usedTime = new SimpleDateFormat("mm:ss:SSS")
					.format(new Date(Long.parseLong(data[1])));

			String display = String.format("%s. %s Points in %s - Date: %s ",
					values.size() + 1, points, usedTime, date);
			values.add(display);
		}
		scoreList = (ListView) findViewById(R.id.scoreList);
		ArrayAdapter<String> myarrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, values);
		scoreList.setAdapter(myarrayAdapter);
		scoreList.setTextFilterEnabled(true);

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
