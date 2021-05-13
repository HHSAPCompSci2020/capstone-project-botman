package Sprites;

import processing.core.PApplet;
import processing.core.PImage;
/**@author kennywu**/
public class Bullet extends Sprite{

	
	private static final String FILE_IMAGE_NAME = "Bullet.png";
	private static final int WIDTH = 10;
	private static final int HEIGHT = 10;
	private int duration;
	private int currDuration; 
	private int damage; 
	private int speed;
	private double angle;
	// update so it doesnt have to load image every time
	
	/**
	 * Creates a new bullet
	 * @param x center x coordinate
	 * @param y center y coordinate
	 * @param width width of bullet
	 * @param height height of bullet
	 * @param image Pimage image of bullet
	 * @param damage damage of bullet
	 * @param duration duration of bullet 
	 * @param angle angle of bullet shot
	 * @param speed speed of bullet shot
	 */
	public Bullet(int x, int y, int width, int height, PImage image , int damage, 
			int duration, double angle, int speed) {
		super(x, y, width, height, image);
		this.damage = damage;
		this.duration = duration;
		this.currDuration = duration;
		this.speed = speed;
		this.angle = angle;
	}
	
	/**
	 * Creates bullet for storage purposes
	 * @param drawer PApplet drawer to store image
	 * @param duration duration of bullet
	 * @param damage damage of bullet
	 * @param speed speed of bullet shot
	 */
	public Bullet(PApplet drawer, int duration, int damage, int speed) {
		super(WIDTH, HEIGHT, FILE_IMAGE_NAME, drawer);
		this.duration = duration;
		this.currDuration = duration;
		this.damage = damage;
		this.speed = speed;
		angle = 0;
	}
	
	/**
	 * Checks if bullet duration is complete
	 * @return true if the current duration is less than 0, otherwise false
	 */
	public boolean checkDuration() {
		if(currDuration > 0) {
			currDuration--;
			return true;
		}
		return false;
	}
	
	/**
	 * Moves the bullet
	 */
	public void move() {
		translate((int) (speed*Math.cos(angle)), (int) (speed*Math.sin(angle)));
	}
	
	/**
	 * Creates a bullet in order to represent firing of weapon
	 * @param angle angle bullet is shot at
	 * @param x x coordinate to be spawned at
	 * @param y y coordinate to be spawned at
	 * @return new Bullet object
	 */
	public Bullet createBullet(double angle, int x, int y) {
		return new Bullet(x,y, width, height, image, damage, duration, angle, speed);
	}
	
	/**
	 * Sets a new damage
	 * @param damage new damage of bullet
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	/**
	 * return a damage
	 * @return damage
	 */
	public int getDamage() {
		return damage;
	}
	
	/**
	 * Sets a new duration of bullet
	 * @param duration new duration of bullet
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	/**
	 * Sets new speed of bullet
	 * @param speed new speed of bullet
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/**
	 * Return the speed of bullet
	 * @return speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Returns the duration of bullet
	 * @return duration
	 */
	public int getDuration() {
		return duration;
	}
	
}
