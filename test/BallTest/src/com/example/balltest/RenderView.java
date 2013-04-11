package com.example.balltest;

import java.util.Random;
import java.util.Vector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.view.SurfaceView;

// Generates a Colorflash (Caution: Eyecancer)
class RenderView extends SurfaceView {
	Random rand = new Random();
	// PointF b = new PointF(200f, 200f);
	Rect playGround;
	int radius = 34;
	boolean ballInHole = false;
	Vector<PointF> holes = new Vector<PointF>();
	Vector<PointF> points = new Vector<PointF>();

	// Bitmap ball = BitmapFactory.decodeResource(getResources(),
	// R.drawable.ball2);
	Bitmap wood = BitmapFactory.decodeResource(getResources(),
			R.drawable.wood_background);
	Bitmap wall = BitmapFactory.decodeResource(getResources(), R.drawable.wall);
	Bitmap hole = BitmapFactory.decodeResource(getResources(), R.drawable.hole);
	Bitmap star = BitmapFactory.decodeResource(getResources(), R.drawable.star);
	// Drawable ballD;
	Bitmap bitmap;
	Canvas bitmapCanvas;

	boolean alreadyDrawed = false;

	private final Paint paint = new Paint();
	private final Paint eraserPaint = new Paint();

	public RenderView(Context context, int width, int height) {
		super(context);
		playGround = new Rect(40, 40, width - 40, height - 40);
		// ball = Bitmap.createScaledBitmap(ball, radius * 2, radius * 2, true);
		star = Bitmap.createScaledBitmap(star, 60, 60, true);
		holes.addElement(new PointF(500f, 500f));
		points.addElement(new PointF(345f, 345f));

		// Set background
		this.setBackgroundResource(R.drawable.bottom);

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
	}

	protected void onDraw(Canvas canvas) {
		bitmapCanvas.drawBitmap(wall, 0, 0, null);
		bitmapCanvas.drawBitmap(wood, playGround, playGround, null);
		canvas.drawBitmap(bitmap, 0, 0, null);
		// // Foreach > drawing holes
		for (PointF s : points) {
			canvas.drawBitmap(star, s.x, s.y, null);
		}
		for (PointF h : holes) {
			bitmapCanvas.drawCircle(h.x + radius, h.y + radius, radius,
					eraserPaint);
		}

	}
}