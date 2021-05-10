package Main;


import java.awt.Point;
import java.util.ArrayList;

import Screens.*;
import processing.core.PApplet;

/**
 * 
 * The DrawingSurface class is a PApplet with the ability to switch between multiple Screens.
 * 
 * @author Kyle Fu
 *
 */
public class DrawingSurface extends PApplet {
	
	public static final int MAIN_SCREEN = 0;
	public static final int INSTRUCTION_SCREEN = 1;
	public static final int SHOP_SCREEN = 2;
	public static final int GAME_SCREEN = 3;
	public static final int PAUSE_SCREEN = 4;
	public static final int WIN_SCREEN = 5;
	
	public float ratioX, ratioY;
	
	private ArrayList<Integer> keys;
	
	private Screen activeScreen;
	private ArrayList<Screen> screens;
	
	/**
	 * Constructs a DrawingSurface object.
	 */
	public DrawingSurface() {
		keys = new ArrayList<Integer>();
		
		// Add all the screens
		screens = new ArrayList<Screen>();
		screens.add(new MainScreen(400, 600, this));
		screens.add(new InstructionScreen(400, 600, this));
		screens.add(new ShopScreen(400, 600, this));
		screens.add(new GameScreen(400, 600, this));
		screens.add(new PauseScreen(400, 600, this));
		screens.add(new WinScreen(400, 600, this));
		
		// Show the main menu screen first
		activeScreen = screens.get(MAIN_SCREEN);
	}
	
	/**
	 * Settings used by the PApplet. Do not call directly.
	 */
	public void settings() {
		size(activeScreen.WIDTH, activeScreen.HEIGHT);
	}
	
	/**
	 * Sets up the DrawingSurface. Automatically called when needed.
	 */
	public void setup() {
		surface.setResizable(true);
		for (Screen s : screens)
			s.setup();
	}
	
	/**
	 * Draws the currently active screen. Called once per frame.
	 */
	public void draw() {
		ratioX = (float) width / activeScreen.WIDTH;
		ratioY = (float) height / activeScreen.HEIGHT;
		// Draw the active screen
		pushMatrix();
		scale(ratioX, ratioY);
		activeScreen.draw();
		popMatrix();
	}
	
	/**
	 * Records when a key is pressed. Do not call directly.
	 */
	public void keyPressed() {
		keys.add(keyCode);
	}

	/**
	 * Records when a key is released. Do not call directly.
	 */
	public void keyReleased() {
		
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}
	
	/**
	 * Checks if a given key is pressed (currently held down).
	 * @param code The key code to check.
	 * @return Whether the given key is pressed.
	 */
	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
	
	/**
	 * Checks if a given mouse button is clicked (currently held down).
	 * @param code The mouse button to check.
	 * @return Whether the given mouse button is being held.
	 */
	public boolean isClicked(Integer code) {
		return mousePressed && mouseButton == code;
	}
	
	/**
	 * Records when the mouse is pressed. Do not call directly.
	 */
	public void mousePressed() {
		activeScreen.mousePressed();
	}
	
	/**
	 * Records when the mouse is moved. Do not call directly.
	 */
	public void mouseMoved() {
		activeScreen.mouseMoved();
	}
	
	/**
	 * Records when the mouse is dragged. Do not call directly.
	 */
	public void mouseDragged() {
		activeScreen.mouseDragged();
	}
	
	/**
	 * Records when the mouse is released. Do not call directly.
	 */
	public void mouseReleased() {
		activeScreen.mouseReleased();
	}
	
	/**
	 * Converts scaled coordinates to real coordinates.
	 * @param assumed The scaled coordinates.
	 * @return The real coordinates.
	 */
	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}

	/**
	 * Converts real coordinates to scaled coordinates.
	 * @param actual The real coordinates.
	 * @return The scaled coordinates.
	 */
	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}
	
	/**
	 * Gets the current mouse location, with appropriate scaling applied.
	 * @return The current (x, y) mouse location.
	 */
	public Point getMouseLocation() {
		return actualCoordinatesToAssumed(new Point(mouseX, mouseY));
	}
	
	/**
	 * Switches the currently active screen to the one specified.
	 * Use the constants found in DrawingSurface to select the screen.
	 * @param i The ID of the screen to switch to.
	 */
	public void switchScreen(int i) {
		activeScreen = screens.get(i);
	}
	
	/**
	 * Gets a specified Screen object.
	 * Use the constants found in DrawingSurface to select the screen.
	 * @param i The ID of the screen to get.
	 * @return The Screen object.
	 */
	public Screen getScreen(int i) {
		return screens.get(i);
	}
	
}
