package com.example.balltest;

import android.graphics.PointF;

public class Check {
	BallView ballView;
	MazeView back;

	public Check(BallView ball, MazeView back) {
		this.ballView = ball;
		this.back = back;
	}

	public boolean containsBallX(float accelX) {
		if ((ballView.b.x - (accelX * 2f)) > back.playGround.left
				&& (ballView.b.x - (accelX * 2f)) < (back.playGround.right - (ballView.radius * 2))) {
			return true;
		}
		return false;
	}

	public boolean containsBallY(float accelY) {
		if ((ballView.b.y + (accelY * 2f)) > back.playGround.top
				&& (ballView.b.y + (accelY * 2f)) < back.playGround.bottom
						- (ballView.radius * 2)) {
			return true;
		}
		return false;
	}

	public boolean touchOnLeft() {
		if ((ballView.b.x - back.playGround.left) < (back.playGround.right - ballView.b.x)) {
			return true;
		}
		return false;
	}

	public boolean touchOnTop() {
		if ((ballView.b.y - back.playGround.top) < (back.playGround.bottom - ballView.b.y)) {
			return true;
		}
		return false;
	}

	public boolean ballInHole() {
		for (PointF h : back.holes) {
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
		for (PointF p : back.points) {
			float dx = Math.abs(ballView.b.x - p.x);
			float dy = Math.abs(ballView.b.y - p.y);

			if (Math.sqrt(dx * dx + dy * dy) < 30) {
				// the ball is enough near to collect
				back.points.remove(p);
			}
		}

	}

}
