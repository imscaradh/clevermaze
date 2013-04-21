package ch.gibb.project.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
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
		coordinates = stageManager.getLevelOneStartPoint();
	}

	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setDither(true);
		canvas.drawBitmap(backgroundImage, coordinates.x, coordinates.y, paint);
		invalidate();
	}

	public RectF generateRect(float x, float y) {
		RectF ball = new RectF();
		ball.set(x, y, x + getImageWidth(), y + getImageHeight());
		return ball;

	}

	public void moveX(float x) {
		getCoordinates().x = getCoordinates().x - (x * 2f);
	}

	public void moveY(float y) {
		getCoordinates().y = getCoordinates().y + (y * 2f);
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
}