package com.example.balltest;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.view.View;

// Generates a Colorflash (Caution: Eyecancer)
class RenderView extends View {
	Random rand = new Random();
	float xPosition = 200f;
	float yPosition = 200f;
	Rect playGround;
	int radius = 25;
	Bitmap ball = BitmapFactory
			.decodeResource(getResources(), R.drawable.ball2);
	Bitmap wood = BitmapFactory.decodeResource(getResources(),
			R.drawable.wood_background);
	Bitmap wall = BitmapFactory.decodeResource(getResources(), R.drawable.wall);

	public RenderView(Context context, int width, int height) {
		super(context);
		playGround = new Rect(40, 40, width - 40, height - 40);
		ball = Bitmap.createScaledBitmap(ball, 75, 75, true);
		// ball = picture;
	}

	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setStyle(Style.FILL_AND_STROKE);
		paint.setColor(Color.BLACK);
		canvas.drawBitmap(wall, 0, 0, paint);
		canvas.drawBitmap(wood, playGround, playGround, paint);
		canvas.drawBitmap(ball, xPosition, yPosition, paint);

		Rect r = new Rect(100, 100, 150, 150);
		canvas.drawRect(r, paint);
		invalidate();
	}

	public boolean containsBallX(float accelX) {
		if ((xPosition - (accelX * 2f)) > playGround.left
				&& (xPosition - (accelX * 2f)) < playGround.right
						- ball.getWidth()) {
			return true;
		}
		// Log.d("right", String.valueOf(playGround.right));
		// Log.d("XPosition", String.valueOf(xPosition) + "--" +
		// String.valueOf(accelX));
		return false;
	}

	public boolean containsBallY(float accelY) {
		if ((yPosition + (accelY * 2f)) > playGround.top
				&& (yPosition + (accelY * 2f)) < playGround.bottom
						- ball.getHeight()) {
			return true;
		}
		// Log.d("bottom", String.valueOf(playGround.bottom));
		// Log.d("YPosition", String.valueOf(yPosition) + "--" +
		// String.valueOf(accelY));
		return false;
	}

	public boolean touchOnLeft() {
		if ((xPosition - playGround.left) < (playGround.right - xPosition)) {
			return true;
		}
		return false;
	}

	public boolean touchOnTop() {
		if ((yPosition - playGround.top) < (playGround.bottom - yPosition)) {
			return true;
		}
		return false;
	}
}