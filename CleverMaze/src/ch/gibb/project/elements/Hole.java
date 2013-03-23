package ch.gibb.project.elements;

import ch.gibb.project.enums.ArtEnum;

public class Hole extends MazeElement {
	private int radius;
	private ArtEnum art;

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public ArtEnum getArt() {
		return art;
	}

	public void setArt(ArtEnum art) {
		this.art = art;
	}
}
