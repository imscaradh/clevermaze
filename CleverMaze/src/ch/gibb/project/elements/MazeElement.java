package ch.gibb.project.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View;
import ch.gibb.project.activity.Level;

public class MazeElement extends View {
	protected Canvas bitmapCanvas;
	protected Bitmap bitmap;
	private Point position;
	protected Level context;

	public MazeElement(Context context) {
		super(context);
		this.context = (Level) context;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

}
