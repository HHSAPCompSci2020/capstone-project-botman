package Screens;

import Main.DrawingSurface;
import g4p_controls.GButton;
import g4p_controls.GEvent;
import g4p_controls.GLabel;
import processing.core.PConstants;

/**
 * This is the pause screen that is displayed when the player(s) pause the game
 * from the game screen
 * 
 * @author Aayush Kumar
 *
 */
public class PauseScreen extends Screen {

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
	 * Button that resets the timer and leads back to the game screen
	 */
	GButton restart;

	/**
	 * Button that leads back to the game screen without any effects
	 */
	GButton resume;

	/**
	 * Button that quits the game and leads back to the main screen
	 */
	GButton mainMenu;

	/**
	 * Label that does not do anything currently
	 */
	GLabel title;

	/**
	 * Sets up x and y to an initial value of 0 and stores the width, height, and surface locally
	 * 
	 * @param width Initial width of the screen
	 * @param height Initial height of the screen
	 * @param surface PApplet where the screen is drawn
	 */
	public PauseScreen(int width, int height, DrawingSurface surface) {
		super(width, height);
		this.surface = surface;

		x = 0;
		y = 0;
	}

	/**
	 * Sets up resume, restart, and main menu buttons
	 */
	public void setup() {
		resume = new GButton(surface, 90, 100, 200, 50, "Resume");
		restart = new GButton(surface, 90, 175, 200, 50, "Restart");
		mainMenu = new GButton(surface, 90, 250, 200, 50, "Back to Main Menu");

		// title.addEventHandler(instructions, "handleButtonEvents");
		resume.addEventHandler(this, "handleButtonEvents");
		restart.addEventHandler(this, "handleButtonEvents");
		mainMenu.addEventHandler(this, "handleButtonEvents");

		resume.setVisible(false);
		restart.setVisible(false);
		mainMenu.setVisible(false);
	}

	/**
	 * Draws the pause menu and buttons on the screen
	 */
	public void draw() {
		surface.pushStyle();
		surface.background(0, 0, 0);
		surface.fill(255, 255, 0);

		resume.setVisible(true);
		restart.setVisible(true);
		mainMenu.setVisible(true);

		// draws Game Paused title
		surface.textAlign(PConstants.CENTER, PConstants.CENTER);
		surface.textSize(35);
		surface.text("Game Paused", 200, 50);
	}

	/**
	 * Handles the events and their consequences on different buttons
	 * 
	 * @param button Button that has the event performed on it
	 * @param event  Event which gets performed on the button
	 */
	public void handleButtonEvents(GButton button, GEvent event) {
		// if resume is clicked, switch to instructions screen
		if (button == resume && event == GEvent.CLICKED) {
			
			surface.switchScreen(DrawingSurface.GAME_SCREEN);
		}

		// if play is clicked, switch to game screen
		if (button == restart && event == GEvent.CLICKED) {
			// gameScreen switch
			Screen gameScreen = surface.getScreen(DrawingSurface.GAME_SCREEN);
			if (gameScreen instanceof GameScreen) {
				((GameScreen) gameScreen).startGame();
			}
			
			//surface.switchScreen(3);
		}

		// if play is clicked, switch to game screen
		if (button == mainMenu && event == GEvent.CLICKED) {

			surface.switchScreen(DrawingSurface.MAIN_SCREEN);
		}

		// removes buttons
		resume.setVisible(false);
		restart.setVisible(false);
		mainMenu.setVisible(false);
	}

}
