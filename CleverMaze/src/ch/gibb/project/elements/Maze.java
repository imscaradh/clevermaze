package ch.gibb.project.elements;

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
import android.graphics.RectF;
import ch.gibb.project.R;

public class Maze extends MazeElement {
	private Rect playGround;
	private Bitmap backgroundImage;
	private Bitmap wallImage;
	private Vector<PointF> holes = new Vector<PointF>();
	private Bitmap finishImage;
	private PointF finishPoint;
	private int radius = 34;

	public Maze(Context context, int width, int height) {
		super(context, width, height);
		backgroundImage = BitmapFactory.decodeResource(getResources(),
				R.drawable.wood);
		backgroundImage = Bitmap.createScaledBitmap(backgroundImage,
				width - 40, height - 40, true);
		wallImage = BitmapFactory.decodeResource(getResources(),
				R.drawable.wall);
		wallImage = Bitmap.createScaledBitmap(wallImage, width, height, true);
		finishImage = BitmapFactory.decodeResource(getResources(),
				R.drawable.finish);
		finishImage = Bitmap.createScaledBitmap(finishImage, 73, 73, true);

		playGround = new Rect(40, 40, width - 40, height - 40);

		bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		bitmapCanvas = new Canvas();
		bitmapCanvas.setBitmap(bitmap);

		holes = stageManager.getLevelOneHoles();
		finishPoint = stageManager.getLevelOneFinishPoint();
	}

	protected void onDraw(Canvas canvas) {
		bitmapCanvas.drawBitmap(wallImage, 0, 0, null);
		bitmapCanvas.drawBitmap(backgroundImage, playGround, playGround, null);
		bitmapCanvas
				.drawBitmap(finishImage, finishPoint.x, finishPoint.y, null);
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
		return backgroundImage.getWidth();
	}

	public int getImageHeight() {
		return backgroundImage.getHeight();
	}

	public Vector<PointF> getHoles() {
		return holes;
	}

	public void setHoles(Vector<PointF> holes) {
		this.holes = holes;
	}

	public RectF getFinishRect() {
		return new RectF(finishPoint.x, finishPoint.y, finishImage.getWidth()
				+ finishPoint.x, finishImage.getHeight() + finishPoint.y);

	}

}
