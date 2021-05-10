package Sprites;

import processing.core.PApplet;
/**@author kennywu**/
public abstract class Player extends Sprite{

	private int cash;
	private int health;
	private int wins, losses; 
	private int vX, vY; 
	private Weapon weapon; 
	
	public Player(int x, int y, int width, int height, String fileName, PApplet drawer, int health
			, int cash) {
		super(x, y, width, height, fileName, drawer);
		weapon = new Weapon(x, y, width/2, height/4, drawer);
		wins = 0;
		losses = 0;
		vX = 0;
		vY =0;
		
	}
	
	public Player(int x, int y, int width, int height, String fileName, PApplet drawer) {
		super(x, y, width, height, fileName, drawer);
		weapon = new Weapon(x, y, width/2, height/4, drawer);
		cash = 0;
		health = 0;
		wins = 0;
		losses = 0;
		vX = 0;
		vY =0;
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
	
	public void win() {
		wins++;
	}
	
	public void loss() {
		losses++;
	}
	
	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
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

	public void draw(PApplet drawer, double angle) {
		super.draw(drawer);
		weapon.draw(drawer, angle);
	}
	
	public Bullet fire() {
		return weapon.fire();
	}
	
	

	
	
}
