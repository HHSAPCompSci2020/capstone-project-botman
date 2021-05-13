package Sprites;

import processing.core.PApplet;
/**
 * 
 * @author kennywu
 *
 */
public class Rifle extends Weapon{
	
	private static final String FILE_IMAGE_NAME = "Rifle.png";
	private static final int INIT_DURATION = 40;
	private static final int INIT_DAMAGE = 20;
	private static final int INIT_SPEED = 8;
	private static final int INIT_MAX_DELAY = 10;
	
	/**
	 * Initializes Rifle with x,y coordinates, width and height, image, and weapons stats
	 * @param x center x coordinate
	 * @param y center y coordinate
	 * @param width width of sprite
	 * @param height height of sprite
	 * @param drawer PApplet drawer to load image
	 */
	public Rifle(int x, int y, int width, int height, PApplet drawer) {
		super(x, y, width, height, INIT_MAX_DELAY, FILE_IMAGE_NAME, new Bullet(drawer, INIT_DURATION,
				INIT_DAMAGE, INIT_SPEED), drawer);
		
	}

}
