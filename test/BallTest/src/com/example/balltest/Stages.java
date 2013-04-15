package com.example.balltest;

import java.util.Vector;

import android.graphics.PointF;

public class Stages {

	public Stages() {
	}

	public Vector<PointF> firstHoles() {
		Vector<PointF> holes = new Vector<PointF>();
		// TODO: Anpassen auf Displaygrsse (incl. Playground etc.)
		holes.add(new PointF(40f, 40f));
		holes.add(new PointF(40f, 110f));
		holes.add(new PointF(40f, 180f));
		holes.add(new PointF(40f, 250f));
		holes.add(new PointF(500f, 500f));

		return holes;

	}
}
