package Sprites;

import java.awt.Rectangle;

import processing.core.PApplet;
import processing.core.PImage;

/**@author kennywu**/
public abstract class Sprite {
	
	//x, y about the center
	protected int x;
	protected int y;
	protected int width;
	protected int height; 
	protected PImage image;
	
	
	public Sprite(int x, int y, int width, int height, String fileName, PApplet drawer) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = drawer.loadImage(fileName);
	}
	
	public Sprite(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public Sprite(int width, int height, String fileName, PApplet drawer) {
		this.width = width;
		this.height = height;
		this.image = drawer.loadImage(fileName);
	}
	
	public Sprite(int x, int y, int width, int height, PImage image) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = image;
	}
	
	
	public void setImage(String fileName, PApplet drawer) {
		this.image = drawer.loadImage(fileName);
	}
	
	public void setImage(PImage image) {
		this.image = image;
	}
	
	/**
	 * Draws sprite according to x, y, width, and height about the center 
	 * @param drawer PApplet drawer 
	 * @post imageMode of PApplet drawer will be set to drawer.CENTER
	 */
	public void draw(PApplet drawer) {
		drawer.imageMode(drawer.CENTER);
		drawer.image(image, x, y, width, height);
	}
	/**
	 * Returns hitbox surrounding sprite
	 * @return new Rectangle surrounding sprite 
	 */
	public Rectangle getHitBox() {
		return new Rectangle(x-width/2,y-height/2 ,width,height);
	}
	
	/**
	 * Returns center x coordinate
	 * @return x coordinate (centered) 
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Returns center y coordinate
	 * @return y coordinate (centered) 
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Sets center x coordinate
	 * @param x the new center x coordinate 
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Sets center y coordinate
	 * @param y the new center x coordinate 
	 */
	public void setY(int y) {
		this.y = y; 
	} 
	/**
	 * Translate x,y coordinates across specified distance
	 * @param x translation value in x direction
	 * @param y translation value in x direction
	 */
	public void translate(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width; 
	}
	
	
	
}
