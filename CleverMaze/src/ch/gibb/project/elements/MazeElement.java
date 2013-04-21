package ch.gibb.project.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import ch.gibb.project.controller.Stages;

public class MazeElement extends View {
	protected Stages stageManager;
	protected Bitmap backgroundImage;

	public MazeElement(Context context, int width, int height) {
		super(context);
		stageManager = new Stages();
	}

	public int getImageWidth() {
		return backgroundImage.getWidth();
	}

	public int getImageHeight() {
		return backgroundImage.getHeight();
	}
}
