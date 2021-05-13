package Screens;

import java.util.ArrayList;

import Main.DrawingSurface;
import Main.Item;
import Sprites.Rifle;
import Sprites.Shotgun;
import Sprites.SniperRifle;
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

	private Rifle rifle;
	private SniperRifle sniper;
	private Shotgun shotgun;

	private GButton rButton;
	private GButton sButton;
	private GButton shButton;

	private GButton halfArmor;
	private GButton fullArmor;

	boolean hShop;

	int hCash;
	int rCash;

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
		hShop = true;

		hunterShield = 0;
		runnerShield = 0;

		hCash = 1000;
		rCash = 1000;

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
		exit = new GButton(surface, 0, 500, 400, 50, "Exit");
		next = new GButton(surface, 0, 500, 400, 50, "Next");

		rButton = new GButton(surface, 25, 175 - 15, 200, 30, "Buy Rifle");
		sButton = new GButton(surface, 25, 300 - 15, 200, 30, "Buy Sniper");
		shButton = new GButton(surface, 25, 425 - 15, 200, 30, "Buy Shotgun");

		halfArmor = new GButton(surface, 250, 100, 125, 175, "Half\nArmor");
		fullArmor = new GButton(surface, 250, 300, 125, 175, "Full\nArmor");

		rifle = new Rifle(150 - 25, 150 + 15 - 15, 200, 50, surface);
		sniper = new SniperRifle(150 - 25, 275 + 15 - 15, 200, 50, surface);
		shotgun = new Shotgun(150 - 25, 425 - 15, 200, 50, surface);

		// title.addEventHandler(instructions, "handleButtonEvents");
		exit.addEventHandler(this, "handleButtonEvents");
		next.addEventHandler(this, "handleButtonEvents");

		rButton.addEventHandler(this, "handleButtonEvents");
		sButton.addEventHandler(this, "handleButtonEvents");
		shButton.addEventHandler(this, "handleButtonEvents");

		halfArmor.addEventHandler(this, "handleButtonEvents");
		fullArmor.addEventHandler(this, "handleButtonEvents");

		exit.setVisible(false);
		next.setVisible(false);

		rButton.setVisible(false);
		sButton.setVisible(false);
		shButton.setVisible(false);

		halfArmor.setVisible(false);
		fullArmor.setVisible(false);
	}

	/**
	 * Draws the title and buttons on the screen
	 */
	public void draw() {
		surface.pushStyle();
		surface.background(255, 255, 255);

		if (hShop)
			surface.fill(255, 0, 0);
		else
			surface.fill(0, 150, 0);

		rButton.setVisible(true);
		sButton.setVisible(true);
		shButton.setVisible(true);

		halfArmor.setVisible(true);
		fullArmor.setVisible(true);

		// exit.setVisible(true);
		if (nextVisible)
			next.setVisible(true);
		else
			next.setVisible(false);

		rifle.draw(surface);
		sniper.draw(surface);
		shotgun.draw(surface);

		if (hShop)
			surface.stroke(255, 0, 0);
		else
			surface.stroke(0, 150, 0);

		surface.strokeWeight(5);
		surface.line(-5, 100, 500, 100);
		surface.line(-5, 515, 515, 515);
		surface.line(250, 100, 250, 515);

		// draws title and buttons
//		title = new GLabel(surface, 150, 50, 500, 50, "VALHUNT");
//		title.setLocalColor(45, 500);

		// draws VALHUNT title
		surface.textAlign(PConstants.CENTER, PConstants.CENTER);
		surface.textSize(35);
		if (hShop)
			surface.text("Hunter Shop", 200, 50);
		else {
			surface.text("Runner Shop", 200, 50);
		}
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
				// ((GameScreen) gameScreen).beginRound();
				((GameScreen) gameScreen).beginRound();
			}

			// removes buttons
			exit.setVisible(false);
			next.setVisible(false);
			rButton.setVisible(false);
			sButton.setVisible(false);
			shButton.setVisible(false);
			halfArmor.setVisible(false);
			fullArmor.setVisible(false);
			nextVisible = true;
		}

		//
		if (button == next && event == GEvent.CLICKED) {
			hShop = false;
			// surface.switchScreen(surface.SHOP_SCREEN);

			// removes buttons
			exit.setVisible(true);
			nextVisible = false;
		}

		if ((button == rButton || button == sButton || button == shButton) && event == GEvent.CLICKED) {
			// hShop = false;
			// surface.switchScreen(surface.SHOP_SCREEN);

			Screen gameScreen = surface.getScreen(surface.GAME_SCREEN);

			((GameScreen) gameScreen).getHunter().setCash(1000);
			((GameScreen) gameScreen).getRunner().setCash(1000);

			hCash = ((GameScreen) gameScreen).getHunter().getCash();
			System.out.println("The hunter has $" + hCash + " to begin with");

			rCash = ((GameScreen) gameScreen).getRunner().getCash();

			if (hShop) {

				// rifle
				if (button == rButton) {

					HunterWeapon = rifle;

					if (hCash >= 100 && gameScreen instanceof GameScreen) {
						hCash -= 100;
						// ((GameScreen) gameScreen).getHunter().setCash(hCash - 100);
						System.out.println("Hunter bought rifle");
					}

					// sniper
				} else if (button == sButton) {
					HunterWeapon = sniper;

					if (hCash >= 150 && gameScreen instanceof GameScreen) {
						hCash -= 150;
						// ((GameScreen) gameScreen).getHunter().setCash(hCash - 150);
						System.out.println("Hunter bought sniper");
					}

					// shotgun
				} else if (button == shButton) {

					HunterWeapon = shotgun;

					if (hCash >= 75 && gameScreen instanceof GameScreen) {
						hCash -= 75;
						// ((GameScreen) gameScreen).getHunter().setCash(hCash - 75);
						System.out.println("Hunter bought shotgun");
					}

					// half armor
				} else if (button == halfArmor) {

					// hunterShield = 25;

					if (hCash >= 25 && gameScreen instanceof GameScreen) {
						hCash -= 25;
						// ((GameScreen) gameScreen).getHunter().setCash(hCash - 25);
						System.out.println("Hunter bought half armor");
					}

					// full armor
				} else if (button == fullArmor) {

					// hunterShield = 50;

					if (hCash >= 50 && gameScreen instanceof GameScreen) {
						hCash -= 50;
						// ((GameScreen) gameScreen).getHunter().setCash(hCash - 50);
						System.out.println("Hunter bought full armor");
					}

				}

				((GameScreen) gameScreen).getHunter().setCash(hCash);

				System.out.println("The hunter has $" + hCash + " remaining\n");

			} else {

				// Screen gameScreen = surface.getScreen(surface.GAME_SCREEN);

				// rifle
				if (button == rButton) {
					RunnerWeapon = rifle;

					if (rCash >= 100 && gameScreen instanceof GameScreen) {
						rCash -= 100;
						((GameScreen) gameScreen).getRunner().setCash(rCash - 100);
						System.out.println("Runner bought rifle");
					}

					// sniper
				} else if (button == sButton) {

					RunnerWeapon = sniper;

					if (rCash >= 150 && gameScreen instanceof GameScreen) {
						rCash -= 150;
						((GameScreen) gameScreen).getRunner().setCash(rCash - 150);
						System.out.println("Runner bought sniper");
					}

					// shotgun
				} else if (button == shButton) {

					RunnerWeapon = shotgun;

					if (rCash >= 75 && gameScreen instanceof GameScreen) {
						rCash -= 75;
						((GameScreen) gameScreen).getRunner().setCash(rCash - 75);
						System.out.println("Runner bought shotgun");
					}

					// half amror
				} else if (button == halfArmor) {

					// hunterShield = 25;

					if (rCash >= 25 && gameScreen instanceof GameScreen) {
						// rCash -= 25;
						((GameScreen) gameScreen).getRunner().setCash(rCash - 25);
						System.out.println("Runner bought half armor");
					}

					// full armor
				} else if (button == fullArmor) {

					// hunterShield = 50;

					if (rCash >= 50 && gameScreen instanceof GameScreen) {
						// rCash -= 50;
						((GameScreen) gameScreen).getRunner().setCash(rCash - 50);
						System.out.println("Runner bought full armor");
					}

				}

				System.out.println("The runner has $" + rCash + " remaining\n");

				exit.setVisible(true);

				nextVisible = false;
			}

			// removes buttons

		}

		if ((button == halfArmor || button == fullArmor) && event == GEvent.CLICKED) {

			Screen gameScreen = surface.getScreen(surface.GAME_SCREEN);

			// ((GameScreen) gameScreen).getHunter().setCash(1000);
			// ((GameScreen) gameScreen).getRunner().setCash(1000);

			hCash = ((GameScreen) gameScreen).getHunter().getCash();
			// System.out.println(hCash);

			rCash = ((GameScreen) gameScreen).getRunner().getCash();
			// System.out.println(rCash);

			// hunter shields
			if (hShop) {

				if (button == halfArmor) {

					// hunterShield = 25;
					// hCash -= 25;

					if (hCash >= 25 && gameScreen instanceof GameScreen && hunterShield == 0) {
						hCash -= 25;
						((GameScreen) gameScreen).getRunner().setCash(hCash - 25);
						//((GameScreen) gameScreen).get
						System.out.println("Hunter bought half armor");
					}

					((GameScreen) gameScreen).getHunter().setCash(hCash);

					hunterShield = 25;

					// full armor
				} else if (button == fullArmor) {

					if (hCash >= 50 && gameScreen instanceof GameScreen && hunterShield == 0) {
						hCash -= 50;
						((GameScreen) gameScreen).getRunner().setCash(hCash - 50);
						System.out.println("Hunter bought full armor");
					}

					((GameScreen) gameScreen).getRunner().setCash(hCash);

					hunterShield = 50;
				}

				System.out.println("The hunter has $" + hCash + " remaining\n");

				// System.out.println(hCash);

				// runner shields
			} else {

				// System.out.println(rCash);

				if (button == halfArmor) {

					// rCash += 100;
					// rCash -= 25;

					if (rCash >= 25 && gameScreen instanceof GameScreen && runnerShield == 0) {
						rCash += 100;
						rCash -= 25;
						System.out.println(rCash);
						((GameScreen) gameScreen).getRunner().setCash(rCash);
						System.out.println("Runner bought half armor");
					}

					((GameScreen) gameScreen).getHunter().setCash(rCash);

					runnerShield = 25;

					// full armor
				} else if (button == fullArmor) {

					if (rCash >= 50 && gameScreen instanceof GameScreen && runnerShield == 0) {
						rCash += 100;
						rCash -= 50;
						((GameScreen) gameScreen).getRunner().setCash(rCash);
						System.out.println("Runner bought full armor");
					}

					((GameScreen) gameScreen).getRunner().setCash(rCash);

					runnerShield = 50;

				}

				System.out.println("The runner has $" + rCash + " remaining\n");

			}

		}

		// if
	}
	// public

}
