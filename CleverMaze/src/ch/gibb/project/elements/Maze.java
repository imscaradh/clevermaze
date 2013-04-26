package ch.gibb.project.elements;

import java.util.Vector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import ch.gibb.project.activity.Level;

public class Maze extends MazeElement {
	private Rect playGround;
	private Vector<PointF> holes = new Vector<PointF>();
	private PointF finishPoint;
	private int radius = 34;

	public Maze(Context context, int width, int height) {
		super(context, width, height);

		playGround = new Rect(40, 40, width - 40, height - 40);

		bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		bitmapCanvas = new Canvas();
		bitmapCanvas.setBitmap(bitmap);

		holes = stageManager.getLevelOneHoles();
		finishPoint = stageManager.getLevelOneFinishPoint();
	}

	protected void onDraw(Canvas canvas) {
		bitmapCanvas.drawBitmap(Level.wallImage, 0, 0, null);
		bitmapCanvas.drawBitmap(Level.backgroundImage, playGround, playGround,
				null);
		bitmapCanvas.drawBitmap(Level.finishImage, finishPoint.x,
				finishPoint.y, null);
		canvas.drawBitmap(bitmap, 0, 0, null);

		Paint paint = new Paint();
		paint.setAlpha(0);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		for (PointF h : holes) {
			bitmapCanvas.drawCircle(h.x + radius, h.y + radius, radius, paint);
		}
	}

	public Rect getPlayGround() {
		return playGround;
	}

	public void setPlayGround(Rect playGround) {
		this.playGround = playGround;
	}

	public int getImageWidth() {
		return Level.backgroundImage.getWidth();
	}

	public int getImageHeight() {
		return Level.backgroundImage.getHeight();
	}

	public Vector<PointF> getHoles() {
		return holes;
	}

	public void setHoles(Vector<PointF> holes) {
		this.holes = holes;
	}

	public RectF getFinishRect() {
		return new RectF(finishPoint.x, finishPoint.y,
				Level.finishImage.getWidth() + finishPoint.x,
				Level.finishImage.getHeight() + finishPoint.y);

	}

}
