package Screens;

import java.util.ArrayList;

import Main.DrawingSurface;
import Main.Item;
import Sprites.Weapon;
import g4p_controls.GButton;
import g4p_controls.GEvent;
import g4p_controls.GLabel;
import processing.core.PConstants;

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
	 * Next button that leads to the runner weapon
	 */
	GButton next;

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
	
	/**
	 * Tracks if the next button is visible
	 */
	boolean nextVisible;

	/**
	 * 
	 * @param width
	 * @param height
	 * @param surface
	 */
	public ShopScreen(int width, int height, DrawingSurface surface) {
		super(width, height);
		this.surface = surface;
		
		nextVisible = true;

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

	/**
	 * Sets up instructions button and play button
	 */
	public void setup() {
		exit = new GButton(surface, 90, 450, 200, 50, "Exit");
		next = new GButton(surface, 90, 400, 200, 50, "Next");

		// title.addEventHandler(instructions, "handleButtonEvents");
		exit.addEventHandler(this, "handleButtonEvents");
		next.addEventHandler(this, "handleButtonEvents");

		exit.setVisible(false);
		next.setVisible(false);
	}

	/**
	 * Draws the title and buttons on the screen
	 */
	public void draw() {
		surface.pushStyle();
		surface.background(0, 0, 0);
		surface.fill(255, 255, 0);

		//exit.setVisible(true);
		if (nextVisible)
			next.setVisible(true);
		else
			next.setVisible(false);

		// draws title and buttons
//		title = new GLabel(surface, 150, 50, 500, 50, "VALHUNT");
//		title.setLocalColor(45, 500);

		// draws VALHUNT title
		surface.textAlign(PConstants.CENTER, PConstants.CENTER);
		surface.textSize(35);
		surface.text("Shop", 200, 50);
	}

	/**
	 * Handles the events and their consequences on different buttons
	 * 
	 * @param button Button that has the event performed on it
	 * @param event  Event which gets performed on the button
	 */
	public void handleButtonEvents(GButton button, GEvent event) {
		// if play is clicked, switch to instructions screen
		if (button == exit && event == GEvent.CLICKED) {
			Screen gameScreen = surface.getScreen(3);
			if (gameScreen instanceof GameScreen) {
				//((GameScreen) gameScreen).beginRound();
				((GameScreen) gameScreen).beginRound();
			}

			// removes buttons
			exit.setVisible(false);
			next.setVisible(false);
			nextVisible = true;
		}

		// if play is clicked, switch to game screen
		if (button == next && event == GEvent.CLICKED) {
			// gameScreen switch
			Screen gameScreen = surface.getScreen(3);
			if (gameScreen instanceof GameScreen) {
				//((GameScreen) gameScreen).beginRound();
				((GameScreen) gameScreen).startGame();
			}
			
			surface.switchScreen(surface.SHOP_SCREEN);

			// removes buttons
			exit.setVisible(true);
			nextVisible = false;
		}
	}
	// public

}
