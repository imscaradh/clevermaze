package com.example.balltest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;

public class BallView {

	public void drawBall(Canvas canvas, PointF b, Bitmap ball) {
		canvas.drawBitmap(ball, b.x, b.y, null);

	}

}
