package Screens;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import Main.DrawingSurface;
import Sprites.*;
import processing.core.PConstants;

public class GameScreen extends Screen {
	
	private static final double ROTATE_SPEED = 0.07;
	private static final int MAX_SPEED = 5;
	
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
		// Setup hitboxes
		Rectangle2D rRect = runner.getHitBox();
		Ellipse2D.Double runnerHitbox = new Ellipse2D.Double(rRect.getX(), rRect.getY(), rRect.getWidth(), rRect.getHeight());
		Rectangle2D hRect = hunter.getHitBox();
		Ellipse2D.Double hunterHitbox = new Ellipse2D.Double(hRect.getX(), hRect.getY(), hRect.getWidth(), hRect.getHeight());
		
		// Obstacles
		for (int i = 0; i < obstacles.size(); i++) {
			Obstacle o = obstacles.get(i);
			o.translate(0, 2);
		}
		
		// Bullets
		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			boolean toRemove = false;
			b.move();
			if (!b.checkDuration())
				toRemove = true;
			
			// Check intersection with players
			// TODO: Bullets might be able to hit the player that launched them
			if (runnerHitbox.intersects(b.getHitBox())) {
				runner.changeHealth(-b.getDamage());
				toRemove = true;
			}
			if (hunterHitbox.intersects(b.getHitBox())) {
				hunter.changeHealth(-b.getDamage());
				toRemove = true;
			}
			
			if (toRemove) {
				bullets.remove(i);
				i--;
			}
		}
		
		// Players
		runner.move();
		hunter.move();
		
		// Pause
		if (surface.isPressed(KeyEvent.VK_P)) {
			surface.switchScreen(DrawingSurface.PAUSE_SCREEN);
		}
		
		// Runner controls
		movePlayer(runner, KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D);
		if (surface.isPressed(KeyEvent.VK_G))
			runner.setAngle(runner.getAngle() - ROTATE_SPEED);
		if (surface.isPressed(KeyEvent.VK_H))
			runner.setAngle(runner.getAngle() + ROTATE_SPEED);
		if (surface.isPressed(KeyEvent.VK_SPACE)) {
			Bullet bullet = runner.fire();
			if (bullet != null)
				bullets.add(bullet);
		}
		
		// Hunter controls
		movePlayer(hunter, KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT);
		if (surface.isClicked(PConstants.LEFT)) {
			Bullet bullet = hunter.fire();
			if (bullet != null)
				bullets.add(bullet);
		}
		// TODO: Aiming is slightly off center
		Point mouseLoc = surface.getMouseLocation();
		double dx = mouseLoc.x - hunter.getX();
		double dy = mouseLoc.y - hunter.getY();
		double angle = Math.atan2(dy, dx);
		hunter.setAngle(angle);
		
		// Game logic
		if (timer > 0)
			timer--;
		else {
			runner.setWins(runner.getWins() + 1);
			hunter.setLosses(hunter.getLosses() + 1);
			resetRound();
		}
		if (runner.getHealth() <= 0) {
			hunter.setWins(hunter.getWins() + 1);
			runner.setLosses(runner.getLosses() + 1);
			resetRound();
		}
		if (hunter.getHealth() <= 0) {
			
		}
	}
	
	private void movePlayer(Player player, int up, int left, int down, int right) {
		boolean movedX = false;
		if (surface.isPressed(left)) {
			player.setvX(Math.max(player.getvX() - 1, -MAX_SPEED));
			movedX = true;
		}
		if (surface.isPressed(right)) {
			player.setvX(Math.min(player.getvX() + 1, MAX_SPEED));
			movedX = true;
		}
		if (!movedX) {
			int currvX = player.getvX();
			if (currvX < 0) currvX++;
			else if (currvX > 0) currvX--;
			player.setvX(currvX);
		}
		
		boolean movedY = false;
		if (surface.isPressed(up)) {
			player.setvY(Math.max(player.getvY() - 1, -MAX_SPEED));
			movedY = true;
		}
		if (surface.isPressed(down)) {
			player.setvY(Math.min(player.getvY() + 1, MAX_SPEED));
			movedY = true;
		}
		if (!movedY) {
			int currvY = player.getvY();
			if (currvY < 0) currvY++;
			else if (currvY > 0) currvY--;
			player.setvY(currvY);
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
		surface.text(String.format("%d VS %d", runner.getWins(), hunter.getWins()), 200, 230);
		surface.text(String.format("Runner HP: %d", runner.getHealth()), 200, 260);
		surface.text(String.format("Hunter HP: %d", hunter.getHealth()), 200, 290);
		
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
	
	private void spawnRunner() {
		// Random x to avoid spawn camping
		runner.setX(50 + (int) (Math.random() * (WIDTH - 50)));
		runner.setY(100);
		runner.setvX(0);
		runner.setvY(0);
		runner.setAngle(Math.PI / 2);
		runner.setHealth(100);
	}
	
	private void spawnHunter() {
		// Random x to avoid spawn camping
		hunter.setX(50 + (int) (Math.random() * (WIDTH - 50)));
		hunter.setY(500);
		hunter.setvX(0);
		hunter.setvY(0);
		hunter.setAngle(-Math.PI / 2);
		hunter.setHealth(100);
	}
	
	/**
	 * Resets one round of the game.
	 * Call this before starting a new round.
	 */
	public void resetRound() {
		spawnRunner();
		spawnHunter();
		obstacles.clear();
		bullets.clear();
		// A round lasts 30 seconds
		timer = 30 * 60;
		// TODO: For testing
		timer -= 1200;
	}
	
	/**
	 * Resets the entire game.
	 * Call this when starting a new game.
	 */
	public void resetGame() {
		runner.setLosses(0);
		runner.setWins(0);
		hunter.setLosses(0);
		hunter.setWins(0);
		resetRound();
	}
	
	/**
	 * Gets the Runner object.
	 * @return The runner.
	 */
	public Runner getRunner() {
		return runner;
	}
	
	/**
	 * Gets the Hunter object.
	 * @return The hunter.
	 */
	public Hunter getHunter() {
		return hunter;
	}
	
}
