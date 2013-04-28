package ch.gibb.project.elements;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import ch.gibb.project.controller.ActionHandler;

public class Text extends MazeElement {

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
		canvas.drawText("Points:" + ActionHandler.pointcount,
				getWidth() / 2 - 50, 25, paint);

		canvas.drawText("Stage:", 40, getHeight() - 20, paint);
		canvas.drawText(String.format("%d:%02d", ActionHandler.gameminutes,
				ActionHandler.gameseconds), getWidth() - 120, getHeight() - 20,
				paint);
	}
}
