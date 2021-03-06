package ch.gibb.project.elements;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import ch.gibb.project.enums.StageEnum;

public class Text extends MazeElement {
	public static long usedTime;
	public static int stage;
	public static long pointcount;
	private final SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:SSS");

	public Text(Context context) {
		super(context);
	}

	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setStyle(Style.FILL_AND_STROKE);
		paint.setFakeBoldText(true);
		paint.setColor(Color.BLACK);
		paint.setTextSize(yCompare(30));
		canvas.drawText("Stage: " + stage + "/" + StageEnum.values().length,
				getWidth() / 2 - xCompare(50), yCompare(25), paint);
		String time = sdf.format(new Date((long) Text.usedTime));
		canvas.drawText(String.format("Points: %s", pointcount), xCompare(40),
				getHeight() - yCompare(15), paint);
		canvas.drawText(String.format("Used time: %s", time), getWidth()
				- xCompare(330), getHeight() - yCompare(15), paint);
	}

	public static void resetScore() {
		usedTime = 0;
		pointcount = 0;
	}
}
