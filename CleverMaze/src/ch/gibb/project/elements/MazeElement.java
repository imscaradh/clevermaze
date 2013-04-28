package ch.gibb.project.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View;
import ch.gibb.project.controller.Stages;
import ch.gibb.project.util.Dimension;

public class MazeElement extends View {
	protected Canvas bitmapCanvas;
	protected Stages stageManager;
	// TODO: Make with getter/setter
	public Bitmap bitmap;
	private Point position;
	private Dimension dimension;

	public MazeElement(Context context) {
		super(context);
		stageManager = new Stages(context);

	}

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
