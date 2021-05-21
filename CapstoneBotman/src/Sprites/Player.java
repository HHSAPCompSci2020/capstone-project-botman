package Sprites;

import java.awt.Color;

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
	

	/**
	 * Initializes a new Player 
	 * @param x center x coordinate of player
	 * @param y center y coordinate of player
	 * @param width width of player
	 * @param height height of player
	 * @param fileName the file name for the image
	 * @param drawer PApplet drawer to load image
	 * @param health health of player
	 * @param cash cash of player
	 */
	public Player(int x, int y, int width, int height, String fileName, PApplet drawer, int health
			, int cash) {
		super(x, y, width, height, fileName, drawer);
		initWeapons(drawer);
		wins = 0;
		losses = 0;
		vX = 0;
		vY =0;
		angle = 0;
		
	}
	/**
	 * Initializes a new Player 
	 * @param x center x coordinate of player
	 * @param y center y coordinate of player
	 * @param width width of player
	 * @param height height of player
	 * @param fileName the file name for the image
	 * @param drawer PApplet drawer to load image
	 */
	public Player(int x, int y, int width, int height, String fileName, PApplet drawer) {
		super(x, y, width, height, fileName, drawer);
		initWeapons(drawer);
		cash = 0;
		health = 0;
		wins = 0;
		losses = 0;
		vX = 0;
		vY =0;
		angle = 0;
	}
	
	private void initWeapons(PApplet drawer) {
		weapons = new Weapon[3];
		weapons [0] = new Rifle(x, y, width, height/2, drawer);
		weapons [1] = new SniperRifle(x, y, width, height/2, drawer);
		weapons [2] = new Shotgun(x, y, width, height/2, drawer);
	}
	/**
	 * Sets the weapon the player has to no weapon
	 */
	public void setToNone() {
		weapon = null; 
	}
	
	/**
	 * Switches the weapon player has to the Rifle object
	 */
	public void setToRifle() {
		weapon = weapons[0];
	}
	/**
	 * Switches the weapon player has to the SniperRifle object
	 */
	public void setToSniper() {
		weapon = weapons[1];
	}
	/**
	 * Switches the weapon player has to the Shotgun object
	 */
	public void setToShotgun() {
		weapon = weapons[2];
	}
	@Override
	public void draw(PApplet drawer) {
		super.draw(drawer);
		drawer.pushMatrix();
		drawer.strokeWeight(0);
		drawer.fill(Color.GREEN.getRGB());
		drawer.rect((x-(float)width/2), ((y-(float)height/2)-5), 
				(float)(health)/maxHealth*width, (float)(height)/10);
		drawer.fill(Color.RED.getRGB());
		drawer.rect((x-(float)width/2) + (float)(health)/maxHealth*width, ((y-(float)height/2)-5), 
				(float)(maxHealth-health)/maxHealth*width, (float)(height)/10);
		drawer.popMatrix();
		if(weapon != null) {
			weapon.draw(drawer, angle);
		}
	}
	/**
	 * Fire or creates a new bullet
	 * @return new Bullet object, null if player has no weapon
	 */
	public Bullet fire() {
		if(weapon == null) {
			return null;
		}
		return weapon.fire();
	}

	@Override
	public void translate(int x, int y) {
		super.translate(x, y);
		if(weapon != null) {
			weapon.translate(x, y);
		}
	}
	/**
	 * translate the player according to its velocity
	 */
	public void move() {
		super.translate(vX, vY);
		if(weapon != null) {
			weapon.translate(vX, vY);
		}
	}
	
	@Override 
	public void setY(int y) {
		super.setY(y);
		if(weapon != null) {
			weapon.setY(y);
		}
	}
	
	@Override 
	public void setX(int x) {
		super.setX(x);
		if(weapon != null) {
			weapon.setX(x);
		}
	}
	
	/**
	 * Increments or decrements from health depending on
	 * parameter being positive or negative respectively
	 * @param healthVal increment or decrement value for health depending on positive or negative
	 * @return true if health sum does not go over max health, false otherwise
	 */
	public boolean changeHealth(int healthVal) {
		if(health+healthVal > maxHealth) {
			this.health = maxHealth;
			return false;
		}
		this.health += healthVal;
		return true;
	}
	/**
	 * Increments or decrements from cash depending on
	 * parameter being positive or negative respectively
	 * @param cashVal increment or decrement value for cash depending on positive or negative
	 */
	public void changeCash(int cashVal) {
		this.cash += cashVal; 
	}
	/**
	 * Returns the weapon object Player holds
	 * @return weapon 
	 */
	public Weapon getWeapon() {
		return weapon;
	}
	/**
	 * The x velocity of player
	 * @return x velocity
	 */
	public int getvX() {
		return vX;
	}
	/**
	 * The y velocity of player
	 * @return y velocity
	 */
	public int getvY() {
		return vY;
	}
	/**
	 * Sets a new x velocity of player
	 * @param vX new x velocity
	 */
	public void setvX(int vX) {
		this.vX = vX;
	}
	/**
	 * Sets a new y velocity of player
	 * @param vY new y velocity
	 */
	public void setvY(int vY) {
		this.vY = vY;
	}
	/**
	 * Return the angle of where player faces
	 * @return angle of player
	 */
	public double getAngle() {
		return angle;
	}
	/**
	 * Sets a new angle for player to face
	 * @param angle new angle for player
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	/**
	 * Increment the wins
	 */
	public void win() {
		wins++;
	}
	/**
	 * Increment the losses
	 */
	public void loss() {
		losses++;
	}
	
	/**
	 * Returns the amount of cash on player
	 * @return cash of player
	 */
	public int getCash() {
		return cash;
	}
	/**
	 * Returns the health of player
	 * @return health of player
	 */
	public int getHealth() {
		return health;
	}
	/**
	 * Returns number of wins
	 * @return number of wins
	 */
	public int getWins() {
		return wins;
	}
	/**
	 * Returns number of losses
	 * @return number of losses
	 */
	public int getLosses() {
		return losses;
	}
	/**
	 * Sets a new cash amount for player
	 * @param cash new cash amount
	 */
	public void setCash(int cash) {
		this.cash = cash;
	}
	/**
	 * Sets a new health for player
	 * @param health new health for player
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	/**
	 * Sets a new number of wins for player
	 * @param wins new number of wins
	 */
	public void setWins(int wins) {
		this.wins = wins;
	}
	/**
	 * Sets a new number of losses for player
	 * @param wins new number of losses
	 */
	public void setLosses(int losses) {
		this.losses = losses;
	}
	/**
	 * Returns the maximum amount of health the player has
	 * @return maximum amount of health the player has
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	/**
	 * Sets a new max health for the player
	 * @param maxHealth new maximum health of player
	 */
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	
	

	
	
}
