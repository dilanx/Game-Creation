package me.dilan.game.util;

import java.awt.Rectangle;

public class Bounds {

	private Rectangle bounds, bottom, top, left, right;
	
	public Rectangle bounds() {
		return bounds;
	}
	
	public Rectangle top() {
		return top;
	}
	
	public Rectangle left() {
		return left;
	}
	
	public Rectangle right() {
		return right;
	}
	
	public Rectangle bottom() {
		return bottom;
	}

	public Bounds setBounds(float x, float y, int width, int height) {
		this.bounds = new Rectangle((int) x, (int) y, width, height);
		return this;
	}
	
	public Bounds setAllBounds(float x, float y, int width, int height) {
		
		
		this.bounds = new Rectangle((int) x, (int) y, width, height);
		this.bottom = new Rectangle((int) x+(width/2)-((width/2)/2), (int) y+(height/2), width/2, height/2 + 4);
		this.top = new Rectangle((int) x+(width/2)-((width/2)/2), (int) y, width/2, height/2);
		this.right = new Rectangle((int) x+width-(width/8), (int) y+(width/8), width/8, height-((width/8) * 2));
		this.left = new Rectangle((int) x, (int) y+(width/8), width/8, height-((width/8) * 2));
		
		return this;
	}
	
	public Bounds setBottom(float x, float y, int width, int height) {
		this.bottom = new Rectangle((int) x, (int) y, width, height);
		return this;
	}

	public Bounds setTop(float x, float y, int width, int height) {
		this.top = new Rectangle((int) x, (int) y, width, height);
		return this;
	}

	public Bounds setLeft(float x, float y, int width, int height) {
		this.left = new Rectangle((int) x, (int) y, width, height);
		return this;
	}

	public Bounds setRight(float x, float y, int width, int height) {
		this.right = new Rectangle((int) x, (int) y, width, height);
		return this;
	}
	
	
	
}
