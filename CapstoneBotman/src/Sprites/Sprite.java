package Sprites;

import java.awt.Rectangle;

import processing.core.PApplet;
import processing.core.PImage;

/**@author kennywu**/
public abstract class Sprite {
	protected int x;
	protected int y;
	protected int width;
	protected int height; 
	protected PImage image;
	
	
	public Sprite(int x, int y, int width, int height, String fileName, PApplet drawer) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = drawer.loadImage("fileName");
	}
	
	public void draw(PApplet drawer) {
		drawer.image(image, x, y, width, height);
	}
	
	public Rectangle getHitBox() {
		return new Rectangle(x,y,width,height);
	}
	
	
	
	
}
