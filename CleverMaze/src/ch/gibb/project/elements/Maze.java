package ch.gibb.project.elements;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class Maze extends MazeElement {
	private Rect playGround;
	private PointF[] holes;
	private PointF finishPoint;
	private int radius = 34;
	private Paint paint;

	private static Bitmap backgroundImage;
	private static Bitmap wallImage;
	private static Bitmap finishImage;

	public Maze(Level context) {
		super(context);
		Display display = context.getWindowManager().getDefaultDisplay();
		android.graphics.Point displaySize = new android.graphics.Point();
		display.getSize(displaySize);
		setStaticBitmaps(displaySize.x, displaySize.y);
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
		bitmapCanvas.drawBitmap(wallImage, 0, 0, null);
		wallImage.recycle();
		Paint p = new Paint();
		p.setStyle(Style.FILL_AND_STROKE);
		bitmapCanvas.drawBitmap(backgroundImage, null, playGround, null);
		backgroundImage.recycle();
		bitmapCanvas
				.drawBitmap(finishImage, finishPoint.x, finishPoint.y, null);
		finishImage.recycle();
		canvas.drawBitmap(bitmap, 0, 0, null);
		for (PointF h : holes) {
			bitmapCanvas.drawCircle(h.x + radius, h.y + radius, radius, paint);
		}
	}

	public void setStaticBitmaps(int width, int height) {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inInputShareable = true;
		// TODO: Shrink Image Correctly
		backgroundImage = Bitmap.createScaledBitmap(BitmapFactory
				.decodeResource(getResources(), R.drawable.wood, opts),
				width - 40, height - 40, true);
		wallImage = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
				getResources(), R.drawable.wall, opts), width, height, true);
		finishImage = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
				getResources(), R.drawable.finish, opts), 73, 73, true);
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

	public PointF[] getHoles() {
		return holes;
	}

	public void setHoles(PointF[] holes) {
		this.holes = holes;
	}

	public RectF getFinishRect() {
		return new RectF(finishPoint.x, finishPoint.y, finishImage.getWidth()
				+ finishPoint.x, finishImage.getHeight() + finishPoint.y);

	}

}
