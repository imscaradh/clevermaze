package ch.gibb.project.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.View;
import ch.gibb.project.R;

public class Ball extends View {
	private int radius = 30;
	private Bitmap ball;
	private PointF b;

	public Ball(Context context) {
		super(context);
		ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball2);
		ball = Bitmap.createScaledBitmap(ball, radius * 2, radius * 2, true);
		b = new PointF(200f, 200f);
	}

	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setDither(true);
		canvas.drawBitmap(ball, b.x, b.y, paint);
		invalidate();
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public PointF getB() {
		return b;
	}

	public void setB(PointF b) {
		this.b = b;
	}
}