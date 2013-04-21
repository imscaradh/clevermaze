package ch.gibb.project.controller;

import java.util.Vector;

import android.graphics.PointF;
import android.graphics.RectF;

public class Stages {

	public Stages() {
	}

	public PointF getLevelOneStartPoint() {
		return new PointF(600f, 1180f);
	}

	public PointF getLevelOneFinishPoint() {
		return new PointF(200f, 200f);
	}

	public Vector<PointF> getLevelOneHoles() {
		Vector<PointF> holes = new Vector<PointF>();
		// TODO: Anpassen auf Displaygrsse (incl. Playground etc.)
		holes.add(new PointF(612f, 40f));
		holes.add(new PointF(539f, 40f));
		holes.add(new PointF(466f, 40f));
		holes.add(new PointF(393f, 40f));
		holes.add(new PointF(320f, 40f));
		holes.add(new PointF(247f, 40f));
		holes.add(new PointF(612f, 113f));
		holes.add(new PointF(559f, 186f));
		holes.add(new PointF(559f, 259f));
		holes.add(new PointF(320f, 332f));
		holes.add(new PointF(103f, 332f));
		holes.add(new PointF(186f, 478f));
		holes.add(new PointF(395f, 551f));
		holes.add(new PointF(40f, 1172f));
		holes.add(new PointF(40f, 1099f));
		holes.add(new PointF(40f, 1026f));
		holes.add(new PointF(40f, 953f));
		holes.add(new PointF(176f, 1026f));
		holes.add(new PointF(176f, 953f));
		holes.add(new PointF(612f, 880f));
		holes.add(new PointF(176f, 880f));
		holes.add(new PointF(176f, 807f));
		holes.add(new PointF(120f, 750f));

		return holes;

	}

	public Vector<PointF> getLevelOneStars() {
		Vector<PointF> stars = new Vector<PointF>();
		stars.add(new PointF(40f, 40f));
		stars.add(new PointF(320f, 259f));
		stars.add(new PointF(320f, 405f));
		stars.add(new PointF(193f, 740f));
		stars.add(new PointF(622f, 186f));
		return stars;
	}

	public Vector<RectF> getLevelOneWalls() {
		Vector<RectF> walls = new Vector<RectF>();
		walls.add(new RectF(113, 40, 164, 320));
		walls.add(new RectF(119, 480, 170, 740));
		walls.add(new RectF(329, 963, 685, 1014));
		walls.add(new RectF(189, 1109, 685, 1160));
		walls.add(new RectF(259, 250, 310, 559));
		walls.add(new RectF(405, 200, 456, 539));
		walls.add(new RectF(456, 450, 580, 501));
		walls.add(new RectF(529, 501, 580, 801));
		walls.add(new RectF(405, 700, 456, 963));
		return walls;
	}

}
