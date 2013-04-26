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
	private int height;
	private int width;

	public MazeElement(Context context, int width, int height) {
		super(context);
		stageManager = new Stages(context);
		this.width = width;
		this.height = height;

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
