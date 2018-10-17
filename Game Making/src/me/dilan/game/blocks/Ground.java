package me.dilan.game.blocks;

import java.awt.Graphics;

import me.dilan.game.util.Bounds;
import me.dilan.game.util.Identification;
import me.dilan.game.view.ImageLoader;

public class Ground extends Block{
	
	public Ground(float x, float y) {
		super(x, y, 32, 32, Identification.BLOCK_GROUND);
		
		
		
	}

	
	public Bounds bounds() {
		return new Bounds().setBounds(x, y, width, height);
	}


	
	public void tick() {
		
	}


	
	public void render(Graphics g) {
				
		g.drawImage(ImageLoader.graybrick, (int) x, (int) y, null);
	
	}

	
	
}
