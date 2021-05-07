package Screens;

import Main.DrawingSurface;
import Sprites.Player;

public class GameScreen extends Screen {
	
	private DrawingSurface surface;
	
	private Player runner;
	private Player hunter;

	public GameScreen(int width, int height, DrawingSurface surface) {
		super(width, height);
		this.surface = surface;
	}
	
	@Override
	public void draw() {
		surface.pushStyle();
		surface.background(255, 200, 255);
		
		surface.fill(0, 100, 0);
		surface.text("<Insert game here>", 200, 200);
		
		surface.popStyle();
	}
	
}
