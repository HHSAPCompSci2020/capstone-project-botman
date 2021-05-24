package Sprites;

import processing.core.PApplet;
/**@author kennywu**/
public class Runner extends Player{
	
	private static final String FILE_IMAGE_NAME = "Runner.png";

	/**
	 * Initializes Runner with x,y coordinates, width and height, and image
	 * @param x center x coordinate
	 * @param y center y coordinate
	 * @param width width of sprite
	 * @param height height of sprite
	 * @param drawer PApplet drawer to load image
	 */
	public Runner(int x, int y, int width, int height, PApplet drawer) {
		super(x, y, width, height, FILE_IMAGE_NAME, drawer);
		// TODO Auto-generated constructor stub
	}
	
}
