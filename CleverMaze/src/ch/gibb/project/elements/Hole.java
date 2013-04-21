package ch.gibb.project.elements;

import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

public class Hole extends MazeElement {
	private int radius = 34;
	private Vector<PointF> holes = new Vector<PointF>();

	public Vector<PointF> getHoles() {
		return holes;
	}

	public void setHoles(Vector<PointF> holes) {
		this.holes = holes;
	}

	public Hole(Context context, int width, int height) {
		super(context, width, height);
		// backgroundImage = Bitmap.createScaledBitmap(
		// BitmapFactory.decodeResource(getResources(), R.drawable.hole),
		// 0, 0, true);

		holes = stageManager.firstHoles();
	}

	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setAlpha(0);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		for (PointF h : holes) {
			canvas.drawCircle(h.x + radius, h.y + radius, radius, paint);
		}
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

}
