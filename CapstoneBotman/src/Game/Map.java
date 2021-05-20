package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Screens.GameScreen;

/**
 * 
 * The Map class represents a map in the game using a 2D array of characters.
 * 
 * @author Kyle Fu
 *
 */
public class Map {
	
	private GameScreen game;
	private int scrollSpeed;
	private int tileOffset, tileSize;
	private int healthAmount, moneyAmount;
	private String song;
	
	private char[][] map;
	
	private int scrollPos, currRow;
	
	/**
	 * Creates a new map with an empty field (no objects).
	 * @param game The GameScreen that will use this map.
	 * @param mapHeight The height of the map in # of tiles.
	 * @param mapWidth The width of the map in # of tiles.
	 * @param scrollSpeed The scroll speed of the map.
	 * @param healthAmount The amount of health a health pickup restores.
	 * @param moneyAmount The amount of money a money pickup provides.
	 * @param song The background music for this map.
	 * @precondition scrollSpeed > 0
	 */
	public Map(GameScreen game, int mapHeight, int mapWidth, int scrollSpeed, int healthAmount, int moneyAmount, String song) {
		this.game = game;
		// Center the tiles
		this.tileSize = game.WIDTH / mapWidth;
		this.tileOffset = ((game.WIDTH % mapWidth) + 1) / 2;
		this.scrollSpeed = scrollSpeed;
		this.healthAmount = healthAmount;
		this.moneyAmount = moneyAmount;
		this.song = song;
		scrollPos = game.HEIGHT;
		currRow = mapHeight - 1;
		
		map = new char[mapHeight][mapWidth];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = '.';
			}
		}
	}
	
	/**
	 * Creates a new map using a file template.
	 * 
	 * The given file should be in the following format:<br>
	 * Line 1: The width of the map (in # of tiles), and the scroll speed of the map, separated by a space.<br>
	 * Line 2: The health restored by a health pickup, and the money earned by a money pickup, separated by a space.<br>
	 * Line 3: The song associated with the map.<br>
	 * The rest of the lines: Each row contains [tile width] characters denoting the map.<br>
	 * 
	 * @param filename The filename of the map. Should be a .txt file in the /data directory.
	 * @param gameScreen The game screen using this map generator.
	 */
	public Map(String filename, GameScreen game) {
		this.game = game;
		Scanner sc;
		try {
			sc = new Scanner(new File("data/" + filename));
		} catch (FileNotFoundException e) {
			System.err.println("Map file not found: " + filename);
			return;
		}
		
		// Read map from file
		int mapWidth = sc.nextInt();
		int scrollSpeed = sc.nextInt();
		int healthAmount = sc.nextInt();
		int moneyAmount = sc.nextInt();
		sc.nextLine();
		String song = sc.nextLine();
		ArrayList<String> data = new ArrayList<String>();
		while (sc.hasNextLine()) {
			String str = sc.nextLine();
			data.add(str);
		}
		int mapHeight = data.size();
		
		// Setup the map
		this.tileSize = game.WIDTH / mapWidth;
		this.tileOffset = ((game.WIDTH % mapWidth) + 1) / 2;
		this.scrollSpeed = scrollSpeed;
		this.healthAmount = healthAmount;
		this.moneyAmount = moneyAmount;
		this.song = song;
		this.scrollPos = game.HEIGHT;
		currRow = mapHeight - 1;
		
		map = new char[mapHeight][mapWidth];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = data.get(i).charAt(j);
			}
		}
	}
	
	/**
	 * Scrolls the map by the given amount.
	 * @param amount The amount to scroll.
	 * @return Whether or not the map was actually scrolled (is it at the end?)
	 */
	public boolean scroll(int amount) {
		if (map.length - scrollPos / tileSize != -1) {
			scrollPos += amount;
			return true;
		} else return false;
	}
	
	/**
	 * Generates the map. Call this once every frame in GameScreen.
	 * @postcondition Required objects are spawned in using GameScreen's spawn methods.
	 */
	public void generate() {
		int targetRow = Math.max(map.length - scrollPos / tileSize, -1);
		while (currRow > targetRow) {
			for (int j = 0; j < map[0].length; j++) {
				int x = tileOffset + tileSize / 2 + j * tileSize;
				int y = scrollPos - (map.length - currRow) * tileSize - 150;
				char c = map[currRow][j];
				if (c == 'X') {
					game.spawnObstacle(x, y, 40, 40);
				} else if (c == 'H') {
					game.spawnHealthPickup(x, y, 30, 20, healthAmount);
				} else if (c == '$') {
					game.spawnMoneyPickup(x, y, 30, 15, moneyAmount);
				} else if (c == 'G') {
					game.spawnGoal(x, y, 200, 150);
				} else if (c == 'g') {
					game.spawnGoal(x + tileSize / 2, y, 200, 150);
				} else if (c == '.') {
					// Spawn nothing
				} else {
					System.err.println("Unknown character '" + c + "'!");
				}
			}
			currRow--;
		}
	}
	
	/**
	 * Gets the current scroll speed.
	 * @return The scroll speed.
	 */
	public int getScrollSpeed() {
		return scrollSpeed;
	}
	
	/**
	 * Gets the song associated with this map.
	 * @return The song.
	 */
	public String getSong() {
		return song;
	}
	
}
