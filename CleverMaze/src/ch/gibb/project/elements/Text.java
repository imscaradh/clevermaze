package ch.gibb.project.elements;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class Text extends MazeElement {
	public static String usedTime;
	public static int stage;
	public static int pointcount;

	public Text(Context context, int width, int height) {
		super(context, width, height);
		// TODO Auto-generated constructor stub
	}

	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setStyle(Style.FILL_AND_STROKE);
		paint.setFakeBoldText(true);
		paint.setColor(Color.BLACK);
		paint.setTextSize(30);
		canvas.drawText("Points:" + pointcount, getWidth() / 2 - 50, 25, paint);
		canvas.drawText("Stage: " + stage, 40, getHeight() - 20, paint);
		canvas.drawText("Used time: " + usedTime, getWidth() - 250,
				getHeight() - 20, paint);
	}
}
