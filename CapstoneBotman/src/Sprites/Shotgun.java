package Sprites;

import processing.core.PApplet;

public class Shotgun extends Weapon{
	
	private static final String FILE_IMAGE_NAME = "Shotgun.png";
	public static final int INIT_DURATION = 70;
	public static final int INIT_DAMAGE = 200;
	public static final int INIT_SPEED = 12;
	public static final int INIT_MAX_DELAY = 50;
	public static final double BULLET_SPREAD = Math.PI/6;
	private int bullets;
	
	public Shotgun(int x, int y, int width, int height, PApplet drawer) {
		super(x, y, width, height, INIT_MAX_DELAY, FILE_IMAGE_NAME, new Bullet(drawer, INIT_DURATION,
				INIT_DAMAGE, INIT_SPEED), drawer);
		bullets = 0;
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
				bullets = 0;
				resetDelay();
				return getBullet().createBullet(getAngle()-BULLET_SPREAD,
						(int)(x+width*Math.cos(getAngle())), (int)(y+width*Math.sin(getAngle())));
				
			}
			
		}
		return null;
		
	}
	
	
	
	
}
