package ch.gibb.project.enums;

import java.util.ArrayList;
import java.util.Arrays;

import android.graphics.PointF;
import android.graphics.RectF;

public enum StageEnum {

	// @formatter:off
	STAGE_1(new PointF(600, 1180), 
			new PointF(600, 1023), 
			new PointF[] {	new PointF(612, 40), 
							new PointF(539, 40), 
							new PointF(466, 40),
							new PointF(393, 40), 
							new PointF(320, 40), 
							new PointF(247, 40),
							new PointF(612, 113), 
							new PointF(559, 186), 
							new PointF(559, 259),
							new PointF(320, 332), 
							new PointF(103, 332), 
							new PointF(186, 478),
							new PointF(395, 551), 
							new PointF(40, 1172), 
							new PointF(40, 1099),
							new PointF(40, 1026), 
							new PointF(40, 953), 
							new PointF(176, 1026),
							new PointF(176, 953), 
							new PointF(612, 880), 
							new PointF(176, 880),
							new PointF(176, 807), 
							new PointF(120, 750) }, 
			new ArrayList<PointF>(Arrays.asList( 
							new PointF(40, 40), 
							new PointF(320, 259), 
							new PointF(320, 405),
							new PointF(193, 740), 
							new PointF(622, 186))), 
			new RectF[] {
							new RectF(113, 40, 164, 320), 
							new RectF(119, 480, 170, 740),
							new RectF(329, 963, 685, 1014), 
							new RectF(189, 1109, 685, 1160),
							new RectF(259, 250, 310, 559), 
							new RectF(405, 200, 456, 539),
							new RectF(456, 450, 580, 501), 
							new RectF(529, 501, 580, 801),
							new RectF(405, 700, 456, 963) });

	// @formatter:on

	private PointF startPoint;
	private PointF finishPoint;
	private PointF[] holes;
	private ArrayList<PointF> stars;
	private RectF[] walls;

	private StageEnum(PointF startPoint, PointF finishPoint, PointF[] holes,
			ArrayList<PointF> stars, RectF[] walls) {
		this.startPoint = startPoint;
		this.finishPoint = finishPoint;
		this.holes = holes;
		this.stars = stars;
		this.walls = walls;
	}

	public PointF getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(PointF startPoint) {
		this.startPoint = startPoint;
	}

	public PointF getFinishPoint() {
		return finishPoint;
	}

	public void setFinishPoint(PointF finishPoint) {
		this.finishPoint = finishPoint;
	}

	public PointF[] getHoles() {
		return holes;
	}

	public void setHoles(PointF[] holes) {
		this.holes = holes;
	}

	public ArrayList<PointF> getStars() {
		return stars;
	}

	public void setStars(ArrayList<PointF> stars) {
		this.stars = stars;
	}

	public RectF[] getWalls() {
		return walls;
	}

	public void setWalls(RectF[] walls) {
		this.walls = walls;
	}
}
