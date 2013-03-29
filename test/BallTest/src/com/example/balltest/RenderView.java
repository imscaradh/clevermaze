package com.example.balltest;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Picture;
import android.graphics.Rect;
import android.view.View;

// Generates a Colorflash (Caution: Eyecancer)
class Ball extends View {
	Random rand = new Random();
	Picture ball;
	float xPosition = 200f;
	float yPosition = 200f;
	Rect playGround;
	int radius = 25;

	public Ball(Context context, int width, int height) {
		super(context);
		playGround = new Rect(25, 25, width - 25, height - 25);
		// ball = picture;
	}

	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setStyle(Style.FILL_AND_STROKE);
		paint.setColor(Color.BLACK);
		canvas.drawRect(playGround, paint);
		paint.setColor(Color.GRAY);
		canvas.drawCircle(xPosition, yPosition, radius, paint);
		invalidate();
	}

	public boolean containsBall(float accelX, float accelY) {
		if (playGround.contains((int) (xPosition - (accelX * 2f)),
				(int) (yPosition - (accelY * 2f)))) {
			return true;
		}
		return false;
	}
}