package Main;


import java.awt.Point;
import java.util.ArrayList;

import Screens.*;
import processing.core.PApplet;

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
		//activeScreen = screens.get(MAIN_SCREEN);
		activeScreen = screens.get(GAME_SCREEN);
	}
	
	public void settings() {
		size(activeScreen.WIDTH, activeScreen.HEIGHT);
	}
	
	public void setup() {
		surface.setResizable(true);
		for (Screen s : screens)
			s.setup();
	}
	
	public void draw() {
		ratioX = (float) width / activeScreen.WIDTH;
		ratioY = (float) height / activeScreen.HEIGHT;
		// Draw the active screen
		pushMatrix();
		scale(ratioX, ratioY);
		activeScreen.draw();
		popMatrix();
	}
	
	public void keyPressed() {
		keys.add(keyCode);
	}

	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
	
	public void mousePressed() {
		activeScreen.mousePressed();
	}
	
	public void mouseMoved() {
		activeScreen.mouseMoved();
	}
	
	public void mouseDragged() {
		activeScreen.mouseDragged();
	}
	
	public void mouseReleased() {
		activeScreen.mouseReleased();
	}
	
	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}

	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}
	
	public void switchScreen(int i) {
		activeScreen = screens.get(i);
	}
	
	public Screen getScreen(int i) {
		return screens.get(i);
	}
	
}
