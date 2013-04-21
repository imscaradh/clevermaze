package ch.gibb.project.controller;

import android.graphics.PointF;
import android.graphics.RectF;
import ch.gibb.project.activity.Level;
import ch.gibb.project.elements.Ball;
import ch.gibb.project.elements.Hole;
import ch.gibb.project.elements.Maze;
import ch.gibb.project.elements.Point;
import ch.gibb.project.elements.Wall;
import ch.gibb.project.enums.Dimension;

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
		if ((ballElement.getCoordinates().x - (accelX * 2)) > mazeElement
				.getPlayGround().left
				&& (ballElement.getCoordinates().x - (accelX * 2)) < (mazeElement
						.getPlayGround().right - (ballElement.getDiameter()))) {
			return true;
		}
		return false;
	}

	public boolean containsBallY(float accelY) {
		if ((ballElement.getCoordinates().y + (accelY * 2)) > mazeElement
				.getPlayGround().top
				&& (ballElement.getCoordinates().y + (accelY * 2)) < mazeElement
						.getPlayGround().bottom - (ballElement.getDiameter())) {
			return true;
		}
		return false;
	}

	public boolean touchOnWall(Dimension ort) {
		switch (ort) {
		case LEFT:
			if ((ballElement.getCoordinates().x - mazeElement.getPlayGround().left) < (mazeElement
					.getPlayGround().right - ballElement.getCoordinates().x)) {
				return true;
			}
			break;
		case TOP:
			if ((ballElement.getCoordinates().y - mazeElement.getPlayGround().top) < (mazeElement
					.getPlayGround().bottom - ballElement.getCoordinates().y)) {
				return true;
			}
			break;
		case RIGHT:
			if ((ballElement.getCoordinates().x - mazeElement.getPlayGround().right) < (mazeElement
					.getPlayGround().left - ballElement.getCoordinates().x)) {
				return true;
			}
			break;
		case BOTTOM:
			if ((ballElement.getCoordinates().y - mazeElement.getPlayGround().bottom) < (mazeElement
					.getPlayGround().top - ballElement.getCoordinates().y)) {
				return true;
			}
			break;
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
		PointF toRemove = null;
		for (PointF point : pointElement.getPoints()) {
			float dx = Math.abs(ballElement.getCoordinates().x - point.x);
			float dy = Math.abs(ballElement.getCoordinates().y - point.y);

			if (Math.sqrt(dx * dx + dy * dy) < 30) {
				toRemove = point;
				break;
			}
		}
		if (toRemove != null) {
			pointElement.getPoints().remove(toRemove);
			pointElement.postInvalidate((int) toRemove.x - 1,
					(int) toRemove.y + 1,
					(int) toRemove.x + pointElement.getImageWidth() + 1,
					(int) toRemove.y - pointElement.getImageHeight() + 1);
		}
	}

	public boolean checkWallTouch(float x, float y) {
		for (RectF rect : wallElement.getWalls()) {
			RectF ballAsRect = ballElement.generateRect(x, y);
			if (ballAsRect.intersect(rect)) {
				return true;
			}
		}
		return false;
	}

	public void moveAndCheckX(float accelX) {
		if (containsBallX(accelX)) {
			float newX = ballElement.getCoordinates().x - (accelX * 2);
			if (!checkWallTouch(newX, ballElement.getCoordinates().y)) {
				ballElement.getCoordinates().x = newX;
			}
		} else if (touchOnWall(Dimension.LEFT)) {
			ballElement.getCoordinates().x = mazeElement.getPlayGround().left;
		} else if (touchOnWall(Dimension.RIGHT)) {
			ballElement.getCoordinates().x = (mazeElement.getPlayGround().right - (ballElement
					.getRadius() * 2));
		}
	}

	public void moveAndCheckY(float accelY) {
		if (containsBallY(accelY)) {
			float newY = ballElement.getCoordinates().y + (accelY * 2);
			if (!checkWallTouch(ballElement.getCoordinates().x, newY)) {
				ballElement.getCoordinates().y = newY;
			}

		} else if (touchOnWall(Dimension.TOP)) {
			ballElement.getCoordinates().y = mazeElement.getPlayGround().top;
		} else if (touchOnWall(Dimension.BOTTOM)) {
			ballElement.getCoordinates().y = (mazeElement.getPlayGround().bottom - (ballElement
					.getRadius() * 2));
		}
	}
}
