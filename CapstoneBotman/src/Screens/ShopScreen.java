package Screens;

import java.util.ArrayList;

import Main.DrawingSurface;
import Main.Item;
import g4p_controls.GButton;
import g4p_controls.GLabel;

public class ShopScreen extends Screen{

	private int x, y;
	private DrawingSurface surface;
	
	GButton exit;
	GLabel title, cash;
	ArrayList<Item> items;
	
	public ShopScreen(int width, int height, DrawingSurface surface) {
		super(width, height);
		this.surface = surface;
		
		x = 0;
		y = 0;
	}

}
