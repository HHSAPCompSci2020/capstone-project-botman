package Sprites;

import processing.core.PApplet;

public class Bullet extends Sprite{

	public static final String FILE_IMAGE_NAME = "";
	private int duration;
	private int damage; 
	
	public Bullet(int x, int y, int width, int height, PApplet drawer) {
		super(x, y, width, height, FILE_IMAGE_NAME, drawer);
		// TODO Auto-generated constructor stub
	}
	
	public boolean checkDuration() {
		return false;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int getDuration() {
		return duration;
	}
	
}
