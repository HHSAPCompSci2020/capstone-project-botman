package Sprites;

import processing.core.PApplet;
import processing.core.PImage;
/**@author kennywu**/
public class Bullet extends Sprite{

	public static final String FILE_IMAGE_NAME = "Bullet.png";
	public static final int WIDTH = 10;
	public static final int HEIGHT = 10;
	private int duration;
	private int currDuration; 
	private int damage; 
	private int speed;
	private double angle;
	// update so it doesnt have to load image every time
	
	
	public Bullet(int x, int y, int width, int height, PImage image , int damage, 
			int duration, double angle, int speed) {
		super(x, y, width, height, image);
		this.damage = damage;
		this.duration = duration;
		this.currDuration = duration;
		this.speed = speed;
		this.angle = angle;
	}
	
	
	public Bullet(PApplet drawer, int duration, int damage, int speed) {
		super(WIDTH, HEIGHT, FILE_IMAGE_NAME, drawer);
		this.duration = duration;
		this.currDuration = duration;
		this.damage = damage;
		this.speed = speed;
		angle = 0;
	}
	
	public boolean checkDuration() {
		if(currDuration > 0) {
			currDuration--;
			return true;
		}
		return false;
	}
	
	public void move() {
		translate((int) (speed*Math.cos(angle)), (int) (speed*Math.sin(angle)));
	}
	
	
	public Bullet createBullet(double angle, int x, int y) {
		return new Bullet(x,y, width, height, image, damage, duration, angle, speed);
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed() {
		return speed;
	}


	public int getDamage() {
		return damage;
	}
	
	public int getDuration() {
		return duration;
	}
	
}
