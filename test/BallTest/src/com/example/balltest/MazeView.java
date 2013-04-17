package com.example.balltest;

import java.util.Random;
import java.util.Vector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.view.View;

class MazeView extends View {
	Random rand = new Random();
	// PointF b = new PointF(200f, 200f);
	Rect playGround;
	int radius = 34;
	boolean ballInHole = false;
	Vector<PointF> holes = new Vector<PointF>();
	Vector<PointF> points = new Vector<PointF>();
	Vector<Rect> walls = new Vector<Rect>();

	// Bitmap ball = BitmapFactory.decodeResource(getResources(),
	// R.drawable.ball2);
	Bitmap wood = BitmapFactory.decodeResource(getResources(),
			R.drawable.wood_background);
	Bitmap wall = BitmapFactory.decodeResource(getResources(), R.drawable.wall);

	Bitmap star = BitmapFactory.decodeResource(getResources(), R.drawable.star);
	// Drawable ballD;
	Bitmap bitmap;
	Canvas bitmapCanvas;
	Stages stages = new Stages();
	Paint paint = new Paint();
	Paint eraserPaint = new Paint();

	public MazeView(Context context, int width, int height) {
		super(context);
		playGround = new Rect(40, 40, width - 40, height - 40);
		// ball = Bitmap.createScaledBitmap(ball, radius * 2, radius * 2, true);
		star = Bitmap.createScaledBitmap(star, 60, 60, true);
		holes = stages.firstHoles();
		points = stages.firstStars();
		walls = stages.firstWalls();

		// Set bitmap
		bitmap = wood.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		bitmapCanvas = new Canvas();
		bitmapCanvas.setBitmap(bitmap);

		// Set eraser paint properties
		eraserPaint.setAlpha(0);
		eraserPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		eraserPaint.setAntiAlias(true);
		eraserPaint.setFilterBitmap(true);
		eraserPaint.setDither(true);

		paint.setColor(Color.argb(200, 212, 212, 212));
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.OVERLAY));
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);

	}

	protected void onDraw(Canvas canvas) {
		bitmapCanvas.drawBitmap(wall, 0, 0, null);
		bitmapCanvas.drawBitmap(wood, playGround, playGround, null);
		canvas.drawBitmap(bitmap, 0, 0, null);
		// // Foreach > drawing holes
		for (PointF h : holes) {
			bitmapCanvas.drawCircle(h.x + radius, h.y + radius, radius,
					eraserPaint);
		}
		for (PointF s : points) {
			bitmapCanvas.drawBitmap(star, s.x, s.y, null);
		}
		for (Rect w : walls) {
			bitmapCanvas.drawRect(w, paint);
		}

	}
}