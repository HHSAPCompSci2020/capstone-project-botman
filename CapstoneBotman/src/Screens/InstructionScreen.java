package Screens;

import Main.DrawingSurface;
import g4p_controls.GButton;
import g4p_controls.GEvent;
import g4p_controls.GLabel;
import processing.core.PConstants;
import processing.core.PImage;

/**
 * This is the instructions screen that is displayed when the instructions button is clicked from other screens in the game
 * 
 * @author Aayush Kumar
 *
 */
public class InstructionScreen extends Screen{

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
	GButton backButton;
	
	/**
	 * Label that does not do anything currently
	 */
	GLabel instructions;
	
	// backgorund
	PImage back;
	
	/**
	 * Sets up x and y to an initial value of 0 and stores the width, height, and surface locally
	 * 
	 * @param width Initial width of the screen
	 * @param height Initial height of the screen
	 * @param surface PApplet where the screen is drawn
	 */
	public InstructionScreen(int width, int height, DrawingSurface surface) {
		super(width, height);
		this.surface = surface;
		
		x = 0;
		y = 0;
		
		//backButton = new GButton(surface, height, height, height, height);
	}
	
	/**
	 * Sets up instructions button and back button
	 */
	public void setup() {
		instructions = new GLabel(surface, 90, 100, 200, 50, "Instructions");
		backButton = new GButton(surface, 90, 450, 200, 50, "Back");
		
		
		//title.addEventHandler(instructions, "handleButtonEvents");
		//instructions.addEventHandler(this, "handleButtonEvents");
		backButton.addEventHandler(this, "handleButtonEvents");
		
		instructions.setVisible(false);
		backButton.setVisible(false);
	}
	
	/**
	 * Draws the title, instructions, and buttons on the screen
	 */
	public void draw() {
		surface.pushStyle();
		surface.background(0, 0, 0);
		surface.fill(255, 255, 0);
		
		//instructions.setVisible(true);
		backButton.setVisible(true);
		
		// draws title and buttons
//		title = new GLabel(surface, 150, 50, 500, 50, "VALHUNT");
//		title.setLocalColor(45, 500);
		
		// draws VALHUNT title
		surface.textAlign(PConstants.CENTER, PConstants.CENTER);
		surface.textSize(50);
		surface.text("VALHUNT", 200, 50);
		
		// draws instructions
		surface.fill(0, 255, 0);
		surface.textSize(15);
		surface.text("Hunter: \nWASD to move\nMouse to aim\nMouse left click to shoot", 200, 400);
		
		surface.fill(255, 0, 0);
		surface.text("Runner:\nArrow keys to move\nG to rotate aim left\nH to rotate aim right\nSpace to shoot", 200, 275);
		
		surface.textSize(13);
		surface.fill(255, 255, 255);
		surface.text("Objective: \nRunner needs to survive 30s without dying to win that round\n and during that time\n Hunter needs to kill the Runner to win that round\n(Hunter respawns 5s after being killed)\nThe winner is the one who wins three rounds first.", 200, 150);
	}
	
	/**
	 * Handles the events and their consequences on different buttons
	 * 
	 * @param button Button that has the event performed on it
	 * @param event Event which gets performed on the button
	 */
	public void handleButtonEvents(GButton button, GEvent event) {
		// if play is clicked, switch to game screen
		if (button == backButton && event == GEvent.CLICKED) {
			surface.switchScreen(DrawingSurface.MAIN_SCREEN);
			
			// removes buttons
			instructions.setVisible(false);
			backButton.setVisible(false);
		}
	}
}
