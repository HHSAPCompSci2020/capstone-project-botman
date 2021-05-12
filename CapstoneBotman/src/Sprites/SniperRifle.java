package Sprites;

import processing.core.PApplet;

public class SniperRifle extends Weapon{

	private static final String FILE_IMAGE_NAME = "Sniper.png";
	public static final int INIT_DURATION = 70;
	public static final int INIT_DAMAGE = 200;
	public static final int INIT_SPEED = 12;
	public static final int INIT_MAX_DELAY = 50;
	
	public SniperRifle(int x, int y, int width, int height, PApplet drawer) {
		super(x, y, width, height, INIT_MAX_DELAY, FILE_IMAGE_NAME, new Bullet(drawer, INIT_DURATION,
				INIT_DAMAGE, INIT_SPEED), drawer);
	}
	
}