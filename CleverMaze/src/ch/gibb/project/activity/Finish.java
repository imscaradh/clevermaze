package ch.gibb.project.activity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import ch.gibb.project.R;
import ch.gibb.project.elements.Text;

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
		String now = new SimpleDateFormat("yyy-MM-dd hh:mm:ss")
				.format(new Date(System.currentTimeMillis()));

		String FILENAME = "hello_file";
		String string = String.format("%s;%s;%s\n", now, Text.pointcount,
				Text.usedTime);

		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_PRIVATE);
			fos.write(string.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

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
