package com.example.balltest;

import android.graphics.PointF;

public class Check {
	BallView ballView;
	MazeView mazeView;

	public Check(BallView ball, MazeView back) {
		this.ballView = ball;
		this.mazeView = back;
	}

	public boolean containsBallX(float accelX) {
		if ((ballView.b.x - (accelX * 2f)) > mazeView.playGround.left
				&& (ballView.b.x - (accelX * 2f)) < (mazeView.playGround.right - (ballView.radius * 2))) {
			return true;
		}
		return false;
	}

	public boolean containsBallY(float accelY) {
		if ((ballView.b.y + (accelY * 2f)) > mazeView.playGround.top
				&& (ballView.b.y + (accelY * 2f)) < mazeView.playGround.bottom
						- (ballView.radius * 2)) {
			return true;
		}
		return false;
	}

	public boolean touchOnLeft() {
		if ((ballView.b.x - mazeView.playGround.left) < (mazeView.playGround.right - ballView.b.x)) {
			return true;
		}
		return false;
	}

	public boolean touchOnTop() {
		if ((ballView.b.y - mazeView.playGround.top) < (mazeView.playGround.bottom - ballView.b.y)) {
			return true;
		}
		return false;
	}

	public boolean ballInHole() {
		for (PointF h : mazeView.holes) {
			float dx = Math.abs(ballView.b.x - h.x);
			float dy = Math.abs(ballView.b.y - h.y);

			if (Math.sqrt(dx * dx + dy * dy) < 30) {
				// the ball is enough near to fall
				// ballView = Bitmap.createScaledBitmap(ballView.ball, 50,
				// 50,ytrue);
				// ballInHole = true;
				return true;

			}
		}
		return false;
	}

	public void checkStarTouch() {
		for (PointF p : mazeView.points) {
			float dx = Math.abs(ballView.b.x - p.x);
			float dy = Math.abs(ballView.b.y - p.y);

			if (Math.sqrt(dx * dx + dy * dy) < 30) {
				// the ball is enough near to collect
				float px = p.x;
				float py = p.y;
				mazeView.points.remove(p);
				// Only updates the Rect where the Point was.
				mazeView.postInvalidate((int) px - 1, (int) py + 1, (int) px
						+ mazeView.star.getWidth() + 1, (int) py
						- mazeView.star.getHeight() + 1);

			}
		}

	}

}
