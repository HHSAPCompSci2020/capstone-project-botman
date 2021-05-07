package Sprites;

import processing.core.PApplet;

public abstract class Player extends Sprite{
	
	private int maxHealth; 
	private int cash;
	private int health;
	private int vX, vY;
	private int wins, losses; 
	private Weapon weapon; 
	
	public Player(int x, int y, int width, int height, String fileName, PApplet drawer) {
		super(x, y, width, height, fileName, drawer);
		// TODO Auto-generated constructor stub
	}
	
	public Weapon getWeapon() {
		return weapon; 
	}
	
	public Bullet getBullet() {
		return weapon.getBullet();
	}

	
	
}
