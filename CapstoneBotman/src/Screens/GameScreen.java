package Screens;

import java.awt.Point;
import java.awt.Rectangle;
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
	private static final int SCROLL_SPEED = 2;
	private static final int PUSHBACK_SPEED = 20;
	private static final int PUSHBACK_DAMAGE = 25;
	private static final int RESPAWN_DELAY = 180;
	private static final int NUM_WINS = 3;
	private static final boolean USE_SHOP = true;
	
	private DrawingSurface surface;
	
	private Runner runner;
	private Hunter hunter;
	public Goal goal;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Bullet> bullets;
	private int timer, hunterRespawn;

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
	}
	
	/**
	 * Updates the game state. Automatically called by draw().
	 */
	public void update() {
		// Screen scroll, update velocities, handle controls
		updatePre();
		// Add obstacles, check collisions, move players
		updateMid();
		// Handle game logic
		updatePost();
	}
	
	private void updatePre() {
		// Screen scroll
		runner.translate(0, SCROLL_SPEED);
		hunter.translate(0, SCROLL_SPEED);
		for (Obstacle o : obstacles) {
			o.translate(0, SCROLL_SPEED);
		}
		for (Bullet b : bullets) {
			b.translate(0, SCROLL_SPEED);
		}
		
		// Runner controls
		keepPlayerInBounds(runner);
		controlPlayer(runner, KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D);
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
		if (hunterAlive()) {
			keepPlayerInBounds(hunter);
			controlPlayer(hunter, KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT);
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
		} else {
			if (hunterRespawn == 0)
				spawnHunter();
			hunterRespawn--;
		}
		
		// Bullets
		for (Bullet b : bullets) {
			b.move();
		}
	}
	
	private void updateMid() {
		// Add obstacles
		if (timer % 50 == 0) {
			obstacles.add(new Obstacle((int) (401 * Math.random()), -80, 120, 50, surface));
		}
		
		// Setup hitboxes
		Rectangle2D rRect = runner.getHitBox();
		Ellipse2D.Double runnerHitbox = new Ellipse2D.Double(rRect.getX(), rRect.getY(), rRect.getWidth(), rRect.getHeight());
		Rectangle2D hRect = hunter.getHitBox();
		Ellipse2D.Double hunterHitbox = new Ellipse2D.Double(hRect.getX(), hRect.getY(), hRect.getWidth(), hRect.getHeight());
		
		// Move players while checking obstacle collisions
		moveWithCollisionChecks(runner);
		moveWithCollisionChecks(hunter);
		
		// Obstacle off screen
		for (int i = 0; i < obstacles.size(); i++) {
			Obstacle o = obstacles.get(i);
			if (o.getHitBox().getMinY() >= HEIGHT) {
				obstacles.remove(i);
				i--;
			}
		}
		
		// Bullet off screen & collisions
		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			boolean toRemove = false;
			if (!b.checkDuration())
				toRemove = true;
			
			for (Obstacle o : obstacles)
				if (b.getHitBox().intersects(o.getHitBox()))
					toRemove = true;
			
			// Check intersection with players
			// TODO: Bullets might be able to hit the player that launched them
			if (runnerHitbox.intersects(b.getHitBox())) {
				runner.changeHealth(-b.getDamage());
				toRemove = true;
			}
			if (hunterAlive() && hunterHitbox.intersects(b.getHitBox())) {
				hunter.changeHealth(-b.getDamage());
				toRemove = true;
			}
			
			if (toRemove) {
				bullets.remove(i);
				i--;
			}
		}
	}
	
	private void updatePost() {
		// Pause
		if (surface.isPressed(KeyEvent.VK_P))
			surface.switchScreen(DrawingSurface.PAUSE_SCREEN);
		
		// Game logic
		if (timer > 0)
			timer--;
		else {
			runner.setWins(runner.getWins() + 1);
			hunter.setLosses(hunter.getLosses() + 1);
			runner.changeCash(50);
			prepareRound();
		}
		if (runner.getHealth() <= 0) {
			hunter.setWins(hunter.getWins() + 1);
			runner.setLosses(runner.getLosses() + 1);
			hunter.changeCash(50);
			prepareRound();
		}
		if (hunterAlive() && hunter.getHealth() <= 0) {
			runner.changeCash(25);
			hunterRespawn = RESPAWN_DELAY;
		}
	}
	
	private void controlPlayer(Player player, int up, int left, int down, int right) {
		// Move in x direction
		boolean movedX = false;
		if (surface.isPressed(left)) {
			player.setvX(player.getvX() - 1);
			movedX = true;
		}
		if (surface.isPressed(right)) {
			player.setvX(player.getvX() + 1);
			movedX = true;
		}
		if (!movedX) {
			int currvX = player.getvX();
			if (currvX < 0) currvX++;
			else if (currvX > 0) currvX--;
			player.setvX(currvX);
		}
		
		// Move in y direction
		boolean movedY = false;
		if (surface.isPressed(up)) {
			player.setvY(player.getvY() - 1);
			movedY = true;
		}
		if (surface.isPressed(down)) {
			player.setvY(player.getvY() + 1);
			movedY = true;
		}
		if (!movedY) {
			int currvY = player.getvY();
			if (currvY < 0) currvY++;
			else if (currvY > 0) currvY--;
			player.setvY(currvY);
		}
		
		// Constrain x and y speed
		if (player.getvX() < -MAX_SPEED)
			player.setvX(player.getvX() + 2);
		else if (player.getvX() > MAX_SPEED)
			player.setvX(player.getvX() - 2);
		if (player.getvY() < -MAX_SPEED)
			player.setvY(player.getvY() + 2);
		else if (player.getvY() > MAX_SPEED)
			player.setvY(player.getvY() - 2);
	}
	
	private void keepPlayerInBounds(Player player) {
		// Top border
		if (player.getHitBox().getMaxY() <= 0) {
			player.setvY(player.getvY() + PUSHBACK_SPEED);
			player.changeHealth(-PUSHBACK_DAMAGE);
		}
		
		// Bottom border
		if (player.getHitBox().getMinY() >= HEIGHT) {
			player.setvY(player.getvY() - PUSHBACK_SPEED);
			player.changeHealth(-PUSHBACK_DAMAGE);
		}
		
		// Left border
		if (player.getHitBox().getMaxX() <= 0) {
			player.setvX(player.getvX() + PUSHBACK_SPEED);
			player.changeHealth(-PUSHBACK_DAMAGE);
		}
		
		// Right border
		if (player.getHitBox().getMinX() >= WIDTH) {
			player.setvX(player.getvX() - PUSHBACK_SPEED);
			player.changeHealth(-PUSHBACK_DAMAGE);
		}
	}
	
	private void moveWithCollisionChecks(Player player) {
		Rectangle rect = player.getHitBox();
		for (Obstacle o : obstacles) {
			// TODO: Improve collisions to allow for getting out of corners
			// Do this by attempting to translate a bit in the direction with least velocity
			// Pre-move (allows for a bit of sliding)
			// if (moveOutOfObstacle(player, o, 3)) continue;
			
			// X-axis
			while (player.getvX() != 0) {
				Ellipse2D.Double pHitbox = new Ellipse2D.Double(rect.getX() + player.getvX(), rect.getY(), rect.getWidth(), rect.getHeight());
				if (pHitbox.intersects(o.getHitBox())) {
					// Lower speed in x direction
					if (player.getvX() > 0)
						player.setvX(player.getvX() - 1);
					else
						player.setvX(player.getvX() + 1);
				} else {
					// No longer collides with this obstacle
					break;
				}
			}
			
			// Y-axis
			while (true) {
				Ellipse2D.Double pHitbox = new Ellipse2D.Double(rect.getX(), rect.getY() + player.getvY(), rect.getWidth(), rect.getHeight());
				if (pHitbox.intersects(o.getHitBox())) {
					// Lower speed in y direction
					if (player.getvY() > 0)
						player.setvY(player.getvY() - 1);
					else if (player.getvY() < 0)
						player.setvY(player.getvY() + 1);
					else {
						// Stuck in an obstacle; find smallest movement out
						moveOutOfObstacle(player, o, Math.min(player.getWidth(), player.getHeight()));
						break;
					}
				} else {
					// No longer collides with this obstacle
					break;
				}
			}
		}
		
		// Move with new velocity
		player.move();
	}
	
	/**
	 * This should be used as a last resort (may cause very large jumps)!
	 */
	private boolean moveOutOfObstacle(Player player, Obstacle obstacle, int maxD) {
		// Find smallest movement out
		Rectangle rect = player.getHitBox();
		rect.translate(player.getvX(), player.getvY());
		Ellipse2D.Double pHitbox;
		Rectangle oHitbox = obstacle.getHitBox();
		for (int d = 0; d <= maxD; d++) {
			// Move up
			pHitbox = new Ellipse2D.Double(rect.getX(), rect.getY() - d, rect.getWidth(), rect.getHeight());
			if (!pHitbox.intersects(oHitbox)) {
				player.translate(0, -d);
				return true;
			}
			// Move down
			pHitbox = new Ellipse2D.Double(rect.getX(), rect.getY() + d, rect.getWidth(), rect.getHeight());
			if (!pHitbox.intersects(oHitbox)) {
				player.translate(0, d);
				return true;
			}
			// Move left
			pHitbox = new Ellipse2D.Double(rect.getX() - d, rect.getY(), rect.getWidth(), rect.getHeight());
			if (!pHitbox.intersects(oHitbox)) {
				player.translate(-d, 0);
				return true;
			}
			// Move right
			pHitbox = new Ellipse2D.Double(rect.getX() + d, rect.getY(), rect.getWidth(), rect.getHeight());
			if (!pHitbox.intersects(oHitbox)) {
				player.translate(d, 0);
				return true;
			}
			d++;
		}
		return false;
	}
	
	@Override
	public void draw() {
		update();
		
		// Pre drawing
		surface.pushStyle();
		surface.background(200, 200, 200);
		surface.fill(0, 100, 0);
		
		// Debug info
		if (true) {
			surface.textAlign(PConstants.CENTER, PConstants.CENTER);
			surface.textSize(24);
			surface.text(String.format("Timer: %.2f", timer / 60.0), 200, 200);
			surface.text(String.format("%d VS %d", runner.getWins(), hunter.getWins()), 200, 230);
			surface.text(String.format("Runner HP: %d", runner.getHealth()), 200, 260);
			surface.text(String.format("Hunter HP: %d", hunter.getHealth()), 200, 290);
			surface.text(String.format("Runner $: %d", runner.getCash()), 200, 320);
			surface.text(String.format("Hunter $: %d", hunter.getCash()), 200, 350);
		}
		
		// Draw all objects
		runner.draw(surface);
		if (hunterAlive())
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
	
	private boolean hunterAlive() {
		return hunterRespawn < 0;
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
		// TODO: Do not spawn hunter on top of obstacles or bullets
		// Random x to avoid spawn camping
		hunter.setX(50 + (int) (Math.random() * (WIDTH - 50)));
		hunter.setY(400);
		hunter.setvX(0);
		hunter.setvY(0);
		hunter.setAngle(-Math.PI / 2);
		hunter.setHealth(100);
	}
	
	/**
	 * Prepares a new round of the game, and then switches to the shop menu.
	 * Call this when starting a new round.
	 */
	public void prepareRound() {
		// Check for winner
		if (runner.getWins() >= NUM_WINS || hunter.getWins() >= NUM_WINS) {
			surface.switchScreen(DrawingSurface.WIN_SCREEN);
			return;
		}
		runner.changeCash(100);
		hunter.changeCash(100);
		if (USE_SHOP)
			surface.switchScreen(DrawingSurface.SHOP_SCREEN);
		else
			beginRound();
	}
	
	/**
	 * Begins a round. Automatically called by ShopScreen.
	 */
	public void beginRound() {
		surface.switchScreen(DrawingSurface.GAME_SCREEN);
		spawnRunner();
		spawnHunter();
		obstacles.clear();
		bullets.clear();
		// A round lasts 30 seconds
		timer = 30 * 60;
		hunterRespawn = -1;
	}
	
	/**
	 * Starts a new game (resets win counts).
	 */
	public void startGame() {
		runner.setLosses(0);
		runner.setWins(0);
		hunter.setLosses(0);
		hunter.setWins(0);
		prepareRound();
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
