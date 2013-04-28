package ch.gibb.project.elements;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import ch.gibb.project.R;
import ch.gibb.project.activity.Level;

public class Ball extends MazeElement {
	private int radius = 30;
	private Bitmap backgroundImage;
	private PointF coordinates;
	private Paint paint;

	public Ball(Level context) {
		super(context);
		backgroundImage = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(getResources(), R.drawable.ball),
				radius * 2, radius * 2, true);
		coordinates = new PointF();
		coordinates.x = context.getStage().getStartPoint().x;
		coordinates.y = context.getStage().getStartPoint().y;

		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setDither(true);
	}

	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(backgroundImage, coordinates.x, coordinates.y, paint);
		invalidate();
	}

	public RectF generateRect(float x, float y) {
		RectF ball = new RectF();
		ball.set(x, y, x + getImageWidth(), y + getImageHeight());
		return ball;

	}

	public void moveX(float x) {
		coordinates.x = coordinates.x - (x * 2f);
	}

	public void moveY(float y) {
		coordinates.y = coordinates.y + (y * 2f);
	}

	public int getRadius() {
		return radius;
	}

	public int getDiameter() {
		return radius * 2;
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

	public int getImageWidth() {
		return backgroundImage.getWidth();
	}

	public int getImageHeight() {
		return backgroundImage.getHeight();
	}

	public RectF getBallRect() {
		return new RectF(coordinates.x, coordinates.y, getImageWidth()
				+ coordinates.x, getImageHeight() + coordinates.y);
	}
}