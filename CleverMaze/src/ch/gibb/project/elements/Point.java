package ch.gibb.project.elements;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PointF;
import ch.gibb.project.R;
import ch.gibb.project.activity.Level;

public class Point extends MazeElement {
	private int value;
	private Bitmap backgroundImage;
	private ArrayList<PointF> points;

	public Point(Level context, int width, int height) {
		super(context, width, height);
		backgroundImage = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(getResources(), R.drawable.star),
				60, 60, true);

		// TODO: Using Vector?
		points = context.getStage().getStars();
	}

	protected void onDraw(Canvas canvas) {
		for (PointF s : points) {
			canvas.drawBitmap(backgroundImage, s.x, s.y, null);
		}
	}

	public ArrayList<PointF> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<PointF> points) {
		this.points = points;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getImageWidth() {
		return backgroundImage.getWidth();
	}

	public int getImageHeight() {
		return backgroundImage.getHeight();
	}

}
