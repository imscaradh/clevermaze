package ch.gibb.project.controller;

import android.graphics.PointF;
import ch.gibb.project.activity.Level;
import ch.gibb.project.elements.Ball;
import ch.gibb.project.elements.Hole;
import ch.gibb.project.elements.Maze;
import ch.gibb.project.elements.Point;
import ch.gibb.project.elements.Wall;

public class ActionHandler {
	private Ball ballElement;
	private Maze mazeElement;
	private Hole holeElement;
	private Wall wallElement;
	private Point pointElement;

	public ActionHandler(Level levelActivity) {
		ballElement = levelActivity.getBallElement();
		mazeElement = levelActivity.getMazeElement();
		holeElement = levelActivity.getHoleElement();
		wallElement = levelActivity.getWallElement();
		pointElement = levelActivity.getPointElement();
	}

	public boolean containsBallX(float accelX) {
		if ((ballElement.getCoordinates().x - (accelX * 2f)) > mazeElement
				.getPlayGround().left
				&& (ballElement.getCoordinates().x - (accelX * 2f)) < (mazeElement
						.getPlayGround().right - (ballElement.getRadius() * 2))) {
			return true;
		}
		return false;
	}

	public boolean containsBallY(float accelY) {
		if ((ballElement.getCoordinates().y + (accelY * 2f)) > mazeElement
				.getPlayGround().top
				&& (ballElement.getCoordinates().y + (accelY * 2f)) < mazeElement
						.getPlayGround().bottom - (ballElement.getRadius() * 2)) {
			return true;
		}
		return false;
	}

	public boolean touchOnLeft() {
		if ((ballElement.getCoordinates().x - mazeElement.getPlayGround().left) < (mazeElement
				.getPlayGround().right - ballElement.getCoordinates().x)) {
			return true;
		}
		return false;
	}

	public boolean touchOnTop() {
		if ((ballElement.getCoordinates().y - mazeElement.getPlayGround().top) < (mazeElement
				.getPlayGround().bottom - ballElement.getCoordinates().y)) {
			return true;
		}
		return false;
	}

	public boolean ballInHole() {
		for (PointF hole : holeElement.getHoles()) {
			float dx = Math.abs(ballElement.getCoordinates().x - hole.x);
			float dy = Math.abs(ballElement.getCoordinates().y - hole.y);

			if (Math.sqrt(dx * dx + dy * dy) < 30) {
				return true;
			}
		}
		return false;
	}

	public void checkStarTouch() {
		for (PointF point : pointElement.getPoints()) {
			float dx = Math.abs(ballElement.getCoordinates().x - point.x);
			float dy = Math.abs(ballElement.getCoordinates().y - point.y);

			if (Math.sqrt(dx * dx + dy * dy) < 30) {
				// the ball is enough near to collect
				float px = point.x;
				float py = point.y;
				pointElement.getPoints().remove(point);
				// Only updates the Rect where the Point was .

			}

		}

	}

}
