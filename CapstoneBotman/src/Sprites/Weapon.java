package Sprites;

import processing.core.PApplet;
/**@author kennywu**/
public class Weapon extends Sprite{

	public static final String FILE_IMAGE_NAME = "Weapon.png";
	private final int FIRING_DELAY; 
	private Bullet bullet; 
	private double angle;
	private int delay;
	
	public Weapon(int x, int y, int width, int height, PApplet drawer) {
		super(x, y, width, height, FILE_IMAGE_NAME, drawer);
		FIRING_DELAY = 30; 
		bullet = new Bullet(drawer);
		angle = 0;
		// TODO Auto-generated constructor stub
	}
	
	public void draw(PApplet drawer, double angle) {
		delay--;
		this.angle = angle;
		drawer.pushMatrix();
		drawer.translate(x,y);
		drawer.rotate((float) angle);
		drawer.image(image, 0, 0, width, height);
		drawer.popMatrix();
		
	}
	
	public Bullet fire() {
		if(delay <= 0) {
			delay = FIRING_DELAY;
			return bullet.createBullet(angle, (int)(x+width*Math.cos(angle)), (int)(y+width*Math.sin(angle)));
		}
		return null;
	}

	public Bullet getBullet() {
		return bullet;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	
}
