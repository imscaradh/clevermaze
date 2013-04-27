package ch.gibb.project.elements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import ch.gibb.project.activity.Level;

public class Wall extends MazeElement {
	private RectF[] walls;

	public Wall(Level context, int width, int height) {
		super(context, width, height);
		walls = context.getStage().getWalls();
	}

	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(Color.argb(200, 212, 212, 212));
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.OVERLAY));
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		for (RectF w : walls) {
			canvas.drawRect(w, paint);
		}
	}

	public RectF[] getWalls() {
		return walls;
	}

	public void setWalls(RectF[] walls) {
		this.walls = walls;
	}
}
