package Screens;

public abstract class Screen {

	/**
	 * width of the screen
	 */
	public final int WIDTH;
	
	/**
	 * height of the screen
	 */
	public final int HEIGHT;
	
	/**
	 * Constructs a screen with the set width and height
	 * 
	 * @param width Width of the screen
	 * @param height Height of the screen
	 */
	public Screen(int width, int height) {
		this.WIDTH = width;
		this.HEIGHT = height;
	}
	
	/**
	 * Sets up the titles and buttons of the screen
	 */
	public void setup() {
		
	}
	
	/**
	 * Draws components of the screen, including the titles, images, and buttons
	 */
	public void draw() {
		
	}
	
	/**
	 * Performs an action when the mouse is pressed on the screen
	 */
	public void mousePressed() {
		
	}
	
	/**
	 * Performs an action when the mouse is moved on the screen
	 */
	public void mouseMoved() {
		
	}
	
	/**
	 * Performs an action when the mouse is dragged on the screen
	 */
	public void mouseDragged() {
		
	}
	
	/**
	 * Performs an action when the mouse is released on the screen
	 */
	public void mouseReleased() {
		
	}
	
}
