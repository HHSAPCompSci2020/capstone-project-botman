package Screens;

import Main.DrawingSurface;
import g4p_controls.GButton;
import g4p_controls.GLabel;

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
	
}
