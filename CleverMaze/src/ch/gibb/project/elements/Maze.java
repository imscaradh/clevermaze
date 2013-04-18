package ch.gibb.project.elements;

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
import ch.gibb.project.R;

public class Maze extends View {
	private int radius = 34;
	private Rect playGround;
	private Vector<PointF> holes = new Vector<PointF>();
	private Vector<PointF> points = new Vector<PointF>();
	private Bitmap wood;
	private Bitmap wall;
	private Vector<Rect> walls = new Vector<Rect>();
	private Bitmap star;
	private Bitmap bitmap;
	private Canvas bitmapCanvas;
	private Stages stages = new Stages();
	private Paint paint = new Paint();
	private final Paint eraserPaint = new Paint();

	public Maze(Context context, int width, int height) {
		super(context);
		wood = BitmapFactory.decodeResource(getResources(), R.drawable.wood);
		wall = BitmapFactory.decodeResource(getResources(), R.drawable.wall);
		star = BitmapFactory.decodeResource(getResources(), R.drawable.star);

		playGround = new Rect(40, 40, width - 40, height - 40);
		star = Bitmap.createScaledBitmap(star, 60, 60, true);
		holes = stages.firstHoles();
		points = stages.firstStars();
		walls = stages.firstWalls();

		// Set bitmap
		bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		bitmapCanvas = new Canvas();
		bitmapCanvas.setBitmap(bitmap);

		setEreaserPaintProps();

		paint.setColor(Color.argb(200, 212, 212, 212));
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.OVERLAY));
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
	}

	private void setEreaserPaintProps() {
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