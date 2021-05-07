package Screens;

import Main.DrawingSurface;
import Sprites.*;
import processing.core.PConstants;

public class GameScreen extends Screen {
	
	private DrawingSurface surface;
	
	private Runner runner;
	private Hunter hunter;
	private int timer;

	public GameScreen(int width, int height, DrawingSurface surface) {
		super(width, height);
		this.surface = surface;
		// runner = new Runner(100, 100, 50, 50, surface);
		// hunter = new Hunter(300, 300, 50, 50, surface);
		// A round lasts 30 seconds
		timer = 30 * 60;
	}
	
	@Override
	public void draw() {
		surface.pushStyle();
		surface.background(255, 200, 255);
		surface.fill(0, 100, 0);
		
		// Timer
		surface.textAlign(PConstants.CENTER, PConstants.CENTER);
		surface.textSize(24);
		surface.text(String.format("Timer: %.2f", timer / 60.0), 200, 200);
		
		timer--;
		surface.popStyle();
	}
	
	/**
	 * Resets the game.
	 * Call this before starting a new round.
	 */
	public void reset() {
		
	}
	
}
