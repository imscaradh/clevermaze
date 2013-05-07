package ch.gibb.project.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.View;

public class MazeElement extends View {
	protected Canvas bitmapCanvas;
	protected Bitmap bitmap;
	private Point position;

	public MazeElement(Context context) {
		super(context);
	}

	public float PixelToDp(float pixel) {
		DisplayMetrics metrics = this.getResources().getDisplayMetrics();
		float dp = pixel / (metrics.densityDpi / 320f);
		return dp;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

}
