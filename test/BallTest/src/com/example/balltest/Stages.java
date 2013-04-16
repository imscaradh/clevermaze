package com.example.balltest;

import java.util.Vector;

import android.graphics.PointF;

public class Stages {

	public Stages() {
	}

	public Vector<PointF> firstHoles() {
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
		holes.add(new PointF(176f, 478f));
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

	public Vector<PointF> firstStars() {
		Vector<PointF> stars = new Vector<PointF>();
		stars.add(new PointF(40f, 40f));
		stars.add(new PointF(320f, 259f));
		stars.add(new PointF(320f, 405f));
		stars.add(new PointF(193f, 740f));
		stars.add(new PointF(622f, 186f));
		return stars;
	}
}
