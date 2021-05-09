package Screens;

import Main.DrawingSurface;
import g4p_controls.GButton;
import g4p_controls.GEvent;
import g4p_controls.GLabel;
import processing.core.PConstants;

public class MainScreen extends Screen {

	private int x, y;
	private DrawingSurface surface;
	
	GButton instructions, play;
	GLabel title;
	
	public MainScreen(int width, int height, DrawingSurface surface) {
		super(width, height);
		this.surface = surface;
		
		x = 0;
		y = 0;
		
	}
	
	public void setup() {
		instructions = new GButton(surface, 90, 100, 200, 50, "Instrucitons");
		play = new GButton(surface, 90, 175, 200, 50, "Play");
		
		//title.addEventHandler(instructions, "handleButtonEvents");
		instructions.addEventHandler(this, "handleButtonEvents");
		play.addEventHandler(this, "handleButtonEvents");
	}
	
	public void draw() {
		surface.pushStyle();
		surface.background(0, 0, 0);
		surface.fill(255, 255, 0);
		
		// draws title and buttons
//		title = new GLabel(surface, 150, 50, 500, 50, "VALHUNT");
//		title.setLocalColor(45, 500);
		
		// VALHUNT title drawn
		surface.textAlign(PConstants.CENTER, PConstants.CENTER);
		surface.textSize(50);
		surface.text("VALHUNT", 200, 50);
	}
	
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
