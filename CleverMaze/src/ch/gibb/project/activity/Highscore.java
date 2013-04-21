package ch.gibb.project.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import ch.gibb.project.R;

public class Highscore extends About {
	private Button close;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_highscore);
	}

	@Override
	protected void initObjects() {
		close = (Button) findViewById(R.id.btn_close);
		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Highscore.this.nextActivity(Welcome.class);

			}
		});
	}
}
