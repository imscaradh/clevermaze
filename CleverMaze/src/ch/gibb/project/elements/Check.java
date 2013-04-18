package ch.gibb.project.elements;

import android.graphics.PointF;

public class Check {
	private Ball ballView;
	private Maze mazeView;

	public Check(Ball ball, Maze back) {
		this.ballView = ball;
		this.mazeView = back;
	}

	public boolean containsBallX(float accelX) {
		if ((ballView.getB().x - (accelX * 2f)) > mazeView.getPlayGround().left
				&& (ballView.getB().x - (accelX * 2f)) < (mazeView
						.getPlayGround().right - (ballView.getRadius() * 2))) {
			return true;
		}
		return false;
	}

	public boolean containsBallY(float accelY) {
		if ((ballView.getB().y + (accelY * 2f)) > mazeView.getPlayGround().top
				&& (ballView.getB().y + (accelY * 2f)) < mazeView
						.getPlayGround().bottom - (ballView.getRadius() * 2)) {
			return true;
		}
		return false;
	}

	public boolean touchOnLeft() {
		if ((ballView.getB().x - mazeView.getPlayGround().left) < (mazeView
				.getPlayGround().right - ballView.getB().x)) {
			return true;
		}
		return false;
	}

	public boolean touchOnTop() {
		if ((ballView.getB().y - mazeView.getPlayGround().top) < (mazeView
				.getPlayGround().bottom - ballView.getB().y)) {
			return true;
		}
		return false;
	}

	public boolean ballInHole() {
		for (PointF h : mazeView.getHoles()) {
			float dx = Math.abs(ballView.getB().x - h.x);
			float dy = Math.abs(ballView.getB().y - h.y);

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
		for (PointF p : mazeView.getPoints()) {
			float dx = Math.abs(ballView.getB().x - p.x);
			float dy = Math.abs(ballView.getB().y - p.y);

			if (Math.sqrt(dx * dx + dy * dy) < 30) {
				// the ball is enough near to collect
				float px = p.x;
				float py = p.y;
				mazeView.getPoints().remove(p);
				// Only updates the Rect where the Point was .
				mazeView.postInvalidate((int) px - 1, (int) py + 1, (int) px
						+ mazeView.getStar().getWidth() + 1, (int) py
						- mazeView.getStar().getHeight() + 1);

			}
		}

	}

}
