package ch.gibb.project.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;
import ch.gibb.project.R;

public class BackView extends View {
	private Bitmap bottom;

	public BackView(Context context) {
		super(context);
		bottom = BitmapFactory
				.decodeResource(getResources(), R.drawable.bottom);
	}

	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(bottom, 0, 0, null);

	}

}
