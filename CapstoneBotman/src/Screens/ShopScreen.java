package Screens;

import java.util.ArrayList;

import Main.DrawingSurface;
import Main.Item;
import Sprites.Weapon;
import g4p_controls.GButton;
import g4p_controls.GLabel;

/**
 * This is the Shop Screen which is displayed before the game begins when the
 * runners and hunters choose their guns and shields
 * 
 * @author Aayush Kumar
 *
 */
public class ShopScreen extends Screen {

	/**
	 * X-coordinate of the top right of the screen
	 */
	private int x;

	/**
	 * Y-coordinate of the top right of the screen
	 */
	private int y;

	/**
	 * PApplet surface where the game is drawn
	 */
	private DrawingSurface surface;

	/**
	 * Exit button that leads to the game screen once the weapon selection is done
	 */
	GButton exit;

	/**
	 * Currently does not do anything
	 */
	GLabel title;

	/**
	 * Currently does not do anything
	 */
	GLabel cash;

	/**
	 * Item that holds weapon of the HUNTER
	 */
	Weapon HunterWeapon;

	/**
	 * Item that holds weapon of the RUNNER
	 */
	Weapon RunnerWeapon;

	/**
	 * Amount of shields that the HUNTER has
	 */
	private int hunterShield;

	/**
	 * Amount of shields that the RUNNER has
	 */
	private int runnerShield;

	public ShopScreen(int width, int height, DrawingSurface surface) {
		super(width, height);
		this.surface = surface;

		x = 0;
		y = 0;
	}

	/**
	 * 
	 * @return amount of shields that the hunter has, between 0 and 50
	 */
	public int getHunterShields() {
		return hunterShield;
	}

	/**
	 * 
	 * @return amount of shields that the runner has, between 0 and 50
	 */
	public int getRunnerShields() {
		return runnerShield;
	}

	// public

}
