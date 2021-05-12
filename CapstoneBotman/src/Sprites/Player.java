package Sprites;

import processing.core.PApplet;
/**@author kennywu**/
public abstract class Player extends Sprite{

	private int cash;
	private int maxHealth;
	private int health;
	private int wins, losses; 
	private int vX, vY; 
	private double angle;
	private Weapon weapon; 
	private Weapon[] weapons; 
	


	public Player(int x, int y, int width, int height, String fileName, PApplet drawer, int health
			, int cash) {
		super(x, y, width, height, fileName, drawer);
		initWeapons(drawer);
		weapon = weapons[0];
		wins = 0;
		losses = 0;
		vX = 0;
		vY =0;
		angle = 0;
		
	}
	
	public Player(int x, int y, int width, int height, String fileName, PApplet drawer) {
		super(x, y, width, height, fileName, drawer);
		initWeapons(drawer);
		weapon = weapons[0];
		cash = 0;
		health = 0;
		wins = 0;
		losses = 0;
		vX = 0;
		vY =0;
		angle = 0;
	}
	
	private void initWeapons(PApplet drawer) {
		weapons [0] = new Rifle(x, y, width, height/2, drawer);
		weapons [1] = new SniperRifle(x, y, width, height/2, drawer);
		weapons [2] = new Shotgun(x, y, width, height/2, drawer);
	}
	
	public void setToRifle() {
		weapon = weapons[0];
	}
	
	public void setToSniper() {
		weapon = weapons[1];
	}
	
	public void setToShotgun() {
		weapon = weapons[2];
	}
	
	public void draw(PApplet drawer) {
		super.draw(drawer);
		weapon.draw(drawer, angle);
	}
	
	public Bullet fire() {
		return weapon.fire();
	}

	@Override
	public void translate(int x, int y) {
		super.translate(x, y);
		weapon.translate(x, y);
	}
	
	public void move() {
		super.translate(vX, vY);
		weapon.translate(vX, vY);
	}
	
	@Override 
	public void setY(int y) {
		super.setY(y);
		weapon.setY(y);
	}
	
	@Override 
	public void setX(int x) {
		super.setX(x);
		weapon.setX(x);
	}
	
	/**
	 * Increments or decrements from health depending on
	 * parameter being positive or negative respectively
	 * @param healthVal increment or decrement value for health depending on positive or negative
	 */
	public void changeHealth(int healthVal) {
		this.health += healthVal; 
	}
	/**
	 * Increments or decrements from cash depending on
	 * parameter being positive or negative respectively
	 * @param cashVal increment or decrement value for cash depending on positive or negative
	 */
	public void changeCash(int cashVal) {
		this.cash += cashVal; 
	}
	
	public Weapon getWeapon() {
		return weapon;
	}
	
	public int getvX() {
		return vX;
	}

	public int getvY() {
		return vY;
	}

	public void setvX(int vX) {
		this.vX = vX;
	}

	public void setvY(int vY) {
		this.vY = vY;
	}
	
	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public void win() {
		wins++;
	}
	
	public void loss() {
		losses++;
	}
	

	public int getCash() {
		return cash;
	}

	public int getHealth() {
		return health;
	}

	public int getWins() {
		return wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	
	

	
	
}
