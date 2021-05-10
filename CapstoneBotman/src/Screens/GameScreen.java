package Screens;

import java.awt.event.KeyEvent;
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
	}
	
	@Override
	public void setup() {
		super.setup();
		runner = new Runner(0, 0, 44, 44, surface);
		hunter = new Hunter(0, 0, 44, 44, surface);
		obstacles = new ArrayList<Obstacle>();
		bullets = new ArrayList<Bullet>();
		resetRound();
	}
	
	/**
	 * Updates the game state. Automatically called by draw().
	 */
	public void update() {
		// Obstacles
		for (Obstacle o : obstacles) {
			o.translate(0, 2);
		}
		for (Bullet b : bullets) {
			b.move();
		}
		
		// Runner controls
		if (surface.isPressed(KeyEvent.VK_W))
			 runner.translate(0, -5);
		if (surface.isPressed(KeyEvent.VK_A))
			runner.translate(-5, 0);
		if (surface.isPressed(KeyEvent.VK_S))
			runner.translate(0, 5);
		if (surface.isPressed(KeyEvent.VK_D))
			runner.translate(5, 0);
		// TODO: Make runner rotate using some call to runner.rotate() or runner.setAngle()
		if (surface.isPressed(KeyEvent.VK_G))
			System.out.println("PLACEHOLDER: runner rotate left");
		if (surface.isPressed(KeyEvent.VK_H))
			System.out.println("PLACEHOLDER: runner rotate right");
		if (surface.isPressed(KeyEvent.VK_SPACE)) {
			Bullet bullet = runner.fire();
			if (bullet != null)
				bullets.add(bullet);
		}
		
		// Hunter controls
		if (surface.isPressed(KeyEvent.VK_UP))
			 hunter.translate(0, -5);
		if (surface.isPressed(KeyEvent.VK_LEFT))
			hunter.translate(-5, 0);
		if (surface.isPressed(KeyEvent.VK_DOWN))
			hunter.translate(0, 5);
		if (surface.isPressed(KeyEvent.VK_RIGHT))
			hunter.translate(5, 0);
		if (surface.isClicked(PConstants.LEFT)) {
			Bullet bullet = hunter.fire();
			if (bullet != null)
				bullets.add(bullet);
		}
	}
	
	@Override
	public void draw() {
		update();
		
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
		// TODO: Track runner and hunter angles (or do it in the sprites)
		// For now just make them spin to test rotating and bullet firing
		runner.draw(surface, timer/5);
		hunter.draw(surface, timer/5);
		for (Obstacle o : obstacles) {
			o.draw(surface);
		}
		for (Bullet b : bullets) {
			b.draw(surface);
		}
		
		// Post drawing
		surface.popStyle();
	}
	
	@Override
	public void mouseMoved() {
		// TODO: Call something like hunter.rotate() or hunter.setAngle(), or track angle in GameScreen
		// For now ignores the mouse movement
	}
	
	/**
	 * Resets one round of the game.
	 * Call this before starting a new round.
	 */
	public void resetRound() {
		runner.setX(200);
		runner.setY(100);
		hunter.setX(200);
		hunter.setY(400);
		obstacles.clear();
		bullets.clear();
		// A round lasts 30 seconds
		timer = 30 * 60;
	}
	
	public void resetGame() {
		
	}
	
}
