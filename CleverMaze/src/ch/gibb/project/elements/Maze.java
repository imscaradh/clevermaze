package ch.gibb.project.elements;

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
	private PointF[] holes;
	private PointF finishPoint;
	private int radius = 34;
	private Paint paint;

	public Maze(Level context, int width, int height) {
		super(context);

		playGround = new Rect(40, 40, width - 40, height - 40);

		bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		bitmapCanvas = new Canvas();
		bitmapCanvas.setBitmap(bitmap);

		holes = context.getStage().getHoles();
		finishPoint = context.getStage().getFinishPoint();

		paint = new Paint();
		paint.setAlpha(0);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
	}

	protected void onDraw(Canvas canvas) {
		bitmapCanvas.drawBitmap(Level.getWallImage(), 0, 0, null);
		bitmapCanvas.drawBitmap(Level.getBackgroundImage(), playGround,
				playGround, null);
		bitmapCanvas.drawBitmap(Level.getFinishImage(), finishPoint.x,
				finishPoint.y, null);
		canvas.drawBitmap(bitmap, 0, 0, null);

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
		return Level.getBackgroundImage().getWidth();
	}

	public int getImageHeight() {
		return Level.getBackgroundImage().getHeight();
	}

	public PointF[] getHoles() {
		return holes;
	}

	public void setHoles(PointF[] holes) {
		this.holes = holes;
	}

	public RectF getFinishRect() {
		return new RectF(finishPoint.x, finishPoint.y, Level.getFinishImage()
				.getWidth() + finishPoint.x, Level.getFinishImage().getHeight()
				+ finishPoint.y);

	}

}
