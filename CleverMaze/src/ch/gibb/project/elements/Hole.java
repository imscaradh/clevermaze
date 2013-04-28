package ch.gibb.project.elements;

import android.graphics.Canvas;
import android.graphics.PointF;
import ch.gibb.project.activity.Level;

public class Hole extends MazeElement {
	private int radius = 34;
	private PointF[] holes;

	public Hole(Level context) {
		super(context);
		holes = context.getStage().getHoles();
	}

	protected void onDraw(Canvas canvas) {

	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public PointF[] getHoles() {
		return holes;
	}

	public void setHoles(PointF[] holes) {
		this.holes = holes;
	}
}
