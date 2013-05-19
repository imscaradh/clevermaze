package ch.gibb.project.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Debug;
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

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	/**
	 * Checks if a bitmap with the specified size fits in memory
	 * 
	 * @param bmpwidth
	 *            Bitmap width
	 * @param bmpheight
	 *            Bitmap height
	 * @param bmpdensity
	 *            Bitmap bpp (use 2 as default)
	 * @return true if the bitmap fits in memory false otherwise
	 */
	public static boolean checkBitmapFitsInMemory(long bmpwidth,
			long bmpheight, int bmpdensity) {
		long reqsize = bmpwidth * bmpheight * bmpdensity;
		long allocNativeHeap = Debug.getNativeHeapAllocatedSize();

		final long heapPad = (long) Math.max(4 * 1024 * 1024, Runtime
				.getRuntime().maxMemory() * 0.1);
		if ((reqsize + allocNativeHeap + heapPad) >= Runtime.getRuntime()
				.maxMemory()) {
			return false;
		}
		return true;

	}

}
