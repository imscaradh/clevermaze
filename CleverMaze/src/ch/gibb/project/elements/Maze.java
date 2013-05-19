package ch.gibb.project.elements;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.Display;
import ch.gibb.project.R;
import ch.gibb.project.activity.Level;
import ch.gibb.project.activity.Welcome;

public class Maze extends MazeElement {
	private Rect playGround;
	private PointF[] holes;
	private PointF finishPoint;
	private int radius = 34;
	private Paint paint;

	public Maze(Level context) {
		super(context);
		Display display = context.getWindowManager().getDefaultDisplay();
		android.graphics.Point displaySize = new android.graphics.Point();
		display.getSize(displaySize);
		// setStaticBitmaps(displaySize.x, displaySize.y);
		int gap = (int) PixelToDp(40);
		playGround = new Rect(gap, gap, displaySize.x - gap, displaySize.y
				- gap);

		bitmap = Bitmap.createBitmap(displaySize.x, displaySize.y,
				Bitmap.Config.ARGB_8888);
		bitmapCanvas = new Canvas();
		bitmapCanvas.setBitmap(bitmap);
		setBackgroundResource(R.drawable.bottom);

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
		bitmapCanvas.drawBitmap(Welcome.wallImage, 0, 0, null);
		Paint p = new Paint();
		p.setStyle(Style.FILL_AND_STROKE);
		bitmapCanvas
				.drawBitmap(Welcome.backgroundImage, null, playGround, null);
		bitmapCanvas.drawBitmap(Welcome.finishImage, finishPoint.x,
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

	// public int getImageWidth() {
	// return backgroundImage.getWidth();
	// }

	// public int getImageHeight() {
	// return backgroundImage.getHeight();
	// }

	public PointF[] getHoles() {
		return holes;
	}

	public void setHoles(PointF[] holes) {
		this.holes = holes;
	}

	public RectF getFinishRect() {
		return new RectF(finishPoint.x, finishPoint.y,
				Welcome.finishImage.getWidth() + finishPoint.x,
				Welcome.finishImage.getHeight() + finishPoint.y);

	}

}
