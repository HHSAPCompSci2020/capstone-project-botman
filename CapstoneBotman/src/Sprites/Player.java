package Sprites;

import processing.core.PApplet;
/**@author kennywu**/
public abstract class Player extends Sprite{

	private int cash;
	private int health;
	private int wins, losses; 
	private Weapon weapon; 
	
	public Player(int x, int y, int width, int height, String fileName, PApplet drawer, int health
			, int cash) {
		super(x, y, width, height, fileName, drawer);
		weapon = new Weapon(x, y, width/2, height/4, drawer);
		wins = 0;
		losses = 0;
		
	}
	
	public Player(int x, int y, int width, int height, String fileName, PApplet drawer) {
		super(x, y, width, height, fileName, drawer);
		weapon = new Weapon(x, y, width/2, height/4, drawer);
		int cash = 0;
		int health = 0;
		int wins = 0;
		int losses = 0;
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
	
	
	public Bullet fire(PApplet drawer) {
		return weapon.fire(drawer);
	}

	
	
}
