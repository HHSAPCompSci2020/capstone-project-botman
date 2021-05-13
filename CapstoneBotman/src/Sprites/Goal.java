package Sprites;

import processing.core.PApplet;
/**@author kennywu**/
public class Goal extends Sprite{
	
	private static final String FILE_IMAGE_NAME = "Goal.png";
	
	/**
	 * Initializes Goal with x,y coordinates, width and height, and image
	 * @param x center x coordinate
	 * @param y center y coordinate
	 * @param width width of sprite
	 * @param height height of sprite
	 * @param drawer PApplet drawer to load image
	 */
	public Goal(int x, int y, int width, int height, PApplet drawer) {
		super(x, y, width, height, FILE_IMAGE_NAME, drawer);
		// TODO Auto-generated constructor stub
	}

	
	
}
