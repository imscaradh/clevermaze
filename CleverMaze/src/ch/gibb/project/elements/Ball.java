package ch.gibb.project.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import ch.gibb.project.R;

public class Ball extends MazeElement {
	private int radius = 30;
	private Bitmap backgroundImage;
	private PointF coordinates;

	public Ball(Context context, int width, int height) {
		super(context, width, height);
		backgroundImage = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(getResources(), R.drawable.ball),
				radius * 2, radius * 2, true);
		coordinates = new PointF(200f, 200f);
	}

	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setDither(true);
		canvas.drawBitmap(backgroundImage, coordinates.x, coordinates.y, paint);
		invalidate();
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public PointF getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(PointF coordinates) {
		this.coordinates = coordinates;
	}
}