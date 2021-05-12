package Sprites;

import processing.core.PApplet;

public class Rifle extends Weapon{
	
	private static final String FILE_IMAGE_NAME = "Rifle.png";
	public static final int INIT_DURATION = 40;
	public static final int INIT_DAMAGE = 20;
	public static final int INIT_SPEED = 8;
	public static final int INIT_MAX_DELAY = 10;
	
	public Rifle(int x, int y, int width, int height, PApplet drawer) {
		super(x, y, width, height, INIT_MAX_DELAY, FILE_IMAGE_NAME, new Bullet(drawer, INIT_DURATION,
				INIT_DAMAGE, INIT_SPEED), drawer);
		
	}

}
