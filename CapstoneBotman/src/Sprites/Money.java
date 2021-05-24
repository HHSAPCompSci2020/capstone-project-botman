package Sprites;

import processing.core.PApplet;
/**
 * 
 * @author kennywu
 *
 */
public class Money extends Sprite{
	
	
	private static final String FILE_IMAGE_NAME = "Money.png";
	private int money;
	
	/**
	 * Initializes Money with x,y coordinates, width and height, and image
	 * @param x center x coordinate
	 * @param y center y coordinate
	 * @param width width of sprite
	 * @param height height of sprite
	 * @param drawer PApplet drawer to load image
	 * @param money cash the money object provides when picked up
	 */
	public Money(int x, int y, int width, int height, PApplet drawer, int money) {
		super(x, y, width, height, FILE_IMAGE_NAME, drawer);
		this.money = money;
	}
	/**
	 * Returns the money the object provides to players
	 * @return money the object provides to players
	 */
	public int getMoney() {
		return money;
	}
	/**
	 * Sets a new value for the amount of cash the Money object provides
	 * @param money the new cash amount the money object provides when picked up
	 */
	public void setMoney(int money) {
		this.money = money;
	}
}
