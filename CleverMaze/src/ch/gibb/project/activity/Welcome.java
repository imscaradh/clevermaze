package ch.gibb.project.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import ch.gibb.project.R;
import ch.gibb.project.elements.Text;
import ch.gibb.project.util.MessageUtil;

public class Welcome extends Activity {

	private ImageButton start;
	private ImageButton highscore;
	private ImageButton close;
	private ImageButton about;

	public static Bitmap backgroundImage;
	public static Bitmap wallImage;
	public static Bitmap finishImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_main);
		initObjects();
	}

	private void initObjects() {
		start = (ImageButton) findViewById(R.id.btn_play);
		start.setOnClickListener(new startListener());

		highscore = (ImageButton) findViewById(R.id.btn_highscore);
		highscore.setOnClickListener(new highscoreListener());

		close = (ImageButton) findViewById(R.id.btn_close);
		close.setOnClickListener(new closeListener());

		about = (ImageButton) findViewById(R.id.btn_about);
		about.setOnClickListener(new aboutListener());

		Display display = getWindowManager().getDefaultDisplay();
		android.graphics.Point displaySize = new android.graphics.Point();
		display.getSize(displaySize);
		if (backgroundImage == null)
			setStaticBitmaps(displaySize.x, displaySize.y);
	}

	public void setStaticBitmaps(int width, int height) {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inInputShareable = true;
		backgroundImage = Bitmap.createScaledBitmap(BitmapFactory
				.decodeResource(getResources(), R.drawable.wood, opts),
				width - 40, height - 40, true);
		backgroundImage = Bitmap.createScaledBitmap(BitmapFactory
				.decodeResource(getResources(), R.drawable.wood, opts),
				width - 40, height - 40, true);
		wallImage = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
				getResources(), R.drawable.wall, opts), width, height, true);
		finishImage = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
				getResources(), R.drawable.finish, opts), 73, 73, true);
	}

	class startListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Text.resetScore();
			nextActivity(Level.class);
			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
		}
	}

	class highscoreListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			nextActivity(Highscore.class);
			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
		}
	}

	class aboutListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			nextActivity(About.class);
			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
		}
	}

	class closeListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			MessageUtil.getInstance().showAppExitDialog(Welcome.this);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			MessageUtil.getInstance().showAppExitDialog(Welcome.this);
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

}
