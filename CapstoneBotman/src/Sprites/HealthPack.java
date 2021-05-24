package Sprites;

import processing.core.PApplet;
/**
 * 
 * @author kennywu
 *
 */
public class HealthPack extends Sprite{
	private static final String FILE_IMAGE_NAME = "HealthPack.png";
	private int health;
	
	/**
	 * Initializes Health with x,y coordinates, width and height, and image
	 * @param x center x coordinate
	 * @param y center y coordinate
	 * @param width width of sprite
	 * @param height height of sprite
	 * @param drawer PApplet drawer to load image
	 * @param health the health the object provides when picked up
	 */
	public HealthPack(int x, int y, int width, int height, PApplet drawer, int health) {
		super(x, y, width, height, FILE_IMAGE_NAME, drawer);
		this.health = health;
	}
	/**
	 * Returns the health the object provides to players
	 * @return health the object provides to players
	 */
	public int getHealth() {
		return health;
	}
	/**
	 * Sets a new value for the amount of health the Money object provides
	 * @param health the new health amount the HealthPaco object provides when picked up
	 */
	public void setHealth(int health) {
		this.health = health;
	}
}
