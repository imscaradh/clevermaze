package ch.gibb.project.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.DisplayMetrics;
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

	public float PixelToDp(float pixel) {
		DisplayMetrics metrics = this.getResources().getDisplayMetrics();
		float dp = pixel / (metrics.density / 2);
		return dp;
	}

	public float xCompare(float pixel) {
		return pixel * context.getxCompareValue();
	}

	public float yCompare(float pixel) {
		return pixel * context.getyCompareValue();
	}

	public float getDensityValueX() {
		return context.getxCompareValue();
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

}
