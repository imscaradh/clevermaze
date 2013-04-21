package ch.gibb.project.elements;

import java.util.Vector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;

public class Hole extends MazeElement {
	private int radius = 34;
	private Bitmap backgroundImage;
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

	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

}
