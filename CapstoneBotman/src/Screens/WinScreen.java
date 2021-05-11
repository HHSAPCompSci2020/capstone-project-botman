package Screens;

import Main.DrawingSurface;
import g4p_controls.GButton;
import g4p_controls.GEvent;
import g4p_controls.GLabel;
import processing.core.PConstants;

/**
 * This is the Win Screen which is displayed when the game has ended and a
 * certain character (hunter or runner) has won
 * 
 * @author Aayush Kumar
 *
 */
public class WinScreen extends Screen {

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
	 * Button that leads back to the main screen
	 */
	GButton back;

	/**
	 * Label that currently does nothing
	 */
	GLabel win;

	/**
	 * Keeps track of the number of wins that the hunter has accumulated
	 */
	private int runnerWins;

	/**
	 * Keeps track of the number of wins that the runner has accumulated
	 */
	private int hunterWins;

	/**
	 * Sets up x, y, hunterWins, and runnerWins to an initial value of 0 and stores
	 * the width, height, and surface locally
	 * 
	 * @param width   Initial width of the screen
	 * @param height  Initial height of the screen
	 * @param surface PApplet where the screen is drawn
	 */
	public WinScreen(int width, int height, DrawingSurface surface) {
		super(width, height);
		this.surface = surface;

		x = 0;
		y = 0;

		hunterWins = 0;
		runnerWins = 0;
	}

	/**
	 * Sets up back button
	 */
	public void setup() {
		back = new GButton(surface, 90, 100, 200, 50, "Back to Main Menu");

		// title.addEventHandler(instructions, "handleButtonEvents");
		back.addEventHandler(this, "handleButtonEvents");

		back.setVisible(false);
	}

	/**
	 * Draws the win menu and buttons on the screen
	 */
	public void draw() {
		surface.pushStyle();
		surface.background(0, 0, 0);
		surface.fill(255, 255, 0);

		back.setVisible(true);

		// draws Game Paused title
		surface.textAlign(PConstants.CENTER, PConstants.CENTER);
		surface.textSize(35);

		// declares winner
		Screen gameScreen = surface.getScreen(3);
		if (gameScreen instanceof GameScreen) {
			runnerWins = ((GameScreen) gameScreen).getRunner().getWins();
			hunterWins = ((GameScreen) gameScreen).getHunter().getWins();
		}

		if (hunterWins > runnerWins) {
			surface.text("Hunter Wins!!!", 200, 50);
		} else if (hunterWins < runnerWins) {
			surface.text("Runner Wins!!!", 200, 50);
		} else {
			surface.text("Draw!!!", 200, 50);
		}

	}

	/**
	 * Handles the events and their consequences on different buttons
	 * 
	 * @param button Button that has the event performed on it
	 * @param event  Event which gets performed on the button
	 */
	public void handleButtonEvents(GButton button, GEvent event) {
		// if resume is clicked, switch to instructions screen
		if (button == back && event == GEvent.CLICKED) {

			surface.switchScreen(0);
		}
		// removes buttons
		back.setVisible(false);
	}

}
