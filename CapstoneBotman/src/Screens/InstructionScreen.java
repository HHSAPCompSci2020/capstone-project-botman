package Screens;

import Main.DrawingSurface;
import g4p_controls.GButton;
import g4p_controls.GEvent;
import g4p_controls.GLabel;
import processing.core.PConstants;

public class InstructionScreen extends Screen{

	private int x, y;
	private DrawingSurface surface;
	
	GButton backButton;
	GLabel instructions;
	
	public InstructionScreen(int width, int height, DrawingSurface surface) {
		super(width, height);
		this.surface = surface;
		
		x = 0;
		y = 0;
		
		//backButton = new GButton(surface, height, height, height, height);
	}
	
	/**
	 * Sets up instructions button and play button
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
	 * Draws the title and buttons on the screen
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
		surface.textSize(20);
		surface.text("Hunter: \nWASD to move\nMouse to aim\nMouse left click to shoot", 200, 150);
		
		surface.fill(255, 0, 0);
		surface.text("Runer: \nArrow keys to move\nG to rotate aim left\nH to rotate aim right\nSpace to shoot", 200, 350);
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
			surface.switchScreen(0);
			
			// removes buttons
			instructions.setVisible(false);
			backButton.setVisible(false);
		}
	}
}
