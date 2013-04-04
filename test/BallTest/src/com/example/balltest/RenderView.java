package com.example.balltest;

import java.util.Random;
import java.util.Vector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.view.View;

// Generates a Colorflash (Caution: Eyecancer)
class RenderView extends View {
	Random rand = new Random();
	PointF b = new PointF(200f, 200f);
	Rect playGround;
	int radius = 25;
	boolean ballInHole = false;
	Vector<PointF> holes;
	Vector<PointF> points;
	// Vector with the Hole-/Point-Coordinates
	// Read Graphics
	Bitmap ball = BitmapFactory
			.decodeResource(getResources(), R.drawable.ball2);
	Bitmap wood = BitmapFactory.decodeResource(getResources(),
			R.drawable.wood_background);
	Bitmap wall = BitmapFactory.decodeResource(getResources(), R.drawable.wall);
	Bitmap hole = BitmapFactory.decodeResource(getResources(), R.drawable.hole);
	Bitmap star = BitmapFactory.decodeResource(getResources(), R.drawable.star);

	public RenderView(Context context, int width, int height) {
		super(context);
		playGround = new Rect(40, 40, width - 40, height - 40);
		ball = Bitmap.createScaledBitmap(ball, 75, 75, true);
		hole = Bitmap.createScaledBitmap(hole, 75, 75, true);
		star = Bitmap.createScaledBitmap(star, 60, 60, true);
		holes.add(new PointF(500f, 500f));
		points.add(new PointF(345f, 345f));
	}

	// In the onDraw should only the Ball "be drawed".
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
			canvas.drawBitmap(ball, b.x, b.y, paint);
		// Foreach > drawing holes
		for (PointF s : points) {
			canvas.drawBitmap(star, s.x, s.y, paint);
		}
		for (PointF h : holes) {
			canvas.drawBitmap(hole, h.x, h.y, paint);
		}
		if (!ballInHole)
			canvas.drawBitmap(ball, b.x, b.y, paint);
		invalidate();
	}

	public boolean containsBallX(float accelX) {
		if ((b.x - (accelX * 2f)) > playGround.left
				&& (b.x - (accelX * 2f)) < playGround.right - ball.getWidth()) {
			return true;
		}
		// Log.d("right", String.valueOf(playGround.right));
		// Log.d("XPosition", String.valueOf(xPosition) + "--" +
		// String.valueOf(accelX));
		return false;
	}

	public boolean containsBallY(float accelY) {
		if ((b.y + (accelY * 2f)) > playGround.top
				&& (b.y + (accelY * 2f)) < playGround.bottom - ball.getHeight()) {
			return true;
		}
		// Log.d("bottom", String.valueOf(playGround.bottom));
		// Log.d("YPosition", String.valueOf(yPosition) + "--" +
		// String.valueOf(accelY));
		return false;
	}

	public boolean touchOnLeft() {
		if ((b.x - playGround.left) < (playGround.right - b.x)) {
			return true;
		}
		return false;
	}

	public boolean touchOnTop() {
		if ((b.y - playGround.top) < (playGround.bottom - b.y)) {
			return true;
		}
		return false;
	}

	public boolean ballInHole() {
		for (PointF h : holes) {
			float dx = Math.abs(b.x - h.x);
			float dy = Math.abs(b.y - h.y);

			if (Math.sqrt(dx * dx + dy * dy) < 30) {
				ball = Bitmap.createScaledBitmap(ball, 68, 68, true);
				ballInHole = true;
				return true;
				// the ball is enough near to center
			}
		}
		return false;
	}

	public void checkStarTouch() {
		Rect ballRect = new Rect((int) b.x, (int) b.y, (int) b.x
				+ ball.getWidth(), (int) b.x + ball.getHeight());

		for (PointF s : points) {
			Rect point = new Rect((int) s.x, (int) s.y, (int) s.x
					+ star.getWidth(), (int) s.y + star.getHeight());
			if (ballRect.intersect(point)) {
				points.remove(s);
			}
		}

	}
}