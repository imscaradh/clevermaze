package com.example.balltest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.View;

class BallView extends View {
	int radius = 30;
	Bitmap ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
	PointF b = new PointF(600f, 1150f);

	public BallView(Context context) {
		super(context);
		ball = Bitmap.createScaledBitmap(ball, radius * 2, radius * 2, true);
	}

	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setDither(true);
		// canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
		canvas.drawBitmap(ball, b.x, b.y, paint);
		// Log.d("xy", b.x + " y " + b.y);
		invalidate();

	}
}