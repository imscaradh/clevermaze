package com.example.balltest;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Picture;
import android.graphics.Point;
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

	public boolean contains() {
		Point p1 = new Point(playGround.top, playGround.left);
		Point p2 = new Point(playGround.top + playGround.bottom,
				playGround.left);
		Point p3 = new Point(playGround.top + playGround.bottom,
				playGround.left + playGround.right);
		Point p4 = new Point(playGround.top, playGround.left + playGround.right);
		Point[] points = new Point[] { p1, p2, p3, p4 };
		for (Point p : points) {
			if ((xPosition - p.x) + (yPosition - p.y) > radius) {
				return false;
			}
		}
		return true;
	}

	public boolean containsBallX(float accelX) {
		if ((xPosition - (accelX * 2f) - 25) > playGround.left
				&& (xPosition - (accelX * 2f) + 25) < playGround.right) {
			return true;
		}
		return false;
	}

	public boolean containsBallY(float accelY) {
		if ((yPosition + (accelY * 2f) - 25) > playGround.top
				&& (yPosition + (accelY * 2f) + 25) < playGround.bottom) {
			return true;
		}
		return false;
	}
}