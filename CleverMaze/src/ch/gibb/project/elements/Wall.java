package ch.gibb.project.elements;

import java.util.Vector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import ch.gibb.project.R;

public class Wall extends MazeElement {
	private Bitmap backgroundImage;
	private Vector<Rect> walls = new Vector<Rect>();

	public Vector<Rect> getWalls() {
		return walls;
	}

	public void setWalls(Vector<Rect> walls) {
		this.walls = walls;
	}

	public Wall(Context context, int width, int height) {
		super(context, width, height);
		backgroundImage = BitmapFactory.decodeResource(getResources(),
				R.drawable.wall);

		walls = stageManager.firstWalls();
	}

	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(Color.argb(200, 212, 212, 212));
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.OVERLAY));
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		for (Rect w : walls) {
			bitmapCanvas.drawRect(w, paint);
		}
	}
}
