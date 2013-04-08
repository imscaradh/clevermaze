package com.example.balltest;

import java.util.Random;
import java.util.Vector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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
	Vector<PointF> holes = new Vector<PointF>();
	Vector<PointF> points = new Vector<PointF>();
	Bitmap ball = BitmapFactory
			.decodeResource(getResources(), R.drawable.ball2);
	Bitmap wood = BitmapFactory.decodeResource(getResources(),
			R.drawable.wood_background);
	Bitmap wall = BitmapFactory.decodeResource(getResources(), R.drawable.wall);
	Bitmap hole = BitmapFactory.decodeResource(getResources(), R.drawable.hole);
	Bitmap star = BitmapFactory.decodeResource(getResources(), R.drawable.star);
	Bitmap bottom = BitmapFactory.decodeResource(getResources(),
			R.drawable.bottom);

	Bitmap bitmap;
	Canvas bitmapCanvas;

	private final Paint paint = new Paint();
	private final Paint eraserPaint = new Paint();

	public RenderView(Context context, int width, int height) {
		super(context);
		playGround = new Rect(40, 40, width - 40, height - 40);
		ball = Bitmap.createScaledBitmap(ball, 75, 75, true);
		hole = Bitmap.createScaledBitmap(hole, 75, 75, true);
		star = Bitmap.createScaledBitmap(star, 60, 60, true);
		holes.addElement(new PointF(500f, 500f));
		points.addElement(new PointF(345f, 345f));

		// Set background
		this.setBackgroundResource(R.drawable.bottom);

		// Set bitmap
		bitmap = wood.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		bitmapCanvas = new Canvas();
		bitmapCanvas.setBitmap(bitmap);
		// bitmapCanvas.drawBitmap(wood, width, height, null);

		// Set eraser paint properties
		eraserPaint.setAlpha(0);
		eraserPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		eraserPaint.setAntiAlias(true);
	}

	// In the onDraw should only the Ball "be drawed".
	protected void onDraw(Canvas canvas) {

		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG
				| Paint.FILTER_BITMAP_FLAG);
		paint.setStyle(Style.FILL_AND_STROKE);
		if (ballInHole)
			canvas.drawBitmap(ball, b.x, b.y, paint);

		bitmapCanvas.drawBitmap(wall, 0, 0, null);
		bitmapCanvas.drawBitmap(wood, playGround, playGround, null);

		canvas.drawBitmap(bitmap, 0, 0, paint);

		// // Foreach > drawing holes
		for (PointF s : points) {
			canvas.drawBitmap(star, s.x, s.y, paint);
		}
		for (PointF h : holes) {
			bitmapCanvas.drawCircle(h.x, h.y, 30, eraserPaint);
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
		return false;
	}

	public boolean containsBallY(float accelY) {
		if ((b.y + (accelY * 2f)) > playGround.top
				&& (b.y + (accelY * 2f)) < playGround.bottom - ball.getHeight()) {
			return true;
		}
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
				// the ball is enough near to fall
				ball = Bitmap.createScaledBitmap(ball, 68, 68, true);
				ballInHole = true;
				return true;

			}
		}
		return false;
	}

	public void checkStarTouch() {
		for (PointF p : points) {
			float dx = Math.abs(b.x - p.x);
			float dy = Math.abs(b.y - p.y);

			if (Math.sqrt(dx * dx + dy * dy) < 30) {
				// the ball is enough near to collect
				points.remove(p);
			}
		}

	}
}