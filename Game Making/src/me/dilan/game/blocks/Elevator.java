package me.dilan.game.blocks;

import java.awt.Color;
import java.awt.Graphics;

import me.dilan.game.util.Bounds;
import me.dilan.game.util.Identification;

public class Elevator extends Block {
	
	public Elevator(float x, float y) {
		super(x, y, 32, 32, Identification.BLOCK_ELEVATOR);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.drawRect((int) x, (int) y, width, height);
		
	}

	@Override
	public Bounds bounds() {
		return new Bounds().setBounds(x, y, width, height);
	}
	
	

	
	
	
}
