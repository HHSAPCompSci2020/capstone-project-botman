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
			//surface.switchScreen(surface.SHOP_SCREEN);

			// removes buttons
			exit.setVisible(true);
			nextVisible = false;
		}
		
		if ((button == rButton || button == sButton || button == shButton || button == halfArmor || button == fullArmor) && event == GEvent.CLICKED) {
			//hShop = false;
			//surface.switchScreen(surface.SHOP_SCREEN);

			if (hShop) {
				if (button == rButton) {
					HunterWeapon = rifle;
					
					System.out.println("Hunter bought rifle");
				} else if (button == sButton) {
					HunterWeapon = sniper;
					System.out.println("Hunter bought sniper");
				} else if (button == shButton){
					HunterWeapon = shotgun;
					System.out.println("Hunter bought shotgun");
				} else if (button == halfArmor){
					hunterShield = 25;
					System.out.println("Hunter bought half armor");
				} else if (button == fullArmor){
					hunterShield = 50;
					System.out.println("Hunter bought full armor");
				}
				
			} else {
				if (button == rButton) {
					RunnerWeapon = rifle;
					System.out.println("Runner bought rifle");
				} else if (button == sButton) {
					RunnerWeapon = sniper;
					System.out.println("Runner bought sniper");
				} else if (button == shButton){
					RunnerWeapon = shotgun;
					System.out.println("Runner bought shotgun");
				} else if (button == halfArmor){
					hunterShield = 25;
					System.out.println("Runner bought half armor");
				} else if (button == fullArmor){
					hunterShield = 50;
					System.out.println("Runner bought full armor");
				}
				
				exit.setVisible(true);
				
				nextVisible = false;
			}
			
			// removes buttons
			
		}

		// if
	}
	// public

}
