package Sprites;

import processing.core.PApplet;

public class Weapon extends Sprite{

	public static final String FILE_IMAGE_NAME = "";
	private Bullet bullet; 
	
	public Weapon(int x, int y, int width, int height, PApplet drawer) {
		super(x, y, width, height, FILE_IMAGE_NAME, drawer);
		// TODO Auto-generated constructor stub
	}
	
	public Bullet getBullet() {
		return bullet; 
	}
	
	public Boolean shootBullet() {
		return true;
	}
}
