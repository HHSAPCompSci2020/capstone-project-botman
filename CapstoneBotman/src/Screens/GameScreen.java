package Screens;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import Game.Map;
import Main.DrawingSurface;
import Sprites.*;
import processing.core.PConstants;

/**
 * 
 * The GameScreen class combines all the Sprites together to run the actual game.
 * @author Kyle Fu
 *
 */
public class GameScreen extends Screen {
	
	private static final boolean DEBUG_MODE = false;
	private static final double ROTATE_SPEED = 0.07;
	private static final int MAX_SPEED = 5;
	private static final int SCROLL_SPEED = 2;
	private static final int RESPAWN_DELAY = 180;
	private static final int NUM_WINS = 3;
	
	private DrawingSurface surface;
	
	private Map map;
	private Runner runner;
	private Hunter hunter;
	private Goal goal;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Bullet> bullets;
	private ArrayList<HealthPack> healthPickups;
	private ArrayList<Money> moneyPickups;
	private int timer, hunterRespawn;
	private boolean goalReached;
	
	/**
	 * Constructs a GameScreen.
	 * @param width The screen width
	 * @param height The screen height
	 * @param surface The DrawingSurface containing this screen
	 */
	public GameScreen(int width, int height, DrawingSurface surface) {
		super(width, height);
		this.surface = surface;
	}
	
	/**
	 * Sets up the screen. Do not call this method manually.
	 */
	@Override
	public void setup() {
		super.setup();
		runner = new Runner(0, 0, 44, 44, surface);
		hunter = new Hunter(0, 0, 44, 44, surface);
		obstacles = new ArrayList<Obstacle>();
		bullets = new ArrayList<Bullet>();
		healthPickups = new ArrayList<HealthPack>();
		moneyPickups = new ArrayList<Money>();
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
		if (map.scroll(SCROLL_SPEED)) {
			runner.translate(0, SCROLL_SPEED);
			hunter.translate(0, SCROLL_SPEED);
			if (goal != null)
				goal.translate(0, SCROLL_SPEED);
			for (Obstacle o : obstacles) {
				o.translate(0, SCROLL_SPEED);
			}
			for (Bullet b : bullets) {
				b.translate(0, SCROLL_SPEED);
			}
			for (HealthPack h : healthPickups) {
				h.translate(0, SCROLL_SPEED);
			}
			for (Money m : moneyPickups) {
				m.translate(0, SCROLL_SPEED);
			}
		}
		
		// Runner controls
		keepPlayerInBounds(runner);
		controlPlayer(runner, KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D);
		if (surface.isPressed(KeyEvent.VK_G))
			runner.setAngle(runner.getAngle() - ROTATE_SPEED);
		if (surface.isPressed(KeyEvent.VK_H))
			runner.setAngle(runner.getAngle() + ROTATE_SPEED);
		if (surface.isPressed(KeyEvent.VK_SPACE)) {
			boolean fired = false;
			while (runner.getWeapon().getCurrDelay() <= 0) {
				Bullet bullet = runner.fire();
				bullets.add(bullet);
				fired = true;
			}
			if (fired)
				surface.playSoundEffect("vandalTap.mp3");
		}
		
		// Hunter controls
		if (hunterAlive()) {
			keepPlayerInBounds(hunter);
			controlPlayer(hunter, KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT);
			if (surface.isClicked(PConstants.LEFT)) {
				boolean fired = false;
				while (hunter.getWeapon().getCurrDelay() <= 0) {
					Bullet bullet = hunter.fire();
					bullets.add(bullet);
					fired = true;
				}
				if (fired)
					surface.playSoundEffect("vandalTap.mp3");
			}
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
		// Generate the map
		map.generate();
		
		// Move players while checking obstacle collisions
		moveWithCollisionChecks(runner);
		moveWithCollisionChecks(hunter);
		
		// Off screen
		removeOffScreen();
		
		// Setup hitboxes
		Rectangle2D rRect = runner.getHitBox();
		Ellipse2D.Double runnerHitbox = new Ellipse2D.Double(rRect.getX(), rRect.getY(), rRect.getWidth(), rRect.getHeight());
		Rectangle2D hRect = hunter.getHitBox();
		Ellipse2D.Double hunterHitbox = new Ellipse2D.Double(hRect.getX(), hRect.getY(), hRect.getWidth(), hRect.getHeight());
		
		// Goal collision
		if (goal != null && runnerHitbox.intersects(goal.getHitBox()))
			goalReached = true;
		
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
			// Note: If players move super fast, bullets might be able to hit the player that launched them
			if (runnerHitbox.intersects(b.getHitBox())) {
				runner.changeHealth((int) (-b.getDamage() / 1.5));
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
		
		// Health pickup collisions
		for (int i = 0; i < healthPickups.size(); i++) {
			HealthPack h = healthPickups.get(i);
			boolean toRemove = false;
			
			if (runnerHitbox.intersects(h.getHitBox())) {
				runner.changeHealth(h.getHealth());
				toRemove = true;
			} else if (hunterAlive() && hunterHitbox.intersects(h.getHitBox())) {
				hunter.changeHealth(h.getHealth());
				toRemove = true;
			}
			
			if (toRemove) {
				surface.playSoundEffect("health.mp3");
				healthPickups.remove(i);
				i--;
			}
		}
		
		// Money pickup collisions
		for (int i = 0; i < moneyPickups.size(); i++) {
			Money m = moneyPickups.get(i);
			boolean toRemove = false;
			
			if (runnerHitbox.intersects(m.getHitBox())) {
				runner.changeCash(m.getMoney());
				toRemove = true;
			} else if (hunterAlive() && hunterHitbox.intersects(m.getHitBox())) {
				hunter.changeCash(m.getMoney());
				toRemove = true;
			}
			
			if (toRemove) {
				surface.playSoundEffect("money.mp3");
				moneyPickups.remove(i);
				i--;
			}
		}
		
		// Player-to-player collision
		Area areaIntersect = new Area(runnerHitbox);
		areaIntersect.intersect(new Area(hunterHitbox));
		if (hunterAlive() && !areaIntersect.isEmpty()) {
			runner.changeHealth(-hunter.getHealth() / 2);
			hunter.setHealth(0);
		}
	}
	
	private void removeOffScreen() {
		// Obstacles
		for (int i = 0; i < obstacles.size(); i++) {
			Obstacle o = obstacles.get(i);
			if (o.getHitBox().getMinY() >= HEIGHT) {
				obstacles.remove(i);
				i--;
			}
		}
		
		// Health pickups
		for (int i = 0; i < healthPickups.size(); i++) {
			HealthPack h = healthPickups.get(i);
			if (h.getHitBox().getMinY() >= HEIGHT) {
				healthPickups.remove(i);
				i--;
			}
		}
		
		// Money pickups
		for (int i = 0; i < moneyPickups.size(); i++) {
			Money m = moneyPickups.get(i);
			if (m.getHitBox().getMinY() >= HEIGHT) {
				moneyPickups.remove(i);
				i--;
			}
		}
	}
	
	private void updatePost() {
		// Pause
		if (surface.isPressed(KeyEvent.VK_P)) {
			surface.switchScreen(DrawingSurface.PAUSE_SCREEN);
		}
		
		// Game logic
		timer++;
		if (goalReached || runner.getHealth() <= 0)
			endRound();
		if (hunterAlive() && hunter.getHealth() <= 0) {
			surface.playSoundEffect("boom.mp3");
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
		if (player.getHitBox().getMaxY() < player.getHeight()) {
			player.translate(0, (int) (player.getHeight() - player.getHitBox().getMaxY()));
			player.setvY(-SCROLL_SPEED);
		}
		
		// Bottom border
		if (player.getHitBox().getMinY() > HEIGHT) {
			// Kill the player instantly
			player.setHealth(0);
		}
		
		// Left border
		if (player.getHitBox().getMaxX() < player.getWidth()) {
			player.translate((int) (player.getWidth() - player.getHitBox().getMaxX()), 0);
			player.setvX(0);
		}
		
		// Right border
		if (player.getHitBox().getMinX() > WIDTH - player.getWidth()) {
			player.translate((int) (WIDTH - player.getWidth() - player.getHitBox().getMinX()), 0);
			player.setvX(0);
		}
	}
	
	private void moveWithCollisionChecks(Player player) {
		Rectangle rect = player.getHitBox();
		for (Obstacle o : obstacles) {
			// Maybe improve collisions to allow for getting out of corners
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
	
	/**
	 * Updates and draws the GameScreen. Automatically called once per frame.
	 */
	@Override
	public void draw() {
		update();
		
		// Pre drawing
		surface.pushStyle();
		surface.background(184, 226, 170);
		
		// Draw all objects
		if (goal != null)
			goal.draw(surface);
		for (Obstacle o : obstacles) {
			o.draw(surface);
		}
		for (Bullet b : bullets) {
			b.draw(surface);
		}
		for (HealthPack h : healthPickups) {
			h.draw(surface);
		}
		for (Money m : moneyPickups) {
			m.draw(surface);
		}
		runner.draw(surface);
		if (hunterAlive())
			hunter.draw(surface);
		
		// Text
		surface.textAlign(PConstants.CENTER, PConstants.CENTER);
		surface.textSize(24);
		surface.fill(0, 100, 0);
		if (DEBUG_MODE) {
			surface.text(String.format("DEBUG INFO", surface.frameRate), 200, 140);
			surface.text(String.format("FPS: %.2f", surface.frameRate), 200, 170);
			surface.text(String.format("Timer: %.2f", timer / 60.0), 200, 200);
			surface.text(String.format("R %d VS %d H", runner.getWins(), hunter.getWins()), 200, 230);
			surface.text(String.format("Runner HP: %d", runner.getHealth()), 200, 260);
			surface.text(String.format("Hunter HP: %d", hunter.getHealth()), 200, 290);
			surface.text(String.format("Runner $: %d", runner.getCash()), 200, 320);
			surface.text(String.format("Hunter $: %d", hunter.getCash()), 200, 350);
		} else {
			surface.text(String.format("%d", runner.getWins()), WIDTH - 20, HEIGHT / 2 - 30);
			surface.text("VS", WIDTH - 20, HEIGHT / 2);
			surface.text(String.format("%d", hunter.getWins()), WIDTH - 20, HEIGHT / 2 + 30);
		}
		
		// Post drawing
		surface.popStyle();
	}
	
	private boolean hunterAlive() {
		return hunterRespawn < 0;
	}
	
	private void spawnRunner() {
		// Random x to avoid spawn camping
		runner.setX(50 + (int) ((WIDTH - 49) * Math.random()));
		runner.setY(100);
		runner.setvX(0);
		runner.setvY(0);
		runner.setAngle(Math.PI / 2);
		runner.setHealth(runner.getMaxHealth());
	}
	
	private void spawnHunter() {
		// Do not spawn hunter on top of obstacles or bullets (20 attempts max)
		for (int i = 0; i < 20; i++) {
			// Random x to avoid spawn camping
			hunter.setX(25 + (int) (Math.random() * (WIDTH - 50)));
			hunter.setY(400);
			Rectangle2D hRect = hunter.getHitBox();
			Ellipse2D.Double hunterHitbox = new Ellipse2D.Double(hRect.getX(), hRect.getY(), hRect.getWidth(), hRect.getHeight());
			boolean valid = true;
			for (Obstacle o : obstacles) {
				if (hunterHitbox.intersects(o.getHitBox())) {
					valid = false;
					break;
				}
			}
			if (valid) break;
		}
		hunter.setvX(0);
		hunter.setvY(0);
		hunter.setAngle(-Math.PI / 2);
		hunter.setHealth(hunter.getMaxHealth());
	}
	
	/**
	 * Spawns an obstacle.
	 * @param x center x coordinate
	 * @param y center y coordinate
	 * @param width width of sprite
	 * @param height height of sprite
	 */
	public void spawnObstacle(int x, int y, int width, int height) {
		obstacles.add(new Obstacle(x, y, width, height, surface));
	}
	
	/**
	 * Spawns a health pickup.
	 * @param x center x coordinate
	 * @param y center y coordinate
	 * @param width width of sprite
	 * @param height height of sprite
	 * @param health the health the object provides when picked up
	 */
	public void spawnHealthPickup(int x, int y, int width, int height, int health) {
		healthPickups.add(new HealthPack(x, y, width, height, surface, health));
	}
	
	/**
	 * Spwans a money pickup.
	 * @param x center x coordinate
	 * @param y center y coordinate
	 * @param width width of sprite
	 * @param height height of sprite
	 * @param money cash the money object provides when picked up
	 */
	public void spawnMoneyPickup(int x, int y, int width, int height, int money) {
		moneyPickups.add(new Money(x, y, width, height, surface, money));
	}
	
	/**
	 * Spawns the goal. Should be called only once, at the end of the round.
	 * @param x center x coordinate
	 * @param y center y coordinate
	 * @param width width of sprite
	 * @param height height of sprite
	 */
	public void spawnGoal(int x, int y, int width, int height) {
		goal = new Goal(x, y, width, height, surface);
	}
	
	/**
	 * Prepares a new round of the game, and then switches to the shop menu.
	 * Call this when starting a new round.
	 * @postcondition The currently active screen will be ShopScreen.
	 */
	public void prepareRound() {
		runner.changeCash(50);
		hunter.changeCash(50);
		// Reset some stats
		runner.setMaxHealth(100);
		hunter.setMaxHealth(100);
		runner.setToRifle();
		hunter.setToRifle();
		surface.switchScreen(DrawingSurface.SHOP_SCREEN);
	}
	
	/**
	 * Begins a round. Automatically called by ShopScreen.
	 */
	public void beginRound() {
		surface.switchScreen(DrawingSurface.GAME_SCREEN);
		goal = null;
		obstacles.clear();
		bullets.clear();
		healthPickups.clear();
		moneyPickups.clear();
		goalReached = false;
		timer = 0;
		hunterRespawn = -1;
		
		// Choose the map
		map = new Map("map1.txt", this);
		map.generate();
		spawnRunner();
		spawnHunter();
		surface.playMusic(map.getSong());
	}
	
	/**
	 * Ends a round, giving a point to the winner.
	 * @precondition One of the win conditions must be met.
	 */
	public void endRound() {
		if (goalReached) {
			surface.playSoundEffect("kahootGong.mp3");
			runner.setWins(runner.getWins() + 1);
			hunter.setLosses(hunter.getLosses() + 1);
			runner.changeCash(50);
		} else if (runner.getHealth() <= 0) {
			surface.playSoundEffect("boom.mp3");
			hunter.setWins(hunter.getWins() + 1);
			runner.setLosses(runner.getLosses() + 1);
			hunter.changeCash(50);
		} else {
			System.err.println("Warning: endRound() called with no winner!");
			System.err.printf("Goal reached %d, Runner health %d\n", goalReached, runner.getHealth());
		}
		// End music
		surface.stopMusic();
		// Check for winner
		if (runner.getWins() >= NUM_WINS || hunter.getWins() >= NUM_WINS) {
			surface.switchScreen(DrawingSurface.WIN_SCREEN);
			return;
		}
		// Maybe wait a bit before advancing?
		// For now, no swap
		// Swap stats
//		int temp = runner.getWins();
//		runner.setWins(hunter.getWins());
//		hunter.setWins(temp);
//		temp = runner.getLosses();
//		runner.setLosses(hunter.getLosses());
//		hunter.setLosses(temp);
//		temp = runner.getCash();
//		runner.setCash(hunter.getCash());
//		hunter.setCash(temp);
		prepareRound();
	}
	
	/**
	 * Starts a new game (resets win counts).
	 */
	public void startGame() {
		runner.setLosses(0);
		runner.setWins(0);
		runner.setCash(0);
		hunter.setLosses(0);
		hunter.setWins(0);
		hunter.setCash(0);
		prepareRound();
	}
	
	/**
	 * Gets the current Runner.
	 * @return The runner.
	 */
	public Runner getRunner() {
		return runner;
	}
	
	/**
	 * Gets the current Hunter.
	 * @return The hunter.
	 */
	public Hunter getHunter() {
		return hunter;
	}
	
	/**
	 * Gets the current value of the game timer (in frames)
	 * @return The value of the game timer.
	 */
	public int getTimer() {
		return timer;
	}
	
}
