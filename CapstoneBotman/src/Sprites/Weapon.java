package Sprites;

import processing.core.PApplet;
/**@author kennywu**/
public class Weapon extends Sprite{
	
	private int maxDelayTime; 
	private Bullet bullet; 
	private double angle;
	private int delay;
	
	/**
	 * Creates and initializes the weapon with a image. 
	 * Creates a bullet object for image storage and weapon stats purposes 
	 * @param x center x coordinate of weapon 
	 * @param y center y coordinate of weapon
	 * @param width width of weapon
	 * @param height height of weapon
	 * @param drawer PApplet drawer to initialize weapon image
	 */
	public Weapon(int x, int y, int width, int height, int maxDelay, String fileName, Bullet bullet, PApplet drawer) {
		super(x, y, width, height, fileName, drawer);
		maxDelayTime = maxDelay; 
		this.bullet = bullet;
		angle = 0;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Draws the weapon and updates some additional info for weapon, 
	 * such as the firing delay and the new weapon angle
	 * @param drawer PApplet drawer
	 * @param angle the angle of the weapon in radians 
	 */
	public void draw(PApplet drawer, double angle) {
		delay--;
		this.angle = angle;
		drawer.pushMatrix();
		drawer.translate(x,y);
		drawer.rotate((float) angle);
		drawer.image(image, 0, 0, width, height);
		drawer.popMatrix();
		
	}
	
	/**
	 * Initializes and creates a new bullet with stats according to the bullet field 
	 * If the time after the last bullet has been created is less than the allocated delay time then a bullet
	 * will not be created. 
	 * @return new Bullet object if delay <= 0, else return null 
	 */
	public Bullet fire() {
		if(delay <= 0) {
			resetDelay();
			return bullet.createBullet(angle, (int)(x+width*Math.cos(angle)), (int)(y+width*Math.sin(angle)));
		}
		return null;
	}
	/**
	 * After bullets are fired call method to set the delay between next shot 
	 */
	public void resetDelay() {
		delay = maxDelayTime;
	}
	
	/**
	 * returns the bullet object in case stats want to be changed
	 * @return the bullet object held in field 
	 */
	public Bullet getBullet() {
		return bullet;
	}
	/**
	 * returns the angle of weapon
	 * @return Angle of the weapon
	 */
	public double getAngle() {
		return angle;
	}
	
	/**
	 * Returns the current delay between each time a new bullet is created 
	 * @return current delay between each time a new bullet is created 
	 */
	public int getCurrDelay() {
		return delay;
	}
	/**
	 * Sets new delay between each time a new bullet is created
	 * @param delay the new delay time 
	 */
	public void setDelay(int delay) {
		this.maxDelayTime = delay;
	}
	
	
}
