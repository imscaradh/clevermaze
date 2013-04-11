package com.example.balltest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.util.Log;
import android.view.SurfaceView;

class BallView extends SurfaceView {
	int radius = 34;
	Bitmap ball = BitmapFactory
			.decodeResource(getResources(), R.drawable.ball2);
	PointF b;

	public BallView(Context context, int width, int height) {
		super(context);
		ball = Bitmap.createScaledBitmap(ball, radius * 2, radius * 2, true);
		setBackgroundColor(Color.BLACK);
		b = new PointF(200f, 200f);
		// Set BG to Transparent!!
		// setZOrderOnTop(true);
		// SurfaceHolder holder = getHolder();
		// holder.setFormat(PixelFormat.TRANSLUCENT);
	}

	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(ball, b.x, b.y, null);
		Log.d("xy", b.x + " y " + b.y);
		invalidate();

	}
}