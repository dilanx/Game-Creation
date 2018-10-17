package me.dilan.game.blocks;

import java.awt.Graphics;

import me.dilan.game.GObject;
import me.dilan.game.util.Identification;

public abstract class Block extends GObject{

	
	
	public Block(float x, float y, int width, int height, Identification id) {
		super(x, y, width, height, id);
	}
	
	public void objTick() {
		
		
		tick();
	}
	
	public abstract void tick();

	public void objRender(Graphics g) {
		
		render(g);
		
	}

	public abstract void render(Graphics g);
	
	
	
}
