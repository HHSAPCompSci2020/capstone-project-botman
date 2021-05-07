package Screens;

import Main.DrawingSurface;
import g4p_controls.GButton;
import g4p_controls.GLabel;

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
}
