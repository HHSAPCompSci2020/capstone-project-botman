package Sprites;

import processing.core.PApplet;
/**@author kennywu**/
public class Weapon extends Sprite{

	public static final String FILE_IMAGE_NAME = "Weapon.png";
	private Bullet bullet; 
	private double angle;
	
	public Weapon(int x, int y, int width, int height, PApplet drawer) {
		super(x, y, width, height, FILE_IMAGE_NAME, drawer);
		bullet = new Bullet();
		angle = 0;
		// TODO Auto-generated constructor stub
	}
	
	public void draw(PApplet drawer, double angle) {
		drawer.rotate((float) angle);
		this.angle = angle;
		drawer.image(image, x, y, width, height);
		drawer.rotate(0);
		
	}
	
	public Bullet fire(PApplet drawer) {
		return bullet.createBullet(angle, (int)(x+width*Math.cos(angle)), (int)(y+width*Math.sin(angle)), drawer);
	}
}
