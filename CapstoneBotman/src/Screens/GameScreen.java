package Screens;

import java.util.ArrayList;

import Main.DrawingSurface;
import Sprites.*;
import processing.core.PConstants;

public class GameScreen extends Screen {
	
	private DrawingSurface surface;
	
	private Runner runner;
	private Hunter hunter;
	public Goal goal;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Bullet> bullets;
	private int timer;

	public GameScreen(int width, int height, DrawingSurface surface) {
		super(width, height);
		this.surface = surface;
		runner = new Runner(100, 100, 50, 50, surface);
		hunter = new Hunter(300, 300, 50, 50, surface);
		goal = null;
		obstacles = new ArrayList<Obstacle>();
		bullets = new ArrayList<Bullet>();
		// A round lasts 30 seconds
		timer = 30 * 60;
	}
	
	@Override
	public void draw() {
		// Pre drawing
		surface.pushStyle();
		surface.background(255, 200, 255);
		surface.fill(0, 100, 0);
		
		// Timer
		surface.textAlign(PConstants.CENTER, PConstants.CENTER);
		surface.textSize(24);
		surface.text(String.format("Timer: %.2f", timer / 60.0), 200, 200);
		if (timer > 0) timer--;
		
		// Draw all objects
		runner.draw(surface);
		hunter.draw(surface);
		for (Obstacle o : obstacles) {
			o.draw(surface);
		}
		for (Bullet b : bullets) {
			b.draw(surface);
		}
		
		// Post drawing
		surface.popStyle();
	}
	
	/**
	 * Resets the game.
	 * Call this before starting a new round.
	 */
	public void reset() {
		
	}
	
}
