package Main;

import processing.core.PImage;

public class Item {
	
	private int cost;
	private String name;
	private PImage image;
	private boolean selected;
	
	public Item(int cost, String name, PImage image, boolean selected) {
		this.cost = cost;
		this.name = name;
		this.image = image;
		this.selected = selected;
	}
	
	// getter methods
	public int getCost() {
		return cost;
	}
	
	public String getName() {
		return name;
	}
	
	public PImage getImage() {
		return image;
	}

	public boolean isSelected() {
		return selected;
	}
	
	// select item
	// changes to the opposite of the current selection state of the item
	public void selectItem() {
		selected = !(selected);
		
	}
}
