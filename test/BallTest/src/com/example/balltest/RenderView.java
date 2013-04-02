package com.example.balltest;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.view.View;

// Generates a Colorflash (Caution: Eyecancer)
class RenderView extends View {
	Random rand = new Random();
	float bx = 200f;
	float by = 200f;
	float hx = 500f;
	float hy = 500f;
	Rect playGround;
	int radius = 25;
	boolean ballInHole = false;

	// Read Graphics
	Bitmap ball = BitmapFactory
			.decodeResource(getResources(), R.drawable.ball2);
	Bitmap wood = BitmapFactory.decodeResource(getResources(),
			R.drawable.wood_background);
	Bitmap wall = BitmapFactory.decodeResource(getResources(), R.drawable.wall);
	Bitmap hole = BitmapFactory.decodeResource(getResources(), R.drawable.hole);

	public RenderView(Context context, int width, int height) {
		super(context);
		playGround = new Rect(40, 40, width - 40, height - 40);
		ball = Bitmap.createScaledBitmap(ball, 75, 75, true);
		hole = Bitmap.createScaledBitmap(hole, 75, 75, true);
	}

	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG
				| Paint.FILTER_BITMAP_FLAG);
		paint.setStyle(Style.FILL_AND_STROKE);
		paint.setColor(Color.BLACK);
		canvas.drawBitmap(wall, 0, 0, paint);
		canvas.drawBitmap(wood, playGround, playGround, paint);
		Paint p = new Paint();
		p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
		canvas.drawCircle(300, 300, radius, p);
		if (ballInHole)
			canvas.drawBitmap(ball, bx, by, paint);
		// Foreach > drawing holes

		canvas.drawBitmap(hole, hx, hy, paint);

		if (!ballInHole)
			canvas.drawBitmap(ball, bx, by, paint);
		invalidate();
	}

	public boolean containsBallX(float accelX) {
		if ((bx - (accelX * 2f)) > playGround.left
				&& (bx - (accelX * 2f)) < playGround.right - ball.getWidth()) {
			return true;
		}
		// Log.d("right", String.valueOf(playGround.right));
		// Log.d("XPosition", String.valueOf(xPosition) + "--" +
		// String.valueOf(accelX));
		return false;
	}

	public boolean containsBallY(float accelY) {
		if ((by + (accelY * 2f)) > playGround.top
				&& (by + (accelY * 2f)) < playGround.bottom - ball.getHeight()) {
			return true;
		}
		// Log.d("bottom", String.valueOf(playGround.bottom));
		// Log.d("YPosition", String.valueOf(yPosition) + "--" +
		// String.valueOf(accelY));
		return false;
	}

	public boolean touchOnLeft() {
		if ((bx - playGround.left) < (playGround.right - bx)) {
			return true;
		}
		return false;
	}

	public boolean touchOnTop() {
		if ((by - playGround.top) < (playGround.bottom - by)) {
			return true;
		}
		return false;
	}

	public boolean ballInHole() {
		float dx = Math.abs(bx - hx);
		float dy = Math.abs(by - hy);

		if (Math.sqrt(dx * dx + dy * dy) < 30) {
			ball = Bitmap.createScaledBitmap(ball, 68, 68, true);
			ballInHole = true;
			return true;
			// the ball is enough near to center
		}
		return false;
	}
}