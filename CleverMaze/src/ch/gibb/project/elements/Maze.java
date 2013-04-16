package ch.gibb.project.elements;

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
import android.view.View;
import ch.gibb.project.R;

public class Maze extends View {
	Random rand = new Random();
	private int radius = 34;
	private Rect playGround;
	private Vector<PointF> holes = new Vector<PointF>();
	private Vector<PointF> points = new Vector<PointF>();
	private Bitmap frame;
	private Bitmap wood;
	private Bitmap wall;
	private Bitmap star;
	private Bitmap bitmap;
	private Canvas bitmapCanvas;
	private final Paint eraserPaint = new Paint();

	public Maze(Context context, int width, int height) {
		super(context);

		setPlayGround(new Rect(40, 40, width - 40, height - 40));
		setStar(Bitmap.createScaledBitmap(getStar(), 60, 60, true));
		getHoles().addElement(new PointF(500f, 500f));
		getPoints().addElement(new PointF(345f, 345f));

		// Set bitmap
		star = BitmapFactory.decodeResource(getResources(), R.drawable.star);
		wood = BitmapFactory.decodeResource(getResources(), R.drawable.wood);
		wall = BitmapFactory.decodeResource(getResources(), R.drawable.wall);
		frame = BitmapFactory.decodeResource(getResources(), R.drawable.bottom);
		bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		bitmapCanvas = new Canvas();
		bitmapCanvas.setBitmap(bitmap);

		setEreaserPaintProps();
	}

	private void setEreaserPaintProps() {
		eraserPaint.setAlpha(0);
		eraserPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		eraserPaint.setAntiAlias(true);
		eraserPaint.setFilterBitmap(true);
		eraserPaint.setDither(true);
	}

	protected void onDraw(Canvas canvas) {
		drawBitmaps();

		for (PointF h : getHoles()) {
			bitmapCanvas.drawCircle(h.x + radius, h.y + radius, radius,
					eraserPaint);
		}
		for (PointF s : getPoints()) {
			canvas.drawBitmap(getStar(), s.x, s.y, null);
		}

	}

	private void drawBitmaps() {
		bitmapCanvas.drawBitmap(frame, 0, 0, null);
		bitmapCanvas.drawBitmap(wall, 0, 0, null);
		bitmapCanvas.drawBitmap(wood, getPlayGround(), getPlayGround(), null);
		bitmapCanvas.drawBitmap(bitmap, 0, 0, null);
	}

	public Rect getPlayGround() {
		return playGround;
	}

	public void setPlayGround(Rect playGround) {
		this.playGround = playGround;
	}

	public Vector<PointF> getHoles() {
		return holes;
	}

	public void setHoles(Vector<PointF> holes) {
		this.holes = holes;
	}

	public Vector<PointF> getPoints() {
		return points;
	}

	public void setPoints(Vector<PointF> points) {
		this.points = points;
	}

	public Bitmap getStar() {
		return star;
	}

	public void setStar(Bitmap star) {
		this.star = star;
	}
}