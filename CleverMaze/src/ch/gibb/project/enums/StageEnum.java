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
							new RectF(405, 700, 456, 963) }),
							
	STAGE_2(new PointF(40, 40), 
			new PointF(322, 1006), 
			new PointF[] {	
							new PointF(612, 40), 
							new PointF(466, 40),
							new PointF(393, 40),
							new PointF(527, 220),
							new PointF(527, 293),
							new PointF(527, 366),
							new PointF(527, 439),
							new PointF(216, 164),
							new PointF(216, 237),
							new PointF(159, 500),
							new PointF(216, 600),
							new PointF(40, 870),
							new PointF(40, 943),
							new PointF(40, 1016),
							new PointF(40, 1089),
							new PointF(40, 1162),
							new PointF(166, 800),
							new PointF(166, 870),
							new PointF(166, 943),
							new PointF(166, 1016),
							new PointF(400, 1079),
							new PointF(400, 1006),
							new PointF(400, 933),
							new PointF(327, 933),
							new PointF(400, 860),
							new PointF(400, 787),
							new PointF(523, 1079),
							new PointF(523, 1006),
							new PointF(523, 933),
							new PointF(523, 860),
							new PointF(523, 787),
							new PointF(523, 714),
							new PointF(523, 641)
			}, 
			new ArrayList<PointF>(Arrays.asList( 
						new PointF(539, 40),
						new PointF(159, 680),
						new PointF(226, 680))),
			new RectF[] {
							new RectF(40, 113, 340, 164), 
							new RectF(289, 164, 600, 215),
							new RectF(340, 565, 685, 616),
							new RectF(409, 335, 460, 565),
							new RectF(289, 215, 340, 405),
							new RectF(108, 245, 159, 800),
							new RectF(289, 565, 340, 800),
							new RectF(159, 749, 289, 800),
							new RectF(166, 1089, 400, 1140),
							}),
							
	STAGE_3(new PointF(40, 40), 
			new PointF(322, 1006), 
			new PointF[] {	
							new PointF(612, 40)
			}, 
			new ArrayList<PointF>(Arrays.asList( 
						new PointF(226, 680))),
			new RectF[] {
							new RectF(300, 100, 373, 1140)
							}),
							
	STAGE_4(new PointF(40, 40), 
			new PointF(322, 1006), 
			new PointF[] {	
							new PointF(612, 40)
			}, 
			new ArrayList<PointF>(Arrays.asList( 
						new PointF(226, 680))),
			new RectF[] {
							new RectF(300, 100, 373, 1140)
							}),
	
	STAGE_5(new PointF(40, 40), 
			new PointF(322, 1006), 
			new PointF[] {	
							new PointF(612, 40)
			}, 
			new ArrayList<PointF>(Arrays.asList( 
						new PointF(226, 680))),
			new RectF[] {
							new RectF(300, 100, 373, 1140)
							});
	
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

	// TODO: Implement this function, => Display Density relative
	// public float dpFromPx(float px) {
	// Resources resources = Level.getResources();
	// DisplayMetrics metrics = resources.getDisplayMetrics();
	// float dp = px / (metrics.densityDpi / 320f);
	// return dp;
	// }

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
