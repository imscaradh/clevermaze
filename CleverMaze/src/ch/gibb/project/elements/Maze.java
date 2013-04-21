package ch.gibb.project.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import ch.gibb.project.R;

public class Maze extends MazeElement {
	private Rect playGround;
	private Bitmap backgroundImage;

	public Maze(Context context, int width, int height) {
		super(context, width, height);
		backgroundImage = BitmapFactory.decodeResource(getResources(),
				R.drawable.wood);
		backgroundImage = Bitmap.createScaledBitmap(backgroundImage,
				width - 40, height - 40, true);

		playGround = new Rect(40, 40, width - 40, height - 40);

	}

	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(backgroundImage, playGround, playGround, null);
	}

	public Rect getPlayGround() {
		return playGround;
	}

	public void setPlayGround(Rect playGround) {
		this.playGround = playGround;
	}

	public int getImageWidth() {
		return backgroundImage.getWidth();
	}

	public int getImageHeight() {
		return backgroundImage.getHeight();
	}
}
