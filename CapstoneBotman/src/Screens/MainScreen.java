package Screens;

import Main.DrawingSurface;
import g4p_controls.GButton;
import g4p_controls.GEvent;
import g4p_controls.GLabel;
import processing.core.PConstants;

/**
 * This is the main screen that is run at the beginning of the program, and is the first screen the user interacts with
 * 
 * @author Aayush Kumar
 *
 */
public class MainScreen extends Screen {

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
	 * Button that leads to the instructions screen
	 */
	GButton instructions;
	
	/**
	 * Button that leads to the play screen
	 */
	GButton play;
	
	/**
	 * Title that does not display anything currently
	 */
	GLabel title;
	
	/**
	 * Sets up x and y to an initial value of 0 and stores the width, height, and surface locally
	 * 
	 * @param width Initial width of the screen
	 * @param height Initial height of the screen
	 * @param surface PApplet where the screen is drawn
	 */
	public MainScreen(int width, int height, DrawingSurface surface) {
		super(width, height);
		this.surface = surface;
		
		x = 0;
		y = 0;
		
	}
	
	/**
	 * Sets up instructions button and play button
	 */
	public void setup() {
		instructions = new GButton(surface, 90, 100, 200, 50, "Instructions");
		play = new GButton(surface, 90, 175, 200, 50, "Play");
		
		//title.addEventHandler(instructions, "handleButtonEvents");
		instructions.addEventHandler(this, "handleButtonEvents");
		play.addEventHandler(this, "handleButtonEvents");
		
		instructions.setVisible(false);
		play.setVisible(false);
	}
	
	/**
	 * Draws the title and buttons on the screen
	 */
	public void draw() {
		surface.pushStyle();
		surface.background(0, 0, 0);
		surface.fill(255, 255, 0);
		
		instructions.setVisible(true);
		play.setVisible(true);
		
		// draws title and buttons
//		title = new GLabel(surface, 150, 50, 500, 50, "VALHUNT");
//		title.setLocalColor(45, 500);
		
		// draws VALHUNT title
		surface.textAlign(PConstants.CENTER, PConstants.CENTER);
		surface.textSize(50);
		surface.text("VALHUNT", 200, 50);
	}
	
	/**
	 * Handles the events and their consequences on different buttons
	 * 
	 * @param button Button that has the event performed on it
	 * @param event Event which gets performed on the button
	 */
	public void handleButtonEvents(GButton button, GEvent event) {
		// if play is clicked, switch to instructions screen
		if (button == instructions && event == GEvent.CLICKED) {
			surface.switchScreen(1);
			
			// removes buttons
			instructions.setVisible(false);
			play.setVisible(false);
		}
		
		// if play is clicked, switch to game screen
		if (button == play && event == GEvent.CLICKED) {
			surface.switchScreen(3);
			
			// removes buttons
			instructions.setVisible(false);
			play.setVisible(false);
		}
	}

}
