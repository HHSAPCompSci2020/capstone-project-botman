package Game;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Scanner;

import Screens.GameScreen;
import Sprites.*;

/**
 * 
 * This class simulates a runner AI using a selection of prerecorded paths and delayed turning towards the hunter.
 * @author Kyle Fu
 *
 */
public class RunnerAI {
	
	private GameScreen game;
	private Runner runner;
	private Hunter hunter;
	private Map map;
	private boolean enabled;
	private int nextUpdate, fireDelay;
	private double targetAngle;
	
	private ArrayList<Point> path;
	
	/**
	 * Creates a RunnerAI with the specified GameScreen.
	 * @param game The game using this AI.
	 */
	public RunnerAI(GameScreen game) {
		this.game = game;
		enabled = false;
		path = new ArrayList<Point>();
	}
	
	/**
	 * Initializes the AI. Call this before calling controlRunner(). 
	 */
	public void init() {
		runner = game.getRunner();
		hunter = game.getHunter();
		map = game.getMap();
		nextUpdate = 30;
		fireDelay = 50;
		targetAngle = Math.PI / 2;
		path.clear();
		if (isEnabled())
			loadMapPath();
	}
	
	private void loadMapPath() {
		// Choose a path to run through
		File dir = new File("data/" + map.getName());
		File[] paths = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.startsWith("path");
			}
		});
		if (paths.length == 0) {
			System.err.println("No path files found in data/" + map.getName());
			return;
		}
		int choice = randInt(0, paths.length - 1);
		Scanner sc;
		try {
			sc = new Scanner(paths[choice]);
		} catch (FileNotFoundException e) {
			System.err.println(e);
			return;
		}
		int numPoints = sc.nextInt();
		for (int i = 0; i < numPoints; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			path.add(new Point(x, y));
		}
		sc.close();
	}
	
	/**
	 * Controls the runner automatically.
	 * @postcondition The runner will have its velocities and rotation set according to the AI.
	 * @return Whether or not the runner should fire its weapon.
	 */
	public boolean controlRunner() {
		// Move the runner
		Point point = path.get(game.getTimer());
		runner.setX(point.x);
		runner.setY(point.y);
		
		// Rotate towards the hunter (on a delay)
		if (game.getTimer() == nextUpdate) {
			nextUpdate += randInt(15, 45);
			if (game.hunterAlive()) {
				double dx = hunter.getX() - runner.getX();
				double dy = hunter.getY() - runner.getY();
				double angle = Math.atan2(dy, dx);
				targetAngle = angle + randDouble(-0.4, 0.4);
				if (targetAngle < 0)
					targetAngle += 2 * Math.PI;
			} else {
				targetAngle = Math.PI / 2;
			}
		}
		double runnerAngle = runner.getAngle() % (2 * Math.PI);
		if (runnerAngle < 0)
			runnerAngle += 2 * Math.PI;
		double cwDist, ccwDist;
		if (runnerAngle > targetAngle) {
			cwDist = runnerAngle - targetAngle;
			ccwDist = 2 * Math.PI - cwDist;
		} else {
			ccwDist = targetAngle - runnerAngle;
			cwDist = 2 * Math.PI - ccwDist;
		}
		if (Math.min(cwDist, ccwDist) >= GameScreen.ROTATE_SPEED) {
			if (cwDist < ccwDist)
				runner.setAngle(runner.getAngle() - GameScreen.ROTATE_SPEED);
			else
				runner.setAngle(runner.getAngle() + GameScreen.ROTATE_SPEED);
		}
		
		// Decide whether to fire
		if (!game.hunterAlive())
			fireDelay = 30;
		if (fireDelay-- > 0)
			return false;
		Line2D line = new Line2D.Double(runner.getX(), runner.getY(), hunter.getX(), hunter.getY());
		for (Obstacle o : game.getObstacles()) {
			if (line.intersects(o.getHitBox()) && Math.random() < 0.95)
				return false;
		}
		return true;
	}
	
	/**
	 * Records the current position of the runner into the path.
	 */
	public void recordPath() {
		if (!isEnabled())
			path.add(new Point(runner.getX(), runner.getY()));
	}
	
	/**
	 * Prints the path the runner took during this round.
	 */
	public void printPath() {
		System.out.println("--------------------------------------------------");
		System.out.println("COPY THE BELOW INFORMATION (the map is " + map.getName() + ")");
		System.out.println("--------------------------------------------------");
		System.out.println(path.size());
		for (Point p : path) {
			System.out.printf("%d %d\n", p.x, p.y);
		}
	}
	
	private double randDouble(double a, double b) {
		return Math.random() * (b-a) + a;
	}
	
	private int randInt(int a, int b) {
		return (int) (Math.random() * (b-a+1)) + a;
	}
	
	/**
	 * Checks if the AI is enabled.
	 * @return The result.
	 */
	public boolean isEnabled() {
		return enabled;
	}
	
	/**
	 * Enables or disables runner AI.
	 * @param enabled The desired value.
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
