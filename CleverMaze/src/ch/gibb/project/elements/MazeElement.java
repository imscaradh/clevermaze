package ch.gibb.project.elements;

import ch.gibb.project.util.Dimension;
import android.graphics.Point;

public class MazeElement {
	private Point position;
	private Dimension dimension;

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

}
