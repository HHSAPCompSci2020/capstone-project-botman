package Sprites;

import processing.core.PApplet;
/**
 * 
 * @author kennywu
 *
 */
public class Shotgun extends Weapon{
	
	private static final String FILE_IMAGE_NAME = "Shotgun.png";
	private static final int INIT_DURATION = 20;
	private static final int INIT_DAMAGE = 60;
	private static final int INIT_SPEED = 7;
	private static final int INIT_MAX_DELAY = 25;
	private static final double BULLET_SPREAD = Math.PI/6;
	private int bullets;
	
	/**
	 * Initializes Shotgun with x,y coordinates, width and height, image, and weapons stats
	 * @param x center x coordinate
	 * @param y center y coordinate
	 * @param width width of sprite
	 * @param height height of sprite
	 * @param drawer PApplet drawer to load image
	 */
	public Shotgun(int x, int y, int width, int height, PApplet drawer) {
		super(x, y, width, height, INIT_MAX_DELAY, FILE_IMAGE_NAME, new Bullet(drawer, INIT_DURATION,
				INIT_DAMAGE, INIT_SPEED), drawer);
		bullets = 1;
	}
	
	@Override
	public Bullet fire() {
		if(getCurrDelay() <= 0) {
			switch(bullets) {
			case 1:
				bullets++;
				return getBullet().createBullet(getAngle()+BULLET_SPREAD,
						(int)(x+width*Math.cos(getAngle())), (int)(y+width*Math.sin(getAngle())));
			case 2:
				bullets++;
				return getBullet().createBullet(getAngle(),
						(int)(x+width*Math.cos(getAngle())), (int)(y+width*Math.sin(getAngle())));
			case 3:
				bullets = 1;
				resetDelay();
				return getBullet().createBullet(getAngle()-BULLET_SPREAD,
						(int)(x+width*Math.cos(getAngle())), (int)(y+width*Math.sin(getAngle())));
				
			}
			
		}
		return null;
		
	}
	
	
	
	
}
