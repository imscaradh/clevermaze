package com.example.balltest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.View;

class BallView extends View {
	int radius = 34;
	Bitmap ball = BitmapFactory
			.decodeResource(getResources(), R.drawable.ball2);
	PointF b = new PointF(200f, 200f);

	public BallView(Context context, int width, int height) {
		super(context);
		ball = Bitmap.createScaledBitmap(ball, radius * 2, radius * 2, true);
	}

	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		// canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
		canvas.drawBitmap(ball, b.x, b.y, null);
		// Log.d("xy", b.x + " y " + b.y);
		invalidate();

	}
}