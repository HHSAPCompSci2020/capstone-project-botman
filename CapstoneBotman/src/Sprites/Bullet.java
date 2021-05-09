package Sprites;

import processing.core.PApplet;
import processing.core.PImage;
/**@author kennywu**/
public class Bullet extends Sprite{

	public static final String FILE_IMAGE_NAME = "Bullet.png";
	public static final int INIT_DURATION = 60;
	public static final int INIT_DAMAGE = 20;
	public static final int INIT_SPEED = 10;
	public static final int WIDTH = 10;
	public static final int HEIGHT = 10;
	private int duration;
	private int currDuration; 
	private int damage; 
	private int speed;
	private double angle;
	// update so it doesnt have to load image every time
	
	public Bullet(int x, int y, int width, int height, PApplet drawer, int damage, int duration) {
		super(x, y, width, height, FILE_IMAGE_NAME, drawer);
		this.damage = damage;
		this.duration = duration;
		this.currDuration = duration;
		angle = 0;
	}
	
	public Bullet(int x, int y, int width, int height, PImage image , int damage, int duration) {
		super(x, y, width, height, image);
		this.damage = damage;
		this.duration = duration;
		this.currDuration = duration;
		angle = 0;
	}
	
	public Bullet(int x, int y, int width, int height, PApplet drawer) {
		super(x, y, width, height, FILE_IMAGE_NAME, drawer);
		this.duration = INIT_DURATION;
		this.currDuration = INIT_DURATION;
		this.damage = INIT_DAMAGE;
		this.speed = INIT_SPEED;
		angle = 0;
	}
	
	public Bullet(PApplet drawer) {
		super(WIDTH, HEIGHT, FILE_IMAGE_NAME, drawer);
		this.duration = INIT_DURATION;
		this.currDuration = INIT_DURATION;
		this.damage = INIT_DAMAGE;
		this.speed = INIT_SPEED;
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
		x += speed*Math.cos(angle);
		y += speed*Math.sin(angle);
	}
	
	
	public Bullet createBullet(double angle, int x, int y) {
		this.angle = angle;
		return new Bullet(x,y, width, height, image, damage, duration);
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
