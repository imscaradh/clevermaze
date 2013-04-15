package com.example.balltest;

import java.util.Vector;

import android.graphics.PointF;

public class Stages {

	public Stages() {
	}

	public Vector<PointF> firstHoles() {
		Vector<PointF> holes = new Vector<PointF>();
		holes.add(new PointF(0f, 0f));
		holes.add(new PointF(500f, 500f));

		return holes;

	}
}
