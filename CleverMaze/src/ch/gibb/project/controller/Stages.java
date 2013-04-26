package ch.gibb.project.controller;

import java.util.Vector;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.DisplayMetrics;

public class Stages {
	Context context;

	public Stages(Context context) {
		this.context = context;
	}

	// Level 1
	public PointF getLevelOneStartPoint() {
		return new PointF(dpFromPx(600f), dpFromPx(1180f));
	}

	public PointF getLevelOneFinishPoint() {
		return new PointF(dpFromPx(600f), dpFromPx(1023f));
	}

	public Vector<PointF> getLevelOneHoles() {
		Vector<PointF> holes = new Vector<PointF>();
		// TODO: Anpassen auf Displaygrsse (incl. Playground etc.)
		holes.add(new PointF(dpFromPx(612f), dpFromPx(40f)));
		holes.add(new PointF(dpFromPx(539f), dpFromPx(40f)));
		holes.add(new PointF(dpFromPx(466f), dpFromPx(40f)));
		holes.add(new PointF(dpFromPx(393f), dpFromPx(40f)));
		holes.add(new PointF(dpFromPx(320f), dpFromPx(40f)));
		holes.add(new PointF(dpFromPx(247f), dpFromPx(40f)));
		holes.add(new PointF(dpFromPx(612f), dpFromPx(113f)));
		holes.add(new PointF(dpFromPx(559f), dpFromPx(186f)));
		holes.add(new PointF(dpFromPx(559f), dpFromPx(259f)));
		holes.add(new PointF(dpFromPx(320f), dpFromPx(332f)));
		holes.add(new PointF(dpFromPx(103f), dpFromPx(332f)));
		holes.add(new PointF(dpFromPx(186f), dpFromPx(478f)));
		holes.add(new PointF(dpFromPx(395f), dpFromPx(551f)));
		holes.add(new PointF(dpFromPx(40f), dpFromPx(1172f)));
		holes.add(new PointF(dpFromPx(40f), dpFromPx(1099f)));
		holes.add(new PointF(dpFromPx(40f), dpFromPx(1026f)));
		holes.add(new PointF(dpFromPx(40f), dpFromPx(953f)));
		holes.add(new PointF(dpFromPx(176f), dpFromPx(1026f)));
		holes.add(new PointF(dpFromPx(176f), dpFromPx(953f)));
		holes.add(new PointF(dpFromPx(612f), dpFromPx(880f)));
		holes.add(new PointF(dpFromPx(176f), dpFromPx(880f)));
		holes.add(new PointF(dpFromPx(176f), dpFromPx(807f)));
		holes.add(new PointF(dpFromPx(120f), dpFromPx(750f)));

		return holes;
	}

	public Vector<PointF> getLevelOneStars() {
		Vector<PointF> stars = new Vector<PointF>();
		stars.add(new PointF(dpFromPx(40f), dpFromPx(40f)));
		stars.add(new PointF(dpFromPx(320f), dpFromPx(259f)));
		stars.add(new PointF(dpFromPx(320f), dpFromPx(405f)));
		stars.add(new PointF(dpFromPx(193f), dpFromPx(740f)));
		stars.add(new PointF(dpFromPx(622f), dpFromPx(186f)));
		return stars;
	}

	public Vector<RectF> getLevelOneWalls() {
		// TODO: dpFromPx
		// @formatter:off
		Vector<RectF> walls = new Vector<RectF>();
		walls.add(new RectF(dpFromPx(113), dpFromPx(40), dpFromPx(164), dpFromPx(320)));
		walls.add(new RectF(dpFromPx(119), dpFromPx(480), dpFromPx(170), dpFromPx(740)));
		walls.add(new RectF(dpFromPx(329), dpFromPx(963), dpFromPx(685), dpFromPx(1014)));
		walls.add(new RectF(dpFromPx(189), dpFromPx(1109), dpFromPx(685), dpFromPx(1160)));
		walls.add(new RectF(dpFromPx(259), dpFromPx(250), dpFromPx(310), dpFromPx(559)));
		walls.add(new RectF(dpFromPx(405), dpFromPx(200), dpFromPx(456), dpFromPx(539)));
		walls.add(new RectF(dpFromPx(456), dpFromPx(450), dpFromPx(580), dpFromPx(501)));
		walls.add(new RectF(dpFromPx(529), dpFromPx(501), dpFromPx(580), dpFromPx(801)));
		walls.add(new RectF(dpFromPx(405), dpFromPx(700), dpFromPx(456), dpFromPx(963)));
		return walls;
		// @formatter:on
	}

	// TODO: Implement in StageEnum
	public float dpFromPx(float px) {
		Resources resources = this.context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float dp = px / (metrics.densityDpi / 320f);
		return dp;
	}
}
