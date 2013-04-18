package ch.gibb.project.elements;

import java.util.Vector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PointF;
import ch.gibb.project.R;

public class Point extends MazeElement {
	private int value;
	private Bitmap backgroundImage;
	private Vector<PointF> points = new Vector<PointF>();

	public Vector<PointF> getPoints() {
		return points;
	}

	public void setPoints(Vector<PointF> points) {
		this.points = points;
	}

	public Point(Context context, int width, int height) {
		super(context, width, height);
		backgroundImage = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(getResources(), R.drawable.star),
				60, 60, true);
		points = stageManager.firstStars();
	}

	protected void onDraw(Canvas canvas) {
		for (PointF s : points) {
			bitmapCanvas.drawBitmap(backgroundImage, s.x, s.y, null);
		}
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
