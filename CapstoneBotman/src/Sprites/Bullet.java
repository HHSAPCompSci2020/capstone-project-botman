package Sprites;

import processing.core.PApplet;
/**@author kennywu**/
public class Bullet extends Sprite{

	public static final String FILE_IMAGE_NAME = "Bullet.png";
	private static final int INIT_DURATION = 60;
	private static final int INIT_DAMAGE = 20;
	private static final int INIT_SPEED = 10;
	private static final int WIDTH = 10;
	private static final int HEIGHT = 10;
	private int duration;
	private int currDuration; 
	private int damage; 
	private int speed;
	private double angle;
	
	
	public Bullet(int x, int y, int width, int height, PApplet drawer, int damage, int duration) {
		super(x, y, width, height, FILE_IMAGE_NAME, drawer);
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
	
	public Bullet() {
		super(WIDTH, HEIGHT);
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
	
	
	public Bullet createBullet(double angle, int x, int y, PApplet drawer) {
		this.angle = angle;
		return new Bullet(x,y, width, height, drawer, damage, duration);
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int getDuration() {
		return duration;
	}
	
}
